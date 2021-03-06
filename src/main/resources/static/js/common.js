//jqGrid的配置信息
if($.jgrid){
	$.jgrid.defaults.width = 1000;
	$.jgrid.defaults.responsive = true;
	$.jgrid.defaults.styleUI = 'Bootstrap';
	$.extend($.jgrid.defaults, {
	    ajaxGridOptions : {
	        headers: {
	            "token": token
	        }
	    }
//	    loadError: function(xhr,status,error){  
//	    	if(xhrstatus){
//	    		
//	    	}
//	        alert(1) 
//	    }
	});
}

//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//请求前缀
//var baseURL = "http://demo.open.renren.io/renren-fastplus/";
//var baseURL = "/renren-fastplus/";
var baseURL = "/super/";

//登录token
var token = localStorage.getItem("token");
if(token == 'null'){
    parent.location.href = baseURL + 'login.html';
}

//jquery全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false,
    headers: {
        "token": token
    },
    // xhrFields: {
    //     withCredentials: true
    // },
    complete: function(xhr) {
        //token过期，则跳转到登录页面
        if(xhr.responseText&&JSON.parse(xhr.responseText).code == 401){
            parent.location.href = baseURL + 'login.html';
        }
    }
});
//jqgrid全局配置


//权限判断
function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}


//重写alert
window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请至少选择一条记录");
    	return null;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    
    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	return null;
    }
    
    return grid.getGridParam("selarrrow");
}

//选择一条记录
function getSelectedOneRow() {	
	var selectedData;
	layui.use('table', function(){
		var table = layui.table;
		var checkStatus = table.checkStatus('tableId'); //获取表格选中行
		var checkNumber = checkStatus.data.length; //获取选中行数量
		var checkData = checkStatus.data; //获取选中行的数据		
		if(checkNumber == 0){
			alert("请至少选择一条记录");
			return;
		}
		if(checkNumber > 1){
			alert("只能选择一条记录");
			return;
		}
		return selectedData = checkData[0]
	})
	return selectedData
}

//选择多条记录
function getSelectedMoreRow() {
	var selectedData;
	layui.use('table', function(){
		var table = layui.table;
		var checkStatus = table.checkStatus('tableId'); //获取表格选中行
		var checkNumber = checkStatus.data.length; //获取选中行数量
		var checkData = checkStatus.data; //获取选中行的数据
		if(checkNumber == 0){
			alert("请至少选择一条记录");
			return;
		}				
		return selectedData = checkData
	})
	return selectedData
}