<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>table模块快速使用</title>
  <link rel="stylesheet" href="../layui/css/layui.css" media="all">
</head>
<body>
 
<table id="demo" lay-filter="test"></table>
 
<script src="../layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table;
  
  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 312
    ,url: '/demo/table/user/' //数据接口
    ,page: true //开启分页
    ,response: {
    statusName: 'status' //规定数据状态的字段名称，默认：code
    ,statusCode: 200 //规定成功的状态码，默认：0
    ,msgName: 'hint' //规定状态信息的字段名称，默认：msg
    ,countName: 'total' //规定数据总数的字段名称，默认：count
    ,dataName: 'rows' //规定数据列表的字段名称，默认：data
  } ,parseData: function(res){ //res 即为原始返回的数据
    return {
      "code": res.status, //解析接口状态
      "msg": res.hint, //解析提示文本
      "count": res.total, //解析数据长度
      "data": res.data //解析数据列表
    };
  }
    ,cols: [[ //表头
      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
      ,{field: 'username', title: '用户名', width:80}
      ,{field: 'sex', title: '性别', width:80, sort: true}
      ,{field: 'city', title: '城市', width:80} 
      ,{field: 'sign', title: '签名', width: 177}
      ,{field: 'experience', title: '积分', width: 80, sort: true}
      ,{field: 'score', title: '评分', width: 80, sort: true}
      ,{field: 'classify', title: '职业', width: 80}
      ,{field: 'wealth', title: '财富', width: 135, sort: true}
    ]]
    
  });
  
});
</script>
</body>
</html>