package com.jk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.model.Role;
import com.jk.model.RoleList;
import com.jk.model.User;
import com.jk.service.RoleService;
import com.jk.service.UserService;

@Controller
@RequestMapping("roleController")
public class RoleController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("toShowRole")
	public String toshow(String userId,HttpServletRequest request){
		
		request.getSession().setAttribute("userId", userId);
		
		return "roleShow";
	}
	
	@RequestMapping("tosettree")
	public String toallottree(String roleId,HttpServletRequest request){
		request.getSession().setAttribute("roleId", roleId);
		return "settree";
	}
	
	@RequestMapping("roleNodes")
	@ResponseBody
	public RoleList roleNodes(String userId,HttpServletRequest request){
		User u = new User();
		u.setId(userId);
		RoleList roleList = new RoleList();
		try {
			User resourcesList = userService.getResourcesList(u);
			Set<Role> roles = resourcesList.getRoles();
			List<Role> list = roleService.getrole();
			roleList.setList(list);
			roleList.setRoles(roles);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roleList;
	}
	
	@RequestMapping("selectRoleList")
	@ResponseBody
	public List<Role> selectRoleList(HttpServletRequest request){
		List<Role> list = new ArrayList<Role>();
		
		try {
			 list = roleService.getrole();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	@RequestMapping("allotRole")
	@ResponseBody
	public String allotRole(String rids, String userId,HttpServletRequest request){
		System.out.println(rids);
		System.out.println(userId);
		String[] split = rids.split(",");
		try {
			roleService.deleteuser(userId);
			
			for (int i = 0; i < split.length; i++) {
				roleService.insertRole(split[i],userId);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "成功";
	}
	
	@RequestMapping("querysettree")
	@ResponseBody
	public List<Map<String,Object>> querysettree(HttpServletRequest request){
		String roleId = (String)request.getSession().getAttribute("roleId");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			 list = roleService.querysettree(roleId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping("setTreeChange")
	@ResponseBody
	public String setTreeChange(String treeids ,HttpServletRequest request){
		String roleId = (String)request.getSession().getAttribute("roleId");
		try {
			roleService.setTreeChange(treeids,roleId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	
	
	
	
	
}
