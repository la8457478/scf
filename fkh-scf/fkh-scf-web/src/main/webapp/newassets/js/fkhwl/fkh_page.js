/**
 * 开源代码, 有兴趣的可以在保留声明的前提下免费使用.
 *********** 声明开始 ***********
 * @author ideawu@163.com
 * @link http://www.ideawu.net
 *********** 声明结束 ***********
 *
 * 分页控件, 使用原生的JavaScript代码编写. 重写onclick方法, 获取翻页事件,
 * 可用来向服务器端发起AJAX请求.
 *
 * 示例:
 * HTML:
 * <div id="pager"></div>
 *
 * JavaScript:
 * var pager = new PagerView('pager');
 * pager.index = 3; // 当前是第3页
 * pager.size = 16; // 每页显示16条记录
 * pager.itemCount = 100; // 一共有100条记录
 *
 * pager.onclick = function(index){
 *	alert('click on page: ' + index);
 *	// display data...
 *	pager.render();
 * };
 *
 * pager.render();
 *
 * @param id: HTML节点的id属性值, 控件将显示在该节点中. 可以延迟到render方法调用之前设置.
 */
function PagerView(id) {
	var self = this;
	this.id = id;

	this.container = null;

	this.index = 1;
	// 当前页码, 从1开始
	this.size = 15;
	// 每页显示记录数
	this.maxButtons = 3;
	// 显示的分页按钮数量
	this.itemCount = 0;
	// 记录总数
	this.pageCount = 0;
	// 总页数

	/**
	 * 控件使用者重写本方法, 获取翻页事件, 可用来向服务器端发起AJAX请求.
	 * @param index: 被点击的页码.
	 */
	this.onclick = function(index) {

	};

	/**
	 * 内部方法.
	 */
	this._onclick = function(index) {
		self.index = index;
		self.onclick(index);
		self.render();
	};

	/**
	 * 在显示之前计算各种页码变量的值.
	 */
	this.calculate = function() {
		self.pageCount = parseInt(Math.ceil(self.itemCount / self.size));
		self.index = parseInt(self.index);
		if (self.index > self.pageCount) {
			self.index = self.pageCount;
		}
	};

	/**
	 * 渲染分页控件.
	 */
	this.render = function() {
		if (self.id != undefined) {
			var div = document.getElementById(self.id);
			div.view = self;
			self.container = div;
		}

		self.calculate();

		var start, end;
		start = Math.max(1, self.index - parseInt(self.maxButtons / 2));
		end = Math.min(self.pageCount, start + self.maxButtons - 1);
		start = Math.max(1, end - self.maxButtons + 1);

		var str = "";
		str += "<div class=\"PagerView\">\n";
		if (self.pageCount > 1) {
			if (self.index != 1) {
				str += '<a href="javascript://1"><span class="first">|&lt;</span></a>';
				str += '<a href="javascript://' + (self.index - 1) + '"><span>&lt;&lt;</span></a>';
			} else {
				str += '<span class="first">|&lt;</span>';
				str += '<span>&lt;&lt;</span>';
			}
		}
		for (var i = start; i <= end; i++) {
			if (i == this.index) {
				str += '<span class="on">' + i + "</span>";
			} else {
				str += '<a href="javascript://' + i + '"><span>' + i + "</span></a>";
			}
		}
		if (self.pageCount > 1) {
			if (self.index != self.pageCount) {
				str += '<a href="javascript://' + (self.index + 1) + '"><span>&gt;&gt;</span></a>';
				str += '<a href="javascript://' + self.pageCount + '"><span>&gt;|</span></a>';
			} else {
				str += '<span>&gt;&gt;</span>';
				str += '<span>&gt;|</span>';
			}
		}
		str += ' 共' + self.pageCount + '页,' + self.itemCount + '条';
		/** 禁止跳转分页
		str += "<span class='mobileHidden' style='border:none;'>到:<input type='text' id='pagePlugNo'/> 页&nbsp;";
		str += "<input type='button' value='确定' onclick='P.turnPageNo();' style='width: 40px'/></span>";
		**/
		str += "</div>";

		self.container.innerHTML = str;

		var a_list = self.container.getElementsByTagName('a');
		for (var i = 0; i < a_list.length; i++) {
			a_list[i].onclick = function() {
				var index = this.getAttribute('href');
				if (index != undefined && index != '') {
					index = parseInt(index.replace('javascript://', ''));
					self._onclick(index)
				}
				return false;
			};
		}
	};
}

