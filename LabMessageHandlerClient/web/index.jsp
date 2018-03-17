<%-- 
    Document   : indexs
    Created on : 15-Mar-2018, 4:20:23 PM
    Author     : dhruvinparikh.byethost24.com
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="processMessage.jsp" method="post">
            <input type="text" name="msg">
            <input type="submit" value="Send" name="send">
        </form>
        <br>
        <%-- start web service invocation --%><hr/>
        <%
            if (request.getParameter("Send") != null) {
                if (request.getParameter("send").equals("Send")) {
                    try {
                        messagehandler.client.MessageHandlerService_Service service = new messagehandler.client.MessageHandlerService_Service();
                        messagehandler.client.MessageHandlerService port = service.getMessageHandlerServicePort();
                        // TODO initialize WS operation arguments here
                        java.lang.String name = request.getParameter("msg");
                        // TODO process result here
                        java.lang.String result = port.hello(name);
                        out.println("Result = " + result);
                    } catch (Exception ex) {
                        out.println("Exception = " + ex.toString());
                    }
                }
            }
        %>
        <%-- end web service invocation --%>
    </body>
</html>
