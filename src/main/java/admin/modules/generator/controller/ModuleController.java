package admin.modules.generator.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import admin.common.annotation.SysLog;
import admin.common.exception.CheckException;
import admin.common.utils.Constant.MenuType;
import admin.common.utils.R;
import admin.modules.api.annotation.AuthIgnore;
import admin.modules.generator.entity.ModuleEntity;
import admin.modules.generator.service.ModuleService;
import admin.modules.generator.service.SpringBootTemplate;




/**
 * 菜单管理
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2017-09-07 00:18:45
 */
@Controller
@RequestMapping("sys/module")
public class ModuleController {
//	@Autowired
//	private moduleService moduleService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private SpringBootTemplate springBootTemplate;
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	@RequiresPermissions("sys:module:list")
	public List<ModuleEntity> list(){
		List<ModuleEntity> menuList = moduleService.queryList(new HashMap<String,Object>());

		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@ResponseBody
	@RequiresPermissions("sys:module:select")
	public R select(){
		//查询列表数据
		List<ModuleEntity> menuList = moduleService.queryNotButtonList();
		
		//添加顶级菜单
		ModuleEntity root = new ModuleEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{menuId}")
	@ResponseBody
	@RequiresPermissions("sys:module:info")
	public R info(@PathVariable("menuId") Long menuId){
		ModuleEntity menu = moduleService.queryObject(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@RequestMapping("/save")
	@RequiresPermissions("sys:module:save")
	@ResponseBody
	public R save(@RequestBody ModuleEntity menu){
		//数据校验
		verifyForm(menu);
		
		moduleService.save(menu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@RequestMapping("/update")
	@RequiresPermissions("sys:module:update")
	@ResponseBody
	public R update(@RequestBody ModuleEntity menu){
		//数据校验
		verifyForm(menu);
				
		moduleService.update(menu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:module:delete")
	@ResponseBody
	public R delete(long menuId){

		//判断是否有子菜单或按钮
		List<ModuleEntity> menuList = moduleService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return R.error("请先删除子菜单或按钮");
		}

		moduleService.deleteBatch(new Long[]{menuId});
		
		return R.ok();
	}
	/**
	 * 生成spring模版代码
	 */
	@RequestMapping("/springbootproject")
	@RequiresPermissions("sys:module:springbootproject")
	@AuthIgnore
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		String[] tableNames = new String[]{};
		String dis = request.getParameter("dis");
		String projectName = request.getParameter("projectName");
		String packageName = request.getParameter("packageName");
		String[] parseArray = dis.split(",");
		
		List<ModuleEntity> queryByIDs = moduleService.queryByIDs(parseArray);
		byte[] data = springBootTemplate.generatorCode(queryByIDs,projectName, packageName);
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\""+projectName+".zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(ModuleEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new CheckException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new CheckException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == MenuType.MENU.getValue()){
//			if(StringUtils.isBlank(menu.getUrl())){
//				throw new CheckException("菜单URL不能为空");
//			}
		}
		
		//上级菜单类型
		int parentType = MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			ModuleEntity parentMenu = moduleService.queryObject(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == MenuType.CATALOG.getValue() ||
				menu.getType() == MenuType.MENU.getValue()){
			if(parentType != MenuType.CATALOG.getValue()){
//				throw new CheckException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == MenuType.BUTTON.getValue()){
//			if(parentType != MenuType.MENU.getValue()){
//				throw new CheckException("上级菜单只能为菜单类型");
//			}
			return ;
		}
	}
}
