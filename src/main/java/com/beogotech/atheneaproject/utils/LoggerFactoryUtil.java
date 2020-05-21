package com.beogotech.atheneaproject.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerFactoryUtil encapsulates functionalities that keep a log on debug and error levels
 * @author beogotech
 */
public class LoggerFactoryUtil {
	/**
	 * This method provides an instance of Logger for the class that instantiates it
	 * @param clasz instantiator class
	 * @return instance of log
	 */
	@SuppressWarnings("rawtypes")
	public static Logger getLog(Class clasz) {
		Logger classLog;
		classLog = LoggerFactory.getLogger(clasz);
		return classLog;
	}
	
	/**
	 * This method writes a info message to a logger
	 * @param log Descriptor to write the messages
	 * @param methodName method name
	 */
	public static void writeInfoMessage(Logger log, String methodName) {
		if (log.isInfoEnabled() && !"".equals(methodName)) {
			log.info(methodName);
		}
	}
	
	/**
	 * This method traces the access to a method
	 *
	 * @param log Logger instance that writes the traces
	 * @param methodName name of the method that is entered
	 */
	public static void writeEnterMethodMessage(Logger log, String methodName) {
		if (log.isTraceEnabled() && !"".equals(methodName)) {
			log.trace("Entering: " + methodName);
		}
	}
	
	/**
	 * This method trace the exit to a method
	 *
	 * @param log Logger instance that writes the traces
	 * @param methodName name of the method that is exited
	 */
	public static void writeExitMethodMessage(Logger log, String methodName) {
		if (log.isTraceEnabled() && !"".equals(methodName)) {
			log.trace("Exiting: " + methodName);
		}
	}
	
	/**
	 * This method writes a warning message to a logger
	 *
	 * @param log Logger instance that writes the traces
	 * @param message Message to write
	 */
	public static void writeWarningMessage(Logger log, String message) {
		if (!"".equals(message)) {
			log.warn(message);
		}
	}
	
	/**
	 * This method writes a debug message to a logger
	 *
	 * @param log Logger instance that writes the traces
	 * @param message Message to write
	 */
	public static void writeDebugMessage(Logger log, String message) {
		if (log.isDebugEnabled() && !"".equals(message)) {
			log.debug(message);
		}
	}
	
	/**
	 * This method writes an error message to a logger
	 *  @param log Logger instance that writes the traces
	 * @param message Message to write*/
	public static void writeErrorMessage(Logger log, String message) {
		if (!"".equals(message)) {
			log.error(message);
		}
	}
	
	
	/**
	 * This method writes an exception to a logger
	 *
	 * @param log Logger instance that writes the traces
	 * @param ex Exception
	 */
	public static void writeErrorMessage(Logger log, Exception ex) {
		writeErrorMessage(log, " --> " + formatException(ex));
	}
	
	/**
	 * This method writes an error message with an exception to a logger
	 *
	 * @param log Logger instance that writes the traces
	 * @param ex Exception
	 * @param message Message to be written
	 */
	public static void writeErrorMessage(Logger log, Exception ex, String message) {
		writeErrorMessage(log, message + " --> " + formatException(ex));
	}
	
	private static String formatException(Exception ex) {
		StringBuilder message = new StringBuilder();
		message.append("EXCEPTION DETAILS: [MESSAGE] ");
		
		if ((ex.getMessage() != null) && !ex.getMessage().isEmpty()) {
			message.append(ex.getMessage());
		} else {
			message.append("-");
		}
		
		if ((ex.getStackTrace() != null) && (ex.getStackTrace().length != 0)) {
			for (StackTraceElement stack : ex.getStackTrace()) {
				message.append(formatStackTrace(stack));
			}
		}
		return message.toString();
	}
	
	private static String formatStackTrace(StackTraceElement stack) {
		StringBuilder message = new StringBuilder();
		
		message.append("\n\t");
		if ((stack.getClassName() != null) && !stack.getClassName().isEmpty()) {
			message.append(stack.getClassName());
		}
		
		if (stack.getLineNumber() != 0) {
			message.append(":").append(stack.getLineNumber()).append(" ");
		}
		
		if ((stack.getMethodName() != null) && !stack.getMethodName().isEmpty()) {
			message.append(stack.getMethodName()).append(" ");
		}
		return message.toString();
	}
	
}
