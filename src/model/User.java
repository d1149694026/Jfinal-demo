package model;

import java.io.Serializable;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> implements Serializable{
	
	public static final User user = new User();
	

}
