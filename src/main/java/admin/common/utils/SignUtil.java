package admin.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.NavigableSet;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cglib.beans.BeanMap;

/**
 * @Auther: cjw
 * @Date: 2018/11/20 17:40
 * @Description:
 */
public class SignUtil {

	/** 字符编码 */
	private final static String INPUT_CHARSET = "UTF-8";

	/**
	 * @param params 通知返回来的参数数组
	 * @return 验证结果
	 */
	public static boolean verify(Object obj, String accessKeySecret,boolean isWeb) {
		BeanMap create = BeanMap.create(obj);
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.putAll(create);
		String sign = "";
		StringBuffer str = new StringBuffer();
		NavigableSet<String> navigableKeySet = params.navigableKeySet();
		Object value = null;
		for (String key : navigableKeySet) {
			if ("sign".equals(key)) {
				sign = String.valueOf(params.get("sign"));
				continue;
			}
			value = params.get(key);
			if(value == null) {
				continue;
			}
			str.append(String.valueOf(value));
		}
		String mysign = DigestUtils.md5Hex(getContentBytes(str.toString() + accessKeySecret, INPUT_CHARSET));
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String sign(Object obj,String accessKeySecret) {
		BeanMap create = BeanMap.create(obj);
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.putAll(create);
		String sign = "";
		StringBuffer str = new StringBuffer();
		NavigableSet<String> navigableKeySet = params.navigableKeySet();
		Object value = null;
		for (String key : navigableKeySet) {
			if ("sign".equals(key)) {
				sign = String.valueOf(params.get("sign"));
				continue;
			}
			value = params.get(key);
			if(value == null) {
				continue;
			}
			str.append(String.valueOf(value));
		}
		String mysign = DigestUtils.md5Hex(getContentBytes(str.toString() + accessKeySecret, INPUT_CHARSET));
		return mysign;
	}

	/**
	 * @param params 通知返回来的参数数组
	 * @return 验证结果
	 */
	public static boolean verify(Object obj, String accessKeySecret) {
		return verify(obj, accessKeySecret, true);
	}
	/**
	 * 
	 * @param content
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}
}
