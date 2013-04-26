package uk.ac.ox.it;

import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.profiler.ProfilerEvent;
import com.mysql.jdbc.profiler.ProfilerEventHandler;

/**
 * By default you get both the statement executions logged and the
 * fetches of the result sets. This means if you're only logging some
 * of the statements you can end up having the fetches logged which probably
 * isn't very useful. Use this by adding:
 * <code>profilerEventHandler=uk.ac.ox.it.StatementLogging</code>
 * in your connection URL.
 *
 * @author Matthew Buckett
 */
public class StatementLogging implements ProfilerEventHandler {
	private Log log;

	public StatementLogging() {}

	public void consumeEvent(ProfilerEvent evt) {
		if (evt.getEventType() == ProfilerEvent.TYPE_QUERY) {
			log.logInfo(evt);
		}
	}

	public void destroy() {
		this.log = null;
	}

	public void init(Connection conn, Properties props) throws SQLException {
		this.log = conn.getLog();
	}

}