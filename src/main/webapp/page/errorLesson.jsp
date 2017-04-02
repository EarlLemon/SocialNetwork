<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="Bundle"/>
<fmt:message key="longLesson" var="longLesson"/>
<fmt:message key="goBack" var="goBack"/>
<span>${longLesson}</span>
<form action="/page/">
    <button type="submit">${goBack}</button>
</form>
</body>
