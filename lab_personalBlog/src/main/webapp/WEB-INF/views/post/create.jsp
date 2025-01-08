<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>New Post</title>
<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>
    <%@include file="../fragments/header.jspf"%>


    <div class="container-fluid">
        <main class="mt-2">
            <div class="card">
                <div class="card-header">
                    <h1>새 포스트</h1>
                </div>

                <div class="card-body">
                    <!-- c:url태그와 form의 action태그는 생략 가능함 -->
                    <c:url value="/post/create" var="postCreatePage" />
                    <form method="post" action="${postCreatePage}"
                        enctype="multipart/form-data" id="newpost">
                        <div class="mt-2">
                            <input class="form-control" type="text"
                                placeholder="제목" name="title" required
                                autofocus required />
                        </div>
                        <div class="mt-2">
                            <input class="form-control" type="text"
                                placeholder="작성자" name="author"
                                value="${signedInUser}" readonly />
                        </div>
                        <div class="mt-2">
                            <textarea class="form-control"
                                name="content" placeholder="내용"
                                cols="20" rows="5" required></textarea>
                        </div>
                        <div class="mt-2">
                            <label for="file"> 파일</label> <input
                                type="file" id="file" name="fileName"><br>
                        </div>
                        <div class="mt-2 d-flex justify-content-end">
                            <input class="btn btn-outline-danger me-2"
                                type="reset" value="취소" />

                            <button class="btn btn-outline-success"
                                value="submit">저장</button>

                        </div>
                    </form>
                </div>




            </div>

        </main>
    </div>
    <%@ include file="../fragments/footer.jspf"%>

    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>