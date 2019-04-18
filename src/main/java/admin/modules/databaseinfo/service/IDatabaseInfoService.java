package admin.modules.databaseinfo.service;

import admin.modules.databaseinfo.entity.DatabaseInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据库信息表
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-06-15 17:49:11
 */
public interface IDatabaseInfoService {
	
	List<DatabaseInfoEntity> queryDatabaseInfoEntityListAll(DatabaseInfoEntity databaseInfoEntity);
	
	int saveDatabaseInfoEntity(DatabaseInfoEntity databaseInfoEntity);
	
	int saveDatabaseInfoEntity(List<DatabaseInfoEntity> databaseInfoEntitys);
	
	int editDatabaseInfoEntity(DatabaseInfoEntity databaseInfoEntity);
	
	int editDatabaseInfoEntity(List<DatabaseInfoEntity> databaseInfoEntitys);
	
	int removeDatabaseInfoEntity(DatabaseInfoEntity databaseInfoEntity);
	
	int removeDatabaseInfoEntity(List<DatabaseInfoEntity> databaseInfoEntity);
}
