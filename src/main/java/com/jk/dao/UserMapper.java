package com.jk.dao;

import java.util.List;
import java.util.Map;

import com.jk.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	/** <pre>selectUserList(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月21日 下午1:56:09    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月21日 下午1:56:09    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	List<User> selectUserList(User user) throws Exception;

	/** <pre>login(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月21日 下午2:09:56    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月21日 下午2:09:56    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	User login(User user)throws Exception;

	
	List<Map<String, Object>> getResourcesList(User login)throws Exception;
}