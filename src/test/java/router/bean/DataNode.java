package router.bean;

/**

  @author :chenyong
  @version 1.0
  @date 2013-3-12
 */

public class DataNode {
	

	private String tablename;	//hash后得到的表名前缀
    private String database;	//hash后得到的数据库名前缀
    private String keyname;		//hash取得字段名
    private String hashtype;	//hash方法的类型
    private String hashvalue;	//hash的值
    
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public String getHashtype() {
		return hashtype;
	}
	public void setHashtype(String hashtype) {
		this.hashtype = hashtype;
	}
	public String getHashvalue() {
		return hashvalue;
	}
	public void setHashvalue(String hashvalue) {
		this.hashvalue = hashvalue;
	}

}
