<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--顶部导航-->
<div class="publicHeaderNav">
    <ul>
        <li>
            <a id="personnalText">个人资料</a>
        </li>
    </ul>
</div>
<style>
    .personmsgs span {
        display: inline-block;
        width: 80px !important;
        font-size: 14px;
        text-align: right;
        color: #666;
    }
</style>
<!--顶部导航 end-->
<div class="uploadImg" style="display:none"></div>
<div class="changeLoginPasswdPanel" style="display: none;">
    <form action="scfUser/modifyLoginPwd.json" name="loginPasswdForm" id="loginPasswdForm" onsubmit="return PF.changeLoginPasswd();">
        <div class="modifyPassWord">
            <table>
                <tr>
                    <td style="width:100px;">
                        <span><img src="newassets/imgs/Required.png">旧密码：</span>
                    </td>
                    <td><input type="password" id="usedPwd" class="pwdInput" placeholder="请输入旧密码"></td>
                </tr>
                <tr>
                    <td>
                        <span><img src="newassets/imgs/Required.png">新密码：</span>
                    </td>
                    <td><input type="password" id="newPwd" class="pwdInput" placeholder="请输入新密码"></td>
                </tr>
                <tr>
                    <td>
                        <span><img src="newassets/imgs/Required.png" >确认密码：</span>
                    </td>
                    <td><input style="display:none"><input type="password" id="confirmPwd" class="pwdInput" placeholder="请输入确认密码"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <br/>
                        <input type="hidden" name="newPassword" id="md5Passwd"/>
                        <input type="hidden" name="oldPassword" id="oldMd5Passwd"/>
                        <input type="hidden" name="id" id="id" value="${onlineUser.id }"/>
                        <input class="publicBtn new_public_blue_bg" type="submit"  value="保存"/>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<!-- 导入弹窗需要的HTML -->
<div class="publicContainer">
    <div class="publicMainContainerBox">
        <div class="new_public_tab publicTitle">
            <c:if test="${1 != onlineUser.userType}">
                <span class="staticsActives" onclick="personMoneyLoad(this,1)">个人资料</span>
            </c:if>
        </div>
        <div class="publicTab" style="background: white;border: 1px solid #e6e6ef;border-top:none;width: 100%;">
            <div class="BoxTab">
                <c:if test="${1 != onlineUser.userType}">
                    <div class="publicBox" style="display: block;">
                        <div class="PersonalInformation">
                            <table>
                                <tr>
                                    <td rowspan="12" width="180px"style="border-right: 1px solid #EEEEEE;">
                                        <div class="personCenterPic">
                                            <br>
                                            <img width="150px" height="150px" id="profileIcon" src="newassets/imgs/default.png">
                                            <br>
                                            <input type="button" style="background:#42a6ff;border:none;" class="modifyBtn" onclick="showChangeLoginPasswd()" value="修改密码">

                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" >
                                        <p class="new_title_text" style="padding-bottom: 5px;"><img src="newassets/imgs/icon_chart_line.png"><a>个人信息</a></p>
                                        <div class="personmsgs">
                                                <p><span>姓名：</span><a>${onlineUser.nickName }</a></p>
                                            <p class="tstatusname"></p>
                                            <%--<c:if test="${onlineUser.shipperChild}">--%>
                                                <%--<p><span>主账号：</span><a style="font-weight: bold;">${shipper.shipperName } - ${shipper.mobileNo } </a></p>--%>
                                            <%--</c:if>--%>
                                            <p><span>登录账号：</span><a>${onlineUser.username }</a></p>

                                            <%--<c:if test="${ oilUser.roleId!=23 }">--%>
                                                <p><span>公司名称：</span><a>${onlineUser.companyName }</a></p>
                                                <%--<c:if test="${ oilUser.roleId!=22 }">--%>
                                                    <%--<p><span>站点名称：</span><a>${oilSite.siteName }</a></p>--%>
                                                    <%--<c:if test="${ oilSite.siteType==1 }">--%>
                                                        <%--<p><span>站点性质：</span><a>加油站</a></p>--%>
                                                    <%--</c:if>--%>
                                                    <%--<c:if test="${ oilSite.siteType==2 }">--%>
                                                        <%--<p><span>站点性质：</span><a>加气站</a></p>--%>
                                                    <%--</c:if>--%>
                                                    <%--<p><span>站点地址：</span><a>${oilSite.siteAddress }</a></p>--%>
                                                <%--</c:if>--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${onlineUser.shipperChild == false}">--%>
                                                <%--<p style="padding-bottom: 5px;"><span>身份证号：</span><a id="personID">${childShipper.idCardNo }</a></p>--%>
                                            <%--</c:if>--%>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </c:if>


            </div>
        </div>
    </div>
   </div>
