package com.huaxin.util;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceHelper {
	
	private ResourceBundleMessageSource messageSource;

	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		String msg = messageSource.getMessage(code, args, defaultMessage, locale);
		return msg != null ? msg.trim() : msg;
	}
}
