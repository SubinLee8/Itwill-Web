<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상세보기</title>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="포스트 상세보기" />
        <%@include file="../fragments/header.jspf"%>
        <main>

            <div class="card">
                <div class="card-header">
                    <h1>글 상세보기</h1>
                </div>
                <div class="card-body">
                    <form id="modifyForm">
                        <div class="mt-2 d-none">
                            <label class="form-label" for="id">번호</label>
                            <input class="form-control" type="text"
                                id="id" value="${post.id}" name="id"
                                readonly>
                        </div>
                        <div class="mt-2">
                            <label class="form-label" for="title">제목</label>
                            <input class="form-control" type="text"
                                id="title" name="title"
                                value="${post.title}">
                        </div>

                        <div class="mt-2">
                            <label class="form-label" for="content">내용</label>
                            <textarea class="form-control" type="text"
                                id="content" value="${post.content}"
                                rows="10" col="5" name="content">${post.content}</textarea>
                        </div>

                    </form>
                </div>

                <c:if test="${post.author eq signedInUser}">
                    <div class="card-footer d-flex justify-content-end">
                        <button id="btnDelete"
                            class="btn btn-outline-danger me-2">삭제</button>
                        <button id="btnUpdate"
                            class="btn btn-outline-success">업데이트</button>
                    </div>
                </c:if>


            </div>



        </main>
    </div>

    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <%-- 자바스크립트 요청 주소는 /spring2/js/post-modify.js 인데,
    servlet-context.xml 설정에 의해서 static 폴더 아래에서 js 폴더를 찾음. --%>
    <c:url value="/js/post-modify.js" var="postModifyJs" />
    <script src="${postModifyJs}"></script>
</body>
</html>