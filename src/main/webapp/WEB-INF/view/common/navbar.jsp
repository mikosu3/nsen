    <nav class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <button type="button" class="btn btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse"><i class="icon-list"></i></button>
                <s:link href="/" title="Nsenモニター" styleClass="brand"><i class=" icon-eye-open"></i> Nsen Monitor</s:link>
                <c:if test="${userName != ''}">
                    <div class="nav-collapse collapse">
                        <ul class="nav nav-pills pull-right">
                            <li><a><img src="http://www.paper-glasses.com/api/twipi/${userName}/mini" width="15" class="img-rounded" > ${userName}</a></li>
                            <li><s:link href="/top"><i class="icon-white  icon-user"></i> マイページ</s:link></li>
                            <li><s:link href="/logout"><i class="icon-white icon-off"></i> ログアウト</s:link></li>
                        </ul>
                </div>
                </c:if>
                <c:if test="${userName == ''}">
                    <div class="nav-collapse collapse">
                        <ul class="nav nav-pills pull-right">
                            <li><s:link href="/search"><i class="icon-white icon-search"></i> 検索</s:link></li>
                            <li><s:link href="/rank"><i class="icon-white icon-flag"></i> ランキング</s:link></li>
                            <li><s:link href="/login"><i class="icon-white icon-share"></i> ログイン</s:link></li>
                        </ul>
                </div>
                </c:if>
            </div>
        </div>
    </nav>