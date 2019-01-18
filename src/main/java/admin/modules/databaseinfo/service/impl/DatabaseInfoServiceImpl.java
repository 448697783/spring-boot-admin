package admin.modules.databaseinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import admin.modules.databaseinfo.dao.DatabaseInfoDao;
import admin.modules.databaseinfo.entity.DatabaseInfoEntity;
import admin.modules.databaseinfo.service.IDatabaseInfoService;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("databaseInfoService")
public class DatabaseInfoServiceImpl implements IDatabaseInfoService {
	@Autowired
	private DatabaseInfoDao databaseInfoDao;
	
	@Override
	public List<DatabaseInfoEntity> queryDatabaseInfoEntityListAll(DatabaseInfoEntity databaseInfoEntity){
		return databaseInfoDao.selectEntityListAll(databaseInfoEntity);
	}
	
	@Override
	public int saveDatabaseInfoEntity(DatabaseInfoEntity databaseInfoEntity){
		return databaseInfoDao.insertEntity(databaseInfoEntity);
	}
	
	@Override
	public int saveDatabaseInfoEntity(List<DatabaseInfoEntity> databaseInfoEntitys){
		return databaseInfoDao.batchInsertEntity(databaseInfoEntitys);
	}
	
	@Override
	@AutoRetry
	public int editDatabaseInfoEntity(DatabaseInfoEntity databaseInfoEntity){
		int size = databaseInfoDao.updateEntity(databaseInfoEntity);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	@AutoRetry
	public int editDatabaseInfoEntity(List<DatabaseInfoEntity> databaseInfoEntitys){
		int size = databaseInfoDao.batchUpdateEntity(databaseInfoEntitys);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int removeDatabaseInfoEntity(DatabaseInfoEntity databaseInfoEntity){
		return databaseInfoDao.deleteEntity(databaseInfoEntity);
	}
	
	@Override
	public int removeDatabaseInfoEntity(List<DatabaseInfoEntity> databaseInfoEntitys){
		return databaseInfoDao.batchDeleteEntity(databaseInfoEntitys);
	}
}
