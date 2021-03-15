package admin.modules.createSql.service.impl;

import admin.common.exception.CheckException;
import admin.common.utils.Base64Binrary;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import admin.modules.createSql.dao.CreateSqlDao;
import admin.modules.createSql.entity.CreateSqlEntity;
import admin.modules.createSql.service.ICreateSqlService;
import admin.common.utils.ShiroUtils;
import admin.common.annotation.AutoRetry;
import admin.common.exception.AutoRetryException;




@Service("createSqlService")
public class CreateSqlServiceImpl implements ICreateSqlService {

	@Override
	public String createSql(CreateSqlEntity createSqlEntity) throws UnsupportedEncodingException {

//		if(!StringUtils.isBlank(createSqlEntity.getOrdernum())||!StringUtils.isBlank(createSqlEntity.getOutTradeNo())){
//			if(createSqlEntity.getOrdernum().length()!=32&&StringUtils.isBlank(createSqlEntity.getCustomernum())){
//				throw new CheckException("ordernum长度小于32时,customernum不能为空");
//			}
//		}
		StringBuilder sb = new StringBuilder();
		
		if(!StringUtils.isBlank(createSqlEntity.getOrdernum())){
			String customerNum = StringUtils.isBlank(createSqlEntity.getCustomernum())?"":createSqlEntity.getCustomernum().trim();
			String orderNum = StringUtils.isBlank(createSqlEntity.getOrdernum())?"":createSqlEntity.getOrdernum().trim();
			String substring = StringUtils.isBlank(customerNum)?orderNum.substring(orderNum.length() - 10,orderNum.length() - 4):customerNum.substring(customerNum.length() - 6);
			int orderIndex = Integer.valueOf(substring)%2000;
			int payIndex = Integer.valueOf(substring)%4000;
			int orderDbIndex = orderIndex/200;
			int payDbIndex = payIndex/200;
			
			sb.append("<h2>order_center: </h2>");
			sb.append("<strong>mydb-dlbordercenter-"+(orderDbIndex<10?"0"+orderDbIndex:orderDbIndex)+"02.db.jdfin.local:3306:order_center_"+orderDbIndex);
			sb.append("</strong><br>");
			sb.append("<span> select * from <strong>order_info_"+orderIndex);
			sb.append("</strong> where num='"+orderNum+"'</span>");

//		sb.append("<br>");
			sb.append("<HR style=\"BORDER-RIGHT: #00686b 1px dotted; BORDER-TOP: #00686b 1px dotted; BORDER-LEFT: #00686b 1px dotted; BORDER-BOTTOM: #00686b 1px dotted\" noShade SIZE=1>");
			
			sb.append("<h2>pay_center: </h2>");
			sb.append("<strong>mydb-dlbpaycenter-"+(payDbIndex<10?"0"+payDbIndex:payDbIndex)+"02.db.jdfin.local:3306:pay_center_"+payDbIndex);
			sb.append("</strong> <br>");
			sb.append("select * from <strong>pay_info_"+payIndex);
			sb.append("</strong> where order_num='"+orderNum+"'");
			
			sb.append("<br>");
			sb.append("select * from <strong>pay_extends_info_"+payIndex);
			sb.append(" </strong> where order_num='"+orderNum+"'");
			sb.append("<br>");
			
			sb.append("<HR style=\"BORDER-RIGHT: #00686b 1px dotted; BORDER-TOP: #00686b 1px dotted; BORDER-LEFT: #00686b 1px dotted; BORDER-BOTTOM: #00686b 1px dotted\" noShade SIZE=1>");
			String pinBase64 = Base64Binrary.encodeBase64Binrary(substring.getBytes("utf-8"));
			int  hashCode = Math.abs(pinBase64.hashCode());
			int  routeAggreLxfCodeInt = hashCode % 100000;
			
			long dbAggreLxfIndex = getDbIndex(routeAggreLxfCodeInt);
			String tbAggreLxfIndexStr = getTableIndex(routeAggreLxfCodeInt);
			sb.append("<h2>蓝小方分表规则 aggretrade: </h2>");
			sb.append("<strong>mydb-aggrepaytrade-"+(dbAggreLxfIndex<10?"0"+dbAggreLxfIndex:dbAggreLxfIndex)+"02.db.jdfin.local:3306:aggre_paytrade_"+dbAggreLxfIndex);
			sb.append("</strong> <br>");
			sb.append("select * from <strong>trade_base_"+tbAggreLxfIndexStr);
			sb.append("</strong> where trade_no='"+orderNum+"'");
			sb.append("<br>");
			sb.append("select * from <strong>trade_pay_request_"+tbAggreLxfIndexStr);
			sb.append("</strong> where trade_no='"+orderNum+"'");
			sb.append("<br>");
			sb.append("select * from <strong>trade_pay_request_result_"+tbAggreLxfIndexStr);
			sb.append("</strong> where trade_no='"+orderNum+"'");
			sb.append("<br>");
			sb.append("select * from <strong>trade_result_"+tbAggreLxfIndexStr);
			sb.append("</strong> where trade_no='"+orderNum+"'");
		}
		
		if(StringUtils.isNotBlank(createSqlEntity.getOutTradeNo())){
			String outTradeNo = StringUtils.isBlank(createSqlEntity.getOutTradeNo())?"":createSqlEntity.getOutTradeNo().trim();
			String pinBase64 = Base64Binrary.encodeBase64Binrary(outTradeNo.getBytes("utf-8"));
			int hashCode = Math.abs(pinBase64.hashCode());
			int  routeCodeInt = hashCode % 100000;
			
			long dbIndex = getDbIndex(routeCodeInt);
			String tbIndexStr = getTableIndex(routeCodeInt);
			
			sb.append("<HR style=\"BORDER-RIGHT: #00686b 1px dotted; BORDER-TOP: #00686b 1px dotted; BORDER-LEFT: #00686b 1px dotted; BORDER-BOTTOM: #00686b 1px dotted\" noShade SIZE=1>");
			sb.append("<h2>原分库分表规则 aggretrade: </h2>");
			sb.append("<strong>mydb-aggrepaytrade-"+(dbIndex<10?"0"+dbIndex:dbIndex)+"02.db.jdfin.local:3306:aggre_paytrade_"+dbIndex);
			sb.append("</strong> <br>");
			sb.append("select * from <strong>trade_base_"+tbIndexStr);
			sb.append("</strong> where out_trade_no='"+outTradeNo+"'");
			sb.append("<br>");
			sb.append("select * from <strong>trade_pay_request_"+tbIndexStr);
			sb.append("</strong> where trade_no in");
			sb.append("(select trade_no from <strong>trade_base_"+tbIndexStr);
			sb.append("</strong> where out_trade_no='"+outTradeNo+"')");
			sb.append("<br>");
			sb.append("select * from <strong>trade_result_"+tbIndexStr);
			sb.append("</strong> where trade_no in");
			sb.append("(select trade_no from <strong>trade_base_"+tbIndexStr);
			sb.append("</strong> where out_trade_no='"+outTradeNo+"')");
			sb.append("<br>");
			sb.append("select * from <strong>trade_pay_request_result_"+tbIndexStr);
			sb.append("</strong> where trade_no in");
			sb.append("(select trade_no from <strong>trade_base_"+tbIndexStr);
			sb.append("</strong> where out_trade_no='"+outTradeNo+"')");
			
			if(StringUtils.isNotBlank(createSqlEntity.getCustomernum())&&StringUtils.isNotBlank(createSqlEntity.getOutTradeNo())) {
				String routeAggreLxfCodeInt = createSqlEntity.getCustomernum().substring(createSqlEntity.getCustomernum().length() - 6);
				long dbAggreLxfIndex = getDbIndex(Integer.parseInt(routeAggreLxfCodeInt));
				String tbAggreLxfIndexStr = getTableIndex(Integer.parseInt(routeAggreLxfCodeInt));
				sb.append("<h2>蓝小方分表规则 aggretrade: </h2>");
				sb.append("<strong>mydb-aggrepaytrade-"+(dbAggreLxfIndex<10?"0"+dbAggreLxfIndex:dbAggreLxfIndex)+"02.db.jdfin.local:3306:aggre_paytrade_"+dbAggreLxfIndex);
				sb.append("</strong> <br>");
				sb.append("select * from <strong>trade_base_"+tbAggreLxfIndexStr);
				sb.append("</strong> where trade_no='"+createSqlEntity.getOutTradeNo()+"'");
				sb.append("<br>");
				sb.append("select * from <strong>trade_pay_request_"+tbAggreLxfIndexStr);
				sb.append("</strong> where trade_no='"+createSqlEntity.getOutTradeNo()+"'");
				sb.append("<br>");
				sb.append("select * from <strong>trade_pay_request_result_"+tbAggreLxfIndexStr);
				sb.append("</strong> where trade_no='"+createSqlEntity.getOutTradeNo()+"'");
				sb.append("<br>");
				sb.append("select * from <strong>trade_result_"+tbAggreLxfIndexStr);
				sb.append("</strong> where trade_no='"+createSqlEntity.getOutTradeNo()+"'");
			}
			
		}
		return sb.toString();
	}
	
	private long getDbIndex(int routeCodeInt) {
		long mode = 8 * 200;
		return routeCodeInt % mode / 200;
	}
	
	private String getTableIndex(int routeCodeInt) {
		long tbIndex = routeCodeInt % 200;
		DecimalFormat df = new DecimalFormat("000");
		return df.format(tbIndex);
	}
}
