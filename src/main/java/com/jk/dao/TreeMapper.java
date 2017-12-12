package com.jk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jk.model.Tree;

public interface TreeMapper {

	/** <pre>queryTree(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月17日 下午2:31:02    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月17日 下午2:31:02    
	 * 修改备注： 
	 * @param tree
	 * @return</pre>    
	 */
	List<Tree> queryTree(Tree tree) throws Exception;

	/** <pre>asyncTreeList(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月17日 下午2:46:20    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月17日 下午2:46:20    
	 * 修改备注： 
	 * @param tree
	 * @return</pre>    
	 */
	List<Tree> asyncTreeList(Tree tree)throws Exception;

	/** <pre>updateAfterSave(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月20日 上午10:38:59    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月20日 上午10:38:59    
	 * 修改备注： 
	 * @param tree</pre>    
	 */
	void updateAfterSave(Tree tree)throws Exception;

	/** <pre>deleteTreeNodes(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月20日 下午1:45:21    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月20日 下午1:45:21    
	 * 修改备注： 
	 * @param tree</pre>    
	 */
	void deleteTreeNodes(Tree tree)throws Exception;

	/** <pre>addAfterSave(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月20日 下午2:00:29    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月20日 下午2:00:29    
	 * 修改备注： 
	 * @param tree</pre>    
	 */
	void addAfterSave(Tree tree)throws Exception;

	/** <pre>insertMany(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月22日 下午4:17:38    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月22日 下午4:17:38    
	 * 修改备注： 
	 * @param list</pre>    
	 */
	void insertMany(List<Tree> list)throws Exception;

	List<Tree> queryTree2(@Param("tree") Tree tree, @Param("id") String id);

}