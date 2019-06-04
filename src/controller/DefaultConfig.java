package controller;

import model.User;
import model.students;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class DefaultConfig extends JFinalConfig{
	
	@Override
	public void configConstant(Constants me) {
		me.setViewType(ViewType.JSP);
		me.setDevMode(true);
		PropKit.use("config.properties");
		 // 开启对 jfinal web 项目组件 Controller、Interceptor、Validator 的注入
	    me.setInjectDependency(true);
	    
	    // 开启对超类的注入。不开启时可以在超类中通过 Aop.get(...) 进行注入
	    me.setInjectSuperClass(true);
	    me.setBaseUploadPath("WEB-INF/upload/");
	    me.setBaseDownloadPath("WEB-INF/upload/");
	}
	
	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void configHandler(Handlers me) {
		
	}
	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void configPlugin(Plugins me) {
		 C3p0Plugin c3p0Plugin=new C3p0Plugin(PropKit.get("jdbcUrl"),PropKit.get("username"),PropKit.get("password"));
	        ActiveRecordPlugin arp0=new ActiveRecordPlugin(c3p0Plugin);
	        arp0.setShowSql(true);
	        arp0.addMapping("students", students.class);
	        arp0.addMapping("user", User.class);
	        me.add(c3p0Plugin);
	        me.add(arp0);
	}@Override
	public void configRoute(Routes me) {
		
		me.add("/user", UserController.class,"/WEB-INF/view");
		me.add("/students",StudentController.class,"/WEB-INF/view");
		me.add("/table", TableController.class,"/WEB-INF/table");
	}
	
	public static void main(String[] args) {
		JFinal.start("WebRoot",8080,"/",5);
	}

}
