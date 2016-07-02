package cn.mmb.b2b.user.domain.service;

import cn.mmb.b2b.user.domain.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : anchao
 * @ClassName:mmb2b
 * @Description:公司资质
 * @Date : 2015/12/18 0018 上午 10:58
 */
public interface RoleServiceInterface {

    List<Role> getRoleListByUserName(String userName);

}
