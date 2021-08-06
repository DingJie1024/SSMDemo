<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: D.J
  Date: 8/4
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>修改书籍</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row-clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>修改书籍</small>
                    </h1>
                </div>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <input type="hidden" name="bookID" value="${QBook.bookID}">
        <div class="form-group">
            <label for="bkName">书籍名称</label>
            <input type="text" name="bookName" class="form-control" id="bkName" value="${QBook.bookName}" required>
        </div>
        <div class="form-group">
            <label for="bkCount">书籍数量</label>
            <input type="text" name="bookCounts" class="form-control" id="bkCount" value="${QBook.bookCounts}" required>
        </div>
        <div class="form-group">
            <label for="bkDetail">书籍描述</label>
            <input type="text" name="detail" class="form-control" id="bkDetail" value="${QBook.detail}" required>
        </div>
        <div class="form-group">
           <input type="submit" value="确认修改">
        </div>
    </form>
</body>
</html>
