package admin.modules.swagger2Url.service;

import admin.modules.swagger2Url.entity.Swagger2UrlEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-09-02 18:13:02
 */
public interface ISwagger2UrlService {

	List<Swagger2UrlEntity> querySwagger2UrlEntityListForPage(Swagger2UrlEntity swagger2UrlEntity,Long startRow, Long endRow);
	
	Long querySwagger2UrlEntityListForPageCount(Swagger2UrlEntity swagger2UrlEntity);
	
	List<Swagger2UrlEntity> querySwagger2UrlEntityListAll(Swagger2UrlEntity swagger2UrlEntity);
	
	int saveSwagger2UrlEntity(Swagger2UrlEntity swagger2UrlEntity);
	
	int saveSwagger2UrlEntity(List<Swagger2UrlEntity> swagger2UrlEntitys);
	
	int editSwagger2UrlEntity(Swagger2UrlEntity swagger2UrlEntity);
	
	int editSwagger2UrlEntity(List<Swagger2UrlEntity> swagger2UrlEntitys);
	
	int removeSwagger2UrlEntity(Swagger2UrlEntity swagger2UrlEntity);
	
	int removeSwagger2UrlEntity(List<Swagger2UrlEntity> swagger2UrlEntity);
}
