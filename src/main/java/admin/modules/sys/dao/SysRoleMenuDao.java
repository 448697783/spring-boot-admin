package admin.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;
import admin.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年9月18日 上午9:33:46
 */
@Mapper
public interface SysRoleMenuDao extends BaseMappper<SysRoleMenuEntity,Long> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
