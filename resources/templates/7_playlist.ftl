<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <title>👋</title>
</head>
<body>
    <div class="content">
        <p>
        You’re right, it can’t possibly be legit. It must be another clue!
        </p>
        <p>
        This seems like… some sort of party playlist? One of these songs doesn’t seem to belong with the others, though. Do you know which one I mean?
        </p>
        <p>
        <iframe src="https://open.spotify.com/embed/playlist/5TvayRvZ8QjQAaLJ7CSo93" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        </p>
        <#if error??>
            <p style="color:red;">${error}</p>
        </#if>
        <form action="/roadtrip" method="post" enctype="application/x-www-form-urlencoded">
            <select id="tracks" name="tracks">
            <#list tracks as item>
                <option value="${item}">${item}</option>
            </#list>
            </select>
            <div class="col"><input type="submit" value="Submit" /></div>
        </form>
    </div>
</body>
</html>