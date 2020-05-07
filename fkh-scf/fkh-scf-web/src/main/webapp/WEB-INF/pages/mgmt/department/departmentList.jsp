<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>组织架构</title>
</head>
<body>
<style>
    .planMsgAlert{   width: 100%; margin-top: 12px;    height: 275px;overflow-y: auto;}
    .planMsgAlert p{margin-left: 15px;}
    .planMsgAlert p:last-child{padding-bottom: 5px;}
    .planMsgAlert input[type="checkbox"]{position: relative;top:2px;    margin-right: 3px;}
</style>
<!--顶部导航 每个新的页面必须保留，用于显示顶部导航标题-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>运营管理</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>组织架构</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>
<!--顶部导航 end-->
<!--组织架构start-->
<div class="">

    <%--<div class="publicNavMain projectMgmtForm">--%>
        <%--<form action="company/listPage.json" name="reviewInvoiceForm" onsubmit="return searchReviewInvoices();" autocomplete="off">--%>
            <%--<input id="searchCompanyName" name="companyName" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入企业公司名称" style="width: 10%;">--%>
            <%--<select id="searchCompanyType" name="companyType" class="newPublicInput publicSwitchPlanProject" style="width: 10%;">--%>
                <%--<option value="">全部企业</option>--%>
                <%--<option value="1">资金方</option>--%>
                <%--<option value="2">借款方</option>--%>
            <%--</select>--%>
            <%--<input type="hidden" id="pageNo" name="pageNo" value="1">--%>
            <%--<div class="newPublicCustomSearchBtn" onclick="searchReviewInvoices()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>--%>
            <%--&lt;%&ndash;<div class="newPublicCustomResetBtn" onclick="document.reviewInvoiceForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>&ndash;%&gt;--%>
            <%--<div class="newPublicCustomGoodsBtn" onclick="turnToDetail('')"><img src="newassets/imgs/icon_new_add.png"><a>添加部门</a></div>--%>
            <%--<div class="newPublicCustomGoodsBtn" onclick="turnToDetail('')"><img src="newassets/imgs/icon_new_add.png"><a>添加人员</a></div>--%>
        <%--</form>--%>
    <%--</div>--%>



        <c:choose>
            <c:when test="${sessionUser.authMap.ORG_LIST == true}">
                <div class="tissueStructure">
                    <!--部门组织结构-->
                    <div class="sectorStructure" id="new_sectorStructure">
                        <h4 class="sectorTitle">部门结构</h4>
                        <ul id="sectorStructure" class="ztree"></ul>
                    </div>
                    <!--部门操作信息-->
                    <div class="operationMessage publicTable">
                        <div class="publicNavMain" id="new_operationMessage_header">
                            <form id="departmentForm" name="departmentForm" action="scfUser/listPage.json" onsubmit="return false;">
                                <input name="deptId" id="depId" type="hidden">
                                <input name="userId" id="departmentUserId" type="hidden">
                                    <%--<input type="hidden" id="userType" name="userType" value="21">--%>
                                <div class="operationMessage-head">
                                    <h4 id="sectorText" style="margin-top: -5px;text-indent: 15px;font-size:15px;"></h4>
                                    <div class="operationBtn" style="width: auto;margin-right: 10px;margin-top: 20px;">
                                        <input id="removeSector" style="background: #ff5176;" class="sectorPublicBtn delBtn btnRed" type="button" value="删除部门">
                                        <c:if test="${sessionUser.authMap.ORG_USER_ADD == true}">
                                            <input class="sectorPublicBtn addPersonsBtn shallowOrangeBg new_public_blue_bg" type="button" onclick="editDeptUser('add', null,'','',-1,0)" value="添加人员">
                                        </c:if>
                                        <c:if test="${sessionUser.authMap.ORG_DEPT_MGMT == true}">
                                            <input class="sectorPublicBtn createBtn shallowOrangeBg new_public_blue_bg" type="button" onclick="editSectorWin('创建部门', 'newsector')" value="创建部门">
                                            <input class=" sectorPublicBtn editSectorBtn btnDeepBlue new_public_blue_bg" type="button" onclick="editSectorWin('编辑部门','editsector')" value="编辑部门">
                                            <input class=" sectorPublicBtn createSonBtn btnOrange shallowOrangeBg new_public_blue_bg" type="button" onclick="editSectorWin('创建子部门', 'newchildsector')" value="创建子部门">
                                        </c:if>
                                        <img class="iconlinePic" src="newassets/imgs/icon-line.png">
                                        <input class="sectorPublicBtn btnOrange searchBtnTextName new_public_blue_bg" style="margin-top:0px;height:27px;" type="button" onclick="searchDepartmentData();" value="搜索">
                                        <input id="searchName" class="searchBtnTextNameInput newPublicAlertInput" type="text" name="keyword" placeholder="请输入姓名或角色名称" onkeydown="if(event.keyCode==13){searchDepartmentData();}" autocomplete="off">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!--人员信息表格-->
                        <div class="newPublicTable"  style="margin-top: 0px;" id="new_table_departmentMgmt">
                            <table style="margin-top:0px;">
                                <colgroup>
                                    <col>
                                    <%--<col>--%>
                                    <col>
                                    <col>
                                    <col>
                                        <%--<col>--%>
                                    <col width="230">
                                </colgroup>
                                <tr>
                                    <th style="text-align:left;">姓名</th>
                                    <%--<th style="text-align:center;">手机号码</th>--%>
                                    <th style="text-align:center;">登录帐号</th>
                                    <th style="text-align:center;">角色</th>
                                    <th style="text-align:center;width:150px;">注册时间</th>
                                        <%--<th style="text-align:center;width:150px;">最后登录</th>--%>
                                    <th>操作</th>
                                </tr>
                                <tbody id="roleListTable"></tbody>
                            </table>
                        </div>
                        <div id="rolePager" class="pagerones"></div>
                        <div id="departmentPageBar" class="pageBar">
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </c:when>
            <c:otherwise>
                <span>暂无权限!</span>
            </c:otherwise>
        </c:choose>
