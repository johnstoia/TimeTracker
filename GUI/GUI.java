package GUI;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class GUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
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
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);

 Scene scene = new Scene(root, 100, 100);

        primaryStage.setTitle("T!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
}