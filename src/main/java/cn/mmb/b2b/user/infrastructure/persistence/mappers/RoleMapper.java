package cn.mmb.b2b.user.infrastructure.persistence.mappers;

import cn.mmb.b2b.user.domain.entity.Role;
import cn.mmb.b2b.user.infrastructure.persistence.RoleDaoInterface;
import com.mmb.framework.support.DynamicDataSource;
import org.apache.ibatis.spring.support.AbstractDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : anchao
 * @ClassName:mmb2b
 * @Description:公司资质
 * @Date : 2015/12/18 0018 上午 11:00
 */
@Repository
public class RoleMapper extends AbstractDaoSupport implements RoleDaoInterface {
    @Override
    public List<Role> getRoleListByUserName(String userName) {
        return this.getSession(DynamicDataSource.SLAVE).selectList(userName);
    }

}
