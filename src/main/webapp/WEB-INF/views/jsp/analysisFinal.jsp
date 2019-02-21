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
			p {
				font-family: courier;
				text-decoration: underline;
			    text-align: center;
			    font-size: 1em;
			}
			footer {
				position: absolute;
			    right: 0;
		    	bottom: 0;
				left: 0;
				padding: 1rem;
			}
		</style>
      	<title> Pie Chart</title>
	    <script src="https://cdn.anychart.com/js/8.0.1/anychart-core.min.js"></script>
	    <script src="https://cdn.anychart.com/js/8.0.1/anychart-pie.min.js"></script>
  </head>

<body>
	<h1>Detailed Analysis</h1>
	 
	<div id="fileViewed" style="float-left;width:400px;height: 60%;display:inline-block;margin-left: 250px;">
		<p> ${video} </p>
		<!--img src="${pageContext.request.contextPath}/resources/images/${video}" alt="imageUploaded" height="400" width="350"-->
		
		<embed src="${pageContext.request.contextPath}/resources/images/${video}" type="video/mp4" loop="1" height="400" width="350" autostart="true"></embed>
		<!--a href="${pageContext.request.contextPath}/resources/images/${video}">${video}</a-->

		<!--video width="320" height="240" controls>
		  <source src="${pageContext.request.contextPath}/resources/images/${video}" type="video/mp4">
		  
		  Your browser does not support the video tag.
		</video-->
		
	</div>


	<div id="container" style="float-right;width:600px; height: 60%;display:inline-block;"></div>

	<script>
		anychart.onDocumentReady(function() {

		  // set the data
		  var data = [
		      {x: "Neutral", value: ${emotionList[0]}},
		      {x: "Anger", value: ${emotionList[1]}},
		      {x: "Disgust", value: ${emotionList[2]}},
		      {x: "Happy", value: ${emotionList[3]}},
		      {x: "Surprise", value: ${emotionList[4]}}
		      
		  ];

		  // create the chart
		  var chart = anychart.pie();

		  // set the chart title
		  chart.title("Emotion Count Analysis");

		  // add the data
		  chart.data(data);

		  // display the chart in the container
		  chart.container('container');
		  chart.draw();

		});
	</script>
	
	<form method="POST">
		<input type="hidden" name="video" value="${video}" />
		<br/><br/><br/>
		<div style="text-align:center">
		<label for="comment" style='font-size:15pt; font-family:arial;margin-right:25px'>Enter Comments</label>
		<input type="text" name="comments" id="comments" size="30" style='font-size:15pt; font-family:arial;margin-right:50px;height:36px'/>
		<input type="submit" value="Save Comments" style="font-size:15pt; font-family:arial; color:white; background-color:red; border:4px solid #A9A9A9; padding:5px; width:200px" formaction="${pageContext.request.contextPath}/saveComments"/>
		<input type="submit" value="Go to HomePage" style="font-size:15pt; font-family:arial; color:white; background-color:red; border:4px solid #A9A9A9; padding:5px; width:200px" formaction="${pageContext.request.contextPath}/"/>
	</div>
	</form>
	<footer>
		</br></br>
		  <img src="${pageContext.request.contextPath}/resources/images/oracle_footer.png" alt="oracle_copyright" class="expand" style="width:1300px;height:40px;"/>
		</footer>
</body>
</html>
