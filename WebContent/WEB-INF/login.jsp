<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Login for Web Service</title>


 <link rel="stylesheet" href="css/foundation.css">

  <!-- This is how you would link your custom stylesheet -->
  <link rel="stylesheet" href="css/app.css">
   <link rel="stylesheet" href="css/login.css" />

</head>


<jsp:include page="error-list.jsp" />
<body>
  <br/>
  <br/>
  <br/>



<section class="login">
  <div class="titulo">Login</div>
  <form method="post" action="login.do">
      <input type="text" name="userName" required title="Username required" placeholder="Username" data-icon="U">
        <input type="password"  name="password" required title="Password required" placeholder="Password" data-icon="x">
        <div class="olvido">
          <div class="col"><a href="signin" title="Sign in with Twitter">Sign In with twitter</a></div>
            <div class="col"><a href="register.do" title="Register a user">Register</a></div>
        </div>
        
        <input class="enviar" type="submit" name="button"
				value="Login" />
        
    </form>
</section>
     
   </body>
   
<jsp:include page="template-bottom.jsp" />

    


