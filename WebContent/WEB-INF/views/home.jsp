<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
  <head>
    <title>MySpringMVCTestPage</title>
    <link rel="stylesheet"  type="text/css" href="<c:url value="/resources/style.css" />"/>
  </head>
  <body>
    
    <h1><s:message code="home.welcome"/></h1>

     <script type=text/javascript>
     var num = Math.floor( Math.random()*100 );
     document.write("<a href='<c:url value='/login' />'>login</a><hr/>");
     document.write("<a href='<c:url value='/updateMessage/"+num+"' />'>update message with id"+num+"</a><hr/>");
     document.write("<a href='<c:url value='/getMessage/"+num+"' />'>get message with id"+num+"</a><hr/>");
     document.write("<a href='<c:url value='/scrf-token' />'>get csrf token</a><hr/>");
     document.write("<a href='<c:url value='/postMessage' />'>post message</a><hr/>");
     document.write("<a href='<c:url value='/deleteMessage' />'>delete message with id "+num+"</a><hr/>");
     document.write("<p>JMS</p><hr/>");
     document.write("<a href='<c:url value='/receiveMessage' />'>receive message</a><hr/>");
     document.write("<a href='<c:url value='/wanted/"+num+"' />'>sent id"+num+"</a><hr/>");
     document.write("<a href='<c:url value='/invokertest' />'>invoker test</a><hr/>");
     </script>
     
     
     
     
    
     
     
     
    
    
    
  </body>
</html> 
