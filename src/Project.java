//Project class file
import java.io.*;


public class Project {
  private static String name = "NameHolder";
  private String color;
  private static long time = 0;
  private TimeTracker timer = new TimeTracker();

  public Project(String name){
	  Project.name = name;
	  Project.time = 0;
	  this.color = "Red";
  }

    public void writeTime(){
	  try{
		  FileWriter fw = new FileWriter(getName()+".txt"); 
		  fw.write(""+getTime());
		  fw.close();
	  }catch(Exception e){
		  e.printStackTrace();
	
	  }
    }
    
    public void readTime(){
    	try{
    		BufferedReader reader = new BufferedReader(new FileReader(getName()+".txt"));
    		long num = Long.parseLong(reader.readLine());
    		reader.close();
    	}catch(FileNotFoundException e){
    		setTime(0);
    		writeTime();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	
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

	public static long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public TimeTracker getTimer() {
		return timer;
	}

	public void setTimer(TimeTracker timer) {
		this.timer = timer;
	}
}
