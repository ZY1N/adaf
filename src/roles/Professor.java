package roles;

public class Professor extends User {

    
	public Professor(String name, String id, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.userType = "Professor";

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
