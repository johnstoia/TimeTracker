//Project class file
package Model;
import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
  private ObservableList<Data> tableData = FXCollections.observableArrayList();
  private boolean isRunning = false;
  //Project Constructor
  public Project(String name){
	  this.name = name;
	  try {
		readTime();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  /*public static void main(String[] args) throws InterruptedException, IOException{
	  Project test = new Project("Test");
	  test.startTimer();
	  TimeUnit.SECONDS.sleep(2);
	  test.stopTimer();
	 // System.out.println("Current newTime: "+newTime);
	  test.writeTime();
	  
	  test.startTimer();
	  TimeUnit.SECONDS.sleep(1);
	  test.stopTimer();
	  //System.out.println("Current newTime: "+newTime);
	  test.writeTime();
	  
	  
	  //System.out.println("Export String: "+exportString());
  }*/
  
  //Starts the timer for this project
  public void startTimer(){
	  getTimer().startTimer();
	  isRunning = true;
  }
  
  //Pauses the timer, can be resumed by calling resumeTimer()
  public void pauseTimer(){
	  getTimer().suspendTimer();
	  isRunning = true;
  }
  
  //Resumes the timer
  public void resumeTimer(){
	  getTimer().resumeTimer();
	  isRunning = true;
  }
  //Stops the Timer and stores the time
  public void stopTimer(){
	  getTimer().stopTimer();
	  isRunning = false;
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
		  tableData.add(new Data(date,getNewTime().substring(0,getNewTime().length()-1)));
		  fw.write(date+" "+getNewTime().substring(0,getNewTime().length()-1)+"\n");
		  fw.close();
	  }catch(Exception e){
		  e.printStackTrace();
	
	  }
    }
    
    //Reads the current time already spent from the dat file
    public void readTime() throws IOException{
    	File file = new File(name+".dat");
		String line;
	    BufferedReader reader;
		reader = new BufferedReader(new FileReader(file));

	    while ((line = reader.readLine()) != null){
	    	String[] parts = line.split(" ");
	    	tableData.add(new Data(parts[0],parts[1]));
	    }
	    reader.close();
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
    
    public boolean isRunning(){
		return isRunning;
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
	
	public ObservableList<Data> getTableData(){
		return this.tableData;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
