package com.bootdo.classinfo.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.classinfo.domain.ClassinfoDO;
import com.bootdo.classinfo.service.ClassinfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 班级介绍
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 23:56:21
 */
 
@Controller
@RequestMapping("/classinfo/classinfo")
public class ClassinfoController {
	@Autowired
	private ClassinfoService classinfoService;
	
	@GetMapping()
	@RequiresPermissions("classinfo:classinfo:classinfo")
	String Classinfo(){
	    return "classinfo/classinfo/classinfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("classinfo:classinfo:classinfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ClassinfoDO> classinfoList = classinfoService.list(query);
		int total = classinfoService.count(query);
		PageUtils pageUtils = new PageUtils(classinfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("classinfo:classinfo:add")
	String add(){
	    return "classinfo/classinfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("classinfo:classinfo:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ClassinfoDO classinfo = classinfoService.get(id);
		model.addAttribute("classinfo", classinfo);
	    return "classinfo/classinfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("classinfo:classinfo:add")
	public R save( ClassinfoDO classinfo){
		if(classinfoService.save(classinfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("classinfo:classinfo:edit")
	public R update( ClassinfoDO classinfo){
		classinfoService.update(classinfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("classinfo:classinfo:remove")
	public R remove( Long id){
		if(classinfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("classinfo:classinfo:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		classinfoService.batchRemove(ids);
		return R.ok();
	}
	
}
