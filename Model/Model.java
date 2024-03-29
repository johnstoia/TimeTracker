package Model;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	private ObservableList<String> projectNames = FXCollections.observableArrayList();
	private ObservableList<Project> projects = FXCollections.observableArrayList();
	
	public Model(){
		try {
			FillProjects();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<Project> getProjects(){
		return this.projects;
	}
	
	public ObservableList<String> getProjectNames(){
		return this.projectNames;
	}
	/*public static void main(String[] args) throws InterruptedException, IOException{
		FillProjects();
		exportAll();
		/*String x = "Project 1,06/28/2017,01:10:11.12\nProject 2,06/28/2017,02:22:10.13\n";
		FileWriter fw = new FileWriter("testing.csv",true);
		fw.write(x);
		fw.close();
	}*/
	
	//Fill the ArrayList with all current projects stored on the HDD
	private void FillProjects() throws IOException{
		File[] files = new File(".").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile()) {
		    	if(file.getName().endsWith(".dat")){
		    		System.out.println("Adding: "+file.getName().substring(0,file.getName().length()-4));
		    		projectNames.add(file.getName().substring(0,file.getName().length()-4));
		    	}
		    }
		}
		
		for(String name : projectNames){
			projects.add(new Project(name));
		}
	}
	
	//Add a new project to the Arraylist and create a new dat file for it
	public boolean addProject(String name){
		File file = new File(name+".dat");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Project Already Exists!");
			return false;
		}
		projects.add(new Project(name));
		projectNames.add(name);
		
		return true;
	}
	
	//Delete the project from the ArrayList and delete its corresponding dat file
	public boolean deleteProject(String name){
		for(int i = 0; i < projects.size(); i++){
			if(projects.get(i).getName() == name){
				try{
			  		  File del = new File(name+".dat");
			  		  del.delete();
			  	  }catch(Exception e){
			  		  e.printStackTrace();
			  		  return false;
			  	
			  	  }
				projects.remove(i);
				projectNames.remove(name);
				return true;
			}		
		}
		return false;
	}
	
	//Export's all Project/Timers to csv
	public void exportAll(){
		String CSV = "";
		String date = null;
		String prevdate = null;
		int hours = 0;
		int minutes = 0;
		double seconds = 0;
		for(int i = 0; i < projects.size();i++){
			String proj = projects.get(i).exportString();
			if(proj == null) continue;
			String[] split = proj.split("\n");
			for(int j = 0; j < split.length; j++){
				try{
					date = split[j].substring(0,8);
					if(prevdate == null)
						prevdate = date;
					if(date.equals(prevdate)){
						String time = split[j].substring(9,split[j].length());
				    	String[] split1 = time.split(":");
				    	hours += Integer.parseInt(split1[0]);
				    	minutes += Integer.parseInt(split1[1]);
				    	seconds += Double.parseDouble(split1[2]);
				    	if(j == split.length-1){
				    		CSV += addToExport(hours,minutes,seconds,date,projects.get(i).getName());
				    	}
					}else{
						CSV += addToExport(hours,minutes,seconds,prevdate,projects.get(i).getName());
						prevdate = date;
						String time = split[j].substring(9,split[j].length());
				    	String[] split1 = time.split(":");
				    	hours = Integer.parseInt(split1[0]);
				    	minutes = Integer.parseInt(split1[1]);
				    	seconds = Double.parseDouble(split1[2]);
				    	if(j == split.length-1){
				    		CSV += addToExport(hours,minutes,seconds,date,projects.get(i).getName());
				    	}
					}
				}catch(NumberFormatException e){
					System.out.println("Numbers Formatted Wrong, Moving on.");
				}catch(StringIndexOutOfBoundsException e){
					System.out.println("Index out of bounds, this means it's formatted wrong");
				}
			}
			date = null;
			prevdate = null;
    		hours = 0;
    		minutes = 0;
    		seconds = 0;
		}
		try {
			File test = new File("Timesheet.csv");
			int i = 1;
			while(test.exists()){
				test= new File("Timesheet("+i+").csv");
				i++;
			}
			FileWriter fw = new FileWriter(test);
			fw.write(CSV);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Private Method for Export
	private static String addToExport(int hours, int minutes, double seconds,String date,String name){
		String ret = "";
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
		  
		  ret += name+","+date+","+hours+":"+minutes+":"+seconds+"\n";
		  System.out.print(ret);
		  
		  return ret;
	}
}
