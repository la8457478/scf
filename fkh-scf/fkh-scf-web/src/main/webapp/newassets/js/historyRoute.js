/**
 * 历史轨迹回放类
 */
HistoryRoutePanel = {};
HistoryRoutePanel._isOver = true;// 播放结束标志
HistoryRoutePanel._isPlay = false;// 正在播放的状态
HistoryRoutePanel.timerId = null;// 定时器ID
HistoryRoutePanel.playSpeed = 30;// 播放速度,
HistoryRoutePanel._autoChangePage = true;// 自动翻页播放
HistoryRoutePanel.totalNum = 0;// 总记录数
HistoryRoutePanel.maxTimeSpan = 300;// 最大播放间隔,每隔200ms，播放一次
HistoryRoutePanel.ByAreaAndTime = false;// 定时定区域轨迹查询
HistoryRoutePanel._isFromBtn = false;// 历史轨迹表格的按钮控制
HistoryRoutePanel._isClickStop = false;//是否点击了停止按钮


// 返回地图对象
HistoryRoutePanel.getRouteMapHandler = function() {
    return this.routeMapHandler;
};
// 查询开始时间
HistoryRoutePanel.getStartTime = function() {
    var startTime = $("#routeStartTime").datetimebox('getText');
    return startTime;
};
// 查询结束时间
HistoryRoutePanel.getEndTime = function() {
    var endTime = $("#routeEndTime").datetimebox('getText');
    return endTime;
};

// 设置查询条件中的车牌号输入控件
HistoryRoutePanel.setPlateNo = function(plateNo) {
    $("#routePlateNo").textbox('setText', plateNo);
};

HistoryRoutePanel.isButtonDisabled = function(btnId) {
    var opts = $("#" + btnId).linkbutton("options");
    return true == opts.disabled;
};

HistoryRoutePanel.setButtonText = function(btnId, txt) {
    $("#" + btnId + " .l-btn-text").html(txt);
};

HistoryRoutePanel.resetButton = function() {
    HistoryRoutePanel.setButtonText("btnQueryHisData", "查询");
    $("#btnQueryHisData").linkbutton('enable');
    $("#btnPlayRoute").linkbutton('disable');
    $("#btnPauseRoute").linkbutton('disable');
    $("#btnStopPlayRoute").linkbutton('disable');
};

