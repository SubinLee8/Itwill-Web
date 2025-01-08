<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>home</title>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
<style>
.jumbotron {
    padding: 2rem 1rem;
    margin-bottom: 2rem;
    background-color: #e9ecef;
    border-radius: .3rem;
}

.container {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}

div {
    display: block;
    unicode-bidi: isolate;
}
</style>
</head>
<body>
    <%@include file="./fragments/header.jspf"%>

    <div class="jumbotron">
        <div class="container">
            <div class="container mt-2">
                <h2 class="display-3">Hello, world!</h2>
                <p>생각하는 것을 적고, 다른 사람들의 의견을 감상하세요. 개인정보를 공개하지않아도 됩니다.</p>
                <p>
                    <c:url value="/post/create" var="postCreatePage"></c:url>
                    <a class="btn btn-primary" href="${postCreatePage }"
                        role="button">새 글 적기 »</a>
                </p>
            </div>
        </div>

    </div>


    <div class="container">
        <!-- Example row of columns -->
        <div class="row">
            <c:forEach var="post" items="${list}">
                <div class="col-md-4">
                    <h2>${post.title }</h2>
                    <p>${post.content}</p>
                    <p>
                        <c:url value="/post/details"
                            var="postDetailPage">
                            <c:param name="id" value="${post.id }" />
                        </c:url>
                        <a class="btn btn-secondary"
                            href="${postDetailPage }" role="button">View
                            details »</a>
                    </p>
                </div>
            </c:forEach>


        </div>

        <hr>

    </div>
    <%@include file="./fragments/footer.jspf"%>

    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>