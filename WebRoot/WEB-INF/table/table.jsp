<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap 实例</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>响应式表格</h2>
  <p>.table-responsive 类用于创建响应式表格：在屏幕宽度小于 992px 时会创建水平滚动条，如果可视区域宽度大于 992px 则显示不同效果（没有滚动条）:</p>                                                                                      
  <div class="table-responsive">          
  <table class="table">
    <thead>
      <tr>
        <th>Id</th>
        <th>Username</th>
        <th>Sex</th>
        <th>City</th>
        <th>City</th>
        <th>Experience</th>
        <th>Score</th>
        <th>Classify</th>
        <th>Wealth</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${user}" var="user">
      <tr>
        <th>${user.id}</th>
        <td>${user.username}</td>
        <td>${user.sex}</td>
        <td>${user.city}</td>
        <td>${user.sign}</td>
        <td>${user.experience}</td>
        <td>${user.score}</td>
        <td>${user.classify}</td>
        <td>${user.wealth}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <h1>哈哈哈哈哈</h1>
  </div>
</div>
<form action="/table/alipay">
	<button type="submit">支付</button>
</form>

</body>
</html>