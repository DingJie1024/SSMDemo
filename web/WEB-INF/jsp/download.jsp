<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/load/upload" enctype="multipart/form-data" method="post">
  <input type="file" name="file"/><br>
  <input type="submit" value="上传"/>
</form>
<a href="${pageContext.request.contextPath}/load/download">文件流下载</a><br>
<a href="${pageContext.request.contextPath}/static/img_title.jpg">直接下载</a>
</body>
</html>
