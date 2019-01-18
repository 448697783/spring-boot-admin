package admin.modules.testcase.dao;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;
import admin.modules.testcase.entity.ParamterEntity;

/**
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-05-15 18:38:02
 */
@Mapper
public interface ParamterDao extends BaseMappper<ParamterEntity,Long> {
	
}
