<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>새글작성</title>
<%@ include file="../fragments/header.jspf"%>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>
    <c:url var="postCreatePage" value="/post/create" />
    <form method="post" action="${postCreatePage }" class="form">
    <div class="mt-2">
     <input class="form-control mt-2" type="text" name="title" 
            placeholder="제목 입력하세요" required autofocus> 
    </div>
        <div class="mt-2">
        <input class="form-control"
            type="text" name="author"  placeholder="작성자">
        </div>
       <div class="mt-2">
       <textarea class="form-control" name="content" placeholder="내용" cols="20" rows="5"></textarea>
       </div>
       <div class="d-flex justify-content-end mt-2">
       <input class="btn btn-outline-danger me-2" type="reset" value="취소"/>
       <input class="btn btn-outline-success" type="submit">
       </div>
    </form>





    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>