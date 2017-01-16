<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="dao.BookDao"%>
<%@page import="model.Books"%>
<%@page import="controller.UpdateBook"%>
<!DOCTYPE html>
<html>
<head>
	<title>Admin Update</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="default.css" />
</head>	
<body>
<%!
int counter=0; 
ArrayList<Books> history=null;
ArrayList<String> genres = null;
String genre;
BookDao bookDao=new BookDao();
%>
	<div class="page">
		<form action="UpdateBook" method="post">
			<select name="genre" class="genre-select" onchange="this.form.submit()">
			<%
			counter=1;
			genre =(String)session.getAttribute("selectedGenre");
			genres = bookDao.getGenreMapping();
			
			while(counter < genres.size()) 
			{
				if(genres.get(counter).equals(genre))
				{
				%>
					 <option value="<%=genres.get(counter)%>" selected><%=genres.get(counter) %></option>
				<%
				}
				else
				{
				%>
					 <option value="<%=genres.get(counter)%>"><%=genres.get(counter) %></option>
				<%
				}
			
			counter++;
			}%>
			</select>
			</form>
			<div class="books">
			<%
			history=new ArrayList<Books>();
			history=(ArrayList<Books>)session.getAttribute("history");
			
			if(history==null || history.size()==0)
				{
					out.println("No Books to display");
				}
				else
				{
					
					for(int i=0;i<history.size();i++) 
					{
						%>
		
				<div class="book-details">
					<h2><%=history.get(i).getTitle()%></h2>
					<p class="description"><%=history.get(i).getDescription()%></p>
					<form id="form-<%=i %>" action="ProcessInput" method="get">
					<div class="controls">
						<div>
						    <input type="text" id="hidden" name="hidden" value="<%=history.get(i).getBookid()%>"/>
							<input type="submit"  name="btn-submit" class="btn btn-default" value="update"  onclick="return sendInput(this.value, <%=i%>);"> 
							<input type="submit"  name="btn-submit" class="btn btn-default" value="view"   onclick="return sendInput(this.value, <%=i%>);"> 
							<input type="submit"  name="btn-submit" class="btn btn-default" value="delete"   onclick="return sendInput(this.value, <%=i%>);">
						</div>
					</div>
					</form>
				</div>
				<%
					}
				}
				%>
			</div>
	</div>
	<script>
		function sendInput(val,index) {
			console.log(index);
			var form = document.getElementById("form-"+index);
			console.log(form);
		    bookTitle = form.getElementsByTagName("input")[0].value;
		    console.log(bookTitle);
			form.getElementsByTagName("input")[0].value = val + "-" + bookTitle;
			return true;
		}
	</script>
</body>
</html>