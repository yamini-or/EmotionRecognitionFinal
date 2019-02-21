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
			footer {
				position: absolute;
			    right: 0;
		    	bottom: 0;
				left: 0;
				padding: 1rem;
			}
		</style>
    </head>

<body>
<h1>Upload Status</h1>
<br/><br/>
<h2>Message : ${message}</h2>
<br/>
<form method="POST" action="${pageContext.request.contextPath}/startAnalysis" >
    <input type="hidden" name="video" value="${video}"/>
    <input type="submit" name="submit" value="View Analysis" style="font-size:15pt; font-family:arial; color:white; background-color:red; border:4px solid #A9A9A9; padding:5px; width:200px"/>
</form>

<footer>
		  <img src="${pageContext.request.contextPath}/resources/images/oracle_footer.png" alt="oracle_copyright" class="expand" style="width:1300px;height:40px;"/>
</footer>
</body>
</html>
