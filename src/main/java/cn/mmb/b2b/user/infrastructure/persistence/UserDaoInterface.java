package cn.mmb.b2b.user.infrastructure.persistence;

import cn.mmb.b2b.user.domain.entity.User;

/**
 * Created with IDEA
 * User : anchao
 * Date : 2015/12/16 0016 上午 11:06
 */
public interface UserDaoInterface {

    User getUserByUserName(String userName);
}
