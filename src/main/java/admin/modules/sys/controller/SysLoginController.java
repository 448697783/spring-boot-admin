package admin.modules.sys.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import admin.common.utils.R;
import admin.common.utils.ShiroUtils;
import admin.modules.sys.entity.SysDeptEntity;
import admin.modules.sys.entity.SysUserEntity;
import admin.modules.sys.service.SysDeptService;
import admin.modules.sys.service.SysUserService;
import admin.modules.sys.service.SysUserTokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;

/**
 * 登录相关
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
public class SysLoginController {
	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysUserTokenService sysUserTokenService;

	@GetMapping(value="/captcha.jpg",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseHeader
	@ApiOperation(value="获取验证码",produces="application/octet-stream",httpMethod="GET",tags="用户登陆")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	@ApiOperation(value="用户登陆",produces="application/json",httpMethod="POST",tags="用户登陆")
	public Map<String, Object> login(String username, String password, String captcha)throws IOException {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return R.error("验证码不正确");
		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(username);

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		SysDeptEntity queryObject = sysDeptService.queryObject( user.getDeptId());
		r.put("parentId", queryObject==null?0:queryObject.getParentId());
		return r;
	}
	
}
