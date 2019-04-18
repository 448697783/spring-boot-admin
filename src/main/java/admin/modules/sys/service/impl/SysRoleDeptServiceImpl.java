package admin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import admin.modules.sys.dao.SysRoleDeptDao;
import admin.modules.sys.entity.SysRoleDeptEntity;
import admin.modules.sys.service.SysRoleDeptService;


/**
 * 角色与部门对应关系
 *
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2017年6月21日 23:42:30
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
	@Autowired
	private SysRoleDeptDao sysRoleDeptDao;

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
		//先删除角色与菜单关系
		sysRoleDeptDao.delete(roleId);

		if(deptIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		SysRoleDeptEntity sysRoleDept ;
		ArrayList<SysRoleDeptEntity> list = new ArrayList<>();
		for (int i = 0; i < deptIdList.size(); i++) {
			sysRoleDept = new SysRoleDeptEntity();
			sysRoleDept.setRoleId(roleId);
			sysRoleDept.setDeptId(deptIdList.get(i));
			list.add(sysRoleDept);
		}
		sysRoleDeptDao.insertBacth(list);
	}

	@Override
	public List<Long> queryDeptIdList(Long roleId) {
		return sysRoleDeptDao.queryDeptIdList(roleId);
	}

}
