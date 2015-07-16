package com.mzy.moban.mapper;


import com.mzy.moban.model.BaseModel;
import org.apache.ibatis.metadata.Table;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * appInfo 表基本操作
 * @author zhuwei
 *
 * @param <T>
 */
public interface AppMapper<T extends BaseModel> extends BaseMapper<T> {
	
	
	/**
	 * 
	 * @param where
	 * @return
	 */
	public List<T>  getByWhere(String where);
	
	
	/**
	 * 
	 * @param appKey
	 * @return
	 */
	public T getByKey(String appKey);
	
	/**
	 * 根据包名查询数据
	 * @param packName
	 * @return
	 */


    public T getById(Integer id);
}
