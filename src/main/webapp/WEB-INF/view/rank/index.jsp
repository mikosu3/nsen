<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="Nsenモニターランキング"/>
<tiles:put name="content" type="string">

<c:if test="${rankItems.size() > 0 }">

<c:forEach var="rank" items="${rankItems}">
<div class="alert alert-success"><strong>${rank.channelName}</strong></div>
  <table class="table table-striped table-responsive">
      <thead>
          <tr>
              <th>No</th>
              <th>動画</th>
              <th>動画番号</th>
              <th>タイトル</th>
              <th>放映回数</th>
              <th>最終放映日</th>
          </tr>
      </thead>
      <tbody>
    <c:forEach var="e" varStatus="s" items="${rank.channelLogDataDtoItems}">
          <tr>
              <td width="8"><div class="text-right">${s.index + 1}</div></td>
              <td width="70"><img class="img-rounded" width="64" src="${e.thumbnailUrl}" title="${e.title}"></td>
              <td>${e.video}</td>
              <td>${e.title}</td>
              <td><div class="text-right">${e.onAirCnt} 回</div></td>
              <td><div class="text-center">${e.lastOnAir}</div></td>
          </tr>
    </c:forEach>
      </tbody>
  </table>
</c:forEach>
</c:if>
</tiles:put>
</tiles:insert>