package com.jk.service;

import java.util.List;

import com.jk.model.Tree;

public interface TreeService {

	/** <pre>queryTree(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月17日 下午2:29:32    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月17日 下午2:29:32    
	 * 修改备注： 
	 * @param tree
	 * @param id 
	 * @return</pre>    
	 */
	List<Tree> queryTree(Tree tree) throws Exception;

	/** <pre>asyncTreeList(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月17日 下午2:45:38    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月17日 下午2:45:38    
	 * 修改备注： 
	 * @param tree
	 * @return</pre>    
	 */
	List<Tree> asyncTreeList(Tree tree)throws Exception;

	/** <pre>updateAfterSave(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月20日 上午10:38:23    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月20日 上午10:38:23    
	 * 修改备注： 
	 * @param tree</pre>    
	 */
	void updateAfterSave(Tree tree) throws Exception;

	/** <pre>deleteTreeNodes(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月20日 下午1:44:47    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月20日 下午1:44:47    
	 * 修改备注： 
	 * @param tree</pre>    
	 */
	void deleteTreeNodes(Tree tree)throws Exception;

	/** <pre>addAfterSave(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月20日 下午1:59:44    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月20日 下午1:59:44    
	 * 修改备注： 
	 * @param tree</pre>    
	 */
	void addAfterSave(Tree tree)throws Exception;

	/** <pre>insertMany(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月22日 下午4:16:49    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月22日 下午4:16:49    
	 * 修改备注： 
	 * @param list</pre>    
	 */
	void insertMany(List<Tree> list)throws Exception;

	List<Tree> queryTree2(Tree tree, String id);

}
