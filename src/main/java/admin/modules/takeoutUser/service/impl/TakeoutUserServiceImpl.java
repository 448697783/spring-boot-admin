package admin.modules.takeoutUser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.common.exception.AutoRetryException;
import admin.common.utils.ShiroUtils;
import admin.modules.sys.dao.SysRoleDeptDao;
import admin.modules.sys.entity.SysRoleDeptEntity;
import admin.modules.takeoutUser.dao.TakeoutUserDao;
import admin.modules.takeoutUser.entity.TakeoutUserEntity;
import admin.modules.takeoutUser.service.ITakeoutUserService;




@Service("takeoutUserService")
public class TakeoutUserServiceImpl implements ITakeoutUserService {
	@Autowired
	private TakeoutUserDao takeoutUserDao;
	@Autowired
	private SysRoleDeptDao sysRoleDeptDao;
	
	@Override
	public List<TakeoutUserEntity> queryTakeoutUserEntityListForPage(TakeoutUserEntity takeoutUserEntity,Long startRow, Long endRow){
        List<SysRoleDeptEntity> sysRoleDeptEntity = sysRoleDeptDao.querySysRoleDeptBacth(ShiroUtils.getUserEntity().getRoleIdList());
        takeoutUserEntity.setSysRoleDeptEntity(sysRoleDeptEntity);
		return takeoutUserDao.selectEntityListForPage(takeoutUserEntity,startRow,endRow);
	}
	
	@Override
	public Long queryTakeoutUserEntityListForPageCount(TakeoutUserEntity takeoutUserEntity){
        List<SysRoleDeptEntity> sysRoleDeptEntity = sysRoleDeptDao.querySysRoleDeptBacth(ShiroUtils.getUserEntity().getRoleIdList());
        takeoutUserEntity.setSysRoleDeptEntity(sysRoleDeptEntity);
		return takeoutUserDao.selectEntityListForPageCount(takeoutUserEntity);
	}
	
	@Override
	public List<TakeoutUserEntity> queryTakeoutUserEntityListAll(TakeoutUserEntity takeoutUserEntity){
        List<SysRoleDeptEntity> sysRoleDeptEntity = sysRoleDeptDao.querySysRoleDeptBacth(ShiroUtils.getUserEntity().getRoleIdList());
        takeoutUserEntity.setSysRoleDeptEntity(sysRoleDeptEntity);
		return takeoutUserDao.selectEntityListAll(takeoutUserEntity);
	}
	
	@Override
	public int saveTakeoutUserEntity(TakeoutUserEntity takeoutUserEntity){
		return takeoutUserDao.insertEntity(takeoutUserEntity);
	}
	
	@Override
	public int saveTakeoutUserEntity(List<TakeoutUserEntity> takeoutUserEntitys){
		return takeoutUserDao.batchInsertEntity(takeoutUserEntitys);
	}
	
	@Override
	public int editTakeoutUserEntity(TakeoutUserEntity takeoutUserEntity){
		int size = takeoutUserDao.updateEntity(takeoutUserEntity);
		if(size==0){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int editTakeoutUserEntity(List<TakeoutUserEntity> takeoutUserEntitys){
		int size = takeoutUserDao.batchUpdateEntity(takeoutUserEntitys);
		if(size==takeoutUserEntitys.size()){
			throw new AutoRetryException();
		}
		return size;
	}
	
	@Override
	public int removeTakeoutUserEntity(TakeoutUserEntity takeoutUserEntity){
		return takeoutUserDao.deleteEntity(takeoutUserEntity);
	}
	
	@Override
	public int removeTakeoutUserEntity(List<TakeoutUserEntity> takeoutUserEntitys){
		return takeoutUserDao.batchDeleteEntity(takeoutUserEntitys);
	}
}
