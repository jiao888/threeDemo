package com.jk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.TreeMapper;
import com.jk.model.Tree;
import com.jk.service.TreeService;

@Service("treeService")
public class TreeServiceImpl implements TreeService {

	@Autowired
	private TreeMapper treeMapper;

	/* (non-Javadoc)    
	 * @see com.jk.service.TreeService#queryTree(com.jk.model.Tree)    
	 */
	@Override
	public List<Tree> queryTree(Tree tree) throws Exception {
		List<Tree> list= treeMapper.queryTree(tree);
		return list;
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.TreeService#asyncTreeList(com.jk.model.Tree)    
	 */
	
	
	public boolean nodeHaveChild(Tree tree){
		
		boolean flag =false;
		try {
			List<Tree> queryTree = treeMapper.queryTree(tree);
			if(queryTree.size()>0){
				flag =true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flag;
	}
	
	
	@Override
	public List<Tree> asyncTreeList(Tree tree) throws Exception {
		List<Tree> list= new ArrayList<Tree>();
		if(tree.getId()!=null && !"".equals(tree.getId())){
			list = treeMapper.queryTree(tree);
		}else{
			list= treeMapper.asyncTreeList(tree);
		}
		
		List<Tree> asyncList =new ArrayList<Tree>();
		
		for (Tree tree2 : list) {
			if(nodeHaveChild(tree2)){
				tree2.setIsParent("true");
			}
			asyncList.add(tree2);
		}
		 
		
		return asyncList;
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.TreeService#updateAfterSave(com.jk.model.Tree)    
	 */
	@Override
	public void updateAfterSave(Tree tree) throws Exception {
		treeMapper.updateAfterSave(tree);	
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.TreeService#deleteTreeNodes(com.jk.model.Tree)    
	 */
	@Override
	public void deleteTreeNodes(Tree tree) throws Exception {
		treeMapper.deleteTreeNodes(tree);
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.TreeService#addAfterSave(com.jk.model.Tree)    
	 */
	@Override
	public void addAfterSave(Tree tree) throws Exception {
		treeMapper.addAfterSave(tree);		
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.TreeService#insertMany(java.util.List)    
	 */
	@Override
	public void insertMany(List<Tree> list) throws Exception {
		treeMapper.insertMany(list);		
	}
	
	@Override
	public List<Tree> queryTree2(Tree tree, String id) {
		List<Tree> list= treeMapper.queryTree2(tree,id);
		return list;
	}


	
	
	
}
