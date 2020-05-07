<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.publicImgs{position: relative;top:5px;}
.titleImgs{margin-top: 20px;margin-bottom: 50px;}

<c:if test="${companyType==1}">
.containerBoxS{width: 1083px;height: auto;position: relative;margin-left: 150px;}
.icon_linesText{position: absolute;clear: both;display: block; top: 160px;  left: 124px;}
.containerBoxCenterS{width: 1083px;height: 370px;background: url("newassets/imgs/icon_top02.png")no-repeat;}
.containerBoxCenterS ul li{float: left;margin-top: 40px;cursor: pointer;}
.icon_errows{padding-left: 25px;padding-right: 25px;}
.icon_errows img{display: block;  margin-top: 35px;}
@media screen and (min-width:100px) and (max-width:1440px) {
    .containerBoxS{margin-left: 20px;}
    .titleImgs{margin-bottom: 28px;}
}
</c:if>

<c:if test="${companyType==2}">
    .containerBox{width:900px;height:auto; margin-left: 150px;}
    .containerBoxCenter{width: 885px;height: 162px;background: url("newassets/imgs/icon_bg_center.png")no-repeat;}
    .containerBoxCenter ul li{float: left;margin-top: 40px;cursor: pointer;}
    .icon_errows{padding-left: 25px;padding-right: 25px;}
    .icon_errows img{display: block;  margin-top: 35px;}
@media screen and (min-width:100px) and (max-width:1340px) {
    .containerBoxS{margin-left: 20px;}
    .titleImgs{margin-bottom: 28px;}
}
</c:if>
</style>
<!--顶部导航-->
<div class="publicHeaderNav">
    <ul>
        <li>
            <a>首页</a>
        </li>
    </ul>
</div>
<!--顶部导航 end-->
<div class="clear"></div>
<div id="new_public_height">
    <c:if test="${empty companyType}">
        <img src="assets/imgs/logo/login_logo.png" style="display: block;margin-left: 140px;margin-top: 80px;">
    </c:if>
    <c:if test="${companyType==1}">
        <p class="titleImgs">
            <img src="newassets/imgs/icon_lines.png" class="publicImgs">
            <span>操作流程图</span>
        </p>
        <div class="containerBoxS">
            <img src="newassets/imgs/icon_top01.png" style="margin-bottom: 40px;">
            <img class="icon_linesText" src="newassets/imgs/icon_line01.png">
            <img class="icon_linesText" src="newassets/imgs/icon_line02.png" style="left: 325px;">
            <div class="containerBoxCenterS">
                <ul>
                    <li style="margin-left: 288px;" target="客户管理" targetChildIndex="2" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_mgmt01.png">
                    </li>
                    <li class="icon_errows">
                        <img src="newassets/imgs/icon_left_errow.png">
                    </li>
                    <li  target="客户管理" targetChildIndex="3" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_mgmt02.png">
                    </li>
                </ul>
                <div style="clear: both;"></div>
                <ul style="margin-top: 90px;">
                    <li style="margin-left: 90px;" target="运营审核" targetChildIndex="2" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_mgmt03.png">
                    </li>
                    <li class="icon_errows">
                        <img src="newassets/imgs/icon_errows.png">
                    </li>
                    <li  target="风控审核" targetChildIndex="2" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_mgmt04.png">
                    </li>
                    <li class="icon_errows">
                        <img src="newassets/imgs/icon_errows.png">
                    </li>
                    <li  target="财务审核" targetChildIndex="2" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_mgmt05.png">
                    </li>
                    <li class="icon_errows">
                        <img src="newassets/imgs/icon_errows.png">
                    </li>
                    <li  target="管理层审核" targetChildIndex="2" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_mgmt06.png">
                    </li>
                    <li class="icon_errows">
                        <img src="newassets/imgs/icon_errows.png">
                    </li>
                    <li  target="出纳审核" targetChildIndex="2" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_mgmt07.png">
                    </li>
                </ul>
            </div>
        </div>
    </c:if>

    <c:if test="${companyType==2}">
        <p class="titleImgs">
            <img src="newassets/imgs/icon_lines.png" class="publicImgs">
            <span>操作流程图</span>
        </p>
        <div class="containerBox">
            <img src="newassets/imgs/icon_bg_box.png">
            <div class="containerBoxCenter">
                <ul>
                    <li style="margin-left: 90px;" target="我的额度" targetChildIndex="0" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_pic_01.png">
                    </li>
                    <li class="icon_errows">
                        <img src="newassets/imgs/icon_errows.png">
                    </li>
                    <li target="用款申请" targetChildIndex="0" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_pic_02.png">
                    </li>
                    <li style="margin-left: 120px;" target="用款申请记录" targetChildIndex="2" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_pic_03.png">
                    </li>
                    <li class="icon_errows"  style=" ">
                        <img src="newassets/imgs/icon_errows.png">
                    </li>
                    <li  target="账单还款" targetChildIndex="2" onclick="toPage(this)">
                        <img src="newassets/imgs/icon_pic_04.png">
                    </li>
                </ul>
            </div>
            <img src="newassets/imgs/icon_bg_bottom.png">
        </div>
    </c:if>
</div>
<script>
    $(document).ready(function(){
        // Page.showPageTitleBindReturnUrl();
        // initReady();
        // $(".menuList ul li:first>a").click();
        // $(".menuList ul li:first ul li:first a").click();
        initReady();
    });
    function toPage(obj) {
        //目标菜单
        var target = $(obj).attr('target');
        //目标菜单子目录索引，0代表主菜单
        var targetChildIndex = $(obj).attr('targetChildIndex');
        var indexTmp=0;
        var isGo=false;
        $(".menuList a").each(function (index,data) {
            if(target==$(data).text().trim()){
                $(data).click();
                isGo=true;
            }
            if(isGo){
                if(parseInt(targetChildIndex)>0){
                    if(++indexTmp == parseInt(targetChildIndex)){
                        $(data).click();
                        // menuListShowChildren($(data));
                    }
                }
            }

        })
        //Page.clickChildMenuToPage(path,$(obj).text(),$(obj).parent().parent().children(":first").text().trim());
    }
    // //兼容20条以上的数据重新布局页面 必须120毫秒 不然渲染不成功
    // setTimeout(function(){
    //     initReady();
    // },120);
</script>
