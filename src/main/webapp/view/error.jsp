<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Hello World!</title>
    <meta name="viewport"
          content="width=device-width,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0, initial-scale=1.0">
</head>
<body>
<h2>error!</h2>
    <h3>${requestScope.msg}</h3>

<h2>自定义方法异常 显示页面</h2>
<h3>${requestScope.exception}</h3>
<h3>${requestScope.e}</h3>
</body>
</html>
