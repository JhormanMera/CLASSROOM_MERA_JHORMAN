package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
	private TableView<UserAccount> showAccountsL;

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
	private TextField txtRegUsername;

	@FXML
	private PasswordField txtRegPassword;

	@FXML
	private TextField txtRegProfilePhoto;

    @FXML
    private CheckBox softwareCareer;

    @FXML
    private CheckBox telematicCareer;

    @FXML
    private CheckBox industrialCareer;

    @FXML
    private RadioButton Male;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private RadioButton Female;

    @FXML
    private RadioButton Other;
    
	@FXML
	private ChoiceBox<String> choiceBrowser;

	@FXML
	private DatePicker DpBirthday;    

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
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(root);
		choiceBrowser.getItems().addAll("Firefox","Chrome","Edge","Safari","Opera","Thor","Brave");
	}

	public void showLogin() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlloader.setController(this);
		Parent root = fxmlloader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(root);
	}

	@FXML
	public void SignIn(ActionEvent event) throws IOException {     	
		showLogin();
	}

	@FXML
	public void Logout(ActionEvent event) throws IOException {
		showLogin();
	}
	
	@FXML
	public void BrowsePhoto(ActionEvent event) {
		FileChooser fc = new FileChooser();		
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images","*.jpg","*.png","*.jpeg"));
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null) {
			txtRegProfilePhoto.setText(selectedFile.getAbsolutePath().toString());
		}else {
			txtRegProfilePhoto.setText("Archivo Invalido");
		}
	}

	@FXML
	public void CreateAnAccount(ActionEvent event) throws IOException {
		String career="";
		var gender1= "";
		if(softwareCareer.isSelected()) {
			career="Software Engineering";
		}else if(telematicCareer.isSelected()) {
			career="Telematic Engineering";
		}else if(industrialCareer.isSelected()) {
			career="Industrial Engineering";
		}
		if(Male.isSelected()) {
			gender1="Male";
		}else if(Female.isSelected()) {
			gender1="Female";
		}else if(Other.isSelected()) {
			gender1="Other";
		}
		if (userVerification(txtRegUsername.getText())==null) { 
			File f = new File(txtRegProfilePhoto.getText());
			Image img = new Image(f.toURI().toString());
			this.classRoom.addUserAccount(
				txtRegUsername.getText(), 
				txtRegPassword.getText(), 
				img, 
				gender1, 
				career,
				DpBirthday.getValue().toString(),
				choiceBrowser.getSelectionModel().getSelectedItem().toString());
				showLogin();
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Error");
			alert.setContentText("El nombre de usuario no está disponible");
			alert.showAndWait();
		}
	}
	@FXML
	public void Login(ActionEvent event) throws IOException {		
		if (loginVerification(txtUserName.getText(),txtPassword.getText())!=null) {			
			UserAccount user = loginVerification(txtUserName.getText(),txtPassword.getText());
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("account-list.fxml"));
			fxmlloader.setController(this);
			Parent root = fxmlloader.load(); 			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(root);
			txtUserName.setText("");
			txtPassword.setText("");
			initializableTableView();			
			this.UsernameAccount.setText(user.getUsername());
			this.showProfileImage.setImage(user.getPhoto());
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Error");
			alert.setContentText("Nombre de usuario y/o contraseña invalido(s)");
			alert.showAndWait();
		}
	}
	
	public UserAccount loginVerification (String userName, String password) {
		UserAccount exists = null;
		String user = userName;	
		String pass = password;
		boolean found = false;
		for (int i=0; i<classRoom.getAccounts().size() && !found;i++) {
			if (classRoom.getAccounts().get(i).getUsername().equals(user) && classRoom.getAccounts().get(i).getPassword().equals(pass)) {
			exists = classRoom.getAccounts().get(i);
			found = true;
			}
		}
		return exists;
	}
	
	public UserAccount userVerification (String userName) {
		UserAccount exists = null;
		String user = userName;	
		boolean found = false;
		for (int i=0; i<classRoom.getAccounts().size() && !found;i++) {
			if (classRoom.getAccounts().get(i).getUsername().equals(user)) {
			exists = classRoom.getAccounts().get(i);
			found = true;
			}
		}
		return exists;
	}
	public void initializableTableView() throws IOException{
		ObservableList<UserAccount> accountsArray = FXCollections.observableArrayList(classRoom.getAccounts());
		userNameColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("username"));
		genderColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("gender"));
		careerColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("career"));	
		birthdayColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("birthday"));
		browserColumn.setCellValueFactory(new PropertyValueFactory <UserAccount,String>("browser"));
		showAccountsL.setItems(accountsArray); 
	}
	 

}
