package roles;

public class Admin extends User {

	//these are defined in User
//	 String type id;
//	 String name;
//	 String username;
//	 String password;
	
	public Admin(String id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.userType = "Admin";
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}

    public String toString() {
        return ("Id:" + id + "\nName:" + name +  "\nUsername:" + username + "\nPassword:" + password);
    }

	public String getName() {
		return this.name;
	}
}
