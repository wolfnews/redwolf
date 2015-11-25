package com.hoteam.wolf.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.hoteam.wolf.utils.DESUtil;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static final String SYSTEM_KEY="DMWSQWHLS";
	private static Logger logger = Logger.getLogger(EncryptPropertyPlaceholderConfigurer.class);
	private static String[] encryptPropNames ={
			"db.username",
			"db.password",
			"db.url",
			"pay.partner",
			"pay.key",
			"auth.key",
			"api.key",
			"secret.key"};
	@Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            String decryptValue=null;
			try {
				decryptValue = DESUtil.decrypt(propertyValue, SYSTEM_KEY);
			} catch (Exception e) {
				decryptValue = propertyValue;
				logger.error("ERROR:",e);
			}
            return decryptValue;
        } else {
            return propertyValue;
        }
    }
 
    /**
     * 判断是否是加密的属性
     * 
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName) {
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName))
                return true;
        }
        return false;
    }
}
