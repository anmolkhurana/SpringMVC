<%@ include file = "common/header.jspf" %>
<%@ include file= "common/navigation.jspf" %>
<div class="container">
<p><font color="red">${errorMessage}</font></p>
<form action="login" method="POST">
		Name : <input class="form-control" name="name" type="text" /> 
		Password : <input class="form-control" name="password" type="password" />
		<input class="btn btn-success" type="submit"/>
	</form>
</div>
<%@ include file="common/footer.jspf"%>