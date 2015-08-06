package com.mzy.moban.exception.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * single instance of error properties
 * @author fangyi
 *
 */
public class ErrorConfigInstance {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorConfigInstance.class);
	
	private static ErrorConfigInstance errorConfigInstance = null;
	private static String propPath = "/error.properties";
	private Properties pros = null;
	
	public static synchronized ErrorConfigInstance getInstance() throws IOException {
		if(errorConfigInstance == null){
			errorConfigInstance = new ErrorConfigInstance();
		}
		return errorConfigInstance;
	}
	
	public String getValue(String key){
		return pros.getProperty(key);
	}
	
	private ErrorConfigInstance() throws IOException {
		readConfig();
	}

	private void readConfig() throws IOException {
		pros = new Properties();
		InputStream in = null;
		try {
			in = ErrorConfigInstance.class.getResourceAsStream(propPath);
			pros.load(in);
		} catch (FileNotFoundException e) {
			logger.error("error.properties file not found." + e.getMessage());
			throw e;
		}catch (IOException e) {
			logger.error("io exception in load error.properties." + e.getMessage());
			throw e;
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				logger.error("io exception in closing InputStream." + e.getMessage());
			}
		}
	}
	
	/**
	 * get error messsage by code and fill by parameters
	 * @param errCode
	 * @param parameters
	 * @return
	 */
	public String getErrorMessage(String errCode, String...parameters){
		String msg = pros.getProperty(errCode);
		return MessageFormat.format(msg, parameters);
	}
}
