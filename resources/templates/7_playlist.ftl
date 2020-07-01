<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <title>👋</title>
</head>
<body>
    <div class="content">
        <img src="/static/images/zune.png" />
        <p>It's definitely Chris's Zune. Look at this Donatello on the back!
        </p>
        <img src="/static/images/donatello_sewer.png" />
        <p>
        That's a blatant
        example of copyright infringement if I've ever seen one.
        And in New Zealand, no less!
        I guess Chris is an
        international criminal now.
        </p>
        <p>
        I doubt that Chris normally carries a Zune, though. It must be another clue!
        Let's take a look at the songs on it.
        </p>
        <p>
        <#--
        <iframe src="https://open.spotify.com/embed/playlist/5TvayRvZ8QjQAaLJ7CSo93" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        -->
        <iframe src="https://open.spotify.com/embed/playlist/5TvayRvZ8QjQAaLJ7CSo93" width="560" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        </p>
        <p>
        It seems like a party playlist. There's a theme to these songs, but one of the tracks
        doesn't match the theme.
        </p>
        <p>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/videoseries?list=PLfZ07pyx9_ZDzCyZ1siZbcu5gydOaCyfn" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        </p>
        <p>
        Which song doesn't belong?
        </p>
        <#if error??>
            <p style="color:red;">${error}</p>
        </#if>
        <form action="/roadtrip" method="post" enctype="application/x-www-form-urlencoded">
        <span>
            <select id="tracks" name="tracks">
            <#list tracks as item>
                <option value="${item}">${item}</option>
            </#list>
            </select>
            <div class="col"><input type="submit" value="Submit" /></div>
            </span>
        </form>
    </div>
</body>
</html>