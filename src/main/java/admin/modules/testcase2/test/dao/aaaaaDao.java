package admin.modules.testcase2.test.dao;

import admin.modules.testcase2.test.entity.aaaaaEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-04-18 20:12:20
 */
@Mapper
public interface aaaaaDao {
		
	@Select({
			"<script>",
			"select",
					"`controller_name`, ",
					"`describe`, ",
					"`method`, ",
					"`test_case_id`, ",
					"`dept_id`, ",
					"`created_time`, ",
					"`updated_time`, ",
					"`exec_last_time`, ",
					"`status`",
			"from",
				"( testcase) F",
			"<where>",
				"<if test='entity.controllerName != null and entity.controllerName != \"\" '>AND `controller_name` = #{entity.controllerName} </if>",
				"<if test='entity.describe != null and entity.describe != \"\" '>AND `describe` = #{entity.describe} </if>",
				"<if test='entity.method != null and entity.method != \"\" '>AND `method` = #{entity.method} </if>",
				"<if test='entity.testCaseId != null '>and `test_case_id` = #{entity.testCaseId} </if>",
				"<if test='entity.deptId != null '>and `dept_id` = #{entity.deptId} </if>",
				"<if test='entity.createdTime != null '>and `created_time` = #{entity.createdTime} </if>",
				"<if test='entity.updatedTime != null '>and `updated_time` = #{entity.updatedTime} </if>",
				"<if test='entity.execLastTime != null '>and `exec_last_time` = #{entity.execLastTime} </if>",
				"<if test='entity.status != null '>and `status` = #{entity.status}</if>",
				"<if test='entity.sysRoleDeptEntity != null and entity.sysRoleDeptEntity.size() != 0'>",
					"and dept_id in (",
					"<foreach collection='entity.sysRoleDeptEntity' item='item' index='index' separator=',' >",
						"#{item.deptId}",
					"</foreach>",
					")",
				"</if>",
			"</where>",
			"limit #{startRow},#{endRow}  ",
			"</script>"
    })
	List<aaaaaEntity> selectEntityListForPage(@Param("entity")aaaaaEntity aaaaaEntity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"( testcase) F",
			"<where>",
				"<if test='entity.controllerName != null and entity.controllerName != \"\" '>AND `controller_name` = #{entity.controllerName} </if>",
				"<if test='entity.describe != null and entity.describe != \"\" '>AND `describe` = #{entity.describe} </if>",
				"<if test='entity.method != null and entity.method != \"\" '>AND `method` = #{entity.method} </if>",
				"<if test='entity.testCaseId != null '>and `test_case_id` = #{entity.testCaseId} </if>",
				"<if test='entity.deptId != null '>and `dept_id` = #{entity.deptId} </if>",
				"<if test='entity.createdTime != null '>and `created_time` = #{entity.createdTime} </if>",
				"<if test='entity.updatedTime != null '>and `updated_time` = #{entity.updatedTime} </if>",
				"<if test='entity.execLastTime != null '>and `exec_last_time` = #{entity.execLastTime} </if>",
				"<if test='entity.status != null '>and `status` = #{entity.status}</if>",
				"<if test='entity.sysRoleDeptEntity != null and entity.sysRoleDeptEntity.size() != 0'>",
					"and dept_id in (",
					"<foreach collection='entity.sysRoleDeptEntity' item='item' index='index' separator=',' >",
						"#{item.deptId}",
					"</foreach>",
					")",
				"</if>",
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")aaaaaEntity aaaaaEntity);
}
