package com.jk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.RoleMapper;
import com.jk.model.Role;
import com.jk.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	public List<Role> getrole() {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleList();
	}

	@Override
	public void deleteuser(String userId) throws Exception {
		// TODO Auto-generated method stub
		roleMapper.deleteuser(userId);
	}

	@Override
	public void insertRole(String string, String userId) throws Exception {
		// TODO Auto-generated method stub
		roleMapper.insertRole(string,userId);
	}

	@Override
	public List<Map<String, Object>> querysettree(String roleId)throws Exception {
		
		return roleMapper.querysettree(roleId);
	}

	@Override
	public void setTreeChange(String treeids, String roleId) throws Exception {
		// TODO Auto-generated method stub
		roleMapper.deleteByRoleId(roleId);
		
		if (treeids!=null && !"".equals(treeids)) {
			String[] split = treeids.split(",");
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < split.length; i++) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("roleId", roleId);
				map.put("treeId", split[i].trim().toString());
				
				list.add(map);
			}
			roleMapper.addsetTree(list);
		}
		
		
		
	}
	
}
