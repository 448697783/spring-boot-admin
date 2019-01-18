package admin.modules.discountinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import admin.modules.discountinfo.dao.DiscountActiveInfoDao;
import admin.modules.discountinfo.entity.DiscountActiveInfoEntity;
import admin.modules.discountinfo.service.IDiscountActiveInfoService;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("discountActiveInfoService")
public class DiscountActiveInfoServiceImpl implements IDiscountActiveInfoService {
	@Autowired
	private DiscountActiveInfoDao discountActiveInfoDao;
	
	@Override
	public List<DiscountActiveInfoEntity> queryDiscountActiveInfoEntityListForPage(DiscountActiveInfoEntity discountActiveInfoEntity,Long startRow, Long endRow){
		return discountActiveInfoDao.selectEntityListForPage(discountActiveInfoEntity,startRow,endRow);
	}
	
	@Override
	public List<DiscountActiveInfoEntity> queryDiscountActiveInfoEntityListAll(DiscountActiveInfoEntity discountActiveInfoEntity){
		return discountActiveInfoDao.selectEntityListAll(discountActiveInfoEntity);
	}
	
	@Override
	public int saveDiscountActiveInfoEntity(DiscountActiveInfoEntity discountActiveInfoEntity){
		return discountActiveInfoDao.insertEntity(discountActiveInfoEntity);
	}
	
	@Override
	public int saveDiscountActiveInfoEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntitys){
		return discountActiveInfoDao.batchInsertEntity(discountActiveInfoEntitys);
	}
	
	@Override
	@AutoRetry
	public int editDiscountActiveInfoEntity(DiscountActiveInfoEntity discountActiveInfoEntity){
		int size = discountActiveInfoDao.updateEntity(discountActiveInfoEntity);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	@AutoRetry
	public int editDiscountActiveInfoEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntitys){
		int size = discountActiveInfoDao.batchUpdateEntity(discountActiveInfoEntitys);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int removeDiscountActiveInfoEntity(DiscountActiveInfoEntity discountActiveInfoEntity){
		return discountActiveInfoDao.deleteEntity(discountActiveInfoEntity);
	}
	
	@Override
	public int removeDiscountActiveInfoEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntitys){
		return discountActiveInfoDao.batchDeleteEntity(discountActiveInfoEntitys);
	}
}
