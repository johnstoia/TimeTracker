//Project class file
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;


public class Project {
  private static String name = "NameHolder";
  private String color;
  private static String previousTime = "00:00:00.00";
  private static String newTime = "00:00:00.00";
  private TimeTracker timer = new TimeTracker();

  public Project(String name){
	  Project.name = name;
	  this.color = "Red";
  }
  
  public static void main(String[] args) throws InterruptedException{
	  Project test = new Project("Test3");
	  test.startTimer();
	  TimeUnit.SECONDS.sleep(2);
	  test.stopTimer();
	  System.out.println("Current newTime: "+newTime);
	  test.readTime();
	  test.writeTime();
	  
	  test.startTimer();
	  TimeUnit.SECONDS.sleep(1);
	  test.stopTimer();
	  System.out.println("Current newTime: "+newTime);
	  test.readTime();
	  test.writeTime();
	  
	  
	  System.out.println("Export String: "+exportString());
  }
  
  //Starts the timer for this project
  public void startTimer(){
	  getTimer().startTimer();
  }
  
  public void pauseTimer(){
	  getTimer().stopTimer();
  }
  //Stops the Timer and stores the time
  public void stopTimer(){
	  getTimer().stopTimer();
	  setNewTime(timer.toString());
	  getTimer().resetTimer();
  }

    public void writeTime(){
	  try{
		  FileWriter fw = new FileWriter(getName()+".dat");
		  DecimalFormat df2 = new DecimalFormat(".##");
		  //Add old time to new time
		  String[] split1 = getPreviousTime().split(":");
		  String[] split2 = getNewTime().split(":");
		  int hours = Integer.parseInt(split1[0])+Integer.parseInt(split2[0]);
		  int minutes = Integer.parseInt(split1[1])+Integer.parseInt(split2[1]);
		  double seconds = Double.parseDouble(split1[2])+Double.parseDouble(split2[2]);
		  
		  if(seconds >= 60){
			  int add = (int)seconds/60;
			  seconds = (seconds-(60*add));
			  minutes += add;
		  }
		  if(minutes >= 60){
			  int add2 = minutes/60;
			  minutes = (minutes-(60*add2));
			  hours += add2;
		  }
		  
		  fw.write(hours+":"+minutes+":"+df2.format(seconds));
		  fw.close();
	  }catch(Exception e){
		  e.printStackTrace();
	
	  }
    }
    
    public void readTime(){
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader(getName()+".dat"));
    		String stime = reader.readLine();
    		if(stime != null)
    			setPreviousTime(stime);
    		else
    			setPreviousTime("00:00:00.00");
    		reader.close();
    	}catch(FileNotFoundException e){
    		setPreviousTime("00:00:00.00");
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	
    }
    
    public static String exportString(){
    	String ret = "00:00:00.00";
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader(getName()+".dat"));
    		ret = reader.readLine();
    	}catch(FileNotFoundException e){
    		e.printStackTrace();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	//reset file
    	try{
  		  FileWriter fw = new FileWriter(getName()+".dat");
  		  fw.write("00:00:00.00");
  		  fw.close();
  	  }catch(Exception e){
  		  e.printStackTrace();
  	
  	  }
    	return ret;
    }

	public static String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static String getPreviousTime() {
		return previousTime;
	}

	public void setPreviousTime(String time) {
		this.previousTime = time;
	}

	public TimeTracker getTimer() {
		return timer;
	}

	public void setTimer(TimeTracker timer) {
		this.timer = timer;
	}

	public static String getNewTime() {
		return newTime;
	}

	public static void setNewTime(String newTime) {
		Project.newTime = newTime;
	}
}
