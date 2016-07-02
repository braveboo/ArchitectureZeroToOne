package cn.mmb.b2b.user.domain.service;

import cn.mmb.b2b.user.domain.entity.Role;
import cn.mmb.b2b.user.infrastructure.persistence.RoleDaoInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : anchao
 * @ClassName:mmb2b
 * @Description:公司资质
 * @Date : 2015/12/18 0018 上午 10:56
 */
@Service
public class RoleService implements RoleServiceInterface {

    @Resource
    RoleDaoInterface roleDaoInterface;

    public List<Role> getRoleListByUserName(String userName) {
        return roleDaoInterface.getRoleListByUserName(userName);
    }
}