HistoryRoutePanel.init = function() {
    var me = this;

    this.createHistoryRouteGrid();

    //查询按钮点击事件
    $("#btnQueryHisData").click(function() {
        if(me.isButtonDisabled("btnQueryHisData")){
            return;
        }

        var plateNo = $("#routePlateNo").textbox("getText");
        if(isNull(plateNo)){
            $.messager.alert("提示", "请输入有效车牌号!");
            return;
        }
        plateNo = plateNo.replace("-","");
        if(!isCarNumber(plateNo)) {
            $.messager.alert("提示", "请输入有效车牌号!");
            return;
        }

        HistoryRoutePanel.queryHistoryRoute();
        HistoryRoutePanel._isClickStop=false;
    });

    //播放按钮点击事件
    $("#btnPlayRoute").click(function() {
        if (me.isButtonDisabled("btnPlayRoute"))
            return;
        me.play();
        HistoryRoutePanel._isFromBtn=true;
        HistoryRoutePanel._isClickStop=false;
        $(".pagination-num").attr("disabled",true);
        $(".pagination-page-list").attr("disabled",true);
    });

    //暂停按钮点击事件
    $("#btnPauseRoute").click(function() {
        if (me.isButtonDisabled("btnPauseRoute"))
            return;
        me.pause();
        HistoryRoutePanel._isFromBtn=false;
        HistoryRoutePanel._isClickStop=false;
        $(".pagination-num").attr("disabled",false);
        $(".pagination-page-list").attr("disabled",false);
    });

    //停止按钮点击事件
    $("#btnStopPlayRoute").click(function() {
        if (me.isButtonDisabled("btnStopPlayRoute"))
            return;
        me.stopPlay();
        $(".pagination-num").attr("disabled",false);
        $(".pagination-page-list").attr("disabled",false);
        HistoryRoutePanel._isPlay=false;
        HistoryRoutePanel._isClickStop=true;
    });

    // excel导出
    $("#btnExportHisData").click(function() {
        me.exportExcel();
    });
    // $("#playSpeedSlider").slider({
    //     disabled:true
    // });
    // 播放速度滑块
    $('#playSpeedSlider').slider({
                                     value : 1,
                                     showTip : true,
                                     tipFormatter : function(value) {
                                         var descr = "慢";
                                         if (value > 60)
                                             descr = "快";
                                         else if (value > 40)
                                             descr = "中";
                                         return descr;
                                     },
                                     onChange : function(value) {

                                         value = parseInt(value);
                                         value = value <= 10 ? 10 : value;
                                         if(value > 60){
                                             me.playSpeed = value * 15; //900~1500
                                         }else if(value > 40) {
                                             me.playSpeed = value * 10; //400~600
                                         }else{
                                             me.playSpeed = value * 6 //0~240
                                         }
                                         console.log(me.playSpeed);
                                         //me.playSpeed = me.maxTimeSpan * (110 - parseInt(value)) / 100;
                                     }
                                 });

    $("#cbDisplayHisData").click(function() {
        // 判断是否已经打勾
        if ($("#cbDisplayHisData").attr('checked') == "checked") {
            $('#hisRouteLayout').layout('expand', 'south');
            $(".pagination-page-list").attr("disabled",false);
        } else {
            $('#hisRouteLayout').layout('collapse', 'south');
        }
    });
    $('#hisRouteLayout').layout('collapse', 'south');

    // 设置在线地图路径
    var mapUrl = globalConfig.mapPath;
    if (mapUrl.indexOf('?') < 0){
        mapUrl += '?hisRoute=true';
    }else{
        mapUrl += '&hisRoute=true';
    }
    $("#routeMapFrame").attr("src", mapUrl);

    // 日期类型拉框触发事件，快捷的选择日期
    $('#selDateOption')
        .combobox(
            {
                onChange : function(newValue, oldValue) {
                    var filterType = newValue;
                    var now = Utility.today();
                    if (filterType == "1") {
                        // 当天
                        var startTime = now + " 00:00:00";
                        var endTime = now + " 23:59:00";
                        $("#routeStartTime").datetimebox('setValue',
                                                         startTime);
                        $("#routeEndTime").datetimebox('setValue',
                                                       endTime);

                    } else if (filterType == "2") {
                        // 昨天
                        var now = new Date();
                        var date1 = new Date(now.getFullYear(), now
                            .getMonth(), now.getDate());
                        date1 = Utility.addDay(date1, -1);
                        var yesterday = Utility.dateToString(date1,
                                                             "yyyy-MM-dd");
                        var startTime = yesterday + " 00:00:00";
                        var endTime = yesterday + " 23:59:00";
                        $("#routeStartTime").datetimebox('setValue',
                                                         startTime);
                        $("#routeEndTime").datetimebox('setValue',
                                                       endTime);
                    } else if (filterType == "3") {
                        // 前天
                        var now = new Date();
                        var date1 = new Date(now.getFullYear(), now
                            .getMonth(), now.getDate());
                        date1 = Utility.addDay(date1, -2);
                        var yesterday = Utility.dateToString(date1,
                                                             "yyyy-MM-dd");
                        var startTime = yesterday + " 00:00:00";
                        var endTime = yesterday + " 23:59:00";
                        $("#routeStartTime").datetimebox('setValue',
                                                         startTime);
                        $("#routeEndTime").datetimebox('setValue',
                                                       endTime);

                    } else if (filterType == "4") {
                        // 最近一天
                        var date1 = new Date();
                        var endTime = Utility.dateToString(date1,
                                                           "yyyy-MM-dd HH:mm:ss");

                        date1 = Utility.addDay(date1, -1);
                        var startTime = Utility.dateToString(date1,
                                                             "yyyy-MM-dd HH:mm:ss");

                        $("#routeStartTime").datetimebox('setValue',
                                                         startTime);
                        $("#routeEndTime").datetimebox('setValue',
                                                       endTime);
                    } else if (filterType == "5") {
                        // 最近一天
                        var date1 = new Date();
                        var endTime = Utility.dateToString(date1,
                                                           "yyyy-MM-dd HH:mm:ss");

                        date1 = Utility.addDay(date1, -2);
                        var startTime = Utility.dateToString(date1,
                                                             "yyyy-MM-dd HH:mm:ss");

                        $("#routeStartTime").datetimebox('setValue',
                                                         startTime);
                        $("#routeEndTime").datetimebox('setValue',
                                                       endTime);
                    }
                }
            });

    // 当选择开始时间的时候，自动检测日期段天数只差，如果超过2天，则自动修改结束时间，
    // 使其最大天数差保持为2.
    $("#routeStartTime")
        .datetimebox(
            {
                'onSelect' : function(date) {
                    var strEndTime = $("#routeEndTime").datetimebox(
                        'getText');
                    startTime = date;
                    endTime = Utility.stringToDate(strEndTime);
                    if (isNaN(startTime.getTime())
                        || isNaN(endTime.getTime())) {
                        $.messager.alert('提示', '非法的日期格式');
                    }
                    var iDays = Utility.getDay(startTime, endTime);
                    if (iDays > 2 || iDays <= 0) {
                        endTime = startTime;
                        // endTime = Utility.addDay(endTime,1);//加一天
                        var strEndTime = Utility.dateToString(endTime,
                                                              "yyyy-MM-dd")
                                         + " 23:59:00";
                        $("#routeEndTime").datetimebox('setValue',
                                                       strEndTime);
                    }

                }
            });

    var now = Utility.today();
    var startTime = now + " 00:00:00";
    var endTime = now + " 23:59:00";
    $("#routeStartTime").datetimebox('setValue', startTime);
    $("#routeEndTime").datetimebox('setValue', endTime);
};

