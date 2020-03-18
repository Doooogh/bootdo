package com.bootdo.filedetail.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.filedetail.domain.FileDetailDO;
import com.bootdo.filedetail.service.FileDetailService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 15:00:30
 */
 
@Controller
@RequestMapping("/filedetail/fileDetail")
public class FileDetailController {
	@Autowired
	private FileDetailService fileDetailService;
	
	@GetMapping()
	@RequiresPermissions("filedetail:fileDetail:fileDetail")
	String FileDetail(){
	    return "filedetail/fileDetail/fileDetail";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("filedetail:fileDetail:fileDetail")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<FileDetailDO> fileDetailList = fileDetailService.list(query);
		int total = fileDetailService.count(query);
		PageUtils pageUtils = new PageUtils(fileDetailList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("filedetail:fileDetail:add")
	String add(){
	    return "filedetail/fileDetail/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("filedetail:fileDetail:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		FileDetailDO fileDetail = fileDetailService.get(id);
		model.addAttribute("fileDetail", fileDetail);
	    return "filedetail/fileDetail/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("filedetail:fileDetail:add")
	public R save( FileDetailDO fileDetail){
		if(fileDetailService.save(fileDetail)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("filedetail:fileDetail:edit")
	public R update( FileDetailDO fileDetail){
		fileDetailService.update(fileDetail);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("filedetail:fileDetail:remove")
	public R remove( Integer id){
		if(fileDetailService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("filedetail:fileDetail:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		fileDetailService.batchRemove(ids);
		return R.ok();
	}
	
}
