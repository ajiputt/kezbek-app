package com.telkomsel.kezbek.common.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

import com.telkomsel.kezbek.common.exception.JoinPointException;

public abstract class CommonInterceptor {
	
	static final long MAX_BYTES_LOG_OUTPUT = 25000;

    static final String LINE_SEPARATOR = "line.separator";
	
	public abstract Object aroundAdvise(ProceedingJoinPoint jp) throws JoinPointException;
    
}
