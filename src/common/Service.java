package common;

import java.util.List;

import model.students;

public class Service {
	 public List<students> StudentList(Integer number)
	    {
	        String sql="select * from students limit 0,";
	        sql+=number;
	        List<students> dao=students.students.find(sql);
	        return dao;
	    }
	    /**
	     * 实现增加功能
	     */
	    public void submitMessage(students student)
	    {
	        System.out.println(student);
	        student.save();     
	    }
	    /**
	     * 实现删除功能
	     */
	    public void deleteStudent(Integer id)
	    {
	        students.students.deleteById(id);
	    }
	    public students editMessage(Integer id)
	    {
	        students stu=students.students.findById(id);
	        return stu;
	    }
	    /**
	     * 实现更新数据
	     */
	    public void updateMessage(students stu)
	    {
	        stu.update();
	    }
	    public List<students> sqlstatement(Integer p,Integer p2)
	    {
	        String sql="select * from students limit ";
	        sql+=p;
	        sql+=","; 
	        sql+=p2;
	        List<students> dao=students.students.find(sql);
	        return dao;
	    }
	    
	    public List<students> pageNumber()
	    {
	        List<students> dao=students.students.find("select * from students");
	        return dao;
	    }
	    
	    public List<students> pageNumber2(String name){
	    	String sql="select * from students where name=";
	    	sql+="'"+name+"'";
	    	sql+=" or age="+"'"+name+"'";
	    	sql+=" or sex="+"'"+name+"'";
	    	sql+=" or remark="+"'"+name+"'";
	    	List<students> dao=students.students.find(sql);
	    	return dao;
	    }
	    
	    public List<students> selectInfo(String name,Integer number){
	    	String sql="select * from students where name=";
	    	sql+="'"+name+"'";
	    	sql+=" or age="+"'"+name+"'";
	    	sql+=" or sex="+"'"+name+"'";
	    	sql+=" or remark="+"'"+name+"'";
	    	sql+=" limit 0,"+number;
	    	List<students> dao=students.students.find(sql);
//	    	Page<Record> dao=Db.paginate(1, 3,"select *", "from students where name=? or age=? or sex=? or remark=?", name);
	    	return dao;
	    }

}
