<!-- Graphic design is my passion. -->
<html>
<head>
    <#include "/common/head.ftl">
    <title>👋</title>
</head>
<body>
    <div class="content">
        <p>
        You made it!
        </p>
        <p>
        I was born in 1980, the same year the arcade game Pac-Man was released. It would go on to become the best-selling arcade game of all time!
        </p>
        <p>
        I have fond memories of playing Pac-Man as a kid. My favorite arcade game, though, was Teenage Mutant Ninja Turtles: Turtles in Time. I always wanted to be Donatello!
        </p>
        <p>
        <img src="/static/images/donatello_bo.jpg" />
        </p>
        <p>
        Teenage Mutant Ninja Turtles taught me many valuable lessons, such as the importance of friendship, the deliciousness of pizza, and the radness of skateboards. Also to be careful of time travel! In fact, I’d better make sure that YOU aren’t traveling through time now, trying to mess up history like Splinter did.
        </p>
        <p>
        I need to know that I’m talking with the real you in the present, and not some evil robot from the past! So, here’s something that only present-day you would know:
        </p>
        <p>
        <img src="/static/images/spoon_cherry2.jpg" />
        </p>
        This statue was erected in a Midwestern city in the same year I moved there. What is TODAY’S headline in this city’s newspaper?
        <p>
        <iframe src="https://open.spotify.com/embed/playlist/5TvayRvZ8QjQAaLJ7CSo93" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
        </p>
        <p>3</p>
        <p>
        <img src="/static/images/donatello_standing.webp" />
        </p>
        <p>4</p>
        <#if error??>
            <p style="color:red;">${error}</p>
        </#if>
        <form action="/login" method="post" enctype="application/x-www-form-urlencoded">
            <div class="col"><input type="text" name="answer" /></div>
            <div class="col"><input type="submit" value="Submit" /></div>
        </form>
        <iframe class="game" src="https://ckforty.web.app/" />
    </div>
</body>
</html>