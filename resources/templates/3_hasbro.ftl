<html>
<head>
    <#include "/common/head.ftl">
    <#include "/common/krang.ftl">
    <title>👊</title>
</head>
<body>
<div class="content">
<img src="/static/images/hasbro_200.png" />
<p>${p1}</p>

<#if p2??>
<p>${p2}</p>
</#if>

<#if p3??>
<p>${p3}</p>
</#if>

<#if label??>
<form action="/knockknock" method="post" enctype="application/x-www-form-urlencoded">
    <input type="hidden" id="index" name="index" value="${index}">
    <div><input type="submit" value="${label}" /></div>
</form>

</#if>
</div>
</body>
