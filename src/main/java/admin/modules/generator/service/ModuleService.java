package admin.modules.generator.service;

import java.util.List;
import java.util.Map;

import admin.mapper.base.BaseMappper;
import admin.modules.generator.entity.ModuleEntity;

/**
 * 菜单管理
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2017-09-07 00:18:45
 */
public interface ModuleService{
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<ModuleEntity> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<ModuleEntity> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<ModuleEntity> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
//	List<ModuleEntity> getUserMenuList(Long userId);
	
	/**
	 * 查询菜单
	 */
	ModuleEntity queryObject(Long menuId);
	
	/**
	 * 查询菜单列表
	 */
	List<ModuleEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存菜单
	 */
	void save(ModuleEntity menu);
	
	/**
	 * 修改
	 * @return 
	 */
	int update(ModuleEntity menu);
	
	/**
	 * 删除
	 * @return 
	 */
	int deleteBatch(Long[] menuIds);
	
	/**
	 * 查询用户的权限列表
	 */
	List<ModuleEntity> queryUserList(Long userId);
	
	List<ModuleEntity> queryByIDs(String [] ids);
}
