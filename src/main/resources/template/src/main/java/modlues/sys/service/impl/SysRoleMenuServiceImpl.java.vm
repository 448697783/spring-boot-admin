package admin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.modules.sys.dao.SysRoleMenuDao;
import admin.modules.sys.entity.SysRoleDeptEntity;
import admin.modules.sys.entity.SysRoleMenuEntity;
import admin.modules.sys.service.SysRoleMenuService;



/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//先删除角色与菜单关系
		sysRoleMenuDao.delete(roleId);

		if(menuIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		Map<String, Object> map = new HashMap<>();
		ArrayList<SysRoleMenuEntity> list = new ArrayList<>();
		SysRoleMenuEntity sysRoleMenuEntity;
		for (int i = 0; i < menuIdList.size(); i++) {
			sysRoleMenuEntity = new SysRoleMenuEntity();
			sysRoleMenuEntity.setRoleId(roleId);
			sysRoleMenuEntity.setMenuId(menuIdList.get(i));
			list.add(sysRoleMenuEntity);
		}
		sysRoleMenuDao.insertBacth(list);
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuDao.queryMenuIdList(roleId);
	}

}
