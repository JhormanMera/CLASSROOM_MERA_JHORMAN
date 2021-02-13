package model;


public class UserAccount {
	
	private String username;
	private String password;
	private String gender;
	private String career;
	private String birthday;
	private String browser;
	private String photo;
	
	public UserAccount(String user, String pass, String photo, String gender, String career, String birth, String browser) {
		this.username=user;
		this.password=pass;
		this.photo = photo;
		this.gender=gender;
		this.career=career;
		this.birthday=birth;
		this.browser=browser;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getGender() {
		return gender;
	}
	public String getCareer() {
		return career;
	}
	public String getBirthday() {
		return birthday;
	}
	public String getBrowser() {
		return browser;
	}
	public String getPhoto() {
		return photo;
	}
	
	

}
