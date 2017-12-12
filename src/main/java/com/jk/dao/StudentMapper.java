package com.jk.dao;

import java.util.List;
import java.util.Map;

import com.jk.model.Student;

public interface StudentMapper {
    int insert(Student record);

    int insertSelective(Student record);

	/** <pre>findCountStudent(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月24日 上午10:23:51    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月24日 上午10:23:51    
	 * 修改备注： 
	 * @return</pre>    
	 */
	Long findCountStudent();

	/** <pre>findStudentList(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月24日 上午10:36:04    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月24日 上午10:36:04    
	 * 修改备注： 
	 * @param map
	 * @return</pre>    
	 */
	List<Map> findStudentList(Map map);
}