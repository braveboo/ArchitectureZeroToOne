package cn.mmb.b2b.user.infrastructure.persistence;

import cn.mmb.b2b.user.domain.entity.Role;

import java.util.List;

/**
 * @author : anchao
 * @ClassName:mmb2b
 * @Description:公司资质
 * @Date : 2015/12/18 0018 上午 10:59
 */
public interface RoleDaoInterface {
    List<Role> getRoleListByUserName(String userName);

}
