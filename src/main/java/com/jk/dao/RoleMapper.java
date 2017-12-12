package com.jk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jk.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	/** <pre>selectRoleList(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月28日 下午4:00:25    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月28日 下午4:00:25    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Role> selectRoleList();

	void deleteuser(@Param("userId") String userId)throws Exception;

	void insertRole(@Param("string") String string, @Param("userId") String userId)throws Exception;

	List<Map<String, Object>> querysettree(String roleId)throws Exception;

	void deleteByRoleId(@Param("roleId") String roleId)throws Exception;

	void addsetTree(List<Map<String, Object>> list)throws Exception;
}