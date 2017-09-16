package io.renren.service.impl;

import io.renren.dao.SysMenuDao;
import io.renren.entity.SysMenuEntity;
import io.renren.service.SysMenuService;
import io.renren.service.SysRoleMenuService;
import io.renren.service.SysUserService;
import io.renren.utils.Constant.MenuType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		System.out.println("查询的parentId:"+parentId);
		System.out.println("menuIdList:"+menuIdList);

		//查询所有的系统跟目录,这个可以使用缓存
		List<SysMenuEntity> menuList = sysMenuDao.queryListParentId(parentId);

//		System.out.println("========查询到的根菜单的目录=============");
//		if(menuIdList!=null&&!menuIdList.isEmpty()){
//			for(SysMenuEntity m:menuList){
//				System.out.println("菜单名称："+m.getName()+",菜单id:"+m.getMenuId()+",父id:"+m.getParentId());
//			}
//		}


		if(menuIdList == null){
			return menuList;
		}

		//看当前的menuidList中，是否包含，如果包含，则添加到用户菜单列表中去
		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return sysMenuDao.queryNotButtonList();
	}

	@Override
	public List<SysMenuEntity> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
		if(userId == 1){
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		System.out.println("=========用户菜单列表===========");
		if(menuIdList!=null&&!menuIdList.isEmpty()){
			for(Long id:menuIdList){
				System.out.println("用户所有的菜单id");
			}
		}

		return getAllMenuList(menuIdList);
	}
	
	@Override
	public SysMenuEntity queryObject(Long menuId) {
		return sysMenuDao.queryObject(menuId);
	}

	@Override
	public List<SysMenuEntity> queryList(Map<String, Object> map) {
		return sysMenuDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysMenuDao.queryTotal(map);
	}

	@Override
	public void save(SysMenuEntity menu) {
		sysMenuDao.save(menu);
	}

	@Override
	public void update(SysMenuEntity menu) {
		sysMenuDao.update(menu);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] menuIds) {
		sysMenuDao.deleteBatch(menuIds);
	}
	
	@Override
	public List<SysMenuEntity> queryUserList(Long userId) {
		return sysMenuDao.queryUserList(userId);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
		
		for(SysMenuEntity entity : menuList){
			if(entity.getType() == MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
}
