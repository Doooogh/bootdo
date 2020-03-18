package com.bootdo.classinfo.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 班级介绍
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 23:56:21
 */
public class ClassinfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//班级介绍
	private String introduce;
	//班级文化
	private String culture;
	//班级id
	private Long deptId;
	//创建人
	private String createBy;
	//
	private Date createDate;
	//修改时间
	private Date updateDate;
	//是否是最新 0不是 1 是
	private Integer isNew;

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
	 * 设置：班级介绍
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	/**
	 * 获取：班级介绍
	 */
	public String getIntroduce() {
		return introduce;
	}
	/**
	 * 设置：班级文化
	 */
	public void setCulture(String culture) {
		this.culture = culture;
	}
	/**
	 * 获取：班级文化
	 */
	public String getCulture() {
		return culture;
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
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：是否是最新 0不是 1 是
	 */
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	/**
	 * 获取：是否是最新 0不是 1 是
	 */
	public Integer getIsNew() {
		return isNew;
	}
}
