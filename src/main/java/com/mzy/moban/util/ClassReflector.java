/**
 * 
 */
package com.mzy.moban.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:类的反射 </p>
 * <p>Description: </p>
 * <p>Company: ifeng.com</p>
 * @author :chengds
 * @version 1.0
 * <p>--------------------------------------------------------------------</p>
 * <p>date                   author                    reason             </p>
 * <p>2013-06-04             chengds               create the class      </p>
 * <p>--------------------------------------------------------------------</p>
 */

public class ClassReflector {
	/**
	 * @param model
	 * @author chengds
	 * @return 实体类对象序列化成为的Map
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> ReflectModel2Map(Object model) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String, Object> modelMap = new HashMap<String, Object>();
        
		Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组  
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
            String propertyName = field[j].getName();    //获取属性的名字
                
            String name = propertyName.substring(0,1).toUpperCase()+propertyName.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = field[j].getGenericType().toString();    //获取属性的类型
            if(type.equals("class java.lang.String")){   //如果type是类类型，则前面包含"class "，后面跟类名
                Method m = model.getClass().getMethod("get"+name);
                String value = (String) m.invoke(model);    //调用getter方法获取属性值
                if(value != null){
                	modelMap.put(propertyName,value);
                }
            }
            if(type.equals("class java.lang.Integer")){     
                Method m = model.getClass().getMethod("get"+name);
                Integer value = (Integer) m.invoke(model);
                if(value != null){
                	modelMap.put(propertyName,value);
                }
            }
            if(type.equals("class java.lang.Long")){     
                Method m = model.getClass().getMethod("get"+name);
                Long value = (Long) m.invoke(model);
                if(value != null){
                	modelMap.put(propertyName,value);
                }
            }
            if(type.equals("class java.lang.Short")){     
                Method m = model.getClass().getMethod("get"+name);
                Short value = (Short) m.invoke(model);
                if(value != null){
                	modelMap.put(propertyName,value);
            	}
            }       
            if(type.equals("class java.lang.Double")){     
                Method m = model.getClass().getMethod("get"+name);
                Double value = (Double) m.invoke(model);
                if(value != null){                    
                	modelMap.put(propertyName,value);  
                }
            }                  
            if(type.equals("class java.lang.Boolean")){
                Method m = model.getClass().getMethod("get"+name);    
                Boolean value = (Boolean) m.invoke(model);
                if(value != null){                      
                	modelMap.put(propertyName,value);
                }
            }
            if(type.equals("class java.util.Date")){
                Method m = model.getClass().getMethod("get"+name);                    
                Date value = (Date) m.invoke(model);
                if(value != null){
                	modelMap.put(propertyName,FormatUtils.formatDateTime(value));
                }
            }  
            if(type.equals("class java.util.Map")){
                Method m = model.getClass().getMethod("get"+name);                    
				Map value = (Map) m.invoke(model);
                if(value != null){
                	modelMap.put(propertyName,value);
                }
            }
            if(type.equals("class java.util.List")){
                Method m = model.getClass().getMethod("get"+name);                    
                List value = (List) m.invoke(model);
                if(value != null){
                	modelMap.put(propertyName,value);
                }
            }
        }
		return modelMap;
    }
}