/**
 * 根据查询条件进行excel导出
 */
HistoryRoutePanel.exportExcel = function() {
    var routeForm = $('#routeForm');
    var type = $("#selTypeOption").val();
    var queryUrl = globalConfig.webPath + '/vehicle/gpsInfoList.json?type='+type;
    this.routeMapHandler = this.getHisMapHandler();
    routeForm.form({
                       url : queryUrl,
                       onSubmit : function() {
                           // 进行表单验证
                           // 如果返回false阻止提交
                           var isValid = routeForm.form('validate');
                           if (isValid) {
                               var plateNo = $("#routePlateNo").textbox('getText');
                               var startTime = $("#routeStartTime").datetimebox('getValue');
                               var endTime = $("#routeEndTime").datetimebox('getValue');
                               var minSpeed = $("#minSpeed").val();
                               var excelFileName = "GPS历史轨迹";
                               var queryParams = {
                                   queryId : "selectHisotryGpsInfos",
                                   fileName : excelFileName,
                                   plateNo : plateNo,
                                   startTime : startTime,
                                   endTime : endTime,
                                   minSpeed : minSpeed
                               };
                               var strParam = jQuery.param(queryParams);
                               var url = globalConfig.webPath + "/data/excelExport.action?"
                                         + strParam;
                               // openWindow(url, 300,300,"");
                               window.open(url);
                           }
                           return false;
                       },
                       success : function(data) {
                           // alert(data)
                           // me.hisDataGrid.datagrid("loadData",data);
                       }
                   });
    // 提交表单
    $('#routeForm').submit();
};

/**
 * 历史轨迹数据分页表格
 */
HistoryRoutePanel.createHistoryRouteGrid = function() {
    var queryUrl = globalConfig.webPath + '/vehicle/gpsInfoList.json';
    var queryParams = HistoryRoutePanel.getQueryParams();
    this.hisDataGrid = $("#hisDataGrid");
    this.hisDataGrid.datagrid({
          columns : [ [ {
              title : '时间', field : 'sendTime', width : 130, minWidth : 120
          }, {
              title : '车牌号', field : 'plateNo', width : 80, minWidth : 80, formatter:formatPlateNoe
          }, {
              title : 'SIM卡号', field : 'simNo', width : 120, minWidth : 80
          }, {
              title : '位置', field : 'location', width : 240, minWidth : 240
          }, {
              title : '速度', field : 'velocity', width : 40, minWidth : 40
          }, {
              title : '方向', field : 'directionDescr', width : 80, minWidth : 80
          }, {
              title : '里程', field : 'mileage', width : 60, minWidth : 60
          }, {
              title : '油量', field : 'gas', width : 60, minWidth : 60
          }, {
              title : '海拔', field : 'altitude', width : 40, minWidth : 40
          }, {
              title : '经度', field : 'longitude', width : 90, isSort : false, minWidth : 80
          }, {
              title : '纬度', field : 'latitude', width : 90, isSort : false, minWidth : 80
          }, {
              title : '报警', field : 'alarmStateDescr', width : 0
          }, {
              title : '报警ID', field : 'alarmState', width : 0
          } ] ],
          // height: 150,
          fit : true,
          url : queryUrl,
          method : 'POST',
          queryParams : queryParams,
          // idField: 'gpsId',
          striped : true,
          fitColumns : true,
          singleSelect : true,
          rownumbers : true,
          pagination : true,
          nowrap : true,
          pageSize : 20,
          pageList : [ 20, 50],
          showFooter : true,
          onBeforeLoad : function(param) {
              // 在没有输入查询参数前，不查询
              if (param.plateNo){
                  if(HistoryRoutePanel && HistoryRoutePanel._isPlay && !HistoryRoutePanel.isButtonDisabled("btnStopPlayRoute")){//播放中 不处理查询
                      return false;
                  }else{
                  }
                  return true;
              }else{
                  return false;
              }

          },
          onLoadSuccess : function(data) {
              if (data.rows && data.rows.length > 0) {
                  //HistoryRoutePanel.startPlayRoute(data); // 开始画轨迹
              } else {
                  HistoryRoutePanel.resetButton();
                  //$.messager.alert('提示', '该时间段没有历史轨迹数据');
              }
          }
    });

    return this.hisDataGrid;
};

