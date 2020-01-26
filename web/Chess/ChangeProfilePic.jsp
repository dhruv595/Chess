
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="Header.jsp" %>


<form method="post" action="../PictureServlet" enctype="multipart/form-data">
    <b>Select Pic<b>
<input type="file" name="ProfilePic" id="btnUploadPic" required class="more">
<input type="submit" value="Upload"  class="register" >    
    
</form>
    


<%@include file="Footer.jsp" %>
