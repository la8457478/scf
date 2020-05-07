// 判断字符串是否为空字符串
function isNull(str) {
	var re = /^[　\s]*$/;
	return re.test(str);
}
//添加图片验证
function isImage(str){
	var re = /image\/jpg|jpeg|png/;
	return re.test(str);

}
// 判断字符串是否为英文
function isEnglish(str) {
	var re = /^\w*$/;
	return re.test(str);
}
// 判断是否为数字串
function isNumber(str) {
	var re = /^\d*$/;
	return re.test(str);
}
function isNumber8(str) {
	var re = /^[1-9][0-9]{0,8}$/;
	return re.test(str);
}

function isNumber3(str) {
	var re = /^[1-9][0-9]{0,2}$/;
	return re.test(str);
}
function isCargoNumber3_2(str){
    var re = /^\d{1,3}(\.\d{1,2})?$/;
	return re.test(str);
}
function isCargoNumber3_3(str){
    var re = /^\d{1,3}(\.\d{1,3})?$/;
    return re.test(str);
}

function isCargoNumber2_3(str){
	var re=/^\d{1,2}(\.\d{1,3})?$/;
	return re.test(str);
}

function isCargoNumber4_3(str){
	var re = /^\d{1,4}(\.\d{1,3})?$/;
	return re.test(str);
}

function isCargoNumber9_2(str){
    var re=/^\d{1,9}(\.\d{1,2})?$/;
    return re.test(str);
}

function isCargoNumber10_2(str){
    var re=/^\d{1,10}(\.\d{1,2})?$/;
    return re.test(str);
}

// 车辆核载吨位
function isCargoTonnage(str){
    var re = /^[1-9][0-9]{0,2}(\.\d{0,2})?$/;
    return re.test(str);
}

function isCargoNumber3(){
	var re = /^[1-9][0-9]{0,2}$/;
	return re.test(str);
}
function isNumber4(str) {
	var re = /^[1-9][0-9]{0,3}$/;
	return re.test(str);
}
function isNumber5(str) {
	var re = /^[1-9][0-9]{0,4}$/;
	return re.test(str);
}
function isAmountNumber3_1(str){
	var re = /^\d{1,3}(\.\d{0,1})?$/;
	return re.test(str);
}
/*新增验证2位小数*/
function isSmallNumber2(str){
	var re = /^\+?(\d*\.\d{2})$/;
	return re.test(str);
}

// 验证是否为3位小数
function isSmallNumber3(str) {
	var re = /^\d*\.?\d{0,3}$/;
	return re.test(str);
}

/*验证是否为小数，正确版*/
function SmallNumber(str){
    var re = /^\d+\.\d+$/;
    return re.test(str);
}

/*验证计划数量时，整数不超过四位*/
function planNumberOver4(str){
    var re = /^\d+\d{4}(\.\d+)?$/;
    return re.test(str);
}
/*验证计划数量时，整数不超过三位*/
function planNumberOver3(str){
    var re = /^\d+\d{3}(\.\d+)?$/;
    return re.test(str);
}
/*验证计划数量时，小数不超过三位*/
function planSmallNumberOver3(str){
    var re = /^\d+\.\d{3}\d+$/;
    return re.test(str);
}

// 车辆核载吨位2位小数
function isCargoTonnages(str){
	var re =  /^\d+(?:\.\d{1,2})?$/;
	return re.test(str);
}
// 验证是否为小数点后三位
function isSmallNumber3(str) {
    var re = /^\d*\.?\d{0,3}$/;
    return re.test(str);
}

// 验证是否为6位+2位的小数，可以是整数
function isSmall2Number2(str) {
    var re = /^\d{1,2}(\.\d{0,2})?$/;
    return re.test(str);
}

// 验证是否为6位+2位的小数，可以是整数
function isSmall4Number2(str) {
	var re = /^\d{1,4}(\.\d{0,2})?$/;
	return re.test(str);
}
// 验证是否为7位+2位的小数
function isSmall7Number2(str) {
	var re = /^\d{1,7}(\.\d{1,2})?$/;
	return re.test(str);
}
// 验证是否为7位+3位的小数
function isSmall7Number3(str) {
	var re = /^\d{1,7}(\.\d{1,3})?$/;
	return re.test(str);
}

// 验证是否为7位+6位的小数
function isSmall7Number6(str) {
    var re = /^\d{1,7}(\.\d{1,6})?$/;
    return re.test(str);
}

// 验证是否为14位+2位的小数
function isSmall14Number2(str) {
    var re = /^\d{1,14}(\.\d{1,2})?$/;
    return re.test(str);
}

// 验证是否为12位+2位的小数
function isSmall12Number2(str) {
    var re = /^\d{1,12}(\.\d{1,2})?$/;
    return re.test(str);
}

// 判断是否为数字0或1
function isO1(str) {
	var re = /^[01]$/;
	return re.test(str);
}
// 判断是否为颜色代码
function isColor(str) {
	var re = /^#?[0-9|a-f|A-F]{6}$/;
	return re.test(str);
}
// 判断是否为身份证号码
function isChinaId(str) {
	var re = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
	return re.test(str);
}
// 判断邮箱格式是否正确
function isEmail(str) {
	var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,5}){1,2})$/;
	return re.test(str);
}
function isDouble(str) {
	var re = /^[-\+]?\d+(\.\d+)?$/;
	return re.test(str);
}
// 判断空串和空格
function isHasSpace(str) {
	var re = /^\S+$/;
	return re.test(str);
}
// 判断手机号
function isMobileNo(str) {
	var re = /^1[3|4|5|6|7|8|9][0-9]{9}$/;
	return re.test(str);
}
// 判断手机号
function isMobileNoLogin(str) {
	var re = /^1[3|4|5|6|7|8|9][0-9]{9}$/;
	return re.test(str);
}

// 判断手机号及座机号
function isPhoneNumber(str) {
	var re = /^(1[3|4|5|6|7|8|9][0-9]{9})$|^((\d{7,8})|[0]{1}(\d{2,3})-(\d{7,8})|[0]{1}(\d{9,11}))$/;
	return re.test(str);
}
// 是否为中文
function isChinese(str) {
	var re = /^[·.\u4e00-\u9fa5]*$/;
	return re.test(str);
}
// 是否为中文
function isChineseNo(str) {
	var re = /^[\u4e00-\u9fa5]*$/;
	return re.test(str);
}

// 是否为公司名称或项目名称
function isCompanyName(str) {
	var re = /^[A-Za-z0-9\u4e00-\u9fa5-—()（）]*$/;
	return re.test(str);
}
// 是否为中文或字符
function isChineseAndChar(str) {
	var re = /^[·.a-zA-Z\u4e00-\u9fa5]+$/;
	return re.test(str);
}

//判断是否含非法字符(a-zA-Z0-9中文)
function isPassWords(str){
    var re = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
    return re.test(str);
}
//新增判断是否含非法字符(a-zA-Z0-9中文-)
function isPassWordsCar(str){
    var re = /^[a-zA-Z0-9\u4e00-\u9fa5-]+$/;
    return re.test(str);
}
//验证常用字符
function isNormalText(str){
	var re = /^[A-Za-z0-9.+=\-\u4e00-\u9fa5]+$/;
	return re.test(str);
}

//判断是否为车牌号
function isCarNumber(str){
	var re = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Za-z0-9]{5,6}$/;
    return re.test(str);
}
//判断是否含非法字符
function isInvalidContent(str){
	var re = /^[A-Za-z0-9\n\r，。,.！!\u4e00-\u9fa5]+$/;
	return re.test(str);
}

//判断是否含非法字符
function isInvalidContent1(str){
	var re = /^[.\u4e00-\u9fa5]+$/;
	return re.test(str);
}

function isInvalidRating(str){
	var re = /^[A-Za-z0-9 \n\r，。,.！!、~?？“”\u4e00-\u9fa5]+$/;
	return re.test(str);
}
//货源描述
function isInvalidWaybillDesc(str){
	var re = /^[A-Za-z0-9 \n\r，。,.！!、~?？“”()（）[]【】\u4e00-\u9fa5]+$/;
	return re.test(str);
}
//字符和数字
function isCharOrNumber(str){
	var re = /^[A-Za-z0-9]+$/;
	return re.test(str);
}

// function isLoginAccount(str){
// 	var re= /^[a-zA-Z][a-zA-Z0-9_]*$/;
// 	return re.test(str);
// }

//4-20位:  大小写英文 + 数字
function isLoginAccount(str){
    var re= /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,20}$/;
    return re.test(str);
}

//大小写字母数字中文 -—;
function isCharOrNumberOrChinese(str){
	var re = /^[A-Za-z0-9\u4e00-\u9fa5-—]+$/;
	return re.test(str);
}

//大小写字母数字中文;
function isCharOrNumOrChinese(str){
    var re = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
    return re.test(str);
}
//根据单位及值验证磅差值精度
function validatePoundValueByUnit(unit,poundValue){
	if(unit==null || unit==""){
        // selfAlert("请输入6个字以内的货物类型！", function(){ $("#cargoType").focus(); });
        return false;
	}
    if(unit=="吨" ||unit=="件" ||unit=="台"){
		if(!isNumber5(poundValue) && poundValue != 0){
			selfAlert("请输入0~99999的磅差数量！" , function(){});
            return false;
		}
    }else if(unit=="方"){
        if(!isCargoNumber2_3(poundValue) && poundValue != 0){
            selfAlert("请输入0~99.999的磅差数量！", function(){});
            return false;
        }
	}
    return true;
}
//身份证校验
function idCodeValid(code) {
	var tip = "";
	var pass= true;
    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};

    if(!code || !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(code)){
        tip = "身份证号格式错误";
        pass = false;
    }

    else if(!city[code.substr(0,2)]){
        tip = "地址编码错误";
        pass = false;
    }
    else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                tip = "校验位错误";
                pass =false;
            }
        }
    }
    //if(!pass) console.log(tip);

    return pass;
}

/**
 * 校验整数部分（两位数以上）是否以“0”开头,
 * @param val
 * @returns {boolean}
 */
function beginWithZero(val) {
    if (!P.isNullOrEmptyString(val) ){
        var integerAndDecimal = val.split(".");
        var firstChar = integerAndDecimal[0].charAt(0);
        if (firstChar != "-") {
            if (integerAndDecimal[0].length > 1 && integerAndDecimal[0].indexOf("0") == 0) {
                return true;
            }
        } else {
            if (integerAndDecimal[0].length > 2 && integerAndDecimal[0].indexOf("0") == 1) {
                return true;
            }
        }
    }

    return false;
}

/**
 * 验证字符长度范围 : true.在范围之外，false.在范围之内
 * @param val 校验值
 * @param required 校验值是否可以为空
 * @param minLength 最小长度
 * @param maxLength 最大长度
 */
function isLengthOutRange(val, required, minLength, maxLength) {
    if (!P.isNullOrEmptyString(minLength) && !P.isNullOrEmptyString(maxLength)) {
        return required ? (P.isNullOrEmptyString(val) || val.length < minLength || val.length > maxLength)
                        : (!P.isNullOrEmptyString(val) && (val.length < minLength || val.length > maxLength));
    } else if (!P.isNullOrEmptyString(minLength)) {
        return required ? (P.isNullOrEmptyString(val) || val.length < minLength) : (!P.isNullOrEmptyString(val) && val.length < minLength);
    } else if (!P.isNullOrEmptyString(minLength)) {
        return required ? (P.isNullOrEmptyString(val) || val.length > maxLength) : (!P.isNullOrEmptyString(val) && val.length > maxLength);
    } else {
        return required ? P.isNullOrEmptyString(val) : !P.isNullOrEmptyString(val);
    }

}

/**
 * 验证“非必填字段”字符长度范围 : true.不是指定长度的"英文、数字、下划线组成的字符串"，false.是指定长度的"英文、数字、下划线组成的字符串"
 * @param val 校验值
 * @param required 校验值是否可以为空
 * @param minLength 最小长度
 * @param maxLength 最大长度
 */
function isLoginAccountAndLengthOutRange(val, required, minLength, maxLength) {
    return isLengthOutRange(val, required, minLength, maxLength) || !isLoginAccount(val);
}

/**
 * 验证中文字符长度范围 : true.不是指定长度的中文字符，false.是指定长度的中文字符
 * @param val 校验值
 * @param required 校验值是否可以为空
 * @param minLength 最小长度
 * @param maxLength 最大长度
 */
function isChineseCharLengthOutRange(val, required, minLength, maxLength) {
    return isLengthOutRange(val, required, minLength, maxLength) || !isChineseNo(val);
}

/**
 * 验证字符或者数字长度范围 : true.不是指定长度的符或者数字，false.是指定长度的符或者数字
 * @param val 校验值
 * @param required 校验值是否可以为空
 * @param minLength 最小长度
 * @param maxLength 最大长度
 */
function isCharOrNumberLengthOutRange(val, required, minLength, maxLength) {
    return isLengthOutRange(val, required, minLength, maxLength) || !isCharOrNumber(val);
}

