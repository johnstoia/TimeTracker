import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class projectGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	TabPane tp = new TabPane();
    	tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    	
    	Tab project1_tab = new Tab("Project 1");
    	HBox hb = new HBox();
    	Button b1 = new Button("Start");
    	Button b2 = new Button("Stop");
    	hb.getChildren().addAll(b1,b2);
    	hb.setPadding(new Insets(5));
    	project1_tab.setContent(hb);
    	
    	
    	Tab project2_tab = new Tab("Project 2");
    	HBox hb1 = new HBox();
    	Button b3 = new Button("Start");
    	Button b4 = new Button("Stop");
    	hb1.getChildren().addAll(b3,b4);
    	hb1.setPadding(new Insets(5));
    	project2_tab.setContent(hb1);
    	
    	Tab project3_tab = new Tab("Project 3");
    	HBox hb2 = new HBox();
    	Button b5 = new Button("Start");
    	Button b6 = new Button("Stop");
    	hb2.getChildren().addAll(b5,b6);
    	hb2.setPadding(new Insets(5));
    	project3_tab.setContent(hb2);
    	
    	tp.getTabs().addAll(project1_tab, project2_tab,project3_tab);
    	Scene scene = new Scene(tp,400,250);
    	
    	Group root = new Group();
    	
//    	Scene scene = new Scene(root, 400, 250);
    	
/*       Button btn = new Button();
        btn.setText("Export to Excel");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setTitle("Confirmation");
            	alert.setHeaderText("Are you sure you want to Export to Excel?");
            	alert.setContentText("This will transfer all time information from the program to your spreadsheet!");

            	Optional<ButtonType> result = alert.showAndWait();
            	if (result.get() == ButtonType.OK){
            		Alert alert2 = new Alert(AlertType.INFORMATION);
                	alert2.setTitle("Completed");
                	alert2.setHeaderText(null);
                	alert2.setContentText("Exported to Excel Successfully!");
                	alert2.showAndWait();
            	} else {
            	    System.out.println("Canceled");
            	}
            }
        });
        
        //StackPane root = new StackPane();
        root.getChildren().add(btn);
*/



        primaryStage.setTitle("Project Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
}
