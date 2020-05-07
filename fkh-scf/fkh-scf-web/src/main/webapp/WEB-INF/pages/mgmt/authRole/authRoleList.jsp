<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
</head>
<body>
<!--角色管理列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>运营管理</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>角色管理</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>
<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div class="publicNavMain">

    <form id="roleForm" name="roleForm" action="authRole/listPage.json" onsubmit="return queryRoles();" onkeydown="if(event.keyCode==13){queryRoles();}" autocomplete="off">

        <input id="roleName" name="roleName" class="newPublicInput" style="width: 200px;" type="text" placeholder="请输入角色名称">
        <div class="newPublicCustomSearchBtn" onclick="queryRoles()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>
        <div class="newPublicCustomResetBtn" onclick="document.roleForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>
        <c:if test="${sessionUser.authMap.ROLE_ADD == true}">
            <div class="newPublicCustomSearchBtn" onclick="addRole()">
<%--                <img src="newassets/imgs/icon_new_add.png">--%>
                <a>新增角色</a></div>
        </c:if>
        <input type="hidden" id="rPageNo"/>
    </form>
</div>
<!--公用导航模块end-->
<!--公用表格模块-->
<div class="newPublicTable">
    <table>
        <colgroup>
            <col width="250">
            <col width="250">
            <col>
        </colgroup>
        <tbody>
            <tr>
                <th>角色名称</th>
                <th>备注信息</th>
                <th>操作</th>
            </tr>
        </tbody>
        <tbody id="roleManagerList"></tbody>
    </table>
    <div id="roleManagerListPageBar" class="pageBar">
    </div>
</div>
<!--公用表格模块end-->
<!--角色管理列表end-->
</body>
</html>



<!--角色管理编辑与新增内容弹出 start-->
<div class="rolePublicManage PublicShow" style="display: none;">
    <form>
        <table>
            <tr>
                <td width="140" id="test" align="right"><i>角色名称：</i></td>
                <td align="left"><input id="_roleName" name="_roleName" class="nameInput" maxlength="20" size="20" style="width: 200px;" placeholder="请输入角色名称" pattern="^[A-Za-z0-9\u4e00-\u9fa5-—]+$"/><span class="star"> </span></td>
            </tr>
            <tr>
                <td width="140" align="right"><i>备注信息：</i></td>
                <td align="left"><input id="_roleDesc" name="_roleDesc" class="nameInput" placeholder="请输入备注信息" maxlength="50" size="50" style="width: 200px;" pattern="^[A-Za-z0-9\u4e00-\u9fa5-—]+$"/><span class="star"> </span></td>
            </tr>
            <tr>
                <td align="right"><i>功能：</i></td>
                <td align="left">
                    <div class="companyManage">
                        <ul id="rolePerson" class="ztree"></ul>
                    </div>
                </td>
            </tr>
            <tr class="showTrHeight">
                <td colspan="4" align="center">
                    <input  id="_roleId" name="_roleId" type="hidden"/>
                    <input id="funcIds" name="funcIds" type="hidden"/>
                    <button  id="saveBtnShow" style="margin-top: 18px;" type="button" class="publicBtn new_public_blue_bg" onclick="saveRole()">保存</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<!--角色管理编辑与新增内容弹出 end-->


<script type="text/javascript">
    $(document).ready(function(){
        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        queryRoles();
    });

    /********** 角色管理start *************/
    function queryRoles(){
        if ("${sessionUser.authMap.ROLE_LIST}" == "false") {
            $("#roleManagerList").html("<tr><td colspan='3' align='center'>暂无权限！</td></tr>");
            $("#roleManagerListPageBar").hide();
            return false;
        }

        var projectSearch =  PF.projectValidate("roleName");
        if(projectSearch){
            $("#roleManagerList").empty();
            var formData = P.formData(document.roleForm);
            formData.pageNo = 1;
            ajaxPost("authRole/listPage.json", formData, function(resp){ loadRolesData(resp);});
        }
        return false;
    }

    function loadRolesData(resp){
        var html = "";
        var data = resp.data;
        if(data.records.length <= 0){
            html = "<tr><td colspan='3' align='center'>暂无角色信息！</td></tr>";
            $("#roleManagerList").html(html);
            $("#roleManagerListPageBar").hide();
        }else{
            $("#roleManagerListPageBar").show();
            $.each(data.records, function(index,item){
                html += '<tr>' +
                        '<td class="roleName">'+item.roleName+'</td>'+
                        '<td class="roleDesc">'+(null == item.roleDesc?"":item.roleDesc)+'</td>'+
                         '<td><ul class="new_icon_pic">';
                if(item.roleType == 6) {
                    if ("${sessionUser.authMap.ROLE_EDIT}" == "true") {
                        html += '<li onclick="editRole(this, '+item.id+',1, '+item.roleType+')" title="修改"><img src=\'assets/imgs/operate/icon_edit.png\'/><span>修改</span></li>';
                    }
                    if ("${sessionUser.authMap.ROLE_DELETE}" == "true") {
                        html += '<li onclick="deleteRole('+item.id+')" title="删除"><img src=\'assets/imgs/operate/icon_delete.png\'/><span style="color: #ff5176;">删除</span></li>';
                    }
                } else {
                    html += '<li onclick="editRole(this, '+item.id+',2, '+item.roleType+')" title="详情"><img src=\'assets/imgs/operate/icon_view.png\'/><span>详情</span></li>';
                }
                html += '</ul></td></tr>';
                $("#roleManagerList").html(html);
                tableBgChanger();
            });
            // 修复分页删除数据超过多页；删除最后一页的所有角色BUg
            $("#rPageNo").val(data.pageNumber);
            if(data.records.length<=1){
                $("#rPageNo").val(data.pageNumber-1);
            }
            ajaxPageBarId("roleManagerListPageBar", data.total, data.size, data.current, document.roleForm, loadRolesData);
        }
    }

    function deleteRole(roleId){
        selfConfirm("你确定要删除该角色吗？", function () {
            $.getJSON("authRole/delete/"+roleId+".json", function(result){
                if (result.success == true) {
                    selfAlert("删除角色成功", function(){
                        var formData = P.formData(document.roleForm);
                        ajaxPost("authRole/listPage.json", formData, function(resp){ loadRolesData(resp);});
                    });
                } else {
                    selfAlert("无法删除角色!" + result.message);
                }
            });
        });
    }

    function clearShowBtnGroup(){
        $(".editSectorBtn").hide();
        $(".createSonBtn").hide();
        $(".delBtn").hide();
        $(".addPersonsBtn").hide();
        $(".createBtn").hide();
    }
    /********** 角色管理end *************/
</script>