<script type="text/javascript">

    $(document).ready(function(){
        bindCardTurn();
        initReady();
        var str=$("#personID").text();
        if(str != ""){
            var str2 = str.substr(0,6)+"********"+str.substr(14);
            $("#personID").html(str2);
        }
        $("#checkbox").on('click',function(){
            var status;
            var yes="newassets/imgs/icon_yes.png";
            if($("#checkbox").attr("src")==yes){
                $(this).attr('src','newassets/imgs/icon_no.png');
                $("#checkbox").data('istrue',true);
                status=0;
            }else {
                $(this).attr('src','newassets/imgs/icon_yes.png');
                $("#checkbox").data('istrue',false);
                status=1;
            }
            ajaxGet("user/changeReceive.json?status="+status, function(result){
                if(result.success == true){
                }else{
                    selfAlert("状态变更失败! " + result.message);
                }
            });

        });

        $("#insurance_pingan_btn").click(function(){
            window.open("${pinganUrl}");
        });

        if("${isFinancial}" == 1){
            $(".publicTitle span")[1].click();
        }

        $("#getSmsCode").click(function () {
            var url = "ajax/sendSmsCode.json?flag=3";
            ajaxGet(url, function(response){
                if(response.resultCode ==1000 || response.resultCode == 2074){
                    var waitTime = response.waitTime;
                    if(waitTime>0){
                        VerificationTime = waitTime;
                        $("#BoxNames").html(response.message);
                    }else{
                        VerificationTime = 60;
                    }
                    P.setTimes(document.getElementById("getSmsCode"));
                }else {
                    $("#BoxNames").html(response.message);
                }
            });
        });
        $("#addBankGetSmsCode").click(function () {
            var url = "ajax/sendSmsCode.json?flag=1";
            ajaxGet(url, function(response){
                if(response.resultCode ==1000 || response.resultCode == 2074){
                    var waitTime = response.waitTime;
                    if(waitTime>0){
                        VerificationTime = waitTime;
                        $("#BoxName").html(response.message);
                    }else{
                        VerificationTime = 60;
                    }
                    P.setTimes(document.getElementById("addBankGetSmsCode"));
                }else {
                    $("#BoxName").html(response.message);
                }
            });
        });

        $("#delBankGetSmsCode").click(function () {
            var url = "ajax/sendSmsCode.json?flag=2";
            ajaxGet(url, function(response){
                if(response.resultCode ==1000 || response.resultCode == 2074){
                    var waitTime = response.waitTime;
                    if(waitTime>0){
                        VerificationTime = waitTime;
                        $("#delMessageInfo").html(response.message);
                    }else{
                        VerificationTime = 60;
                    }
                    P.setTimes(document.getElementById("delBankGetSmsCode"));
                }else {
                    $("#delMessageInfo").html(response.message);
                }
            });
        });
    });
    function personMoneyLoad(obj,index){
        $(obj).addClass("staticsActives").siblings().removeClass("staticsActives");
        $(".publicBox").hide().eq(index-1).show();
        if(index == 1){
            $("#personnalText").text("个人资料");
            $(".PersonalList").hide();
        }else{
            $("#personnalText").text("我的钱包");
            $(".PersonalList").show();
        }
    }
    function bindCardTurn(){
        $("#bindCardTurnBtn").css("height","35px");
        $("#bindCardTurnBtn span").css("width","50%");
        $("#bindCardTurnBtn span").css("height","32px");
        $("#bindCardTurnBtn span").css("line-height","32px");

        $("#bindCardTurnBtn span").on('click', function () {
            $(this).addClass("source-active").siblings().removeClass("source-active");
            var index = $(this).index();
            $(".bindCardTurnPanel").hide().eq(index).show();
        })
    }

    function bindAddBankCardClick(type){
        document.addWithdrawBankCardForm.reset();
        $("#temp_back_type").val(type);
        layer.closeAll();
        var obj = $(".addBankcard");
        var dialogHeight = "320px";
        if(type == 2){
            dialogHeight = "350px";
            $(".bankAddress").show();
            $("#publicTag").val("Y");
        }else{
            $(".bankAddress").hide();
            $("#publicTag").val("N");
        }
        $("#BoxName").html("");
        layer.open({
            type: 1,
            title: ['添加银行卡', 'font-size:16px;color:white;background-color:#0071db;'],
            area: ['400px', dialogHeight],
            closeBtn: 4,
            content: obj
        });
    }

    function showBankCardList(type, result){
        var bindId = type == 2 ? "#personCard" : "#companyCard";
        var html = "";
        for (var i = 0; i < result.data.cards.length; i++) {
            var itemCard = result.data.cards;
            var bankAccountNo = itemCard[i].bankAccountNo;
            html += "<tr>";
            if(type == 2){ //个人卡
                html += '<td>' + itemCard[i].bankAccountName + '&nbsp;&nbsp;' + itemCard[i].bankName+'<br/>';
                html += bankAccountNo.substr(0, 4) + "****";
                html += bankAccountNo.substr(bankAccountNo.length - 4, 4);
                html += '</td>';
                var isDefault = itemCard[i].isDefault;
                html += '<td style="text-align: center;">' + (1 == isDefault ? '<span class="red">默认银行卡</span>' : '<input type="button" class="publicBtn" style="background: #18aec9;" value="设为默认" onclick="PF.setDefaultBankCard(2, \'' + itemCard[i].bankAccountNo + '\')"/>') + '</td>';
                html += '<td style="width:60px;"><input type="button" class="publicBtn deleteBtn" value="删除" onclick="toDeleteBankCard(' + itemCard[i].id + ',\''+itemCard[i].bankAccountName+'\',\''+itemCard[i].bankName+'\',\''+(bankAccountNo.substr(0, 4) + "****"+bankAccountNo.substr(bankAccountNo.length - 4, 4))+'\')"></td>';
            }else{ //公司卡
                html += '<td>' + itemCard[i].bankAccountName + '&nbsp;&nbsp;' + itemCard[i].bankName+'<br/>';
                var address = itemCard[i].bankAddress;
                html += address == null ? "" : address +'<br/>';
                html += bankAccountNo.substr(0, 4) + "****";
                html += bankAccountNo.substr(bankAccountNo.length - 4, 4);
                html += '</td>';
                var isDefault = itemCard[i].isDefault;
                html += '<td style="text-align: center;">' + (1 == isDefault ? '<span class="red">默认银行卡</span>' : '<input type="button" class="publicBtn" style="background: #18aec9;" value="设为默认" onclick="PF.setDefaultBankCard(1, \'' + itemCard[i].bankAccountNo + '\')"/>') + '</td>';
                html += '<td style="width:60px;"><input type="button" class="publicBtn deleteBtn" value="删除" onclick="toDeleteBankCard(' + itemCard[i].id + ',\''+itemCard[i].bankAccountName+'\',\''+itemCard[i].bankName+'\',\''+(bankAccountNo.substr(0, 4) + "****"+bankAccountNo.substr(bankAccountNo.length - 4, 4))+'\')"></td>';
            }
            html += "</tr>";
        }
        if (html == "") {
            html += '<tr><td colspan="3" align="center">您还没有绑定银行卡!</td>'
        }
        $(bindId).html(html);
    }

    //密码框
    var $input = $(".fake-box input");
    $("#pwd-input").on("input", function() {
        var pwd = $(this).val().trim();
        for (var i = 0, len = pwd.length; i < len; i++) {
            $input.eq("" + i + "").val(pwd[i]);
        }
        $input.each(function() {
            var index = $(this).index();
            if (index >= len) {
                $(this).val("");
            }
        });
        if (len == 6) {
            //pwd是input的值alert(pwd)
        }else if(len > 6){
            return false;
        }
    });

    var imageType = 1;
    function showImage(url){
        if(imageType == 1){
            $("#idCardPicture").val(url);
        }
    }

    function showChangeLoginPasswd(){
        var obj = $(".changeLoginPasswdPanel");
        layer.open({
            type: 1,
            title: ['修改登录密码', 'font-size:16px;color:white;background-color:#0070db;'],
            area: ['400px', '235px'],
            closeBtn:4,
            content: obj
        });
    }

    function profileIconUpload(){
        $.ajax({
            url:'drivers/toUpload.html',
            type:'get',
            success:function(data){
                var obj = $(".uploadImg").html(data)

                layer.open({
                    type: 1,
                    title: ['上传头像', 'font-size:16px;color:white;background-color:#0071db;'],
                    area: ['450px', '390px'],
                    closeBtn:4,
                    content: obj
                });

                //设置头像显示
                $("#imageType").val("18");
            }
        });
    }

    //关闭弹窗
    $("#complete").on("click",function(){
        layer.closeAll();
    });

    //跳转充值记录页面
    $("#problemComplete").on("click",function(){
        layer.closeAll();
        var  dataStatus = $(".rechargeWait").attr("data");
        if(dataStatus==1){
            PF.showRechargeDialog();
        }else if(dataStatus==2){
            PF.showRechargeToMasterAccountDialog();
        }
    });

    /**
     * 计算手续费
     */
    function countCommission(){
        $("#commissionChargeParent").show();
        var ReflectMoney=$("#ReflectMoney").val();//提现金额
        var userBalance=$("#userBalance").html();//余额
        var tradeBalance=$("#tradeBalance").html();//免手续费提现金额
        var delayHours=$("#delayHours option:selected").attr('value');//提现类型 t+0 t+1
        var defaultCommissionCharge=$("#defaultCommissionCharge").val();//默认手续费比例
        var commissionCharge;
        //提现金额大于免手续费金额时 区分t+0 t+1
        if(ReflectMoney - tradeBalance > 0 ){
            console.log(ReflectMoney - tradeBalance)
            commissionCharge = (ReflectMoney - tradeBalance) * defaultCommissionCharge;
            if(delayHours==0){
                commissionCharge = commissionCharge + 2;
            }
            $("#commissionCharge").html("手续费 "+Math.round(commissionCharge*100)/100+" 元"+"(仅供参考)");

        }else{
            //提现金额小于免手续费时 区分t+0 t+1
            if(delayHours==0){
                commissionCharge =  2;
                $("#commissionCharge").html("手续费 "+Math.round(commissionCharge*100)/100+" 元"+"(仅供参考)");
            }else{
                $("#commissionCharge").html("");
            }
        }
        $("#commissionChargeVal").val(commissionCharge);
    }

    /**
     * 全部提现
     */
    function withdrawalsAll(){
        var projectId = $("#projectId").val();

        var userBalance=$("#userBalance").html();//余额
        var tradeBalance=$("#tradeBalance").html();//免手续费提现金额
        var rechargeBalance = mathRound(userBalance - tradeBalance);//充值账户总金额
        var real = 0; //充值账户扣除手续费能提取的实际金额
        var onePenny = 0;
        var delayHours=$("#delayHours option:selected").attr('value');//提现类型 t+0 t+1
        var defaultCommissionCharge=Number($("#defaultCommissionCharge").val());//默认手续费比例
        var commissionCharge = 0; //手续费

        //即时提现手续费
        if(delayHours == 0) {
            //先扣除2元手续费, 再计算充值账户提现所需手续费
            rechargeBalance = mathRound(rechargeBalance - 2);
            commissionCharge = 2;
        }

        //充值账户余额大于0元, 则充值账户需要提现, 需要计算手续费
        if(rechargeBalance > 0) {
            /**
             * 假设充值账户实际提现金额为x
             * x + x*0.006 = rechargeBalance
             * 先计算出x, 再计算实际手续费(x*0.006)
             * warn: 存在1分钱提不出来的bug. 如总金额为141.68
             */
            real = mathRound(rechargeBalance/(1+defaultCommissionCharge));
            commissionCharge = mathRound(commissionCharge + mathRound(real * defaultCommissionCharge));
        }

        // 判断是否有1分钱bug
        if(real != 0 && mathRound(mathRound(real * defaultCommissionCharge) + real) != rechargeBalance) {
            //one penny bug
            onePenny = 0.01;
        }

        var reflectMoney = mathRound(userBalance - commissionCharge - onePenny);
        $("#ReflectMoney").val(reflectMoney);
        if(commissionCharge!=0){
            $("#commissionCharge").html("手续费 " + commissionCharge + " 元"+"(仅供参考)");
        }
        $("#commissionChargeVal").val(commissionCharge);
    }

    function mathRound(num) {
        return Math.round(num*100)/100;
    }

    function toDeleteBankCard(id,name,type,cardNo) {
        $("#bankCardId").val(id);
        $("#bankCardNameView").html(name);
        $("#bankCardTypeView").html(type);
        $("#bankCardNoView").html(cardNo);
        $("#delBankCardDialog").show();
        $("#bankCardListDialog").hide();
        $("#delSmsCode").val("");
    }

    function toBankCardList() {
        $("#bankCardId").val('');
        $("#bankCardNameView").html('');
        $("#bankCardTypeView").html('');
        $("#bankCardNoView").html('');
        $("#delBankCardDialog").hide();
        $("#bankCardListDialog").show();
    }
    //查看所有项目余额
    function showAllProjectMoney(){

    }
</script>
