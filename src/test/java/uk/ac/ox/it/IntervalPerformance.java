package uk.ac.ox.it;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import com.google.caliper.SimpleBenchmark;

public class IntervalPerformance extends SimpleBenchmark {

	public int timeRandom(int reps) {
		Random rnd = new Random();
		int dummy = 0;
		for (int i = 0; i < reps; i++) {
			dummy = rnd.nextInt(1000);
		}
		return dummy;
	}

	public long timeAtomicLong(int reps) {
		AtomicLong atomic = new AtomicLong();
		long dummy = 0;
		for (int i = 0; i < reps; i++) {
			dummy = atomic.getAndIncrement();
		}
		return dummy;
	}

//	public int timeThreadRandom(int reps) {
//		int dummy = 0;
//		for (int i = 0; i < reps; i++) {
//			dummy = ThreadLocalRandom.current().nextInt();
//		}
//		return dummy;
//	}

}
