package admin.modules.generator.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;

import admin.common.exception.BaseException;
import admin.common.utils.DateUtils;
import admin.modules.generator.entity.ModuleEntity;

/**
 * 代码生成器   工具类
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年12月19日 下午11:40:24
 */
@Service
public class SpringBootTemplate {

	
	public  List<String> getTemplates(){
		List<String> templates = new ArrayList<String>();
//		templates.add("template/Entity.java.vm");
		templates.add("template.common.annotation.Datasource.java.vm");
		return templates;
	}
	
	/**
	 * 生成代码
	 */
	public byte[] generatorCode(List<ModuleEntity> templates,String projectName,String packageName){
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		//设置velocity资源加载器
		Properties prop = new Properties();  
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");  
		Velocity.init(prop);
		
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("projectName", projectName);
		map.put("packageName", packageName);
		map.put("author", "auto");
		map.put("email", "email");
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		
		
		
		for(ModuleEntity template : templates){
			String name = template.getName();
			if(StringUtils.isNotBlank(name)){
				String[] split = name.split("_");
				if(map.containsKey(split[0])){
					continue;
				}
				map.put(split[0], template.getPerms());
			}
		}
        VelocityContext context = new VelocityContext(map);
        
        //获取模板列表
		fromVelocityToZipSteam(zip,  context, templates,projectName,packageName);
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
	public void fromVelocityToZipSteam(ZipOutputStream zip,VelocityContext context,String projectName) {
		StringWriter sw = new StringWriter();
		velocity(context, sw, "template/pom.xml.vm");
		try {
			zip.putNextEntry(new ZipEntry(projectName+"/pom.xml"));
			IOUtils.write(sw.toString(), zip, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(sw);
		}
		
	}

	public void fromVelocityToZipSteam(ZipOutputStream zip,VelocityContext context, List<ModuleEntity> templates,String projectName,String projectPackage) {
		String project = projectName  ;;
		String pathClass="";
		
		
		for(ModuleEntity template : templates){
 			if("spring-boot".equals(template.getName())){
				fromVelocityToZipSteam(zip, context, projectName);
			}
			if(template.getUrl()==null||"".equals(template.getUrl())){
				continue;
			}
			String[] split = template.getUrl().split(";");
			for(String str : split){
				StringWriter sw = new StringWriter();
				if(str==null||"".equals(str.trim())||str.trim().startsWith("$")){
					continue;
				}
				
				//渲染模板
				velocity(context, sw, str);
				pathClass = (str.trim().replaceAll("\\.vm", "")).replace("main/java", "main/java/"+projectPackage.replace(".", "/"));
				
				try {
					zip.putNextEntry(new ZipEntry(project+pathClass.replaceAll("template", "")));  
					IOUtils.write(sw.toString(), zip, "UTF-8");
					IOUtils.closeQuietly(sw);
				} catch (IOException e) {
					throw new BaseException("渲染模板失败，模版名：" + templates, e);
				}
			}
		}
	}

	private static Template velocity(VelocityContext context, StringWriter sw, String velocityPath) {
		Template tpl = Velocity.getTemplate(velocityPath.trim(),"UTF-8");
		tpl.merge(context, sw);
		return tpl;
	}
	
}
