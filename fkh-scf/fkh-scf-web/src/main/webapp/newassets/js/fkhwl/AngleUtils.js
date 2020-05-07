/**
 * Created by Administrator on 2017/9/5.
 */

var Rc = 6378137; //赤道半径
var Rj = 6356725; //南北极半径

function getRad(val){
    return val * Math.PI / 180; //转换弧度
}

function initAngle(longitude, latitude){
    var m_LoDeg = parseInt(longitude);
    var m_LoMin = parseInt((longitude - m_LoDeg) * 60);
    var m_LoSec = (longitude - m_LoDeg - m_LoMin / 60) * 3600;

    var m_LaDeg = parseInt(latitude);
    var m_LaMin = parseInt((latitude - m_LaDeg) * 60);
    var m_LaSec = (latitude - m_LaDeg - m_LaMin / 60) * 3600;

    var m_RadLo = getRad(longitude); //转换弧度
    var m_RadLa = getRad(latitude); //转换弧度

    //计算得出地球半径
    var Ec = Rj + (Rc - Rj) * (90 - latitude) / 90; //赤道半径与极半径的差  = (Rc - Rj) = 21412

    //维度在纬度圈的半径
    var Ed = Ec * Math.cos(m_RadLa);
    return {
        m_LoDeg: m_LoDeg, m_LoMin: m_LoMin, m_LoSec: m_LoSec,
        m_LaDeg: m_LaDeg, m_LaMin: m_LaMin, m_LaSec: m_LaSec,
        longitude: longitude, latitude: latitude,
        m_RadLo: m_RadLo, m_RadLa: m_RadLa,
        Ec:Ec, Ed:Ed
    };
}

/**
 * 获取两点间的距离（单位：米）
 * @param A
 * @param B
 * @returns {number}
 */
function getDistance(A, B){
    var radLat1 = getRad(A.lat); //转换弧度
    var radLat2 = getRad(B.lat); //转换弧度
    var a = radLat1 - radLat2;
    var b = getRad(A.lng) - getRad(B.lng);
    var s = 2 * Math.asin(Math.sqrt(
            Math.pow(Math.sin(a/2),2) + Math.cos(radLat1) *
            Math.cos(radLat2) * Math.pow(Math.sin(b/2),2)));

    s = s * Rc / 1000;
    s = parseInt(s * 10000000 / 10000);

    return s;
}

/**
 * 获取点B相对A的角度(0~360)
 * @param A
 * @param B
 * @returns {number}
 */
function getAngle(A, B){
    A = initAngle(A.lng, A.lat);
    B = initAngle(B.lng, B.lat);
    var dx = (B.m_RadLo - A.m_RadLo) * A.Ed;
    var dy = (B.m_RadLa - A.m_RadLa) * A.Ec;

    var angle = Math.atan(Math.abs(dx / dy)) * 180 / Math.PI;
    var dLo = B.longitude - A.longitude;
    var dLa = B.latitude - A.latitude;
    if (dLo > 0 && dLa <= 0) {
        angle = (90 - angle) + 90;
    } else if (dLo <= 0 && dLa < 0) {
        angle = angle + 180;
    } else if (dLo < 0 && dLa >= 0) {
        angle = (90 - angle) + 270;
    }
    return angle;
}

/**
 * 转换为百度的角度(-180~180)
 * @param angle
 * @returns {*}
 */
function convertToBaidu(angle){
    if(angle < 90){
        angle = angle - 90;
    }else if(angle >= 90 && angle < 180){
        angle = angle - 90;
    }else if(angle >= 180 && angle < 270){
        angle = angle - 90;
    }else if(angle >= 270){
        angle = angle - 360 - 90;
    }

    return angle;
}

