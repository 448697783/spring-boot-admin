package admin.modules.testcase1.dao;

import admin.modules.testcase1.entity.Testcase1Entity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-05-15 07:07:51
 */
@Mapper
public interface Testcase1Dao {

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
				"testcase1 F",
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
			"</where>",
			"limit #{startRow},#{endRow}  ",
			"</script>"
    })
	List<Testcase1Entity> selectEntityListForPage(@Param("entity")Testcase1Entity testcase1Entity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"testcase1 F",
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
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")Testcase1Entity testcase1Entity);
	
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
				"testcase1",
			"<where>",
				"<if test='controllerName != null and controllerName != \"\" '>AND `controller_name` = #{controllerName} </if>",
				"<if test='describe != null and describe != \"\" '>AND `describe` = #{describe} </if>",
				"<if test='method != null and method != \"\" '>AND `method` = #{method} </if>",
				"<if test='testCaseId != null '>and `test_case_id` = #{testCaseId} </if>",
				"<if test='deptId != null '>and `dept_id` = #{deptId} </if>",
				"<if test='createdTime != null '>and `created_time` = #{createdTime} </if>",
				"<if test='updatedTime != null '>and `updated_time` = #{updatedTime} </if>",
				"<if test='execLastTime != null '>and `exec_last_time` = #{execLastTime} </if>",
				"<if test='status != null '>and `status` = #{status}</if>",
			"</where>",
			"</script>"
    })
	List<Testcase1Entity> selectEntityListAll(Testcase1Entity testcase1Entity);

	@Insert({
			"insert into testcase1",
				"(",
					"`controller_name`, ",
					"`describe`, ",
					"`method`, ",
					"`test_case_id`, ",
					"`dept_id`, ",
					"`created_time`, ",
					"`updated_time`, ",
					"`exec_last_time`, ",
					"`status`",
				")",
			"values",
				"(",
					"#{controllerName,jdbcType=VARCHAR}, ",
					"#{describe,jdbcType=VARCHAR}, ",
					"#{method,jdbcType=VARCHAR}, ",
					"#{testCaseId,jdbcType=BIGINT}, ",
					"#{deptId,jdbcType=BIGINT}, ",
					"#{createdTime,jdbcType=TIMESTAMP}, ",
					"#{updatedTime,jdbcType=TIMESTAMP}, ",
					"#{execLastTime,jdbcType=TIMESTAMP}, ",
					"#{status,jdbcType=INTEGER}",
				")"
    })
	int insertEntity(Testcase1Entity testcase1Entity);
	
	@Insert({
			"<script>",
			"insert into testcase1",
				"(",
				"`controller_name`, ",
				"`describe`, ",
				"`method`, ",
				"`test_case_id`, ",
				"`dept_id`, ",
				"`created_time`, ",
				"`updated_time`, ",
				"`exec_last_time`, ",
				"`status`",
				")",
			"values",
			"<foreach collection ='list' item='obj' separator =','>",
				"(",
					"#{obj.controllerName,jdbcType=VARCHAR}, ",
					"#{obj.describe,jdbcType=VARCHAR}, ",
					"#{obj.method,jdbcType=VARCHAR}, ",
					"#{obj.testCaseId,jdbcType=BIGINT}, ",
					"#{obj.deptId,jdbcType=BIGINT}, ",
					"#{obj.createdTime,jdbcType=DATE}, ",
					"#{obj.updatedTime,jdbcType=DATE}, ",
					"#{obj.execLastTime,jdbcType=DATE}, ",
					"#{obj.status,jdbcType=INTEGER}",
				")",
			"</foreach >",
			"</script>",
    })
	int batchInsertEntity(@Param("list")List<Testcase1Entity> testcase1Entitys);
	
	@Update({
			"<script>",
			"update testcase1",
				"<set>",
						"`controller_name` = #{entity.controllerName}, ",
						"`describe` = #{entity.describe}, ",
						"`method` = #{entity.method}, ",
						"`dept_id` = #{entity.deptId}, ",
						"`created_time` = #{entity.createdTime}, ",
						"`updated_time` = #{entity.updatedTime}, ",
						"`exec_last_time` = #{entity.execLastTime}, ",
						"`status` = #{entity.status}",
				"</set>",
				"where test_case_id = #{entity.testCaseId}",
			"</script>"
	})
	int updateEntity(@Param("entity")Testcase1Entity testcase1Entity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update testcase1",
					"<set>",
						"`controller_name` = #{obj.controllerName}, ",
						"`describe` = #{obj.describe}, ",
						"`method` = #{obj.method}, ",
						"`dept_id` = #{obj.deptId}, ",
						"`created_time` = #{obj.createdTime}, ",
						"`updated_time` = #{obj.updatedTime}, ",
						"`exec_last_time` = #{obj.execLastTime}, ",
						"`status` = #{obj.status}",
					"</set>",
					"where test_case_id = #{entity.testCaseId}",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(@Param("list")List<Testcase1Entity> testcase1Entitys);
	
	@Delete({
			"<script>",
				"delete from testcase1",
				"<where>",
				"<if test='entity.controllerName != null  and entity.controllerName  != \"\" '> and `controller_name` = #{controllerName} </if>",
				"<if test='entity.describe != null  and entity.describe  != \"\" '> and `describe` = #{describe} </if>",
				"<if test='entity.method != null  and entity.method  != \"\" '> and `method` = #{method} </if>",
				"<if test='entity.deptId != null  '> and `dept_id` = #{deptId} </if>",
				"<if test='entity.createdTime != null  '> and `created_time` = #{createdTime} </if>",
				"<if test='entity.updatedTime != null  '> and `updated_time` = #{updatedTime} </if>",
				"<if test='entity.execLastTime != null  '> and `exec_last_time` = #{execLastTime} </if>",
				"<if test='entity.status != null  '> and `status` = #{status}</if>",
				"</where>",
				"and test_case_id = #{obj.testCaseId}",
			"</script>"
	})
	int deleteEntity(@Param("entity")Testcase1Entity testcase1Entity);
	
	@Delete({
		"<script>",
		"<foreach collection ='list' item='obj' separator =';'>",
			"delete from testcase1",
			"<where>",

				"<if test='obj.controllerName != null  and obj.controllerName  != \"\" '> and `controller_name` = #{obj.controllerName}</if>",

				"<if test='obj.describe != null  and obj.describe  != \"\" '> and `describe` = #{obj.describe}</if>",

				"<if test='obj.method != null  and obj.method  != \"\" '> and `method` = #{obj.method}</if>",
				"<if test='obj.deptId != null  '> and `dept_id` = #{obj.deptId}</if>",
				"<if test='obj.createdTime != null  '> and `created_time` = #{obj.createdTime}</if>",
				"<if test='obj.updatedTime != null  '> and `updated_time` = #{obj.updatedTime}</if>",
				"<if test='obj.execLastTime != null  '> and `exec_last_time` = #{obj.execLastTime}</if>",
				"<if test='obj.status != null  '> and `status` = #{obj.status}</if>",
			"</where>",
			"and test_case_id = #{obj.testCaseId}",
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(@Param("list")List<Testcase1Entity> testcase1Entitys);
}
