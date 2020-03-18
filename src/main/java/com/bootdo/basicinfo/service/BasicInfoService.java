package com.bootdo.basicinfo.service;

import com.bootdo.basicinfo.domain.BasicInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-08 17:53:30
 */
public interface BasicInfoService {
	
	BasicInfoDO get(Integer id);
	
	List<BasicInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BasicInfoDO basicInfo);
	
	int update(BasicInfoDO basicInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
