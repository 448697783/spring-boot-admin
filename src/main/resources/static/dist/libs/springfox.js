window.onload = function() {

	// Build a system
	const ui = SwaggerUIBundle({
		url : "http://127.0.0.1:8081/v2/api-docs",
		dom_id : '#swagger-ui',
		deepLinking : true,
		presets : [ SwaggerUIBundle.presets.apis, SwaggerUIStandalonePreset ],
		plugins : [ SwaggerUIBundle.plugins.DownloadUrl ],
		layout : "StandaloneLayout"
	})

	$(document)
			.on(
					'click','button.btn.try-out__btn',
					function(e) {
						if ($(this).parent().parent().children().length <= 2) {
							$(this)
									.parent()
									.parent()
									.append(
											"&nbsp;&nbsp;<div class='try-out'><button class='btn try-out__btn Add_Test'>Add Test</button></div>");
							$(this)
									.parent()
									.parent()
									.append(
											"&nbsp;&nbsp;<div class='try-out'><button class='btn try-out__btn Random_Template'>Random Template</button></div>");
							$(this)
									.parent()
									.parent()
									.append(
											"&nbsp;&nbsp;<div class='try-out'><button class='btn try-out__btn Test_List'>Test List</button></div>");
						}
					});
	$(document)
	.on(
			'click','button.btn.Add_Test',
			function(e) {
				var parameter = $(this).parent().parent().parent().parent().find("td[class='col parameters-col_name']");
				var parameter_text = $(this).parent().parent().parent().parent().find("td[class='col parameters-col_description']");
				var column = {};
				var columns = [];
				var data = {};
				var row = {};
				var rows = [];


				for (var i = parameter.length-1; i >=0 ; i--) {
					column["field"]=$(parameter[i]).find("div[class='parameter__name']").text();
					column["title"]=$(parameter[i]).find("div[class='parameter__name']").text();
					column["type"] =$(parameter[i]).find("div[class='parameter__type']").text();
					column["parameter__in"] =$(parameter[i]).find("div[class='parameter__in']").text();
//					alert($(parameter[i]).find("div[class='parameter__in']").text());
					if(column["parameter__in"]=="(body)"){
						row[column["field"]]=$(parameter_text[i]).find("textarea[class='body-param__text']").text();
//						alert($(parameter_text[i]).find("textarea[class='body-param__text']").text())
					}else if(column["parameter__in"]=="(query)"){
						row[column["field"]]=$(parameter_text[i]).find("input").val();
//						alert($(parameter_text[i]).find("input").val());
					}else{
						alert("没有法相此类型："+column["parameter__in"]);
					}
					columns.push(column);
					column = {};
				}	
				rows.push(row);
				data["columns"]=columns;
				data["rows"]=rows;
				
				var rowCount = data.rows.length;
				var cellCount = data.columns.length;
				for (var i = 0; i < rowCount; i++) {
					var tr = $("<tr></tr>");
					$(this).parent().parent().parent().find("table[class='gridtable']").append(tr);
					for (var j = 0; j < cellCount; j++) {
						var field = data.columns[j].field;
						var val = "";
						if (data.rows[i][field] != null) {
							val = data.rows[i][field];
						}

						var td = $("<td    >" + val + "</td>");
						td.appendTo(tr);
					}
					var td = $("<td   ><div class='try-out'><button class='btnn Test_excute'>执行</button><button >删除</button></div></td>");
					td.appendTo(tr);
				}
				
			});

	$(document)
	.on(
			'click','button.btn.Random_Template',
			function(e) {
				var a = $(this).parent().parent().parent().parent().find("button[class='btn execute opblock-control__btn']");
					a.trigger("click");
				alert($.RC(true,2,10));
			});
	$(document)
	.on(
			'click','button.btn.Random_Test_excute',
			function(e) {
				alert('test_excute');
			});
	$(document)
	.on(
			'click','button.btnn.Test_excute',
			function(e) {
//				var mode = $(this).parent().parent().parent().parent().find("button[class='btn execute opblock-control__btn']");
//				mode.trigger("click");
				

				var len = $(this).parent().parent().parent().parent().parent().find("thead[class='colThead']").children('th').length;
				var temp = {};
				for (var i = 0; i < len; i++) {
					var ccc =$(this).parent().parent().parent().children('td').eq(i).text();
					ccc = $.replaceParamter(ccc);
					temp[$(this).parent().parent().parent().parent().parent().find("thead[class='colThead']").children('th').eq(i).text()]=ccc;
				}
				var parameter = $(this).parent().parent().parent().parent().parent().parent().parent().parent().find("td[class='col parameters-col_name']");
				var parameter_text = $(this).parent().parent().parent().parent().parent().parent().parent().parent().find("td[class='col parameters-col_description']");

				for (var i = parameter.length-1; i >=0 ; i--) {
					if($(parameter[i]).find("div[class='parameter__in']").text()=="(body)"){
						$(parameter_text[i]).find("textarea[class='body-param__text']").val(temp[$(parameter[i]).find("div[class='parameter__name']").text()]);
//						alert($(parameter_text[i]).find("textarea[class='body-param__text']").text())
					}else if($(parameter[i]).find("div[class='parameter__in']").text()=="(query)"){
						$(parameter_text[i]).find("input").focus();
						
						$(parameter_text[i]).find("input").val('');
						$(parameter_text[i]).find("input").attr('');

						
						$(parameter_text[i]).find("input").val(temp[$(parameter[i]).find("div[class='parameter__name']").text()]).trigger('change');
						$(parameter_text[i]).find("input").attr("value",temp[$(parameter[i]).find("div[class='parameter__name']").text()]).trigger('change');
						$(parameter_text[i]).find("input").change(true);
						
						
						var obj= $(parameter_text[i]).find("input").get(0);
						 fireKeyEvent(obj, "keydown", "1".charCodeAt(0));

						
						
					}
				}	
				
			
			});
	$(document)
			.on(
					'click','button.btn.Test_List',
					function(e) {
						// alert($(this).parent().parent().parent().parent().find("textarea[class='body-param__text']").val());
						var parameter = $(this).parent().parent().parent()
								.parent().find(
										"td[class='col parameters-col_name']");
						var column = {};
						var columns = [];
						var data = {};

						for (var i = parameter.length-1; i >=0 ; i--) {
							column["field"]=$(parameter[i]).find("div[class='parameter__name']").text();
							column["title"]=$(parameter[i]).find("div[class='parameter__name']").text();
							column["type"] =$(parameter[i]).find("div[class='parameter__type']").text();
							columns.push(column);
							column = {};
						}	
						column = {};
						column["field"]="操作";
						column["title"]="操作";
						column["type"] ="操作";
						columns.push(column);
						
						data["columns"]=columns;
						data["rows"]=[];
						var htable = $('<div style="	width:100%; overflow-x:auto;overflow-y:auto; top: expression_r(this.style.pixelHeight + document.body.scrollTop+273);" class="createTable" />');
						var _table = CreateTable(data,htable);
						$(this).parent().parent().parent().append(htable);
						function CreateTable(data,htable) {
//							var str = '{  "columns": [{ "field": "EMPLOYEEID", "title": "员工11编号" }, { "field": "EMPLOYEENAME", "title": "员工姓11名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}, { "field": "EMPLOYEENAME", "title": "员工姓名"}],"rows":[{"EMPLOYEEID":"001","EMPLOYEENAME":"小明","a":"fdsafdsa"},{"EMPLOYEEID":"002","EMPLOYEENAME":"小宏","a":"fdsafdsa"}]}';
//							var data = JSON.parse(str);
							
//							htable.append("<button value='fdsa' />");
							var rowCount = data.rows.length;
							var cellCount = data.columns.length;

							var table = $("<table   class='gridtable' >");
							table.appendTo(htable);
							var trHeader = $("<thead class='colThead'></thead>");
							trHeader.appendTo(table);
							for (var j = 0; j < cellCount; j++) {
								var td = $("<th >" + data.columns[j].title + "</th>");
								td.appendTo(trHeader);
							}

							for (var i = 0; i < rowCount; i++) {
								var tr = $("<tr></tr>");
								tr.appendTo(table);
								for (var j = 0; j < cellCount; j++) {
									var field = data.columns[j].field;
									var val = "";
									if (data.rows[i][field] != null) {
										val = data.rows[i][field];
									}

									var td = $("<td    >" + val + "</td>");
									td.appendTo(tr);
								}
							}
//							$("#createTable").append("</table>");
						}
						
						
						
//						$(this).parent().parent().parent().find("table[class='gridtable']").on("dblclick", "tr", function() {
//							var len = $(this).parent().parent().parent().find("thead[class='colThead']").children('th').length;
//							var temp = {};
//							for (var i = 0; i < len; i++) {
//								temp[$(this).parent().parent().parent().find("thead[class='colThead']").children('th').eq(i).text()]=$(this).children('td').eq(i).text();
//								var ccc =$(this).children('td').eq(i).text().toString();
//								
//								temp[$(this).parent().parent().parent().find("thead[class='colThead']").children('th').eq(i).text()]=ccc;
//							}
//							var parameter = $(this).parent().parent().parent().parent().find("td[class='col parameters-col_name']");
//							var parameter_text = $(this).parent().parent().parent().parent().find("td[class='col parameters-col_description']");
//
//							for (var i = parameter.length-1; i >=0 ; i--) {
//								if($(parameter[i]).find("div[class='parameter__in']").text()=="(body)"){
//									$(parameter_text[i]).find("textarea[class='body-param__text']").val(temp[$(parameter[i]).find("div[class='parameter__name']").text()]);
////									alert($(parameter_text[i]).find("textarea[class='body-param__text']").text())
//								}else if($(parameter[i]).find("div[class='parameter__in']").text()=="(query)"){
//									$(parameter_text[i]).find("input").val(temp[$(parameter[i]).find("div[class='parameter__name']").text()]);
//									var newstr=JSON.stringify(window.ui); //返回一个新字符串
//									console.log(newstr)
//								}
//							}	
//							
//						});
					});
	window.ui = ui;
	
	
	
	
	


	/*
	** randomFlag-是否任意长度 min-任意长度最小位[固定位数] max-任意长度最大位
	** xuanfeng 2014-08-28
	*/
}
$.textTemp={};
$.extend({
   RC:function(name,randomFlag, min, max){
		var str = "",
		range = min ,
		arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
		// 随机产生
		if(randomFlag){
			range = Math.round(Math.random() * (max-min)) + min;
		}else{
			if(!min){
				range = 10;
			}
		}
		var uuid = Number(Date.now()).toString(36);
		if(range>uuid.length){
			str = uuid;
			range=range-uuid.length;
		}
		for(var i=0; i<range; i++){
			pos = Math.round(Math.random() * (arr.length-1));
			str += arr[pos];
		}
		$.textTemp[name] = str;
		return str;
   },
   RU:function(name,range){
	   if(!range){
		   range = name;
	   }
	    var str = "";
	    if(!range){
	    	range = 10;
	    }
		for(var i=0; i<range; i++){
			str+=eval( '"\\u' + (Math.round(Math.random() * 20901) + 19968).toString(16)+'"') ;
		}
		$.textTemp[name] = str;
	   return str;
   },
   RN:function(name,range){
	   if(!range){
		   range = name;
	   }
	   var str = "",
		arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
		if(!range){
			range = 10;
		}
		for(var i=0; i<range; i++){
			pos = Math.round(Math.random() * (arr.length-1));
			str += arr[pos];
		}
		$.textTemp[name] = str;
		return str;
   },
   RE:function(name,range){
	   if(!range){
		   range = name;
	   }
	   var end =  ["@qq.com","@qq2.com","@gmail.com","@126.com","@163.com","@hotmail.com","@yahoo.com","@yahoo.com.cn","@live.com","@sohu.com","@sina.com"] 
	   var str = "";
	   if(!range){
		   range = 10;
	   }
	   str = this.RC('',false,range)+end[Math.round(Math.random() * (end.length-1))];
	   $.textTemp[name] = str;

	   return str;
   },
   RR:function(name,arr){
	   if(!arr){
		   arr = name;
	   }
	   var cc = arr.split(",");
	   var str = "";
	   str = cc[Math.round(Math.random() * (cc.length-1))];
	   $.textTemp[name] = str;
	   return str;
   },
   V:function(name){
	  return $.textTemp[name];
   },
   replaceParamter:function(str){
	   str = this.replaceRC(str);
	   str = this.replaceVar(str);
	   str = this.replaceRU(str);
	   str = this.replaceRE(str);
	   str = this.replaceRN(str);
	   str = this.replaceRR(str);
	   return str;
   },
   replaceRC : function(str){
	   if(str==""){
			return "";
		}
		var matchRCs = str.match(/@RC\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?(true|false)?,?(\d*)?,?(\d*)?\)/g);
		if(!matchRCs){
			return str;
		}
		var mlen = matchRCs.length;
		for (var k = 0; k< mlen; k++) {
//			alert(matchRCs[k]);
			var re = eval(matchRCs[k].replace('@','$.'))
			if((typeof re=='string')&&re.constructor==String&&re.length>0){
				return str.replace(/@RC\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?(true|false)?,?(\d*)?,?(\d*)?\)/g,re)
			}else{
				alert('变量参数不合法：'+matchRCs[k]);
				return str;
			}
	
		}
   },
   replaceVar:function(str){
	   if(str==""){
			return "";
		}
		var matchRCs = str.match(/@V\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')\)/g);
		if(!matchRCs){
			return str;
		}
		var mlen = matchRCs.length;
		for (var k = 0; k< mlen; k++) {
//			alert(matchRCs[k]);
			var re = eval(matchRCs[k].replace('@','$.'))
			if((typeof re=='string')&&re.constructor==String&&re.length>0){
				return str.replace(/@V\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')\)/g,re)
			}else{
				alert('变量参数不合法：'+matchRCs[k]);
				return str;
			}
	
		}
   },
   replaceRU:function(str){
	   if(str==""){
			return "";
		}
		var matchRCs = str.match(/@RU\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?(\d*)?\)/g);
		if(!matchRCs){
			return str;
		}
		var mlen = matchRCs.length;
		for (var k = 0; k< mlen; k++) {
//			alert(matchRCs[k]);
			var re = eval(matchRCs[k].replace('@','$.'))
			if((typeof re=='string')&&re.constructor==String&&re.length>0){
				return str.replace(/@RU\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?(\d*)?\)/g,re)
			}else{
				alert('变量参数不合法：'+matchRCs[k]);
				return str;
			}
	
		}
   },
   replaceRE:function(str){
	   if(str==""){
		   return "";
	   }
	   var matchRCs = str.match(/@RE\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?(\d*)?\)/g);
	   if(!matchRCs){
		   return str;
	   }
	   var mlen = matchRCs.length;
	   for (var k = 0; k< mlen; k++) {
//		   alert(matchRCs[k]);
		   var re = eval(matchRCs[k].replace('@','$.'))
		   if((typeof re=='string')&&re.constructor==String&&re.length>0){
			   return str.replace(/@RE\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?(\d*)?\)/g,re)
		   }else{
			   alert('变量参数不合法：'+matchRCs[k]);
			   return str;
		   }
		   
	   }
   },
   replaceRN:function(str){
	   if(str==""){
		   return "";
	   }
	   var matchRCs = str.match(/@RN\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?(\d*)?\)/g);
	   if(!matchRCs){
		   return str;
	   }
	   var mlen = matchRCs.length;
	   for (var k = 0; k< mlen; k++) {
//		   alert(matchRCs[k]);
		   var re = eval(matchRCs[k].replace('@','$.'))
		   if((typeof re=='string')&&re.constructor==String&&re.length>0){
			   return str.replace(/@RN\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?(\d*)?\)/g,re)
		   }else{
			   alert('变量参数不合法：'+matchRCs[k]);
			   return str;
		   }
	   }
   },
   replaceRR:function(str){
	   if(str==""){
		   return "";
	   }
	   var matchRCs = str.match(/@RR\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?('.+(,.+)*')?\)/g);
	   if(!matchRCs){
		   return str;
	   }
	   var mlen = matchRCs.length;
	   for (var k = 0; k< mlen; k++) {
//		   alert(matchRCs[k]);
		   var re = eval(matchRCs[k].replace('@','$.'))
		   if((typeof re=='string')&&re.constructor==String&&re.length>0){
			   return str.replace(/@RR\(('[A-Za-z0-9_\-\u4e00-\u9fa5]*')?,?('.+(,.+)*)?'\)/g,re)
		   }else{
			   alert('变量参数不合法：'+matchRCs[k]);
			   return str;
		   }
	   }
   },
    fireKeyEvent:function(el, evtType, keyCode) {
       var evtObj;
       if (document.createEvent) {
           if (window.KeyEvent) {//firefox 浏览器下模拟事件
               evtObj = document.createEvent('KeyEvents');
               evtObj.initKeyEvent(evtType, true, true, window, true, false, false, false, keyCode, 0);
           } else {//chrome 浏览器下模拟事件
               evtObj = document.createEvent('UIEvents');
               evtObj.initUIEvent(evtType, true, true, window, 1);

               delete evtObj.keyCode;
               if (typeof evtObj.keyCode === "undefined") {//为了模拟keycode
                   Object.defineProperty(evtObj, "keyCode", { value: keyCode });                       
               } else {
                   evtObj.key = String.fromCharCode(keyCode);
               }

               if (typeof evtObj.ctrlKey === 'undefined') {//为了模拟ctrl键
                   Object.defineProperty(evtObj, "ctrlKey", { value: true });
               } else {
                   evtObj.ctrlKey = true;
               }
           }
           el.dispatchEvent(evtObj);

       } else if (document.createEventObject) {//IE 浏览器下模拟事件
           evtObj = document.createEventObject();
           evtObj.keyCode = keyCode
           el.fireEvent('on' + evtType, evtObj);
       }
   }
   
  

})

//baseURL='http://127.0.0.1:8080';
