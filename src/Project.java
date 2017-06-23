//Project class file
import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class Project {
  private String name = "NameHolder";
  private String color;
  private String previousTime = "00:00:00.00";
  private String newTime = "00:00:00.00";
  private TimeTracker timer = new TimeTracker();
  private static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
  private String date = sdf.format(new Date());

  //Project Constructor
  public Project(String name){
	  this.name = name;
	  this.color = "Red";
  }
  
  /*public static void main(String[] args) throws InterruptedException, IOException{
	  Project test = new Project("Test");
	  test.startTimer();
	  TimeUnit.SECONDS.sleep(2);
	  test.stopTimer();
	  System.out.println("Current newTime: "+newTime);
	  test.writeTime();
	  
	  test.startTimer();
	  TimeUnit.SECONDS.sleep(1);
	  test.stopTimer();
	  System.out.println("Current newTime: "+newTime);
	  test.writeTime();
	  
	  
	  //System.out.println("Export String: "+exportString());
  }*/
  
  //Starts the timer for this project
  public void startTimer(){
	  getTimer().startTimer();
  }
  
  //Pauses the timer, can be resumed by calling startTimer()
  public void pauseTimer(){
	  getTimer().stopTimer();
  }
  //Stops the Timer and stores the time
  public void stopTimer(){
	  getTimer().stopTimer();
	  setNewTime(timer.toString());
	  getTimer().resetTimer();
	  writeTime();
  }

  //Add's the newTime to the time stored in the dat file already and writes the combo time to the file
  //If file is empty, writes the current time to the file
    public void writeTime(){
	  try{
		  System.out.println("Writing to "+getName()+".dat");
		  FileWriter fw = new FileWriter(getName()+".dat",true);
		  /*DecimalFormat df2 = new DecimalFormat(".##");
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
		  */
		  fw.write(date+" "+getNewTime().substring(0,getNewTime().length()-1)+"\n");
		  fw.close();
	  }catch(Exception e){
		  e.printStackTrace();
	
	  }
    }
    
    //Reads the current time already spent from the dat file
    public void readTime(){
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader(getName()+".dat"));
    		String stime = reader.readLine();
    		stime = stime.substring(9,stime.length());
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
    
    //Exports the time to a string to be formatted and sent to Excel
    //Resets the dat file
    public String exportString(){
    	String ret = "";
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader(getName()+".dat"));
    		String holder;
    		while((holder = reader.readLine()) != null){
    			ret = ret+holder+"\n";
    		}
    		reader.close();
    	}catch(FileNotFoundException e){
    		e.printStackTrace();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	/*//delete file java
     try{
  		  File del = new File(getName()+".dat");
  		  del.delete();
  		  System.out.println("deleted");
  	  }catch(Exception e){
  		  e.printStackTrace();
  	
  	  }
    	*/
    	try{
    	FileWriter fw = new FileWriter(getName()+".dat");
    	fw.write("");
    	fw.close();
    	}catch(FileNotFoundException e){
    		e.printStackTrace();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	return ret;
    }

    /*
     * Setters and Getters for all private values
     */
	public String getName() {
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

	public String getPreviousTime() {
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

	public String getNewTime() {
		return newTime;
	}

	public void setNewTime(String newTime) {
		this.newTime = newTime;
	}
}
