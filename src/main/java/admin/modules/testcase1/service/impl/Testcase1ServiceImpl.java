package admin.modules.testcase1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import admin.modules.testcase1.dao.Testcase1Dao;
import admin.modules.testcase1.entity.Testcase1Entity;
import admin.modules.testcase1.service.ITestcase1Service;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("testcase1Service")
public class Testcase1ServiceImpl implements ITestcase1Service {
	@Autowired
	private Testcase1Dao testcase1Dao;
	
	@Override
	public List<Testcase1Entity> queryTestcase1EntityListForPage(Testcase1Entity testcase1Entity,Long startRow, Long endRow){
		return testcase1Dao.selectEntityListForPage(testcase1Entity,startRow,endRow);
	}
	
	@Override
	public Long queryTestcase1EntityListForPageCount(Testcase1Entity testcase1Entity){
		return testcase1Dao.selectEntityListForPageCount(testcase1Entity);
	}
	
	@Override
	public List<Testcase1Entity> queryTestcase1EntityListAll(Testcase1Entity testcase1Entity){
		return testcase1Dao.selectEntityListAll(testcase1Entity);
	}
	
	@Override
	public int saveTestcase1Entity(Testcase1Entity testcase1Entity){
		return testcase1Dao.insertEntity(testcase1Entity);
	}
	
	@Override
	public int saveTestcase1Entity(List<Testcase1Entity> testcase1Entitys){
		return testcase1Dao.batchInsertEntity(testcase1Entitys);
	}
	
	@Override
	@AutoRetry
	public int editTestcase1Entity(Testcase1Entity testcase1Entity){
		int size = testcase1Dao.updateEntity(testcase1Entity);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	@AutoRetry
	public int editTestcase1Entity(List<Testcase1Entity> testcase1Entitys){
		int size = testcase1Dao.batchUpdateEntity(testcase1Entitys);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int removeTestcase1Entity(Testcase1Entity testcase1Entity){
		return testcase1Dao.deleteEntity(testcase1Entity);
	}
	
	@Override
	public int removeTestcase1Entity(List<Testcase1Entity> testcase1Entitys){
		return testcase1Dao.batchDeleteEntity(testcase1Entitys);
	}
}
