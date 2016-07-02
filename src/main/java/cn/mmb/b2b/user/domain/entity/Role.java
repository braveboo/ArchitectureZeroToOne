package cn.mmb.b2b.user.domain.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * User : anchao
 * Date : 2015/12/10 0010 下午 14:26
 */
@SuppressWarnings("Convert2Diamond")
@Alias("role")
public class Role implements Serializable{

    private static final long serialVersionUID = -2880031798225909929L;

    /**UUID*/
    private String id;

    /**角色名称*/
    private String name;

    /**角色编号*/
    private String code;

    /**角色类型[1:系统角色；2:自定义角色]*/
    private int type;


    /**状态*/
    private int status;

    /**所有权限*/
    private List<Permission> permissions = new ArrayList<Permission>();

    /**操作人*/
    private String operator;

    /**操作时间*/
    private String operateTime;

    /**别名*/
    private String alias;

    private Boolean isCancel;

    public Boolean getCancel() {
        return isCancel;
    }

    public void setCancel(Boolean cancel) {
        isCancel = cancel;
    }

    public Role() {
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
}
