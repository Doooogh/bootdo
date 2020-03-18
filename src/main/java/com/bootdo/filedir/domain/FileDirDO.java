package com.bootdo.filedir.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 15:27:35
 */
public class FileDirDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//相册目录名称
	private String name;
	//创建人
	private Integer createBy;
	//创建时间
	private Date createDate;
	//类型
	private Integer type;

	private String cuName;  //创建人

	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：相册目录名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：相册目录名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateBy() {
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
	/**
	 * 设置：类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public Integer getType() {
		return type;
	}
}
