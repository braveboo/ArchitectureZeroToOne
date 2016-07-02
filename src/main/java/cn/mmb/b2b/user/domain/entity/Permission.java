package cn.mmb.b2b.user.domain.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created with IDEA
 * User : anchao
 * Date : 2015/12/10 0010 下午 14:27
 */
@Alias("permission")
public class Permission implements Serializable{

    private static final long serialVersionUID = -5753168549518686355L;

    /**UUID*/
    private String id;

    /**权限名称*/
    private String name;

    /**权限编号*/
    private String code;

    /**url*/
    private String url;

    /**
     * 权限表达式,example:
     * user:create
     * 表示有用户的添加权限
     * */
    private String expression;

    public Permission() {
    }

    public Permission(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