function formatPlateNoe() {
    return HistoryRoutePanel.getQueryParams().plateNo;
}

// 设置历史轨迹播放的进度
HistoryRoutePanel.setProgress = function(value) {
    $('#routeProgressbar').progressbar('setValue', value);
};

HistoryRoutePanel.getQueryParams = function(){
    var plateNo = $("#routePlateNo").textbox('getText');
    var startTime = $("#routeStartTime").datetimebox('getText');
    var endTime = $("#routeEndTime").datetimebox('getText');
    // 设置轨迹查询类型(1=系统，2=中交)
    var type = $("#selTypeOption").val();
    var minSpeed = $("#minSpeed").val();
    return {
        plateNo : plateNo,
        startTime : startTime,
        endTime : endTime,
        minSpeed : minSpeed,
        type : type
    };
};

// 查询历史轨迹
HistoryRoutePanel.queryHistoryRoute = function() {
    this._isFromBtn=true;
    $(".pagination-num").attr("disabled",true);
    $(".pagination-page-list").attr("disabled",true);

    this.routeMapHandler = this.getHisMapHandler();
    this.routeMapHandler.clearHisRoute();

    var me = this;
    var queryParams = HistoryRoutePanel.getQueryParams();
    queryParams.page = me.pageNo;
    //添加该设置，需要和finishItemCallback的 queryParams.rows 保持一致
    queryParams.rows = 100;
    // 进行表单验证，如果返回false阻止提交
    if (!(me.validateDate() && $('#routeForm').form('validate'))) {
        return;
    }

    $("#btnQueryHisData").linkbutton('disable');
    $("#btnPlayRoute").linkbutton('disable');
    me.pageNo = 1;
    me.setButtonText("btnQueryHisData", "请稍后");

    var type = queryParams.type;
    var queryUrl = globalConfig.webPath + '/vehicle/gpsInfoList.json?less=1';
    $.ajax({
        type: 'post',
        url: queryUrl,
        data: queryParams,
        success: function (resp) {
            if (resp.rows == 0) {
                $("#btnQueryHisData").linkbutton('enable');
                me.setButtonText("btnQueryHisData", "查询");
                var message = resp.error ? resp.error : "该时间段没有历史轨迹数据";
                $.messager.alert('提示', message);
            } else {
                // 有定位记录，加载定位记录表格数据
                me.hisDataGrid.datagrid("load", queryParams);

                //绘制路书
                me.pageNo = 1;
                me.routeMapHandler.clearHisRoute();
                resp.rows[0].plateNo = queryParams.plateNo;
                me.routeMapHandler.drawRoute(resp.rows[0], null, me.playSpeed);//初始化出发点
                me.routeMapHandler.finish = function () {
                   //me.setButtonText("btnQueryHisData", "查询");
                   //$("#btnQueryHisData").linkbutton('enable');
                   me.stopPlay();
                };
                me.setButtonText("btnQueryHisData", "正在回放..");

                //停用播放，启用暂停、停止
                $("#btnQueryHisData").linkbutton('disable');
                $("#btnPauseRoute").linkbutton('enable');
                $("#btnStopPlayRoute").linkbutton('enable');

                var points = [];
                for (var i = 0; i < resp.rows.length; i++) {
                    if(type && type == 2){
                        var tmp=wgs84togcj02(convertGps(resp.rows[i].longitude), convertGps(resp.rows[i].latitude));
                        points.push(gcj02tobd09(tmp[0],tmp[1]));
                    }else{
                        points.push([resp.rows[i].longitude, resp.rows[i].latitude]);
                    }
                }
                if(points.length > 0){
                    //points = amendmentPoints(points);
                    me.routeMapHandler.drawRoute(null, points);
                    me.finishItemCallback();
                }
            }
        }
    });

};

