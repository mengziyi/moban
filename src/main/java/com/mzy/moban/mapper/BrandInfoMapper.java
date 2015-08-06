package com.mzy.moban.mapper;


import com.mzy.moban.model.BaseModel;

import java.util.List;

/**
 * appInfo 表基本操作
 * @author zhuwei
 *
 * @param <T>
 */
public interface BrandInfoMapper<T extends BaseModel>{
	/**
	 * 
	 * @param where
	 * @return
	 */
	public List<T>  getByWhere(String where);




    public T getById(Integer id);

	public List<T> get(T a);
}
