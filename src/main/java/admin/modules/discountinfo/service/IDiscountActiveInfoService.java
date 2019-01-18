package admin.modules.discountinfo.service;

import admin.modules.discountinfo.entity.DiscountActiveInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 折扣活动信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 15:41:29
 */
public interface IDiscountActiveInfoService {
	
	List<DiscountActiveInfoEntity> queryDiscountActiveInfoEntityListForPage(DiscountActiveInfoEntity discountActiveInfoEntity,Long startRow, Long endRow);
	
	List<DiscountActiveInfoEntity> queryDiscountActiveInfoEntityListAll(DiscountActiveInfoEntity discountActiveInfoEntity);
	
	int saveDiscountActiveInfoEntity(DiscountActiveInfoEntity discountActiveInfoEntity);
	
	int saveDiscountActiveInfoEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntitys);
	
	int editDiscountActiveInfoEntity(DiscountActiveInfoEntity discountActiveInfoEntity);
	
	int editDiscountActiveInfoEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntitys);
	
	int removeDiscountActiveInfoEntity(DiscountActiveInfoEntity discountActiveInfoEntity);
	
	int removeDiscountActiveInfoEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntity);
}
