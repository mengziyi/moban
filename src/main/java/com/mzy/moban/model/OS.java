package com.mzy.moban.model;

/**
 * 应用平台系统类型
 * @author zhuwei
 *
 */
public enum OS {
	
	IOS("IOS",1),
	Android("Android",2);
	
	
	private String name;
	private int val;
	
	/**
	 * 
	 * @param name
	 * @param val
	 */
	private OS(String name, int val) {
		this.name = name;
		this.val = val;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return name;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getVal() {
		return val;
	}
	
	
	
	/**
	 * 
	 * @param val
	 * @return
	 */
	public static OS parse(int val) {
		if(val == 1)
			return IOS;
		if(val == 2)
			return Android;
		return null;
	}
}
