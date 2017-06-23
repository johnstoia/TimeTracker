import GUI.GUI;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Controller {
	static List<String> projectNames = new ArrayList<String>();
	static List<Project> projects = new ArrayList<Project>();
	
	public Controller(){
		FillProjects();
	}
	public static void main(String[] args) throws InterruptedException{
		FillProjects();
		
	}
	
	//Fill the ArrayList with all current projects stored on the HDD
	public static void FillProjects(){
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
	public static void addProject(String name){
		File file = new File(name+".dat");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Project Already Exists!");
			return;
		}
		projects.add(new Project(name));
	}
	
	//Delete the project from the ArrayList and delete its corresponding dat file
	public static void deleteProject(String name){
		for(int i = 0; i < projects.size(); i++){
			if(projects.get(i).getName() == name){
				try{
			  		  File del = new File(name+".dat");
			  		  del.delete();
			  	  }catch(Exception e){
			  		  e.printStackTrace();
			  	
			  	  }
				projects.remove(i);
				break;
			}
				
		}
	}
}
