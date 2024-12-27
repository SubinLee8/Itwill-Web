<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
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
    <div class="container-fluid">
        <c:set value="포스트 수정하기" var="pageTitle" />
        <%@include file="../fragments/header.jspf"%>
        <div class="container-fluid">
            <main class="mt-2">
                <div class="card">

                    <div class="card-header">
                        <h1>글 수정하기</h1>
                    </div>

                    <div class="card-body">
                        <form id="modifyForm">
                           
                            <div class="mt-2 d-none">
                                <label class="form-label " for="id">번호</label> <input
                                    class="form-control" name="id"
                                    id="id" type="text"
                                    value=${post.id} readonly />
                            </div>
                            <div class="mt-2">
                                <label class="form-label"  for="title">제목</label> <input
                                    id="title" type="text"  class="form-control"  name="title" autofocus
                                    value=${post.title} />
                            </div>
                            <div class="mt-2 d-none">
                                <label class="form-label "  for="author">작성자</label> <input
                                    id="author" type="text" class="form-control"
                                    value=${post.author} readonly />
                            </div>
                            <div class="mt-2">
                                <label class="form-label"  for="content">내용</label>
                                <textarea id="content"  name="content" class="form-control" rows="5" >${post.content}</textarea>
                            </div>
                           
                        </form>
                    </div>
                    
                    <div class="card-footer">
                        <div class="d-flex justify-content-center">
                            <button class="btn btn-outline-danger me-2" id="btnDelete">삭제</button>
                            <button class="btn btn-outline-success" id="btnUpdate">업데이트</button>
                        </div>
                    </div>  
                </div>
            </main>
        </div>



    </div>


    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        
        
        
    <%--정적 리소스 (CSS, JS, 이미지, 동영상, ...)들은 WEB-INF 폴더 밑에 저장하면 안된다. --%>
    <!-- src/main/webapp/static/js/post_modify.js 파일을 포함. -->
    <c:url value="/static/js/post_modify.js" var="postModifyJs" />
    <script src=${postModifyJs}>
    </script>
</body>
</html>