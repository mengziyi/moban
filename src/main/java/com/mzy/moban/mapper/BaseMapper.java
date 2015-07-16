package com.mzy.moban.mapper;


import com.mzy.moban.model.BaseModel;

import java.util.List;

/**
 * 数据基本操作接口
 * @author zhuwei
 *
 * @param <T>
 */
public interface BaseMapper<T extends BaseModel> {
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> get(T entity);
	
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	
	public int delete(T entity);
	
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity);
}
