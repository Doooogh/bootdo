package com.bootdo.filedetail.service;

import com.bootdo.filedetail.domain.FileDetailDO;
import com.bootdo.filedetail.domain.FileDetailVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 15:00:30
 */
public interface FileDetailService {
	
	FileDetailDO get(Integer id);
	
	List<FileDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDetailDO fileDetail);
	
	int update(FileDetailDO fileDetail);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<FileDetailVO> getList(Map<String, Object> map);

	int getListCount(Map<String, Object> map);


}
