<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <#include "/common/turtle1.ftl">
    <title>👋</title>
</head>
<body>
    <div class="content">
        <p>
        <img src="/static/images/king_brown_eyes.png" />
        </p>
        <p>
        Thanks for celebrating this special day with me. I had a blast, and look forward to seeing
        everyone again in person when the time is right!
        </p>
        <#if error??>
            <p style="color:red;">${error}</p>
        </#if>
    </div>
</body>
</html>