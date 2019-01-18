package admin.modules.discountinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import admin.modules.discountinfo.vo.DiscountActiveInfoMergeQueryVO;
import admin.modules.discountinfo.dao.DiscountActiveInfoMergeDao;
import admin.modules.discountinfo.entity.DiscountActiveInfoMergeEntity;
import admin.modules.discountinfo.service.IDiscountActiveInfoMergeService;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("discountActiveInfoMergeService")
public class DiscountActiveInfoMergeServiceImpl implements IDiscountActiveInfoMergeService {
	@Autowired
	private DiscountActiveInfoMergeDao discountActiveInfoMergeDao;
	@Override
	public List<DiscountActiveInfoMergeEntity> queryDiscountActiveInfoMergeEntityListForPage(DiscountActiveInfoMergeEntity discountActiveInfoMergeEntity,Long startRow, Long endRow){
		return discountActiveInfoMergeDao.selectEntityListForPage(discountActiveInfoMergeEntity,startRow,endRow);
	}
	
	@Override
	public Long queryDiscountActiveInfoMergeEntityListForPageCount(DiscountActiveInfoMergeEntity discountActiveInfoMergeEntity){
		return discountActiveInfoMergeDao.selectEntityListForPageCount(discountActiveInfoMergeEntity);
	}
}
