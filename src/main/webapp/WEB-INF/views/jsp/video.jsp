<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      
      <style>
         h1 {
             font-family: verdana;
             text-decoration: underline;
             text-align: center;
         }
         td {
                font-family: arial;
                font-size: 20px;
            }
         input[type=text]:focus {
                background-color: lightblue;
            }
         fieldset { 
                padding-top: 5em;
                padding-bottom: 5em;
                border: 10px groove;
                vertical-align: baseline;
            }
        footer {
                position: absolute;
                right: 0;
                bottom: 0;
                left: 0;
                padding: 1rem;
            }
		form {
				text-align: center;
			}	
      </style>

      <title>New Video Upload</title>
   </head>

   <body>

      <script>
         function copyFileNameToLink(f) {
           if(f.file.value != "") {
            var path = f.file.value;
             var filename = path.replace(/^.*\\/, "");
             f.link.value = filename;
           }
         }
      </script>


      <h1>New Video Upload</h1>
      <br/><br/>

      <form:form method = "POST" action="${pageContext.request.contextPath}/video" enctype="multipart/form-data" modelAttribute="video" align="center">
       <fieldset>
         <table align="center" >
            
            <tr>
               <td><form:label path = "title">Title</form:label></td>
               <td><form:input path = "title" required="required"/></td>
            </tr> 
            
            <tr>
               <td><form:label path = "recruiter">Recruiter</form:label></td>
               <td><form:input path = "recruiter" required="required"/></td>
            </tr> 
            <tr>
               <td><form:label path = "candidate">Candidate</form:label></td>
               <td><form:input path = "candidate" required="required"/></td>
            </tr> 
            <tr>
               <td><label for="selectFile">Select a file (~50MB)</label> </td>
               <td><input type="file" name="file" accept="video/mp4,video/x-m4v,video/*,image/*" onchange="copyFileNameToLink(this.form)" required="required"/> </td>
            </tr> 
            <form:input type="hidden" name="link" path = "link" />

         </table> 

         <br/><br/>
         <input type = "submit" value = "Upload" align="center" style="font-size:15pt; font-family:arial; color:white; background-color:red; border:4px solid #A9A9A9; padding:5px; width:200px"/>
       
       </fieldset>
      </form:form>

   <footer>
      </br></br>
        <img src="${pageContext.request.contextPath}/resources/images/oracle_footer.png" alt="oracle_copyright" class="expand" style="width:1300px;height:40px;"/>
      </footer>   
   </body>
</html>
