package cn.mmb.b2b.user.domain.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * User : anchao
 * Date : 2015/12/10 0010 下午 14:27
 */
@Alias("user")
public class User implements Serializable{

    private static final long serialVersionUID = 2213714348820228822L;

    /**UUID*/
    private String id;

    /**用户名*/
    private String userName;

    /**姓名*/
    private String name;

    /**性别，1：女，2：男*/
    private int gender;

    /**状态*/
    private int status;

    /**注册时间*/
    private String registerTime;

    /**密码*/
    private String password;

    /**电话号码*/
    private String phone;

    /**联系地址*/
    private String address;

    /**创建人*/
    private String createUser;

    /**最近一次登陆时间*/
    private String lastLoginTime;

    /**用户的所有角色*/
    private List<Role> roles = new ArrayList<Role>();

    /**
     * 加密随机盐
     */
    private String salt;


    private Boolean isCancel;

    public Boolean getCancel() {
        return isCancel;
    }

    public void setCancel(Boolean cancel) {
        isCancel = cancel;
    }

    public User() {
    }

    public Boolean isActived(){
        return true;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCredentialsSalt() {
        return userName + salt;
    }
}
