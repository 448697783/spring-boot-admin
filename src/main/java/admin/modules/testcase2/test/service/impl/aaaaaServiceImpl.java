package admin.modules.testcase2.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import admin.modules.testcase2.test.vo.aaaaaQueryVO;
import admin.modules.sys.dao.SysRoleDeptDao;
import admin.modules.sys.entity.SysRoleDeptEntity;

import admin.modules.testcase2.test.dao.aaaaaDao;
import admin.modules.testcase2.test.entity.aaaaaEntity;
import admin.modules.testcase2.test.service.IaaaaaService;
import admin.common.utils.ShiroUtils;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("aaaaaService")
public class aaaaaServiceImpl implements IaaaaaService {
	@Autowired
	private aaaaaDao aaaaaDao;
	@Autowired
	private SysRoleDeptDao sysRoleDeptDao;
	@Override
	public List<aaaaaEntity> queryaaaaaEntityListForPage(aaaaaEntity aaaaaEntity,Long startRow, Long endRow){
        List<SysRoleDeptEntity> sysRoleDeptEntity = sysRoleDeptDao.querySysRoleDeptBacth(ShiroUtils.getUserEntity().getRoleIdList());
        aaaaaEntity.setSysRoleDeptEntity(sysRoleDeptEntity);
		return aaaaaDao.selectEntityListForPage(aaaaaEntity,startRow,endRow);
	}
	
	@Override
	public Long queryaaaaaEntityListForPageCount(aaaaaEntity aaaaaEntity){
        List<SysRoleDeptEntity> sysRoleDeptEntity = sysRoleDeptDao.querySysRoleDeptBacth(ShiroUtils.getUserEntity().getRoleIdList());
        aaaaaEntity.setSysRoleDeptEntity(sysRoleDeptEntity);
		return aaaaaDao.selectEntityListForPageCount(aaaaaEntity);
	}
}
