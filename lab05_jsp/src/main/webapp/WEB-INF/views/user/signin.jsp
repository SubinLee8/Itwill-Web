<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>
    <c:url value="로그인" var="pageTitle" />
    <%@include file="../fragments/header.jspf"%>
    
    <div class="card">
        <div class="card-head">
        
        </div>
        <div class="card-body">
        <c:url value="/user/signin" var="signInPage" />
            <form action="${signInPage }" method="post">
            <div class="mt-2">
             <label  for="username">아이디</label>
                <input class="form-control" type="text" name="username" id="username" required>
            </div>
               <div class="mt-2">
                <label for="password">비밀번호</label>
                <input class="form-control" type="text" name="password" id="password" required>
               </div>
               <div class="d-none">
                <input name="target" value=" ${param.target}" readonly/>
               </div>
               <c:if test="${not empty param.result && param.result eq 'f'}" >
               <div class="text-danger mt-2">아이디와 패스워드를 확인하세요.</div></c:if>
               
               
               <div class="mt-2">
                <input class="form-control  btn btn-outline-primary" type="submit" value="로그인">
               </div>
            </form>
            
        </div>
    </div>


    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>