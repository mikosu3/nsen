<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/nonHaderLayout.jsp" flush="true">
<tiles:put name="title"  value="Nsenモニター"/>
<tiles:put name="content" type="string">
<div class="row">
    <div class="span2">
        <img src="${f:url('/img/logo.jpg')}" class="img-rounded" width="200" />
    </div>
    <div class="span10">
        <dl>
            <dt>このサイトについて</dt>
            <dd><a href="http://live.nicovideo.jp/watch/nsen/" target="_blank">Nsen</a>で流れた動画情報を蓄積し、ツイッターにてお知らせを行います。右上のログインボタンからログインしてご利用下さい。</dd>
        </dl>
    </div>
</div>
<div class="row">
    <div class="span10">
        <div class="alert alert-info"><strong>使い方</strong></div>
<script type="text/javascript" src="http://ext.nicovideo.jp/thumb_watch/sm23921985?w=600"></script><noscript><a href="http://www.nicovideo.jp/watch/sm23921985">【ニコニコ動画】Nsenを監視するアプリつくったったー</a></noscript>
    </div>
    <div class="span2">
    <div class="alert alert-info"><strong>総合情報</strong></div>
    <dl>
        <dt>登録件数</dt>
        <dd><fmt:formatNumber >${totalCnt}</fmt:formatNumber>件</dd>
        <dd>&nbsp;</dd>
        <dt>集計期間</dt>
        <dd>${start}</dd>
        <dd>&nbsp;&nbsp;&nbsp;から</dd>
        <dd>${end}</dd>
    </dl>
   </div>
</div>
<div class="row">
    <div class="span12">
        <c:if test="${rankItems.size() > 0 }">

        <div class="alert alert-info"><strong>人気動画</strong></div>
        <c:forEach var="rank" items="${rankItems}">
        <span class="label label-success"><strong>${rank.channelName}</strong></span>
          <table class="table table-striped table-responsive">
              <tbody>
            <c:forEach var="e" varStatus="s" items="${rank.channelLogDataDtoItems}">
                  <tr>
                      <td width="8"><div class="text-right">${s.index + 1}</div></td>
                      <td width="70"><img class="img-rounded" width="64" src="${e.thumbnailUrl}" title="${e.title}"></td>
                      <td>${e.title}</td>
                      <td><div class="text-right">${e.onAirCnt} 回</div></td>
                  </tr>
            </c:forEach>
              </tbody>
          </table>
        </c:forEach>
        </c:if>
    </div>
</div>
</tiles:put>
</tiles:insert>