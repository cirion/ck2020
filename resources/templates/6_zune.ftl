<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <#include "/common/splinter.ftl">
    <title>👣</title>
</head>
<body>
<div class="content">
<p>${p1}</p>
    <form action="/brown" method="post" enctype="application/x-www-form-urlencoded">
        <input type="hidden" id="index" name="index" value="${index}">
        <div class="col"><input type="submit" value="${label}" /></div>
    </form>
</div>
</body>
</html>