/**
 * 播放完成一段轨迹后，回调获取下一段轨迹
 */
HistoryRoutePanel.finishItemCallback = function(){
    var me = this;
    me.pageNo++;
    var queryParams = HistoryRoutePanel.getQueryParams();
    queryParams.page = me.pageNo;
    queryParams.less = 1;
    //该设置需要和 queryHistoryRoute 的 queryParams.rows 保持一致
    queryParams.rows = 100;
    var type = queryParams.type;
    var queryUrl = globalConfig.webPath + '/vehicle/gpsInfoList.json';
    $.post(queryUrl, queryParams, function(resp){
        if(resp.rows.length > 0){
            var points = [];
            for(var i=0;i<resp.rows.length;i++){
                if(type && type==2){
                    var tmp=wgs84togcj02(convertGps(resp.rows[i].longitude), convertGps(resp.rows[i].latitude));
                    points.push(gcj02tobd09(tmp[0],tmp[1]));
                }else{
                    points.push([resp.rows[i].longitude, resp.rows[i].latitude]);
                }
            }
            //修复queryHistoryRoute中关闭纠偏功能后，这里未关闭
            // points = amendmentPoints(points);
            me.routeMapHandler.drawRoute(null, points);
            me.finishItemCallback();
        }else{
            me.pageNo = 1;
            //$("#btnQueryHisData").linkbutton('enable');
            //$("#btnQueryHisData").linkbutton({text:'暂停'});
        }
    });
};

HistoryRoutePanel.validateDate = function() {
    var startTime = $("#routeStartTime").datetimebox('getText');
    var endTime = $("#routeEndTime").datetimebox('getText');
    startTime = Utility.stringToDate(startTime);
    endTime = Utility.stringToDate(endTime);
    if (isNaN(startTime.getTime()) || isNaN(endTime.getTime())) {
        $.messager.alert('提示', '非法的日期格式');
    }
    var today = new Date();

    var date1 = new Date(startTime.getFullYear(), startTime.getMonth(),
        startTime.getDate());
    var date2 = new Date(endTime.getFullYear(), endTime.getMonth(), endTime
        .getDate());
    if (startTime > today) {
        $.messager.alert('提示', '开始日期不能大于当前日期');
        return false;
    }
    if (startTime >= endTime) {
        $.messager.alert('提示', '开始时间不能大于等于结束时间');
        return false;
    }

    var iDays = Utility.getDay(date1, date2);
    if (iDays > 7) {
        $.messager.alert('提示', '一次只能查询不超过7天的历史数据');
        return false;
    }
    return true;
};

// 重新回到第一页播放
HistoryRoutePanel.firstPage = function() {
    this.hisDataGrid.datagrid("load");
};

HistoryRoutePanel.nextPage = function() {
    // this.hisDataGrid.datagrid('getPager').pagination({pageNumer:2});
    // $(".pagination-next")
    // this.hisDataGrid.datagrid('reload');
    var p = this.hisDataGrid.datagrid('getPager');
    var p1 = p.find(".pagination-next");
    p1.click();
    // p.find(".pagination-next]").click();
    // p.selectPage(
};

HistoryRoutePanel.getCurPageData = function() {
    return this.hisDataGrid.datagrid('getRows');
};

HistoryRoutePanel.getCurRowData = function() {
    return this.hisDataGrid.datagrid('getSelected');
};

HistoryRoutePanel.isLastPage = function() {
    var options = this.hisDataGrid.datagrid('getPager').data("pagination").options;
    var pageSize = options.pageSize;
    var total = options.total;
    if (pageSize == 0 || total == 0)
        return true;
    var pageNum = Math.ceil(total / pageSize);
    return options.pageNumber == pageNum;
};

