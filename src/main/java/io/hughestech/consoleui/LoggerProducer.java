package io.hughestech.consoleui;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Singleton
public class LoggerProducer {

	/**
	 * @param injectionPoint
	 * @return logger
	 */


	@Produces
	public Logger producer(InjectionPoint ip) {
		System.out.println("preparing logger");
	    return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	}
}
