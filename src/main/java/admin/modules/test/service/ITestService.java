package admin.modules.test.service;

import admin.modules.test.entity.TestEntity;

import java.util.List;
import java.util.Map;

/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-06-15 20:06:11
 */
public interface ITestService {
	
	List<TestEntity> queryTestEntityListAll(TestEntity TestEntity);
	
	int saveTestEntity(TestEntity TestEntity);
	
	int saveTestEntity(List<TestEntity> TestEntitys);
	
	int editTestEntity(TestEntity TestEntity);
	
	int editTestEntity(List<TestEntity> TestEntitys);
	
	int removeTestEntity(TestEntity TestEntity);
	
	int removeTestEntity(List<TestEntity> TestEntity);
}
