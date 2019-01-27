package admin.modules.testcase1.service;

import admin.modules.testcase1.entity.Testcase1Entity;

import java.util.List;
import java.util.Map;

/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2019-01-27 22:30:03
 */
public interface ITestcase1Service {

	List<Testcase1Entity> queryTestcase1EntityListForPage(Testcase1Entity testcase1Entity,Long startRow, Long endRow);
	
	Long queryTestcase1EntityListForPageCount(Testcase1Entity testcase1Entity);
	
	List<Testcase1Entity> queryTestcase1EntityListAll(Testcase1Entity testcase1Entity);
	
	int saveTestcase1Entity(Testcase1Entity testcase1Entity);
	
	int saveTestcase1Entity(List<Testcase1Entity> testcase1Entitys);
	
	int editTestcase1Entity(Testcase1Entity testcase1Entity);
	
	int editTestcase1Entity(List<Testcase1Entity> testcase1Entitys);
	
	int removeTestcase1Entity(Testcase1Entity testcase1Entity);
	
	int removeTestcase1Entity(List<Testcase1Entity> testcase1Entity);
}
