package admin.modules.testcase.service.impl.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.modules.testcase.dao.ParamterDao;
import admin.modules.testcase.entity.ParamterEntity;
import admin.modules.testcase.service.impl.IParamterService;



@Service("paramterService")
public class ParamterServiceImpl implements IParamterService {
	@Autowired
	private ParamterDao paramterDao;
	
	@Override
	public ParamterEntity queryObject(long id){
		return paramterDao.queryObject(id);
	}
	
	@Override
	public List<ParamterEntity> queryList(Map<String, Object> map){
		return paramterDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return paramterDao.queryTotal(map);
	}
	
	@Override
	public void save(ParamterEntity paramter){
		paramterDao.insert(paramter);
	}
	
	@Override
	public void update(ParamterEntity paramter){
		paramterDao.update(paramter);
	}
	
	@Override
	public void delete(long parameterName){
		paramterDao.delete(parameterName);
	}
	
	@Override
	public void deleteBatch(Long[] parameterNames){
		paramterDao.deleteBatch(parameterNames);
	}

	@Override
	public void insertBath(List<ParamterEntity> list) {
		paramterDao.insertBacth( list);
	}
	
}
