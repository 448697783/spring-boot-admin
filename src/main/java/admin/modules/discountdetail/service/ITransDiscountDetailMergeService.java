package admin.modules.discountdetail.service;

import admin.modules.discountdetail.entity.TransDiscountDetailMergeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-07 17:00:23
 */
public interface ITransDiscountDetailMergeService {

	List<TransDiscountDetailMergeEntity> queryTransDiscountDetailMergeEntityListForPage(TransDiscountDetailMergeEntity transDiscountDetailMergeEntity,Long startRow, Long endRow);
	Long queryTransDiscountDetailMergeEntityListForPageCount(TransDiscountDetailMergeEntity transDiscountDetailMergeEntity);
}