</div>


<!--组织架构end-->
<!--组织架构编辑与新增内容弹出 start-->
<div style="display: none;" class="publicTissue publicShow PublicShow">
    <form id="userForm" name="userForm" action="projects/saveOrUpdateDeptUser.json" autocomplete="off">
        <table>
            <tr>
                <td>
                    <i>用户姓名：</i>
                    <input id="userName" name="nickName" class="newPublicAlertInput" type="text" pattern="^[·.•．\u4e00-\u9fa5]+$" maxlength="20" placeholder="请输入用户姓名"/>
                </td>
                <td>
                    <i>登录帐号：</i>
                    <input id="loginAccount" name="username" class="newPublicAlertInput" type="text" maxlength="20" placeholder="请输入登录账号"/>
                </td>
            </tr>
            <tr>
                <td>
                    <i>设置角色：</i>
                    <select id="roleId" name="roleId" class="newPublicAlertSelect"></select>
                </td>
                <%--<td>--%>
                    <%--&lt;%&ndash;//shouji123&ndash;%&gt;--%>
                    <%--<i>手机号码：</i>--%>
                    <%--<input id="mobileNo" name="userMobileNo" class="newPublicAlertInput" type="text" maxlength="11" placeholder="请输入手机号码" pattern="^1[3|4|5|7|8|9][0-9]{9}$"/>--%>
                <%--</td>--%>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="positionMessage">
                        <ul id="personSector" class="ztree"></ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center" style="border:none;">
                    <input id="userId" name="id" type="hidden"/>
                    <%--<input name="userType" type="hidden" value="21"/>--%>
                    <input id="deptId" name="deptId" type="hidden"/>
                    <input id="btnSaveUser" name="btnSaveUser" class="sectorPublicBtn confirmBtn new_public_blue_bg" type="button" onclick="saveOrUpdateDeptUser()"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<!--组织架构编辑与新增内容弹出 end-->

<!--部门信息弹出 start-->
<div style="display: none;" class="sectorPublic PublicShow new_companyAlert_bxo">
    <form onsubmit="return false;" autocomplete="off">
        <p>
            <span class="create">创建部门：</span>
            <input id="messageText" class="newPublicAlertInput" type="text" placeholder="请输入部门名称" maxlength="20"/>
        </p>
        <p>
            <input id="confirmSubmit" class="new_public_blue_bg publicBtn " type="button" value="确定">
        </p>
    </form>
</div>

<%--<div class="showAlert PublicShow publicTissue" style="display: none;">
    <table>
        <tr>
            <td>
                <i>用户姓名：</i>
                <input id="userNameNew" name="userNameNew" class="newPublicAlertInput" value="123" type="text" pattern="^[·.•．\u4e00-\u9fa5]+$" maxlength="20"  placeholder="请输入用户姓名"/>
            </td>
            <td>
                <i>用户角色：</i>
                <input id="userRole" name="userRole" class="newPublicAlertInput" type="text" maxlength="11" placeholder="请输入登录账号"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="line-height: 5px;height: 276px; vertical-align: initial;">
                <div class="planMsgAlert"></div>
            </td>
        </tr>
    </table>
    <input type="button" style="display:block;margin:0 auto;margin-top: 15px;" class="newPublicAlertBtn new_public_blue_bg"  value="确定" onclick="muchSaveOrDeleteProjectOfUser()">
