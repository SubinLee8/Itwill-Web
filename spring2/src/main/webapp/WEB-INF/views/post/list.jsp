<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>목록</title>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="포스트 목록" />
        <%@ include file="../fragments/header.jspf"%>
    </div>
    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

<div class="mt-2 card">
<div class="card-header">
<c:url var="postSearchPage" value="/post/search" />
<form action="${postSearchPage}" method="get">
    <div class="row"></div>
        <div class="col-3">
            <select class="form-control" name="category">
                <option value="t">제목</option>
                <option value="c">내용</option>
                <option value="tc">제목+내용</option>
                <option value="a">작성자</option>
            </select>
        </div>
        <div class="col-7">
            <input type="text" class="form-control" name="keyword" placeholder="검색어입력" required />
        </div>
        <div class="col-2">
            <input type="submit" value="검색" class="form-control btn btn-outline-secondary"/>
        </div>
</form>
</div>

<div class="card-body">
    <table class="table-hover table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>TITLE</th>
                <th>AUTHOR</th>
                <th>CREATED_TIME</th>
                <th>MODIFIED_TIME</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach var="post" items="${list}" >
            <tr>
                <td>${post.id}</td>
                <td><c:url value="/post/details" var="postModifyPage">
                <c:param name="id" value="${post.id }"/>
                </c:url><a href="${postModifyPage }">
                ${post.title}</a></td>
                <td>${post.author}</td>
                <td>${post.createdTime}</td>
                <td>${post.modifiedTime}</td>
            </tr>
           </c:forEach>
           
        </tbody>
    </table>
</div>
</div>
</main>

</html>