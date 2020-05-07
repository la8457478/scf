/** @Author: wangly*/
function bulidSelectBox(){
    //拼接字符串 构建html
    bulidHtmlone();
    //构建树结构
    bulidZtreeBoxone();
    $('.person_select').show();
}
//构建html
function bulidHtmlone(){
    // 拼接字符 然后添加到person_select中
    var departmentBox = '<ul id="tree" class="ztree"></ul>'
    $("#treeBox").append(departmentBox);
}
// 构建树状结构
function bulidZtreeBoxone(multi){
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
        },


    };
    $.fn.zTree.init($("#tree"), setting, getZNodes());
}
function zTreeBeforeMouseDown(treeId, treeNode) {
    return false;
};

// 点击文字选中checkbox框
function beforeClick(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
}
// 绑定checkbox事件
function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("tree");
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


