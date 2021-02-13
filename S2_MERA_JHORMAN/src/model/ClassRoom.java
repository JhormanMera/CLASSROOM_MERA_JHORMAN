package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;


public class ClassRoom {
	
	private List<UserAccount> Accounts;
	
	public ClassRoom() {
		Accounts=new ArrayList<>();
	}
	
	public void addUserAccount(String user, String pass, Image photo, String gender, String career, String birth, String browser) {		
		Accounts.add(new UserAccount(user, pass, photo, gender, career, birth, browser));
		
	}
	
	public List <UserAccount> getAccounts(){
		return Accounts;
	}
}