</div>--%>
<!--部门信息弹出 end-->
</body>
<script>
    /******* 组织架构start *********/
    $(document).ready(function(){
        Page.showPageTitleBindReturnUrl();
        departmentTreeMenu();
        initReady();
    });
    /*
    * 搜索人员
    * */
    function searchDepartmentData() {
        var projectSearch =  PF.projectValidate("searchName");
        if(projectSearch){

            var formData = P.formData(document.departmentForm);
            ajaxPost("scfUser/listPage.json", formData, function (resp) {
                loadDepartmentData(resp);
            });
        }
        return false;
    }
    /*
     * 加载表格数据
     * */
    function loadDepartmentData(resp){
        var result = resp.data;
        var html = "";
        // $("#depId").val(resp.data.depId);
        $("#departmentUserId").val(resp.data.userId);
        if(result.records.length <= 0){
            html = "<tr><td colspan='5' align='center'>暂无人员信息！</td></tr>";
            $("#roleListTable").html(html);
            $("#departmentPageBar").hide();
        }else{
            $("#departmentPageBar").show();
            $.each(result.records,function(index,item){
                html += '<tr>' +
                        '<td style="text-align:left;width: 180px">'+item.nickName+'</td>'+
                        // '<td style="width:150px;text-align: center;">'+(null == item.userMobileNo?"":item.userMobileNo)+'</td>'+
                        '<td style="width:150px;text-align: center;">'+(null == item.username?"":item.username)+'</td>'+
                        '<td style="width:200px;text-align: center;">'+(null == item.roleName?"":item.roleName)+'</td>'+
                        '<td style="width:120px;text-align: center;">'+P.long2Datetime(item.createTime)+'</td>'+
                        // '<td style="width:120px;text-align: center;">'+P.long2Datetime(item.lastLoginTime)+'</td>'+
                        '<td style="width:200px;">'+
                        '<ul class="new_icon_pic">';

                if ("${sessionUser.authMap.ORG_USER_EDIT}" == "true") {
                    html+='<li onclick="editDeptUser(\'edit\', null, \''+item.nickName+'\','+item.roleId+','+item.deptId+','+item.id+',\''+item.companyTel+'\',\''+item.username+'\')">' +
                          '<img src=\'assets/imgs/operate/icon_edit.png\'/><span>编辑</span></li>';
                }
                if ("${sessionUser.authMap.ORG_USER_DELETE}" == "true") {
                    html+='<li onclick="delDeptUser('+item.id+')"><img src="assets/imgs/operate/icon_delete.png"><span style="color: #ff5176;">删除</span></li>';
                }
                html += '</ul></td></tr>';
            });
            $("#roleListTable").html(html);
        }
        ajaxPageBarId("departmentPageBar", result.total, result.size, result.current, document.departmentForm, loadDepartmentData);
    }

    /*
    * 人员配置-批量保存或删除项
    * */
    /*function muchSaveOrDeleteProjectOfUser() {
        var projectIds="";
        $("input[name='projectOfSingleton']").each(function () {
            if($(this).prop("checked") && $(this).prop("disabled")!=true){
                projectIds+=$(this).attr("projectId")+"\("+$(this).attr("funcType")+"\)\,";
            }
        })
        if(projectIds==""){
            selfAlert("您沒有進行项目选择");
            layer.closeAll();
            return;
        }
        var userId= $("#userRole").attr("userId");
//        $.post("projects/muchSaveOrDeleteProjectOfUser.json?projectIds="+projectIds+"&userId="+userId, function (resp) {
        $.post("projects/muchSaveOrDeleteProjectOfUserNew.json?projectIds="+projectIds+"&byUserId="+userId, function (resp) {
            if(validateAjaxSuccess(resp)){
                selfAlert("添加成功");
                layer.closeAll();
            }

        })
    }*/

    /*
     * 删除人员
     * */
    function delDeptUser(userId){
        selfConfirm("你确定要删除该人员吗？", function () {
            $.getJSON("scfUser/delete/"+userId+".json", function(result){
                if (result.success == true) {
                    selfAlert("操作成功!", function(){
                        departmentTreeMenu();
                    });
                } else {
                    selfAlert("无法删除人员!" + result.message);
                }
            });
        });
    }

    /*
    * 保存新增与编辑
    * */
    function saveOrUpdateDeptUser(){
        if(editPersonValidate()){
            var formData = P.formData(document.userForm);
            formData.username = formData.username.toLowerCase();
            if (P.isNullOrEmptyString($("#userId").val()) || $("#userId").val() == 0) {
                formData.userPassword = MD5("888888");
            }
            ajaxPost("scfUser/saveOrUpdate.json", formData, function(result){
                if(result.success == true){
                    selfAlert("操作成功!",function(){
                        layer.closeAll();
                        clearShowBtnGroup();
                        $("#sectorText").html("");
                        $(".operationBtn").hide();
                        departmentTreeMenu('${CURRENT_LOGIN_USERID}');
                    });
                } else {
                    selfAlert("操作失败! " + result.message);
                }
            });
        }
    }

    function clearShowBtnGroup(){
        $("#new_operationMessage_header").hide();
        $("#new_table_departmentMgmt").css("marginTop","0px");
        $(".editSectorBtn").hide();
        $(".createSonBtn").hide();
        $(".delBtn").hide();
        $(".addPersonsBtn").hide();
        $(".createBtn").hide();
    }
    /********* 组织架构end**********/
</script>
</html>
