package admin.modules.generator.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.modules.generator.dao.ModuleDao;
import admin.modules.generator.entity.ModuleEntity;
import admin.modules.generator.service.ModuleService;



@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleDao moduleDao;
//	@Autowired
//	private SysUserService sysUserService;
	
	@Override
	public List<ModuleEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<ModuleEntity> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<ModuleEntity> userMenuList = new ArrayList<>();
		for(ModuleEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<ModuleEntity> queryListParentId(Long parentId) {
		return moduleDao.queryListParentId(parentId);
	}

	@Override
	public List<ModuleEntity> queryNotButtonList() {
		return moduleDao.queryNotButtonList();
	}

//	@Override
//	public List<ModuleEntity> getUserMenuList(Long userId) {
//		//系统管理员，拥有最高权限
//		if(userId == Constant.SUPER_ADMIN){
//			return getAllMenuList(null);
//		}
//		
//		//用户菜单列表
//		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
//		return getAllMenuList(menuIdList);
//	}
	
	@Override
	public ModuleEntity queryObject(Long menuId) {
		return moduleDao.queryObject(menuId);
	}

	@Override
	public List<ModuleEntity> queryList(Map<String, Object> map) {
		return moduleDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return moduleDao.queryTotal(map);
	}

	@Override
	public void save(ModuleEntity menu) {
		moduleDao.insert(menu);
	}

	@Override
	public int update(ModuleEntity menu) {
		return moduleDao.update(menu);
	}

	@Override
	@Transactional
	public int deleteBatch(Long[] menuIds) {
		return moduleDao.deleteBatch(menuIds);
	}
	
	@Override
	public List<ModuleEntity> queryUserList(Long userId) {
		return moduleDao.queryUserList(userId);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<ModuleEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<ModuleEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
//		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}


	@Override
	public List<ModuleEntity> queryByIDs(String[] ids) {
		List<ModuleEntity> templates = queryByIDs(ids,null);
		return templates;
	}

	public List<ModuleEntity> queryByIDs(String[] ids,Map<Long,ModuleEntity> map) {
		if(map==null){
			map = new HashMap<>();
		}
		
		List<ModuleEntity> templates = moduleDao.queryListByIDs(ids);
		
		List<String> arr = new ArrayList<>();
		for (ModuleEntity template : templates) {
			if (map.containsKey(template.getMenuId())) {
				continue;
			}
			map.put(template.getMenuId(), template);
			Pattern pattern = Pattern.compile("\\$(.*?)\\$");// 匹配的模式
			if (StringUtils.isNotBlank(template.getUrl())) {
				Matcher m = pattern.matcher(template.getUrl());
				while (m.find()) {
					if (StringUtils.isNotBlank(m.group(1))) {
						arr.add(m.group(1).split("_")[0]);
					}
				}
			}
			if (StringUtils.isNotBlank(template.getPerms())) {
				Matcher m1 = pattern.matcher(template.getPerms());
				while (m1.find()) {
					if (StringUtils.isNotBlank(m1.group(1))) {
						arr.add(m1.group(1).split("_")[0]);
					}
				}
			}
		}
		if(arr.size()>0){
			List<ModuleEntity> arrList = moduleDao.queryListByName(arr.toArray(new String[0]));
			String[] tep = new String[arrList.size()];
			for (int i = 0; i < tep.length; i++) {
				tep[i]=String.valueOf(arrList.get(i).getMenuId());
			}
			if(tep.length>0) {
				queryByIDs(tep,map);
			}
		}
		Collection<ModuleEntity> values = map.values();
		ArrayList<ModuleEntity> arrayList = new ArrayList<>();
		boolean addAll = arrayList.addAll(values);
		return arrayList;
	}
	/**
	 * 递归
	 */
//	private List<ModuleEntity> getMenuTreeList(List<ModuleEntity> menuList, List<Long> menuIdList){
//		List<ModuleEntity> subMenuList = new ArrayList<ModuleEntity>();
//		
//		for(ModuleEntity entity : menuList){
//			if(entity.getType() == MenuType.CATALOG.getValue()){//目录
//				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
//			}
//			subMenuList.add(entity);
//		}
//		
//		return subMenuList;
//	}
}
