package com.edut.springboot.tarena.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * VO：通过此对象封装角色以及角色对应的菜单id
 * 基于此对象封装角色修改页面上要呈现的数据
 * 1)角色自身信息：id，name，note（来自于角色表：sys_role3s）
 * 2)角色对应的菜单信息：menuId（来自于角色菜单关系表：sys_role_menus）
 * 
 * 如上数据应该来与数据库数据的查询，具体查询方案：
 * 1)单表查询：（在业务层执行两次数据查询操作，最终在业务层进行数据封装）
 * 2)嵌套查询：（数据库端可能要发两次sql）
 * 3)多表查询：（sys_role 关联 sys_role_menus）
 * @author ta
 */
@Data //lombok
public class SysRoleMenuVo implements Serializable {
	private static final long serialVersionUID = -3155052334238354667L;
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
	/**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;
	public int updateObject() {
		// TODO Auto-generated method stub
		return 0;
	}
}
