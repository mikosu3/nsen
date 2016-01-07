<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/nonHaderLayout.jsp" flush="true">
<tiles:put name="title"  value="お探しのページは見つかりませんでした"/>
<tiles:put name="content" type="string">
<ul class="inline">
    <li><img src="${f:url('/img/error.png')}" /></li>
    <li>
        <div>
        <div class="alert alert-error">
            <h3>ページが見つかりません</h3>
        </div>
        <p>
           どこ見てるんですか<br />
           そんなページありませんよ！
        </p>
        <p><s:link href="/">TOPに戻る</s:link></p>
        </div>
    </li>
</ul>

</tiles:put>
</tiles:insert>