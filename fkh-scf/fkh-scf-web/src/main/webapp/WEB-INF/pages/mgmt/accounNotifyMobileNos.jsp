<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--结算详情页面-->
<div class="paymentPage">

    <!--顶部导航-->
    <div class="publicHeaderNav">
        <ul>
            <li>
                <a href="javascript:;" class="returnPay">系统管理</a>
            </li>
            <li>
                <img src="newassets/imgs/icon_new_arrow_nav.png">
            </li>
            <li>
                <a href="javascript:;" class="returnPay">账单短信设置</a>
            </li>


        </ul>
    </div>
    <!--顶部导航 end-->
    <!--确认支付主题内容-->
    <div class="public_container" id="new_public_height3" style="width: 100%;background: white; border: 1px solid #e6e6ef; padding-bottom: 20px;">
        <div class="publicMainContainerBox publicMainContainerBoxs">
            <!--头部公用-->
            <div class="newPublicHeader">
                <span>账单短信设置</span>
            </div>
            <!--确认支付信息-->
            <div class="messagePayInvoices detail">
                <p>
                    <span style="float:left;margin-top: 40px;">账单提醒接收人：</span><i style="padding-left: 10px;" class="companyMessage"></i>
                    <textarea id="mobileNos"  name="mobileNos"  style="resize:none;width: 300px;height: 100px" placeholder="请输入接收人11位手机号码，请回车换行">${company.accountMobileNos}</textarea>
                    <a style="color: red;display: block;text-align: left;padding-left: 150px;">※可设置多个手机号码，使用enter回车换行</a>
                </p>
                <div>
                    <p>
                        <span></span>
                        <input type="button" id="confirmPayInvoices" class="publicBtn new_public_blue_bg" onclick="saveMobileNos()" value="保存">
                    </p>
                </div>
                <%--<span></span>--%>
                <%--<input type="button" id="confirmPayInvoices" class="publicBtn new_public_blue_bg" value="确定支付">--%>
                <%--</p>--%>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        //公用布局模块方法调用
        initReady();

    });
    function saveMobileNos() {
        var accountMobileNos = $("#mobileNos").val().trim();
        // if (P.isNullOrEmptyString(accountMobileNos)) {
        //     selfAlert("请填写接收人手机号码!");
        //     return false;
        // }
        var nums = accountMobileNos.split("\n");
        var flag =true;
        var accountMobileNosValue =[];
        $.each(nums, function (index, item) {

            if (item.trim()!=""&&!isMobileNoLogin(item)) {
                selfAlert("请填写正确手机号码!");
                flag = false;
                return ;
            }
            if(accountMobileNosValue.indexOf(item)<0){
                accountMobileNosValue.push(item);
            }

        });
        if(!flag){
            return false ;
        }
        accountMobileNos =   accountMobileNosValue.join("\n");

        var formData={
            "accountMobileNos":accountMobileNos
        }
        ajaxPost("company/saveAccountMobileNos.json", formData, function (resp) {
            if (resp.success) {
                selfAlert("设置成功!");
                $("#mobileNos").val(accountMobileNos);
            } else {
                selfAlert(resp.message);
            }
        });
    }
</script>

