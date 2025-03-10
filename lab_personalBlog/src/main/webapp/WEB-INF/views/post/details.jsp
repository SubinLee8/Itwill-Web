<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Details</title>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>
    <%@include file="../fragments/header.jspf"%>

    <div class="card-body">



        <div class="mt-2">
            <label class="form-label" for="id">번호</label> <input
                class="form-control" name="id" id="id" type="text"
                value="${post.id}" readonly />
        </div>

        <div class="mt-2">
            <label class="form-label" for="title">제목</label> <input
                class="form-control" id="title" type="text"
                value="${ post.title }" readonly />
        </div>
        <div class="mt-2">
            <label class="form-label" for="author">작성자</label> <input
                id="author" type="text" class="form-control"
                value="${ post.author }" readonly />
        </div>
        <div class="mt-2">
            <label class="form-label" for="content">내용</label>
            <textarea id="content" class="form-control" rows="5"
                readonly>${post.content}</textarea>
        </div>
        <div class="mt-2">
            <label class="form-label" for="createdTime">작성시간</label> <input
                type="text" id="createdTime" class="form-control"
                value="${post.createdTime }" readonly />
        </div>
        <div class="mt-2">
            <label class="form-label" for="modifiedTime">최종수정시간</label>
            <input id="modifiedTime" type="text" class="form-control"
                value="${post.modifiedTime }" readonly />
        </div>
        <div class="mt-2">
            <label class="form-label" for="img">첨부 파일</label>
            
            <c:set value="${post.fileName}" var="file" />
            <div class="mt-2">
                <img id="img" src="${file}" width="400" height="400" />
            </div>



        </div>
        <div class="card-footer">
            <div class="d-flex justify-content-center">

                <!--<c:url value="/post/delete" var="postDeletePage">
                        <c:param name="id" value="${post.id}" />
                    </c:url>-->
                <c:if test="${signedInUser eq post.author}">
                    <button class="btn btn-outline-danger me-2"
                        id="btnDelete">삭제</button>
                    <c:url value="/post/modify" var="postModifyPage">
                        <c:param name="id" value="${post.id}" />
                    </c:url>
                    <a class="btn btn-outline-success"
                        href="${postModifyPage}">수정하기</a>

                    <!--  <a class="btn btn-outline-success"
                                href="${postModifyPage}">수정하기</a>
                                <a class="btn btn-outline-success"
                                href="${postDeletePage}">삭제하기</a>-->
                </c:if>
            </div>
        </div>

    </div>
    <%@ include file="../fragments/footer.jspf"%>
    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <c:url value="/static/post_delete.js" var="postDeleteJs" />
    <script src="${postDeleteJs}">
					
				</script>
</body>
</html>