<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <header class="navbar navbar-static-top" id="top" role="banner">

</header>
<body>
    <form action="${student==null?'/students/submit':'/students/update'}" method="post">
    <input type="hidden" name="student.id" value="${student.id}">
    <label>姓名</label>
    <input type="text" name="student.name" value="${student.name}">
    <label>年龄</label>
    <input type="text" name="student.age" value="${student.age}">
    <div >
    <label>性别</label>
    <div>
    <input type="radio"  name="student.sex" <c:if test="${student.sex=='男'}">checked="checked"</c:if>value="男"> 男
    </div>
    <div>
    <input type="radio"   name="student.sex" <c:if test="${student.sex=='女'}">checked="checked"</c:if>value="女"> 女
    </div>
    </div>
    <label>备注</label>
    <textarea rows="10" cols="20" name="student.remark">${student.remark}</textarea>
    <button type="submit">提交</button>
    </form>
</body>

