<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
    	<style>
			h1 {
			    font-family: verdana;
			    text-decoration: underline;
			    text-align: center;
			}
			h2 {
			    font-family: courier;
			}
			th {
				font-family: times;
				text-align: center;
				color: white;
				}
			td {
				font-family: arial;
				text-align: center;
				}
			footer {
				position: absolute;
			    right: 0;
		    	bottom: 0;
				left: 0;
				padding: 1rem;
			}
		</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Emotion Recognition Model</title>
        <h1>Emotion Recognition Model</h1>
    </head>
    <body>
    	<div align="left">
	        <h2>Video/Image Analysis Summary:</h2>
	        
	        <table border="1">
	        	<th bgcolor="#FF0000">SNo.</th>
	        	<th bgcolor="#FF0000">Title</th>
	        	<th bgcolor="#FF0000">Link</th>
	        	<th bgcolor="#FF0000">Recruiter Name</th>
	        	<th bgcolor="#FF0000">Candidate Name</th>
	        	<th bgcolor="#FF0000">Face Count</th>
	        	<th bgcolor="#FF0000">Neutral</th>
	        	<th bgcolor="#FF0000">Anger</th>
	        	<th bgcolor="#FF0000">Disgust</th>
	        	<th bgcolor="#FF0000">Happy</th>
	        	<th bgcolor="#FF0000">Surprise</th>
	        	<th bgcolor="#FF0000">Comments</th>
	        	<th bgcolor="#FF0000">Action</th>
				<c:forEach var="video" items="${listVideo}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${video.title}</td>

					<td><a href = "${pageContext.request.contextPath}/startAnalysis?video=${video.link}">${video.link}</a></td>
					
					<td>${video.recruiter}</td>
					<td>${video.candidate}</td>
					<td>${video.faceCount}</td>
					<td>${video.neutral}</td>
					<td>${video.anger}</td>
					<td>${video.disgust}</td>
					<td>${video.happy}</td>
					<td>${video.surprise}</td>
					<td>${video.comments}</td>

					<td>
						<a href="${pageContext.request.contextPath}/deleteVideo?video=${video.link}">Delete</a>
					</td>
						
	        	</tr>
				</c:forEach>	        	
			</table>

    	</div>
    	<br><br><br>
    	<form method="POST" action="${pageContext.request.contextPath}/homePage" enctype="multipart/form-data">
     		<!--input type="submit" name = "act1" value="View Files" /-->
     		<input type="submit" name = "act2" value="Upload New File" style="font-size:15pt; font-family:arial; color:white; background-color:red; border:4px solid #A9A9A9; padding:5px; width:200px"/>
		</form>
		<footer>
		</br></br>
		  <img src="${pageContext.request.contextPath}/resources/images/oracle_footer.png" alt="oracle_copyright" class="expand" style="width:1300px;height:40px;"/>
		</footer>
    </body>
</html>
