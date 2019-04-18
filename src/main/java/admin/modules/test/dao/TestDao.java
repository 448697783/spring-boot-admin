package admin.modules.test.dao;

import admin.modules.test.entity.TestEntity;
import admin.mapper.base.BaseMappper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-06-15 20:06:11
 */
@Mapper
public interface TestDao {
	@Select({
			"<script>",
			"select",
					"controller_name, ",
					"test_case_id, ",
					"created_time, ",
					"updated_time, ",
					"exec_last_time, ",
					"status",
			"from",
				"testcase1",
			"<where>",
				"<if test='controllerName != null'>AND controller_name = #{controllerName} </if>",
				"<if test='testCaseId != null'>AND test_case_id = #{testCaseId} </if>",
				"<if test='createdTime != null'>AND created_time = #{createdTime} </if>",
				"<if test='updatedTime != null'>AND updated_time = #{updatedTime} </if>",
				"<if test='execLastTime != null'>AND exec_last_time = #{execLastTime} </if>",
				"<if test='status != null'>AND status = #{status}</if>",
			"</where>",
			"</script>"
    })
	List<TestEntity> selectEntityListAll(TestEntity TestEntity);

	@Insert({
			"insert into testcase1",
				"(",
					"controller_name, ",
					"test_case_id, ",
					"created_time, ",
					"updated_time, ",
					"exec_last_time, ",
					"status",
				")",
			"values",
				"(",
					"#{controllerName}, ",
					"#{testCaseId}, ",
					"#{createdTime}, ",
					"#{updatedTime}, ",
					"#{execLastTime}, ",
					"#{status}",
				")"
    })
	int insertEntity(TestEntity TestEntity);
	
	@Insert({
			"<script>",
			"insert into testcase1",
				"(",
				"controller_name, ",
				"test_case_id, ",
				"created_time, ",
				"updated_time, ",
				"exec_last_time, ",
				"status",
				")",
			"values",
			"<foreach collection ='list' item='obj' separator =','>",
				"(",
					"#{obj.controllerName}, ",
					"#{obj.testCaseId}, ",
					"#{obj.createdTime}, ",
					"#{obj.updatedTime}, ",
					"#{obj.execLastTime}, ",
					"#{obj.status}",
				")",
			"</foreach >",
			"</script>",
    })
	int batchInsertEntity(List<TestEntity> TestEntitys);
	
	@Update({
			"<script>",
			"update testcase1",
				"<set>",
					"<if test='controllerName != null'>controller_name = #{controllerName}, </if>",
					"<if test='createdTime != null'>created_time = #{createdTime}, </if>",
					"<if test='updatedTime != null'>updated_time = #{updatedTime}, </if>",
					"<if test='execLastTime != null'>exec_last_time = #{execLastTime}, </if>",
					"<if test='status != null'>status = #{status}</if>",
				"</set>",
				"where test_case_id = #{testCaseId}",
			"</script>"
	})
	int updateEntity(TestEntity TestEntity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update testcase1",
					"<set>",
						"<if test='obj.controllerName != null'>controller_name = #{obj.controllerName}, </if>",
						"<if test='obj.createdTime != null'>created_time = #{obj.createdTime}, </if>",
						"<if test='obj.updatedTime != null'>updated_time = #{obj.updatedTime}, </if>",
						"<if test='obj.execLastTime != null'>exec_last_time = #{obj.execLastTime}, </if>",
						"<if test='obj.status != null'>status = #{obj.status}</if>",
					"</set>",
					"where test_case_id = #{obj.testCaseId}",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(List<TestEntity> TestEntitys);
	
	@Delete({
			"<script>",
				"delete from testcase1",
				"<where>",
					"<if test='controllerName != null'>controller_name = #{controllerName} </if>",
					"<if test='testCaseId != null'>test_case_id = #{testCaseId} </if>",
					"<if test='createdTime != null'>created_time = #{createdTime} </if>",
					"<if test='updatedTime != null'>updated_time = #{updatedTime} </if>",
					"<if test='execLastTime != null'>exec_last_time = #{execLastTime} </if>",
					"<if test='status != null'>status = #{status}</if>",
				"</where>",
			"</script>"
	})
	int deleteEntity(TestEntity TestEntity);
	
	@Delete({
		"<script>",
		"<foreach collection ='list' item='obj' separator =';'>",
			"delete from testcase1",
			"where",
				"<if test='obj.controllerName != null'>controller_name = #{obj.controllerName}</if>",
				"<if test='obj.testCaseId != null'>test_case_id = #{obj.testCaseId}</if>",
				"<if test='obj.createdTime != null'>created_time = #{obj.createdTime}</if>",
				"<if test='obj.updatedTime != null'>updated_time = #{obj.updatedTime}</if>",
				"<if test='obj.execLastTime != null'>exec_last_time = #{obj.execLastTime}</if>",
				"<if test='obj.status != null'>status = #{obj.status}</if>",
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(List<TestEntity> TestEntitys);
}
