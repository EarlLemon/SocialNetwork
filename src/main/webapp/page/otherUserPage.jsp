<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${getUsername:getUsername(requestScope["userID"])}</title>

</head>
<body>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="Bundle"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="myPage" var="myPage"/>
<fmt:message key="allUsers" var="allUsers"/>
<fmt:message key="subscribes" var="subs"/>
<fmt:message key="followers" var="followers"/>
<fmt:message key="text" var="text"/>
<fmt:message key="lessons" var="lessons"/>
<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">${logout}</button>
    </form>
</div>

<span>${getUsername:getUsername(requestScope["userId"])}</span>
<div align="left">
    ${followers}: <a href="/followersList/?userId=${userId:getUserId(requestScope["userId"])}">
    ${countSubscribes:countSubscribes(requestScope["otherFollowers"])}</a>
    ${subs}: <a href="/subscribeList/?userId=${userId:getUserId(requestScope)["userId"]}">
    ${countSubscribes:countSubscribes(requestScope["otherSubscribes"])}</a>
</div>
<div align="left">
    <form action="/page/">
        <button type="submit">${myPage}</button>
    </form>
    <form action="GetAllUsers">
        <button type="submit">${allUsers}</button>
    </form>
</div>
<div align="middle">
    <form action="SubscribeController">
        <input type="hidden" value="${userId:getUserId(requestScope["userId"])}" name="idButton">
        <input type="submit" value="${subButton:getSubButton(requestScope["subButton"])}" name="subButton">
    </form>
    <span> ${lessons} ${getUsername:getUsername(requestScope["userId"])}:</span> <br/>
    <table border="1">
        <tr>

            <th>${text}</th>
        </tr>

        ${lessonOut:getUserLessons(requestScope["anotherPosts"])}

    </table>
    <br/>
</div>
</body>
</html>