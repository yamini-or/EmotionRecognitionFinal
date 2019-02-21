<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html>

<body>
<h1>Video Files : </h1>


<form method="POST" action="${pageContext.request.contextPath}/viewFiles" >
	<input list="video" name="video"  autocomplete="off">
  	<datalist id="video">
    
    <c:forTokens items="${files}" delims="," var="f">
  		<option value="${f}"/>
	</c:forTokens>
  </datalist>
	
    <input type="submit" value="Submit" />
</form>

<footer>
		</br></br>
		  <img src="${pageContext.request.contextPath}/resources/images/oracle_footer.png" alt="oracle_copyright" class="expand" style="width:1300px;height:40px;"/>
		  <p align="center">Copyright &copy 2010, Oracle and/or its affiliates. All rights reserved. Oracle Proprietary and Confidential</p>
		</footer>
</body>
</html>