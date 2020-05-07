<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>权限菜单管理</title>
    <style>
        .PublicShow{display: none;}
        .PublicShow h4{width:100%;height:35px;border-bottom:1px solid #ccc; line-height: 35px;text-indent: 10px;}
        .PublicShow .nameInput{width:120px;border-radius:5px;border: 1px solid #ccc;height:28px;text-indent: 5px;}
        .PublicShow table{ width:98%; height:auto; font-family: verdana,arial,sans-serif;  font-size:11px;margin: 0 auto; margin-top:15px; color:#333333;border-width: 1px;  border-color: #666666;  border-collapse: collapse;}
        .PublicShow table tr td{color:#5C5A5A;  word-break: break-all; height:40px; font-size:14px;  border-width: 1px; line-height: 40px;   text-indent:10px; border-style: solid;  border-color: #dddddd;}
        .PublicShow span{display: inline-block;width:100px;text-align: right;}
    </style>
</head>
    <body>

        <!--顶部导航-->
        <div class="publicHeaderNav">
<%--            <ul>--%>
<%--                <li>--%>
<%--                    <a id="SettingReturn">用户管理</a>--%>
<%--                    <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--                    <a>权限菜单管理</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
        </div>
        <!--顶部导航 end-->
        <div class="clear"></div>
        <!--公用导航模块-->
        <div class="publicNavMain">


            <form id="searchForm" name="searchForm" action="settings/permissionsMenu.json">

                <div class="projectGoodsFormLeft">
                    <div class="newPublicCustomGoodsBtn" onclick="addPermission()"><img src="newassets/imgs/icon_new_add.png"><a>新增</a></div>

                    <input id="descr" name="descr" class="newPublicInput" type="text" placeholder="权限名称">

                    <select id="funcType" name="funcType" class="newPublicSelect publicSelectSwitchTimeProject">
                    </select>
                    <select id="parentId" name="parentId" class="newPublicSelect publicSelectSwitchTimeProject">
                    </select>
                </div>

                <div class="projectGoodsFormRight">
                    <div class="newPublicCustomSearchBtn" onclick="query()"><img src="newassets/imgs/icon_new_search_btn.png"><a>查询</a></div>
                    <div class="newPublicCustomSearchBtn" onclick="reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>
                </div>
            </form>

        </div>
        <!--公用导航模块end-->
        <!--公用表格模块-->
        <div class="newPublicTable">

            <table>
                <colgroup>
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="80">
                    <col width="80">
                    <col width="80">
                    <col width="40">
                    <col width="80">
                    <col width="240">
                </colgroup>
                <tbody><tr>
                    <th class="newPublicTableCenter">名称</th>
                    <th class="newPublicTableCenter">菜单类型</th>
                    <th class="newPublicTableCenter">上级菜单</th>
                    <th class="newPublicTableCenter">URL地址</th>
                    <th class="newPublicTableCenter">css图标</th>
                    <th class="newPublicTableCenter">编码</th>
                    <th class="newPublicTableCenter">排序值</th>
                    <th class="newPublicTableCenter">创建日期</th>
                    <th class="newPublicTableCenter">操作</th>
                </tr></tbody>
                <tbody id="menuTable"></tbody>

            </table>
            <div class="pageBar" id="pageBar">
            </div>

        </div>
        <!--公用表格模块end-->

    </body>
</html>

<!--弹出编辑页面-->
<div class="InformationShow PublicShow" style="display: none;">
    <h4>基本信息</h4>
    <form id="updateFuncForm" name="updateFuncForm" action="settings/updatePermissionMenu.json">
        <table>
            <tr>
                <td>
                    <i><img src="newassets/imgs/Required.png">权限名称：</i>
                    <input id="permissionName" name="permissionName" class="nameInput" type="text">
                </td>
                <td>
                    <i><img src="newassets/imgs/Required.png">权限类型：</i>
                    <select id="editFuncType" name="funcType" class="selectName" onclick="selectFuncType">
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <i>上级分类：</i>
                    <select id="editParentId" name="parentId" class="selectName" style="  width: 120px;"></select>
                </td>
                <td>
                    <i><img src="newassets/imgs/Required.png">排序值：</i>
                    <input id="menuSort" name="menuSort" class="nameInput" type="text">
                </td>
            </tr>
            <tr>
                <td>
                    <i>url地址：</i>
                    <input id="url" name="url" class="nameInput" type="text">
                </td>
                <td>
                    <i>权限编码：</i>
                    <input id="charset" name="charset" class="nameInput" type="text">
                    <label>(慎重修改)</label>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <i>菜单图标css:</i>
                    <input id="cssIcon" name="cssIcon" class="nameInput" type="text">
                    <label>(引用icon.css文件中的css 风格)</label>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="hidden" id="id" name="id" />
                    <input type="button" class="MenuBtn" onclick="saveOrUpdatePermission()" value="保存">
                </td>
            </tr>
        </table>

    </form>
</div>

<script type="text/javascript">
    var functypelist=[{code:0,desc:"功能权限非菜单"},{code:1,desc:"主菜单"},{code:2,desc:"右键命令菜单"},
        {code:3,desc:"地图工具"},{code:4,desc:"移动端权限"},{code:5,desc:"终端命令工具栏"},
        {code:6,desc:"顶部快捷菜单"},{code:7,desc:"前端功能权限非菜单"},{code:8,desc:"APP客户端菜单"}];
    $(document).ready(function(){
        Page.showPageTitleBindReturnUrl();
       // fillSelectOptData("parentId", {queryID:"selectRootMenus"});
       // fillSelectOptData("funcType", {category:"FunctionType", selectedValue:"${entity.funcType}"});
        initParentKinds(-1);
        initOptionValue("funcType",functypelist);
        initOptionValue("editFuncType",functypelist);
        returnfn();
        ajaxGet("settings/permissionsMenu.json", function(resp){
            loadPermissions(resp);
        });
    });

    function query(){
        var formData = P.formData(document.searchForm);
        ajaxPost("settings/permissionsMenu.json", formData, function(resp){ loadPermissions(resp);});
    }

    function reset(){
        $("#descr").val("");
        $("#funcType").val("");
        $("#parentId").val("");
    }

    function saveOrUpdatePermission(){
        if(validatePermissionForm()){//base.js
            var formData = P.formData(document.updateFuncForm);
            var action = "settings/saveOrUpdatePermission.json";
            var id = $("#id").val();
            ajaxPost(action, formData, function(result){
                if(result.success == true){
                    ajaxGet("settings/permissionsMenu.json", function(resp){ loadPermissions(resp); });
                }else{
                    selfAlert("操作失败! " + result.message);
                }
                return;
            });
        }
    }

    function deletePermission(id){
        selfConfirm("你确定要删除吗？", function () {
            $.getJSON("settings/deletePermissionMenu.json?id="+id, function(result){
                if (result.success == true) {
                    ajaxGet("settings/permissionsMenu.json", function(resp){ loadPermissions(resp); });
                    selfAlert('提示',"删除成功!");
                } else {
                    selfAlert("无法删除! " + result.message);
                }
            });
        });
    }

    function loadPermissions(data){
        $("#menuTable").empty();
        var html = "";
        if(data.page.list.length <= 0){
            html = "<tr><td colspan='9' align='center'>暂无信息！</td></tr>";
            $("#menuTable").html(html);
            $("#pageBar").hide();
        }else {
            $.each(data.page.list, function (index, item) {
                var pname = item.parentName;
                var sdesc = "添加货主该权限";
                var cdesc = "添加物流公司该权限";
                var soperType = "1";
                var coperType = "1";
                var scolor = "#0071db";
                var ccolor = "##0071db";
                var simage = "newassets/imgs/icon_new_plan.png";
                var cimage = "newassets/imgs/icon_new_plan.png";
                if (item.sisSet) {
                    sdesc = "解除货主该权限";
                    soperType = 2;
                    scolor = "#eb385e";
                    simage = "newassets/imgs/icon_new_close.png";
                }
                if (item.cisSet) {
                    cdesc = "解除物流公司该权限";
                    coperType = 2;
                    ccolor = "#eb385e";
                    cimage = "newassets/imgs/icon_new_close.png";
                }
                if (typeof pname == "undefined") {
                    pname = "-";
                }
                html += '<tr>' +
                    '<td class="name">' + item.modelDesc + '</td>' +
                    '<td class="menuType">' + getFuncTypeDesc(item.funcType) + '</td>' +
                    '<td class="menu" align="center">' + pname + '</td>' +
                    '<td class="addUrl">' + (item.url ? item.url : "&nbsp;") + '</td>' +
                    '<td class="cssIcon">' + (item.icon ? item.icon : "&nbsp;") + '</td>' +
                    '<td class="charset">' + (item.modelName ? item.modelName : "&nbsp;") + '</td>' +
                    '<td class="number">' + item.menuSort + '</td>' +
                    '<td class="time">' + P.long2Datetime(item.createTime) + '</td>' +
                    '<td>' +
                    '<ul class="new_icon_pic">' +
                    '<li onclick="editPermission(' + item.id + ')"><img src=\'assets/imgs/operate/icon_edit.png\'/><span>编辑</span></li>' +
                    '<li onclick="deletePermission(' + item.id + ')"><img src=\'assets/imgs/operate/icon_delete.png\'/><span  style="color: #ff5176;">删除</span></li>' +
                    '<li itemid="' + item.id + '" roleType="2" operType="' + soperType + '" class="bingding"><img src="' + simage + '"><span  style="color:' + scolor + '">' + sdesc + '</span></li>' +
                    '<li itemid="' + item.id + '" roleType="3" operType="' + coperType + '" class="bingding"><img src="' + cimage + '"><span style="color:' + ccolor + '">' + cdesc + '</span></li>' +
                    '</ul>' +
                    '</td>' +
                    '</tr>';
                $("#menuTable").html(html);
                tableBgChanger();
            });

            //添加权限
            $("li.bingding").click(function () {
                var curNode = $(this);
                var itemId = curNode.attr("itemId");
                var roleType = curNode.attr("roleType");
                var operType = curNode.attr("operType");

                selfConfirm("你确定要"+(operType==1?"添加":"解除")+"该角色该权限吗？", function () {
                    $.getJSON("settings/addOrDelAuth.json", {
                        itemId: itemId,
                        roleType: roleType,
                        operType: operType
                    }, function (data) {
                        if (data.success == false) {
                            return;
                        }
                        var curspan = curNode.find("span")[0];
                        var curimg = curNode.find("img")[0];
                        var roleType = curNode.attr("roleType");
                        var operType = curNode.attr("operType");
                        var desc = "";
                        var curcolor = "";
                        var curimage = "";
                        if (operType == 1) {
                            desc = "解除";
                            curcolor = "#eb385e";
                            curimage = "newassets/imgs/icon_new_close.png";
                            curNode.attr("operType", 2);
                        } else if (operType == 2) {
                            desc = "添加";
                            curcolor = "#0071db";
                            curimage = "newassets/imgs/icon_new_plan.png";
                            curNode.attr("operType", 1);
                        }
                        if (roleType == 2) {
                            desc += "货主该权限"
                        } else if (roleType == 3) {
                            desc += "物流公司该权限"
                        }
                        curspan.innerHTML = desc;
                        $(curimg).attr("src", curimage);
                        $(curspan).css("color", curcolor);

                    }.bind(this));
                });


            });

            //总条数、页条数、当前页、搜索表单、分页按钮点击ajax请求后回调函数
            //document.searchForm 必须要有 action，不然点击分页无法发送请求
            $("#pageBar").show();
            ajaxPageBar(data.page.totalResults, data.page.limit, data.page.pageNumber, document.searchForm, loadPermissions);
        }
    }

    // function fillSelectOptData(id, params){
    //     var selectObj01 = $("#"+id);
    //     selectObj01.empty();
    //     id = id.substring(0,1).toUpperCase()+id.substring(1);
    //     var selectObj02 = $("#edit"+id);
    //     selectObj02.empty();
    //     ajaxGetData(selectObj01, selectObj02, params);
    // }
    //
    // function ajaxGetData(obj1, obj2, params){
    //     $.get("basicData.json", params,  function(result){
    //         if(result.success == false){
    //             return;
    //         }
    //         result = result.data;
    //         initOptionValue(obj1, result);
    //         initOptionValue(obj2, result);
    //     });
    // }

    function initOptionValue(selectObj, result){
        var html = "<option value=''>-请选择-</option>";
        var defaultVal = "";
        for(var i=0;i<result.length;i++){
            var itemVal = result[i];
            html += "<option value='" + itemVal.code + "'>";
            html += itemVal.desc + "</option>"
        }
        $("#"+selectObj).append(html);
    }

    function getFuncTypeDesc(functype){
        for(var i=0;i<functypelist.length;i++){
            var f=functypelist[i];
            if(f.code==functype){
                return f.desc;
            }
        }
    }

    //上级分类初始化
    function initParentKinds(funcType){
        $.getJSON("settings/parentFkhuserrolemodelList.json?funcType="+funcType, function(result){
            if(result.success == false){
                return;
            }
            var parentModels=result.data;
            if(!isNull(parentModels)){
                var selectHtml="<option value=''>-请选择-</option>";
                $("#editParentId").empty();
                $("#parentId").empty();
                for(var index=0;index<parentModels.length;index++){
                    selectHtml+="<option value ='"+parentModels[index].id+"'>"+parentModels[index].modelDesc+"</option>";
                }

                $("#editParentId").html(selectHtml);
                $("#parentId").html(selectHtml);
            }
        });
    }


</script>
