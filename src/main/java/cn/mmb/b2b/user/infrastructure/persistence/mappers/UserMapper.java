package cn.mmb.b2b.user.infrastructure.persistence.mappers;

import cn.mmb.b2b.user.domain.entity.User;
import cn.mmb.b2b.user.infrastructure.persistence.UserDaoInterface;
import org.apache.ibatis.spring.support.AbstractDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created with IDEA
 * User : anchao
 * Date : 2015/12/16 0016 上午 11:09
 */
@Repository
public class UserMapper extends AbstractDaoSupport implements UserDaoInterface {

    @Override
    public User getUserByUserName(String userName) {
        return this.getSession().selectOne(userName);
    }

}
