package uk.ac.ox.it;

import static com.mysql.jdbc.profiler.ProfilerEvent.TYPE_QUERY;
import static com.mysql.jdbc.profiler.ProfilerEvent.TYPE_SLOW_QUERY;

import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.profiler.ProfilerEvent;
import com.mysql.jdbc.profiler.ProfilerEventHandler;

/**
 * By default you get both the statement executions logged and the fetches of
 * the result sets. This means if you're only logging some of the statements you
 * can end up having the fetches logged which probably isn't very useful. Use
 * this by adding:
 * <code>profilerEventHandler=uk.ac.ox.it.StatementLogging</code> in your
 * connection URL.
 * <p>
 * You can configure what events you want to see with with the properties on
 * the connection URL or properties passed when connecting:
 * <dl>
 * <dt>logQueries</dt>
 * <dd>If <code>true</code> then all queries will be passed to the logging system
 * at info level. The default is <code>true</code>.</dd>
 * <dt>logSlowQueries</dt>
 * <dd>If <code>true</code> then all slow queries will be passed to the logging
 * system at warn level. The default is <code>true</code>.</dd>
 * </dl>
 *
 * @author Matthew Buckett
 */
public class StatementLogging implements ProfilerEventHandler {
	private Log log;
	private boolean logQueries;
	private boolean logSlowQueries;

	public StatementLogging() {
	}

	public void consumeEvent(ProfilerEvent evt) {
		switch (evt.getEventType()) {
			case TYPE_QUERY:
				if (logQueries) {
					log.logInfo(evt);
				}
				break;
			case TYPE_SLOW_QUERY:
				// We log slow queries as warning so we can filter them using
				// the logging framework.
				if (logSlowQueries) {
					log.logWarn(evt);
				}
				break;
		}
	}

	public void destroy() {
		this.log = null;
	}

	public void init(Connection conn, Properties props) throws SQLException {
		this.log = conn.getLog();
		logQueries = Boolean.valueOf(props.getProperty("logQueries", "true"));
		logSlowQueries = Boolean.valueOf(props.getProperty("logSlowQueries", "true"));
	}

}