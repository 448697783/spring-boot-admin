package io.renren;

import admin.common.httpclient.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.data.domain.Sort;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanghonghui
 * @description 提取注释与属性对照信息
 * @Date Created in 10:11-2019/9/10
 */
public class ExtractAnnotationField {

    public ExtractAnnotationField() {
        //NO_OP
    }

    /**
     * 功能描述: 扫描一个文件夹下面的所有jar，不包含子文件夹和子jar
     *
     * @param directoryPath
     * @return:java.util.Map<java.lang.String,java.lang.Class<?>>
     * @since: v1.0
     * @Author:wanghonghui
     * @Date: 2019/9/12-15:21
     */
    public static Map<String, Class<?>> loadAllJarFromAbsolute(String directoryPath) throws IOException {

        File directory = new File(directoryPath);
        File directory1 = new File("D:\\tool\\classfild\\"+directory.getName()+".csv");
        if(directory1.exists()){
            directory1.mkdir();
        }else{
            directory1.delete();
        }

        directory1.createNewFile();

        StringBuilder sb = new StringBuilder();
        StringBuilder outputSb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try{
            if (!directory.isDirectory()) {
                bufferedReader = new BufferedReader(new FileReader(directory));
                String s = null;
                int f = 0;
                while((s = bufferedReader.readLine())!=null){//使用readLine方法，一次读一行
                    sb.append(s );
                }
            }
        }finally {
            if(bufferedReader!=null){
                bufferedReader.close();
            }
        }
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";
        pattern = "(\\/\\*(\\s|.)*?\\*\\/)?\\s*?private\\s+?(\\w+?)\\s+?(\\w+?)(;|\\s+?|=)";
        String start = "\\/\\*(\\s|.*?);";
        String start1 = "private(\\s|.*?);";
        String s1 = sb.toString();

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(start);
        Pattern startr = Pattern.compile(start1);
        Pattern startp = Pattern.compile(pattern);
        Matcher startm = startr.matcher(s1);



        TreeSet<String> set = new TreeSet<>();
//        System.out.print(s1);
        // 现在创建 matcher 对象
        Matcher m1 = r.matcher(s1);
        String s =null;
        while(m1.find()) {

            String group1 = m1.group(0);
            if(group1.indexOf("private")!=-1){
                Matcher m = startp.matcher(group1);
                while(m.find()) {
    //            System.out.println(group);
                    String group = m.group(1);
                    if (StringUtils.isNotBlank(group) && group.indexOf(" class ") == -1) {
                        s = group.replaceAll("\\*", "").replaceAll("\\/", "").replaceAll("\\\\", "").replaceAll("\\s", "");
                        System.out.println(String.format("%-20s     %-20s    %-20s", m.group(4), m.group(3), s));
                        set.add(m.group(4) + "," + m.group(3) + "," + s + "\n");
                    }
                }
            }
        }
        String[] strings = set.toArray(new String[0]);

        for (int i=0;i<strings.length;i++){
            outputSb.append(strings[i]);
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(directory1));
        try {
            bufferedWriter.write(outputSb.toString());
            bufferedWriter.flush();
        }finally {
            if(bufferedWriter!=null){
                bufferedWriter.close();
            }
        }
        return null;
    }

