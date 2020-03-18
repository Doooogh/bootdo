package com.bootdo.basicinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.basicinfo.dao.BasicInfoDao;
import com.bootdo.basicinfo.domain.BasicInfoDO;
import com.bootdo.basicinfo.service.BasicInfoService;



@Service
public class BasicInfoServiceImpl implements BasicInfoService {
	@Autowired
	private BasicInfoDao basicInfoDao;
	
	@Override
	public BasicInfoDO get(Integer id){
		return basicInfoDao.get(id);
	}
	
	@Override
	public List<BasicInfoDO> list(Map<String, Object> map){
		return basicInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return basicInfoDao.count(map);
	}
	
	@Override
	public int save(BasicInfoDO basicInfo){
		return basicInfoDao.save(basicInfo);
	}
	
	@Override
	public int update(BasicInfoDO basicInfo){
		return basicInfoDao.update(basicInfo);
	}
	
	@Override
	public int remove(Integer id){
		return basicInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return basicInfoDao.batchRemove(ids);
	}
	
}