HistoryRoutePanel.selectRow = function(rowNo) {
    this.hisDataGrid.datagrid('selectRow', rowNo);
};

/**
 * 播放轨迹
 */
HistoryRoutePanel.play = function() {
    //console.log("play....");
    if (!this._isPlay) {
        this._isPlay = true;
        this._isPause = false;

        $("#btnPauseRoute").linkbutton('enable');
        $("#btnPlayRoute").linkbutton('disable');

        HistoryRoutePanel.setButtonText("btnQueryHisData", "正在回放..");

        if (this._isOver == true) {
            // 重新播放时，清除掉原有的轨迹
            this._isOver = false;
            //this.firstPage();
            //this.routeMapHandler.clearHisRoute();
            //return;
        } else {
            // 从暂停处，开始播放
            var me = this;
            // this.timerId = window.setInterval(function() {
            // 	me.PlayWithGrid()
            // }, this.playSpeed);
        }
        $("#btnStopPlayRoute").linkbutton('enable');
    }

    var mapHandler = this.routeMapHandler;
    if (mapHandler) {
        mapHandler.luShu.start();
    }

};

/**
 * 暂停播放轨迹
 */
HistoryRoutePanel.pause = function() {
    //console.log("pause....");
    $("#btnPauseRoute").linkbutton('disable');
    $("#btnPlayRoute").linkbutton('enable');
    this._isPlay = false;
    this._isPause = true;
    if (this.timerId != null) {
        window.clearInterval(this.timerId);
        this.timerId = null;
    }

    var mapHandler = this.routeMapHandler;
    if (mapHandler) {
        mapHandler.luShu.pause();
    }
};

/**
 * 停止播放轨迹
 */
HistoryRoutePanel.stopPlay = function() {
    //console.log("stopPlay....");
    HistoryRoutePanel.resetButton();
    $("#btnPlayRoute").linkbutton('enable');

    window.clearInterval(this.timerId);
    this.timerId = null;
    this._isOver = true;
    this._intervalId = null;
    this.firstPlay = false;
    this._isPlay = false;
    this._nCount = 0;
    this._preLat = null;
    this._preLng = null;
    this._isOver = true;

    var mapHandler = this.routeMapHandler;
    if (mapHandler) {
        mapHandler.drawStop();
        if(mapHandler.luShu){
            mapHandler.luShu.stop();
        }
    }
};

// 得到历史轨迹回放的地图对象，调用baidu.jsp中的地图对象
HistoryRoutePanel.getHisMapHandler = function() {
    var iframeDom = $("#routeMapFrame")[0];
    return iframeDom.contentWindow.OperatorObj;
};

HistoryRoutePanel.startPlayRoute = function(records) {
    $("#btnQueryHisData").linkbutton('disable');
    $("#btnPauseRoute").linkbutton('enable');
    $("#btnStopPlayRoute").linkbutton('enable');
    HistoryRoutePanel.setButtonText("btnQueryHisData", "正在回放..");
    if (this.routeMapHandler == null)
        return;
    // this.routeMapHandler.setGrid(this);
    this.routeMapHandler._playButton = $("#btnPlayRoute");

    // var playSpeed = $('#playSpeed').slider("getValue");//播放速度
    // var maxTimeOut = 200;
    // this._nTimeOut = (maxTimeOut - parseInt(playSpeed));
    var p = this.hisDataGrid.datagrid('getPager');
    var options = this.hisDataGrid.datagrid('getPager').data("pagination").options;
    var curr = options.pageNumber;
    // var total = options.total;
    this.totalNum = options.total;

    var curPageData = this.getCurPageData();
    var curPageSize = curPageData.length;
    var routeMapHandler = this.routeMapHandler;
    this._nCurrentPageSize = curPageSize;
    this._nCount = 0;
    this._isOver = false;
    this._isPause = false;
    this.autoChangePage = (true);
    //如果不是从查询按钮来的，是从表格的翻页而来：只更新数据，不自动播放  add by sj 2017.09.06
    if(HistoryRoutePanel&&(!HistoryRoutePanel._isFromBtn || HistoryRoutePanel._isClickStop)){
        HistoryRoutePanel.setButtonText("btnQueryHisData", "查询");
        $("#btnQueryHisData").linkbutton('enable');
        $("#btnPauseRoute").linkbutton('disable');
        $("#btnStopPlayRoute").linkbutton('disable');
        return false;
    }

    var me = this;
    if (this.timerId) {
        routeMapHandler.drawStop();
        this._timerId = null;
    }
    this.timerId = setInterval(function() {
        me.PlayWithGrid();
    }, this.playSpeed);

};

