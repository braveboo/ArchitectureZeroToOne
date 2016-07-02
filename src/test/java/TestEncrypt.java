import cn.mmb.b2b.user.domain.entity.User;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.Key;
import java.util.UUID;

/**
 *
 **/
public class TestEncrypt {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private final int hashIterations = 2;

    @Test
    public void testGeneratPass(){
//        ApplicationContext ac  = new ClassPathXmlApplicationContext("spring.xml");
//        PasswordHelper ph = (PasswordHelper) ac.getBean("passwordHelper");

        User u = new User();
        u.setUserName("lb");
        u.setPassword("12345");
        u.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                u.getPassword(),
                ByteSource.Util.bytes(u.getCredentialsSalt()),
                Integer.valueOf(hashIterations)).toHex();

        u.setPassword(newPassword);
        System.out.println(u.getPassword());
        System.out.println(u.getSalt());

        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void testUuid(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        System.out.println(org.apache.shiro.codec.Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
    }

    @Test
    public void testAes(){
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128); //设置 key 长度
//生成 key
        Key key = aesCipherService.generateNewKey();
        System.out.println(new String(Base64.encode(key.getEncoded())));
        String text = "hello";
//加密
        String encrptText =
                aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
//解密
        String text2 =
                new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
        Assert.assertEquals(text, text2);
    }

}
