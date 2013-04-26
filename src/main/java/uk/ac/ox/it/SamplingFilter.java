package uk.ac.ox.it;

import java.util.Random;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * This filter only logs a limited amount of events so that when you have a large
 * number of logs happening. It uses Random so that you don't get set log lines
 * when the pattern is very static. The performance difference between an
 * atomic long and a random is very small.
 *
 * @author Matthew Buckett
 */
public class SamplingFilter extends Filter {

	private Random counter = new Random();
	private int interval = 10;

	/**
	 * The number of events to ignore before logging one again.
	 * The default value is 10.
	 */
	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public int decide(LoggingEvent event) {
		return (counter.nextInt(interval)==0)?NEUTRAL:DENY;
	}

}
