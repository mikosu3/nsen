<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="Nsenモニターお知らせ登録"/>
<tiles:put name="content" type="string">
<script type="text/javascript">
<!--
$(function(){
    <c:forEach var="e" varStatus="s" items="${myDataList}">
        $("#video${s.index + 1}").attr('placeholder', '動画番号を入力してください sm9');
    </c:forEach>
});
-->
</script>

<h3>お知らせ登録</h3>
<s:form>

<html:errors/>
  <table class="table table-striped table-responsive">
      <thead>
          <tr>
              <th>No</th>
              <th>動画</th>
              <th>お知らせ対象</th>
          </tr>
      </thead>
      <tbody>
    <c:forEach var="e" varStatus="s" items="${myDataList}">
          <tr>
              <td width="8"><div class="text-right">${s.index + 1}</div></td>
              <td width="70"><c:if test="${e != ''}"><img class="img-rounded" width="64" src="${e.thumbnailUrl}" title="${e.title}"></c:if></td>
              <td><html:text property="videos[${s.index}]" value="${e.video}" styleId="video${s.index + 1}" /></td>
          </tr>
    </c:forEach>
      </tbody>
      <tfoot>
          <tr>
              <td colspan="3"><input type="submit" name="update" value="　　登録　　"  class="btn btn-primary"></td>
          </tr>
      </tfoot>
  </table>
</s:form>
</tiles:put>
</tiles:insert>
