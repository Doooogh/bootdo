package com.bootdo.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.bootdo.common.utils.ShiroUtils;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.DeptService;


@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao sysDeptMapper;

    @Override
    public DeptDO get(Long deptId) {
        return sysDeptMapper.get(deptId);
    }

    @Override
    public List<DeptDO> list(Map<String, Object> map) {
        return sysDeptMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysDeptMapper.count(map);
    }

    @Override
    public int save(DeptDO sysDept) {
        return sysDeptMapper.save(sysDept);
    }

    @Override
    public int update(DeptDO sysDept) {
        return sysDeptMapper.update(sysDept);
    }

    @Override
    public int remove(Long deptId) {
        return sysDeptMapper.remove(deptId);
    }

    @Override
    public int batchRemove(Long[] deptIds) {
        return sysDeptMapper.batchRemove(deptIds);
    }

    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        //根据用户的部门来查询tree 部门
        Long deptId = ShiroUtils.getUser().getDeptId();
        Map map=new HashMap();
        map.put("deptId",deptId);
        List<DeptDO> allList = list(new HashMap<>());
        boolean isAdmin=false;
        Long nowDeptId=null;
        if(ShiroUtils.getUserId()==1L){
            nowDeptId=0L;
            isAdmin=true;
        }else{
            nowDeptId=ShiroUtils.getUser().getDeptId();
        }
        List<DeptDO> sysDepts = getAllChilDept(allList,new ArrayList<>(),nowDeptId);
            if(!isAdmin){
            DeptDO deptDO = get(nowDeptId);
            sysDepts.add(deptDO);
        }

        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees,String.valueOf(null==get((nowDeptId))?"0":get(nowDeptId).getParentId()));
        return t;
    }

    @Override
    public boolean checkDeptHasUser(Long deptId) {
        // TODO Auto-generated method stub
        //查询部门以及此部门的下级部门
        int result = sysDeptMapper.getDeptUserNumber(deptId);
        return result == 0 ? true : false;
    }

    @Override
    public List<Long> listChildrenIds(Long parentId) {
        List<DeptDO> deptDOS = list(null);
        return treeMenuList(deptDOS, parentId);
    }

    @Override
    public List<DeptDO> listAllChildDept(String deptId) {
        List<DeptDO> allChildList=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("deptId",deptId);
        List<DeptDO> list = list(map);
        Map<String,Object> findMap=new HashMap<>();

        return null;
    }

    @Override
    public List<DeptDO> listNoChildDept() {
        List<DeptDO> allList = list(new HashMap<>());
        List<DeptDO> list=new ArrayList<>();
        p:for (DeptDO deptDO : allList) {
            boolean hasChild=false;
            c:for (DeptDO aDo : allList) {
                if(aDo.getParentId()==deptDO.getDeptId()){  //有子集
                    hasChild=true;
                    break c;
                }
            }
            if(!hasChild){
                list.add(deptDO);
            }
        }
        return list;
    }

    public List<DeptDO> getAllChilDept(List<DeptDO> allList,List<DeptDO> list,Long parentId){
        for (DeptDO deptDO : allList) {
            if(deptDO.getParentId()==parentId){
                list.add(deptDO);
                getAllChilDept(allList,list,deptDO.getDeptId());
            }
        }
        return list;
    }

    List<Long> treeMenuList(List<DeptDO> menuList, long pid) {
        List<Long> childIds = new ArrayList<>();
        for (DeptDO mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getParentId() == pid) {
                //递归遍历下一级
                treeMenuList(menuList, mu.getDeptId());
                childIds.add(mu.getDeptId());
            }
        }
        return childIds;
    }

}