HistoryRoutePanel.PlayWithGrid = function() {
    if (this.isOver)
        return;

    if (this._nCount == this._nCurrentPageSize) {
        // 当前页的记录已经播放完毕，需要翻页，继续播放
        if (this.isLastPage() == false) {
            if (this._autoChangePage) {
                this._nCount = 0;
                this._isPlay = false;
                if (this.timerId != null) {
                    window.clearInterval(this.timerId);
                    this.timerId = null;
                }
                this.nextPage();
            } else {
                // this._drawPlayWithGrigEnd();
            }
        } else {
            this.stopPlay();// 播放结束
        }
    } else {
        if (!this._isPlay)
            this._isPlay = true;
        // 设置表格显示正在播放的行上
        this.hisDataGrid.select(this._nCount, true);
        this.selectRow(this._nCount);

        var record = this.getCurRowData();
        if (record) {
            record.online = true;
            this.routeMapHandler.drawRoute(record);//画出历史轨迹
            if (this.totalNum > 0) {
                var progress = parseInt(100 * (record.RowNum / this.totalNum));
                this.setProgress(progress);
            }
        }
    }
    this._nCount++;
};


function convertGps(num){
    var cVal = 600000;
    var type = $("#selTypeOption").val();
    if(type && type==2){
        if(parseFloat(num)>1000){
            return parseFloat(num)/cVal;
        }else if(parseFloat(num)<0.001){
            return parseFloat(num) * cVal;
        }else{
            return parseFloat(num);
        }
    }
}
var x_PI = 3.14159265358979324 * 3000.0 / 180.0;
var PI = 3.1415926535897932384626;
var a = 6378245.0;
var ee = 0.00669342162296594323;
/**
 * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换
 * 即谷歌、高德 转 百度
 * @param lng
 * @param lat
 * @returns {*[]}
 */
function gcj02tobd09(lng, lat) {
    var z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_PI);
    var theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_PI);
    var bd_lng = z * Math.cos(theta) + 0.0065;
    var bd_lat = z * Math.sin(theta) + 0.006;
    if(bd_lng<1||bd_lat<1){
        console.log(lng+"__"+lat)
    }
    return [bd_lng, bd_lat]
}

/**
 * WGS84转GCj02
 * @param lng
 * @param lat
 * @returns {*[]}
 */
function wgs84togcj02(lng, lat) {
    if (out_of_china(lng, lat)) {
        if(lng<1||lat<1){
            console.log(lng+"__"+lat)
        }
        return [lng, lat]
    }
    else {
        var dlat = transformlat(lng - 105.0, lat - 35.0);
        var dlng = transformlng(lng - 105.0, lat - 35.0);
        var radlat = lat / 180.0 * PI;
        var magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        var sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
        var mglat = lat + dlat;
        var mglng = lng + dlng;

        if(mglat<1||mglng<1){
            console.log(lng+"__"+lat)
        }
        return [mglng, mglat]
    }
}

function transformlat(lng, lat) {
    var ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
    ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
    ret += (20.0 * Math.sin(lat * PI) + 40.0 * Math.sin(lat / 3.0 * PI)) * 2.0 / 3.0;
    ret += (160.0 * Math.sin(lat / 12.0 * PI) + 320 * Math.sin(lat * PI / 30.0)) * 2.0 / 3.0;
    return ret
}

function transformlng(lng, lat) {
    var ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
    ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
    ret += (20.0 * Math.sin(lng * PI) + 40.0 * Math.sin(lng / 3.0 * PI)) * 2.0 / 3.0;
    ret += (150.0 * Math.sin(lng / 12.0 * PI) + 300.0 * Math.sin(lng / 30.0 * PI)) * 2.0 / 3.0;
    return ret
}

/**
 * 判断是否在国内，不在国内则不做偏移
 * @param lng
 * @param lat
 * @returns {boolean}
 */
function out_of_china(lng, lat) {
    return (lng < 72.004 || lng > 137.8347) || ((lat < 0.8293 || lat > 55.8271) || false);
}
