<%@include file = "common/header.jspf" %>

<div class = "container">
    <h1>Welcome to the Login Page!!</h1>
    <hr>
    <pre>${errorMessage}</pre>
    <form action="" method="post">
              Name : <input type="text" name="name"> <br>
              Password : <input type="password" name="password"><br>
              <input type="submit">
    </form>
</div>

<%@include file = "common/footer.jspf" %>