function initPageBar(count, pageSize, index, objForm) {
	initPageBarPanel("pageBar", count, pageSize, index, objForm);
}

function initPageBar2(count, pageSize, index, objForm) {
	initPageBarPanel("pageBar", count, pageSize, index, objForm, undefined, "currentPage");
}

function initPageBar(count, pageSize, index, objForm, objPanel, pageNoName) {
	initPageBarPanel("pageBar", count, pageSize, index, objForm, objPanel, pageNoName);
}

function initPageBarPanel(id, count, pageSize, index, objForm, objPanel, pageNoName) {
	if(count == "") return;
	var pager = new PagerView(id);
	pager.itemCount = count;
	pager.size = pageSize;
	pager.index = index;
	var panelId = typeof(objPanel) != "undefined" ? objPanel : "#indexRight";

	pager.onclick = function(index) {
		var formData = P.formData(objForm);
		var requestUrl = P.listPageUrl || objForm.action;
		if (requestUrl.indexOf("?") == -1) {
			requestUrl += "?";
		}
		pageNoName = pageNoName ? pageNoName : "page.currentPage";
		requestUrl += "&"+pageNoName+"=" + index;
		ajaxPost(requestUrl, formData, function(response) {
			$(panelId).html(response);
		});
	};

	pager.render();
}

function initPageBarSubmit(count, pageSize, index, objForm) {
	if(count == "") return;
	var pager = new PagerView('pageBar');
	pager.itemCount = count;
	pager.size = pageSize;
	pager.index = index;

	pager.onclick = function(index) {
		var formData = P.formData(objForm);
		var requestUrl = P.listPageUrl || objForm.action;
		if (requestUrl.indexOf("?") == -1) {
			requestUrl += "?";
		}
		requestUrl += "&page.currentPage=" + index;
		objForm.action = requestUrl;
		objForm.submit();
	};

	pager.render();
}

function initPageBarSubmit2(count, pageSize, index, objForm) {
	if(count == "") return;
	var pager = new PagerView('pageBar');
	pager.itemCount = count;
	pager.size = pageSize;
	pager.index = index;

	pager.onclick = function(index) {
		var formData = P.formData(objForm);
		var requestUrl = P.listPageUrl || objForm.action;
		if (requestUrl.indexOf("?") == -1) {
			requestUrl += "?";
		}
		requestUrl += "&pageNo=" + index;
		objForm.action = requestUrl;
		objForm.submit();
	};

	pager.render();
}


/*
 * 总条数、页条数、当前页、搜索表单、分页按钮点击ajax请求后回调函数
 * objForm.action = ajax的Url
 */
function ajaxPageBar(count, pageSize, index, objForm, callBack) {
	ajaxPageBarId("pageBar", count, pageSize, index, objForm, callBack);
}

function ajaxPageBarId(pageBarId, count, pageSize, index, objForm, callBack) {
	if(count == "") return;
	var pager = new PagerView(pageBarId);
	pager.itemCount = count;
	pager.size = pageSize;
	pager.index = index;
	pager.onclick = function(index) {
		var formData = P.formData(objForm);
		var requestUrl = P.listPageUrl || objForm.action;
		if (requestUrl.indexOf("?") == -1) {
			requestUrl += "?";
		}
		requestUrl += "&pageNo=" + index;

		ajaxPost(requestUrl, formData, function(response) {
    		if(ajaxTimeout(response)) return;
			callBack(response);
			if(P){
				P.hideDialog();
			}
		});
		
	};
	pager.render();

	//解决跳转后，保存搜索条件问题
	if(Page){
        Page.ajaxPageInit(objForm,index);
    }
}
