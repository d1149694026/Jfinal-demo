package controller;

import java.util.ArrayList;
import java.util.List;

import model.User;
import result.ResponseResult;

import com.jfinal.core.Controller;

public class TableController extends Controller{
	public void index(){
		List<User> users=User.user.find("select * from user");
		setAttr("user", users);
		System.out.println(users);
		renderJsp("table.jsp");
	}
	
	public void list() {
		ResponseResult<List<User>> rs=new ResponseResult<>(200, "");
		List<User> users=new ArrayList<User>();
		users=User.user.find("select * from user");
		rs.setTotal(18);
		rs.setRows(users);
		System.out.println(rs);
		setAttr("res", rs);
		render("layuitable.jsp");
	}
}
