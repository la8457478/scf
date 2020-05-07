<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<ul>

    <%--<li url="oilSites/cashierDesk.html" tag="1"><a class="menuListActive" onclick="menuListShow(this,1)"><img src="newassets/imgs/icon_home_1.png"><span>收银台</span></a></li>--%>
    <%--<li url="oilSites/order.html" tag="2"><a class="menuListActive" onclick="menuListShow(this,1)"><img src="newassets/imgs/icon_home_1.png"><span>订单管理</span></a></li>--%>
    <%--<c:if test="${onlineUser.admin}">--%>
    <%--<li tag="3"><a onclick="menuListShow(this,2)"><img src="newassets/imgs/icon_home_3.png"><span>管理菜单</span></a>--%>
    <%--<ul>--%>
    <%--<li url="home/companyMgmt.html" onclick="menuListShowChildren(this)"><a>公司管理</a></li>--%>
    <%--<li url="oilCompanyProjects.html" onclick="menuListShowChildren(this)"><a>油卡项目</a></li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--</c:if>--%>

    <%--<c:if test="${!onlineUser.admin and onlineUser.oilUser.roleId==2}">--%>
    <%--<li reloadMenu="1" tag="5"><a onclick="menuListShow(this,5)"><img src="newassets/imgs/icon_home_10.png"><span>数据统计</span></a>--%>
    <%--<ul>--%>
    <%--<li url="home/oil/oilOderPage.html?type=1" onclick="menuListShowChildren(this)"><a>消费明细</a></li>--%>
    <%--<li url="home/oil/oilOderPage.html?type=2" onclick="menuListShowChildren(this)"><a>收入明细</a></li>--%>
    <%--<li url="home/oil/driverAmountDetail.html" onclick="menuListShowChildren(this)"><a>用户数据</a></li>--%>
    <%--</ul>--%>
    <%--</li>--%>

    <%--<li url="home/config/oilSiteConfig.html" tag="2"><a class="menuListActive" onclick="menuListShow(this,1)"><img src="newassets/imgs/icon_home_1.png"><span>配置中心</span></a></li>--%>
    <%--</c:if>--%>
    <%--<c:if test="${!onlineUser.admin and onlineUser.oilUser.roleId==3}">--%>
    <%--<li url="home/config/oilUserConfig.html" tag="2"><a class="menuListActive" onclick="menuListShow(this,1)"><img src="newassets/imgs/icon_home_1.png"><span>配置中心</span></a></li>--%>
    <%--</c:if>--%>

    <%--<c:if test="${!onlineUser.admin and onlineUser.oilUser.roleId==4}">--%>
    <%--<li url="home/config/oilSiteGunMgmt.html" tag="2"><a class="menuListActive" onclick="menuListShow(this,1)"><img src="newassets/imgs/icon_home_1.png"><span>配置中心</span></a></li>--%>
    <%--</c:if>--%>

    <%--<c:if test="${onlineUser.admin or onlineUser.oilUser.roleId==1}">--%>
    <%--<li reloadMenu="1" tag="5"><a onclick="menuListShow(this,5)"><img src="newassets/imgs/icon_home_10.png"><span>对账单</span></a>--%>
    <%--<ul>--%>
    <%--<li url="home/oil/oilCardWaybillPage.html" onclick="menuListShowChildren(this)"><a>油卡运单</a></li>--%>
    <%--<li url="home/oil/driverIncomeDetail.html" onclick="menuListShowChildren(this)"><a>用户收入汇总</a></li>--%>
    <%--<li url="home/oil/payDetailPage.html" onclick="menuListShowChildren(this)"><a>用户消费明细</a></li>--%>
    <%--<li url="home/oil/driverCostDetail.html" onclick="menuListShowChildren(this)"><a>用户消费汇总</a></li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--</c:if>--%>

    <%--<li url="authRoleMgmt.html" onclick="menuListShowChildren(this)"><a>角色管理</a></li>
    <li url="departmentMgmt.html" onclick="menuListShowChildren(this)"><a>组织架构</a></li>
    &lt;%&ndash;<li url="menuFuncMgmt.html" onclick="menuListShowChildren(this)"><a>菜单管理</a></li>&ndash;%&gt;
    &lt;%&ndash;<li url="authFuncMgmt.html" onclick="menuListShowChildren(this)"><a>权限管理</a></li>&ndash;%&gt;
    <li url="customerMgmt.html" onclick="menuListShowChildren(this)"><a>客户管理</a></li>
    <li url="companyMgmt.html" onclick="menuListShowChildren(this)"><a>企业管理</a></li>
        <li url="waybillList.html" onclick="menuListShowChildren(this)"><a>运单管理</a></li>
        <li url="subjectClaimsOrder.html" onclick="menuListShowChildren(this)"><a>标的债权管理</a></li>
        <li  reloadMenu="1">
            <a  onclick="menuListShow(this)">
                &lt;%&ndash;<img src="newassets/imgs/icon_home_3.png">&ndash;%&gt;
                <span>用款申请管理</span></a>
                <ul>
                    <li url="creditApply.html?type=1" onclick="menuListShowChildren(this)"><a>审核中</a></li>
                    <li url="creditApply.html?type=2" onclick="menuListShowChildren(this)"><a>已放款</a></li>
                    <li url="creditApply.html?type=3" onclick="menuListShowChildren(this)"><a>历史订单</a></li>
                </ul>

        </li>
    <li url="accountBill.html" onclick="menuListShowChildren(this)"><a>账单管理</a></li>
        <li  reloadMenu="1">
            <a  onclick="menuListShow(this)">
                &lt;%&ndash;<img src="newassets/imgs/icon_home_3.png">&ndash;%&gt;
                <span>运营审核</span></a>
            <ul>
                <li url="creditApply.html?type=4" onclick="menuListShowChildren(this)"><a>用款申请审核</a></li>
            </ul>
        </li>
        <li  reloadMenu="1">
            <a  onclick="menuListShow(this)">
                &lt;%&ndash;<img src="newassets/imgs/icon_home_3.png">&ndash;%&gt;
                <span>风控审核</span></a>
            <ul>
                <li url="creditApply.html?type=5" onclick="menuListShowChildren(this)"><a>用款申请审核</a></li>
                <li url="companyContractMgmt.html" onclick="menuListShowChildren(this)"><a>项目管理</a></li>
            </ul>
        </li>
        <li  reloadMenu="1">
            <a  onclick="menuListShow(this)">
                &lt;%&ndash;<img src="newassets/imgs/icon_home_3.png">&ndash;%&gt;
                <span>管理层审核</span></a>
            <ul>
                <li url="creditApply.html?type=6" onclick="menuListShowChildren(this)"><a>用款申请审核</a></li>
                <li url="companyContractMgmt.html" onclick="menuListShowChildren(this)"><a>项目管理</a></li>
            </ul>
        </li>
        <li  reloadMenu="1">
            <a  onclick="menuListShow(this)">
                &lt;%&ndash;<img src="newassets/imgs/icon_home_3.png">&ndash;%&gt;
                <span>财务审核</span></a>
            <ul>
                <li url="creditApply.html?type=7" onclick="menuListShowChildren(this)"><a>用款申请审核</a></li>
                <li url="creditApply.html?type=8" onclick="menuListShowChildren(this)"><a>放款管理</a></li>
                <li url="repayList.html" onclick="menuListShowChildren(this)"><a>还款管理</a></li>
            </ul>
        </li>

        <li url="scfConfigMgmt.html" onclick="menuListShowChildren(this)"><a>系统配置管理</a></li>
    <li url="creditRegulationConfigMgmt.html" onclick="menuListShowChildren(this)"><a>配置用信规则</a></li>
    <li url="projectMgmt.html" onclick="menuListShowChildren(this)"><a>项目管理</a></li>
    <li url="companyContractMgmt.html" onclick="menuListShowChildren(this)"><a>主体合同管理</a></li>--%>
        <c:forEach items="${menus}" var="menu">
            <li <c:if test="${ not empty menu.funcUrl }"> url="${menu.funcUrl }" </c:if> reloadMenu="1">
                   <c:choose>
                       <c:when test="${not empty menu.subList}">
                       <a onclick="menuListShow(this)" data="1">
                       </c:when>
                       <c:otherwise>
                           <a onclick="menuListShow(this)" data="2">
                       </c:otherwise>
                   </c:choose>

                    <c:if test="${menu.funcName == '首页' }">
                        <img src="assets/imgs/menu/home_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '财务审核' }">
                        <img src="assets/imgs/menu/finance_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '出纳审核' }">
                        <img src="assets/imgs/menu/cashier_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '风控审核' }">
                        <img src="assets/imgs/menu/risk_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '管理层审核' }">
                        <img src="assets/imgs/menu/manage_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '基础信息管理' }">
                        <img src="assets/imgs/menu/base_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '客户管理' }">
                        <img src="assets/imgs/menu/customer_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '收款管理' }">
                        <img src="assets/imgs/menu/payee_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '我的额度' }">
                        <img src="assets/imgs/menu/my_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '系统管理' }">
                        <img src="assets/imgs/menu/sys_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '用款申请' }">
                        <img src="assets/imgs/menu/apply_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '用款申请记录' }">
                        <img src="assets/imgs/menu/apply_list_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '运营审核' }">
                        <img src="assets/imgs/menu/operate_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '账单还款' }">
                        <img src="assets/imgs/menu/account_select.png">
                    </c:if>
                    <c:if test="${menu.funcName == '账单管理' }">
                        <img src="assets/imgs/menu/account_select.png">
                    </c:if>
                <span>${menu.funcName }</span>
                    <%--<c:if test="${menu.funcName != '首页'&& menu.funcName != '用款申请' && menu.funcName != '账单管理'&& menu.funcName != '收款管理'&& menu.funcName != '我的额度' }">--%>
                        <%--<img class="imgsMenu" style=" float: right; margin-right: 40px;" src="assets/imgs/menu/icon_memu02.png">--%>
                    <%--</c:if>--%>
                    <c:if test="${not empty menu.subList}">
                        <img class="imgsMenu" style=" float: right; margin-right: 40px;" src="assets/imgs/menu/icon_memu02.png">
                    </c:if>
                </a>

                <ul>
                    <c:forEach items="${menu.subList}" var="sub">
                        <li url="${sub.funcUrl }" onclick="menuListShowChildren(this)"><a style="margin-left: 3px;"><img src="assets/imgs/menu/icon_checked.png" style="margin-left: -15px;position: relative;top:5px;">${sub.funcName }</a></li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
</ul>
