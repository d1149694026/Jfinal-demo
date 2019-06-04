package common;

import java.util.List;

import model.User;

public class UserService {
	public List<User> userList(){
		List<User> users=User.user.find("select * from user");
		return users;
	}
}
