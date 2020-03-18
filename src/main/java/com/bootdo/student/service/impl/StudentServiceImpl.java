package com.bootdo.student.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.student.dao.StudentDao;
import com.bootdo.student.domain.StudentDO;
import com.bootdo.student.service.StudentService;



@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DeptDao deptDao;
	
	@Override
	public StudentDO get(Integer id){
		return studentDao.get(id);
	}
	
	@Override
	public List<StudentDO> list(Map<String, Object> map){
		return studentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentDao.count(map);
	}
	
	@Override
	public int save(StudentDO student){
		Long userId = ShiroUtils.getUserId();
		Map<String,Object> map=new HashMap<>();
		map.put("userId",userId);
		List<StudentDO> list1 = list(map);
		int length=list1.size();

		Integer []ids=new Integer[length];
		for (int i = 0; i < list1.size(); i++) {
			ids[i]=list1.get(i).getId();
		}
		List<Integer> studentIds = student.getStudentIds();

		List<StudentDO> list=new ArrayList<>();
		for (Integer studentId : studentIds) {
			if(-1!=studentId){
				StudentDO newStu=new StudentDO();
				newStu.setUserId((int)(long)userId);
				newStu.setStuId(studentId);
				list.add(newStu);
			}
		}
		if(ids.length>0){
			studentDao.batchRemove(ids);
		}
		return studentDao.batchSave(list);
	}
	
	@Override
	public int update(StudentDO student){
		Long userId = ShiroUtils.getUserId();
		Map<String,Object> map=new HashMap<>();
		map.put("userId",userId);
		List<StudentDO> list1 = list(map);
		Integer []ids=new Integer[list1.size()];
		for (int i = 0; i < list1.size(); i++) {
			ids[i]=list1.get(i).getId();
		}
		List<Integer> studentIds = student.getStudentIds();
		List<StudentDO> list=new ArrayList<>();
		for (Integer studentId : studentIds) {
			StudentDO newStu=new StudentDO();
			newStu.setUserId((int)(long)userId);
			newStu.setStuId(studentId);
			list.add(newStu);
		}
		return studentDao.batchSave(list);
	}
	
	@Override
	public int remove(Integer id){
		return studentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return studentDao.batchRemove(ids);
	}

	@Override
	public Tree<UserDO> getTreeByDeptId() {
		Map<String, Object> map=new HashMap<>();
		map.put("userId",ShiroUtils.getUserId());
		map.put("deptId",ShiroUtils.getUser().getDeptId());
		DeptDO parentDept = deptDao.get(ShiroUtils.getUser().getDeptId());
		List<UserDO> list = userDao.getListByDeptId(map);
		List<Tree<UserDO>> trees = new ArrayList<Tree<UserDO>>();
		for (UserDO user : list) {
			Tree<UserDO> tree = new Tree<UserDO>();
			tree.setId(user.getUserId().toString());
			tree.setParentId(user.getDeptId().toString());
			tree.setText(user.getName());
			trees.add(tree);
		}
		Tree<UserDO> topNode=new Tree();
		topNode.setId("-1");
		topNode.setText(parentDept.getName());
		topNode.setHasParent(false);
		topNode.setChildren(true);
		topNode.setChecked(true);
		topNode.setChildren(trees);
		Map<String, Object> state = new HashMap<>(16);
		state.put("opened", true);
		topNode.setState(state);
		// 默认顶级菜单为０，根据数据库实际情况调整
		return topNode;
	}

	@Override
	public Tree<UserDO> getTreeByUserId() {
		Map<String, Object> map=new HashMap<>();
		map.put("userId",ShiroUtils.getUserId());
		map.put("deptId",ShiroUtils.getUser().getDeptId());
		DeptDO parentDept = deptDao.get(ShiroUtils.getUser().getDeptId());
		List<UserDO> list = userDao.getListByDeptId(map);  //查询出所有
		List<Tree<UserDO>> trees = new ArrayList<Tree<UserDO>>();

		List<StudentDO> studentDOS = studentDao.list(map);  //查出所有添加同学
		List<Long> studentIds=new ArrayList<>();
		for (StudentDO studentDO : studentDOS) {
			studentIds.add((long)(int)studentDO.getStuId());   //将useId添加到list
		}
		for (UserDO user : list) {
			Tree<UserDO> tree = new Tree<UserDO>();
			tree.setId(user.getUserId().toString());
			tree.setParentId(user.getDeptId().toString());
			tree.setText(user.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long userId = user.getUserId();
			if (studentIds.contains(userId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		Tree<UserDO> topNode=new Tree();
		topNode.setId("-1");
		topNode.setText(parentDept.getName());
		topNode.setHasParent(false);
		topNode.setChildren(true);
		topNode.setChecked(true);
		topNode.setChildren(trees);
		Map<String, Object> state = new HashMap<>(16);
		state.put("opened", true);
		topNode.setState(state);
		// 默认顶级菜单为０，根据数据库实际情况调整
		return topNode;
	}

}
