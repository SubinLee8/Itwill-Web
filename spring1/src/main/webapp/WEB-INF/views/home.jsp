<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spring1</title>

<!-- Bootstrap CSS 링크. -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>

    <header>
        <h1>Home Page</h1>
        <h2>${now}</h2>
        <div>
        <c:url value="/images/cat2.jpeg" var="gureum" />
            <img alt="구름이" src="${gureum}">
        </div>
    </header>
    <main>
        <h1>목차</h1>
        <ul>
            <li>
                <c:url value="/example" var="examplePage"/>
                <a href="${examplePage}">컨트롤러 예제</a>
            </li>
            <li>
                <c:url value="/test" var="testPage"/>
                <a href="${testPage}">테스트페이지</a>
            </li>
            <li>
                <c:url value="/test2" var="forwardPage"/>
                <a href="${forwardPage}">포워드페이지</a>
            </li>
            <li>
                <c:url value="/test3" var="RedirectPage"/>
                <a href="${RedirectPage}">리다이렉트</a>
            </li>
            <li>
                <c:url value="/rest1" var="rest1Page"/>
                <a href="${rest1Page}">REST 1</a>
            </li>
            <li>
                <c:url value="/rest2" var="rest2Page"/>
                <a href="${rest2Page}">REST 2</a>
            </li>
            <li>
                <c:url value="/rest3" var="rest3Page"/>
                <a href="${rest3Page}">REST Controller 3</a>
            </li>
             <li>
                <c:url value="/rest4" var="rest4Page"/>
                <a href="${rest4Page}">REST Controller 4</a>
            </li>
        </ul>
    </main>
    
    

    <!-- Bootstrap Javascript  -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>