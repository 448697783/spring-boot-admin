package admin.modules.automatic.entity;

/**
 * 列的属性
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年12月20日 上午12:01:45
 */
public class ColumnEntity {
	//列名
    private String columnName;
    private String characterMaximumLength;
    private String numericPrecision;
    private String isNullable;
    //mybatis列类型
    private String sqlType;
    //列名类型
    private String dataType;
    //列名备注
    private String comments;
    //主键
    private String columnKey;

    //属性名称(第一个字母大写)，如：user_name => UserName
    private String attrName;
    //属性名称(第一个字母小写)，如：user_name => userName
    private String attrname;
    //属性类型
    private String attrType;
    //auto_increment
    private String extra;
    
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getAttrname() {
		return attrname;
	}
	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey =columnKey;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public String getCharacterMaximumLength() {
		return characterMaximumLength;
	}
	public void setCharacterMaximumLength(String characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}
	public String getNumericPrecision() {
		return numericPrecision;
	}
	public void setNumericPrecision(String numericPrecision) {
		this.numericPrecision = numericPrecision;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
}
