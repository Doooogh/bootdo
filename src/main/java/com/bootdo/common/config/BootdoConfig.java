package com.bootdo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="bootdo")
public class BootdoConfig {
	//上传路径
	private String uploadPath;

	private String username;

	private String password;

	private boolean codeValidate;

	private String defaultDeptId;


	private String defaultRoleId;

	private String adminId;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getDefaultRoleId() {
		return defaultRoleId;
	}

	public void setDefaultRoleId(String defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}

	public String getDefaultDeptId() {
		return defaultDeptId;
	}

	public void setDefaultDeptId(String defaultDeptId) {
		this.defaultDeptId = defaultDeptId;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getCodeValidate() {
		return codeValidate;
	}

	public void setCodeValidate(boolean codeValidate) {
		this.codeValidate = codeValidate;
	}
}
