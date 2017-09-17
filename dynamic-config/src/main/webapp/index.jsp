<%@ page import="org.apache.commons.lang3.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: wqq
  Date: 17-9-16
  Time: 下午10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>组件测试页</title>
    <%@include file="app/common/include.jsp"%>
</head>
<body>
<h2>Reports using TreeGrid</h2>
<p>Using TreeGrid to show complex reports.</p>
<div style="margin:20px 0;"></div>
<table title="Reports using TreeGrid" class="easyui-treegrid" style="width:700px;height:250px"
       data-options="
				url: '${pageContext.request.contextPath}/index',
				method: 'get',
				rownumbers: true,
				showFooter: true,
				idField: 'id',
				treeField: 'region'
			">
    <thead frozen="true">
    <tr>
        <th field="region" width="200">Region</th>
    </tr>
    </thead>
    <thead>
    <tr>
        <th colspan="4">2009</th>
        <th colspan="4">2010</th>
    </tr>
    <tr>
        <th field="f1" width="60" align="right">1st qrt.</th>
        <th field="f2" width="60" align="right">2st qrt.</th>
        <th field="f3" width="60" align="right">3st qrt.</th>
        <th field="f4" width="60" align="right">4st qrt.</th>
        <th field="f5" width="60" align="right">1st qrt.</th>
        <th field="f6" width="60" align="right">2st qrt.</th>
        <th field="f7" width="60" align="right">3st qrt.</th>
        <th field="f8" width="60" align="right">4st qrt.</th>
    </tr>
    </thead>
</table>

</body>
</html>