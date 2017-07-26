package GUI;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Model.*;

public class Controller {
	public Model model = new Model();
	public ObservableList<Project> oList;
	private Project currentProject = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Project> projectList;

    @FXML
    private SplitPane details;

    @FXML
    private TableView<Data> table;
    
    @FXML
    private TableColumn<Data,String> dateColumn;

    @FXML
    private TableColumn<Data,String> timeColumn;

    @FXML
    private HBox timerButtons;

    @FXML
    private Button startTimer;

    @FXML
    private Button pauseTimer;

    @FXML
    private Button resumeTimer;

    @FXML
    private Button stopTimer;

    @FXML
    private Text logo;

    @FXML
    private HBox topButtons;

    @FXML
    private Button newProject;

    @FXML
    private Button deleteProject;

    @FXML
    private Button export;

    @FXML
    private Label messageBar;
    
    @FXML
    private Label timeLabel;

    @FXML
    void DeleteProjectPressed(ActionEvent event) {
       	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Are you sure you want to Delete this Project?");
    	alert.setContentText("This will delete all data that has not been exported!");
    	ButtonType delete = new ButtonType("Delete");
    	alert.getButtonTypes().set(0,delete);
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == delete){
    		model.deleteProject(projectList.getSelectionModel().getSelectedItem().getName());
    		table.setItems(projectList.getSelectionModel().getSelectedItem().getTableData());
    	}
    }

    @FXML
    void NewProjectPressed(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("New Project");
    	dialog.setHeaderText("Please Enter the Project Name");
    	dialog.setContentText("Project Name: ");

    	// Traditional way to get the response value.
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    model.addProject(result.get());
    	}
    	
    }

    @FXML
    void exportPressed(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Are you sure you want to Export to Excel?");
    	alert.setContentText("This will transfer all time information from the program to your spreadsheet!");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		FileChooser fileChooser = new FileChooser();
    		fileChooser.setTitle("Save file");
    		fileChooser.showSaveDialog(null);
    		
    		Alert alert2 = new Alert(AlertType.INFORMATION);
        	alert2.setTitle("Completed");
        	alert2.setHeaderText(null);
        	alert2.setContentText("Exported to Excel Successfully!");
        	alert2.showAndWait();
    	} else {
    	    System.out.println("Canceled");
    	}
    }

    @FXML
    void pauseTimer(ActionEvent event) {
    	projectList.getSelectionModel().getSelectedItem().pauseTimer();
    	timeLabel.setText("Paused");
    	pauseTimer.setDisable(true);
    	resumeTimer.setDisable(false);
    }

    @FXML
    void resumeTimer(ActionEvent event) {
    	projectList.getSelectionModel().getSelectedItem().resumeTimer();
    	timeLabel.setText("Running");
    	resumeTimer.setDisable(true);
    	pauseTimer.setDisable(false);
    }

    @FXML
    void startTimer(ActionEvent event) {
    	projectList.getSelectionModel().getSelectedItem().startTimer();
    	timeLabel.setText("Running");
    	startTimer.setDisable(true);
    	pauseTimer.setDisable(false);
    	resumeTimer.setDisable(false);
    	stopTimer.setDisable(false);
    }

    @FXML
    void stopTimer(ActionEvent event) {
    	projectList.getSelectionModel().getSelectedItem().stopTimer();
    	timeLabel.setText("Stopped");
    	startTimer.setDisable(false);
    	stopTimer.setDisable(true);
    	pauseTimer.setDisable(true);
    	resumeTimer.setDisable(true);
    }
    
    @FXML
    void listClicked(MouseEvent event){
    	if(currentProject != null && currentProject.isRunning())
    		currentProject.stopTimer();
    	if(deleteProject.isDisabled())
    		deleteProject.setDisable(false);
    	if(startTimer.isDisabled())
    		startTimer.setDisable(false);
    	
    	currentProject = projectList.getSelectionModel().getSelectedItem();
    	table.setItems(currentProject.getTableData());
    }
    
	@FXML
    void initialize() {
        assert projectList != null : "fx:id=\"projectList\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert details != null : "fx:id=\"details\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert timeColumn != null : "fx:id=\"timeColumn\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert timerButtons != null : "fx:id=\"timerButtons\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert startTimer != null : "fx:id=\"startTimer\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert pauseTimer != null : "fx:id=\"pauseTimer\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert resumeTimer != null : "fx:id=\"resumeTimer\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert stopTimer != null : "fx:id=\"stopTimer\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert logo != null : "fx:id=\"logo\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert topButtons != null : "fx:id=\"topButtons\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert newProject != null : "fx:id=\"newProject\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert deleteProject != null : "fx:id=\"deleteProject\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert export != null : "fx:id=\"export\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert messageBar != null : "fx:id=\"messageBar\" was not injected: check your FXML file 'FXGUI.fxml'.";
        assert timeLabel != null : "fx:id=\"timeLabel\" was not injected: check your FXML file 'FXGUI.fxml'.";
        
        //projectList.getItems().addAll(model.getProjects());
        //FXCollections.observableArrayList(model.getProjects());
        projectList.setItems(model.getProjects());
    	dateColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("date"));
    	timeColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("time"));
        startTimer.setDisable(true);
        deleteProject.setDisable(true);
    }
}
