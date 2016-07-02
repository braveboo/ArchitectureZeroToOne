package cn.mmb.b2b.user.domain.service;

import cn.mmb.b2b.user.domain.entity.User;

/**
 * Created with IDEA
 * User : anchao
 * Date : 2015/12/16 0016 上午 10:46
 */
public interface UserServiceInterface {

    User getUserByUserName(String userName);
}
