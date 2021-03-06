package admin.modules.oss.dao;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;
import admin.modules.oss.entity.SysOssEntity;

/**
 * 文件上传
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2017-03-25 12:13:26
 */
@Mapper
public interface SysOssDao extends BaseMappper<SysOssEntity,Long> {
	
}
