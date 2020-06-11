<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <title>👣</title>
</head>
<body>
<div class="content">
        <p>${error}</p>
    <#if error??>
    </#if>

    <p>
    Well spotted! Yes, he must have taken those on the Routeburn Track on the South Island. If he's back online now,
    though, he must be off the trail and back in one of the small towns nearby. Where should we look for him?
    </p>
    <#if error??>
        <p style="color:red;">${error}</p>
    </#if>
    <form action="/tramp" method="post" enctype="application/x-www-form-urlencoded">
        <div class="col"><input type="text" name="answer" /></div>
        <div class="col"><input type="submit" value="Submit" /></div>
    </form>
</div>
</body>
</html>