/**
 * 验证数字串长度范围 : true.不是指定长度的数字串，false.是指定长度的数字串
 * @param val 校验值
 * @param required 校验值是否可以为空
 * @param minLength 最小长度
 * @param maxLength 最大长度
 */
function isNumberLengthOutRange(val, required, minLength, maxLength) {
    return isLengthOutRange(val, required, minLength, maxLength) || !isNumber(val);
}

/**
 * 验证整数字值范围 : true.不是范围的整数，false.是指定范围的整数
 * @param val 校验值
 * @param required 校验值是否可以为空
 * @param minValue 最小值
 * @param maxValue 最大值
 */
function isNumberValueOutRange(val, required, minValue, maxValue) {
    if (required) {
        return  P.isNullOrEmptyString(val) || beginWithZero(val) || !isNumber(val) || val < minValue || val > maxValue;
    } else {
        return !P.isNullOrEmptyString(val) && !beginWithZero(val) && (!isNumber(val) || val < minValue || val > maxValue);
    }
}

/**
 * 验证值范围 : true.不是范围的整数，false.是指定范围的整数
 * @param val 校验值
 * @param required 校验值是否可以为空
 * @param minValue 最小值
 * @param maxValue 最大值
 */
function isValueOutRange(val, required, minValue, maxValue) {
    if (required) {
        return  P.isNullOrEmptyString(val) || val < minValue || val > maxValue;
    } else {
        return !P.isNullOrEmptyString(val) && (val < minValue || val > maxValue);
    }
}

/**
 * 验证是否为0.01-99.99 : true.不是，false.是
 * @param val 校验值
 * @param required 校验值是否可以为空
 */
function isSmall2Number2OutRange(val, required) {
    return required ? (P.isNullOrEmptyString(val) || beginWithZero(val) || !isSmall2Number2(val) || val <= 0) : (!P.isNullOrEmptyString(val) && !beginWithZero(val) && (!isSmall2Number2(val) || val <=0));
}

//***********************************************************************************************************

//新增凭证信息图片验证
function checkUploadFile() {
	var val = $("#file").val();
	if (val == '') {
		$("#upMessage").html("请选择要上传的图片文件!");
		return false;
	}
	var stuff = val.toLowerCase().replace(/.+\./, "");
	stuff = stuff.toLowerCase();
	if (stuff != 'png' && stuff != 'jpg' && stuff != 'jpeg' && stuff != 'bmp') {
		$("#upMessage").html('图片类型不匹配，请选择正确的图片格式文件!');
		return false;
	}
	return true;
}
//验证发布评价
function validateAddRating(){
	var ratingLevel = $("#ratingLevel").val();
	if(ratingLevel == "1" || ratingLevel == "2"){
		var ratingContent = $("#ratingContent").val();
		if(ratingContent.length > 100 || ratingContent.length < 10){
			$("#errorMsg").html("请输入10~100字以内的差评理由!");
			return false;
		}
	}

	var ratingContent = $("#ratingContent").val();
	if(ratingContent.length > 100){
		$("#errorMsg").html("请输入100字以内的评价内容!");
		return false;
	}
	if("" != ratingContent && !isInvalidRating(ratingContent)){
		$("#errorMsg").html("请输入不含非法字符的评价内容!");
		return false;
	}
	return true;
}

function validateAddWaybill() {
	var defaultD = "请选择出发地";
	var defaultA = "请选择目的地";

	var departureCity = $("#departureCity").val();
	if ("" == departureCity) {
		selfAlert(defaultD);
		$("#departureCity").focus();
		return false;
	}
	//delete by sj 2017.05.03 来源于自定义字典，已经过滤。
	/*
	var departurePoint = $("#departurePoint").val();
	  if(!isNull(departurePoint)){
		  if ("" != departurePoint && departurePoint.length > 20) {
				selfAlert("请输入20个字以内的装货地!");
				$("#departurePoint").focus();
				return false;
			}else if(!isPassWords(departurePoint)){
				selfAlert("装货地不能输入特殊字符!");
				$("#departurePoint").focus();
				return false;
			}
	  }

	var arrivalPoint = $("#arrivalPoint").val();
	if ("" != arrivalPoint && arrivalPoint.length > 20) {
		selfAlert("请输入20个字以内的卸货地!");
		$("#departurePoint").focus();
		return false;
	}*/



	var arrivalCity = $("#arrivalCity").val();
	if ("" == arrivalCity) {
		selfAlert(defaultA);
		$("#arrivalCity").focus();
		return false;
	}


	var cargoType = $("#cargoType").val();
	if("" == cargoType){
		$("#cargoType").val("普货");
	}else if("" != cargoType && cargoType.length > 10){
		selfAlert("请输入6个字以内的货物类型！", function(){ $("#cargoType").focus(); });
		return false;
	}


	var numT = $("#cargoNumT").val();
	var cargoNumN = $("#cargoNumN").val();
	//if(cargoNumN != "不详"){  //货物数量必填，用于统计数据。
		if (isNull(cargoNumN)) {
			selfAlert("货物数量不能为空！", function(){ $("#cargoNumN").focus(); });
			return false;
		}
		if (isNull(cargoNumN)) {
        			selfAlert("货物数量不能为空！", function(){ $("#cargoNumN").focus(); });
        			return false;
        }

		if (!isCargoNumber3_2(cargoNumN)) {
			selfAlert("请输入0.01-999"+numT+"的货物数量！", function(){ $("#cargoNumN").focus(); });
			return false;
		}
		if (!isDouble(cargoNumN)||cargoNumN <= 0 || cargoNumN > 999) {
			selfAlert("请输入0.01-999"+numT+"的货物数量！", function(){ $("#cargoNumN").focus(); });
			return false;
		}
		var numT = $("#cargoNumT").val();
		$("#cargoNum").val(cargoNumN + numT);
	//}else{
	//	$("#cargoNum").val("不详");
	//}

	/*var cargoNumN2 = $("#cargoNumN2").val();
	if(cargoNumN2 != "不详"){
		if (isNull(cargoNumN2)) {
			selfAlert("货物数量不能为空！", function(){ $("#cargoNumN2").focus(); });
			return false;
		}
		if (!isCargoNumber3_2(cargoNumN2)) {
			selfAlert("请输入0.01-999"+numT+"的货物数量！", function(){ $("#cargoNumN2").focus(); });
			return false;
		}
		if (cargoNumN2 <= 0 || cargoNumN2 > 999) {
			selfAlert("请输入0.01-999"+numT+"的货物数量！", function(){ $("#cargoNumN2").focus(); });
			return false;
		}
		if(parseFloat(cargoNumN) >= parseFloat(cargoNumN2)){
			selfAlert("请输入大于"+cargoNumN+"的货物数量！", function(){ $("#cargoNumN2").focus(); });
			return false;
		}
		var numT = $("#cargoNumT").val();
		if(cargoNumN == "不详"){
			$("#cargoNum").val(cargoNumN2 + numT);
		}else{
			$("#cargoNum").val(cargoNumN + "~" + cargoNumN2 + numT);
		}
	}else{
		if(cargoNumN != "不详"){
			var numT = $("#cargoNumT").val();
			$("#cargoNum").val(cargoNumN + numT);
		}else{
			$("#cargoNum").val("不详");
		}
	}*/

	var cargoType = $("#cargoType").val();
	var carLengthBegin = $("#carLengthBegin").val();
	var carLengthEnd = $("#carLengthEnd").val();
	if(carLengthBegin!="不限" && carLengthEnd!="不限"){
		carLengthBegin = carLengthBegin.split("米")[0];
		carLengthBegin = parseFloat(carLengthBegin);
		carLengthEnd = carLengthEnd.split("米")[0];
		carLengthEnd = parseFloat(carLengthEnd);
		if(carLengthBegin > carLengthEnd){
			$("#carLengthBegin").val(carLengthEnd+"米");
			$("#carLengthEnd").val(carLengthBegin+"米");
		}
	}

	var carType = $("#carType").val();

	var freightN = $("#freightN").val();
	if (isNull(freightN)) {
		selfAlert("运费不能为空！", function(){ $("#freightN").focus(); });
		return false;
	}
	if (freightN != "面议") {
		if (!isNumber(freightN)) {
			selfAlert("请输入1-99999元以内的货物运费！", function(){ $("#freightN").focus(); });
			return false;
		}
		if (freightN > 99999||freightN < 1) {
			selfAlert("请输入1~99999元以内的货物运费！", function(){ $("#freightN").focus(); });
			return false;
		}
		var cargoNumT2 = $("#cargoNumT2").val();
		$("#freight").val(freightN + "元/"+ cargoNumT2);
	} else {
		$("#freight").val("面议");
	}

	var loadingTime = $("#loadingTime").val();
	/**
	if (isNull(loadingTime)) {
		selfAlert("装车时间不能为空！", function(){ $("#loadingTime").focus(); });
		return false;
	}
	*/

	var cargoDesc = $("#cargoDesc").val();
	if (!isNull(cargoDesc) && cargoDesc.length > 30) {
		selfAlert("请输入30个字以内的货物描述！", function(){ $("#cargoDesc").focus(); });
		return false;
	}
	/**
	if (!isNull(cargoDesc) &&  !isInvalidWaybillDesc(cargoDesc)) {
		selfAlert("货物描述不能包含特殊字符！", function(){ $("#cargoDesc").focus(); });
		return false;
	}
	*/
	if (isNull(cargoDesc)){
		$("#cargoDesc").val("车到上货");
	}

	return true;
}

