package uk.ac.ox.it;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mysql.jdbc.log.Log;

/**
 * Log4J Logger for MySQL.
 * Configure this with a connection parameter of:
 * <code>logger=uk.ac.ox.it.Log4JLogger</code>.
 *
 * @author Matthew Buckett
 */
public class Log4JLogger implements Log {

	private Logger log;

	public Log4JLogger(String logger) {
		log = Logger.getLogger(logger);
	}

	public boolean isDebugEnabled() {
		return log.isEnabledFor(Level.DEBUG);
	}

	public boolean isErrorEnabled() {
		return log.isEnabledFor(Level.ERROR);
	}

	public boolean isFatalEnabled() {
		return log.isEnabledFor(Level.FATAL);
	}

	public boolean isInfoEnabled() {
		return log.isEnabledFor(Level.INFO);
	}

	public boolean isTraceEnabled() {
		return log.isEnabledFor(Level.TRACE);
	}

	public boolean isWarnEnabled() {
		return log.isEnabledFor(Level.WARN);

	}

	public void logDebug(Object arg0) {
		log.debug(arg0);
	}

	public void logDebug(Object arg0, Throwable arg1) {
		log.debug(arg0, arg1);
	}

	public void logError(Object arg0) {
		log.error(arg0);
	}

	public void logError(Object arg0, Throwable arg1) {
		log.error(arg0, arg1);
	}

	public void logFatal(Object arg0) {
		log.fatal(arg0);
	}

	public void logFatal(Object arg0, Throwable arg1) {
		log.fatal(arg0, arg1);
	}

	public void logInfo(Object arg0) {
		log.info(arg0);
	}

	public void logInfo(Object arg0, Throwable arg1) {
		log.info(arg0, arg1);
	}

	public void logTrace(Object arg0) {
		log.trace(arg0);
	}

	public void logTrace(Object arg0, Throwable arg1) {
		log.trace(arg0, arg1);
	}

	public void logWarn(Object arg0) {
		log.warn(arg0);
	}

	public void logWarn(Object arg0, Throwable arg1) {
		log.warn(arg0,  arg1);
	}

}
