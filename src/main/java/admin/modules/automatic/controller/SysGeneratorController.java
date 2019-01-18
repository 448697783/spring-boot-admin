package admin.modules.automatic.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import admin.common.annotation.DataSource;
import admin.common.baseenum.DataSourceEnum;
import admin.common.utils.PageUtils;
import admin.common.utils.Query;
import admin.common.utils.R;
import admin.datasource.MultiDataSource;
import admin.modules.api.annotation.AuthIgnore;
import admin.modules.automatic.service.SysGeneratorService;
import admin.modules.automatic.vo.CoderGeneratorVO;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58
 */
@RestController
@RequestMapping("sys/generator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@AuthIgnore
	public R list(@RequestParam Map<String, Object> params){
		if(params.get("aliase")!=null) {
			MultiDataSource.switchSource(params.get("aliase").toString());
		}
		PageUtils pageUtil = null;
		try {
			//查询列表数据
			Query query = new Query(params);
			List<Map<String, Object>> list = sysGeneratorService.queryList(query);
			int total = sysGeneratorService.queryTotal(query);
			
			 pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		}finally {
			MultiDataSource.clear();
		}
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	@AuthIgnore
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String body = request.getParameter("text");
		CoderGeneratorVO vo = (CoderGeneratorVO) JSONObject.toJavaObject(JSONObject.parseObject(body), CoderGeneratorVO.class);
		if(vo.getAliase()!=null) {
			MultiDataSource.switchSource(vo.getAliase());
		}
		try {
			byte[] data = sysGeneratorService.generatorCode(vo);
			
			response.reset();  
			response.setHeader("Content-Disposition", "attachment; filename=\""+vo.getProjectName()+".zip\"");  
			response.addHeader("Content-Length", "" + data.length);  
			response.setContentType("application/octet-stream; charset=UTF-8");  
			
			IOUtils.write(data, response.getOutputStream());  
		}finally {
			MultiDataSource.clear();
		}
	}
	
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/queryTableColums")
	@AuthIgnore
	@ResponseBody
	public R queryTableColums(String tableName,String aliase) throws IOException{
		if(aliase!=null) {
			MultiDataSource.switchSource(aliase);
		}
		PageUtils pageUtil;
		try {
			List<Map<String, Object>> queryColumns = sysGeneratorService.queryColumns(tableName);
			for (Map<String, Object> map: queryColumns) {
				
				if(map.get("CHARACTER_MAXIMUM_LENGTH")!=null&&map.get("CHARACTER_MAXIMUM_LENGTH").toString().length()>0&&!"NUMBER".equals(map.get("DATATYPE"))&&!map.get("DATATYPE").toString().startsWith("TIMESTAMP")){
					map.put("CHARACTER_MAXIMUM_LENGTH","@Size(min=0,max="+map.get("CHARACTER_MAXIMUM_LENGTH")+",message=\""+map.get("COLUMNCOMMENT")+"长度为0-"+map.get("CHARACTER_MAXIMUM_LENGTH")+"个字符\")");
				}
				if(map.get("NUMERIC_PRECISION")!=null&&map.get("NUMERIC_PRECISION").toString().length()>0&&"NUMBER".equals(map.get("DATATYPE"))){
					int len = Integer.parseInt(map.get("NUMERIC_PRECISION").toString());
					StringBuilder tempLen = new StringBuilder();
					if(len>19) {
						map.put("DATATYPE","BigDecimal");
					}
					for(int i=0;i<len;i++) {
						tempLen.append("9");
					}
					map.put("NUMERIC_PRECISION","@Min(value=0,message=\""+map.get("COLUMNCOMMENT")+"值应大于0)\n@Max(value="+map.get("NUMERIC_PRECISION")+","+map.get("COLUMNCOMMENT")+"值应小于"+tempLen+"\")");
				}
				if("NO".equals(map.get("IS_NULLABLE").toString())) {
					map.put("IS_NULLABLE","@NotNull("+map.get("COLUMNCOMMENT")+"为必输项)");
				}
			}
			pageUtil = new PageUtils(queryColumns, 100, 1000, 1);
		}finally {
			MultiDataSource.clear();
		}
		return R.ok().put("page", pageUtil);
	}
}
