import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;

public class TimeTracker {
	private long startTime = System.nanoTime();
	private static StopWatch stopwatch = new StopWatch();
	
	/*public static void main(String[] args) throws InterruptedException{
		long totalTime = 0;
		startTimer();
		System.out.println("Started Timer: "+getStopwatch().toString());
		System.out.println("Running for 3 seconds");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Stopping Timer");
		stopTimer();
		System.out.println("End Time: "+getStopwatch().toString()+"\nSaving to Total Time");
		totalTime += 3.006;
		getStopwatch().reset();
		System.out.println("Total Time: "+totalTime);
		System.out.println("Started Timer: "+getStopwatch().toString());
		System.out.println("Running for 3 seconds");
		startTimer();
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Stopping Timer");
		stopTimer();
		System.out.println("End Time: "+getStopwatch().toString()+"\nSaving to Total Time");
		totalTime += 3.006;
		System.out.println("Total Time: "+totalTime);
		getStopwatch().reset();
	}*/
	
	//Starts the timer
	public static void startTimer(){
		getStopwatch().start();
	}
	
	//Stops the timer, stores the total time, resets it
	public static void stopTimer(){
		getStopwatch().stop();
		getStopwatch().reset();
	}

	//Returns a String of the total time in 00:00:00.00 Format
	public String totalTime(){
		return getStopwatch().toString();
	}
	
	//Returns the time the program was started in NanoSeconds
	public long getStartTime() {
		return startTime;
	}

	//StopWatch getter
	public static StopWatch getStopwatch() {
		return stopwatch;
	}

}
