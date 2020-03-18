package com.bootdo.student.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.UserDO;
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

import com.bootdo.student.domain.StudentDO;
import com.bootdo.student.service.StudentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-18 22:05:52
 */
 
@Controller
@RequestMapping("/student/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping()
	@RequiresPermissions("student:student:student")
	String Student(){
	    return "student/student/student";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:student:student")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentDO> studentList = studentService.list(query);
		int total = studentService.count(query);
		PageUtils pageUtils = new PageUtils(studentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:student:add")
	String add(){
	    return "student/student/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:student:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		StudentDO student = studentService.get(id);
		model.addAttribute("student", student);
	    return "student/student/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:student:add")
	public R save( StudentDO student){
		if(studentService.save(student)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:student:edit")
	public R update( StudentDO student){
		studentService.update(student);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:student:remove")
	public R remove( Integer id){
		if(studentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:student:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		studentService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/getTree")
	@ResponseBody
	public Tree<UserDO> getTree(){
		Tree<UserDO> treeByDeptId = studentService.getTreeByDeptId();
		return treeByDeptId;
	}

	@GetMapping("/getTreeSelected")
	@ResponseBody
	public Tree<UserDO> getTreeSelected(){
		Tree<UserDO> treeByDeptId = studentService.getTreeByUserId();
		return treeByDeptId;
	}
	
}
