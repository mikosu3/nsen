<!doctype html>
<tiles:insert page="/WEB-INF/view/common/common.jsp" />
<html lang="ja">
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width, maximum-scale=1.0, minimum-scale=0.5,user-scalable=yes,initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>
<tiles:insert page="/WEB-INF/view/common/inc.jsp" />
</head>
<body>
<tiles:insert page="/WEB-INF/view/common/navbar.jsp" />

<div class="container">
    <div class="row">
        <div class="span12">
            <tiles:insert attribute="content" />
        </div>
    </div>
</div>

<tiles:insert page="/WEB-INF/view/common/footer.jsp" />
</body>
</html>