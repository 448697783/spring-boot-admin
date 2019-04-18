package admin.modules.testcase2.test.service;

import admin.modules.testcase2.test.entity.aaaaaEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-04-18 20:12:20
 */
public interface IaaaaaService {

	List<aaaaaEntity> queryaaaaaEntityListForPage(aaaaaEntity aaaaaEntity,Long startRow, Long endRow);
	
	Long queryaaaaaEntityListForPageCount(aaaaaEntity aaaaaEntity);
}
