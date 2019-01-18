package admin.common.baseenum;
/**
 * @Desc   : <P>TODO<P>
 * @Author : SOHU-changjiwang
 * @Date   : 2016年11月22日 上午11:08:11
 * @Version: V1.0
 */
public enum DataSourceEnum {

	DATASOURCE_MASTER("takeout"),
	DATASOURCE_SLAVE("sohupay");

	private String value;

	private DataSourceEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
