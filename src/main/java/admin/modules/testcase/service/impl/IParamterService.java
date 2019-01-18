package admin.modules.testcase.service.impl;

import java.util.List;
import java.util.Map;

import admin.modules.testcase.entity.ParamterEntity;

/**
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-05-15 18:38:02
 */
public interface IParamterService {
	
	ParamterEntity queryObject(long parameterName);
	
	List<ParamterEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ParamterEntity paramter);
	
	void insertBath(List<ParamterEntity> paramter);
	
	void update(ParamterEntity paramter);
	
	void delete(long parameterName);
	
	void deleteBatch(Long[] parameterNames);
}
