package com.bootdo.leavemsg.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.leavemsg.dao.LeavemsgDao;
import com.bootdo.leavemsg.domain.LeavemsgDO;
import com.bootdo.leavemsg.service.LeavemsgService;



@Service
public class LeavemsgServiceImpl implements LeavemsgService {
	@Autowired
	private LeavemsgDao leavemsgDao;

	public final String zz[]=new String[]{"草","操","傻逼","傻X","你妈","你爸"};
	
	@Override
	public LeavemsgDO get(Integer id){
		return leavemsgDao.get(id);
	}
	
	@Override
	public List<LeavemsgDO> list(Map<String, Object> map){
		return leavemsgDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return leavemsgDao.count(map);
	}
	
	@Override
	public int save(LeavemsgDO leavemsg){

		return leavemsgDao.save(leavemsg);
	}
	
	@Override
	public int update(LeavemsgDO leavemsg){
		return leavemsgDao.update(leavemsg);
	}
	
	@Override
	public int remove(Integer id){
		return leavemsgDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return leavemsgDao.batchRemove(ids);
	}

	@Override
	public void filterZZ(String content) {
		for (String s : zz) {
			if(content.contains(s)){
				content.replaceAll("s","*");
			}
		}
	}

	@Override
	public boolean hasZZ(String content) {
		for (String s : zz) {
			if(content.contains(s)){
				return true;
			}
		}
		return false;
	}


}
