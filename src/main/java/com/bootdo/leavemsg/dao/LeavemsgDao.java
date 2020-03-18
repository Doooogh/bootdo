package com.bootdo.leavemsg.dao;

import com.bootdo.leavemsg.domain.LeavemsgDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 留言
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 22:46:28
 */
@Mapper
public interface LeavemsgDao {

	LeavemsgDO get(Integer id);
	
	List<LeavemsgDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeavemsgDO leavemsg);
	
	int update(LeavemsgDO leavemsg);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
