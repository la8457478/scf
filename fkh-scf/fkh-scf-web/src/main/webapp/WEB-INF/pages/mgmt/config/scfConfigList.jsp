<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统配置管理</title>
</head>
<body>
<style>
    #oilSiteCompanyDiv p:first-child{padding-top: 2px !important;}
    .configOil{margin-top: 25px;}
    .configOil span{margin-left: 15px;}
    /*.configOil span:first-child{margin-left: 0px;font-size: 14px;width: 200px}*/
    .saveConfig{width: 120px;height: 30px;border-radius: 6px;background-color: #0071db;color: white;display: block;margin: 0 auto;line-height: 30px;text-align: center;margin-top: 15px;}
    .configOil a{display: inline-block;width: 100px;text-align: right;}
</style>
<!--单据复核列表-->
<!--顶部导航-->
<div class="publicHeaderNav">
<%--    <ul>--%>
<%--        <li>--%>
<%--            <a>管理菜单</a>--%>
<%--            <img src="newassets/imgs/icon_new_arrow_nav.png">--%>
<%--            <a>系统配置管理</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
</div>

<!--顶部导航 end-->
<div class="clear"></div>
<!--公用导航模块-->
<div id="heightPage" style="border: none; background: none;overflow-y: auto;overflow-x: hidden;">
    <div class="publicNavMain projectMgmtForm">
        <form action="scfConfig/page.json" name="scfConfigForm" onsubmit="return searchScfConfig();" autocomplete="off">
            <input type="hidden" id="page" name="page" value="1">
            <input id="configKeySearch" class="newPublicInput"  name="configKey"  type="text"  placeholder="请输入配置的key" style="width: 140px;"/>
            <input id="configDescSearch" name="configDesc" class="newPublicInput publicSwitchPlanProject" type="text" placeholder="请输入配置的描述" style="width: 140px;">
            <input style="width: 160px;" class="newPublicInput newPublicInputOne publicTimeShow" id="dateStartTime" onclick="$.jeDate('#dateStartTime',{trigger:false,isTime:true,multiPane:true,onClose:false,format:'YYYY-MM-DD hh:mm:ss',maxDate: $.nowDate({DD:0})})" name="beginTime" type="text" placeholder="请选择最后修改开始时间" readonly>
            <label class="newTmsTo publicTimeShow" style="margin-left:-5px;">至</label>
            <input style="width: 160px;margin-left: -5px;" class="newPublicInput newPublicInputOneTwo publicTimeShow"  id="dateEndTime" onclick="$.jeDate('#dateEndTime',{trigger:false,isTime:true,multiPane:true,onClose:false,format:'YYYY-MM-DD hh:mm:ss'})" name="overTime" type="text" placeholder="请选择最后修改结束时间" readonly>

            <div class="newPublicCustomSearchBtn" onclick="searchScfConfig()"><img src="newassets/imgs/icon_new_search_btn.png"><a>搜索</a></div>

            <div class="newPublicCustomResetBtn" onclick="document.scfConfigForm.reset()"><img src="newassets/imgs/icon_new_reset_btn.png"><a>重置</a></div>
            <div class="newPublicCustomGoodsBtn" onclick="editConfig('','')"><img src="newassets/imgs/icon_new_add.png"><a>新增配置</a></div>
        </form>
    </div>
    <!--公用导航模块end-->
    <!--公用表格模块-->
    <div class="newPublicTable">
        <table>
            <colgroup>
                <col >
                <col >
                <col >
                <col >
            </colgroup>
            <thead>
            <tr role="row">
                <th>配置KEY</th>
                <th>配置VALUE</th>
                <th>描述</th>
                <th width="100px">状态</th>
                <th width="200px" class="mobileHidden">创建时间</th>
                <th width="200px" class="mobileHidden">最后修改</th>
                <th width="130px">操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <tbody id="projectList"></tbody>
        </table>
        <div id="projectPageBar" class="pageBar"></div>
    </div>
    <!--公用表格模块end-->
</div>

<!--单据复核列表end-->

<!--编辑角色-->
<div class="publicContainerShow batchRecheckShowContainer configOilCompany" style="display:none;overflow: hidden;">
    <div class="remarksContainer newRemarksContainer">
        <form id="editConfigForm" name="editConfigForm">
            <input id="configId" type="hidden" name="id">
            <div class="configOil">
                <a>父级:</a>
                <span>
                    <select class="newPublicSelect newPublicSelect1 publicSelectSwitchTimeProject" id="parentId" name="parentId" style="width:50%  !important;">
                        <option value='0'>---请选择父级配置---</option>
                    </select>
                </span>
            </div>
            <div class="configOil">
                <a>配置Key:</a>
                <span>
                    <input id="configKey" class="designatedConsumption newPublicInput" name="configKey" type="text" value=""/>
                </span>
            </div>
            <div class="configOil">
                <a>配置Value:</a>
                <span>
                    <input id="configValue" class="designatedConsumption newPublicInput" name="configValue" type="text" value=""/>
                </span>
            </div>
            <div class="configOil">
                <a>配置描述:</a>
                <span>
                    <input id="configDesc" class="designatedConsumption newPublicInput" name="configDesc" type="text" value=""/>
                </span>
            </div>
            <div class="configOil">
                <a>是否生效:</a>
                <span>
                <select class="newPublicSelect newPublicSelect1 publicSelectSwitchTimeProject" id="configStatus" name="configStatus" style="width:25%  !important;">
                    <option value='1'>有效</option>
                    <option value='0'>无效</option>
                </select>
                </span>
            </div>
            <div style="clear: both;"></div>
            <div>
                <span class="saveConfig" onclick="saveOrUpdate();">保存</span>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    var totalPages;
    P.pushCurrentPage();
    var heightPage = document.documentElement.clientHeight;
    $("#heightPage").css("height", heightPage - 160 + "px");
    $(document).ready(function () {

        //显示页面标题并绑定返回按钮的跳转地址
        Page.showPageTitleBindReturnUrl();
        //回显条件
        ajaxPost("scfConfig/page.json", P.formData(document.scfConfigForm), function (resp) {
            parseInvoiceObjToHtml(resp);
        });
    });

    //搜索项目列表
    function searchScfConfig() {
        var projectValidate = PF.projectValidate("keywords", "planName");
        if (projectValidate && document.scfConfigForm) {
            var formData = P.formData(document.scfConfigForm);
            ajaxPost("scfConfig/page.json", formData, function (resp) {
                parseInvoiceObjToHtml(resp);
            });
        }
        return false;
    }

    //解析单据对象为html
    function parseInvoiceObjToHtml(resp) {
        var dataTableId = "projectList";
        var dataTablePageBarId = "projectPageBar";

        var html = "";
        var responseData = resp.data;
        if (responseData.records.length <= 0) {
            html = "<tr><td colspan='7' align='center'>暂无数据！</td></tr>";
            $("#" + dataTableId).html(html);
            $("#" + dataTablePageBarId).hide();
            return false;
        }
        $.each(responseData.records, function (index, item) {
            html += '<tr>';
            html += '<td title="' + item.configKey + '" >' + item.configKey + '</td>';
            html += '<td title="' + item.configValue + '" >' + item.configValue + '</td>';
            html += '<td title="' + item.configDesc + '" >' + item.configDesc + '</td>';
            html += '<td title="' + (item.configStatus==1?"有效":"无效") + '" >' + (item.configStatus==1?"有效":"无效") + '</td>';
            html += '<td class="newPublicTableCenter" title="'+P.long2Datetime(item.createTime)+'"><span class="time">'+P.long2Datetime(item.createTime)+'</span></td>';
            html += '<td class="newPublicTableCenter" title="'+P.long2Datetime(item.updateTime)+'"><span class="time">'+P.long2Datetime(item.updateTime)+'</span></td>';

            var json=JSON.stringify(item);
            html += "<td><ul class='new_icon_pic'><li onclick='editConfig(" + item.id + ",\"" + item.roleName
                + "\",\"" + json.replaceAll("\"","\\\"")+ "\")'><img src='assets/imgs/operate/icon_view.png'/><span>编辑</span></li>";
            html += "<li onclick='deleteConfig(" + item.id + ")'><img src='assets/imgs/operate/icon_delete.png'/><span style='color: #fc5679;'>删除</span></li>";
            html += "</ul></td></tr>";
        });
        $("#" + dataTablePageBarId).show();
        $("#" + dataTableId).html(html);
        $("#publicTable1 tr:even").css("background", "#f2f2f2");
        ajaxPageBarId(dataTablePageBarId, responseData.total, responseData.size, responseData.current, document.scfConfigForm, parseInvoiceObjToHtml);
        //记住总的页数
        totalPages = responseData.pages;
    }

    //新增或修改弹窗
    function editConfig(configId, roleName,json) {
        document.editConfigForm.reset();
        $("#configId").val(configId);
        var item=jQuery.parseJSON(json);
        ajaxGet("scfConfig/findAllParentList.json", function (resp) {
                var list="<option value='0'>---请选择父级配置---</option>";
                var oilSites=resp.data;
                for(var i=0;i<oilSites.length;i++){
                    if(item && oilSites[i].id==item.parentId){
                        list+='<option value="'+oilSites[i].id+'" title="'+oilSites[i].configDesc+'" selected>'+oilSites[i].configDesc+'</option>';
                    }else{
                        list+='<option value="'+oilSites[i].id+'" title="'+oilSites[i].configDesc+'">'+oilSites[i].configDesc+'</option>';
                    }
                }
                $("#parentId").html(list);
                showEditView(item);
        });
    }
    function showEditView(item) {
        var objs = $(".configOilCompany");
        var title = item && item.id? "编辑配置" : "新增配置";
        layer.open({
            type: 1,
            title: [title, 'font-size:16px;color:white;background-color:#0071db;'],
            area: ['400px', '500px'],
            closeBtn: 4,
            content: objs
        });

        /** 父级Id */
        /** 配置键 */
        /** 配置值 */
        /** 常量描述 */
        /** 配置是否生效 */
        $("#configId").val(item.id);
        $("#configKey").val(item.configKey);
        $("#configValue").val(item.configValue);
        $("#configDesc").val(item.configDesc);
        $("#configStatus").val(item.configStatus);
    }

    //删除
    function deleteConfig(configId) {
        selfConfirm("你确定要删除该配置吗!",function () {
            ajaxGet("scfConfig/delete/"+configId+".json", function (resp) {
                if (resp.success) {
                    selfAlert("保存成功!",function () {
                        layer.closeAll();
                        $("#page").val($("span.on").html());
                        searchScfConfig();
                    });
                } else {
                    selfAlert(resp.message);
                }
            });
        });
    }

    //保存
    function saveOrUpdate() {
        var formData = P.formData(document.editConfigForm);
        if (P.isNullOrEmptyString(formData.configKey)) {
            selfAlert("请填写配置key名称!");
            return false;
        }
        ajaxPost("scfConfig/saveOrUpdate.json", formData, function (resp) {
            if (resp.success) {
                selfAlert("保存成功!",function () {
                    layer.closeAll();
                    $("#page").val($("span.on").html());
                    searchScfConfig();
                });
            } else {
                selfAlert(resp.message);
            }
        });
    }
</script>
