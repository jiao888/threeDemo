package com.jk.service;

import java.util.List;
import java.util.Map;

import com.jk.model.Role;

public interface RoleService {

	List<Role> getrole() throws Exception;

	void deleteuser(String userId) throws Exception;

	void insertRole(String string, String userId)throws Exception;

	List<Map<String, Object>> querysettree(String roleId)throws Exception;

	void setTreeChange(String treeids, String roleId)throws Exception;


}