function validateEditWaybill() {
	var defaultD = "请选择出发地";
	var defaultA = "请选择目的地";

	var departureCity = $("#departureCity").val();
	if (defaultD == departureCity) {
		selfAlert(defaultD);
		$("#departureCity").focus();
		return false;
	}

	var arrivalCity = $("#arrivalCity").val();
	if (defaultA == arrivalCity) {
		selfAlert(defaultA);
		$("#arrivalCity").focus();
		return false;
	}

	var cargoType = $("#cargoType").val();
	if("" == cargoType){
		$("#cargoType").val("普货");
	}else if("" != cargoType && cargoType.length > 10){
		selfAlert("请输入10个字以内的货物类型！", function(){ $("#cargoType").focus(); });
		return false;
	}

	var numT = $("#cargoNumT").val();
	var cargoNumN = $("#cargoNumN").val();
	if(cargoNumN != "不详"){
		if (isNull(cargoNumN)) {
			selfAlert("货物数量不能为空！", function(){ $("#cargoNumN").focus(); });
			return false;
		}
		if (!isCargoNumber3_2(cargoNumN)) {
			selfAlert("请输入0.01-999"+numT+"的货物数量！", function(){ $("#cargoNumN").focus(); });
			return false;
		}
		if (cargoNumN <= 0 || cargoNumN > 999) {
			selfAlert("请输入0.01-999"+numT+"的货物数量！", function(){ $("#cargoNumN").focus(); });
			return false;
		}
		//var numT = $("#cargoNumT").val();
		//$("#cargoNum").val(cargoNumN + numT);
	}else{
		//$("#cargoNum").val("不详");
	}

	var cargoNumN2 = $("#cargoNumN2").val();
	if(cargoNumN2 != "不详"){
		if (isNull(cargoNumN2)) {
			selfAlert("货物数量不能为空！", function(){ $("#cargoNumN2").focus(); });
			return false;
		}
		if (!isCargoNumber3_2(cargoNumN2)) {
			selfAlert("请输入0.01-999"+numT+"的货物数量！", function(){ $("#cargoNumN2").focus(); });
			return false;
		}
		if (cargoNumN2 <= 0 || cargoNumN2 > 999) {
			selfAlert("请输入0.01-999"+numT+"的货物数量！", function(){ $("#cargoNumN2").focus(); });
			return false;
		}
		if(parseFloat(cargoNumN) >= parseFloat(cargoNumN2)){
			selfAlert("请输入大于"+cargoNumN+"的货物数量！", function(){ $("#cargoNumN2").focus(); });
			return false;
		}
		var numT = $("#cargoNumT").val();
		if(cargoNumN == "不详"){
			$("#cargoNum").val(cargoNumN2 + numT);
		}else{
			$("#cargoNum").val(cargoNumN + "~" + cargoNumN2 + numT);
		}
	}else{
		if(cargoNumN != "不详"){
			var numT = $("#cargoNumT").val();
			$("#cargoNum").val(cargoNumN + numT);
		}else{
			$("#cargoNum").val("不详");
		}
	}


	var cargoType = $("#cargoType").val();

	var carLengthBegin = $("#carLengthBegin").val();
	var carLengthEnd = $("#carLengthEnd").val();
	if(carLengthBegin!="不限" && carLengthEnd!="不限"){
		carLengthBegin = carLengthBegin.split("米")[0];
		carLengthBegin = parseFloat(carLengthBegin);
		carLengthEnd = carLengthEnd.split("米")[0];
		carLengthEnd = parseFloat(carLengthEnd);
		if(carLengthBegin > carLengthEnd){
			$("#carLengthBegin").val(carLengthEnd+"米");
			$("#carLengthEnd").val(carLengthBegin+"米");
		}
	}

	var carType = $("#carType").val();

	var freightN = $("#freightN").val();
	if (isNull(freightN)) {
		selfAlert("运费不能为空！", function(){ $("#freightN").focus(); });
		return false;
	}
	if (freightN != "面议") {
		if (!isNumber(freightN)) {
			selfAlert("请输入1-99999元以内的货物运费！", function(){ $("#freightN").focus(); });
			return false;
		}
		if (freightN > 99999) {
			selfAlert("请输入1-99999元以内的货物运费！", function(){ $("#freightN").focus(); });
			return false;
		}
		var cargoNumT2 = $("#cargoNumT2").val();
		$("#freight").val(freightN + "元/"+ cargoNumT2);
	} else {
		$("#freight").val("面议");
	}

	var loadingTime = $("#loadingTime").val();
	/**
	if (isNull(loadingTime)) {
		selfAlert("装车时间不能为空！", function(){ $("#loadingTime").focus(); });
		return false;
	}
	*/

	var cargoDesc = $("#cargoDesc").val();
	if (!isNull(cargoDesc) && cargoDesc.length > 30) {
		selfAlert("请输入30个字以内的货物描述！", function(){ $("#cargoDesc").focus(); });
		return false;
	}
	/**
	if (!isNull(cargoDesc) &&  !isInvalidWaybillDesc(cargoDesc)) {
		selfAlert("货物描述不能包含特殊字符！", function(){ $("#cargoDesc").focus(); });
		return false;
	}
	*/
	if (isNull(cargoDesc)){
		$("#cargoDesc").val("车到上货");
	}

	var consigneeName = $("#consigneeName").val();
	/**
	if (isNull(consigneeName)) {
		selfAlert("收货人名字不能为空！", function(){ $("#consigneeName").focus(); });
		return false;
	}
	if (!isChineseAndChar(consigneeName)) {
		selfAlert("请正确输入收货人名字！", function(){ $("#consigneeName").focus(); });
		return false;
	}
	if (2 > consigneeName.length || consigneeName.length > 10) {
		selfAlert("请输入长度为2-10个字的收货人名字！", function(){ $("#consigneeName").focus(); });
		return false;
	}
	*/
	if(!isNull(consigneeName)){
		if (!isChineseAndChar(consigneeName)) {
			selfAlert("收货人名字不能包含特殊字符！", function(){ $("#consigneeName").focus(); });
			return false;
		}
		if (2 > consigneeName.length || consigneeName.length > 10) {
			selfAlert("请输入长度为2-10个字的收货人名字！", function(){ $("#consigneeName").focus(); });
			return false;
		}
	}

	var consigneeMobileNo = $("#consigneeMobileNo").val();
	/**
	if (isNull(consigneeMobileNo)) {
		selfAlert("收货人电话不能为空！", function(){ $("#consigneeMobileNo").focus(); });
		return false;
	}
	if (!isPhoneNumber(consigneeMobileNo)) {
		selfAlert("请输入正确的电话号码<br/>支持11位手机号及7~8位座机号<br/>如需加区号，如:&nbsp;028-85158955", function(){ $("#consigneeMobileNo").focus(); });
		return false;
	}
	*/
	if (!isNull(consigneeMobileNo)) {
		if (!isPhoneNumber(consigneeMobileNo)) {
			selfAlert("请输入正确的电话号码<br/>支持11位手机号及7~8位座机号<br/>如需加区号，如:&nbsp;028-85158955", function(){ $("#consigneeMobileNo").focus(); });
			return false;
		}
	}


	var consigneeAddress = $("#consigneeAddress").val();
	/**
	if (isNull(consigneeAddress)) {
		selfAlert("收货方地址不能为空！", function(){ $("#consigneeAddress").focus(); });
		return false;
	}
	if (!isPassWords(consigneeAddress)) {
		selfAlert("收货方地址不能包含特殊字符！", function(){ $("#consigneeAddress").focus(); });
		return false;
	}
	if (10 > consigneeAddress.length || consigneeAddress.length > 30) {
		selfAlert("请输入长度为10-30个字的收货人地址信息！", function(){ $("#consigneeAddress").focus(); });
		return false;
	}
	*/
	if (!isNull(consigneeAddress)) {
		if (!isPassWords(consigneeAddress)) {
			selfAlert("收货方地址不能包含特殊字符！", function(){ $("#consigneeAddress").focus(); });
			return false;
		}
		if (consigneeAddress.length > 30) {
			selfAlert("请输入长度为30个字以内的收货人地址信息！", function(){ $("#consigneeAddress").focus(); });
			return false;
		}
	}

	return true;
}

