window.onload = function(){
	changeChart(0);
	/*
    var whichPage = document.getElementById("consumption-page");
	var oDiv1 = document.getElementById("WeekLineGraph");
    var customText = [];
    var data = [];
    if(!!whichPage){
        customText = [{text:"油费表",x:20,y:30,fontSize:16},{text:"日平均值：33.19",x:20,y:55,fontSize:12},{text:"12.57升/公里",x:-35,y:30,fontSize:16,xMax:true},{text:"累计",x:38,y:55,fontSize:12,xMax:true}];
        data = [{x:"6月24日",y:14},{x:"25",y:15},{x:"26",y:150},{x:"6月27日",y:76},{x:"28",y:350},{x:"29",y:30},{x:"今天",y:500}];
    }else{
        customText = [{text:"里程表",x:20,y:30,fontSize:16},{text:"日平均值：33.19",x:20,y:55,fontSize:12},{text:"12.57升/公里",x:-35,y:30,fontSize:16,xMax:true},{text:"累计",x:38,y:55,fontSize:12,xMax:true}];
        data = [{x:"6月24日",y:14},{x:"25",y:15},{x:"26",y:150},{x:"6月27日",y:76},{x:"28",y:350},{x:"29",y:30},{x:"今天",y:500}];
    }
	if(!d1){
		var d1 = new CreatLine();
		d1.init(oDiv1,{
			type:"",
			xTitleShow:true,        //显示X轴标题
			yTextHide:true,         //X轴标题字体大小
			yLineHide:true,         //X轴标题字体大小
			xTextFontSise:12,      //X轴标题字体大小
			textColor:"#fff",      //全局字体颜色
			customText:customText,
			data:data
		});
	}
	*/
}


$(function(){
	//油耗动画one
	function animate(){
		$(".charts").each(function(i,item){
			var a=parseInt($(item).attr("w"));
			$(item).animate({
				width: a+"%"
			},1000);
		});
	}
	animate();
	
	//油耗动画two
	function animateone(){
		$(".Charts").each(function(i,item){
			var a=parseInt($(item).attr("ws"));
			$(item).animate({
				width: a+"%"
			},1000);
		});
	}
	animateone();
});



