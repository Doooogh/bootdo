package com.bootdo.leavemsg.service;

import com.bootdo.leavemsg.domain.LeavemsgDO;

import java.util.List;
import java.util.Map;

/**
 * 留言
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 22:46:28
 */
public interface LeavemsgService {
	
	LeavemsgDO get(Integer id);
	
	List<LeavemsgDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeavemsgDO leavemsg);
	
	int update(LeavemsgDO leavemsg);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 脏字过滤
	 * @param content
	 */
	public void filterZZ(String content);


	/**
	 * 判断是否有脏字
	 * @param content
	 * @return
	 */
	public boolean hasZZ(String content);
}
