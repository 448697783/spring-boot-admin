package admin.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;
import admin.modules.sys.entity.SysRoleEntity;

/**
 * 角色管理
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年9月18日 上午9:33:33
 */
@Mapper
public interface SysRoleDao extends BaseMappper<SysRoleEntity,Long> {
	

}
