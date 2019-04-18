package admin.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;
import admin.modules.job.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年12月1日 下午10:30:02
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMappper<ScheduleJobLogEntity,Long> {
	
}
