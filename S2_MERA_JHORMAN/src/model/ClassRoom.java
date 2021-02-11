package model;

import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
	
	private List<UserAccount> Accounts;
	
	public ClassRoom() {
		Accounts=new ArrayList<>();
	}
	
	public void addUserAccount() {		
		Accounts.add(new UserAccount());
	}
	
	public List<UserAccount> getAccounts(){
		return Accounts;
	}
}