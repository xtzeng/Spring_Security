package com.huaxin.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	
	private SpringUtil() {
		
	}
	
	
	public static Object getBean(String beanName) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		return ac.getBean(beanName);
	}
}
