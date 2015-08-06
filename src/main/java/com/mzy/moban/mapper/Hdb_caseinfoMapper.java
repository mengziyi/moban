package com.mzy.moban.mapper;


import com.mzy.moban.model.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * appInfo 表基本操作
 * @author zhuwei
 *
 * @param <T>
 */
public interface Hdb_caseinfoMapper<T extends BaseModel> extends BaseMapper<T> {
	


	public List<T>  getByPhoneAndLicense(Map m);


}
