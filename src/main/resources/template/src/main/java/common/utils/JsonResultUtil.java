package ${packageName}.common.utils;

import ${packageName}.common.baseenum.BaseEnum.BusinessExceptionEnum;
import ${packageName}.common.utils.JsonResult;


public class JsonResultUtil {

    private static JsonResult jsonResult;

    public static final int SUCCESS = 1;

    public static final int ERROR = 0;
    
    private JsonResultUtil(){}

    /**
     * 成功返回结果
     *
     * @param totalCount
     * @param data
     * @return
     */
    public static JsonResult success(long totalCount, Object data) {
        jsonResult = new JsonResult();
        jsonResult.setFlag(SUCCESS);
        jsonResult.setTotalCount(totalCount);
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @return
     */
    public static JsonResult success(Object data) {
        jsonResult = new JsonResult();
        jsonResult.setFlag(SUCCESS);
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 成功返回结果
     *
     * @return
     */
    public static JsonResult success() {
        jsonResult = new JsonResult();
        jsonResult.setFlag(SUCCESS);
        return jsonResult;
    }

    /**
     * 失败返回结果
     *
     * @param msg
     * @return
     */
    public static JsonResult error(String msg) {
        jsonResult = new JsonResult();
        jsonResult.setFlag(ERROR);
        jsonResult.setMsg(msg);
        return jsonResult;
    }
    
    public static JsonResult error(BusinessExceptionEnum error) {
    	JsonResult jsonResult = new JsonResult();
        jsonResult.setFlag(0);
        jsonResult.setMsg(error.Msg());
        jsonResult.setCode(error.Code());
        return jsonResult;
    }
    
    public static JsonResult error(String msg,int code) {
        jsonResult = new JsonResult();
        jsonResult.setFlag(ERROR);
        jsonResult.setCode(code);
        jsonResult.setMsg(msg);
        return jsonResult;
    }
    public static JsonResult buildResult(int flag,Object data) {
        jsonResult = new JsonResult();
        jsonResult.setFlag(flag);
        jsonResult.setData(data);
        return jsonResult;
    }
    public static JsonResult buildResult(int flag,int code,Object data) {
    	jsonResult = new JsonResult();
    	jsonResult.setFlag(flag);
    	jsonResult.setCode(code);
    	jsonResult.setData(data);
    	return jsonResult;
    }
    
    public static JsonResult buildResult(int flag,Object data,String msg) {
        jsonResult = new JsonResult();
        jsonResult.setFlag(flag);
        jsonResult.setData(data);
        jsonResult.setMsg(msg);
        return jsonResult;
    }
}
