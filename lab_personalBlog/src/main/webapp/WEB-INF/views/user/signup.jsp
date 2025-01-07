<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign Up</title>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>

    <main class="mt-2">
        <%@include file="../fragments/header.jspf"%>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h1>회원가입</h1>
                </div>
                <div class="card-body">

                    <c:url value="/user/signup" var="userSignUpPage" />
                    <form method="post" action="${userSignUpPage }">

                        <div class="mt-2">
                            <label for="username" class="form-label">아이디</label>
                            <input class="form-control" type="text"
                                placeholder="아이디" name="username"
                                id="username" required autofocus
                                required>
                        </div>
                        <div class="mt-2">
                            <label for="password" class="form-label">비밀번호</label>
                            <input class="form-control" type="text"
                                placeholder="비밀번호" name="password"
                                id="password" required>
                        </div>
                        <div class="mt-2">
                            <label for="email" class="form-label">이메일</label>
                            <input class="form-control" type="text"
                                placeholder="이메일" name="email"
                                id="email" required>
                        </div>
                        <div class="d-none">
                            <input name="target"
                                value=" ${param.target}" readonly />
                        </div>
                        <c:if
                            test="${not empty param.result && param.result eq 'f'}">
                            <div class="text-danger mt-2">중복되는 아이디가 존재합니다.</div>
                        </c:if>

                        <div class="mt-2">
                            <input
                                class="form-control btn btn-outline-primary mt-2"
                                type="submit">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </main>

    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>