function checkAngle(points){
    var temp = 0;
    var newPoints = [];
    console.log("检查前： "+points.length);
    for(var i=0;i<points.length;i++){
        if((i+1) == points.length){
            return newPoints;
        }
        var nowPoint = points[i];
        if(i == 35){
            console.log(111);
        }
        if(temp){
            var pointA = {lng: temp[0], lat:temp[1]};
            var pointB = {lng: nowPoint[0], lat:nowPoint[1]};
            var distance = getDistance(pointA, pointB);
            if(distance > 100){
                temp = nowPoint;
                var prevPoint = newPoints[newPoints.length-1];
                if(!prevPoint){
                    newPoints.push(nowPoint);
                    continue;
                }
                var pointPrev =  {lng: prevPoint[0], lat:prevPoint[1]};
                var angle = getAngle(pointPrev, pointB); //上上个点 和当前点的角度差
                angle = parseInt(angle);
                if((angle - 90 <= 30) && (angle - 90 >= -30)){ //上个点和当前点距离大于100米，角度超过90度，中间90度补点
                    var type;
                    var x1 = [pointB.lng-0.0001, pointA.lat-0.0001];
                    var x = [pointB.lng, pointA.lat];
                    if(angle % 90 >= 30){ //左下补点
                        type = 1;
                        console.log(1);
                        x = [pointA.lng, pointB.lat];
                        x1 = [pointA.lng+0.0001, pointB.lat+0.0001];
                    }else{ //右上补点
                        type = 2;
                    }
                    var d2 = getDistance(pointB, {lng: x[0], lat: x[1]});
                    var d3 = getDistance(pointA, {lng: x[0], lat: x[1]});
                    if(20 < d2 && 20 < d3){ //补点位置和上、下点距离大于20米
                        //newPoints.push(x1);
                        newPoints.push(x);
                        console.log(newPoints.length+": "+d2+"米，角度差: "+(angle)+"，位置补点"+d2+" - "+type);
                    }
                    newPoints.push(nowPoint);
                    x = temp;
                    continue;
                }else{
                    //console.log(newPoints.length+": "+distance+"米，角度差: "+(angle)+"，位置采点");
                }
                newPoints.push(nowPoint);
            }else{
                newPoints.push(nowPoint);
                //console.log("距离： "+distance);
            }
        }else{
            temp = points[i];
            newPoints.push(nowPoint);
        }
    }
    return newPoints;
}

/**
 * 根据拐点纠偏
 * @param points
 * @returns {Array}
 */
function amendmentPoints(points){
    var newPoints = [];
    var pointIndex = 1;
    var prevPointVal = [];
    for(var i=0;i<points.length;i+=3){
        if((i+3) >= points.length){
            return newPoints;
        }
        var p0 = points[i-1];
        p0 = p0 ? p0 : [0, 0];
        var p1 = points[i];
        var p2 = points[i+1];
        var p3 = points[i+2];

        var pointA = {lng: p1[0], lat:p1[1]};
        var pointB = {lng: p2[0], lat:p2[1]};
        var pointC = {lng: p3[0], lat:p3[1]};

        var angle1 = getAngle(pointA, pointB); //夹角方向1
        var angle2 = getAngle(pointB, pointC); //夹角方向2
        var angle3 = getAngle(pointA, pointC); //夹角方向3

        var distance1 = getDistance(pointA, pointB); //夹角距离1
        var distance2 = getAngle(pointB, pointC); //夹角距离2
        var distance3 = getAngle(pointA, pointC); //夹角距离3

        var max = angle1 > angle2 ? " A" : "";
        max = angle2 > angle1 ? " B" : max;
        max = angle3 > angle1 ? " C" : max;

        prevPointVal.push(max);
        var prevLength = prevPointVal.length;

        if(prevLength >= 2
            && prevPointVal[prevLength-1] == max
            && prevPointVal[prevLength-2] == max){ //连续2个点向同一方向行驶

            if(distance3 >= 100){ //3点距离大于等于100米
                if(max == "A"){
                    //console.log(pointIndex+" i="+i+"可能是拐点: "+((angle1 - angle2) +"- "+ (angle1-angle3)) + max);
                    newPoints.push(points[i]);
                    newPoints.push(points[i+1]);
                    newPoints.push(points[i+2]);
                }else if(max == "B"){
                    //console.log(pointIndex+" i="+i+"可能是拐点: "+((angle2 - angle1) +"- "+ (angle2-angle3)) + max);
                    newPoints.push(points[i]);
                    newPoints.push(points[i+1]);
                    newPoints.push(points[i+2]);
                }else{
                    //console.log(pointIndex+" i="+i+"可能是拐点: "+((angle3 - angle1) +"- "+ (angle3-angle2)) + max);
                    newPoints.push(points[i]);
                    newPoints.push(points[i+1]);
                    newPoints.push(points[i+2]);
                }
            }else{ //3点距离小于100米
//                console.log(pointIndex+" i="+i+"可能是拐点: "+((angle3 - angle1) +"- "+ (angle3-angle2)) + max);
                newPoints.push(points[i]);
            }

            prevPointVal = [];
            pointIndex++;
        }else{ //单点运动
//            console.log(pointIndex+" i="+i+"可能是拐点: "+((angle3 - angle1) +"- "+ (angle3-angle2)) + max);
            newPoints.push(points[i]);
//            newPoints.push(points[i+1]);
//            newPoints.push(points[i+2]);
            pointIndex++;
        }
    }

    return newPoints;
}


