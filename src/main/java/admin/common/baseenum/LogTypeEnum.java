package admin.common.baseenum;

public enum LogTypeEnum {
	/**
	 * 标记执行到第几个方法
	 */
	WHICH_NUMBER_KEY ("LOG_TYPE_GLOBAL_WHICH_NUMBER_KEY"),
	/**
	 * 标记请求唯一表示
	 */
	WHICH_MARK_KEY("LOG_TYPE_GLOBAL_WHICH_MARK_KEY");
	private String key;
	private LogTypeEnum(String key) {
		this.key = key;
	}
	public String toString() {
		return key;
	}
	public String getValue() {
		return key;
	}
	
}

