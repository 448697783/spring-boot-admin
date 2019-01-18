package admin.modules.discountinfo1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import admin.modules.discountinfo1.dao.DiscountInfo1Dao;
import admin.modules.discountinfo1.entity.DiscountInfo1Entity;
import admin.modules.discountinfo1.service.IDiscountInfo1Service;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("discountInfo1Service")
public class DiscountInfo1ServiceImpl implements IDiscountInfo1Service {
	@Autowired
	private DiscountInfo1Dao discountInfo1Dao;
	
	@Override
	public List<DiscountInfo1Entity> queryDiscountInfo1EntityListForPage(DiscountInfo1Entity discountInfo1Entity,Long startRow, Long endRow){
		return discountInfo1Dao.selectEntityListForPage(discountInfo1Entity,startRow,endRow);
	}
	
	@Override
	public Long queryDiscountInfo1EntityListForPageCount(DiscountInfo1Entity discountInfo1Entity){
		return discountInfo1Dao.selectEntityListForPageCount(discountInfo1Entity);
	}
	
	@Override
	public List<DiscountInfo1Entity> queryDiscountInfo1EntityListAll(DiscountInfo1Entity discountInfo1Entity){
		return discountInfo1Dao.selectEntityListAll(discountInfo1Entity);
	}
	
	@Override
	public int saveDiscountInfo1Entity(DiscountInfo1Entity discountInfo1Entity){
		return discountInfo1Dao.insertEntity(discountInfo1Entity);
	}
	
	@Override
	public int saveDiscountInfo1Entity(List<DiscountInfo1Entity> discountInfo1Entitys){
		return discountInfo1Dao.batchInsertEntity(discountInfo1Entitys);
	}
	
	@Override
	@AutoRetry
	public int editDiscountInfo1Entity(DiscountInfo1Entity discountInfo1Entity){
		int size = discountInfo1Dao.updateEntity(discountInfo1Entity);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	@AutoRetry
	public int editDiscountInfo1Entity(List<DiscountInfo1Entity> discountInfo1Entitys){
		int size = discountInfo1Dao.batchUpdateEntity(discountInfo1Entitys);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int removeDiscountInfo1Entity(DiscountInfo1Entity discountInfo1Entity){
		return discountInfo1Dao.deleteEntity(discountInfo1Entity);
	}
	
	@Override
	public int removeDiscountInfo1Entity(List<DiscountInfo1Entity> discountInfo1Entitys){
		return discountInfo1Dao.batchDeleteEntity(discountInfo1Entitys);
	}
}
