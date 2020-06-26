<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <title>👋</title>
</head>
<body>
    <div class="content">
        <p>
        You’re right, it can’t possibly be his actual music. It must be another clue! Let's take a look at the songs on it.
        </p>
        <p>
        This seems like… some sort of party playlist? One of them doesn't belong with the others, though. Which one is that?
        </p>
        <p>
        <!--
        <iframe src="https://open.spotify.com/embed/playlist/5TvayRvZ8QjQAaLJ7CSo93" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        -->
        <iframe src="https://open.spotify.com/embed/playlist/5TvayRvZ8QjQAaLJ7CSo93" width="560" height="710" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        </p>
        <p>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/videoseries?list=PLfZ07pyx9_ZDzCyZ1siZbcu5gydOaCyfn" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
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