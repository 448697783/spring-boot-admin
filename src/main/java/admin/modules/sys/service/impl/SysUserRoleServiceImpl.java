package admin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.modules.sys.dao.SysUserRoleDao;
import admin.modules.sys.entity.SysUserRoleEntity;
import admin.modules.sys.service.SysUserRoleService;



/**
 * 用户与角色对应关系
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		
		//先删除用户与角色关系
		sysUserRoleDao.delete(userId);
		
		//保存用户与角色关系
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", roleIdList);
		ArrayList<SysUserRoleEntity> list = new ArrayList<>();
		SysUserRoleEntity sysRoleMenuEntity;
		for (int i = 0; i < roleIdList.size(); i++) {
			sysRoleMenuEntity = new SysUserRoleEntity();
			sysRoleMenuEntity.setUserId(userId);
			sysRoleMenuEntity.setRoleId(roleIdList.get(i));
			list.add(sysRoleMenuEntity);
		}
		sysUserRoleDao.insertBacth(list);
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleDao.queryRoleIdList(userId);
	}

	@Override
	public void delete(Long userId) {
		sysUserRoleDao.delete(userId);
	}
}