    public static void sort(){
        //trade_base
//        String strFild = "trade_no\n" +
//                "app_id\n" +
//                "merchant_no\n" +
//                "out_trade_no\n" +
//                "trade_subject\n" +
//                "trade_type\n" +
//                "sub_trade_type\n" +
//                "trade_remark\n" +
//                "currency\n" +
//                "amount\n" +
//                "deadline_time\n" +
//                "out_ext\n" +
//                "biz_trade_no\n" +
//                "page_back_url\n" +
//                "server_notify_url\n" +
//                "return_params\n" +
//                "ext_map\n" +
//                "request_version\n" +
//                "out_trade_date\n" +
//                "out_trade_time\n" +
//                "out_referer_hosts\n" +
//                "retry_pay_count\n" +
//                "refunded_amount\n" +
//                "original_trade_no\n" +
//                "compensate_status\n" +
//                "created_date\n" +
//                "modified_date\n" +
//                "remote_ip\n" +
//                "out_trade_ip\n" +
//                "out_trade_port\n" +
//                "auth_code\n" +
//                "agent_merchant_no\n" +
//                "shop_id\n" +
//                "user_id\n" +
//                "terminal_id\n" +
//                "out_shop_id\n" +
//                "MODIFIED_TIME";

//        String strFild=
//                "trade_no(varchar(32))\n" +
//                "app_id(varchar(32))\n" +
//                "merchant_no(varchar(32))\n" +
//                "out_trade_no(varchar(64))\n" +
//                "trade_subject(varchar(255))\n" +
//                "trade_type(varchar(16))\n" +
//                "sub_trade_type(varchar(8))\n" +
//                "trade_remark(varchar(128))\n" +
//                "currency(varchar(16))\n" +
//                "amount(decimal(16))\n" +
//                "deadline_time(datetime(19))\n" +
//                "out_ext(varchar(3072))\n" +
//                "biz_trade_no(varchar(64))\n" +
//                "page_back_url(varchar(500))\n" +
//                "server_notify_url(varchar(255))\n" +
//                "return_params(varchar(500))\n" +
//                "ext_map(varchar(2048))\n" +
//                "request_version(varchar(32))\n" +
//                "out_trade_date(varchar(8))\n" +
//                "out_trade_time(varchar(8))\n" +
//                "out_referer_hosts(varchar(500))\n" +
//                "retry_pay_count(int(11))\n" +
//                "refunded_amount(bigint(19))\n" +
//                "original_trade_no(varchar(32))\n" +
//                "compensate_status(varchar(8))\n" +
//                "created_date(datetime(19))\n" +
//                "modified_date(datetime(19))\n" +
//                "remote_ip(varchar(128))\n" +
//                "out_trade_ip(varchar(128))\n" +
//                "out_trade_port(varchar(128))\n" +
//                "auth_code(varchar(32))\n" +
//                "agent_merchant_no(varchar(32))\n" +
//                "shop_id(varchar(64))\n" +
//                "user_id(varchar(64))\n" +
//                "terminal_id(varchar(64))\n" +
//                "out_shop_id(varchar(64))\n" +
//                "MODIFIED_TIME(timestamp(19))\n" +
//                "merchant_no(merchant_no,out_trade_no)\n" +
//                "PRIMARY(trade_no)";
        //order_info
//        String strFild =
//                "id(bigint(19))\n" +
//                "version(bigint(19))\n" +
//                "pay_way(varchar(32))\n" +
//                "customer_num(varchar(32))\n" +
//                "shop_num(varchar(32))\n" +
//                "num(varchar(32))\n" +
//                "request_num(varchar(32))\n" +
//                "member_num(varchar(32))\n" +
//                "source(varchar(32))\n" +
//                "bussiness_type(varchar(32))\n" +
//                "type(varchar(32))\n" +
//                "status(varchar(32))\n" +
//                "complete_time(datetime(19))\n" +
//                "amount(decimal(15))\n" +
//                "refund_time(datetime(19))\n" +
//                "machine_num(varchar(32))\n" +
//                "receipt_time(datetime(19))\n" +
//                "receipt_count(int(11))\n" +
//                "receipt_status(smallint(5))\n" +
//                "deleted(smallint(5))\n" +
//                "deleted_time(datetime(19))\n" +
//                "gmt_create(datetime(19))\n" +
//                "gmt_modify(datetime(19))\n" +
//                "user_num(varchar(32))\n" +
//                "ledger_status(varchar(10))\n" +
//                "sub_order_type(varchar(10))\n" +
//                "reserve_info(varchar(256))\n" +
//                "key_pair_num(varchar(32))\n" +
//                "callback_url(varchar(256))\n" +
//                "complete_url(varchar(256))\n" +
//                "pay_model(varchar(32))\n" +
//                "order_business_one(varchar(512))\n" +
//                "order_business_two(varchar(512))\n" +
//                "order_business_three(varchar(1024))\n" +
//                "batch_num(varchar(32))\n" +
//                "refunded_amount(decimal(15))\n" +
//                "time_expire(datetime(19))\n" +
//                "error_code(varchar(32))\n" +
//                "error_msg(varchar(64))\n" +
//                "i_order_info_gmt_modify(gmt_modify)\n" +
//                "u_order_info_num(num)\n" +
//                "i_order_info_customer_num(customer_num)\n" +
//                "u_order_info_gmt_create(gmt_create)";

       // pay_extend_info
//        String strFild = "id(bigint(19))\n" +
//                "id(bigint(19))\n" +
//                "version(bigint(19))\n" +
//                "order_num(varchar(32))\n" +
//                "pay_num(varchar(32))\n" +
//                "customer_num(varchar(32))\n" +
//                "shop_num(varchar(32))\n" +
//                "currency(varchar(32))\n" +
//                "bank_type(varchar(32))\n" +
//                "card_type(varchar(32))\n" +
//                "info_detail(varchar(1024))\n" +
//                "bank_request_num(varchar(32))\n" +
//                "bank_batch_num(varchar(32))\n" +
//                "amount(varchar(32))\n" +
//                "deleted(smallint(5))\n" +
//                "deleted_time(datetime(19))\n" +
//                "gmt_create(datetime(19))\n" +
//                "gmt_modify(datetime(19))\n" +
//                "pay_business_one(varchar(32))\n" +
//                "pay_business_two(varchar(1024))\n" +
//                "pay_business_three(varchar(64))\n" +
//                "pay_business_four(varchar(32))\n" +
//                "pay_business_five(int(11))\n";
//        pay_info
//        String strFild = "id(bigint(19))\n" +
//                "id(bigint(19))\n" +
//                "version(bigint(19))\n" +
//                "order_num(varchar(32))\n" +
//                "num(varchar(32))\n" +
//                "batch_num(varchar(32))\n" +
//                "amount(varchar(32))\n" +
//                "customer_num(varchar(32))\n" +
//                "shop_num(varchar(32))\n" +
//                "bank_mark(varchar(32))\n" +
//                "type(varchar(32))\n" +
//                "remark(varchar(32))\n" +
//                "source_type(varchar(32))\n" +
//                "status(varchar(32))\n" +
//                "bank_request_num(varchar(32))\n" +
//                "bank_trade_num(varchar(32))\n" +
//                "auth_code(varchar(32))\n" +
//                "complete_time(datetime(19))\n" +
//                "can_accounted(smallint(5))\n" +
//                "has_accounted(smallint(5))\n" +
//                "member_num(varchar(32))\n" +
//                "pay_way(varchar(32))\n" +
//                "ledger_status(varchar(10))\n" +
//                "ledger_rule_str(varchar(512))\n" +
//                "ledger_rule_result_str(varchar(512))\n" +
//                "deleted(smallint(5))\n" +
//                "deleted_time(datetime(19))\n" +
//                "gmt_create(datetime(19))\n" +
//                "gmt_modify(datetime(19))\n" +
//                "pay_business_one(varchar(64))\n" +
//                "pay_business_two(varchar(64))\n" +
//                "pay_business_three(varchar(64))\n" +
//                "pay_business_four(varchar(64))\n" +
//                "pay_business_five(int(11))\n" +
//                "prepay_id(varchar(64))\n" +
//                "real_pay_amount(varchar(32))\n" +
//                "real_refund_amount(varchar(32))\n" +
//                "pay_source(varchar(32))\n" +
//                "origin_batch_num(varchar(32))\n" +
//                "coupon_amount(decimal(15))\n" +
//                "refunded_amount(decimal(15))\n" +
//                "error_code(varchar(32))\n" +
//                "error_msg(varchar(64))\n" +
//                "bank_error_code(varchar(32))\n" +
//                "bank_error_msg(varchar(64))";

//        String strFild = "pay_request_pi_no(varchar(32))\n" +
//                "pay_request_pi_no(varchar(32))\n" +
//                "trade_no(varchar(32))\n" +
//                "trade_pay_request_no(varchar(32))\n" +
//                "channel_type(varchar(8))\n" +
//                "channel_app_id(varchar(32))\n" +
//                "channel_merchant_no(varchar(32))\n" +
//                "product_type(varchar(16))\n" +
//                "pi_type(varchar(8))\n" +
//                "pi_types(varchar(32))\n" +
//                "currency(varchar(16))\n" +
//                "amount(bigint(19))\n" +
//                "auth_code(varchar(64))\n" +
//                "remark(varchar(128))\n" +
//                "created_date(datetime(19))\n" +
//                "modified_date(datetime(19))\n" +
//                "risk_id(varchar(64))\n" +
//                "trade_type(varchar(32))\n" +
//                "sub_trade_type(varchar(16))\n" +
//                "pay_source(varchar(32))\n" +
//                "refunded_amount(bigint(19))\n" +
//                "original_trade_pay_request_no(varchar(32))\n" +
//                "ext_map(varchar(2048))\n" +
//                "real_channel_app_id(varchar(32))\n" +
//                "channel_agent_merchant_no(varchar(32))\n" +
//                "original_open_id(varchar(64))\n" +
//                "bill_split(varchar(512))\n" +
//                "MODIFIED_TIME(timestamp(19))\n";

//        String strFild = "pay_request_pi_no(varchar(32))\n" +
//                "pay_request_pi_no(varchar(32))\n" +
//                "trade_no(varchar(32))\n" +
//                "trade_pay_request_no(varchar(32))\n" +
//                "pay_status(varchar(16))\n" +
//                "pay_code(varchar(16))\n" +
//                "pay_desc(varchar(255))\n" +
//                "pay_finish_time(datetime(19))\n" +
//                "channel_no(varchar(32))\n" +
//                "created_date(datetime(19))\n" +
//                "modified_date(datetime(19))\n" +
//                "deadline_time(datetime(19))\n" +
//                "bank_date(varchar(17))\n" +
//                "pi_type(varchar(30))\n" +
//                "pi_types(varchar(30))\n" +
//                "bank_return_no(varchar(32))\n" +
//                "ext_map(varchar(500))\n" +
//                "channel_merchant_no(varchar(32))\n" +
//                "open_id(varchar(64))\n" +
//                "bank_type(varchar(64))\n" +
//                "card_type(varchar(32))\n" +
//                "bank_code(varchar(64))\n" +
//                "bank_desc(varchar(128))\n" +
//                "channel_no_seq(varchar(64))\n" +
//                "MODIFIED_TIME(timestamp(19))\n";
//        String strFild = "trade_no(varchar(32))\n" +
//                "trade_pay_request_no(varchar(32))\n" +
//                "pay_status(varchar(16))\n" +
//                "pay_code(varchar(16))\n" +
//                "pay_desc(varchar(255))\n" +
//                "pay_finish_time(datetime(19))\n" +
//                "channel_no(varchar(32))\n" +
//                "compensate_status(varchar(16))\n" +
//                "pi_types(varchar(32))\n" +
//                "update_lock(tinyint(3))\n" +
//                "ext_map(varchar(500))\n" +
//                "created_date(datetime(19))\n" +
//                "modified_date(datetime(19))\n" +
//                "deadline_time(datetime(19))\n" +
//                "merchant_no(varchar(32))\n" +
//                "MODIFIED_TIME(timestamp(19))\n";
        String strFild ="id(bigint(19))\n" +
                "id(bigint(19))\n" +
                "version(bigint(19))\n" +
                "num(varchar(32))\n" +
                "customer_num(varchar(32))\n" +
                "bank_channel_account_num(varchar(32))\n" +
                "pay_num(varchar(32))\n" +
                "clear_type(varchar(32))\n" +
                "settle_type(varchar(32))\n" +
                "bank_channel(varchar(32))\n" +
                "bank_merchat_num(varchar(32))\n" +
                "sub_bank_merchat_num(varchar(32))\n" +
                "key(varchar(128))\n" +
                "public_key(varchar(128))\n" +
                "private_key(varchar(128))\n" +
                "app_id(varchar(32))\n" +
                "sub_app_id(varchar(32))\n" +
                "cert_local_path(varchar(64))\n" +
                "cert_password(varchar(32))\n" +
                "remark(varchar(32))\n" +
                "deleted(smallint(5))\n" +
                "deleted_time(datetime(19))\n" +
                "gmt_create(datetime(19))\n" +
                "gmt_modify(datetime(19))\n" +
                "auth_channel_num(varchar(32))\n" +
                "pay_type(varchar(32))\n" +
                "wap_ver(varchar(32))\n" +
                "pos_id(varchar(32))\n" +
                "branch_id(varchar(32))\n" +
                "tx_code(varchar(32))\n" +
                "sales_fee(varchar(32))\n" +
                "pay_business_one(varchar(32))\n" +
                "pay_business_two(varchar(64))\n" +
                "pay_business_three(varchar(32))\n" +
                "pay_business_four(varchar(32))\n" +
                "pay_business_five(int(11))\n" +
                "u_pay_snapshot_num(num)\n" +
                "IDX_PAY_NUM(pay_num)\n" +
                "idx_pay_snapshot_gmt_modify(gmt_modify)\n" +
                "idx_pay_snapshot_customer_num(customer_num)\n" +
                "PRIMARY(id)\n" +
                "idx_pay_snapshot_gmt_create(gmt_create)";
        String[] split = strFild.split("\n");
        TreeSet<String> set = new TreeSet();
        for (int i = 0;i<split.length;i++){
            set.add(StringUtils.uncapitalize(WordUtils.capitalizeFully(split[i], new char[]{'_'}).replace("_", "")));
        }
        String[] strings = set.toArray(new String[0]);
        for (int i = 0;i<strings.length;i++){
            System.out.println(strings[i]);
        }

    }

