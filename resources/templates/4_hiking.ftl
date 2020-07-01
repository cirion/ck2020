<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <#include "/common/bebop.ftl">
    <title>⛰️</title>
</head>
<body>
<div class="content">
    <p>
I’ve been poring over <a href="https://photos.app.goo.gl/CPVuP2Ssn6jxCaZ56">these pictures</a>.
What country do you think he is in?
    </p>
    <#if error??>
        <p style="color:red;">${error}</p>
    </#if>
    <form action="/carmensandiego" method="post" enctype="application/x-www-form-urlencoded">
        <div class="col"><input type="text" name="answer" /></div>
        <div class="col"><input type="submit" value="Submit" /></div>
    </form>
</div>
</body>
</html>