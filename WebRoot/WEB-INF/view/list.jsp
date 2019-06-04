<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">

<title>Insert title here</title>
</head>

<body>
    <a href="/students/form/">增加学生</a>
    <a href="/students/export/">导出excel表格数据</a>
    <form action="/students/inport" method="post">
    <input type="file">
    <input type="submit" value="导入excel表格数据">
    </form>
    <form action="/students/select/">
    	<input type="text" name="selectone">
    	<button type="submit">查询</button></form>
    	<a href="/students/upload/">上传文件</a>
    <table border="2" width="80%">      
        <caption><h1>学生信息</h1></caption>
        <thead>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>备注</th>
        <th rowspan="2">操作</th>
        </thead>
        <tbody>
        <c:forEach items="${student}" var="student">
        <tr>
        <th>${student.name}</th>
        <th>${student.age}</th>
        <th>${student.sex}</th>
        <th>${student.remark}</th>
        <th><a href="/students/edit/${student.id}">修改 </a><a href="/students/delete/${student.id}"> 删除</a></th>
        </tr>
        </c:forEach>
        </tbody>
            
        <tr>        
        <th colspan="5">总页数  ${pagination}/${page}    
        <a href="/students">首页</a>    
        <a href="/students/Previouspage/${pageNumber}-${pagination}">上一页</a>    
        <a href="/nextpage/${pageNumber}-${pagination}">下一页</a>    
        <a href="/students/trailerpage/">尾页</a>   
        <form action="/students/jumpPage/">
        <label>跳转到</label>
        <input type="text" name="number">
        <button type="submit">确定</button>
        </form>
        </th>
        </tr>
    </table>
    
    
      
</body>
</html>