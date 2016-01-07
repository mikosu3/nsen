<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/nonHaderLayout.jsp" flush="true">
<tiles:put name="title"  value="エラーページ"/>
<tiles:put name="content" type="string">
   <h3 class="light">エラー発生</h3>
   <p class="alert alert-info">何らかのエラーが発生しました。問題が解決シない場合は管理者に連絡してください。</p>
   <img src="${f:url('/img/error.png')}" />
</tiles:put>
</tiles:insert>
