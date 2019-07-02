package admin.modules.takeoutUser.service;

import admin.modules.takeoutUser.entity.TakeoutUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-07-01 19:19:04
 */
public interface ITakeoutUserService {

	List<TakeoutUserEntity> queryTakeoutUserEntityListForPage(TakeoutUserEntity takeoutUserEntity,Long startRow, Long endRow);
	
	Long queryTakeoutUserEntityListForPageCount(TakeoutUserEntity takeoutUserEntity);
	
	List<TakeoutUserEntity> queryTakeoutUserEntityListAll(TakeoutUserEntity takeoutUserEntity);
	
	int saveTakeoutUserEntity(TakeoutUserEntity takeoutUserEntity);
	
	int saveTakeoutUserEntity(List<TakeoutUserEntity> takeoutUserEntitys);
	
	int editTakeoutUserEntity(TakeoutUserEntity takeoutUserEntity);
	
	int editTakeoutUserEntity(List<TakeoutUserEntity> takeoutUserEntitys);
	
	int removeTakeoutUserEntity(TakeoutUserEntity takeoutUserEntity);
	
	int removeTakeoutUserEntity(List<TakeoutUserEntity> takeoutUserEntity);
}
