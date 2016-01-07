<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/nonHaderLayout.jsp" flush="true">
<tiles:put name="title"  value="システムエラー"/>
<tiles:put name="content" type="string">

<ul class="inline">
    <li><img src="${f:url('/img/error.png')}" /></li>
    <li>
        <div>
        <div class="alert alert-error">
            <h3>システムエラー</h3>
        </div>
            <p>
                べ、べつにエラーなんて起きてません！<br />
                起きてませんけど一応、管理者に連絡した方がいいと思います。
            </p>
            <p>別にエラーじゃないですけど一応！</p>

            <p><s:link href="/">TOPに戻る</s:link></p>
        </div>
    </li>
</ul>

</tiles:put>
</tiles:insert>