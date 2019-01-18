package admin.modules.discountdetail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import admin.modules.discountdetail.vo.TransDiscountDetailMergeQueryVO;
import admin.modules.discountdetail.dao.TransDiscountDetailMergeDao;
import admin.modules.discountdetail.entity.TransDiscountDetailMergeEntity;
import admin.modules.discountdetail.service.ITransDiscountDetailMergeService;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("transDiscountDetailMergeService")
public class TransDiscountDetailMergeServiceImpl implements ITransDiscountDetailMergeService {
	@Autowired
	private TransDiscountDetailMergeDao transDiscountDetailMergeDao;
	@Override
	public List<TransDiscountDetailMergeEntity> queryTransDiscountDetailMergeEntityListForPage(TransDiscountDetailMergeEntity transDiscountDetailMergeEntity,Long startRow, Long endRow){
		return transDiscountDetailMergeDao.selectEntityListForPage(transDiscountDetailMergeEntity,startRow,endRow);
	}
	
	@Override
	public Long queryTransDiscountDetailMergeEntityListForPageCount(TransDiscountDetailMergeEntity transDiscountDetailMergeEntity){
		return transDiscountDetailMergeDao.selectEntityListForPageCount(transDiscountDetailMergeEntity);
	}
}
