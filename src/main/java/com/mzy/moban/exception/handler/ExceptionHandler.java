package com.mzy.moban.exception.handler;

import com.mzy.moban.exception.ServiceException;
import com.mzy.moban.exception.config.ErrorConfigInstance;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * handle all Exceptions
 * @author fangyi
 *
 */

public class ExceptionHandler {
	
	private static Logger logger = Logger.getLogger(ExceptionHandler.class);
	
	@SuppressWarnings("rawtypes")
	public Object doAround(ProceedingJoinPoint pjp){
		System.out.println("start");
		Object retVal = null;
		try {
			retVal = pjp.proceed();
		} catch (Throwable e) {
			logger.error("error occurred." + e.getMessage());
			ErrorConfigInstance errorConfigInstance = null;
			String returnJson = null;
			try {
				errorConfigInstance = ErrorConfigInstance.getInstance();

				 if(e instanceof ServiceException){
					ServiceException serviceException = (ServiceException)e;
					String info = errorConfigInstance.getErrorMessage(serviceException.getErrCode(), serviceException.getParameters());
					returnJson = String.format("{'code':%s, 'info':'%s'}", serviceException.getErrCode(), info);
					logger.error(returnJson,e);
				}
				else if(e instanceof IOException){
					ServiceException serviceException = (ServiceException)e;
					logger.error(e.getMessage(),e);
				}
				else{
					logger.error("exception",e);
					e.printStackTrace();
					returnJson = String.format("{'code':%s, 'info':'%s'}", "other exception", e.getMessage());
				}
				//异常处理扩展点,可添加else if 来处理各种不同类型的异常
				
			} catch (IOException e1) {
				logger.error("error occurred in loading error.properties." + e1.getMessage());
				returnJson = "{'code':101, 'info':'error occured when loading error.properties'}";
			}

            //debug info
            System.out.println("error info : " + returnJson);
            e.printStackTrace();

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Type", "application/json; charset=utf-8");
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("success", false);
			resultMap.put("msg", returnJson);
			retVal= new ResponseEntity<Map>(resultMap,httpHeaders, HttpStatus.EXPECTATION_FAILED);

		}
		System.out.println("end");
		return retVal;
	}
}