<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>

<br><#if exists??>

    <h3 style="color:red"> User with this name already exists!!!</h3>

       </#if>

<br>

<form action="/registration" method="post">
    <label for="username"> Username</label>
    <input type="text" name="username" id="username">
    <br>
    <label for="password">Password</label>
    <input type="password" name="password" id="password">
    <br>
    <input type="submit" id="submit" value="Register">
</form>


</body>
</html>