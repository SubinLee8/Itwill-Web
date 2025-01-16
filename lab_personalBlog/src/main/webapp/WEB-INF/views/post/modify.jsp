<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Modify</title>

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
                    <h1>포스트 수정</h1>
                </div>

                <div class="card-body">
                    <form enctype="multipart/form-data" id="modifyForm">

                        <div class="mt-2 d-none">
                            <label class="form-label " for="id">번호</label>
                            <input class="form-control" name="id"
                                id="id" type="text" value="${post.id}"
                                readonly />
                        </div>
                        <div class="mt-2">
                            <label class="form-label" for="title">제목</label>
                            <input id="title" type="text"
                                class="form-control" name="title"
                                autofocus value="${post.title}" />
                        </div>
                        <div class="mt-2 d-none">
                            <label class="form-label " for="author">작성자</label>
                            <input id="author" type="text"
                                class="form-control"
                                value="${post.author
                                }"
                                readonly />
                        </div>
                        <div class="mt-2">
                            <label class="form-label" for="content">내용</label>
                            <textarea id="content" name="content"
                                class="form-control" rows="5">${post.content}</textarea>
                        </div>
                        <label class="form-label" for="presentPhoto">현재
                            첨부 사진</label> <img id="presentPhoto"
                            src="${post.fileName}">

                        <div class="mt-2">
                            <label for="file">바꿀 사진</label> <input
                                type="file" accept="image/*"
                                onchange="previewImage(event);"
                                id="file" name="fileName"><br>
                        </div>
                        <img id="preview" src="" alt="Image Preview"
                            style="display: none; width: 200px; height: 200px; margin-top: 10px;">
                    </form>
                </div>



            </div>

            <div class="card-footer">
                <c:if test="${post.author eq signedInUser }">
                    <div class="d-flex justify-content-center">
                        <button class="btn btn-outline-success"
                            id="btnUpdate">업데이트</button>
                    </div>
                </c:if>
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
    <c:url value="/static/post_modify.js" var="postModifyJs" />
    <script src=${postModifyJs}>
					
				</script>
    <script>
					function previewImage(event) {
						var reader = new FileReader();
						reader.onload = function() {
							var output = document.getElementById('preview');
							output.src = reader.result;
							output.style.display = 'block';
						};
						reader.readAsDataURL(event.target.files[0]);
					}
				</script>
</body>
</html>