package com.bootdo.leavemsg.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 留言
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 22:46:28
 */
public class LeavemsgDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//给谁留言
	private Integer touserId;
	//留言者
	private Integer fromuserId;
	//留言内容
	private String content;
	//留言日期
	private Date createDate;
	//审批是否通过 0否 1是
	private Integer isPass;

	//发送者
	private String formUserName;

	//给谁留言
	private String toUserName;

	public String getFormUserName() {
		return formUserName;
	}

	public void setFormUserName(String formUserName) {
		this.formUserName = formUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：给谁留言
	 */
	public void setTouserId(Integer touserId) {
		this.touserId = touserId;
	}
	/**
	 * 获取：给谁留言
	 */
	public Integer getTouserId() {
		return touserId;
	}
	/**
	 * 设置：留言者
	 */
	public void setFromuserId(Integer fromuserId) {
		this.fromuserId = fromuserId;
	}
	/**
	 * 获取：留言者
	 */
	public Integer getFromuserId() {
		return fromuserId;
	}
	/**
	 * 设置：留言内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：留言内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：留言日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：留言日期
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：审批是否通过 0否 1是
	 */
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	/**
	 * 获取：审批是否通过 0否 1是
	 */
	public Integer getIsPass() {
		return isPass;
	}
}
