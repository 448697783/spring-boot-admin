package admin.modules.discountinfo1.service;

import admin.modules.discountinfo1.entity.DiscountInfo1Entity;

import java.util.List;
import java.util.Map;

/**
 * 折扣活动信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-12 16:21:22
 */
public interface IDiscountInfo1Service {

	List<DiscountInfo1Entity> queryDiscountInfo1EntityListForPage(DiscountInfo1Entity discountInfo1Entity,Long startRow, Long endRow);
	
	Long queryDiscountInfo1EntityListForPageCount(DiscountInfo1Entity discountInfo1Entity);
	
	List<DiscountInfo1Entity> queryDiscountInfo1EntityListAll(DiscountInfo1Entity discountInfo1Entity);
	
	int saveDiscountInfo1Entity(DiscountInfo1Entity discountInfo1Entity);
	
	int saveDiscountInfo1Entity(List<DiscountInfo1Entity> discountInfo1Entitys);
	
	int editDiscountInfo1Entity(DiscountInfo1Entity discountInfo1Entity);
	
	int editDiscountInfo1Entity(List<DiscountInfo1Entity> discountInfo1Entitys);
	
	int removeDiscountInfo1Entity(DiscountInfo1Entity discountInfo1Entity);
	
	int removeDiscountInfo1Entity(List<DiscountInfo1Entity> discountInfo1Entity);
}
