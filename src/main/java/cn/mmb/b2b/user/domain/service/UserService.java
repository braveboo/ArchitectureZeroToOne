package cn.mmb.b2b.user.domain.service;

import cn.mmb.b2b.user.domain.entity.User;
import cn.mmb.b2b.user.infrastructure.PasswordHelper;
import cn.mmb.b2b.user.infrastructure.persistence.UserDaoInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IDEA
 * User : anchao
 * Date : 2015/12/16 0016 上午 10:52
 */
@Service
public class UserService implements UserServiceInterface {

    @Resource
    UserDaoInterface userDaoInterface;
    @Resource
    PasswordHelper passwordHelper;

    @Override
    public User getUserByUserName(String userName) {
        return userDaoInterface.getUserByUserName(userName);
    }
}
