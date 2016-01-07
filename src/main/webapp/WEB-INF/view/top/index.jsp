<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="Nsenモニターマイページ"/>
<tiles:put name="content" type="string">
<h3>登録動画一覧</h3>
<c:if test="${myDataList.size() == 0 }">
<div class="alert alert-info">
     <button type="button" class="close" data-dismiss="alert"></button>
     お知らせ対象登録から登録を行ってください。
</div>
</c:if>
<c:if test="${myDataList.size() > 0 }">
  <table class="table table-striped table-responsive">
      <thead>
          <tr>
              <th>No</th>
              <th>動画</th>
              <th>お知らせ対象</th>
              <th>タイトル</th>
              <th>放映回数</th>
              <th>最終放映日</th>
          </tr>
      </thead>
      <tbody>
    <c:forEach var="e" varStatus="s" items="${myDataList}">
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
</c:if>
</tiles:put>
</tiles:insert>