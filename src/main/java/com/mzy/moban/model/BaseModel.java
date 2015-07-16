package com.mzy.moban.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzy.moban.util.ClassReflector;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 数据基本信息
 * @author Administrator
 *
 */
public class BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	/*记录id*/
	private Integer id;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return 序列化后的Json字符串
	 * @author chengds
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String toJson() throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, JsonProcessingException {
		java.util.Map<String,Object> map = ClassReflector.ReflectModel2Map(this);
		//将父类的属性写入到map
		map.put("id", this.id);//
        return new ObjectMapper().writeValueAsString(map);
    }
	
}
