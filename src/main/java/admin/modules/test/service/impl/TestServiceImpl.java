package admin.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import admin.modules.test.dao.TestDao;
import admin.modules.test.entity.TestEntity;
import admin.modules.test.service.ITestService;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("TestService")
public class TestServiceImpl implements ITestService {
	@Autowired
	private TestDao TestDao;
	
	@Override
	public List<TestEntity> queryTestEntityListAll(TestEntity TestEntity){
		return TestDao.selectEntityListAll(TestEntity);
	}
	
	@Override
	public int saveTestEntity(TestEntity TestEntity){
		return TestDao.insertEntity(TestEntity);
	}
	
	@Override
	public int saveTestEntity(List<TestEntity> TestEntitys){
		return TestDao.batchInsertEntity(TestEntitys);
	}
	
	@Override
	@AutoRetry
	public int editTestEntity(TestEntity TestEntity){
		int size = TestDao.updateEntity(TestEntity);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	@AutoRetry
	public int editTestEntity(List<TestEntity> TestEntitys){
		int size = TestDao.batchUpdateEntity(TestEntitys);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int removeTestEntity(TestEntity TestEntity){
		return TestDao.deleteEntity(TestEntity);
	}
	
	@Override
	public int removeTestEntity(List<TestEntity> TestEntitys){
		return TestDao.batchDeleteEntity(TestEntitys);
	}
}
