package com.bootdo.basicinfo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-08 17:53:30
 */
public class BasicInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//姓名
	private String name;
	//性别 1男 2女
	private String sex;
	//生日
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	//身高
	private String height;
	//体重
	private String weight;
	//过敏史
	private String allergicHistory;
	//疾病史
	private String diseasesHistory;
	//家族病史
	private String faDiseasesHistory;
	//其他
	private String others;
	//用户id
	private Integer userId;

	//用户名
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：性别 1男 2女
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 1男 2女
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：生日
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：身高
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * 获取：身高
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * 设置：体重
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * 获取：体重
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * 设置：过敏史
	 */
	public void setAllergicHistory(String allergicHistory) {
		this.allergicHistory = allergicHistory;
	}
	/**
	 * 获取：过敏史
	 */
	public String getAllergicHistory() {
		return allergicHistory;
	}
	/**
	 * 设置：疾病史
	 */
	public void setDiseasesHistory(String diseasesHistory) {
		this.diseasesHistory = diseasesHistory;
	}
	/**
	 * 获取：疾病史
	 */
	public String getDiseasesHistory() {
		return diseasesHistory;
	}
	/**
	 * 设置：家族病史
	 */
	public void setFaDiseasesHistory(String faDiseasesHistory) {
		this.faDiseasesHistory = faDiseasesHistory;
	}
	/**
	 * 获取：家族病史
	 */
	public String getFaDiseasesHistory() {
		return faDiseasesHistory;
	}
	/**
	 * 设置：其他
	 */
	public void setOthers(String others) {
		this.others = others;
	}
	/**
	 * 获取：其他
	 */
	public String getOthers() {
		return others;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserId() {
		return userId;
	}
}