function validateDriverInfo(isMore){
	/********************************Driver******************************/
	//driverName
	var driverName = $("#driverName").val();
	if (isNull(driverName)) {
		selfAlert("请输入驾驶员名字！", function(){ $("#driverName").focus(); });
		return false;
	}
	if (!isChinese(driverName)) {
		selfAlert("请输入2~10位中文的驾驶员名字！", function(){ $("#driverName").focus(); });
		return false;
	}
	if (2 > driverName.length || driverName.length > 10) {
		selfAlert("请输入长度为2-10个字的驾驶员名字！", function(){ $("#driverName").focus(); });
		return false;
	}


	//mobileNo
	var mobileNo = $("#mobileNo").val();
	if (isNull(mobileNo)) {
		selfAlert("请输入11位数字的手机号码！", function(){ $("#mobileNo").focus(); });
		return false;
	}
	if (!isMobileNo(mobileNo)) {
		selfAlert("请输入11位数字的有效手机号码！", function(){ $("#mobileNo").focus(); });
		return false;
	}

	//idCardNo
	if(isMore == "1"){
		var idCardNo = $("#idCardNo").val();
		if (isNull(idCardNo)) {
			selfAlert("请输入驾驶员的身份证号码！", function(){ $("#idCardNo").focus(); });
			return false;
		}
		if (!idCodeValid(idCardNo)) {
			selfAlert("请输入正确的的身份证号码！", function(){ $("#idCardNo").focus(); });
			return false;
		}
	}

	//driverCarNo
	/**
	var driverCarNo = $("#driverCarNo").val();
	if (isNull(driverCarNo)) {
		selfAlert("请输入驾驶员的驾驶证号码！", function(){ $("#driverCarNo").focus(); });
		return false;
	}
	if (!idCodeValid(driverCarNo)) {
		selfAlert("请输入正确的的驾驶证号码！", function(){ $("#driverCarNo").focus(); });
		return false;
	}
	if (idCardNo != driverCarNo) {
		selfAlert("驾驶证号码与身份证号码不一致，<br/>请重新输入！", function(){ $("#driverCarNo").focus(); });
		return false;
	}


	//driverCarDate
	var driverCarDate = $("#driverCarDate").val();
	if (isNull(driverCarDate)) {
		selfAlert("请选择驾驶员驾驶证的过期时间！", function(){ $("#driverCarDate").focus(); });
		return false;
	}
	*/



	/********************************CAR******************************/
	//licensePlateNo
	var licensePlateNoN = $("#licensePlateNoN").val();
	if (isNull(licensePlateNoN)) {
		selfAlert("请输入车辆的车牌号！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}
	if (!isCharOrNumber(licensePlateNoN)) {
		selfAlert("车牌号为字母A-Z和数字0-9的组合！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}
	if (licensePlateNoN.length != 5){
		selfAlert("车牌号为5位数组或字母的组合！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}
	var licenseP = $("#licenseP").val();
	var licenseS = $("#licenseS").val();
	$("#licensePlateNo").val(licenseP+licenseS+"-"+licensePlateNoN);


	//cargoTonnage
	if(isMore == "1"){
		var cargoTonnageN = $("#cargoTonnageN").val();
		if (isNull(cargoTonnageN)) {
			selfAlert("请输入车辆的核载吨位！", function(){ $("#cargoTonnageN").focus(); });
			return false;
		}
		if (!isNumber2(cargoTonnageN)) {
			selfAlert("核载吨位为1~99之间的任意数字！", function(){ $("#cargoTonnageN").focus(); });
			return false;
		}
		$("#cargoTonnage").val(cargoTonnageN+"吨");
	}else{
		var cargoTonnageN = $("#cargoTonnageN").val();
		if (!isNull(cargoTonnageN)) {
			if (!isNumber2(cargoTonnageN)) {
				selfAlert("核载吨位为1~99之间的任意数字！", function(){ $("#cargoTonnageN").focus(); });
				return false;
			}
			$("#cargoTonnage").val(cargoTonnageN+"吨");
		}
	}

	//vehicleLicenseNo
	/**
	var vehicleLicenseNo = $("#vehicleLicenseNo").val();
	if (isNull(vehicleLicenseNo)) {
		selfAlert("请输入车辆的行驶证号码！", function(){ $("#vehicleLicenseNo").focus(); });
		return false;
	}
	if (vehicleLicenseNo.length > 20){
		selfAlert("请输入20位以内的行驶证号码！", function(){ $("#vehicleLicenseNo").focus(); });
		return false;
	}
	*/

	//vehicleOperatingNo
	/** modify 2015/03/13
	var vehicleOperatingNo = $("#vehicleOperatingNo").val();
	if (isNull(vehicleOperatingNo)) {
		selfAlert("请输入车辆的运输许可证号码！", function(){ $("#vehicleOperatingNo").focus(); });
		return false;
	}
	if (!isNull(vehicleOperatingNo) && vehicleOperatingNo.length > 20){
		selfAlert("请输入20位以内的运输许可证号码！", function(){ $("#vehicleOperatingNo").focus(); });
		return false;
	}*/


	//carBrand
	/**
	var carBrand = $("#carBrand").val();
	if (isNull(carBrand)) {
		selfAlert("请输入2~20位字符的车辆品牌！", function(){ $("#carBrand").focus(); });
		return false;
	}
	if (!isPassWords(carBrand)) {
		selfAlert("车辆品牌不能包含特殊字符，<br/>可输入中文、数字、字符！", function(){ $("#carBrand").focus(); });
		return false;
	}
	if (carBrand.length > 20){
		selfAlert("请输入20位以内的车辆品牌名称！", function(){ $("#carBrand").focus(); });
		return false;
	}
	*/


	//engine
	/**
	var engine = $("#engine").val();
	if (!isNull(engine) && !isPassWords(carBrand)) {
		selfAlert("发动机型号不能包含特殊字符，<br/>可输入中文、数字、字符！", function(){ $("#engine").focus(); });
		return false;
	}
	if (engine.length > 20){
		selfAlert("请输入20位以内的发动机型号！", function(){ $("#engine").focus(); });
		return false;
	}
	*/

	//horsepower
	/**
	var horsepower = $("#horsepower").val();
	if (!isNull(engine) && !isNumber5(horsepower)) {
		selfAlert("车辆马力为1~9999之间的任意数字！", function(){ $("#cargoTonnage").focus(); });
		return false;
	}
	*/


	//buyCarTime
	var buyCarTime = $("#buyCarTime").val();

	//carRegTime
	var carRegTime = $("#carRegTime").val();

	//mileage
	var mileage = $("#mileage").val();


	/********************************PIC******************************/
	//driverPhoto
	/**
	var driverPhoto = $("#driverPhoto").val();
	if (isNull(driverPhoto)) {
		selfAlert("请点击上传驾驶员照片！", function(){ $("#driverPhoto").focus(); });
		return false;
	}
	*/

	//vehicleOperatingPicture(option)
	/**
	var vehicleOperatingPicture = $("#vehicleOperatingPicture").val();
	*/

	//idCardPicture
	var idCardPicture = $("#idCardPicture").val();
	if (isNull(idCardPicture)) {
		selfAlert("请点击上传驾驶员证件照片！", function(){ $("#idCardPicture").focus(); });
		return false;
	}


	//idCardPictureBack
	/**
	var idCardPictureBack = $("#idCardPictureBack").val();
	if (isNull(idCardPictureBack)) {
		selfAlert("请点击上传驾驶员身份证照片背面！！", function(){ $("#idCardPictureBack").focus(); });
		return false;
	}
	*/


	//driverCarPicture
	/**
	var driverCarPicture = $("#driverCarPicture").val();
	if (isNull(driverCarPicture)) {
		selfAlert("请点击上传驾驶证照片！", function(){ $("#driverCarPicture").focus(); });
		return false;
	}
	*/


	//driverCarPictureBack
	/**
	var driverCarPictureBack = $("#driverCarPictureBack").val();
	if (isNull(driverCarPictureBack)) {
		selfAlert("请点击上传驾驶证照片背面！", function(){ $("#driverCarPictureBack").focus(); });
		return false;
	}
	*/


	//vehicleLicensePicture
	/**
	var vehicleLicensePicture = $("#vehicleLicensePicture").val();
	if (isNull(vehicleLicensePicture)) {
		selfAlert("请点击上传行驶证照片！", function(){ $("#vehicleLicensePicture").focus(); });
		return false;
	}
	*/


	//vehicleLicensePictureBack
	/**
	var vehicleOperatingPicture = $("#vehicleOperatingPicture").val();
	if (isNull(vehicleOperatingPicture)) {
		selfAlert("请点击上传车头照片！", function(){ $("#vehicleOperatingPicture").focus(); });
		return false;
	}
	*/

	return true;
}


function validateAddDriverInfo(){
	/********************************Driver******************************/
	//driverName
	var driverName = $("#driverName").val();
	if (isNull(driverName)) {
		window.parent.frames.selfAlert("请输入驾驶员名字！", function(){ $("#driverName").focus(); });
		return false;
	}
	if (!isChinese(driverName)) {
		window.parent.frames.selfAlert("请输入2~20位中文的驾驶员名字！", function(){ $("#driverName").focus(); });
		return false;
	}
	if (2 > driverName.length || driverName.length > 20) {
		window.parent.frames.selfAlert("请输入长度为2-20个字的驾驶员名字！", function(){ $("#driverName").focus(); });
		return false;
	}

	//mobileNo
	var mobileNo = $("#mobileNo").val();
	if (isNull(mobileNo)) {
		window.parent.frames.selfAlert("请输入11位数字的手机号码！", function(){ $("#mobileNo").focus(); });
		return false;
	}
	if (!isMobileNo(mobileNo)) {
		window.parent.frames.selfAlert("请输入11位数字的有效手机号码！", function(){ $("#mobileNo").focus(); });
		return false;
	}

	/********************************CAR******************************/
	//licensePlateNo
	var licensePlateNoN = $("#licensePlateNoN").val();
	if (isNull(licensePlateNoN)) {
		window.parent.frames.selfAlert("请输入车辆的车牌号！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}
	if (!isCharOrNumber(licensePlateNoN)) {
		window.parent.frames.selfAlert("车牌号为字母A-Z和数字0-9的组合！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}
	if (licensePlateNoN.length != 5){
		window.parent.frames.selfAlert("车牌号为5位数组或字母的组合！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}
	var licenseP = $("#licenseP").val();
	var licenseS = $("#licenseS").val();
	$("#licensePlateNo").val(licenseP+licenseS+"-"+licensePlateNoN);

	return true;
}

function validateInsurance(){
	var kindItems = $("#kindItems").val();
	if(kindItems == "1"){
		selfAlert("请选择主险内容!");
		return false;
	}

	//运单号
	var ladingNo = $("#ladingNo").val();
	if(isNull(ladingNo)){
		selfAlert("请选择或输入运单号码!", function(){ $("#ladingNo").focus(); });
		return false;
	}
	if(!isNumber(ladingNo)){
		selfAlert("请输入纯数字的运单号码!", function(){ $("#ladingNo").focus(); });
		return false;
	}
	if(ladingNo.length > 20){
		selfAlert("请输入20位以内纯数字的运单号码!", function(){ $("#ladingNo").focus(); });
		return false;
	}

	//被保险人
	var insuredName = $("#insuredName").val();
	if(isNull(insuredName)){
		selfAlert("请输入被保险人姓名!", function(){ $("#insuredName").focus(); });
		return false;
	}
	if (!isChinese(insuredName)) {
		selfAlert("请输入2~20位中文的被保险人姓名！", function(){ $("#insuredName").focus(); });
		return false;
	}
	if (2 > insuredName.length || insuredName.length > 20) {
		selfAlert("请输入长度为2-20个字的被保险人姓名！", function(){ $("#insuredName").focus(); });
		return false;
	}

	if(type == "1"){ //阳光
		var identifyNumber = $("#identifyNumber").val();
		if (isNull(identifyNumber)) {
			selfAlert("请输入的证件号码！", function(){ $("#identifyNumber").focus(); });
			return false;
		}
		if (!idCodeValid(identifyNumber)) {
			selfAlert("请输入正确的的身份证号码！", function(){ $("#identifyNumber").focus(); });
			return false;
		}

		var insuredAddress = $("#insuredAddress").val();
		if(isNull(insuredAddress)){
			selfAlert("请输入联系地址！", function(){ $("#insuredAddress").focus(); });
			return false;
		}
		if(5 > insuredAddress.length || insuredAddress.length > 80){
			selfAlert("请输入5~80字以内的联系地址！", function(){ $("#insuredAddress").focus(); });
			return false;
		}

		var phoneNumber = $("#phoneNumber").val();
		if(isNull(phoneNumber)){
			selfAlert("请输入联系电话！", function(){ $("#phoneNumber").focus(); });
			return false;
		}

		if(!isPhoneNumber(phoneNumber)){
			selfAlert("请输入正确的联系电话！", function(){ $("#phoneNumber").focus(); });
			return false;
		}
	}

	//太平洋-大类
	var bigClass = $("#bigClass").val();
	if(type == "2" && isNull(bigClass)){
		selfAlert("请选择货物大类！");
		return false;
	}

	//太平洋-小类
	var smallClass = $("#smallClass").val();
	if(type == "2" && isNull(smallClass)){
		selfAlert("请选择货物小类！");
		return false;
	}

	//车牌号
	var licensePlateNoN = $("#licensePlateNoN").val();
	if(isNull(licensePlateNoN)){
		selfAlert("请输入车牌号！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}
	if (!isCharOrNumber(licensePlateNoN)) {
		selfAlert("车牌号为字母A-Z和数字0-9的组合！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}
	if (licensePlateNoN.length != 5){
		selfAlert("车牌号为5位数组或字母的组合！", function(){ $("#licensePlateNoN").focus(); });
		return false;
	}

	//太平洋-起运地
	var startSiteDetail = $("#startSiteDetail").val();
	if(type == "2" && isNull(startSiteDetail)){
		selfAlert("请选择货物的起运地!", function(){ $("#startSiteDetail").focus(); });
		return false;
	}

	//太平洋-目的地
	var endSiteDetail = $("#endSiteDetail").val();
	if(type == "2" && isNull(endSiteDetail)){
		selfAlert("请选择货物的目的地!", function(){ $("#endSiteDetail").focus(); });
		return false;
	}

	//起运日期
	var startTransportDate = $("#startTransportDate").val();
	if(isNull(startTransportDate)){
		selfAlert("请选择起运日期！", function(){ $("#startTransportDate").focus(); });
		return false;
	}

	//太平洋-包装数量
	var packerNum = $("#packerNum").val();
	if(type == "2" && isNull(packerNum)){
		selfAlert("请输入包装数量，如10吨!", function(){ $("#packerNum").focus(); });
		return false;
	}

	//货物类别
	var detailName = $("#detailName").val();
	if(type == "1" && isNull(detailName)){ //阳光
		selfAlert("请选择货物类别！", function(){ $("#detailName").focus(); });
		return false;
	}else if(type == "2" && isNull(detailName)){ //太平洋
		selfAlert("请输入货物名称及类型！", function(){ $("#detailName").focus(); });
		return false;
	}
	if(type == "2" && detailName.length > 10){ //太平洋
		selfAlert("货物名称及类型不能超过10个中文字符！", function(){ $("#detailName").focus(); });
		return false;
	}

	//货物描述
	if(type == "1"){
		var detailDescribe = $("#detailDescribe").val();
		if(isNull(detailDescribe)){
			selfAlert("请输入货物描述！", function(){ $("#detailName").focus(); });
			return false;
		}
		if(5 > detailDescribe.length || detailDescribe.length > 80){
			selfAlert("请输入5~80字以内的货物描述！", function(){ $("#detailName").focus(); });
			return false;
		}
	}

	//保额
	var sumAmount = $("#sumAmount").val();
	if(isNull(sumAmount)){
		selfAlert("请输入保额!", function(){ $("#sumAmount").focus(); });
		return false;
	}
	if(!isAmountNumber3_1(sumAmount)){
		selfAlert("请输入保额在1~999之间的数字，可输入小数!", function(){ $("#sumAmount").focus(); });
		return false;
	}
	if(sumAmount > 999 || sumAmount < 1){
		selfAlert("请输入保额在1~999之间的数字，可输入小数!", function(){ $("#sumAmount").focus(); });
		return false;
	}

	var agreePay = $("#agreePay").is(':checked');
	if(!agreePay){
		selfAlert("请先点击同意投保协议!");
		return false;
	}

	return true;
}

/**
 * 验证普通用户登录
 * @returns
 */
function validateUserLogin() {
	var loginAccount = $("#loginAccount").val();
	if ("" == loginAccount) {
		selfAlert("请输入手机号", function() {
			$("#loginAccount").focus();
		});
		return false;
	}
	if(loginAccount.length<1 || loginAccount.length>11){
		selfAlert("请输入账号或手机号！", function() {
			$("#userName").focus();
		});
		return false;
	}
	// if (!isMobileNoLogin(loginAccount)) {
	// 	selfAlert("请输入正确的11位手机号码! ", function() {
	// 		$("#userName").focus();
	// 	});
	// 	return false;
	// }
	if(loginAccount.length == 11){
		/*if (!isMobileNoLogin(loginAccount)) {
			selfAlert("请输入正确的11位手机号码! ", function() {
				$("#userName").focus();
			});
			return false;
		}*/
	}
	var md5Passwd = $("#md5Passwd").val();
	if ("" == md5Passwd) {
		selfAlert("输入登陆密码", function() {
			$("#pwd").focus();
		});
		return false;
	}

	var rd = $("#randomCode").val();
	if (rd.length <= 0) {
		selfAlert("请输入验证码", function() {
			$("#randomCode").focus();
		});
		return false;
	}

	$("#md5PasswdVal").val(MD5(md5Passwd));
	localStorage.setItem("username",loginAccount);
	localStorage.setItem("pwdname",md5Passwd);

	return true;
}

/**
 * 验证普通用户忘记登录密码
 * @returns
 */
function validateUserForgot(){
	var phoneCode = $("#phoneCode").val();
	if (isNull(phoneCode)) {
		selfAlert("请输入手机验证码", function() {
			$("#phoneCode").focus();
		});
		return false;
	}
	if (!isNumber(phoneCode)) {
		selfAlert("请输入纯数字验证码", function() {
			$("#phoneCode").focus();
		});
		return false;
	}

	var newPassword = $("#newPassword").val();
	if (isNull(newPassword)) {
		selfAlert("请输入新密码", function() {
			$("#newPassword").focus();
		});
		return false;
	}
	if (!isCharOrNumber(newPassword)) {
		selfAlert("请输入不要输入中文", function() {
			$("#newPassword").focus();
		});
		return false;
	}
	if (newPassword.length < 6) {
		selfAlert("新密码长度小于6位字符,请输入6~20位的字符!", function() {
			$("#newPassword").focus();
		});
		return false;
	}
	if (newPassword.length > 20) {
		selfAlert("新密码长度大于20位字符,请输入6~20位的字符!", function() {
			$("#newPassword").focus();
		});
		return false;
	}
	var confirmPassword = $("#confirmPassword").val();
	if (isNull(confirmPassword)) {
		selfAlert("请输入新密码", function() {
			$("#confirmPassword").focus();
		});
		return false;
	}
	if (!isCharOrNumber(confirmPassword)) {
		selfAlert("请输入不要输入中文", function() {
			$("#confirmPassword").focus();
		});
		return false;
	}
	if (confirmPassword.length < 6) {
		selfAlert("新密码长度小于6位字符,请输入6~20位的字符!", function() {
			$("#confirmPassword").focus();
		});
		return false;
	}
	if (confirmPassword.length > 20) {
		selfAlert("新密码长度大于20位字符,请输入6~20位的字符!", function() {
			$("#confirmPassword").focus();
		});
		return false;
	}
	if (newPassword != confirmPassword) {
		selfAlert("两次密码输入不一致!", function() {
			$("#confirmPwd").focus()
		});
		return false;
	}
	return true;
}

/**
 * 验证管理员登录
 * @returns
 */
function validateMgmtLogin() {
	var loginAccount = $("#loginAccount").val();
	var md5Passwd = $("#md5Passwd").val();
	var rd = $("#randomCode").val();

	if (!loginAccount.match(/^\S{2,20}$/)) {
		selfAlert("请输入正确的用户名!", function() {
			$("#loginAccount").focus();
		});
		return false;
	}

	if (md5Passwd.length < 5) {
		selfAlert("请输入5位以上的登录密码!", function() {
			$("#md5Passwd").focus();
		});
		return false;
	}

	if (rd.length <= 0) {
		selfAlert("请输入验证码!", function() {
			$("#randomCode").focus();
		});
		return false;
	}

	$("#md5PasswdVal").val(MD5(md5Passwd));
	//设置管理员用户登陆账户与密码
	localStorage.setItem("adminName", loginAccount);
	localStorage.setItem("adminPasswd", md5Passwd);
	return true;
}

/********表格隔行换色***********/
function Interlaced() {
	$(".publicTable tr:even").addClass("even");
}

/**
 * 验证修改登录密码
 */
function validateChangeLoginPasswd() {
	/*************旧密码验证******************/
	var usedPwd = $("#usedPwd").val();
	if (isNull(usedPwd)) {
		selfAlert("请输入旧密码!", function() {
			$("#usedPwd").focus();
		});
		return false;
	}
	// else if (usedPwd.length < 6) {
	// 	selfAlert("旧密码长度小于6位字符,请输入6~20位的字符!", function() {
	// 		$("#usedPwd").focus();
	// 	});
	// 	return false;
	// } else if (usedPwd.length > 20) {
	// 	selfAlert("旧密码长度大于20位字符,请输入6~20位的字符!", function() {
	// 		$("#usedPwd").focus();
	// 	});
	// 	return false;
	// }
	/*************新密码验证******************/
    var newPwd = $("#newPwd").val();
    if (isNull(newPwd)) {
		selfAlert("请输入新密码!", function() {
			$("#newPwd").focus()
		});
		return false;
    } else if (newPwd.length < 8 || newPwd.length > 20) {
		selfAlert("请输入8~20位的新密码!", function() {
			$("#newPwd").focus();
		});
		return false;
    } else if(P.chkPass(newPwd)  < P.scorePwd){
        selfAlert("请输入至少2种字符的密码！<br/>请不要输入相同字母、相同数字、连续数字!", function() {
            $("#newPwd").focus();
        });
        return false;
    }
	/******************确认密码验证*******************/
	var confirmPwd = $("#confirmPwd").val();
	if (isNull(confirmPwd)) {
		selfAlert("请输入确认密码", function() {
			$("#confirmPwd").focus()
		});
		return false;
	} else if (newPwd != confirmPwd) {
		selfAlert("两次密码输入不一致!", function() {
			$("#confirmPwd").focus()
		});
		return false;
	} else if (usedPwd == confirmPwd) {
		selfAlert("新密码不能与旧密码一致!", function() {
			$("#confirmPwd").focus()
		});
		return false;
	}
	return true;
}



/**
 * 相同字符串 aaa 111 AAA
 * @param str
 * @return
 */
function sameChar(str) {
    if (!P.isNullOrEmptyString(str)) {
        for (var i = 0; i < str.length; i++) {
            var c = str.charAt(i);
            //最后一位
            if (i == str.length - 1) {
                continue;
            }
            //当前和后一位不同 返回
            if(c != str.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
    return false;
}

/**
 * 判断是否为连续字符
 * @param str
 * @param interval:0: 验证相同字符串 111 aaa; -1: 验证123, abc; 1: 验证321, cba
 * @param isNum 是否判断为纯数字
 * @return
 */
function continuousChar(str, interval, isNum) {
    if (!P.isNullOrEmptyString(str) && str.length > 1) {
        //第一位和第二位相减的结果
        var result = str.charCodeAt(0) - str.charCodeAt(1);
        if (isNum && (str.charCodeAt(0) < 48 || str.charCodeAt(0) > 57)) {
            return false;
        }
        //差值是否是间距
        if (result == interval || result == -interval) {
            for (var i = 1; i < str.length; i++) {
                if (isNum && (str.charCodeAt(i) < 48 || str.charCodeAt(i) > 57)) {
                    return false;
                }
                //最后一位
                if (i == str.length - 1) {
                    continue;
                }
                //相减的结果是否和result相同 不同则不连续
                if ((str.charCodeAt(i) - str.charCodeAt(i + 1)) != interval) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    } else {
        return false;
    }
}
/**
 * 验证设置支付密码
 * @returns
 */
function validateSettingPayPasswd() {
	/*************新密码验证******************/
	var NewPaymentPwd = $("#NewPaymentPwd").val();
	if (isNull(NewPaymentPwd)) {
		$("#BoxNameone").html("请输入新的支付密码");
		$("#NewPaymentPwd").focus();
		return false;
	}
	if (!isNumber(NewPaymentPwd) || NewPaymentPwd.length != 6) {
		$("#BoxNameone").html("请输入6位数字的新支付密码");
		$("#NewPaymentPwd").focus();
		return false;
	}
	/******************确认密码验证*******************/
	var confirmPwds = $("#confirmPwds").val();
	if (isNull(confirmPwds)) {
		$("#BoxNameone").html("请输入新的支付密码");
		$("#confirmPwds").focus();
		return false;
	}
	if (!isNumber(confirmPwds) || confirmPwds.length != 6) {
		$("#BoxNameone").html("请输入6位数字的新支付密码");
		$("#confirmPwds").focus();
		return false;
	}
	if (NewPaymentPwd != confirmPwds) {
		$("#BoxNameone").html("两次支付密码输入不一致");
		$("#confirmPwds").focus();
		return false;
	}
	return true;
}

/**
 * 验证修改支付密码
 * @returns
 */
function validateChangePayPasswd() {
	$(".hideTr").show();
	/*************旧密码验证******************/
	var oldPaymentPwd = $("#oldPaymentPwd").val();
	if (isNull(oldPaymentPwd)) {
		$("#BoxNameone").html("请输入旧支付密码");
		$("#oldPaymentPwd").focus();
		return false;
	}
	if (!isNumber(oldPaymentPwd) || oldPaymentPwd.length != 6) {
		$("#BoxNameone").html("请输入6位数字的旧支付密码");
		$("#oldPaymentPwd").focus();
		return false;
	}
	/*************新密码验证******************/
	var NewPaymentPwd = $("#NewPaymentPwd").val();
	if (isNull(NewPaymentPwd)) {
		$("#BoxNameone").html("请输入新的支付密码");
		$("#NewPaymentPwd").focus();
		return false;
	}
	if (!isNumber(NewPaymentPwd) || NewPaymentPwd.length != 6) {
		$("#BoxNameone").html("请输入6位数字的新支付密码!");
		$("#NewPaymentPwd").focus();
		return false;
	}
	/******************确认密码验证*******************/
	var confirmPwds = $("#confirmPwds").val();
	if (isNull(confirmPwds)) {
		$("#BoxNameone").html("请输入确认支付密码!");
		$("#confirmPwds").focus();
		return false;
	}
	if (!isNumber(confirmPwds) || confirmPwds.length != 6) {
		$("#BoxNameone").html("请输入6位数字的确认支付密码!");
		$("#confirmPwds").focus();
		return false;
	}
	if (NewPaymentPwd != confirmPwds) {
		$("#BoxNameone").html("两次支付密码输入不一致");
		$("#confirmPwds").focus();
		return false;
	}
	if (oldPaymentPwd == confirmPwds) {
		$("#BoxNameone").html("旧支付密码不能与新支付密码一致");
		$("#confirmPwds").focus();
		return false;
	}
	return true;
}

/**
 * 验证绑定提现银行卡
 * @returns
 */
function validateAddWithdrawBankCard() {
	$("#errorMsg").html("");
	//企业银行验证开户行
	if($("#temp_back_type").val() == 2){
        var bankAddress = $("#bankAddress").val();
        if (isNull(bankAddress)) {
            $("#BoxName").html("请输入开户行!");
            $("#bankAddress").focus();
            return false;
        }else {
            $("#BoxName").html("");
        }
	}

    /********验证银行卡号***********/
	var backNumber = $("#backNumber").val();
	if (isNull(backNumber)) {
		$("#BoxName").html("请输入银行卡卡号!");
		$("#backNumber").focus();
		return false;
	} else if (!isNumber(backNumber)) {
		$("#BoxName").html("请输入纯数字的银行卡卡号!");
		$("#backNumber").focus();
		return false;
	} else if (5 > backNumber.length || backNumber.length > 20) {
		$("#BoxName").html("请输入5~20位纯数字的银行卡卡号!");
		$("#backNumber").focus();
		return false;
	}else if(!luhmCheck(backNumber)){
        $("#BoxName").html("银行卡号不符合规范!");
        $("#backNumber").focus();
        return false;
	}
	/********确认银行卡号***********/
	var confirmNumber = $("#confirmNumber").val();
	if (isNull(confirmNumber)) {
		$("#BoxName").html("请输入确认银行卡卡号!");
		$("#confirmNumber").focus();
		return false;
	} else if (!isNumber(confirmNumber)) {
		$("#BoxName").html("请输入纯数字的确认银行卡卡号!");
		$("#confirmNumber").focus();
		return false;
	} else if (backNumber != confirmNumber) {
		$("#BoxName").html("银行卡号两次输入不一致，请重新输入!");
		$("#confirmNumber").focus();
		return false;
	}
	/********验证银行户主**********/
	var BackHouseholder = $("#BackHouseholder").val();
	if (isNull(BackHouseholder)) {
		$("#BoxName").html("请输入银行卡的户主姓名!");
		$("#BackHouseholder").focus();
		return false;
	} else if (!isChinese(BackHouseholder)) {
		$("#BoxName").html("请输入2~20位中文的户主姓名！");
		$("#BackHouseholder").focus();
		return false;
	} else if (2 > BackHouseholder.length || BackHouseholder.length > 20) {
		$("#BoxName").html("请输入长度为2-20个字的户主姓名！");
		$("#BackHouseholder").focus();
		return false;
	}else{
		$("#BoxName").html("");
	}

	return true;
}

/**
 * 验证用户新提现
 * @returns
 */

function valiadteNewWaltdraw() {
	var ReflectMoney = $("#ReflectMoney").val();
	if (isNull(ReflectMoney)) {
		$("#BoxNames").html("请输入提现金额!");
		$("#ReflectMoney").focus();
		return false;
	}
	if (!isAmount(ReflectMoney)) {
		$("#BoxNames").html("请输入正确的提现金额数字!");
		$("#ReflectMoney").focus();
		return false;
	}
	if (50 > ReflectMoney ) {
		$("#BoxNames").html("提现金额不得低于50元!");
		$("#ReflectMoney").focus();
		return false;
	}

	var withdrawCard = $("#ReflectSelect").val();
	if ("-1" == withdrawCard) {
		selfAlert("请选择提现银行卡，如果没有请先添加提现银行卡!");
		return false;
	}

	var paymentPwd = $("#paymentPwd").val();
	if (isNull(paymentPwd)) {
		$("#BoxNames").html("请输入提现的支付密码!");
		$("#paymentPwd").focus();
		return false;
	}
	if (!isNumber(paymentPwd)) {
		$("#BoxNames").html("请输入纯数字的支付密码!");
		$("#password").focus();
		return false;
	}
	if (paymentPwd.length != 6) {
		$("#BoxNames").html("请输入6位纯数字的支付密码!");
		$("#paymentPwd").focus();
		return false;
	}

	return true;
}

/**
 * 验证忘记支付密码
 * @returns
 */
function validateForgotPayPasswd() {
	$(".hideTr").show();
	/*******验证码验证********/
	var Verification = $("#Verification").val();
	if (isNull(Verification)) {
		$("#BoxNamesOne").html("请输入验证码!");
		$("#Verification").focus();
		return false;
	}
	if (!isNumber(Verification) || Verification.length != 6) {
		$("#BoxNamesOne").html("请输入6位数字的短信验证码!");
		$("#validateCode").focus();
		return false;
	}
	/*******新密码验证**********/
	var NewPaymentPwd = $("#NewPaymentPwd").val();
	if (isNull(NewPaymentPwd)) {
		$("#BoxNamesOne").html("请输入新密码!");
		$("#NewPaymentPwd").focus();
		return false;
	}
	if (!isNumber(NewPaymentPwd) || NewPaymentPwd.length != 6) {
		$("#BoxNamesOne").html("请输入6位数字的新支付密码!");
		$("#NewPaymentPwd").focus();
		return false;
	}
	/**********确认支付密码验证**************/
	var confirmPwds = $("#confirmPwds").val();
	if (isNull(confirmPwds)) {
		$("#BoxNamesOne").html("请输入确认密码!");
		$("#chkpwd").focus();
		return false;
	}
	if (!isNumber(confirmPwds) || confirmPwds.length != 6) {
		$("#BoxNamesOne").html("请输入6位数字的确认支付密码!");
		$("#confirmPwds").focus();
		return false;
	}
	if (NewPaymentPwd != confirmPwds) {
		$("#BoxNamesOne").html("两次密码输入不一致!");
		$("#confirmPwds").focus();
		return false;
	}
	return true;
}

/**
 * 验证项目支付订单金额
 * @returns
 */
function validateProjectPayAmount(){

    var paymentMoney = $("#paymentMoney").val();
    var deductionPrepayMoney = $("#deductionPrepayMoney").text();
    if(deductionPrepayMoney == "0"){ //不选择抵扣预付
        if(isNull(paymentMoney)){
            selfAlert("请输入你要支付的的金额", function(){
                $("#paymentMoney").focus();
            });
            return false;
        }

        if(!isAmount(paymentMoney)){
            selfAlert("请输入正确的支付金额", function(){
                $("#paymentMoney").focus();
            });
            return false;
        }

        if(paymentMoney < 1 || paymentMoney >= 9999999999.99){
            selfAlert("请输入1~9999999999.99元的支付金额", function(){
                $("#paymentMoney").focus();
            });
            return false;
        }
    }else{ //选择抵扣预付
        paymentMoney = isNull(paymentMoney) ? 0 : paymentMoney;

        if(!isAmount(paymentMoney)){
            selfAlert("请输入正确的支付金额", function(){
                $("#paymentMoney").focus();
            });
            return false;
        }

        if(paymentMoney < 0 || paymentMoney >= 9000000){
            selfAlert("请输入0~9000000元的支付金额", function(){
                $("#paymentMoney").focus();
            });
            return false;
        }
    }

    return true;
}

//验证ajax操作是否成功
function validateAjaxSuccess(resp){
	if(!resp.success){
		selfAlert(resp.message);
		return false;
	}
	return true;
}

//判断支付金额
function isAmount(str){
	var re = /^\d{1,12}\.?\d{0,2}$/;
	return re.test(str);
}

/**
 * 验证项目名称或角色名称
 * @param str
 * @returns
 */
function validateProjectName(str){
	var regx = /^[A-Za-z0-9\u4e00-\u9fa5-—()（）]+$/;
	return regx.test(str);
}

/**
 * 验证部门名称
 * @param str
 * @returns
 */
function validateDeptName(str){
	var regx = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
	return regx.test(str);
}


/**
 * 完善收发货单据的验证
 * @param btnType 1:计算按钮，2：保存按钮  add by sj 2017.11.02
 */
function validateBill(btnType) {
	var sendGrossWeight = $("#sendGrossWeight").val();
	var sendTareWeight = $("#sendTareWeight").val();
	var sendNetWeight = $("#sendNetWeight").val();
	var receiveGrossWeight = $("#receiveGrossWeight").val();
	var receiveTareWeight = $("#receiveTareWeight").val();
	var receiveNetWeight = $("#receiveNetWeight").val();
	var allowDifferenceVal = $("#allowDifferenceVal").val();
	var allowDifferenceAmount = $("#allowDifferenceAmount").val();
	//var valuePrice = $("#valuePrice").val();
	//var unitPrice = $("#unitPrice").val();
	var totalPrice = $("#totalPrice").val();
	var allowDifference = $("#allowDifference").val();
	var invoice = $("#invoice").val();
	var unit = $("#unit").val();//单位 1吨2件
	var receiveType = $("#receiveType").val();//
	var invoiceType = $("#invoiceType").val();//1发货单 2收获单据

	//单位为件,方,台，车时
	var sendNetWeightx = $("#sendNetWeightx").val();
	var receiveNetWeightx = $("#receiveNetWeightx").val();


	var allowDifferenceAmountx=$("#allowDifferenceAmountx").val();
    //allowDifferenceAmountx=""==allowDifferenceAmountx?0:allowDifferenceAmountx;
	if (unit == 1) {
		if (isNull(sendGrossWeight) || isNaN(sendGrossWeight)) {
			selfAlert("请输入正确的发货毛重!", function () {
				$("#sendGrossWeight").focus();
			});
			return false;
		}

		if (!isCargoNumber4_3(sendGrossWeight)) {
			selfAlert("请输入0.001-9999.999的发货毛重！", function(){ $("#sendGrossWeight").focus(); });
			return false;
		}

		sendGrossWeight = parseFloat(sendGrossWeight);
		if (isNull(sendTareWeight) || isNaN(sendTareWeight)) {
			selfAlert("请输入正确的发货皮重!", function () {
				$("#sendTareWeight").focus();
			});
			return false;
		}

		if (sendTareWeight <= 0) {
			selfAlert("请输入大于0的发货皮重!", function () {
				$("#sendTareWeight").focus();
			});
			return false;
		}

		if (!isCargoNumber4_3(sendTareWeight)) {
			selfAlert("请输入0.001-9999.999的发货皮重！", function(){ $("#sendTareWeight").focus(); });
			return false;
		}

		sendTareWeight = parseFloat(sendTareWeight);
		if (sendTareWeight >= sendGrossWeight) {
			selfAlert("发货皮重不能大于或等于发货毛重!", function () {
				$("#sendTareWeight").focus();
			});
			return false;
		}
		//发货净重
		var sendNetWeight = sendGrossWeight - sendTareWeight;
		$("#sendNetWeight").val(parseFloat(sendNetWeight).toFixed(3));

		if (invoiceType == 2) {
			if (isNull(receiveGrossWeight) || isNaN(receiveGrossWeight)) {
				selfAlert("请输入正确的收货毛重!", function () {
					$("#receiveGrossWeight").focus();
				});
				return false;
			}
			if (!isCargoNumber4_3(receiveGrossWeight)) {
				selfAlert("请输入0.001-9999.999的收货毛重！", function(){ $("#receiveGrossWeight").focus(); });
				return false;
			}

			receiveGrossWeight = parseFloat(receiveGrossWeight);
			if (isNull(receiveTareWeight) || isNaN(receiveTareWeight)) {
				selfAlert("请输入正确的收货皮重!", function () {
					$("#receiveTareWeight").focus();
				});
				return false;
			}

			if (receiveTareWeight <= 0) {
				selfAlert("请输入大于0的收货皮重!", function () {
					$("#receiveTareWeight").focus();
				});
				return false;
			}

			if (!isCargoNumber4_3(receiveTareWeight)) {
				selfAlert("请输入0.001-9999.999的收货皮重！", function(){ $("#receiveTareWeight").focus(); });
				return false;
			}

			receiveTareWeight = parseFloat(receiveTareWeight);
			if (receiveTareWeight >= receiveGrossWeight) {
				selfAlert("收货皮重不能大于或等于收货毛重!", function () {
					$("#sendTareWeight").focus();
				});
				return false;
			}
			//收货净重
			var receiveNetWeight = receiveGrossWeight - receiveTareWeight;
			$("#receiveNetWeight").val(parseFloat(receiveNetWeight).toFixed(3));

		}
        if(!validatePrice("valuePrice","unitPrice")){
			return false;
		}

		//扣磅差
		if(allowDifference==3){
			if (isNull(allowDifferenceAmount) || isNaN(allowDifferenceAmount)) {
				selfAlert("请输入正确的磅差", function () {
					$("#allowDifferenceAmount").focus();
				});
				return false;
			}

			if(!isNumber5(allowDifferenceAmount) && allowDifferenceAmount != 0){
                selfAlert("请输入0-99999的磅差", function () {
                    $("#allowDifferenceAmount").focus();
                });
                return false;
			}

		}else if(allowDifference==1){
			if (isNull(allowDifferenceVal)  || isNaN(allowDifferenceVal) || !isNumber(allowDifferenceVal)) {
				selfAlert("请输入正确的允许磅差千分比", function () {
					$("#allowDifferenceVal").focus();
				});
				return false;
			}
			if (0>allowDifferenceVal||allowDifferenceVal>5) {
				selfAlert("请输入0-5的允许磅差比", function () {
					$("#allowDifferenceVal").focus();
				});
				return false;
			}
		}
	} else{//件或方,车，台

        totalPrice = $("#totalPricex").val();

		if (isNull(sendNetWeightx) ) {
			selfAlert("请输入发货数量", function () {
				$("#sendNetWeightx").focus();
			});
			return false;
		}
		if(isNaN(sendNetWeightx)){
			selfAlert("请输入正确的发货数量", function () {
				$("#sendNetWeightx").focus();
			});
			return false;
		}


		if (invoiceType == 2) {
			if (isNull(receiveNetWeightx)) {
				selfAlert("请输入收货数量", function () {
					$("#receiveNetWeightx").focus();
				});
				return false;
			}
			if(isNaN(receiveNetWeightx)){
				selfAlert("请输入正确的收货数量", function () {
					$("#receiveNetWeightx").focus();
				});
				return false;
			}


            if(unit==2||unit==7){//单位为件，台时
				if(!isNumber5(receiveNetWeightx)){
                    selfAlert("请输入1-99999的收货数量", function () {
                        $("#receiveNetWeightx").focus();
                    });
                    return false;
				}
            }else if(unit==3){
                if(!isCargoNumber3_3(receiveNetWeightx)){
                    selfAlert("请输入0.001-999.999的收货数量", function () {
                        $("#receiveNetWeightx").focus();
                    });
                    return false;
                }
			}

		}
        if(!validatePrice("valuePricex","unitPricex")){
			return false;
		}

        if(unit!=5){//单位为车是没有误差值
			if(isNull(allowDifferenceAmountx)){
				selfAlert("请输入允许误差值", function () {
					$("#allowDifferenceAmountx").focus();
				});
				return false;
			}
			if(isNaN(allowDifferenceAmountx)||allowDifferenceAmountx<0){
				selfAlert("请输入正确的允许误差值", function () {
					$("#allowDifferenceAmountx").focus();
				});
				return false;
			}

			if(unit==2||unit==7){//单位为件，台时

				if(!isNumber5(sendNetWeightx)){
                    selfAlert("请输入1-99999的发货数量", function () {
                        $("#sendNetWeightx").focus();
                    });
                    return false;
				}

				if(allowDifferenceAmountx>0&&!isNumber5(allowDifferenceAmountx)){
                    selfAlert("请输入0-99999的误差值", function () {
                        $("#allowDifferenceAmountx").focus();
                    });
                    return false;
				}

			}else if(unit==3){//单位为方时
                if(!isCargoNumber3_3(sendNetWeightx)){
                    selfAlert("请输入0.001-999.999的发货数量", function () {
                        $("#sendNetWeightx").focus();
                    });
                    return false;
                }

                if(allowDifferenceAmountx>0&&!isCargoNumber2_3(allowDifferenceAmountx)){
                    selfAlert("请输入0-99.999的误差值", function () {
                        $("#allowDifferenceAmountx").focus();
                    });
                    return false;
                }
			}

			allowDifferenceAmountx = parseFloat(allowDifferenceAmountx);
			if (allowDifferenceAmountx >= sendNetWeightx) {
				selfAlert("允许误差不能大于或等于发货数量!", function () {
					$("#sendTareWeight").focus();
				});
				return false;
			}
        }
	}

	//保存是验证运费
    if(btnType==2&&invoiceType == 2){//保存按钮时才验证
        //运费总价
       // var totalPrice = $("#totalPrice").val();
        if(isNull(totalPrice)){
            selfAlert("请先点击计算按钮进行运费计算!");
            return false;
        }
        // if(totalPrice <= 0){
        //     selfAlert("运费总价不能小于0!");
        //     return false;
        // }
        if(!isCargoNumber10_2(Math.abs(totalPrice))){
            selfAlert("请输入-9999999999.99~9999999999.99的运费总价");
            return false;

        }
    }
	return true;
}

//验证货值单价和运费单价
function validatePrice(valuePriceid,unitPriceid){
    var valuePrice = $("#"+valuePriceid).val();
    var unitPrice = $("#"+unitPriceid).val();
    var materialType= $("#materialType").val();
    //汽车项目取消了货值单价，因此不验证
	if(materialType!=null && materialType!=1){
        if (isNull(valuePrice) || isNaN(valuePrice)) {
            selfAlert("请输入正确的货值单价!", function () {
                $("#valuePrice").focus();
            });
            return false;
        }
        if (valuePrice <= 0) {
            selfAlert("请输入大于1的货值单价!", function () {
                $("#valuePrice").focus();
            });
            return false;
        }
        if(!isSmall7Number2(valuePrice)){
            selfAlert("请输入0.01-9999999.99的货值单价", function(){
                $("#valuePrice").focus();
            });
            return false;
        }
	}
    if (isNull(unitPrice) || isNaN(unitPrice)) {
        selfAlert("请输入正确的运费单价!", function () {
            $("#unitPrice").focus();
        });
        return false;
    }
    if (unitPrice <= 0) {
        selfAlert("请输入大于1的运费单价!", function () {
            $("#unitPrice").focus();
        });
        return false;
    }
    if(!isSmall7Number2(unitPrice)){
        selfAlert("请输入0.01-9999999.99的运费单价", function(){
            $("#unitPrice").focus();
        });
        return false;
    }
    return true;
}

/**
 * 复核时收发货单据的验证
 */
function validateBillAgree(){
	var etcCardAmount = $("#etcCardAmount").val()!=''?$("#etcCardAmount").val():0;
	var depositAmount = $("#depositAmount").val()!=''?$("#depositAmount").val():0;
	var oilCardAmount = $("#oilCardAmount").val()!=''?$("#oilCardAmount").val():0;
	var cashPayAmount = $("#cashPayAmount").val();

	//运费总价
	var totalPrice = $("#totalPrice").val();
	if(isNull(totalPrice)){
		selfAlert("请先点击计算按钮进行运费计算!");
		return false;
	}
	// if(totalPrice <= 0){
	// 	selfAlert("运费总价不能小于0!");
	// 	return false;
	// }
	if(!isDouble(oilCardAmount)){
		selfAlert("请输入正确的油卡金额!");
		return false;
	}
    if(!isSmall7Number2(oilCardAmount)){
        selfAlert("请输入0.01-9999999.99的油卡金额");
        return false;
    }
	oilCardAmount = parseFloat(oilCardAmount);
	if(oilCardAmount < 0){
		selfAlert("油卡金额必须大于等于0");
		return false;
	}
	if(!isDouble(etcCardAmount)){
		selfAlert("请输入正确的充值ETC金额!");
		return false;
	}
    if(!isSmall7Number2(etcCardAmount)){
        selfAlert("请输入0.01-9999999.99的ETC金额");
        return false;
    }
	etcCardAmount = parseFloat(etcCardAmount);
	if(etcCardAmount < 0){
		selfAlert("充值ETC金额必须大于等于0!");
		return false;
	}
	if(!isDouble(depositAmount)){
		selfAlert("请输入正确的押金金额!");
		return false;
	}
    if(!isSmall7Number2(etcCardAmount)){
        selfAlert("请输入0.01-9999999.99的押金金额");
        return false;
    }
	depositAmount = parseFloat(depositAmount);
	if(depositAmount < 0){
		selfAlert("押金金额必须大于等于0!");
		return false;
	}

	// 押金为驾驶员支付给承运方的押金, 因此计算现金支付时，要退还驾驶员押金
	var cashPayAmount = totalPrice - oilCardAmount - etcCardAmount + depositAmount;
    cashPayAmount = parseFloat(cashPayAmount).toFixed(2);
	// if(cashPayAmount <= 0){
	// 	selfAlert("现金支付金额："+parseFloat(cashPayAmount).toFixed(2)+"元；<br/>数据填写错误，请重新填写");
	// 	return false;
	// }
	//现金支付
	$("#cashPayAmount").val(cashPayAmount);
	return true;
}
// ADD-SJ: 2017.09.27 验证装货地和卸货地的Gps中心点
function validateArrivalLoadGps(arrivalAddress,loadAddress,arrivalAddressId,loadAddressId){
	//1、验证装卸货地，不能相同
	if (arrivalAddress && loadAddress && arrivalAddress==loadAddress) {
		selfAlert("装货地和卸货地不能相同!", function () {
		});
		return false;
	}
	var arrival=UDICT.factoryNameGpsMap[arrivalAddressId];
	var load=UDICT.factoryNameGpsMap[loadAddressId];

	//3、添加或修改时，判断装卸货的中心点是否存在，若不存在，则提示“去参数配置中添加”。
	if(!load){
		selfAlert("装货地未配置中心点，请前往参数配置中添加!", function () {
		});
		return false;
	}
	if(!arrival){
		selfAlert("卸货地未配置中心点，请前往参数配置中添加", function () {
		});
		return false;
	}
}
// ADD-SJ: 2018.04.04 添加编辑客户时的验证
function validateShipperInfo() {
	var shipperName = $("#shipperName").val();
	if (isNull(shipperName)) {
		selfAlert("请输入负责人姓名", function () { $("#shipperName").focus(); });
		return false;
	}
	if (!isChinese(shipperName)) {
		selfAlert("请输入2~20位中文的负责人姓名！", function(){ $("#shipperName").focus(); });
		return false;
	}
	if (2 > shipperName.length || shipperName.length > 20) {
		selfAlert("请输入长度为2-20个字的负责人姓名！", function(){ $("#shipperName").focus(); });
		return false;
	}

	var mobileNo = $("#mobileNo").val();
	if (isNull(mobileNo)) {
		selfAlert("请输入11位数字的手机号码！", function () { $("#mobileNo").focus(); });
		return false;
	}

	if (!isMobileNo(mobileNo)) {
		selfAlert("请输入11位数字的有效手机号码！", function () { $("#mobileNo").focus(); });
		return false;
	}

	var companyName = $("#companyName").val();
	if (isNull(companyName)) {
		selfAlert("请输入公司名称", function () { $("#companyName").focus(); });
		return false;
	}
	if (!isCompanyName(companyName)) {
		selfAlert("请输入正确的5~20位中英文公司名称！", function(){ $("#companyName").focus(); });
		return false;
	}
	if (5 > companyName.length || companyName.length > 20) {
		selfAlert("请输入长度为5~20个字的公司名称！", function(){ $("#companyName").focus(); });
		return false;
	}

	var companyCity = $("#companyCity").val();
	if (isNull(companyCity)) {
		selfAlert("请选择公司所在地", function () {
			$("#companyCity").focus();
		});
		return false;
	}
	var companyAddr = $("#companyAddr").val();
	if (isNull(companyAddr)) {
		selfAlert("请输入公司地址", function () {
			$("#companyAddr").focus();
		});
		return false;
	}
	if (5 > companyAddr.length || companyAddr.length > 30) {
		selfAlert("请输入长度为5-30个字的公司地址！", function(){ $("#companyAddr").focus(); });
		return false;
	}

	var companyTel = $("#companyTel").val();
	// if (isNull(companyTel)) {
	// 	selfAlert("请输入座机号码！", function () { $("#companyTel").focus(); });
	// 	return false;
	// }

	if (!isNull(companyTel)&&!isPhoneNumber(companyTel)) {
		selfAlert("请输入正确的电话号码<br/>支持11位手机号及7~8位座机号<br/>如需加区号，如:&nbsp;028-85158955", function () { $("#companyTel").focus(); });
		return false;
	}

	var businessLicenseNo = $("#businessLicenseNo").val();
	// if (isNull(businessLicenseNo)) {
	// 	selfAlert("请输入营业执照号码！", function () { $("#businessLicenseNo").focus(); });
	// 	return false;
	// }

	if (!isNull(businessLicenseNo)&&!isCharOrNumber(businessLicenseNo)) {
		selfAlert("请输入数字+字母的营业执照号码！", function () { $("#businessLicenseNo").focus(); });
		return false;
	}

	var legalPerson = $("#legalPerson").val();
	// if (isNull(legalPerson)) {
	// 	selfAlert("请输入法人代表姓名", function () { $("#legalPerson").focus(); });
	// 	return false;
	// }
	if (!isNull(legalPerson)&&!isChinese(legalPerson)) {
		selfAlert("请输入2~20位中文的法人代表姓名！", function(){ $("#legalPerson").focus(); });
		return false;
	}
	if (!isNull(legalPerson)&&(2 > legalPerson.length || legalPerson.length > 20)) {
		selfAlert("请输入长度为2-20个字的法人代表姓名！", function(){ $("#legalPerson").focus(); });
		return false;
	}

	var registerNo = $("#registerNo").val();
	// if (isNull(registerNo)) {
	// 	selfAlert("请输入注册登记号！", function () { $("#registerNo").focus(); });
	// 	return false;
	// }

	if (!isNull(registerNo)&&!isNumber(registerNo)) {
		selfAlert("请输入纯数字的注册登记号！", function () { $("#registerNo").focus(); });
		return false;
	}

	var registeredCapital = $("#registeredCapital").val();
	if (!isNull(registeredCapital)&&!isDouble(registeredCapital)) {
		selfAlert("请输入正确的注册资本！", function () { $("#businessLicenseNo").focus(); });
		return false;
	}
	return true;
}

function selectTimeValid(monthCount) {
	monthCount=monthCount?monthCount:12;
	var dateStartTime =$("#dateStartTime").val();
	var dateEndTime =$("#dateEndTime").val();
	if(dateStartTime!=""||dateEndTime!=""){
		if(!dateStartTime||dateStartTime==""){
			selfAlert("开始时间不能为空!");
			return false;
		}
		if(!dateEndTime||dateEndTime==""){
			selfAlert("开始时间不能大于结束时间!");
			return false;
		}
		if(dateStartTime>dateEndTime){
			selfAlert("开始时间不能大于结束时间!");
			return false;
		}
	}
	var startStr=new Date(dateStartTime.replace(/-/g, '/'));
	if(monthCount==1){
		startStr.setDate(startStr.getDate()+31);
	}else{
		startStr.setMonth(startStr.getMonth()+monthCount);
	}
	var endStr=new Date(dateEndTime.replace(/-/g, '/'));
	if(startStr<=endStr){
		if(monthCount==12){
			selfAlert("查询时间不能超过1年!");
		}else if(monthCount==1){
			selfAlert("查询时间不能超过31天!");
		}else{
			selfAlert("查询时间不能超过"+monthCount+"个月!");
		}
		return false;
	}
	return true;
}

/*新增添加发票验证*/
function validateTicket(){
	var isError = false;
	$(".itemRow_ticketNo").each(function (i,item) {
		var itemVal = $(item).val();
		if(isNull(itemVal)){
			selfAlert("请在第"+(i+1)+"列填写发票号码！");
			isError = true;
			return false;
		}
		if(!isNumber(itemVal)){
			selfAlert("请在第"+(i+1)+"列填写数字发票号码！");
			isError = true;
			return false;
		}
	});
	if(isError) return true;

	$(".ticketMoney").each(function (i,item) {
		var itemVal = $(item).val();
		if(isNull(itemVal)){
			selfAlert("请在第"+(i+1)+"列填写发票金额！");
			isError = true;
			return false;
		}
		if(itemVal == 0) {
            selfAlert("第"+(i+1)+"列填写发票金额为0！");
            isError = true;
            return false;
		}
		if (!isAmount(itemVal)) {
			selfAlert("请输入0.01~999999999.99内的发票金额");
			isError = true;
			return false;
		}
		if (!isCargoNumber9_2(itemVal)) {
			selfAlert("请输入0.01~999999999.99内的发票金额");
			isError = true;
			return false;
		}
	});
	if(isError) return true;
   // var itemCarNums = [];
	$(".invoiceTime").each(function (i,item) {
		var itemVal = $(item).val();
		if(isNull(itemVal)){
			selfAlert("请在第"+(i+1)+"列填写发票时间！");
			isError = true;
			return false;
		}
        // var y = $(".invoiceTime")[i];
        // var itemCarNum = $(y).val();
        // for(var j=0;j<itemCarNums.length;j++){
        //     var item = itemCarNums[j];
        //     if(item == itemCarNum){
        //         selfAlert("第"+(i+1)+"列发票时间已经存在!");
        //         isError = true;
        //         return true;
        //     }
        // }
        // itemCarNums.push(itemCarNum);
	});
	if(isError) return true;
	return false;
}

function validatePorjectContract(){
	var consignName = $(".updateProjectSummaryDialog .consignName").val();
	if (isNull(consignName)) {
		selfAlert("请输入甲方名称！", function(){ $(".updateProjectSummaryDialog .consignName").focus(); });
		return false;
	}
	if (!isNull(consignName)&&!isChinese(consignName)) {
		selfAlert("请输入2~20位中文甲方名称！", function(){ $(".updateProjectSummaryDialog .consignName").focus(); });
		return false;
	}
	if (!isNull(consignName)&&(2 > consignName.length || consignName.length > 20)) {
		selfAlert("请输入长度为2-20个字的甲方名称！", function(){ $(".updateProjectSummaryDialog .consignName").focus(); });
		return false;
	}

	var transportName = $(".updateProjectSummaryDialog .transportName").val();
	if (isNull(transportName)) {
		selfAlert("请输入乙方名称！", function(){ $(".updateProjectSummaryDialog .transportName").focus(); });
		return false;
	}
	if (!isNull(transportName)&&!isChinese(transportName)) {
		selfAlert("请输入2~20位中文乙方名称！", function(){ $(".updateProjectSummaryDialog .transportName").focus(); });
		return false;
	}
	if (!isNull(transportName)&&(2 > transportName.length || transportName.length > 20)) {
		selfAlert("请输入长度为2-20个字的乙方名称！", function(){ $(".updateProjectSummaryDialog .transportName").focus(); });
		return false;
	}

	var goodName = $(".updateProjectSummaryDialog .goodName").val();
	if (isNull(goodName)) {
		selfAlert("请输入托运货物名称！", function(){ $(".updateProjectSummaryDialog .goodName").focus(); });
		return false;
	}
	if (!isNull(goodName)&&goodName.length > 30) {
		selfAlert("请输入不超过30字的托运货物名称！", function(){ $(".updateProjectSummaryDialog .goodName").focus(); });
		return false;
	}
	var goodPrice = $(".updateProjectSummaryDialog .goodPrice").val();
	if (!isNull(goodPrice)&&!isSmall7Number2(goodPrice)) {
		selfAlert("请输入正确金额格式的货值单价！", function(){ $(".updateProjectSummaryDialog .goodPrice").focus(); });
		return false;
	}
	if(!isNull(goodPrice)&&(goodPrice < 1 || goodPrice > 10000000)){
		selfAlert("请输入1~9999999货值单价！");
		return false;
	}
	var isok=true;
	$(".updateProjectSummaryDialog .departurePoint").each(function(index,item){
		var departurePoint = item.value;
		if (isNull(departurePoint)) {
			selfAlert("请输入装货点！");
			isok= false;
			return;
		}
		if (!isNull(departurePoint)&&departurePoint.length > 30) {
			selfAlert("请输入不超过30字的装货点！");
			isok= false;
			return;
		}
	})

	if(!isok){
		return false;
	}
	$(".updateProjectSummaryDialog .arrivalPoint").each(function(index,item){
		var arrivalPoint = item.value;
		if (isNull(arrivalPoint)) {
			selfAlert("请输入卸货点！");
			isok= false;
			return ;
		}
		if (!isNull(arrivalPoint)&&arrivalPoint.length > 30) {
			selfAlert("请输入不超过30字的卸货点！");
			isok= false;
			return ;
		}
	})
	if(!isok){
		return false;
	}

	$(".updateProjectSummaryDialog .unitPrice").each(function(index,item){
		var unitPrice = item.value;
		//合同里运费单价改为非必填 by lcy on 2018-11-14 from 开票功能完善 2018.11.13
		// if (isNull(unitPrice)) {
		// 	selfAlert("请输入运费单价！");
		// 	isok= false;
		// 	return ;
		// }
		if (!isNull(unitPrice)&&!isSmall7Number2(unitPrice)) {
			selfAlert("请填写0.01~9999999.99的运费单价！");
			isok= false;
			return ;
		}
	})

	if(isok){
		return true;
	}else{
		return false;
	}
}
//移动创建项目验证代码
// 检查创建项目表单是否合法
function checkNewProjectSubmit(){
	var projectName = $("#newProjectName").val();
	if("" == projectName){
		selfAlert("请输入项目名称",function(){$("#newProjectName").focus()});
		return false;
	}
	if(projectName.length > 20 || projectName.length < 2){
		selfAlert("请输入2-20字的项目名称",function(){$("#newProjectName").focus()});
		return false;
	}
	if(!validateProjectName(projectName)){
		selfAlert("名称不可以为特殊符号",function(){$("#newProjectName").focus()});
		return false;
	}

	var sendCompanyInput = $("#sendCompanyInput").val();
	if("" == sendCompanyInput){
		selfAlert("请选择发货方",function(){$("#sendCompanyInput").focus()});
		return false;
	}
	if(!isCompanyName(sendCompanyInput)){
		selfAlert("请不要输入特殊字符",function(){$("#sendCompanyInput").focus()});
		return false;
	}
	var  transportCompanyInput = $("#transportCompanyInput").val();
	if("" == transportCompanyInput){
		selfAlert("请选择承运方货方",function(){$("#transportCompanyInput").focus()});
		return false;
	}
	if(!isCompanyName(transportCompanyInput)){
		selfAlert("请不要输入特殊字符",function(){$("#transportCompanyInput").focus()});
		return false;
	}
	var consigneeCompanyInput = $("#consigneeCompanyInput").val();
	if("" == consigneeCompanyInput){
		selfAlert("请选择收货方",function(){$("#consigneeCompanyInput").focus()});
		return false;
	}
	if(!isCompanyName(consigneeCompanyInput)){
		selfAlert("请不要输入特殊字符",function(){$("#consigneeCompanyInput").focus()});
		return false;
	}
	var materialType = $("#materialType").val();
	if(materialType<0){
		selfAlert("请选择物资类型",function(){$("#materialType").focus()});
		return false;
	}else if(materialType!=0 && materialType!=1 && materialType!=2){
		selfAlert("请选择正确的物资类型",function(){$("#materialType").focus()});
		return false;
	}
	return true;
}
//创建项目新增公司验证
function addCompanyValidate(){
	var addCompanyName = $("#addCompanyName").val();
	var addShipperName = $("#addShipperName").val();
	var addShipperMobileNo = $("#addShipperMobileNo").val();
	var addShipperAddr = $("#addShipperAddr").val();

	if(isNull(addCompanyName)){
		selfAlert("请填写公司名字", function(){});
		return;
	}
	if(!isCompanyName(addCompanyName)){
		selfAlert("请输入5-20位中英文或数字组成的公司名字", function(){});
		return;
	}
	if(addCompanyName.length < 5 || addCompanyName.length > 20){
		selfAlert("请填写5-20字的公司名字", function(){});
		return;
	}
	if(isNull(addShipperName)){
		selfAlert("请填写负责人姓名", function(){});
		return;
	}
	if(!isInvalidContent1(addShipperName) || !isPassWordsCar(addShipperName)){
		selfAlert("请填写2-20字的中文姓名！", function(){});
		return;
	}
	if(addShipperName.length < 2 || addShipperName.length > 20){
		selfAlert("请填写2-20字的中文姓名！", function(){});
		return;
	}
	if(!isNull(addShipperMobileNo) && !isPhoneNumber(addShipperMobileNo)){
		selfAlert("请填写正确格式的电话号码!", function(){});
		return;
	}
	addShipperMobileNo = addShipperMobileNo ? addShipperMobileNo : "未设置";
	if(isNull(addShipperAddr)){
		selfAlert("请填写联系地址", function(){});
		return;
	}
	if(addShipperAddr.length < 5 || addShipperAddr.length > 30 || !isPassWordsCar(addShipperAddr)){
		selfAlert("请填写5-30字联系地址",function(){});
		return;
	}
	return true;
}
//创建计划货物明细验证
function validateItemCarMsg(){
    var isError = false;
    //判断车架号
    var itemCarNums = [];
    $(".carNum").each(function (i,item) {
        var itemVal = $(item).val();
        if(isNull(itemVal)){
            selfAlert("请在车辆"+(i+1)+"填写车架号！");
            isError = true;
            return false;
        }
        if(!isCharOrNumber(itemVal)){
            selfAlert("车辆"+(i+1)+"请输入17位的车架号！");
            isError = true;
            return false;
        }
        if(itemVal.length!=17){
            selfAlert("车辆"+(i+1)+"请输入17位的车架号！");
            isError = true;
            return false;
        }
        var y = $(".carNum")[i];
        var itemCarNum = $(y).val();
        for(var j=0;j<itemCarNums.length;j++){
            var item = itemCarNums[j];
            if(item == itemCarNum){
                selfAlert("第"+(i+1)+"行的车架号已经存在!");
                isError = true;
                return true;
            }
        }
        itemCarNums.push(itemCarNum);

    });
    if(isError) return true;
    //判断颜色
    $(".carColor").each(function (i,item) {
        var itemVal = $(item).val();
        if(isNull(itemVal)){
            selfAlert("请在车辆"+(i+1)+"填写颜色！");
            isError = true;
            return false;
        }
        if(itemVal.length>5){
            selfAlert("车辆"+(i+1)+"的颜色长度不应超过5！");
            isError = true;
            return false;
        }
        if (!isChineseNo(itemVal)) {
            selfAlert("车辆"+(i+1)+"颜色应填写中文！");
            isError = true;
            return false;
        }
    });
    if(isError) return true;

    //判断品牌
    $(".carBrand").each(function (i,item) {
        var itemVal = $(item).val();
        if(isNull(itemVal)){
            selfAlert("请在车辆"+(i+1)+"填写品牌！");
            isError = true;
            return false;
        }
        if(itemVal.length>20){
            selfAlert("车辆"+(i+1)+"的品牌长度不应超过20！");
            isError = true;
            return false;
        }
        if (!isCharOrNumberOrChinese(itemVal)) {
            selfAlert("请在车辆"+(i+1)+"填写正确品牌！");
            isError = true;
            return false;
        }
    });
    if(isError) return true;
    //判断型号
    $(".cfgCarType").each(function (i,item) {
        var itemVal = $(item).val();
        if(isNull(itemVal)){
            selfAlert("请在车辆"+(i+1)+"填写型号！");
            isError = true;
            return false;
        }
        if(itemVal.length>20){
            selfAlert("车辆"+(i+1)+"的型号长度不应超过20！");
            isError = true;
            return false;
        }
        if (!isCharOrNumberOrChinese(itemVal)) {
            selfAlert("请在车辆"+(i+1)+"填写正确型号！");
            isError = true;
            return false;
        }
    });
    if(isError) return true;
    //判断单价
    $(".carPrice").each(function (i,item) {
        var itemVal = $(item).val();
        if(isNull(itemVal)){
            selfAlert("请在车辆："+(i+1)+"填写单价！");
            isError = true;
            return false;
        }
        if(!isCargoNumber10_2(itemVal)){
            selfAlert("车辆："+(i+1)+"请填写0.01~9999999999.99的单价!", function(){});
            isError = true;
            return false;
        }

    });
    if(isError) return true;

    return false;
}
/*
* 添加专车表单验证
* */
function validateMyVehicle() {
	var p = $("#licensePlateNoArea").val();
	var p2 = $("#licensePlateNoLetter").val();
	var s = $("#carLicensePlateNo").val();
	var plateNo = p + p2 + "-" + s;
	var temp = p + p2 + s;
	if (isNull(s)) {
		selfAlert("请填写车牌号码!", function () {
			$("#carLicensePlateNo").focus();
		});
		return false;
	}
	if (!isCarNumber(temp)) {
		selfAlert("请填写正确的车牌号码!", function () {
			$("#carLicensePlateNo").focus();
		});
		return false;
	}

	var cargoTonnage = $("#cargoTonnage").val();
	if (isNull(cargoTonnage)) {
		selfAlert("请输入核载吨位!", function () {
			$("#cargoTonnage").focus();
		});
		return false;
	}
	// 如果是以吨结尾的，则删除吨字
	if(cargoTonnage.substr(cargoTonnage.length-1) == '吨') {
		cargoTonnage = cargoTonnage.substr(0, cargoTonnage.length-1);
		$("#cargoTonnage").val(cargoTonnage);
	}
	if(cargoTonnage!='不详' && cargoTonnage!='不限'){
		if (isNaN(cargoTonnage)) {
			selfAlert("请输入1~100以内的核载吨位!", function () {
				$("#cargoTonnage").focus();
			});
			return false;
		}
		if (!isCargoTonnage(cargoTonnage)) {
			selfAlert("请输入1~99.9以内的核载吨位!", function () {
				$("#cargoTonnage").focus();
			});
			return false;
		}
		if (cargoTonnage < 1 || cargoTonnage > 99.9) {
			selfAlert("请输入1~99.9以内的核载吨位!", function () {
				$("#cargoTonnage").focus();
			});
			return false;
		}
	}

	var carBrand = $("#carBrand").val();
	if (isNull(carBrand)) {
		selfAlert("请输入2~10位的车辆品牌!", function () {
			$("#carBrand").focus();
		});
		return false;
	}
	if (carBrand.length < 2 || carBrand.length > 10) {
		selfAlert("请输入2~10位的车辆品牌!", function () {
			$("#carBrand").focus();
		});
		return false;
	}
	return true;
}
/*
* 阻止架构编辑人员与新增人员验证
* */
function editPersonValidate(){
	var userName = $("#userName").val();
	if(2>userName.length||userName.length>20 || !isChineseNo(userName)){
		selfAlert("请填写2-20字中文用户姓名!", function(){ $("#userName").focus(); });
		return;
	}

	var loginAccount = $("#loginAccount").val();
	if(isLoginAccountAndLengthOutRange(loginAccount, true, 4, 20)){
		selfAlert("请设置4-20位英文+数字登录账号！", function(){ $("#loginAccount").focus(); });
		return false;
	}

    var roleId = $("#roleId").val();
    if(isNull(roleId)){
        selfAlert("请选择角色!");
        return;
    }

	// var mobileNo = $("#mobileNo").val();
	// if (isNull(mobileNo)) {
	// 	selfAlert("请输入11位数字的手机号码！", function(){ $("#mobileNo").focus(); });
	// 	return false;
	// }
	// if (!isMobileNo(mobileNo)) {
	// 	selfAlert("请输入11位数字的有效手机号码！", function(){ $("#mobileNo").focus(); });
	// 	return false;
	// }
	return true;
}

function configCreditValid(params) {
	// params.projectId = selProjectId;
	// params.companyIds = companyIds.join(",");
	// params.status = creditDesignatedConsumption;
	// params.creditType = creditType;
	// params.creditBalance = creditBalance;
	// params.percentBalance = percentBalance;
	// params.historyBillAmount = historyBillAmount;
	// params.weightOrAmount = weightOrAmount;
	// params.oilCardBalance = oilCardBalance;
	if(params.status==0){
		return true;
	}
	if(isNull(params.status)){
		selfAlert("请选择开启状态!");
		return;
	}
	if(isNull(params.creditType)){
		selfAlert("请选择授信方式!");
		return;
	}
	if(params.creditType==1){
		if(isNull(params.creditBalance)){
			selfAlert("请填写0~9999授信金额!");
			return;
		}
		if(!isNumber4(params.creditBalance)&&params.creditBalance!=0){
			selfAlert("请填写0~9999授信金额!");
			return;
		}
	}else if(params.creditType ==2){

		if(isNull(params.percentBalance)){
			selfAlert("请填写1-100的授信比例值!");
			return;
		}

		if(!isNumber2(params.percentBalance)&&params.percentBalance!=100){
			selfAlert("请填写1-100的授信比例值!");
			return;
		}
		if(isNull(params.oilCardBalance)){
			selfAlert("请填写0~9999扣除油卡金额!");
			return;
		}
		if(!isNumber4(params.oilCardBalance)&&params.oilCardBalance!=0){
			selfAlert("请填写0~9999扣除油卡金额!");
			return;
		}
		if(isNull(params.weightOrAmount)){
			selfAlert("请填写1~99每车重量数量!");
			return;
		}
		if(!isNumber2(params.weightOrAmount)){
			selfAlert("请填写1~99每车重量数量!");
			return;
		}
	}

	if(isNull(params.historyBillAmount)){
		selfAlert("请填写1~99最低运单数量!");
		return;
	}
	if(!isNumber2(params.historyBillAmount)){
		selfAlert("请填写1~99最低运单数量!");
		return;
	}
	if(isNull(params.companyIds)){
		selfAlert("请选择公司!");
		return;
	}
	return true;
}
