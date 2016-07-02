package cn.mmb.b2b.user.infrastructure;

import cn.mmb.b2b.user.ConfigInfo;
import cn.mmb.b2b.user.domain.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * shiro生成密码服务
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Resource
    private ConfigInfo configInfo;
//    private String algorithmName = "md5";
//    private final int hashIterations = 2;

    public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                configInfo.getAlgorithmName(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                Integer.valueOf(configInfo.getHashIterations())).toHex();

        user.setPassword(newPassword);
    }

	/**
     * 加密，根据用户名、密码、salt获取加密之后的密码
     * @author likaige
     * @param userName 用户名
     * @param password 原密码，未加密的
     * @param salt salt
     * @return 加密之后的密码
     */
    public String encryptPassword(String userName, String password, String salt){
        User user = new User();
        user.setUserName(userName);
        user.setSalt(salt);
        String newPassword = new SimpleHash(
                configInfo.getAlgorithmName(),
                password,
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                Integer.valueOf(configInfo.getHashIterations())).toHex();
        return newPassword;
    }
}