/**
 * @param points
 * @returns {Array}
 */
function amendmentPointsNew(points){
    var newPoints = [];
    newPoints.push(points[0]);
    var pointIndex = 1;
    for(var i=0;i<points.length;i+=3){
        if((i+3) >= points.length){
            return newPoints;
        }
        var p0 = points[i-1];
        p0 = p0 ? p0 : [0, 0];
        var p1 = points[i];
        var p2 = points[i+1];
        var p3 = points[i+2];

        var pointA = {lng: p1[0], lat:p1[1]};
        var pointB = {lng: p2[0], lat:p2[1]};
        var pointC = {lng: p3[0], lat:p3[1]};

        var angle1 = getAngle(pointA, pointB); //夹角方向1
        var angle2 = getAngle(pointB, pointC); //夹角方向2
        var angle3 = getAngle(pointA, pointC); //夹角方向3

        var distance1 = getDistance(pointA, pointB); //夹角距离1
        var distance2 = getAngle(pointB, pointC); //夹角距离2
        var distance3 = getAngle(pointA, pointC); //夹角距离3

//        addLabel(i, points[i][0], points[i][1]);
//        addLabel(i+1, points[i+1][0], points[i+1][1]);
//        addLabel(i+2, points[i+2][0], points[i+2][1]);

         if(angle1 <= angle2 && angle1 <= angle3){ //直线 a<b<c
            var index = 0;
            if(distance1 < distance2 && distance1 < distance3){ // a<b<c
                index = 0;
            }else if(distance2 < distance1 && distance2 < distance3){ //b<a<c
                index = 1;
            }else if(distance3 < distance1 && distance3 < distance2){ //c<a<b
                index = 2;
            }

            newPoints.push(points[index+i]);

            //console.log(pointIndex+": "+angle1+"->"+angle2 + "->"+angle3+" \t假设1：直线运动, a-b:"+distance1+", b-c: "+distance2+", a-c: "+distance3+"\t "+index);
            //addLabel(pointIndex, points[index+i][0], points[index+i][1]);
            pointIndex++;
        }else if(angle1 <= angle2 && angle2 >= angle3){ // Z字运动  //a<b>c
            //console.log(pointIndex+": "+angle1+"->"+angle2 + "->"+angle3+" \t假设2： S字运动, a-b:"+distance1+", b-c: "+distance2+", a-c: "+distance3);
            //addLabel(pointIndex, points[i][0], points[i][1], true);
            //pointIndex++;
        }else if(angle1 > angle2 && angle1 > angle3){ //a>b>c
            //console.log(pointIndex+": "+angle1+"->"+angle2 + "->"+angle3+" \t假设3： >行运动, a-b:"+distance1+", b-c: "+distance2+", a-c: "+distance3);
            //addLabel(pointIndex, points[i][0], points[i][1], true);
            //newPoints.push(points[i]);
            //pointIndex++;
        }else if(angle1 > angle2 && angle1 > angle3) { //a>b<c
            newPoints.push(points[i]);
        }
    }

    return newPoints;
}


function addLabel(i, lng, lat, isP){
    var point = new BMap.Point(lng, lat);
    var opts = {
        position : point,    // 指定文本标注所在的地理位置
        offset   : new BMap.Size(30, -30)    //设置文本偏移量
    }
    var label = new BMap.Label(i, opts);  // 创建文本标注对象
    var color = isP ? "red" : "blue";
    label.setStyle({
        color : color,
        fontSize : "12px",
        height : "20px",
        lineHeight : "20px",
        fontFamily:"微软雅黑"
    });
    map.addOverlay(label);
}