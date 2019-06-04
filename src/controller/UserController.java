package controller;

import com.jfinal.core.Controller;

public class UserController extends Controller{
	
	public void index(){
		render("form.jsp");
	}
	
	public void hello(){
		renderText("hello Jfinal world.......");
	}
}
