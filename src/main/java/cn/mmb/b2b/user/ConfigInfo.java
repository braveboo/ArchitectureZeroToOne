package cn.mmb.b2b.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统配置属性
 * <br/>数据来源于：sysconf.properties
 */
@Component
public class ConfigInfo {
    @Value("${qualification_attachment_path_root}")
    private String qualificationAttachmentPathRoot;

    /**服务器路径，包含端口*/
    @Value("${server_path}")
    private String serverPath;
    /**应用上下文*/
    @Value("${context_path}")
    private String contextPath;

    /**系统邮箱服务器*/
    @Value("${sys_email_smtp}")
    private String sysEmailSmtp;
    /**系统邮箱发件人*/
    @Value("${sys_email_from}")
    private String sysEmailFrom;
    /**系统邮箱用户名*/
    @Value("${sys_email_username}")
    private String sysEmailUsername;
    /**系统邮箱密码*/
    @Value("${sys_email_password}")
    private String sysEmailPassword;

	/**登录密码重试次数*/
	@Value("${retry_limit_count}")
	private String retryLimitCount;

	/**获取加密算法名称*/
	@Value("${algorithmName}")
	private String algorithmName;

	/**获取hash次数*/
	@Value("${hashIterations}")
	private String hashIterations;

	public String getHashIterations() {
		return hashIterations;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public String getRetryLimitCount() {
		return retryLimitCount;
	}

	public String getQualificationAttachmentPathRoot() {
		return qualificationAttachmentPathRoot;
	}

	public String getServerPath() {
		return serverPath;
	}

	public String getContextPath() {
		return contextPath;
	}

	public String getSysEmailSmtp() {
		return sysEmailSmtp;
	}

	public String getSysEmailFrom() {
		return sysEmailFrom;
	}

	public String getSysEmailUsername() {
		return sysEmailUsername;
	}

	public String getSysEmailPassword() {
		return sysEmailPassword;
	}
}
