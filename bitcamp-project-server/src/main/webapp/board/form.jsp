<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<div class='container' style='margin-top:20px;'>
<h1>게시물 입력(JSP)</h1>
<form action='add' method='post'>
내용:<br>
<textarea name='title' rows='5' cols='60'></textarea><br>
<button>등록</button>
</form>
</div>
<jsp:include page="/footer.jsp"/>
