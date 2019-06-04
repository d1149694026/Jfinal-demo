package controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.students;
import util.ExcelExportUtil;
import base.Excel;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import common.Service;

public class StudentController extends Controller {
	@Inject 
	Service serivce;
	    //下一页的参数
	    Integer pageNumber=0;
	    //上一页的参数
	    Integer looktg=0;
	    //当前页数
	    Integer pagination=1;
	    //每一页显示的个数
	    Integer number=3;
	    public void index()
	    {
	        pageNumber=0;
	        setAttr("pageNumber", pageNumber);
	        setAttr("student", serivce.StudentList(number));
	        if(serivce.pageNumber().size()%number!=0)
	        {
	            setAttr("page", (serivce.pageNumber().size()/number)+1);
	        }
	        else
	        {
	            setAttr("page", serivce.pageNumber().size()/number);
	        }
	        setAttr("pagination", pagination);
	        render("list.jsp");     

	    }
	    public void submit()
	    {       
	        students student=getModel(students.class,"student");
	        serivce.submitMessage(student);
	        if(serivce.pageNumber().size()%2==1)
	        {
	            setAttr("page", (serivce.pageNumber().size()/number)+1);
	        }
	        else
	        {
	            setAttr("page", serivce.pageNumber().size()/number);
	        }
	        redirect("/students");
	    }
	    public void delete()
	    {
	        serivce.deleteStudent(getParaToInt(0));
	        index();
	    }
	    public void edit()
	    {
	        form();
	    }
	    public void form()
	    {
	        students student=serivce.editMessage(getParaToInt(0));
	        setAttr("student", student);
	        render("form.jsp");
	    }
	    public void update()
	    {
	        students stu=getModel(students.class,"student");
	        serivce.updateMessage(stu);
	        redirect("/students");
	    }
	    /**
	     * 下一页
	     */
	    @ActionKey("/nextpage")
	    public void nextpage()
	    {   
	        pageNumber=getParaToInt(0)+number;
	        System.out.println(pageNumber);
	        if(pageNumber>serivce.pageNumber().size()-1)
	        {
	            index();
	        }
	        else
	        {
	            pagination=getParaToInt(1)+1;
	            List<students> dao=serivce.sqlstatement(pageNumber,number);
	            if(serivce.pageNumber().size()%number!=0)
	            {
	                setAttr("page", (serivce.pageNumber().size()/number)+1);
	            }
	            else
	            {
	                setAttr("page", serivce.pageNumber().size()/number);
	            }
	            setAttr("pageNumber", pageNumber);
	            setAttr("student", dao);
	            setAttr("pagination", pagination);
	            render("list.jsp");
	        }
	    }
	    /**
	     * 上一页
	     */
	    public void Previouspage()
	    {
	        
	        looktg=getParaToInt(0)-number;
	        if(looktg<0)
	        {
	            index();
	        }
	        else
	        {
	            pagination=getParaToInt(1)-1;
	            List<students> dao=serivce.sqlstatement(looktg, number);
	            if(serivce.pageNumber().size()%number!=0)
	            {
	                setAttr("page", (serivce.pageNumber().size()/number)+1);
	            }
	            else
	            {
	                setAttr("page", serivce.pageNumber().size()/number);
	            }
	            setAttr("pageNumber", looktg);
	            setAttr("student", dao);    
	            setAttr("pagination", pagination);
	            render("list.jsp");
	        }
	    }
	    /**
	     * 尾页
	     */
	    public void trailerpage()
	    {
	        Integer number1=0;
	        if(serivce.pageNumber().size()%number==0)
	        {
	            number1=serivce.pageNumber().size()-number; 
	        }
	        else
	        {
	            number1=serivce.pageNumber().size()-(serivce.pageNumber().size()%number);       
	        }   
	        if(serivce.pageNumber().size()%number!=0)
	        {
	            setAttr("page", (serivce.pageNumber().size()/number)+1);
	            pagination=serivce.pageNumber().size()/number+1;
	        }
	        else
	        {
	            setAttr("page", serivce.pageNumber().size()/number);
	            pagination=serivce.pageNumber().size()/number;
	        }
	        System.out.println(number1);
	        List<students> dao=serivce.sqlstatement(number1, number);
	        setAttr("pageNumber", serivce.pageNumber().size()-number);
	        setAttr("student", dao);
	        setAttr("pagination", pagination);
	        render("list.jsp");
	    }
	    /**
	     * 跳转到指定页面
	     */
	    public void jumpPage()
	    {       
	        //获取前端值
	        String pagination=getPara("number");
	        //转换格式
	        Integer number1=Integer.parseInt(pagination);
	        //从数据库中得到指定数据
	        List<students> dao=nextpage(number1);
	        pageNumber=number1*number-number;
	        if(serivce.pageNumber().size()%number!=0)
	        {
	            setAttr("page", (serivce.pageNumber().size()/number)+1);
	        }
	        else
	        {
	            setAttr("page", serivce.pageNumber().size()/number);
	        }
	        setAttr("pagination", pagination);
	        setAttr("pageNumber", pageNumber);
	        setAttr("student", dao);
	        render("list.jsp");
	    }
	    public List<students> nextpage(Integer number1)
	    {   
	        Integer pageNumber=number1*number-number;
	        List<students> dao=serivce.sqlstatement(pageNumber,number);
	        return dao;
	    }
	    public void select(String name,Integer number){
	    	name=getPara("selectone");
	    	number=3;
	    	List<students> dao=serivce.selectInfo(name,number);
	    	System.out.println(dao);
	    	setAttr("student", dao);
	    	 pageNumber=0;
		        setAttr("pageNumber", pageNumber);
		        if(serivce.pageNumber2(name).size()%number!=0)
		        {
		            setAttr("page", (serivce.pageNumber2(name).size()/number)+1);
		        }
		        else
		        {
		            setAttr("page", serivce.pageNumber2(name).size()/number);
		        }
		        setAttr("pagination", pagination);
		        render("list.jsp");
	    }
	  public void upload(){
	    	render("upload.jsp");
	    }
	    public void uploadSave(){
	    	UploadFile uploadFile=getFile();
	    	File file=uploadFile.getFile();
	    	System.out.println(uploadFile.getUploadPath());
	    	
	    	/*File upfile =new File(PathKit.getWebRootPath()+"/WEB-INF/upload/"+System.currentTimeMillis()+".jpg");
	    	System.out.println(upfile);
	    	try {
	    		if (!upfile.exists()) {
					upfile.createNewFile();
					System.out.println("创建完成");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    
	    	try {
	    		FileInputStream fis=new FileInputStream(file);
	    		byte[] in=new byte[fis.available()];
	    		System.out.println(1024);
	    		int len=-1;
	    		if (fis.read(in)!=len) {
					FileOutputStream fos=new FileOutputStream(upfile);
					fos.write(in);
				}
	    		
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				
			}*/
	    	
	    	
//	    	String filename=file.getName();
//	    	String delfilename =filename;
//	    	if (filename!=null&&!filename.equals("")) {
//	    		filename=new SimpleDateFormat("yyyyMMddkkmmss").format(new Date())+filename;
////			新保存的位置
////	    		String path =getRequest().getSession().getServletContext().getRealPath("/");
//	    		String newPath=PathKit.getWebRootPath()+"/WEB-INF/upload/";
////	    		没有就新建目录
//	    		File folder =new File(newPath);
//	    		if (!folder.exists()) {
//					folder.mkdirs();
//				}
////	    		保存新文件
//	    		FileInputStream fis =null;
//	    		FileOutputStream fos =null;
//	    		try {
//					File savePath =new File(newPath+filename);
//					if (!savePath.isDirectory()) {
//						savePath.createNewFile();
//						fis=new FileInputStream(file);
//						fos=new FileOutputStream(savePath);
//						byte[] bt=new byte[300];
//						while (fis.read(bt,0,300)!=-1) {
//							fos.write(bt, 0, 300);
//						}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally{
//					try {
//						if(null!=fis){fis.close();}
//						if(null!=fos){fos.close();}
//					} catch (Exception e2) {
//						e2.printStackTrace();
//					}
//				}
////	    		删除原图片
//	    		File delFile =new File(newPath+delfilename);
//	    		if (delFile.exists()) {
//					delFile.delete();
//					setAttr("msg", filename);
//					setAttr("t", 1);
//				}else {
//					setAttr("t", 0);
//				}
	    		renderJson();
	    	}
	    	
	    
	     public void download() {
	    	 render("download.jsp");
	    	 }
	     public void retention(){
	    	 renderFile("111.jpg");
	     }
	     public void export(){
	    		String sql = "select * from students";
	    	        Map<String, String> titleData = new HashMap<String, String>();//标题，后面用到
	    	        titleData.put("id", "ID");
	    	        titleData.put("name", "学生名字");
	    	        titleData.put("age", "年龄");
	    	        titleData.put("sex", "性别");
	    	        titleData.put("remark", "备注");
	    	        File file = new File(ExcelExportUtil.getTitle());
	    	        try {
	    	        	if (!file.exists()) {
							file.createNewFile();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
	    		file = ExcelExportUtil.saveFile(titleData, sql, file);
	    		this.renderFile(file);
	    	}
	     public void inportExcel() throws Exception{
	    	 File file =new File(PathKit.getWebRootPath()+"/WEB-INF/upload/2019-05-28_统计报表.xls");
	    	 String[][] data=Excel.getData(file, 1);
	    	 
	    	  for(int m=0;m<data.length;m++){//控制行数
//	              for(int n=0;n<data[m].length;n++){//一行中有多少个元素（即多少列）
//	                  System.out.print(data[m][n]+" ");
//	                 
//	              }
	              String sql="insert into students(id,name,age,sex,remark) values("+data[m][0]+",'"+data[m][1]+"',"+data[m][2]+",'"+data[m][3]+"','"+data[m][4]+"');";
	              Db.update(sql);
	              System.out.println("chenggong");
	          }
	    	 
//              List<students> newdata=students.students.find(sql);
             
	    	 renderJson("导入成功");
	     }
	     
}