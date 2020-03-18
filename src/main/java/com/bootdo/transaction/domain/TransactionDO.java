package com.bootdo.transaction.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 班级事务
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 23:44:13
 */
public class TransactionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//班级事务
	private String content;
	//班级id
	private Long deptId;
	//创建人
	private Long createBy;
	//创建时间
	private Date createDate;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：班级事务
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：班级事务
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：班级id
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：班级id
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
}
