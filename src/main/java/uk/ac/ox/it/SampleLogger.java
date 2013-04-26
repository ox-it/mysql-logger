package uk.ac.ox.it;

import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.profiler.ProfilerEvent;
import com.mysql.jdbc.profiler.ProfilerEventHandler;

/**
 * Simple logger that just logs a sample of all statements.
 *
 * @author Matthew Buckett
 */
public class SampleLogger implements ProfilerEventHandler{

	private Log log;
	private AtomicLong counter = new AtomicLong();
	private int interval = 10;

	public void destroy() {
		this.log = null;
	}

	public void init(Connection conn, Properties props) throws SQLException {
		this.log = conn.getLog();
	}

	public void consumeEvent(ProfilerEvent event) {
		if (event.getEventType() == ProfilerEvent.TYPE_EXECUTE) {
			long current = counter.incrementAndGet();
			if (current % interval == 0) {
				log.logInfo(event);
			}
		}
	}

}
