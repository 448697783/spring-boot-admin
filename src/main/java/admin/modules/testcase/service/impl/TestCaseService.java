package admin.modules.testcase.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.modules.sys.dao.SysRoleDeptDao;
import admin.modules.sys.entity.SysRoleDeptEntity;
import admin.modules.sys.entity.SysUserEntity;
import admin.modules.testcase.dao.TestCaseDao;
import admin.modules.testcase.entity.TestCaseEntity;

@Service
public class TestCaseService {
	@Autowired
	private TestCaseDao testCaseDao;
	@Autowired
	private SysRoleDeptDao sysRoleDeptDao;
	public int save(TestCaseEntity testCaseEntity) {
        SysUserEntity sysUserEntity = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal());
        testCaseEntity.setDeptId(sysUserEntity.getDeptId());
		testCaseDao.insert(testCaseEntity);
		return 0;
	}
	public int delete(long id) {
		testCaseDao.delete(id);
		return 0;
	}
	public List<TestCaseEntity> getTestCaseEntity(String controllerName,String describe) {
		
        SysUserEntity sysUserEntity = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal());
        TestCaseEntity testCase = new TestCaseEntity();
        if(sysUserEntity.getRoleIdList().size()>0) {
        	List<SysRoleDeptEntity> sysRoleDeptEntity = sysRoleDeptDao.querySysRoleDeptBacth(sysUserEntity.getRoleIdList());
        	sysUserEntity.setSysRoleDeptEntity(sysRoleDeptEntity);
        	testCase.setSysRoleDeptEntity(sysRoleDeptEntity);
        }
        testCase.setControllerName(controllerName);
        testCase.setDescribe(describe);
		return testCaseDao.getTestCaseEntity(testCase);
	}
	
}
