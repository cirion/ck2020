<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <#include "/common/rocksteady.ftl">
    <title>🏠️</title>
</head>
<body>
<div class="content">
    <p>
    Well spotted! Yes, he took those on the Routeburn Track in the South Island.
    </p>
    <p>
    If he's back online now,
    though, he must be off the trail and hiding in one of the small towns nearby.
    </p>
    <p>
    Where should we look for him?
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