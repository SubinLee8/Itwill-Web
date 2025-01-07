<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>All Posts</title>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">

<style>
.pagination {
    border-radius: 0;
    padding: 0;
    margin: 0
}

.pagination ul {
    display: inline-block;
    *display: inline;
    *zoom: 1;
    margin: 0 auto;
    padding: 0
}

.pagination li {
    display: inline
}

.pagination a {
    float: left;
    padding: 0 18px;
    line-height: 40px;
    text-decoration: none;
    border: 1px solid #dbdbdb;
    border-left-width: 0;
    background: #fff
}

.pagination a:hover {
    background-color: #1d184a;
    color: #fff
}

.pagination .active a {
    background-color: #f7f7f7;
    color: #999;
    cursor: default
}

.pagination .disabled span {
    color: #999;
    background-color: transparent;
    cursor: default
}

.pagination .disabled a {
    color: #999;
    background-color: transparent;
    cursor: default
}

.pagination .disabled a:hover {
    color: #999;
    background-color: transparent;
    cursor: default
}

.pagination li:first-child a {
    border-left-width: 1px
}

.mt-6, .my-6 {
    margin-top: 3.5rem;
}
</style>
</head>
<body>
    <%@include file="../fragments/header.jspf"%>


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
                <c:forEach var="post" items="${posts}">
                    <tr>
                        <c:url value="/post/details"
                            var="postDetailsPage">
                            <c:param name="id" value="${post.id}" />
                        </c:url>
                        <td>${post.id}</a></td>
                        <td><a href="${postDetailsPage}">${post.title}</td>
                        <td>${post.author}</td>
                        <td>${post.createdTime}</td>
                        <td>${post.modifiedTime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row mt-6 wow fadeInUp" data-wow-delay=".6s"
        style="visibility: visible; animation-delay: 0.6s; animation-name: fadeInUp;">
        <div class="col-12">
            <div
                class="pagination text-small text-uppercase text-extra-dark-gray">
                <ul>
                    <li><a href="#!"><i
                            class="fas fa-long-arrow-alt-left me-1 d-none d-sm-inline-block"></i>
                            Prev</a></li>
                    <li class="active"><a href="#!">1</a></li>
                    <li><a href="#!">2</a></li>
                    <li><a href="#!">3</a></li>
                    <li><a href="#!">Next <i
                            class="fas fa-long-arrow-alt-right ms-1 d-none d-sm-inline-block"></i></a></li>
                </ul>
            </div>
        </div>
    </div>


    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>