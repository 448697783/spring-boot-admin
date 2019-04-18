package admin.modules.testcase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;
import admin.modules.testcase.entity.TestCaseEntity;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-05-15 18:11:18
 */
@Mapper
public interface TestCaseDao extends BaseMappper<TestCaseEntity,Long> {
	
	public List<TestCaseEntity> getTestCaseEntity(TestCaseEntity sysUserEntity);
}
