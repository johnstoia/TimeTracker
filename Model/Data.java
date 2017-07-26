package Model;

public class Data{
	  private String date;
	  private String time;
	  
	  public Data(String date,String time){
		  this.date = date;
		  this.time = time;
	  }
	  
	  public String getTime(){
		  return this.time;
	  }
	  
	  public String getDate(){
		  return this.date;
	  }
}