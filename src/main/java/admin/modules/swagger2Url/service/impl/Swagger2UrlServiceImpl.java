package admin.modules.swagger2Url.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import admin.modules.swagger2Url.dao.Swagger2UrlDao;
import admin.modules.swagger2Url.entity.Swagger2UrlEntity;
import admin.modules.swagger2Url.service.ISwagger2UrlService;
import admin.common.utils.ShiroUtils;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("swagger2UrlService")
public class Swagger2UrlServiceImpl implements ISwagger2UrlService {
	@Autowired
	private Swagger2UrlDao swagger2UrlDao;
	
	@Override
	public List<Swagger2UrlEntity> querySwagger2UrlEntityListForPage(Swagger2UrlEntity swagger2UrlEntity,Long startRow, Long endRow){
		return swagger2UrlDao.selectEntityListForPage(swagger2UrlEntity,startRow,endRow);
	}
	
	@Override
	public Long querySwagger2UrlEntityListForPageCount(Swagger2UrlEntity swagger2UrlEntity){
		return swagger2UrlDao.selectEntityListForPageCount(swagger2UrlEntity);
	}
	
	@Override
	public List<Swagger2UrlEntity> querySwagger2UrlEntityListAll(Swagger2UrlEntity swagger2UrlEntity){
		return swagger2UrlDao.selectEntityListAll(swagger2UrlEntity);
	}
	
	@Override
	public int saveSwagger2UrlEntity(Swagger2UrlEntity swagger2UrlEntity){
		return swagger2UrlDao.insertEntity(swagger2UrlEntity);
	}
	
	@Override
	public int saveSwagger2UrlEntity(List<Swagger2UrlEntity> swagger2UrlEntitys){
		return swagger2UrlDao.batchInsertEntity(swagger2UrlEntitys);
	}
	
	@Override
	public int editSwagger2UrlEntity(Swagger2UrlEntity swagger2UrlEntity){
		int size = swagger2UrlDao.updateEntity(swagger2UrlEntity);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int editSwagger2UrlEntity(List<Swagger2UrlEntity> swagger2UrlEntitys){
		int size = swagger2UrlDao.batchUpdateEntity(swagger2UrlEntitys);
		if(size==swagger2UrlEntitys.size()){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int removeSwagger2UrlEntity(Swagger2UrlEntity swagger2UrlEntity){
		return swagger2UrlDao.deleteEntity(swagger2UrlEntity);
	}
	
	@Override
	public int removeSwagger2UrlEntity(List<Swagger2UrlEntity> swagger2UrlEntitys){
		return swagger2UrlDao.batchDeleteEntity(swagger2UrlEntitys);
	}
}
