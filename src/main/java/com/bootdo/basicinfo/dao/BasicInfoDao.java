package com.bootdo.basicinfo.dao;

import com.bootdo.basicinfo.domain.BasicInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-08 17:53:30
 */
@Mapper
public interface BasicInfoDao {

	BasicInfoDO get(Integer id);
	
	List<BasicInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BasicInfoDO basicInfo);
	
	int update(BasicInfoDO basicInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
