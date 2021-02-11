package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ClassRoom;


public class Main extends Application{
	
	private ClassRoomGUI classRoomGUI;
	private ClassRoom classRoom;
    
    
    public Main () {
    	classRoom =new ClassRoom();
    	classRoomGUI= new ClassRoomGUI(classRoom);
    }
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlloader.setController(classRoomGUI);
        Parent root = fxmlloader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ClassRoom");
        primaryStage.show();

    }
}