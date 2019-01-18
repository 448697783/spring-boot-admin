package admin.modules.discountinfo.service;

import admin.modules.discountinfo.entity.DiscountActiveInfoMergeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-07 15:19:14
 */
public interface IDiscountActiveInfoMergeService {

	List<DiscountActiveInfoMergeEntity> queryDiscountActiveInfoMergeEntityListForPage(DiscountActiveInfoMergeEntity discountActiveInfoMergeEntity,Long startRow, Long endRow);
	Long queryDiscountActiveInfoMergeEntityListForPageCount(DiscountActiveInfoMergeEntity discountActiveInfoMergeEntity);
}
