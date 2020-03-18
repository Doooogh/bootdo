package com.bootdo.student.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-18 22:05:52
 */
public class StudentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer userId;
	//同学id
	private Integer stuId;

	//同学名称
	private String stuName;

	private List<Integer> studentIds;

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public List<Integer> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<Integer> studentIds) {
		this.studentIds = studentIds;
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
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：同学名称
	 */
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	/**
	 * 获取：同学名称
	 */
	public Integer getStuId() {
		return stuId;
	}
}
