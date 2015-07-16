package com.mzy.moban.model;

import com.mzy.moban.util.FormatUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 应用 po
 * 
 * @author zhuwei
 * 
 */
public class App extends BaseModel {

	private static final long serialVersionUID = 1L;

	/* app name */
	private String name;
	/* app logo */
	private String logo;
	/* app 运行系统 */
	private String os;
	/* app 版本号 */
	private String version;
	/*应用标识*/
	private String tag;
	/*Android应用包名*/
	private String packName="";
	/*IOS应用包名*/
	private String packName_ios="";
	/* 创建者 */
	private String creator;
	/* 项目创建日期 */
	private Date createTime;
	/* 上次修改时间 */
	private Date lastModifyTime;
	/* 安装app 用户总数 */
	private int userCount;
	/* 月活跃量 */
	private int monthActiveUser;
	/* 推送信息总量 */
	private int pushCount;
	/* 月推送信息总量 */
	private int monthPushCount;
	
	/*唯一标识*/
	private String appKey;
	
	/*secret*/
	private String appMasterSecret;
	
	/*ios生产证书文件路径*/
	private String certificateFile;
	/*ios生产证书密码*/
	private String certificatePassword;
	
	private String departApprover;
    private String appApprover;

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof App))
			return false;
		App another = (App)o;
		return another.getId() == this.getId();
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getMonthActiveUser() {
		return monthActiveUser;
	}

	public void setMonthActiveUser(int monthActiveUser) {
		this.monthActiveUser = monthActiveUser;
	}

	public int getPushCount() {
		return pushCount;
	}

	public void setPushCount(int pushCount) {
		this.pushCount = pushCount;
	}

	public int getMonthPushCount() {
		return monthPushCount;
	}

	public void setMonthPushCount(int monthPushCount) {
		this.monthPushCount = monthPushCount;
	}
	
	
	// for freemaker use 
	
	
	/**
	 * 创建时间的字符串格式
	 * @return
	 */
	public String getCreateTimeStr() {
		return FormatUtils.formatDateTime(createTime);
	}
	
	
	/**
	 * 上次修改日期的字符串格式
	 * @return
	 */
	public String getLastModifyTimeStr() {
		return lastModifyTime == null ? "" :  FormatUtils.formatDateTime(lastModifyTime);
	}
	
	
	/**
	 * 操作系统字符串格式
	 * @return
	 */
	public String getOsStr() {
		return OS.parse(Integer.valueOf(os)).toString();
	}
	
	
	public String getTag() {
		return tag;
	}
	
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getPackName() {
		return packName;
	}
	
	public void setPackName(String packName) {
		this.packName = packName;
	}
	
	
	public String getAppKey() {
		return appKey;
	}
	
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	public String getAppMasterSecret() {
		return appMasterSecret;
	}
	
	public void setAppMasterSecret(String appMasterSecret) {
		this.appMasterSecret = appMasterSecret;
	}

	public String getPackName_ios() {
		return packName_ios;
	}

	public void setPackName_ios(String packName_ios) {
		this.packName_ios = packName_ios;
	}

	public String getCertificateFile() {
		return certificateFile;
	}

	public void setCertificateFile(String certificateFile) {
		this.certificateFile = certificateFile;
	}

	public String getCertificatePassword() {
		return certificatePassword;
	}

	public void setCertificatePassword(String certificatePassword) {
		this.certificatePassword = certificatePassword;
	}


    public String getDepartApprover() {
        return departApprover;
    }

    public void setDepartApprover(String departApprover) {
        this.departApprover = departApprover;
    }

    public String getAppApprover() {
        return appApprover;
    }

    public void setAppApprover(String appApprover) {
        this.appApprover = appApprover;
    }
}
