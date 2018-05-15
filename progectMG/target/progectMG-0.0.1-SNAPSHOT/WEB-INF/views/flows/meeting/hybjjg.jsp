<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/nttags/nantian.tld" prefix="nt" %>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <title>会议表决结果</title>
  <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap/css/bootstrap.css">
  <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/queryframe.js"></script>
  <link type="text/css" rel="stylesheet" href="<%=path%>/js/date/jquery.ui.css">
   <%--<script type="text/javascript" src="<%=path%>/js/date/jquery-1.10.1.js"></script>--%>
  <script type="text/javascript" src="<%=path%>/js/date/jquery.ui.js"></script>
  <script type="text/javascript" src="<%=path%>/js/date/datecontroller.js"></script>
</head>
<script type="text/javascript">
  $(function () {
    $("table").attr("class", "well table resultTable table-hover");
    $("input,select").attr('class', 'input-block-level');
  });

  function closeit() {
    window.close();
  }

</script>
<body>
<div class="container-fluid">
  <div style="height:10px"></div>
  <div>
    <form id="addFrm">
      <div>
        <button type="button" onclick="closeit()">关闭</button>
      </div>
      <div align="center"><h4>会议表决结果</h4></div>
      <div>
        <div><h5>会议基本信息</h5></div>
        <table>
          <tr>
            <td>&nbsp;&nbsp;会议日期</td>
            <td><input type="text" id="hyrqDate" name="meetingdate"
                     value="<fmt:formatDate value="${meetingBase.meetingTime}" pattern="yyyy-MM-dd HH:mm"/>" readonly/>
            </td>
              <td style="width: 12%">会议地点</td>
              <td><input name="meetingLocation" type="text" value="${meetingBase.meetingAdress}" readonly/></td>
          </tr>
          <tr>
            <td>会议次数</td>
            <td><input type="text" name="meetingNumber" onblur="{if(!isinteger($(this).val())){alert('格式不正确');$(this).val('');}}" value="${meetingBase.meetingNumber}" readonly/>
            </td>
            <td>会议类型</td>
            <td>
              <input type="text" id="meetingType" value="<nt:codeValue index="${meetingBase.meetingType}" ctype="044"></nt:codeValue>" readonly/>
            </td>
          </tr>
        </table>
      </div>
      <div>
        <div><h5>会议人员信息</h5></div>
        <table>
          <tr>
            <td style="width: 12%">审批人</td>
            <td><input type="text" name="meetingExaminer" value="${meetingExaminer_CN}" readonly/></td>
          </tr>
          <tr>
            <td style="width: 12%">列席人员</td>
            <td><input type="text" name="meetingAttendee" value="${meetingBase.meetingObserver}" readonly/></td>
          </tr>
        </table>
      </div>
      <div>
        <div><h5>表决结果</h5></div>
        <div>
          <table >
            <thead>
            <th rowspan="1">序号</th>
            <th rowspan="1">议题名称</th>
            <th rowspan="1">表决人数</th>
            <th rowspan="1">同意票数</th>
            <th rowspan="1">续议票数</th>
            <th rowspan="1">否决人数</th>
            <th rowspan="1">最后结果</th>
            </thead>
            <tbody>
            <c:forEach var="item" items="${biaojuelist}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${item.issueName}</td>
                <td>${item.peopleNum}</td>
                <td>${item.tongyinum}</td>
                <td>${item.xvyinum}</td>
                <td>${item.foujuenum}</td>
                <td>
                  <c:choose>
                    <c:when test="${item.result==null}">
                      未表决
                    </c:when>
                    <c:when test="${item.result==1}">
                      同意
                    </c:when>
                    <c:when test="${item.result==2}">
                      续议
                    </c:when>
                    <c:when test="${item.result==3}">
                      否决
                    </c:when>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </form>
  </div>
  <script src="<%=path%>/js/bootstrap/js/bootstrap.js"></script>
</div>
</div>
</body>
</html>
