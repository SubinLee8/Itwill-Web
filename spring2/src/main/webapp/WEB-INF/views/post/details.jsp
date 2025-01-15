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

    <c:set var="pageTitle" value="포스트 상세보기" />
    <%@include file="../fragments/header.jspf"%>
    <main>

        <div class="card">
            <div class="card-header">
                <h1>글 상세보기</h1>
            </div>
            <div class="card-body">
                <form>
                    <div class="mt-2">
                        <label class="form-label" for="id">번호</label> <input
                            class="form-control" type="text" id="id"
                            value="${post.id}" readonly>
                    </div>
                    <div class="mt-2">
                        <label class="form-label" for="title">제목</label>
                        <input class="form-control" type="text"
                            id="title" value="${post.title}" readonly>
                    </div>
                    <div class="mt-2">
                        <label class="form-label" for="author">작성자</label>
                        <input class="form-control" type="text" id="id"
                            value="${post.author}" readonly>
                    </div>
                    <div class="mt-2">
                        <label class="form-label" for="content">내용</label>
                        <textarea class="form-control" type="text"
                            id="content" value="${post.content}"
                            rows="10" col="5" readonly>${post.content}</textarea>
                    </div>
                    <div class="mt-2">
                        <label class="form-label" for="createdTime">작성시간</label>
                        <input class="form-control" type="text"
                            id="createdTime" value="${post.createdTime}"
                            readonly>
                    </div>
                    <div class="mt-2">
                        <label class="form-label" for="modifiedTime">수정시간</label>
                        <input class="form-control" type="text"
                            id="modifiedTime"
                            value="${post.modifiedTime}" readonly>
                    </div>
                </form>
            </div>

            <div class="card-footer d-flex justify-content-center">

                <c:if test="${post.author eq signedInUser}">
                    <c:url value="/post/modify" var="postModifyPage">
                        <c:param name="id" value="${post.id }" />
                    </c:url>
                    <a class="btn btn-outline-primary"
                        href="${postModifyPage }">수정하기</a>
                </c:if>

            </div>
        </div>
    </main>
    <section>
        <div class="mt-2 d-inline-flex gap-1">
            <button class="btn btn-outline-secondary"
                id="btnToggleComment">댓글 보기</button>
        </div>

        <!-- 댓글 보기/감추기 토글 버튼에 의해서 접기/펼치기를 할 영역 -->
        <div class="mt-2 collapse" id="collapseComments">
            <!-- 댓글 등록 UI -->
            <div class="mt-2 card card-body">
                <div class="row">
                    <div class="col-10">
                        <input class="d-none" id="username"
                            value="${signedInUser}" readonly />
                        <textarea class="form-control" rows="3"
                            id="ctext" placeholder="댓글 입력"></textarea>
                    </div>
                    <div class="col-2">
                        <button class="btn btn-outline-success"
                            id="btnRegisterComment">등록</button>
                    </div>
                </div>
            </div>

            <!-- 댓글 목록 UI -->
            <div class="my-2" id="divComments"></div>
        </div>
    </section>


    <!-- 댓글을 업데이트할 수 있는 모달(다이얼로그) -->
    <div class="modal" id="commentModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">댓글 업데이트</h5>
                    <button class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- 수정할 댓글 아이디 (번호) -->
                    <input class="d-none" id="modalCommentId" readonly />
                    <!-- 수정할 댓글 내용 -->
                    <textarea class="form-control" id="modalCommentText"></textarea>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline-secondary"
                        data-bs-dismiss="modal">취소</button>
                    <button class="btn btn-outline-success"
                        id="btnUpdateComnt">저장</button>

                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <!-- Axios Http Js -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <c:url value="/js/comments.js" var="commentsJs" />
    
    <script>
       //세션에 저장된 로그인 사용자 아이디를 자바스크립트 변수에 저장.
       //->comment.js 파일의 코드들에서 그 변수를 사용할 수 있도록 하기 위해서
       const signedInUser='${signedInUser}';//문자열 포맷으로 변수를 저장.
    </script>
    
    <script src="${commentsJs}"></script>
</body>
</html>