    public static void main(String[] args) throws IOException {
//            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\aggre-trade\\aggre-trade-dao\\src\\main\\java\\com\\jd\\jr\\aggre\\trade\\dao\\vo\\tabledata\\TradeBaseDataUnitVo.java");
//            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\dlb_order_center\\order-center-export\\src\\main\\java\\com\\duolabao\\order\\export\\model\\OrderInfoReqModel.java");
//            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\dlb_order_center\\order_center.dao\\src\\main\\java\\com\\duolabao\\order\\dao\\vo\\OrderInfoDO.java");
//            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\dlb_pay_shared\\pay_shared.payment.dal\\src\\main\\java\\com\\duolabao\\pay\\shared\\payment\\dal\\dao\\dataobject\\PayDO.java");
//            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\dlb_pay_shared\\pay_shared.payment.dal\\src\\main\\java\\com\\duolabao\\pay\\shared\\payment\\dal\\dao\\dataobject\\PayExtendDO.java");
//            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\aggre-trade\\aggre-trade-dao\\src\\main\\java\\com\\jd\\jr\\aggre\\trade\\dao\\vo\\tabledata\\TradePayRequestInfoDataUnitVo.java");
//            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\aggre-trade\\aggre-trade-export\\src\\main\\java\\com\\jd\\jr\\aggre\\trade\\export\\model\\TradePayRequestInfoResult.java");
//            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\aggre-trade\\aggre-trade-dao\\src\\main\\java\\com\\jd\\jr\\aggre\\trade\\dao\\vo\\tabledata\\TradeResultDataUnitVo.java");
            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\dlb_pay_shared\\pay_shared.payment.dal\\src\\main\\java\\com\\duolabao\\pay\\shared\\payment\\dal\\dao\\dataobject\\PaySnapshotShardDO.java");
//        sort();
    }
}/**/