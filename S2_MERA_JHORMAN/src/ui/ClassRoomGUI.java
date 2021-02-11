package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.ClassRoom;
import model.UserAccount;


public class ClassRoomGUI {

	private ClassRoom classRoom;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Pane mainPane;

	@FXML
	private TableView<UserAccount> showAccounts;

	@FXML
	private ImageView showProfileImage;

	@FXML
	private Label UsernameAccount;

	@FXML
	private TableColumn<UserAccount,String> userNameColumn;

	@FXML
	private TableColumn<UserAccount,String> genderColumn;

	@FXML
	private TableColumn<UserAccount,String> careerColumn;

	@FXML
	private TableColumn<UserAccount,String> birthdayColumn;

	@FXML
	private TableColumn<UserAccount,String> browserColumn;

	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Label showMessage;
	
	@FXML
    private Pane mainPaneTEST;

	@FXML
	public void initialize(){   
		
		
	}

	public ClassRoomGUI(ClassRoom classRoom) {
		this.classRoom=classRoom;
	}
	@FXML
	public void SignUp(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("register.fxml"));
		fxmlloader.setController(this);
		Parent root = fxmlloader.load();
		mainPaneTEST.getChildren().clear();
		mainPaneTEST.getChildren().setAll(root);
	}
	
	@FXML
	public void SignIn(ActionEvent event) throws IOException {     	
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlloader.setController(this);
    	Parent root = fxmlloader.load(); 
    	mainPaneTEST.getChildren().clear();
    	mainPaneTEST.getChildren().setAll(root);
	}
	@FXML
	public void Login(ActionEvent event) throws IOException {		
		if (userVerification(txtUserName.getText(),txtPassword.getText())!=false) {
			initializableTableView();
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("account-list.fxml"));
			fxmlloader.setController(this);
			Parent root = fxmlloader.load(); 
			mainPaneTEST.getChildren().clear();
			mainPaneTEST.getChildren().setAll(root);
		} else {
			// Crear alerta
		}
	}
	private boolean userVerification(String userName, String password) {
		boolean exists = false;
		if (( (UserAccount) classRoom.getAccounts()).getUsername().equals(userName) && ((UserAccount) classRoom.getAccounts()).getPassword().equals(password)) {
			exists = true;
		}
		return exists;
	}
	@FXML
	public void Logout(ActionEvent event) throws IOException {
		SignIn(event);
	}	

	public void initializableTableView() throws IOException{
		ObservableList <UserAccount> accountsArray = FXCollections.observableArrayList(classRoom.getAccounts());
		showAccounts.setItems(accountsArray); 
		userNameColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("name"));
		genderColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("gender"));
		careerColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("career"));	
		birthdayColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("birthday"));	
		browserColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("browser"));	
	}

}
