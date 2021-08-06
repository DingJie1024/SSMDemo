<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>所有书籍</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row-clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>书籍列表</small>
                    </h1>
                    用户：${name} &nbsp;|&nbsp; <a href="${pageContext.request.contextPath}/user/toLoginOut">退出</a>
                </div>

            </div>
            <div class="row">
                <div class="col-md-4 column">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBookPage">添加书籍</a>
                </div>
                <div class="col-md-4 column">
                   <form class="form-inline" action="${pageContext.request.contextPath}/book/searchBook" method="post" style="float: right">
                       <span style="color: red;font-weight: bold">${error}</span>
                       <input type="text" name="searchBookByName" class="form-control" placeholder="请输入搜索的书名"/>
                       <input type="submit" value="搜索" class="btn btn-primary"/>
                   </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <td>书籍编号</td>
                        <td>书籍名称</td>
                        <td>书籍数量</td>
                        <td>书籍详情</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${list}">
                        <tr>
                            <td>${book.bookID}</td>
                            <td>${book.bookName}</td>
                            <td>${book.bookCounts}</td>
                            <td>${book.detail}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/book/toUpdateBookPage?id=${book.bookID}" class="btn btn-primary">编辑</a>
                                &nbsp;|&nbsp;
                                <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}" class="btn btn-danger">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
