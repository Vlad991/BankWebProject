<html>
<head>
    <title>My Bank</title>
    <link rel="stylesheet" href="view/css/style.css">
</head>
<body>
    <h1>Welcome to our bank!</h1>
    <form action="/mybank/login">
        <input type="text"required placeholder="Login" name="existing_login"/><br>
        <input type="password"required placeholder="Password" name="existing_password"/><br>
        <input type="submit" name="login_button" value="Log in">
    </form>
    <form action="/mybank/register">
        <input type="text"required placeholder="Your new login" name="new_login"/><br>
        <input type="submit" name="registration_button" value="Register">
    </form>
</body>
</html>
