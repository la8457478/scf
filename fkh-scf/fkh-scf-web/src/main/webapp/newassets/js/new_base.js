/**
 * Created by Administrator on 2016/7/27.
 */
/***********权限菜单新增信息*********/
function addPermission(){
	document.updateFuncForm.reset();
    var objs = $(".InformationShow");
    layer.open({
    	type: 1,
        title: ['新增权限信息', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['630px', '315px'],
        closeBtn:4,
        content: objs
    });
}
/***********权限菜单编辑信息*********/
function editPermission(id){
    $.getJSON("settings/permissioInfo.json?id="+id, function(result){
		if(result.success == false){
			return;
		}
		result = result.data;
		if(!isNull(result)){
			 $("#permissionName").val(result.modelDesc);
		     $("#editFuncType").val(result.funcType);
		     $('#editParentId').val(result.parentId);
		     $("#id").val(result.id);
		     $("#menuSort").val(result.menuSort);
		     $("#url").val(result.url);
		     $("#cssIcon").val(result.icon);
		     $("#charset").val(result.modelName);
		}
    });
    var objs = $(".InformationShow");
    layer.open({
        type: 1,
        title: ['权限信息', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['630px', '315px'],
        closeBtn:4,
        content: objs
    });
}
/*********权限菜单验证信息****************/
function validatePermissionForm(){
    var permissionName = $("#permissionName").val();
    if(isNull(permissionName)){
        selfAlert('请输入权限名称',function(){$("#permissionName").focus();});
        return false;
    }
    if(!isChinese(permissionName)){
        selfAlert('请输入2-20位中文的权限名称',function(){$("#permissionName").focus();});
        return false;
    }
    if (2 > permissionName.length || permissionName.length > 20) {
        return false;
    }
    var menunames = "-请选择-";
    var funcType = $("#editFuncType").val();
    if(funcType == menunames || funcType == ""){
        selfAlert("权限类型不能为空",function(){$("#editFuncType").focus();});
        return false;
    }
    /*var parentId = $("#editParentId").val();
    if(parentId == menunames || parentId == ""){
        selfAlert("上级分类不能为空",function(){$("#editParentId").focus();})
        return false;
    }*/
    var numbers = $("#menuSort").val();
    if(isNull(numbers)){
        selfAlert("请输入排序值",function(){$("#menuSort").focus();});
        return false;
    }
    if(!isNumber(numbers)){
        selfAlert("请输入0-99999任意数字",function(){$("#menuSort").focus();});
        return false;
    }
    layer.closeAll();
    return true;
}
/********新增车辆信息管理*********/
 function addNewVehicle(){
    var obj = $(".VehicleManage");
    layer.open({
        type: 1,
        title: ['编辑车辆管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['720px', '610px'],
        closeBtn:4,
        content: obj
    });
}

 /*********编辑车辆信息**********/
 function editVehicle(id){
	 $.getJSON("settings/vehicleInfo.json?id="+id, function(result){
			if(result.success == false){
				return;
			}
			result = result.data;
			if(!isNull(result)){
				$("#driverName").val(result.driverName);
			     $("#mobileNo").val(result.mobileNo);
			     $('#licensePlateNo').val(result.licensePlateNo);
			     $("#carBrand").val(result.carBrand);
			     $("#carType").val(result.carType);
			     $("#carLength").val(result.carLength);
			     $("#axleNum").val(result.axleNum);
			     $("#cargoTonnage").val(result.cargoTonnage);
			     $("#obdSimNo").val(result.obdSimNo);
			     $("#obdNo").val(result.obdNo);
			     $("#id").val(result.id);
			}
	 });

     var obj = $(".VehicleManage");
     layer.open({
         type: 1,
         title: ['编辑车辆管理', 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['720px', '610px'],
         closeBtn:4,
         content: obj
     });
 }

 /**
  * 校验车辆信息
  * @returns
  */
 function validateVehicleForm(){
	 var driverName = $("#driverName").val();
	 if(isNull(driverName)){
	     selfAlert('请输入驾驶员姓名',function(){$("#driverName").focus();});
	     return false;
	 }
	 if(!isChinese(driverName)){
	     selfAlert('请输入2-10位中文的驾驶员姓名',function(){$("#driverName").focus();});
	     return false;
	 }
	 if (2 > driverName.length || driverName.length > 10) {
	     return false;
	 }
	 var mobileNo = $("#mobileNo").val();
	 if(isNull(mobileNo)){
	     selfAlert("请输入手机号码",function(){$("#mobileNo").focus();});
	     return false;
	 }
	 if(!isNumber(mobileNo)){
	     selfAlert("请输入正确的手机号码",function(){$("#mobileNo").focus();});
	     return false;
	 }
	 if (mobileNo.length != 11) {
		 selfAlert("请输入正确的手机号码",function(){$("#mobileNo").focus();});
	     return false;
	 }
	 var licensePlateNo = $("#licensePlateNo").val();
	 if(isNull(licensePlateNo)){
	     selfAlert("请输入车牌号",function(){$('#licensePlateNo').focus();});
	     return false;
	 }
	 if (!isCharOrNumber(licensePlateNo)) {
	     selfAlert("车牌号为字母A-Z和数字0-9的组合！", function(){ $("#licensePlateNo").focus(); });
	     return false;
	 }
	 if (licensePlateNo.length != 5){
	     selfAlert("车牌号为5位数组或字母的组合！", function(){ $("#licensePlateNo").focus(); });
	     return false;
	 }
	 var carType = $("#carType").val();
	 if(isNull(carType)){
	     selfAlert("请输入车辆类型",function(){$("#carType").focus();});
	     return false;
	 }
	 var carLength = $("#carLength").val();
	 if(isNull(carLength)){
	     selfAlert("请输入车厢长度",function(){$("#carLength").focus();});
	     return false;
	 }
	 var axleNum = $("#axleNum").val();
	 if(isNull(axleNum)){
	     selfAlert("请输入车轴数",function(){$("#axleNum").focus();});
	     return false;
	 }
	 var cargoTonnage = $("#cargoTonnage").val();
	 if(isNull(cargoTonnage)){
	     selfAlert("请输入核载吨位",function(){$("#cargoTonnage").focus();});
	     return false;
	 }
	 var obdSimNo = $("#obdSimNo").val();
	 if(isNull(obdSimNo)){
	     selfAlert("请输入终端SIM卡号",function(){$("#obdSimNo").focus();});
	     return false;
	 }
	 var obdNo = $("#obdNo").val();
	 if(isNull(obdNo)){
	     selfAlert("请输入唯一终端ID号",function(){$("#obdNo").focus();});
	     return false;
	 }
	 layer.closeAll();
	 return true;
 }

 /**********新增终端信息管理*************/
 function addTerminal(){
 	document.editTerminalForm.reset();
     var obj = $(".editTerminal");
     layer.open({
         type: 1,
         title: ['添加终端', 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['660px', '390px'],
         closeBtn:4,
         content: obj
     });
 }
 /**********终端信息管理编辑**************/
 function editTerminal(id){
 	$.getJSON("settings/terminalInfo.json?id="+id, function(result){
 		if(result.success == false){
 			return;
 		}
 		result = result.data;
 		if(!isNull(result)){
 			 $("#termNo").val(result.termNo);
 		     $("#termType").val(result.termType);
 		     $('#state').val(result.state);
 		     $("#installTime").val(P.long2Date(result.installTime));
 		     $("#seqNo").val(result.seqNo);
 		     $("#simNo").val(result.simNo);
 		     $("#verSoftware").val(result.verSoftware);
 		     $("#verHardware").val(result.verHardware);
 		     $("#devNo").val(result.devNo);
 		     $("#waitor").val(result.waitor);
 		     $("#remark").val(result.remark);
 		     $("#id").val(result.id);
 		}
 	});

     var obj = $(".editTerminal");
     layer.open({
         type: 1,
         title: ['编辑终端信息', 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['660px', '390px'],
         closeBtn:4,
         content: obj
     });
 }
 /***********发布终端信息管理**************/
 function validateTerminalForm(){
     var termNo = $("#termNo").val();
     if(isNull(termNo)){
         selfAlert("请输入终端号",function(){$("#termNo").focus();});
         return false;
     }
     if(2 > termNo.length || 20 < termNo.length){
         selfAlert("请输入20位由数字组成的唯一终端号",function(){$("#termNo").focus();});
         return false;
     }
     var names = '-请选择-';
     var termType = $("#termType").val();
     if(termType==names || termType == ""){
         selfAlert("请选择终端类型",function(){$("#termType")});
         return false;
     }
     var state = $("#state").val();
     if(state==names || state == ""){
         selfAlert("请选择设备当前状态",function(){$("#state")});
         return false;
     }
     var simNo = $("#simNo").val();
     if(isNull(simNo)){
         selfAlert("请输入电话号码",function(){$("#simNo")});
         return false;
     }
     if (!isMobileNo(simNo)) {
         selfAlert("请输入11位数字的有效手机号码！", function(){ $("#simNo").focus(); });
         return false;
     }
     layer.closeAll();
     return true;
 }

 /*****新增数据字典弹出****/
 function addNewDataDictionary(){
 	document.dataDictionaryForm.reset();
    var objs = $(".dataDictionary");
    layer.open({
        type: 1,
        title: ['新增数据字典', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['420px', '280px'],
        closeBtn:4,
        content: objs
    });
    var dataTypeText= $("#seltparent").find("option:selected").val();
     $("#parent").val(dataTypeText);
 }
 /*******编辑数据字典管理*******/
 function editDataDictionary(id){
 	$.getJSON("settings/dataDictionaryInfo.json?id="+id, function(result){
 		if(result.success == false){
 			return;
 		}
 		result = result.data;
 		if(!isNull(result)){
 			$("#parent").val(result.parent);
 		    $("#name").val(result.name);
 		    $("#code").val(result.code);
 		    $("#id").val(result.id);
 		}
 	});

     var objs = $(".dataDictionary");
     layer.open({
         type: 1,
         title: ['编辑数据字典', 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['420px', '280px'],
         closeBtn:4,
         content: objs
     });
 }
 /******验证数据字典*******/
 function  validateDataDictionaryForm(){
 	var parent = $("#parent").val();
 	if(isNull(parent)){
         selfAlert("请选择数据类型",function(){$("#parent").focus();});
         return false;
     }
     var name = $("#name").val();
     if(isNull(name)){
         selfAlert("请输入名称",function(){$("#name").focus();});
         return false;
     }
     if(!isChineseAndChar(name)){
         selfAlert("请输入2~20中文字符名称",function(){$("#name").focus();});
     }
     if(2 > name.length || name.length > 20){
         selfAlert("请输入长度2~20中文名称",function(){$("#name").focus();});
         return false;
     }
     var code = $("#code").val();
     if(isNull(code)){
         selfAlert("请输入编码",function(){$("#code").focus();});
         return false;
     }
     layer.closeAll();
     return true;
 }
 /*================项目管理树形菜单所有数据====================*/
//项目详情-人员配置
 function personStaffing(userId, projectId, funcType){
	 $.getJSON("projects/listDeptAndUsersByOwnerId.json?userId="+userId+"&projectId="+projectId+"&userDepId=0&funcType="+funcType, function(result){
	 		if(result.success == false){
	 			return;
	 		}
	 		if(!isNull(result)){
	 			//项目详情checkBox树菜单
	 		    $.fn.zTree.init($("#personConfigure"), settingCheckbox, result);
	 		     //显示默认数据
	 		     var treeObj = $.fn.zTree.getZTreeObj("personConfigure");
	 		     var nodeList = treeObj.getCheckedNodes(true);
	 		     var depText = "";
	 		     var selectedIds = "";
	 		     for (var i = 0, l = nodeList.length; i < l; i++) {
	 		         var item = nodeList[i];
	 		         var depName = item.name;
	 		         var depId = item.id;
	 		         selectedIds += item.id+",";
	 		         depText += '<li class="name_con">'+
	 		             '<span class="fl" >'+depName+'</span>'+
	 		             '<span class="dep_delBtn" data-id="'+depId+'"></span>'+
	 		             '</li>';
	 		     }
	 		     $("#texts").html(depText);
	 		     $("#selectText").html("已选择");
	 	         $("#selectUserIds").val(selectedIds);
	 		     $("#selectUserIdOlds").val(selectedIds);
	 		}
	 	});

     var settingCheckbox = {
         callback: {
             beforeClick: beforeClick,
             onCheck:onCheck
         },
         check: {
             enable: true,
             chkboxType: {"Y": "s", "N": "ps"}   //Y是选中 n是取消  s是子节点  p是父级
         },

         data: {
             simpleData: {
                 enable: true
             }
         }
     };

     // 点击文字选中checkbox框
     function beforeClick(treeId, treeNode) {
         var treeObj = $.fn.zTree.getZTreeObj("personConfigure");
         treeObj.checkNode(treeNode, !treeNode.checked, null, true);
     }
     // 绑定checkbox事件
     function onCheck(event, treeId, treeNode) {
         var treeObjs = $.fn.zTree.getZTreeObj("personConfigure");
         var nodes = treeObjs.getCheckedNodes(true);
         var sNodes = treeObjs.getSelectedNodes();
         var depText = '';
         //遍历所有节点数据
         var depIds = "";
         for (var i = 0, l = nodes.length; i < l; i++) {
             var item = nodes[i];
             var depName = item.name;
             depIds += item.id + ",";
             var deptId = item.pId;
             depText += '<li class="name_con">'+
                 '<span class="fl">'+depName+'</span>'+
                 '<span class="dep_delBtn" data-id="'+deptId+'"></span>'+
                 '</li>';
             $("#selectText").html("已选择")
         }

         $("#selectUserIds").val(depIds);
         $("#texts").html(depText);
         if($("#texts").html()==""){
             $("#selectText").html("请选择")
         }
     }
 }
 /*================组织架构创编辑部门===============*/
 function editSectorWin(name, type){
     var objs = $(".sectorPublic");
     layer.open({
         type: 1,
         title: [name, 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['400px', '180px'],
         closeBtn:4,
         content: objs
     });
     $("#messageText").focus();
     $(".create").html("部门名称：");
     if(name=='创建部门'){
         $("#messageText").val("");
     }
     if(name=='创建子部门'){
         $("#messageText").val("");
     }
     if(name=='编辑部门'){
         $("#messageText").val(treeNodeCompany);
     }
     objs.find('#confirmSubmit').attr('data-type',type);
 }
var treeNodeCompany ;
 //组织架构 -部门编辑
 function editDepartmentsTree(userId, userDepId){

     $.getJSON("department/getTreeData.json?deptId="+userDepId, function(result){
	 		if(result.success == false){
	 			return;
	 		}
	 		if(!isNull(result.data)){
	 		    //部门结构树菜单
	 		    $.fn.zTree.init($("#personSector"), settingCheckbox, result.data);
	 		    // ajaxGet("projects/listUsersByOwnerId.json?userId="+userId+"&depId=0", function(resp){ loadDepartmentData(resp); });
	 		}
     });

     var settingCheckbox = {
         callback: {
             beforeClick: beforeClicks,
             onCheck:onChecks
         },
         check: {
             enable: true,
             chkStyle: "radio",
             radioType: "all" //Y是选中 n是取消  s是子节点  p是父级
         },
         data: {
             simpleData: {
                 enable: true
             }
         }
     };

     // 点击文字选中checkbox框
     function beforeClicks(treeId, treeNode) {
    	 var treeObj = $.fn.zTree.getZTreeObj("personSector");
         treeObj.checkNode(treeNode, !treeNode.checked, null, true);
     }

     function onChecks(event, treeId, treeNode){
    	 var treeObj = $.fn.zTree.getZTreeObj("personSector");
         var nodes = treeObj.getCheckedNodes(true);
         var deptId = treeNode.id;
         //所有只能单选一个部门
         treeObj.setting.check.radioType = "all";
         var parentId = treeNode.pId;
         var depName = treeNode.name;
         if(treeNode.checked){
        	 $("#deptId").val(deptId);
         }else{
        	 $("#deptId").val("");
         }
     }
 }

 //组织架构-树形菜单
 function departmentTreeMenu(userId){
     $.getJSON("department/getTreeData.json", function(result){
	 		if(result.success == false){
	 			return;
	 		}
	 		if(!isNull(result)){
	 		    //部门结构树菜单
	 		    $.fn.zTree.init($("#sectorStructure"), setting, result.data);
                //added by yx
                var treeObj = $.fn.zTree.getZTreeObj("sectorStructure");
                var rootNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
                if(rootNode!=null){
                    $("#"+rootNode.tId+"_a").click();
                }
	 		    // ajaxGet("projects/listUsersByOwnerId.json?userId="+userId+"&depId=0", function(resp){ loadDepartmentData(resp); });
            }
	 	});

     //部门组织结构菜单
     var setting = {
         callback: {
             onClick: zTreeClick
         },
         data: {
             simpleData: {
                 enable: true
             }
         }
     };

     function showIconForTree(treeId, treeNode) {
         return !treeNode.isParent;
     }

     //部门结构click事件
     function zTreeClick(event, treeId, treeNode) {
    	 $(".operationBtn").show();
         var treeObj = $.fn.zTree.getZTreeObj("sectorStructure");
         //判断是否父级有值
         var value = treeObj.getSelectedNodes()[0].getParentNode() ? treeObj.getSelectedNodes()[0].getParentNode().name : '';
         var depId = treeNode.id;
         var placeholder = "";
         //如果父级为空就干掉value值
         if (value == "") {
             $("#sectorText").html(treeNode.name);
             ajaxGet("scfUser/listPage.json", function(resp){
            	 loadDepartmentData(resp);
            	 //显示按钮
            	 hideInputFN();
             });
         } else {
             $("#sectorText").html(value + '-' + treeNode.name);
             ajaxGet("scfUser/listPage.json?deptId="+depId, function(resp){
            	 loadDepartmentData(resp);
            	 //显示按钮
            	 hideInputFN();
             });
         }
         treeNodeCompany = treeNode.name;
         $("#messageText").val(treeNode.name);
         $(".searchBtnTextNameInput").val("");
         $("#depId").val(depId);

         //删除选中的部门功能
         $("#removeSector").on("click", function () {
             selfConfirm('你确认要删除' + treeNode.name + '吗？', function () {
                 var nodes = treeObj.getSelectedNodes();
                 var depIds = "";
                 for (var i = 0, l = nodes.length; i < l; i++) {
                	 var selectedNode = nodes[i];
                     var depId = selectedNode.id;
                     var childNodes = selectedNode.children;
                     if(!childNodes){
                    	 childNodes = selectedNode;
                     }
                     for (var j = 0, m = childNodes.length; j < m; j++) {
                    	 var selectedChildNode = childNodes[j];
                    	 var childDepId = selectedChildNode.id;
                    	 depIds += childDepId + ",";
                     }
                     depIds += depId + ",";
                     treeObj.removeNode(selectedNode);
                     $("#sectorText").html("");
                 }
                 ajaxGet("department/delete.json?deptIds="+depIds, function(result){
                	 if(result.success == true){
                		 selfAlert(result.message);
                		 //删除之后调用隐藏按钮方法
                		 hideBtnFN();
                	 }else{
                		 selfAlert("删除失败! " + result.message);
                	 }
                 });
             });
         });
         //创建部门/编辑部门功能
         $("#confirmSubmit").unbind("click").bind("click", function () {
             var type = $(this).attr('data-type');
             var nodes = treeObj.getNodes();
             var selectNode = treeObj.getSelectedNodes();
             var originNode = null;
             if(!selectNode[0]){
            	 selfAlert("请选择属主部门");
                 return false;
             }
             var depId = selectNode[0].id;
             var depName = $('#messageText').val();
             var parentId = selectNode[0].pId;
             if(depName==""){
                 selfAlert("请输入部门名称",function(){$("#messageText").focus()});
                 return false;
             }
             if(depName.length > 20 || depName.length < 2){
            	 selfAlert("请输入2-20个字的部门名称",function(){$("#messageText").focus()});
                 return false;
             }
             if(!validateDeptName(depName)){
            	 selfAlert("不能输入特殊字符",function(){$("#messageText").focus()});
                 return false;
             }
             if(null == parentId){
            	 selectNode[0].pId = 0;
            	 parentId = 0;
             }
             //封装方法
             function updateParentNode(node) {
                 var parentNode = !!node.getParentNode() ? node.getParentNode() : node;
                 if (!!node.getParentNode()) {
                     var selectIndex = node.getIndex();
                     $.each(parentNode.children, function (index, item) {
                         if (index == selectIndex) {
                             item = node;
                         }
                     });
                     updateParentNode(parentNode);
                     //编辑之后改变sectorText值
                     if (value == "") {
                         $("#sectorText").html(treeNode.name);
                     } else {
                         $("#sectorText").html(value + '-' + treeNode.name);
                     }
                 } else {
                     originNode = parentNode;
                     return false;
                 }
             }

             switch (type) {
	             case 'newsector':
	                 var newNode = {name: depName, pId:parentId};
	                 treeObj.addNodes(selectNode[0], newNode);
	                 break;
             	 case 'editsector':
                     selectNode[0].name = depName;
                     updateParentNode(selectNode[0]);
                     if (!!originNode) {
                         nodes[0] = originNode;
                         treeObj.refresh();
                     }
                     break;
                 case 'newchildsector':
                     var newNodes = {name: depName};
                     treeObj.addNodes(selectNode[0], newNodes);
                     $("#sectorText").html(treeObj.getNodes()[0].name);
                     parentId = depId;
                     depId = 0;
                     break;
             }
             //保存新建或修改的部门信息
             var jsondata = {id:depId,parentId:parentId,deptName:depName};
             ajaxPost("department/saveOrUpdate.json", jsondata, function(result){
            	 if(result.success == true){
            		 selfAlert(result.message, function(){
            			 departmentTreeMenu(userId);
            			//创建部门之后调用隐藏按钮方法
            			 hideBtnFN();
            		 });
            	 }else{
            		 selfAlert("操作失败! " + result.message, function(){
            			 departmentTreeMenu(userId);
            		 });
            	 }
             });

             $('#messageText').val("");
             layer.closeAll();
         });
       //创建部门之后影藏按钮
         function hideBtnFN(){
			 $(".addPersonsBtn").hide();
			 $(".createBtn").hide();
			 $(".editSectorBtn").hide();
			 $(".createSonBtn").hide();
			 $(".delBtn").hide();
         }
       //显示搜索按钮和input
         function hideInputFN(){
			$(".iconlinePic").show();
			$(".searchBtnTextName").show();
			$(".searchBtnTextNameInput").show();
         }
         //判断level值显示操作按钮
         var treeObj = $.fn.zTree.getZTreeObj("sectorStructure");
         var sNodes = treeObj.getSelectedNodes();
         if (sNodes.length > 0) {
             var level = sNodes[0].level;
             $("#new_operationMessage_header").show();
             $("#new_table_departmentMgmt").css("marginTop","18px");
             if (level == 0) {
                 $(".editSectorBtn").hide();
                 $(".createSonBtn").hide();
                 $(".delBtn").hide();
                 $(".addPersonsBtn").show();
                 $(".createBtn").show();
             }else if (level == 1) {
                 $(".createBtn").hide();
                 $(".createSonBtn").show();
                 $(".editSectorBtn").show();
                 $(".addPersonsBtn").show();
                 $(".delBtn").show();
             }else if (level == 2) {
                 // $(".createBtn").hide();
                 // $(".createSonBtn").show();
                 // $(".editSectorBtn").show();
                 // $(".addPersonsBtn").show();
                 // $(".delBtn").show();
                 // todo chenli 暂时先支持到二级部门
                 $(".createSonBtn").hide();
                 $(".createBtn").hide();
                 $(".addPersonsBtn").show();
                 $(".editSectorBtn").show();
                 $(".delBtn").show();
             }

             // else if (level == 3) {
             //     $(".createSonBtn").hide();
             //     $(".createBtn").hide();
             //     $(".addPersonsBtn").show();
             //     $(".editSectorBtn").show();
             //     $(".delBtn").show();
             // }
         }else{
             $("#new_operationMessage_header").hide();
         }
     }
 }

 /*================新增角色===============*/
 function addRole(){
	roleFuncModelsTrees(0);
	var objs = $(".rolePublicManage");
	layer.open({
	    type: 1,
	    title: ['新增角色', 'font-size:16px;color:white;background-color:#0070db;'],
	    area: ['620px', '500px'],
	    closeBtn:4,
	    content: objs
	});
	$("#_roleId").val("");
	$("#_roleName").val("");
	$("#_roleDesc").val("");

     $("#saveBtnShow").show();
     $(".showTrHeight").show();
     $("#_roleName").attr("readonly",false);
     $(".companyManage").css("height","310px");
 }

 /*================编辑角色===============*/
 function editRole(obj, roleId,type,roleType,operateType){
	 roleFuncModelsTrees(roleId,type,roleType);
     var otd =$(obj).parent().parent(),
         roleName = otd.siblings(".roleName").text(),
         roleDesc = otd.siblings(".roleDesc").text();
     var objs = $(".rolePublicManage");
     var title = "";
     $("#_roleName").val(roleName);
     $("#_roleDesc").val(roleDesc);
     if(type == 2){
         $("#saveBtnShow").hide();
         $(".showTrHeight").hide();
         $("#_roleName").attr("readonly",true);
         $(".companyManage").css("height","345px");
         title = "查看角色";
         $("#saveBtnShow").hide();
     }else{
         $("#saveBtnShow").show();
         $(".showTrHeight").show();
         $("#_roleName").attr("readonly",false);
         $(".companyManage").css("height","310px");
         title = "修改角色";
         $("#saveBtnShow").show();
     }
     layer.open({
         type: 1,
         title: [title, 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['620px', '500px'],
         closeBtn:4,
         content: objs
     });
     //$("#Remarks").val(Remarks);
 }
 /*
  * 项目管理 - 新增角色
  * */
 function saveRole(){
	 var roleId = $("#_roleId").val();
	 var roleName = $("#_roleName").val();
	 var roleDesc = $("#_roleDesc").val();
	 var roleFuncIds = $("#funcIds").val();
	 if(isLengthOutRange(roleName, true, 2, 20) || !isCharOrNumOrChinese(roleName)){
		 selfAlert("请填写2-20字角色名称!",function(){$("#_roleName").focus()});
         return false;
	 }

     if (isLengthOutRange(roleDesc, false, 2, 30)) {
         selfAlert("请输入2-30字备注信息!",function(){$("#_roleDesc").focus()});
         return false;
     }

     if(isNull(roleFuncIds)){
         selfAlert("请选择该角色拥有的功能权限!",function(){$("#_roleName").focus()});
         return false;
     }
	 var jsondata = {id:roleId,roleName:roleName,roleDesc:roleDesc,funcIds:roleFuncIds};
 	 ajaxPost("authRole/saveOrUpdate.json",jsondata,function(result){
 		if(result.success == true){
 			selfAlert("操作成功!", function(){
 				layer.closeAll();
                var formData = P.formData(document.roleForm);
                ajaxPost("authRole/listPage.json", formData, function(resp){ loadRolesData(resp);});
 			});
		} else {
			selfAlert("操作失败! " + result.message);
		}
 	 });
 	 return false;
 }
 //角色管理
 function roleFuncModelsTrees(roleId,type,roleType){
	 var action = "authFunction/getTreeData.json?roleType=6";
	 if(roleId > 0){
		 action = "authFunction/getTreeData.json?roleId="+roleId+"&roleType="+roleType;
		 $("#_roleId").val(roleId);
	 }
     ajaxGet(action, function(result){
    	 if(!isNull(result)){
    		 //初始化加载
    	     $.fn.zTree.init($("#rolePerson"), settingCheckbox, result.data);
    	     var treeObj = $.fn.zTree.getZTreeObj("rolePerson");
             var nodes = treeObj.getCheckedNodes(true);
             //type 等于2 是查看 xxl add
             if(type == 2){
                 disabledCheckNode("rolePerson");
             }
             var funcIds = "";
             for (var i = 0, l = nodes.length; i < l; i++) {
                 var item = nodes[i];
                 var modelName = item.name;
                 var funcsId = item.id;
                 var parentId = item.pId;
                 funcIds += funcsId + ",";
                 if(type == 2){
                     var node = treeObj.getNodeByParam("id",parentId);
                     treeObj.selectNode(node,true);//指定选中ID的节点
                     treeObj.expandNode(node, true, false);
                     $(".curSelectedNode").css("background","none");
                 }else {
                     $(".curSelectedNode").css("background","#B8C8E3");
                 }
             }
             $("#funcIds").val(funcIds);
    	 }
     });

     var settingCheckbox = {
         callback: {
             beforeClick: beforeClicks,
             onCheck:onChecks
         },
         check: {
             enable: true,
             chkboxType: {"Y" : "ps", "N" : "ps" }   //Y是选中 n是取消  s是子节点  p是父级
         },
         data: {
             simpleData: {
                 enable: true
             }
         }
     };
     //点击文字选中checkbox框禁止点击文字选择checkbox
     function beforeClicks(treeId, treeNode) {
         return false;
    	 var treeObj = $.fn.zTree.getZTreeObj("rolePerson");
         treeObj.checkNode(treeNode, !treeNode.checked, null, true);
     }

     function onChecks(event, treeId, treeNode){
    	 var treeObj = $.fn.zTree.getZTreeObj("rolePerson");
         var nodes = treeObj.getCheckedNodes(true);
         var depText = '';
         var funcIds = "";
         for (var i = 0, l = nodes.length; i < l; i++) {
             var item = nodes[i];
             var modelName = item.name;
             var funcsId = item.id;
             var parentId = item.pId;
             funcIds += funcsId + ",";
         }
         $("#funcIds").val(funcIds);
     }
 }
 //封装checkbox禁用方法
function disabledCheckNode(nodeParam) {
    var zTreeObj = $.fn.zTree.getZTreeObj(nodeParam);
    var nodes = zTreeObj.getNodes();
    var disabled = true;
    var inheritParent = true;
    var inheritChildren = true;
    if(zTreeObj.setting != null || zTreeObj.setting != "undefined"){
        if(nodes.length == 1 && nodes.length > 0){
            for (var i = 0, l = nodes.length; i < l; i++) {
                zTreeObj.setChkDisabled(nodes[i], disabled, inheritParent, inheritChildren);
            }
        }
    }
}
 /*================项目管理项目详情===============*/
 // function showProjectDetails(projectId, currentLoginUserId, sendUserId, transportUserId, consigneeUserId,IsFromClosedProject){
 //     //记住分页
 //     if(document.queryProjectForm){
 //         P.setGlobalForm(1);
 //     }
 //     ajaxGet("projects/projectDetail.html?pid="+projectId+"&IsFromClosedProject="+IsFromClosedProject,function(resp) {
 //
 //         $("#publicRightContainer").html(resp);
 //         showProjectDetailTabContent(currentLoginUserId, sendUserId, transportUserId, consigneeUserId);
 //
 //     });
 //  }

/**
 *项目运输详情
 * @param projectId
 * @param funcType
 */
// function showProjectOfWaybill(projectId, funcType){
//     /*$.ajax({
//         url:"projects/projectOfWaybill.html?projectId="+projectId,
//         type:'get',
//         success:function(data){
//             $(window.mainContainer).html(data);
//             /!*var index = funcType - 1;
//              showProjectDetailTabContent(index);*!/
//         }
//     });*/
//     //记住分页
//     if(document.queryProjectForm){
//         P.setGlobalForm(1);
//     }
//     containerShow("projects/projectOfWaybill.html?projectId="+projectId,"get");
// }

 // function showProjectDetailTabContent(currentLoginUserId, sendUserId, transportUserId, consigneeUserId){
 //
 //     var index = 0;
 //     if(currentLoginUserId == consigneeUserId) {
 //         index = 2;
 //         $(".publicTitle span").eq(index).find(".imgspics").attr("src","newassets/imgs/icon_receiving_2.png");
 //     }
 //     if(currentLoginUserId == transportUserId) {
 //         index = 1;
 //         $(".publicTitle span").eq(index).find(".imgspics").attr("src","newassets/imgs/icon_carrier_2.png");
 //     }
 //     if(currentLoginUserId == sendUserId) {
 //         index = 0;
 //         $(".publicTitle span").eq(index).find(".imgspics").attr("src","newassets/imgs/icon_delivery_2.png");
 //     }
 //
 //     //默认选项卡选中状态，index：0-2
 //     $(".publicTitle span").eq(index).addClass("activeTabProject").siblings().removeClass("activeTabProject");
 //     $(".publicTitle span").eq(index).find(".messageCenter").addClass("activeop1");
	//  $(".projectBox").hide();
	//  $(".projectBox").eq(index).show();
 //
 //     if($(".icon_me").length==3){
 //         $(".publicTitle span:first").addClass("activeTabProject").siblings().removeClass("activeTabProject");
 //         $(".projectBox").hide().eq(0).show();
 //     }
 //
 // }

 /*================组织架构编辑人员===============*/
 function editDeptUser(type, currentLoginUserId, shipperName, userRoleId, userDMId, userId,companyTel,loginAccount){
	 if(null != type && type == 'edit'){
		 $("#userMobileNo").attr("readonly",true);
	 }else{
		 $("#userMobileNo").removeAttr("readonly");
	 }
	 var name = "添加人员";
	 var btnName = "确定";
	 if(null != type && type == 'edit'){
		 name = "编辑人员";
		 editDepartmentsTree(currentLoginUserId, (typeof userDMId == "undefined"?-1:userDMId));
		 fillRolesSelectOpt(userRoleId);
		 $("#userName").val(shipperName);
		 // $("#userMobileNo").val(mobileNo.substring(3,11));
         if(loginAccount!="undefined"){
             $("#loginAccount").val(loginAccount);
         }else{
             $("#loginAccount").val("");
         }
         $("#userId").val(userId);
		 $("#deptId").val(userDMId);

         // $("#mobileNo").val(mobileNo);
         /*if(mobileNo.substring(0,3)=="199"){
             $("#mobileNo").val(companyTel);
         }else{
             $("#mobileNo").val(mobileNo);
         }*/
        //登录账号不能修改
        $("#loginAccount").attr("readOnly", true);
	 }else{
		 // btnName = "确定提交并发送密码";
		 btnName = "确定";
		 $("#userName").val("");
		 $("#userMobileNo").val("");
		 $("#userId").val("");
         // $("#mobileNo").val("");
         $("#loginAccount").val("");
         var selectDeptId = $("#depId").val();
         selectDeptId = selectDeptId ? selectDeptId : -1;
         $("#deptId").val(selectDeptId);
         editDepartmentsTree(currentLoginUserId, selectDeptId);
		 fillRolesSelectOpt("");
         $("#loginAccount").attr("readOnly", false);
	 }
	 $("#btnSaveUser").val(btnName);
     var objs = $(".publicShow");
     layer.open({
         type: 1,
         title: [name, 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['620px', '400px'],
         closeBtn:4,
         content: objs
     });
 }

 /**
  * 组织框架 - 初始化角色下拉列表框
  * @param defaultVal	默认选中的值，这里为roleId
  * @returns
  */
  function fillRolesSelectOpt(defaultVal){
    var params = {pageSize:99999999};
	ajaxPost("authRole/listPage.json", params, function(result){
	    if (result.success) {
	        var data = result.data.records;
            var html = "<option value=''>-请选择-</option>";
            for(var i=0; i < data.length; i++){
                var itemVal = data[i];
                if (itemVal.roleType != 2 && itemVal.roleType != 3) {
                    html += "<option value='" + itemVal.id + "' ";
                    html += itemVal.id == defaultVal ? "selected='selected'>" : ">";
                    html += itemVal.roleName + "</option>"
                }
            }
            $("#roleId").html(html);
        }
	});
 }

 /*================项目管理项目列表修改角色名称===============*/
 function modifyRoleName(userId, roleId, userName, mobileNo, type){
	 fillRolesSelectOpt(roleId);
	 $("#userName").html(userName);
	 $("#userMobileNo").html(mobileNo);
	 $("#userId").val(userId);
	 $("#changeRoleType").val(type);
     var objs = $(".modifyRolePublic");
     layer.open({
         type: 1,
         title: ['修改角色', 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['450px', '210px'],
         closeBtn:4,
         content: objs
     });
  }


 /**
  * 车辆管理 - 添加驾驶员弹窗页面
  * @returns
  */
 function addDriver(){
	    var objs = $(".AddCarMain");
	    layer.open({
	        type: 1,
	        title: ['添加车辆', 'font-size:16px;color:white;background-color:#0070db;'],
	        area: ['510px', '400px'],
	        closeBtn:4,
	        content: objs
	    });
	}
 /********添加驾驶员省份证照片********/
 function viewSimple(){
     var objs = $(".DriverSample");
     layer.open({
         type: 1,
         title: ['查看流程', 'font-size:16px;color:white;background-color:#0070db;'],
         area: ['650px', '610px'],
         closeBtn:4,
         content: objs
     });
 }

 /**
  * 运单管理 -  平台车辆 - 查看车辆详情
  */
 function vehicleDetail(vehicleId, isBase){
	 var url = "drivers/vehicleDetails.json";
	 url = isBase ? "../"+url : url;
	 $.getJSON(url+"?vehicleId="+vehicleId, function(result){
	 		if(result.success == false){
	 			selfAlert("查询失败! " + result.message);
	 			return;
	 		}

	 		result = result.data;
	 		if(!isNull(result)){
	 		    var vehicle = result.vehicle;
	 		    var siji = result.siji;
	 		    var owner=result.owner;

	 		    if(siji!=null){
	 		        $("#driverName").html(siji.driverName);
                    var driverImg = siji.driverPhoto || siji.driverIcon;
                    //个人车辆显示车主头像，货主车辆显示默认头像
                    if(!driverImg) {
                        driverImg = "newassets/imgs/default.png";
                        driverImg = isBase ? "../"+driverImg : driverImg;
                    }
                    $("#imgDriverAvartar").attr("src", driverImg);
                }
                if(owner!=null){
                    $("#carOwner").html(owner.ownerName);
                }
                if(vehicle!=null){
                    // $("#mobileNo").html(vehicle.mobileNo);
                    $("#engineNumber").html(vehicle.engineNumber);
                    $("#carType").html(vehicle.carType);
                    $("#axleNum").html(vehicle.axleNum);
                    $("#carBrand").html(vehicle.carBrand);
                    $("#carLength").html(vehicle.carLength);
                    $("#cargoTonnage").html(vehicle.cargoTonnage);
                    $("#licensePlateNo").html(vehicle.licensePlateNo);
                    var isInstallObd = vehicle.obdSimNo;
                    //判断是否安装OBD设备
                    if(null != isInstallObd && "" != isInstallObd){

                        //显示绑定ODB内容
                        $(".obdMsg").show();
                        $(".vehicleChart").show();

                        showMileageChartData(result.odoMeter7daysAvg, result.odoMeter7daysList);
                        showFuelConsumptionChartData(result.fuel7daysAvg, result.fuel7daysList);
                        showObdInfo(result.canInfo);
                        $(".highcharts-axis-title").css("display","none");
                        $(".highcharts-legend").css("display","none");

                    }else{
                        //改变媒体查询样式
                        if($(".obdMsg").is(':hidden')){
                            $(".vehicleMsg").css('height','260px')
                        }
                    }
                }

	 		    //隐藏绑定ODB内容
	 		    $(".carObd").hide();
                $(".vehicleContainer").css("border","none");

	 		}
	 });
 }

 /**
  * 平台车辆 - 车辆详情 - 行驶里程图
  * @returns
  */
 function showMileageChartData(odoMeter7daysAvg, odoMeter7daysList){
     var mileageLineName = ['行驶里程'];
     var mileageLineKm = [odoMeter7daysAvg];
     var mileageChartData = [odoMeter7daysList];
     $('#mileageChart').highcharts({
         title: {
             text: '<span style="font-size:14px;font-weight:bold;">'+mileageLineName+'</span>',
             align: 'left',
             x: 20
         },
         subtitle: {
             text: '<span style="font-size:12px;">今日</span><span style="font-size:16px;font-weight:bold;color:#2dadec;">' + mileageLineKm + 'km</span>',
             align: 'left',
             x: 20,
             y: 40
         },
         plotOptions: {
             series: {
                 color: '#30afe2',
                 lineWidth: 2,
                 marker: {
                     radius: 3,  //曲线点半径，默认是4
                     symbol: 'circle' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                 }
             }
         },
         xAxis: {
             lineWidth: 0,
             tickWidth: 0,
             labels: {
                 enabled: false
             }
         },
         tooltip:{
             formatter:function(){
                 var s = '<b>'+'行驶里程：'+this.y+'</b>';
                 return s
             },
             shared:true
         },
         credits: {
             text: '',
             href: ''
         },
         yAxis: {
             gridLineWidth: 0,
             labels: {
                 enabled: false
             }

         },
         legend: {

             borderWidth: 0
         },
         series: [{
             name: mileageLineName,
             data: mileageChartData
         }
         ]
     });
 }

 /**
  * 平台车辆 - 车辆详情 - 平均油耗图
  * @returns
  */
 function showFuelConsumptionChartData(fuel7daysAvg, fuel7daysList){
     var oilChartName = ['平均油耗'];
     var oilLine = [fuel7daysAvg];
     var oilChartData = [fuel7daysList];
     $('#oilChart').highcharts({
         title: {
             text: '<span style="font-size:14px;font-weight:bold;">'+oilChartName+'</span>',
             align: 'left',
             x: 20
         },
         subtitle: {
             text: '<span style="font-size:12px;">今日</span><span style="font-size:16px;font-weight:bold;color:#2dadec;">' + oilLine + 'L</span>',
             align: 'left',
             x: 20,
             y: 40
         },
         plotOptions: {
             series: {
                 color: '#f66105',
                 lineWidth: 2,
                 marker: {
                     radius: 3,  //曲线点半径，默认是4
                     symbol: 'circle' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                 }
             }
         },
         xAxis: {
             lineWidth: 0,
             tickWidth: 0,
             labels: {
                 enabled: false
             }
         },
         tooltip:{
             formatter:function(){
                 var s = '<b>'+'平均油耗：'+this.y+'</b>';
                 return s
             },
             shared:true
         },
         credits: {
             text: '',
             href: ''
         },
         yAxis: {
             gridLineWidth: 0,
             labels: {
                 enabled: false
             }
         },
         legend: {
             borderWidth: 0
         },
         series: [{
             name: oilChartName,
             data: oilChartData
         }
         ]
     });
 }

/**
 * 转换报警源数据
 * @param alarmSource
 * @returns {*}
 */
function convertAlarmSource(alarmSource){
     if(alarmSource == "platform_alarm"){
         alarmSource = "平台报警";
     }else if(alarmSource == 'terminal_alarm'){
         alarmSource = "终端报警";
     }
     return alarmSource;
 }

 /**
  * 实时监控车辆详情 - OBD状态信息
  * @returns
  */
 function showObdInfo(obdInfo){

     var html = "";
     if(null == obdInfo){
         var faultTitle ='<tr><td colspan="3" align="center">暂无车辆OBD信息！</td></tr>';
         $("#obdTableContent").html(faultTitle);

     }else{
         $("#obdTableContent").html("");
         if(null != obdInfo.engineRPM){
            html += '<tr><td>发动机转速</td><td>'+obdInfo.engineRPM+'</td></tr>';
         }
         if(null != obdInfo.tachoCarSpeed){
            html += '<tr><td>转速器车辆速度</td><td>'+obdInfo.tachoCarSpeed+'</td></tr>';
         }
         if(null != obdInfo.wheelCarSpeed){
            html += '<tr><td>基于车轮的车辆速度</td><td>'+obdInfo.wheelCarSpeed+'</td></tr>';
         }
         if(null != obdInfo.fmi){
            html += '<tr><td>燃料中水分指示器</td><td>'+obdInfo.fmi+'</td></tr>';
         }
         if(null != obdInfo.cs){
            html += '<tr><td>离合器开关</td><td>'+obdInfo.cs+'</td></tr>';
         }
         if(null != obdInfo.bs){
            html += '<tr><td>制动开关</td><td>'+obdInfo.bs+'</td></tr>';
         }
         if(null != obdInfo.hbs){
            html += '<tr><td>驻车制动开关</td><td>'+obdInfo.hbs+'</td></tr>';
         }
         if(null != obdInfo.etm){
            html += '<tr><td>发动机转矩模式</td><td>'+obdInfo.etm+'</td></tr>';
         }
         if(null != obdInfo.rtm){
            html += '<tr><td>缓速器转矩模式</td><td>'+obdInfo.rtm+'</td></tr>';
         }
         if(null != obdInfo.etp){
            html += '<tr><td>实际发动机－转矩百分比</td><td>'+obdInfo.etp+'</td></tr>';
         }
         if(null != obdInfo.slp){
            html += '<tr><td>当前速度的百分比负载</td><td>'+obdInfo.slp+'</td></tr>';
         }
         if(null != obdInfo.app){
            html += '<tr><td>加速器踏板位置</td><td>'+obdInfo.app+'</td></tr>';
         }
         if(null != obdInfo.bv){
            html += '<tr><td>蓄电池电压(V)</td><td>'+obdInfo.bv+'</td></tr>';
         }
         if(null != obdInfo.ap){
            html += '<tr><td>大气压力</td><td>'+obdInfo.ap+'</td></tr>';
         }
         if(null != obdInfo.bp){
            html += '<tr><td>增压器压力</td><td>'+obdInfo.bp+'</td></tr>';
         }
         if(null != obdInfo.lop){
             html += '<tr><td>发动机润滑油压力</td><td>'+obdInfo.lop+'</td></tr>';
         }
         if(null != obdInfo.lol){
            html += '<tr><td>发动机润滑油液位</td><td>'+obdInfo.lol+'</td></tr>';
         }
         if(null != obdInfo.ftmp){
            html += '<tr><td>燃料温度</td><td>'+obdInfo.ftmp+'</td></tr>';
         }
         if(null != obdInfo.fuelLevel){
            html += '<tr><td>燃油液位</td><td>'+obdInfo.fuelLevel+'</td></tr>';
         }
         if(null != obdInfo.ct){
            html += '<tr><td>发动机冷却剂温度</td><td>'+obdInfo.ct+'</td></tr>';
         }
         if(null != obdInfo.cl){
            html += '<tr><td>冷却剂液位</td><td>'+obdInfo.cl+'</td></tr>';
         }
         if(null != obdInfo.mat1){
            html += '<tr><td>进气歧管1温度</td><td>'+obdInfo.mat1+'</td></tr>';
         }
         if(null != obdInfo.tot){
            html += '<tr><td>变速器油液温度</td><td>'+obdInfo.tot+'</td></tr>';
         }
         if(null != obdInfo.bcm){
            html += '<tr><td>超越控制模式</td><td>'+obdInfo.bcm+'</td></tr>';
         }
         if(null != obdInfo.ptoStatus){
            html += '<tr><td>PTO状态</td><td>'+obdInfo.ptoStatus+'</td></tr>';
         }
         if(null != obdInfo.ccs){
            html += '<tr><td>巡航控制状态</td><td>'+obdInfo.ccs+'</td></tr>';
         }
         if(null != obdInfo.btw){
            html += '<tr><td>刹车温度警告</td><td>'+obdInfo.btw+'</td></tr>';
         }
         if(null != obdInfo.ptp){
            html += '<tr><td>驻车或/和挂车空气压力</td><td>'+obdInfo.ptp+'</td></tr>';
         }
         if(null != obdInfo.loop1){
            html += '<tr><td>行车制动空气压力，回路#1</td><td>'+obdInfo.loop1+'</td></tr>';
         }
         if(null != obdInfo.loop2){
            html += '<tr><td>行车制动空气压力，回路#2</td><td>'+obdInfo.loop2+'</td></tr>';
         }
         if(null != obdInfo.steeredAngle){
            html += '<tr><td>方向盘角度</td><td>'+obdInfo.steeredAngle+'</td></tr>';
         }
         if(null != obdInfo.turnsCounter){
            html += '<tr><td>转向轮圈数记数器</td><td>'+obdInfo.turnsCounter+'</td></tr>';
         }
         if(null != obdInfo.sensorType){
            html += '<tr><td>转向轮角度传感器类型</td><td>'+obdInfo.sensorType+'</td></tr>';
         }
         if(null != obdInfo.yawVelocity){
            html += '<tr><td>横摆角速度</td><td>'+obdInfo.yawVelocity+'</td></tr>';
         }
         if(null != obdInfo.la){
            html += '<tr><td>横向加速度</td><td>'+obdInfo.la+'</td></tr>';
         }
         if(null != obdInfo.lgda){
            html += '<tr><td>纵向加速度</td><td>'+obdInfo.lgda+'</td></tr>';
         }
         if(null != obdInfo.fwsrpm){
            html += '<tr><td>前轮轴转速</td><td>'+obdInfo.fwsrpm+'</td></tr>';
         }

         if(null != obdInfo.lfwrpm){
            html += '<tr><td>左前轮相对转速</td><td>'+obdInfo.lfwrpm+'</td></tr>';
         }
         if(null != obdInfo.rfwrpm){
            html += '<tr><td>右前轮相对转速</td><td>'+obdInfo.rfwrpm+'</td></tr>';
         }
         if(null != obdInfo.ralwrpm){
            html += '<tr><td>后轴#1左轮相对转速</td><td>'+obdInfo.ralwrpm+'</td></tr>';
         }
         if(null != obdInfo.rarwrpm){
            html += '<tr><td>后轴#1右轮相对转速</td><td>'+obdInfo.rarwrpm+'</td></tr>';
         }
         if(null != obdInfo.axlePos){
            html += '<tr><td>车轴位置</td><td>'+obdInfo.axlePos+'</td></tr>';
         }
         if(null != obdInfo.axleWeight){
            html += '<tr><td>车轴重量</td><td>'+obdInfo.axleWeight+'</td></tr>';
         }
         if(null != obdInfo.trailerWeight){
            html += '<tr><td>挂车重量</td><td>'+obdInfo.trailerWeight+'</td></tr>';
         }
         if(null != obdInfo.cargoWeight){
            html += '<tr><td>货物重量</td><td>'+obdInfo.cargoWeight+'</td></tr>';
         }
         if(null != obdInfo.flwbls){
            html += '<tr><td>前轮车轴，左轮制动衬片剩余信息</td><td>'+obdInfo.flwbls+'</td></tr>';
         }
         if(null != obdInfo.frwbls){
            html += '<tr><td>前轮车轴，右轮制动衬片剩余信息</td><td>'+obdInfo.frwbls+'</td></tr>';
         }
         if(null != obdInfo.ralwbls1){
            html += '<tr><td>后轮车轴#1，左轮制动衬片剩余信息</td><td>'+obdInfo.ralwbls1+'</td></tr>';
         }
         if(null != obdInfo.rarwbls1){
            html += '<tr><td>后轮车轴#1，右轮制动衬片剩余信息</td><td>'+obdInfo.rarwbls1+'</td></tr>';
         }
         if(null != obdInfo.ralwbls2){
            html += '<tr><td>后轮车轴#2，左轮制动衬片剩余信息</td><td>'+obdInfo.ralwbls2+'</td></tr>';
         }
         if(null != obdInfo.rarwbls2){
            html += '<tr><td>后轮车轴#2，右轮制动衬片剩余信息</td><td>'+obdInfo.rarwbls2+'</td></tr>';
         }
         if(null != obdInfo.nc){
            html += '<tr><td>NC</td><td>'+obdInfo.nc+'</td></tr>';
         }
         $("#obdTableContent").html(html);
     }
 }

 /**
 * 运单管理 - 平台车辆 - 查看车辆详情 - 查看更多跳转对应故障预警详情页面
 */
 function moreMessage(obj){
     //关闭弹窗
     layer.closeAll();
     //ajaxGet("part/faultDetails.html","get",function(data){$("#mian-container").html(data);});
     H.turnNavigation("home/warning.html");

 }

 function deleteThis(obj) {
	    var otr = $(obj).parent().parent();
	    selfConfirm("你确定要删除吗？", function () {
	        otr.remove();
	    })
	}

 /**
  * 添加/编辑人员输入校验
  * @returns
  */
 function validateUserForm(){
		var userName = $("#userName").val();
		var roleId = $("#roleId").val();
		var userMobileNo = $("#userMobileNo").val();
		var deptId = $("#deptId").val();

		 if(isNull(userName)){
		     selfAlert('请输入用户姓名',function(){$("#userName").focus();});
		     return false;
		 }
		 if(!isChinese(userName)){
		     selfAlert('请输入2-10位中文的用户姓名',function(){$("#userName").focus();});
		     return false;
		 }
		 if (2 > userName.length || userName.length > 10) {
		     return false;
		 }
		 if(isNull(userMobileNo)){
		     selfAlert("请输入手机号码",function(){$("#userMobileNo").focus();});
		     return false;
		 }
		 if(!isNumber(userMobileNo)){
		     selfAlert("请输入正确的手机号码",function(){$("#userMobileNo").focus();});
		     return false;
		 }
		 if (userMobileNo.length != 11) {
			 selfAlert("请输入正确的手机号码",function(){$("#userMobileNo").focus();});
		     return false;
		 }

		 if(isNull(roleId)){
			 selfAlert("请选择用户角色",function(){$("#roleId").focus();});
		     return false;
		 }

		 if(isNull(deptId)){
			 selfAlert("请选择用户所属部门",function(){$("#deptId").focus();});
		     return false;
		 }

	    return true;
	}


 function validateAddDriverForm(){
	 // var carDriverName = $("#carDriverName").val();
	 // var driverMobileNo = $("#driverMobileNo").val();
	 var carLicensePlateNo = $("#carLicensePlateNo").val();
     var cargoTonnage = $("#cargoTonnageN").val();

	 // if(isNull(carDriverName)){
	 //     selfAlert('请输入驾驶员姓名',function(){$("#carDriverName").focus();})
	 //     return false;
	 // }
	 // if(!isChinese(carDriverName)){
	 //     selfAlert('请输入2-10位中文的驾驶员姓名',function(){$("#carDriverName").focus();});
	 //     return false;
	 // }
	 // if (2 > carDriverName.length || carDriverName.length > 10) {
	 //     return false;
	 // }
	 // if(isNull(driverMobileNo)){
	 //     selfAlert("请输入手机号码",function(){$("#driverMobileNo").focus();});
	 //     return false;
	 // }
	 // if(!isMobileNo(driverMobileNo)){
	 //     selfAlert("请输入正确的手机号码",function(){$("#driverMobileNo").focus();});
	 //     return false;
	 // }
	 // if (driverMobileNo.length != 11) {
		//  selfAlert("请输入正确的手机号码",function(){$("#driverMobileNo").focus();});
	 //     return false;
	 // }
	 if(isNull(carLicensePlateNo)){
	     selfAlert("请输入车牌号",function(){$('#carLicensePlateNo').focus();});
	     return false;
	 }
	 if (!isCharOrNumber(carLicensePlateNo)) {
	     selfAlert("车牌号为字母A-Z和数字0-9的组合！", function(){ $("#carLicensePlateNo").focus(); });
	     return false;
	 }
	 if (carLicensePlateNo.length != 5){
	     selfAlert("车牌号为5位数组或字母的组合！", function(){ $("#carLicensePlateNo").focus(); });
	     return false;
	 }

	 var carBrand = $("#carBrandSave").val();
     if(isNull(carBrand)){
         selfAlert('车辆品牌不能为空',function(){$("#carBrandSave").focus();});
         return false;
     }
     else if(!isChineseAndChar(carBrand)){
         selfAlert('请输入正确的车辆品牌名称',function(){$("#carBrandSave").focus();});
         return false;
     }
     else if(2 > carBrand.length || carBrand.length > 20){
         selfAlert('请输入长度2-20位的车辆品牌名称',function(){$("#carBrandSave").focus();});
         return false;
     }

     /**验证核载吨位**/
     if(isNull(cargoTonnage)){
         selfAlert("请输入核载吨位信息!",function(){$("#cargoTonnage").focus();});
         return false;
     }
     if(cargoTonnage != "不详" && !isNumber(cargoTonnage)){
         selfAlert("请输入数字格式的核载吨位信息!",function(){$("#cargoTonnage").focus();});
         return false;
     }
     if(cargoTonnage>99){
         selfAlert("车辆核载吨位不能大于99 吨!",function(){$("#cargoTonnage").focus();});
         return false;
     }

	 return true;
 }

function validateAddSijiForm(){
    var carDriverName = $("#sijiName").val();
    var driverMobileNo = $("#sijiMobileNo").val();
    // var idCard=$("#idCardNo").val();
    var driverCarApproveDate=$("#driverCarApproveDate").val();
    var driverCarDate=$("#driverCarDate").val();
    var driverCarNo=$("#driverCarNo").val();

    if(isNull(carDriverName)){
        selfAlert('请填写司机姓名',function(){$("#carDriverName").focus();});
        return false;
    }
    if(!isChinese(carDriverName)){
        selfAlert('请填写2-20字的中文姓名',function(){$("#carDriverName").focus();});
        return false;
    }
    if (2 > carDriverName.length || carDriverName.length > 20) {
        selfAlert('请填写2-20字的中文姓名',function(){$("#carDriverName").focus();});
        return false;
    }
    if(isNull(driverMobileNo)){
        selfAlert("请填写司机手机号码",function(){$("#driverMobileNo").focus();});
        return false;
    }
    if(!isMobileNo(driverMobileNo)){
        selfAlert("请填写正确的手机号码",function(){$("#driverMobileNo").focus();});
        return false;
    }
    if(isNull(driverCarNo)){
        selfAlert('驾驶证号码不能为空',function(){$("#driverCarNo").focus();});
        return false;
    }
    if(!idCodeValid(driverCarNo)){
        selfAlert('请输入正确的驾驶证号码',function(){$("#driverCarNo").focus();});
        return false;
    }
    /*if(isNull(driverCarApproveDate)){
        selfAlert('驾驶证认证日期不能为空',function(){$("#driverCarApproveDate").focus();})
        return false;
    }*/
    if(isNull(driverCarDate)){
        selfAlert('驾驶证过期日期不能为空',function(){$("#driverCarDate").focus();});
        return false;
    }
    if(!isMobileNo(driverMobileNo)){
        selfAlert("请输入正确的手机号码",function(){$("#driverMobileNo").focus();});
        return false;
    }
    if (driverMobileNo.length != 11) {
        selfAlert("请输入正确的手机号码",function(){$("#driverMobileNo").focus();});
        return false;
    }

    if(!isNull(driverCarApproveDate)&&(driverCarApproveDate==driverCarDate)){
        selfAlert("驾驶证认证日期不能跟过期日期为同一天",function(){$("#driverCarApproveDate").focus();});
        return false;
    }

    var d1 = new Date(driverCarApproveDate.replace(/\-/g, "\/"));
    var d2 = new Date(driverCarDate.replace(/\-/g, "\/"));

    if(driverCarApproveDate!=""&&driverCarDate!=""&&d1 >=d2)
    {
        selfAlert("驾驶证认证日期不能大于驾驶证过期日期",function(){$("#driverCarApproveDate").focus();});
        return false;
    }

    return true;
}

/**======================================================================================================**/
  function selects(){
    /*********发布货源**************/
    var data       = ["普货","重货","泡货","整车","零担","设备","配件","电瓷","显像管","电器","烟叶","服装","棉纱","棉被",
        "平板纸","医药","煤炭","矿产","钢铁","铁粉","建材","胶版","食品","粮食","饮料","危险品","烟花","化工",
        "化肥农药","石油制品","轻工产品","牧产品","牲畜","渔产品","农产品","水果","蔬菜","木材","木方","竹片",
        "桥车","驾驶室","特种货物","军用品","超宽设备","散装设备","罐装货物","冷藏","其他"];
    var data1      = ["不限","4米","4.2米","4.5米","6.2米","6.8米","7.2米","7.6米","8.2米","8.6米","9.6米","10米","10.5米",
        "11米","11.7米","12.5米","13米","13.5米","14米","15米","16.5米","17米","17.5米","18米","20米","22米","24米"];
    var Company     = ["吨", "方", "件", "车", "个", "台", "箱"];
    var Carlength   = ["不限","4米","4.2米","4.5米","6.2米","6.8米","7.2米","7.6米","8.2米","8.6米","9.6米","10米","10.5米",
        "11米","11.7米","12.5米","13米","13.5米","14米","15米","16.5米","17米","17.5米","18米","20米","22米","24米"];
    var licenseP     = ["京","津","沪","渝","冀","豫","鲁","晋","陕","皖","苏","浙","鄂","湘","赣","闽",
        "粤","桂","琼","川","贵","云","辽","吉","黑","蒙","甘","宁","青","新","藏"];
    var licenseS     = ["A","B","C","D","E","F","G","H","J","K","L","M", "N","P","Q","R","S","T","U","V","W","X","Y","Z"];
    var carTypes     = ["不限", "半封闭", "半挂", "保温", "单车", "低板", "二拖三", "二拖四", "仓栅", "高栏", "高栏单桥", "高栏双桥",
        "工程车", "后八轮或前", "后八轮或半", "集装箱", "冷藏", "零担", "笼子", "平板", "平板拖", "普通",
        "起重", "前四后八", "全封闭", "斯太尔", "特种", "危险", "小车", "邮政", "油罐", "自卸", "自由厢板"];
    var carAxles     = ["不限","2轴","3轴", "4轴" ,"5轴", "6轴"];
    var carnumbers   = ["一车","二车","三车","四车","五车"];
    var menuData     = ['请选择','菜单','前端功能权限','右键命令菜单','地图工具','移动端权限','终端命令工具栏','顶级快捷菜单'];
    var SuperiorData = ['请选择','用户管理','基础资料','统计报表','系统管理','地图'];
    var selectState  = ['请选择','正常','暂停'];
    var selectRole   = ['请选择','超级管理员','测试'];
    var operateState = ['请选择','停运','报废','正常','维修'];
    var carnumbers   = ['按车牌号排序','按创建时间排序'];
    var carcolor     = ['请选择','其他','未录入','白色','蓝色','黄色','黑色'];
    var propertys    = ['请选择','千寻物流'];
    var TerminalType = ['请选择','华强II','康达V-1','蓝度车猫'];
    var thisState    = ['请选择','已拆','暂停','正常','脱网'];
    var RunSate      = ['请选择','停运','报废','正常','维修'];
    var BoundTerminal= ['100433','1005410','100070','45765756','325434','2342323','546555','345344','565554','4754444','100010','2343332','235323','211232','1244214'];
    var whetherBind  = ['请选择','未绑定','已绑定'];
    var carGroupType = ['请选择','企业','分公司','车队'];
    var editDataType = ['请选择','功能权限类型','录音音频参数','报警类型','拍照尺寸','照片质量','用户操作类型','电子围栏类型','终端信息','终端信息服务类型','终端类型','终端命令执行状态','车型','车牌颜色','车辆运行状态'];
    var dataRefreshTime= ['5','10','15','20','25','30','35','40','45','50','55','60'];
    var stateType=['显示Acc状态','显示刹车转向状态'];
    var enableDisable  = ['启用','停用'];
    $.each(enableDisable,function(i,item){
        $('<option>').text(item).appendTo("#verificationCode");
        $('<option>').text(item).appendTo("#carCenterMap");
    });
    $.each(stateType,function(i,item){
        $('<option>').text(item).appendTo("#stateType");
    });
    $.each(dataRefreshTime,function(i,item){
        $('<option>').text(item).appendTo("#dataRefreshTime");
    });
    //var weatherName      =['请选择','北京天气','新闻天气'];
    //$.each(weatherName,function(i,item){
    //    $('<option>').text(item).appendTo("#weatherName");
    //});
    $.each(editDataType,function(i,item){
        $('<option>').text(item).appendTo(".editDataType");
    });
    $.each(carGroupType,function(i,item){
        $('<option>').text(item).appendTo("#carGroupType");
        $('<option>').text(item).appendTo("#carGroupTypeOne");
    });
    $.each(whetherBind,function(i,item){
        $('<option>').text(item).appendTo("#whetherBind");
    });
    $.each(BoundTerminal,function(i,item){
        $('<option>').text(item).appendTo("#BoundTerminal");
        $('<option>').text(item).appendTo("#BoundTerminalOne");
    });
    $.each(RunSate,function(i,item){
        $('<option>').text(item).appendTo("#RunSate");
        $('<option>').text(item).appendTo("#RunSateOne");
    });
    $.each(thisState,function(i,item){
        $('<option>').text(item).appendTo("#thisState");
        $('<option>').text(item).appendTo("#thisStateOne");
        $('<option>').text(item).appendTo("#thisStateTwo");
        $('<option>').text(item).appendTo("#thisStateThree");
        $('<option>').text(item).appendTo("#thisStateThrees");
    });
    $.each(TerminalType,function(i,item){
        $('<option>').text(item).appendTo("#TerminalType");
        $('<option>').text(item).appendTo("#TerminalTypeOne");
        $('<option>').text(item).appendTo("#TerminalTypeTwo");
        $('<option>').text(item).appendTo("#TerminalTypeThree");
    });
    $.each(propertys,function(i,item){
        $('<option>').text(item).appendTo("#propertys");
        $('<option>').text(item).appendTo("#propertysOne");
    });
    $.each(carcolor,function(i,item){
        $('<option>').text(item).appendTo("#carColor");
        $('<option>').text(item).appendTo("#carColorOne");
    });
    $.each(carnumbers,function(i,item){
        $('<option>').text(item).appendTo(".carNumbers");
    });
    $.each(operateState,function(i,item){
        $('<option>').text(item).appendTo(".operateState");
    });
    $.each(selectRole,function(i,item){
        $('<option>').text(item).appendTo("#selectRole");
        $('<option>').text(item).appendTo(".selectRole1");
    });
    $.each(selectState,function(i,item){
        $('<option>').text(item).appendTo("#selectState");
        $('<option>').text(item).appendTo(".selectState1");
    });
    $.each(SuperiorData,function(i,item){
        $('<option>').text(item).appendTo("#SuperiorMenu");
        $('<option>').text(item).appendTo("#SuperiorMenuOne");
        $('<option>').text(item).appendTo("#SuperiorMenuTwo");
    });
    $.each(menuData,function(i,item){
        $('<option>').text(item).appendTo($("#selectName"));
        $('<option>').text(item).appendTo($("#selectNameOne"));
        $('<option>').text(item).appendTo($("#selectNameTwo"));
    });
    $.each(data,function(i,item){
        $('<option>').text(item).appendTo($("#selects"));
    });
    $("#selects").on('change',function(){
        var City= $(this).find('option:selected').attr('selected', 'selected').val();
        $("#cargoType").val(City)
    });
    $.each(data1,function(i,item){
        $('<option>').text(item).appendTo($("#selectOne"));
        $('<option>').text(item).appendTo($("#select1Two"));
        $('<option>').text(item).appendTo($("#carLength"));
    });
    $.each(Company,function(i,item){
        $('<option>').text(item).appendTo($("#Company "));
        $('<option>').text(item).appendTo($("#CarLengths"));
    });
    $.each(Carlength,function(i,item){
        $('<option>').text(item).appendTo($("#Carlength"));
    });

    $.each(licenseP,function(i,item){
        $('<option>').text(item).appendTo($("#selectCity"));
        $("<option>").text(item).appendTo($("#carnumberA"))
    });
    $.each(licenseS,function(i,item){
        $('<option>').text(item).appendTo($("#selectCar"));
        $("<option>").text(item).appendTo($("#carnumberB"))
    });
    $.each(carTypes,function(i,item){
        $('<option>').text(item).appendTo($("#carType"));
    });
    $.each(carAxles,function(i,item){
        $('<option>').text(item).appendTo($("#carNuber"));
    });
    $.each(carnumbers,function(index,item){
        $('<option>').text(item).appendTo($("#carnumbers"));
    })
}
selects();
/************click事件集合*******/

/*************车辆管理弹出车辆定位*******************/
function DriverLocation(){

    $.ajax({
        url:'part/Location.html',
        type:'get',
        success:function(data){
            layer.open({
                type: 1,
                title: ['车辆定位', 'font-size:16px;color:white;background-color:#0070db;'],
                area: ['800px', '600px'],
                closeBtn:4,
                content: data
            });
        }
    })
}

/*************推送货源*****************/
function pushsupply(){
    $.ajax({
        url:'part/pushsupply.html',
        type:'get',
        success:function(data){
            layer.open({
                type: 1,
                title: ['查看驾驶员信息', 'font-size:16px;color:white;background-color:#0070db;'],
                area: ['710px', '500px'],
                closeBtn:4,
                content: data
            });
        }
    })

}

/**********指派车辆*************/
function CarAssign(obj){
    var objs = $(".source-mains");
    layer.open({
        type: 1,
        title: ['指派车辆', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['700px', '450px'],
        closeBtn:4,
        content: objs
    });
    var oTd = $(obj).parent(),
        from = oTd.siblings('.from-to').find('.from').text(),
        to =  oTd.siblings('.from-to').find('.to').text();
    $('.Assigncity').html(from+'<img src="newassets/imgs/arrow.png">'+to)
}


/**********评价*************/
function showEvaluate(){
    var objs = $(".carEvaluate");
    layer.open({
        type: 1,
        title: ['驾驶员评价', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['550px', '300px'],
        closeBtn:4,
        content: objs
    });

}
/**********运单管理已完成查看定位*************/
function showMaps(){
    var objs = $(".tableMap");
    layer.open({
        type: 1,
        title: ['查看车辆定位信息', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['600px', '450px'],
        closeBtn:4,
        content: objs
    });

}
/**********运单管理进行中查看定位*************/
function showMap(){
    var objs = $(".tableMap");
    layer.open({
        type: 1,
        title: ['查看车辆定位信息', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['600px', '450px'],
        closeBtn:4,
        content: objs
    });
}
/*********我的钱包兑换*******************/
function exchangeWallet(){
    var objs = $(".exchangeShow");
    layer.open({
        type: 1,
        title: ['兑换话费', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['360px', '340px'],
        closeBtn:4,
        content: objs
    });
}
/*********添加银行卡1*******************/
function addBankcardShow(){
    var objs = $(".addBankcardShow");
    layer.open({
        type: 1,
        title: ['体现银行卡', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['360px', '310px'],
        closeBtn:4,
        content: objs
    });
    /*********添加银行卡2*******************/
    $("#addbackBtn").on('click',function(){
        layer.closeAll();
        var obj = $(".addBankcard");
        layer.open({
            type: 1,
            title: ['添加银行卡', 'font-size:16px;color:white;background-color:#0070db;'],
            area: ['360px', '310px'],
            closeBtn:4,
            content: obj
        });
        $("#backNumber").val("");
        $("#confirmNumber").val("");
        $("#BackHouseholder").val("");
    })


}
/******删除添加银行卡********/
function deleteBackName(obj){
    var otd = $(obj).parent().parent();
    //var otds =$(obj).parent();
    //var back = otds.siblings().find('.backType').text();
    //$('<option/>').text(back).append($("#ReflectSelect"));
    selfConfirm('你确定要删除吗？',function(){
        otd.remove();
    })
}
/*******我的钱包交易记录**********/
function moneyRecord(){
    $.ajax({
        url:'part/Record.html',
        type:'get',
        success:function(data){
            $("#mian-container").html(data);
        }
    })
}
/********退款记录************/
function refund(){
    $.ajax({
        url:'part/refund.html',
        type:'get',
        success:function(data){
            $("#mian-container").html(data);
        }
    })
}
/******我的钱包交易记录查看详情********/
function seeRcords(obj){
    var otd = $(obj).parent(),
        Waybill = otd.siblings().find('.Waybill').text(),
        money = otd.siblings(".money").text(),
        state = otd.siblings(".state").text(),
        time = otd.siblings(".time").text();
    var arr=[];
    var objs = $(".seeRcordDetails");
    layer.open({
        type: 1,
        title: ['交易明细', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['738px', '225px'],
        closeBtn:4,
        content: objs
    });

      arr.push('  <tr>'+
                '<td style="width:270px;"><span>交易类型：</span><a>'+Waybill+'</a></td>'+
                '<td><span>付款金额：</span><a>'+money+'</a></td>'+
                '<td><span>创建时间：</span><a>2016-06-06 14:38:46</a></td>'+
                '</tr>'+
                '<tr>'+
                '<td><span>运单号：</span><a>20160606143872350</a></td>'+
                '<td><span>交易状态：</span><a>'+state+'</a></td>'+
                '<td><span>运单时间：</span><a>'+time+'</a></td>'+
                '</tr>'+
                '<tr>'+
                '<td colspan="5"><span>货源路线：</span><a>自 吉林-通化 到 黑龙江-大庆</a></td>'+
                '</tr>'+
                '<tr>'+
                '<td colspan="5"><span>货源详情：</span><a>普货、数量不详、价格面议、车型不限、车到上货</a></td>'+
    '</tr>');

    $("#seeRcordTable").html(arr.join(""));

}
/*********查看退款详情*************/
function seeRefund(obj){
    var otd = $(obj).parent(),
        cityRoute = otd.siblings('.cityRoute').text(),
        DriverName =otd.siblings().find('.DriverName').text(),
        phone =otd.siblings().find('.phone').text(),
        refundMoney =otd.siblings('.refundMoney').text(),
        state =otd.siblings('.state').text(),
        time =otd.siblings('.time').text();

    var  arr=[];
    $.ajax({
        url:'part/RefundDetails.html',
        type:'get',
        success:function(data){
            $("#mian-container").html(data);
            if(state=="审核中"){
                $('.stateimgs').attr('src','assets/imgs/refund_01.png');
                $('.stateione').text("信息部审核中");
                $('.stateiTwo').text("信息部审核中")
            }
            arr.push(' <tr> '+
                ' <td>'+
            ' <label>货源线路：<a>'+cityRoute+'</a></label>'+
            ' <label>车辆信息：<a>'+DriverName+'-'+phone+'</a></label>'+
            ' <label>退款理由：<a>不需要了不好意思哈！！</a></label>'+
            ' <label>退款凭证：<a>未上传凭证</a></label>'+
            ' <label>申请时间：<a>'+time+'</a></label>'+
            ' <label>处理时间：<a>2016-06-03 14:09:12</a></label>'+
            ' <label>处理理由：<a></a></label>'+
            ' <label>退款凭证：<a>未上传凭证</a></label>'+
            ' </td>'+
            '</tr>');
            $("#tableMoney").append(arr.join(''))
        }
    })
}
/********提现记录跳转*********/
function ReflectRecord(){
    $.ajax({
        url:'part/ReflectRecord.html',
        type:'get',
        success:function(data){
            $("#mian-container").html(data)
        }
    })
}
/*********修改支付密码************/
 function PaymentPassword(){
    $.ajax({
        url:'part/PaymentPassword.html',
        type:'get',
        success:function(data){
            layer.open({
                type: 1,
                title: ['修改支付密码', 'font-size:16px;color:white;background-color:#0070db;'],
                area: ['400px', '300px'],
                closeBtn:4,
                content: data
            });
        }
    })
}
/******体现银行卡***********/
function Reflect(){
    var objs = $(".Reflect");
    layer.open({
        type: 1,
        title: ['体现', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['400px', '300px'],
        closeBtn:4,
        content: objs
    });
}
/*******修改头像*********/
function modifyHead(){
    $.ajax({
        url:'part/imgload.html',
        type:'get',
        success:function(data){
            layer.open({
                type: 1,
                title: ['上传个人头像', 'font-size:16px;color:white;background-color:#0070db;'],
                area: ['450px', '390px'],
                closeBtn:4,
                content: data
            });
        }
    })
}
/*****个人资料手机话费兑换**********/
function exchanges(){
    var objs = $(".exchangeShow");
    layer.open({
        type: 1,
        title: ['兑换话费', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['360px', '340px'],
        closeBtn:4,
        content: objs
    });
}
/***********查看体现记录******************/
function SeeReflect(obj){
    var oTd = $(obj).parent(),
        back = oTd.siblings().find(".back").text(),
        money = oTd.siblings().find(".money").text(),
        state = oTd.siblings().find(".state").text(),
        time = oTd.siblings().find(".time").text();
    var arr=[];
    var objs = $(".backRecord");
    layer.open({
        type: 1,
        title: ['提现明细', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['550px', '340px'],
        closeBtn:4,
        content: objs
    });
    arr.push('<tr>'+
        '<td colspan="4" align="center" class="tableone"> <img class="Recordimgs" src="newassets/imgs/refund_03.png"> </td> </tr>'+
        '  <tr>'+
        '<td style="width: 80px;;">提现金额:</td>'+
        ' <td>'+money+'</td>'+
        ' <td>提现时间:</td>'+
        '<td>'+time+'</td>'+
        ' </tr>'+
        ' <tr>'+
        ' <td>提现银行:</td>'+
        '<td>'+back+'</td>'+
        '<td>提现卡号:</td>'+
        '  <td>6216261000000000018</td>'+
        '</tr>'+
        ' <tr><td class="backboder" style="border-bottom: 1px solid #ccc;" colspan="4">提现日志</td></tr>'+
        '  <tr>'+
        ' <td colspan="2">2016-06-03 10:51:37</td>'+
        ' <td colspan="2"></td>'+
        '  </tr>'+
        ' <tr> <td colspan="2">2016-06-03 10:51:33</td> <td colspan="2">初始化</td>'+
        ' </tr>');
      $("#tableback").html(arr.join(""));
}
/*****个人资料积分兑换**********/
function exchangeData(){
    $.ajax({
        url:'part/exchange.html',
        type:'get',
        success:function(data){
            $("#mian-container").html(data);
        }
    })
}
/**********兑换身份验证***************/
function identitys(){
   var objs = $(".exchangeShows");
       layer.open({
           type: 1,
           title: ['兑换身份验证', 'font-size:16px;color:white;background-color:#0070db;'],
           area: ['360px', '340px'],
           closeBtn:4,
           content: objs
       });
}

/********用户编辑*********/
function editUser(id){
    $.getJSON("settings/userInfo.json?id="+id, function(result){
		if(result.success == false){
			return;
		}
		result = result.data;
		if(!isNull(result)){
			$("#id").val(result.id);
			$("#userNickName").val(result.nickName);
		    $("#userName").val(result.userName);
		    $("#userMobileNo").val(result.userMobileNo);
		    $("#userStatus").val(result.status);
		    $("#userEmail").val(result.userEmail);
		    $("#editRoleId").val(result.roleId);
		}
    });
    var objs = $(".RoleShow ");
    layer.open({
        type: 1,
        title: ['编辑角色管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['700px', '535px'],
        closeBtn:4,
        content: objs
    });
}
/********新增用户***************/
function addUser(){
   $("#id").val("");
   $("#userNickName").val("");
   $("#userName").val("");
   $("#userMobileNo").val("");
   $("#userStatus").val("");
   $("#userEmail").val("");
   $("#editRoleId").val("");
   var obj = $(".RoleShow");
   layer.open({
        type: 1,
        title: ['新增用户管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['700px', '535px'],
        closeBtn:4,
        content: obj
    });
}

/*******新增用户*********/
function newlyRoleAdded(obj){
   var otd = $(obj).parent().parent(),
    nameAdd = otd.siblings().find("#nameAdd").val(),
    selectRoleAdd = otd.siblings().find('#selectRoleAdd option:selected').val(),
    nameLands     = otd.siblings().find("#nameLands").val(),
    selectStateAdd = otd.siblings().find('#selectStateAdd option:selected').val();
    var CreatePerson = '超级管理员';
    //pwdAdd        = otd.siblings().find("#pwdAdd").val(),
    //pwdConfirmAdd = otd.siblings().find("#pwdConfirmAdd").val();
    var arr=[];
        arr.push('<tr>'+
            '<td class="name">'+nameAdd+'</td>'+
            '<td class="LandName">'+nameLands+'</td>'+
            '<td class="state">'+selectStateAdd+'</td>'+
            '<td class="Role">'+selectRoleAdd+'</td>'+
            '<td class="CreatePerson">'+CreatePerson+'</td>'+
            '<td class="time">'+YMDHMS+'</td>'+
            '<td width="170px" >'+
            '<input type="button" class="MenuBtn" value="编辑" onclick="editInformations(this)">'+
            '<input type="button" class="MenuBtn deletes" onclick="deleteThis(this)" value="删除">'+
            '</td>'+
            '</tr>');


    var RoleTable = $("#RoleTable tr").eq(0);
    RoleTable.after(arr);
    layer.closeAll();
}

/***********设置车辆类型**********/
function carTypeName(){
    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {};
    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    var zNodes = [
        {name:"请选择车组", open:true, children:[
            {name:"普通货车",open:true, children:[{name:"中型普通货车"},{name:"重型普通货车"},{name:"危险品货车"}]},
            {name:"客车",open:true,children:[{name:'大型货车'},{name:'中型货车'},{name:'小型货车'}]},
            {name:"专用运输车",open:true,children:[{name:'罐车'},{name:'保温冷藏车'},{name:'其他专用车',children:[{name:'普通货车'}]}]}
        ]}

    ];
    function zTreeOnClick(event, treeId, treeNode) {
        var _this =  treeNode.name;
        $("#carTypeNames").val(_this);
        $("#carTypeNamesOne").val(_this);
        $(".companyCar").hide();
    }
    var setting = {
        callback: {
            onClick: zTreeOnClick
        }
    };
    $(document).ready(function(){
        zTreeObj = $.fn.zTree.init($("#carTypeName"), setting, zNodes);
        zTreeObj = $.fn.zTree.init($("#carTypeNameOne"), setting, zNodes);
    });

}
/*****用户公司角色增加********/
function addRole1(){
    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {};
    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    var zNodes = [
        {name:"请选择车组", open:true, children:[
            {name:"圆通公司",open:true, children:[{name:"辽宁集散中心"},{name:"大连中心"},{name:"测试部门2"},{name:"沈阳中心"},{name:"测试部门3"}]}
            , {name:"成都集散中心",open:true}
        ]}

    ];


    function zTreeOnClick(event, treeId, treeNode) {
        var _this =  treeNode.name;
        $("#companyInputTwo").val(_this)
    }
    var setting = {
        callback: {
            onClick: zTreeOnClick
        }
    };
    $(document).ready(function(){
        zTreeObj = $.fn.zTree.init($("#addTree"), setting, zNodes);
    });

}
/*****设置车辆信息管理行业类型菜单********/
function VehicleManageindustry(){
    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {};
    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    var zNodes = [
              {name:"道路货物运输",open:true, children:[{name:"辽宁集散中心"},{name:"大连中心"},{name:"测试部门2"},{name:"沈阳中心"},{name:"测试部门3"}]},
              {name:"机动车维修",open:true,children:[{name:'摩托车维修'}]},
              {name:"公交运输",open:true,children:[{name:'公交运输'}]}
    ];


    function zTreeOnClick(event, treeId, treeNode) {
        var _this =  treeNode.name;
        $("#companyInputs").val(_this);
        $("#companyInputsOne").val(_this);
        $(".companyCar").hide();
    }
    var setting = {
        callback: {
            onClick: zTreeOnClick
        }
    };
    $(document).ready(function(){
        zTreeObj = $.fn.zTree.init($("#VehicleManageindustry"), setting, zNodes);
        zTreeObj = $.fn.zTree.init($("#VehicleManageindustryOne"), setting, zNodes);
    });

}

/*******编辑角色管理*******/
function editManage(obj){
    var otd = $(obj).parent(),
        userManage = otd.siblings(".name").text();
    var obj = $(".manageRole");
    layer.open({
        type: 1,
        title: ['编辑角色管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['700px', '500px'],
        closeBtn:4,
        content: obj
    });
    $("#namemanage").val(userManage);
}

function addSelectBoxName(){
    //拼接字符串 构建html
    bulidHtml();
    //构建树结构
    bulidZtreeBox();
    //构建html
    function bulidHtml(){
        // 拼接字符 然后添加到person_select中
        var departmentBox = '<ul id="treeOne" class="ztree"></ul>';
        $("#companyManageOne").append(departmentBox);
    }
// 构建树状结构
    function bulidZtreeBox(multi){
        var setting = {
            check: {
                enable: true,
                // chkStyle: "radio",
                chkboxType : {"Y": "s", "N": "ps"}   //Y是选中 n是取消  s是子节点  p是父级
            },
            view: {
                showIcon: false  //不显示图标
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,  //单击选中节点
                onCheck: onCheck,
                // onClick: onClick  //单击
                beforeMouseDown: zTreeBeforeMouseDown
            }
        };
        $.fn.zTree.init($("#treeOne"), setting, getZNodes());

    }
    function zTreeBeforeMouseDown(treeId, treeNode) {
        return false;
    }
// 点击文字选中checkbox框
    function beforeClick(treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeOne");
        zTree.checkNode(treeNode, !treeNode.checked, null, true);
    }
// 绑定checkbox事件
    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeOne");
        alert(treeNode.name)
    }
    /*
     // 构建选择框
     function getCheckedNodes(e, treeId, treeNode){
     var zTree = $.fn.zTree.getZTreeObj("tree");
     var nodes = zTree.getCheckedNodes(true);   //获取所有选中的checkbox框

     var depText = '';
     for (var i = 0, l = nodes.length; i < l; i++) {
     var item = nodes[i];

     var depName = item.name;
     var depId = item.id;
     var deptId = item.tId;
     alert(depName)}
     $(".chooseRi_con").html(depText);
     deldepEvent();

     }	*/
}
/************新增角色管理*************/
function AddmanageOne(){
    var obj = $(".addManageRole");
    layer.open({
        type: 1,
        title: ['新增角色管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['700px', '500px'],
        closeBtn:4,
        content: obj
    })

}


/*******增加角色管理员*******/
function managePreservation(obj){
    var otd = $(obj).parent().parent(),
        nameRole = otd.siblings().find("#nameRole").val(),
        textareas = otd.siblings().find("#textareas").val();
    var arr=[];
    arr.push("<tr>" +
        '<td class="name">'+nameRole+'</td>'+
        '<td class="Create">'+YMDHMS+'</td>'+
        '<td class="Remarks">'+textareas+'</td>'+
        '<td  style="width:170px">'+
        '<input type="button" class="MenuBtn" value="编辑" onclick="editManage(this)">'+
        '<input type="button" class="MenuBtn deletes" onclick="deleteThis(this)" value="删除">'+
        '</td>'+
        '</tr>');
    //验证姓名
    var nameRole = $("#nameRole").val();
    if(isNull(nameRole)){
        selfAlert('请输入角色名称',function(){$("#nameRole").focus();});
        return false;
    }
    if(!isChinese(nameRole)){
        selfAlert('请输入2-20位中文的角色名称',function(){$("#nameRole").focus();});
        return false;
    }
    if (2 > nameRole.length || nameRole.length > 20) {
        selfAlert('请输入2-20位中文的角色名称',function(){$("#nameRole").focus();});
        return false;
    }
    var manegeTable = $("#manegeTable tr").eq(0);
    manegeTable.after(arr);
    layer.closeAll();
    $("#nameRole").val("");
}

/******保存****/

function Terminalsave(){
    var carNameTwo = $("#carNameTwo").val();
    if(isNull(carNameTwo)){
        selfAlert("请输入终端卡号",function(){$("#carNameTwo").focus();});
        return false;
    }
    if(!isNumber(carNameTwo)){
        selfAlert("请输入0到999999任意数字",function(){$("#carNameTwo").focus();});
        return false;
    }
    if(2>carNameTwo.length||10<carNameTwo.length){
        selfAlert("请输入1到10任意数字",function(){$("#carNameTwo").focus();});
        return false;
    }
    var names = '请选择';
    var TerminalTypeTwo = $("#TerminalTypeTwo").val();
    if(TerminalTypeTwo==names){
      selfAlert("请选择终端",function(){$("#TerminalTypeTwo")});
        return false;
    }
    var thisStateTwo = $("#thisStateTwo").val();
    if(thisStateTwo==names){
        selfAlert("请选择设备当前状态",function(){$("#thisStateTwo")});
        return false;
    }
    var phoneNum = $("#phoneNum").val();
    if(isNull(phoneNum)){
        selfAlert("请输入电话号码",function(){$("#phoneNum")});
        return false;
    }
    if (!isMobileNo(phoneNum)) {
        selfAlert("请输入11位数字的有效手机号码！", function(){ $("#phoneNum").focus(); });
        return false;
    }
 layer.closeAll();
    var obj = $(".newAddCarManage");
    layer.open({
        type: 1,
        title: ['编辑车辆管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['720px', '610px'],
        closeBtn:4,
        content: obj
    });
    $("#onlyTerminalOne").val(carNameTwo);
    $("#BoundTerminalOne").find("option:selected").text(carNameTwo);
    $('#TerminalTypeOne').find("option:selected").text(TerminalTypeTwo);
    $('#thisStateOne').find("option:selected").text(thisStateTwo);
}
/******关闭*********/
function endsave(){
    layer.closeAll();
    var obj = $(".newAddCarManage");
    layer.open({
        type: 1,
        title: ['新增车辆管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['720px', '610px'],
        closeBtn:4,
        content: obj
    });
}


/*********保存发布车辆管理*************/
 function TerminalsaveOne(){
     var carNameOne = $("#carNameOne").val();
    if(isNull(carNameOne)){
        selfAlert("请输入车牌号",function(){$('#carNameOne').focus();});
        return false;
    }
    if (!isCharOrNumber(carNameOne)) {
        selfAlert("车牌号为字母A-Z和数字0-9的组合！", function(){ $("#carNameOne").focus(); });
        return false;
    }
    if (carNameOne.length != 5){
        selfAlert("车牌号为5位数组或字母的组合！", function(){ $("#carNameOne").focus(); });
        return false;
    }
    var names = '请选择';
    var carColorOne = $("#carColorOne").val();
    if(carColorOne==names){
        selfAlert("请选择车牌颜色",function(){$("#carColorOne").focus()});
        return false;
    }
    var propertysOne = $("#propertysOne").val();
    if(propertysOne==names){
        selfAlert("请选择所属业户",function(){$("#propertysOne").focus()});
        return false;
    }
    var   cardNumberOne = $("#cardNumberOne").val();
    if(isNull(cardNumberOne)){
        selfAlert("请输入终端卡号",function(){$("#cardNumberOne").focus();});
        return false;
    }
    if(!isNumber(cardNumberOne)){
        selfAlert("请输入0到999999任意数字",function(){$("#cardNumberOne").focus();});
        return false;
    }
    if(2>cardNumberOne.length||10<cardNumberOne.length){
        selfAlert("请输入2到10任意数字",function(){$("#cardNumberOne").focus();});
        return false;
    }
    var onlyTerminalOne = $("#onlyTerminalOne").val();
    if(isNull(onlyTerminalOne)){
        selfAlert("请输入唯一终端卡号",function(){$("#onlyTerminalOne").focus();});
        return false;
    }
    if(!isNumber(onlyTerminalOne)){
        selfAlert("请输入0到999999任意数字",function(){$("#onlyTerminalOne").focus();});
        return false;
    }
    if(2>onlyTerminalOne.length||10<onlyTerminalOne.length){
        selfAlert("请输入2到10任意数字",function(){$("#onlyTerminalOne").focus();});
        return false;
    }
    var TerminalTypeOne = $("#TerminalTypeOne").val();
    if(names==TerminalTypeOne){
        selfAlert("请选择终端类型",function(){$("#TerminalTypeOne").focus();});
    }
    var thisStateOne = $("#thisStateOne").val();
    if(names==thisStateOne){
        selfAlert("请选择设备当前状态",function(){$("#thisStateOne").focus();});
        return false;
    }
    var RunSateOne = $("#RunSateOne").val();
    if(RunSateOne==names){
        selfAlert("运行状态",function(){$("#carColorOne").focus()});
        return false;
    }
    var companyInputOne =$("#companyInputOne").val();
    var carTypeNamesOne =$("#carTypeNamesOne").val();
    var PlaceDepartureOne =$("#PlaceDepartureOne").val();
    var endTimeOne =$("#endTimeOne").val();
    var companyInputs =$("#companyInputs").val();
    var carnamedata=[{
        'carDriver':'王斌'
    },{
        'carDriver':'李鑫'
    },{
        'carDriver':'李艳艳'
    },{
        'carDriver':'王强,'
    }];
    var arr=[];
    $.each(carnamedata,function(index,item){

    });
    arr.push('<tr>'+
        '<td class="carNumbers">'+carNameOne+'</td>'+
        '<td class="color">'+carColorOne+'</td>'+
        '<td class="CardNumber">'+cardNumberOne+'</td>'+
        '<td class="car"></td>'+
        '<td class="state">'+RunSateOne+'</td>'+
        '<td class="carGroup">'+companyInputOne+'</td>'+
        '<td class="carType">'+carTypeNamesOne+'</td>'+
        '<td class="industry">'+companyInputs+'</td>'+
        '<td class="placeOrigin">'+PlaceDepartureOne+'</td>'+
        '<td class="time">'+endTimeOne+'</td>'+
        '<td width="170px" >'+
        '<input type="button" class="MenuBtn" value="编辑" onclick="editVehicleManage(this)">'+
        '<input type="button" class="MenuBtn deletes" onclick="deleteThis(this)" value="删除">'+
        '</td>'+
        '</tr>');
    var vehicleManageTable = $("#vehicleManageTable tr").eq(0);
    vehicleManageTable.after(arr);
    layer.closeAll();
}





/**********关闭终端信息管理编辑**************/
function terminalend(){
    layer.closeAll();
}
/************设置编辑车辆分组管理*****************/

    function editVehicleGroup(obj){
      var otd = $(obj).parent(),
          carGroup = otd.siblings(".carGroup").text(),
          property = otd.siblings(".property").text(),
          companyType = otd.siblings(".companyType").text(),
          licenseWord = otd.siblings(".licenseWord").text(),
          licenseNumber = otd.siblings(".licenseNumber").text(),
          licenseRange = otd.siblings(".licenseRange").text(),
          Contacts = otd.siblings(".Contacts").text(),
          ContactPhone = otd.siblings(".ContactPhone").text(),
          region = otd.siblings(".region").text(),
          superiorGroup = otd.siblings(".superiorGroup").text();
            var obj = $(".editVehicleGroup");
            layer.open({
                type: 1,
                title: ['编辑车辆分组管理', 'font-size:16px;color:white;background-color:#0070db;'],
                area: ['660px', '400px'],
                closeBtn:4,
                content: obj
            });
         $("#carGroupName").val(carGroup);
         $("#EditTreeThree").val(superiorGroup);
         $("#TheUser").val(property);
         $("#carGroupType").val(companyType);
         $("#licenseWord").val(licenseWord);
         $("#licenseNumber").val(licenseNumber);
         $("#licenseRange").val(licenseRange);
         $("#region").val(region);
         $("#Contacts").val(Contacts);
         $("#ContactPhone").val(ContactPhone);

}
/*******设置新增车辆分组管理********/
function addVehicleGroup(){
    var obj = $(".addVehicleGroup");
    layer.open({
        type: 1,
        title: ['编辑车辆分组管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['660px', '400px'],
        closeBtn:4,
        content: obj
    });
}
function VehicleGroupSave(){
    var TheUserOne  = $("#TheUserOne").val();
    var EditTreeOne = $("#EditTreeOne").val();
    var carGroupTypeOne = $("#carGroupTypeOne").val();
    var licenseWordOne = $("#licenseWordOne").val();
    var licenseNumberOne = $("#licenseNumberOne").val();
    var licenseRangeOne = $("#licenseRangeOne").val();
    var ContactsOne = $("#ContactsOne").val();
    var ContactPhoneOne = $("#ContactPhoneOne").val();
    var regionOne = $("#regionOne").val();
  var carGroupNameOne = $("#carGroupNameOne").val();
    if(isNull(carGroupNameOne)){
        selfAlert("请输入车组名称",function(){$("#carGroupNameOne").focus();});
        return false;
    }
    var name ='请选择';
    if(EditTreeOne==name){
        selfAlert("请输入上级部门",function(){$("#EditTreeOne").focus();});
        return false;
    }
    if(carGroupTypeOne==name){
        selfAlert("请输入车组类型",function(){$("#carGroupNameOne").focus();});
        return false;
    }


    var arr=[];
    arr.push('<tr>' +
        '<td class="carGroup">'+carGroupNameOne+'</td>' +
        '<td class="property">'+TheUserOne+'</td>' +
        '<td class="companyType">'+carGroupTypeOne+'</td>' +
        '<td class="licenseWord">'+licenseWordOne+'</td>' +
        '<td class="licenseNumber">'+licenseNumberOne+'</td>' +
        '<td class="licenseRange">'+licenseRangeOne+'</td>' +
        '<td class="Contacts">'+ContactsOne+'</td>' +
        '<td class="ContactPhone">'+ContactPhoneOne+'</td>' +
        '<td class="region">'+regionOne+'</td>' +
        '<td class="superiorGroup">'+EditTreeOne+'</td>' +
        '<td width="170px" >'+
        '<input type="button" class="MenuBtn" value="编辑" onclick="editVehicleGroup(this)">'+
        '<input type="button" class="MenuBtn deletes" onclick="deleteThis(this)" value="删除">'+
        '</td>'+
        '</tr>');
    var vehicleGroupTable = $("#vehicleGroupTable tr").eq(0);
    vehicleGroupTable.after(arr);
    layer.closeAll();
}
//系统时间
var currDate = new Date();
var d = new Date();
var YMDHMS = d.getFullYear() + "-" +"0"+(d.getMonth()+1) + "-" + d.getDate() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();



/***********设置驾驶员管理编辑*********/
function editDriverManage(obj){
    var objs = $(".driverManage");
    layer.open({
        type: 1,
        title: ['编辑驾驶员信息', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['720px', '570px'],
        closeBtn:4,
        content: objs
    });
    var otd = $(obj).parent(),
        number = otd.siblings(".number").text(),
        driverName = otd.siblings(".name").text(),
        ID = otd.siblings(".ID").text(),
    phone = otd.siblings(".phone").text(),
    certificate = otd.siblings(".certificate").text(),
    Issuing = otd.siblings(".Issuing").text();
     $("#driverNumber").val(number);
     $("#driverName").val(driverName);
     $("#ID").val(ID);
     $("#phone").val(phone);
     $("#certificate").val(certificate);
     $("#Issuing").val(Issuing);
}
/*****新增驾驶员管理*********/
function newAddDriverManage(){
    var objs = $(".newAddDriverManage");
    layer.open({
        type: 1,
        title: ['编辑驾驶员信息', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['720px', '570px'],
        closeBtn:4,
        content: objs
    });
}
/**********新增驾驶员管理************/
function  driverManagePreservation(){
    var driverNameOne = $("#driverNameOne").val();
    if(isNull(driverNameOne)){
        selfAlert("请输入驾驶员名字",function(){$("#driverNameOne").focus();});
        return false;
    }
    if (2 > driverNameOne.length || driverNameOne.length > 10) {
        selfAlert("请输入长度为2-10个字的驾驶员名字！", function(){ $("#driverNameOne").focus(); });
        return false;
    }
    var names = '请选择';
    var depIdOne = $("#depIdOne").val();
    if(names==depIdOne){
        selfAlert("请选择驾驶车辆",function(){$("#vehicleId").focus();});
        return false;
    }
    var phoneOne = $("#phoneOne").val();
    if (isNull(phoneOne)) {
        selfAlert("请输入11位数字的手机号码！", function(){ $("#phoneOne").focus(); });
        return false;
    }
    if (!isMobileNo(phoneOne)) {
        selfAlert("请输入11位数字的有效手机号码！", function(){ $("#phoneOne").focus(); });
        return false;
    }
    var  certificateOne = $("#certificateOne").val();
    if(isNull(certificateOne)){
        selfAlert("请输入从业资格证！", function(){ $("#phoneOne").focus(); });
        return false;
    }
    var  CertificationOne = $("#CertificationOne").val();
    if(isNull(CertificationOne)){
        selfAlert("请输入发证机构！", function(){ $("#CertificationOne").focus(); });
        return false;
    }
    var arr=[];
  var driverNumberOne =$("#driverNumberOne").val();
  var ID =$("#ID").val();
    $(".vehicleId").each(function(i,o){
        if(i == currentShowCar){
         var    nameson =   $(".vehicleId").eq(i).val();
            arr.push('<tr>' +
                '<td class="number">'+driverNumberOne+'</td>' +
                '<td class="name">'+driverNameOne+'</td>' +
                '<td class="ID">'+ID+'</td>' +
                '<td class="phone">'+phoneOne+'</td>' +
                '<td class="Practitioners">'+certificateOne+'</td>' +
                '<td class="Issuing">'+CertificationOne+'</td>' +
                '<td class="drivingVehicle">'+nameson+'</td>' +
                '<td width="170px" >'+
                '<input type="button" class="MenuBtn" value="编辑" onclick="editDriverManage(this)">'+
                '<input type="button" class="MenuBtn deletes" onclick="deleteThis(this)" value="删除">'+
                '</td>'+
                '</tr>');
        }
    });
    var driverManageTable = $("#driverManageTable tr").eq(0);
    driverManageTable.after(arr);
    layer.closeAll();
}
/**********编辑业户管理************/
function editownerProperty(obj){
      var otd = $(obj).parent(),
          companyName = otd.siblings('.companyName').text(),
          businessLicense = otd.siblings('.businessLicense').text(),
          codeCertificate = otd.siblings('.codeCertificate').text(),
                    scope = otd.siblings('.scope').text(),
                 Contacts = otd.siblings('.Contacts').text(),
                    phone = otd.siblings('.phone').text(),
           drivingVehicle = otd.siblings('.drivingVehicle').text();
    var objs = $(".editOwnerProperty");
    layer.open({
        type: 1,
        title: ['编辑业户管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['660px', '370px'],
        closeBtn:4,
        content: objs
    });
    /*编辑值*/
    $("#companyName").val(companyName);
    $("#businessScope").val(scope);
    $("#contactPerson").val(Contacts);
    $("#contactPhone").val(phone);
    $("#businessLicense").val(businessLicense);
    $("#codeCertificate").val(codeCertificate);
}
/*****新增业户管理*********/
function addNewOwnerProperty(){
    var objs = $(".addNewOwnerProperty");
    layer.open({
        type: 1,
        title: ['新增业户管理', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['660px', '370px'],
        closeBtn:4,
        content: objs
    });
}
/*******业户管理保存*****/
function ownerPropertySaved(){
    //业户管理验证
    var companyNameOne = $("#companyNameOne").val();
    if(isNull(companyNameOne)){
        selfAlert("请输入业户名称",function(){$("#companyNameOne").focus();});
        return false;
    }
    if(!isChineseAndChar(companyNameOne)){
        selfAlert("请输入2~20个中文的业户名称",function(){$("#companyNameOne").focus();});
        return false;
    }
    if (2 > companyNameOne.length || companyNameOne.length > 20) {
        selfAlert("请输入长度2~20个中文的业户名称！", function(){ $("#companyNameOne").focus(); });
        return false;
    }

    var contactPersonOne = $("#contactPersonOne").val();
    if(isNull(contactPersonOne)){
        selfAlert("请输入联系人",function(){$("#contactPersonOne").focus();});
        return false;
    }
    if(!isChineseAndChar(contactPersonOne)){
        selfAlert("请输入2~10个中文的联系人",function(){$("#contactPersonOne").focus();});
        return false;
    }
    if (2 > contactPersonOne.length || contactPersonOne.length > 10) {
        selfAlert("请输入长度2~10个中文的联系人！", function(){ $("#contactPersonOne").focus(); });
        return false;
    }
    var contactPhoneOne = $("#contactPhoneOne").val();
    if (isNull(contactPhoneOne)) {
        selfAlert("收货人电话不能为空！", function(){ $("#contactPhoneOne").focus(); });
        return false;
    }
    if (!isPhoneNumber(contactPhoneOne)) {
        selfAlert("请输入正确的电话号码<br/>支持11位手机号及7~8位座机号<br/>如需加区号，如:&nbsp;028-85158955", function(){ $("#contactPhoneOne").focus(); });
        return false;
    }
    //获得input的值
    var businessScopeOne   = $("#businessScopeOne").val();
    var businessLicenseOne = $("#businessLicenseOne").val();
    var codeCertificateOne = $("#codeCertificateOne").val();
    var addressOne         = $("#addressOne").val();
    var arr=[];
    arr.push('<tr>' +
        '<td class="companyName">'+companyNameOne+'</td>' +
        '<td class="businessLicense">'+businessLicenseOne+'</td>' +
        '<td class="codeCertificate">'+codeCertificateOne+'</td>' +
        '<td class="scope">'+businessScopeOne+'</td>' +
        '<td class="Contacts">'+contactPersonOne+'</td>' +
        '<td class="phone">'+contactPhoneOne+'</td>' +
        '<td class="drivingVehicle">'+addressOne+'</td>' +
        '<td width="170px" >'+
        '<input type="button" class="MenuBtn" value="编辑" onclick="editownerProperty(this)">'+
        '<input type="button" class="MenuBtn deletes" onclick="deleteThis(this)" value="删除">'+
        '</td>'+
        '</tr>');
    var ownerPropertyTable = $("#ownerPropertyTable tr").eq(0);
    ownerPropertyTable.after(arr);
    layer.closeAll();
}


/****弹出gps查询车辆*****/
function carNumbers(){
    var objs = $(".carNumberName");
    layer.open({
        type: 1,
        title: ['查询车辆', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['824px', '500px'],
        closeBtn:4,
        content: objs
    });
}
/******编辑信息发布维护*******/
function editMessageMaintain(obj){
   var otd = $(obj).parent(),
       messageType = otd.siblings('.messageType').text(),
       message     = otd.siblings('.message').text();
    var objs = $(".messageMaintain");
    layer.open({
        type: 1,
        title: ['编辑信息维护', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['420px', '200px'],
        closeBtn:4,
        content: objs
    });
    $("#weatherName").val(messageType);
    $("#message").val(message);
}
/****新增信息发布维护**********/
function addNewMessageMaintain(){
    var objs = $(".addNewMessageMaintain");
    layer.open({
        type: 1,
        title: ['新增信息发布维护', 'font-size:16px;color:white;background-color:#0070db;'],
        area: ['420px', '200px'],
        closeBtn:4,
        content: objs
    });
    //新增信息清空
    $("#weatherNameOne").val("请选择");
    $("#messageOne").val("");
}
/********新增信息发布**********/
function messageSaved(){
    var names = '请选择';
    var weatherNameOne = $("#weatherNameOne").val();
    if(names==weatherNameOne){
        selfAlert("请选择信息类型",function(){$("#weatherNameOne").focus();});
        return false;
    }
    var messageOne = $("#messageOne").val();
    if(isNull(messageOne)){
        selfAlert("请输入信息",function(){$("#messageOne").focus();});
        return false;
    }
    if(messageOne.length<2||messageOne.length>50){
        selfAlert("请输入2~50个中文信息",function(){$("#messageOne").focus();});
        return false;
    }
    var messageArr=[];
        messageArr.push('<tr>' +
            '<td class="messageType">'+weatherNameOne+'</td>' +
            '<td class="message">'+messageOne+'</td>' +
            '<td class="establish">'+YMDHMS+'</td>' +
            '<td width="170px" >'+
            '<input type="button" class="MenuBtn" value="编辑" onclick="editMessageMaintain(this)">'+
            '<input type="button" class="MenuBtn deletes" onclick="deleteThis(this)" value="删除">'+
            '</td>'+
            '</tr>');
    var messageTableTr = $("#messageTable tr").eq(0);
    messageTableTr.after(messageArr);
    layer.closeAll();
}
/*****菜单********/
function EditRole1(){
    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {};
    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    var zNodes = [
        {name:"请选择车组", open:true, children:[
            {name:"圆通公司",open:true, children:[{name:"辽宁集散中心"},{name:"大连中心"},{name:"测试部门2"},{name:"沈阳中心"},{name:"测试部门3"}]}
            , {name:"成都集散中心",open:true}
        ]}

    ];


    function zTreeOnClick(event, treeId, treeNode) {
        var _this =  treeNode.name;
        $("#companyInput").val(_this);
        $("#EditTreeThree").val(_this);
        $("#companyInputOne").val(_this);
        $("#EditTreeOne").val(_this);
        $(".companyCar").hide();
    }
    var setting = {
        callback: {
            onClick: zTreeOnClick
        }
    };
    $(document).ready(function(){
        zTreeObj = $.fn.zTree.init($("#EditTreeOne"), setting, zNodes);
        zTreeObj = $.fn.zTree.init($("#EditTrees"), setting, zNodes);
        zTreeObj = $.fn.zTree.init($("#EditMenu"), setting, zNodes);
        zTreeObj = $.fn.zTree.init($("#treeThree"), setting, zNodes);
        zTreeObj = $.fn.zTree.init($("#AddOne"), setting, zNodes);
    });
}


function long2DateStr(time){
    var prefixDate = function(val){
        return val < 10 ? "0"+val : val;
    }

    if(!time || time == "") return "";
    if(typeof(time) == "string"){
        return time;
    }

    var newDate = new Date(time);
    return newDate.getFullYear()+"-"+prefixDate(newDate.getMonth()+1)+"-"+prefixDate(newDate.getDate())+" "+prefixDate(newDate.getHours())+":"+prefixDate(newDate.getMinutes())+":"+prefixDate(newDate.getSeconds());
}
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function bindSelectTime(domId,format,maxDate,callback) {
    // var datepickerOptions = {
    //     dateCell:"#datepicker", //目标元素。由于jedate.js封装了一个轻量级的选择器，因此dateCell还允许你传入class、tag这种方式 '#id .class'
    //     format:"YYYY-MM-DD hh:mm:ss", //日期格式
    //     minDate:"1900-01-01 00:00:00", //最小日期
    //     maxDate:"2099-12-31 23:59:59", //最大日期
    //     isinitVal:false, //是否初始化时间
    //     isTime:true, //是否开启时间选择
    //     isClear:true, //是否显示清空
    //     festival:false, //是否显示节日
    //     zIndex:999,  //弹出层的层级高度
    //     marks:null, //给日期做标注
    //     choosefun:function(val) {},  //选中日期后的回调
    //     clearfun:function(val) {},   //清除日期后的回调
    //     okfun:function(val) {}       //点击确定后的回调
    // };
    // jeDate(datepickerOptions);

    var date=new Date();
    date.setMonth(date.getMonth()+1);
    var curryTimelong = date.Format("yyyy-MM-dd hh:mm:ss");
    // $.jeDate('#establishingTime',{trigger:false,isTime:true,maxDate:$.nowDate(0),multiPane:true,onClose:false,format:'YYYY-MM-DD'})
    $.jeDate('#'+domId,{minDate:"2010-01-01 00:00:00",maxDate:maxDate,isTime:false,format:format,isClear:true,okfun:function(val) {!!callback && callback(val)},choosefun:function(val) {!!callback && callback(val)}})
}
/**
 * 用户状态对应的名称
 * @param status
 * @returns {string}
 */
function getFkhUserStatusName(status) {
//        public static final int FKH_USER_STATUS_WAIT = 0;
//        public static final int FKH_USER_STATUS_ACTIVITY = 1;
//        public static final int FKH_USER_STATUS_NOT = 2;
//        public static final int FKH_USER_STATUS_DELETE = 9;
    var name="正常";
    switch (status){
        case 0:
            name="待审核";
            break;
        case 2:
            name="不通过";
            break;
        case 9:
            name="已停用";
            break;
    }
    return name;
}



function showStatus(status) {
    /** 状态：0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过） */
    switch (status) {
        case 0:
            return "待审核";
        case 1:
            return "正常";
        case 2:
            return "审核不通过";

    }
}
