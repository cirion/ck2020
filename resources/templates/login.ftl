<html>
<head>
    <link rel="stylesheet" href="/static/styles.css">
    <link rel="apple-touch-icon" sizes="180x180" href="/static/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/static/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/static/favicon-16x16.png">
    <link rel="manifest" href="/static/site.webmanifest">
</head>
<body>
<#if error??>
    <p style="color:red;">${error}</p>
</#if>
<form action="/login" method="post" enctype="application/x-www-form-urlencoded">
    <div>User:</div>
    <div><input type="text" name="username" /></div>
    <div>Password:</div>
    <div><input type="password" name="password" /></div>
    <div><input type="submit" value="Login" /></div>
</form>
</body>
</html>