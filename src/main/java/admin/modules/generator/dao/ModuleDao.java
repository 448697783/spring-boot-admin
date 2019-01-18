package admin.modules.generator.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;
import admin.modules.generator.entity.ModuleEntity;

/**
 * 菜单管理
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2017-09-07 00:18:45
 */
@Mapper
public interface ModuleDao extends BaseMappper<ModuleEntity,Long> {
	
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
	 * 查询用户的权限列表
	 */
	List<ModuleEntity> queryUserList(Long userId);

	List<ModuleEntity> queryListByIDs(String[] ids);

	List<ModuleEntity> queryListByName(String[] list);
}
