package cn.mmb.b2b.user.infrastructure;

import cn.mmb.b2b.user.domain.entity.Permission;
import cn.mmb.b2b.user.domain.entity.Role;
import cn.mmb.b2b.user.domain.entity.User;
import cn.mmb.b2b.user.domain.service.RoleService;
import cn.mmb.b2b.user.domain.service.UserService;
import com.google.common.collect.Sets;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * shiro授权和认证Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String)principals.getPrimaryPrincipal();
        List<Role> roleList = roleService.getRoleListByUserName(userName);
        Set<String> roleAliaSet = Sets.newHashSet();
        Set<String> permissionSet = Sets.newHashSet();
        List<Permission> permissionList = null;
        for(Role role : roleList){
            roleAliaSet.add(role.getAlias());
            permissionList = role.getPermissions();
            if(permissionList != null) {
                for (Permission p : permissionList) {
                    permissionSet.add(p.getExpression());
                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleAliaSet);
        authorizationInfo.setStringPermissions(permissionSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();
        User user = userService.getUserByUserName(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(!Boolean.TRUE.equals(user.isActived())) {
            throw new DisabledAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
