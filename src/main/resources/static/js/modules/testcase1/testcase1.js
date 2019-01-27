$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'testcase1/queryTestcase1List',
        datatype: "json",
        colModel: [			
			{ label: 'controller名称', name: 'controllerName', index: 'controller_name', width: 80 }, 			
			{ label: 'testCaseId', name: 'testCaseId', index: 'test_case_id', width: 50, key: true },
			{ label: '创建时间', name: 'createdTime', index: 'created_time', width: 80 }, 			
			{ label: '更新时间', name: 'updatedTime', index: 'updated_time', width: 80 }, 			
			{ label: '上次执行时间', name: 'execLastTime', index: 'exec_last_time', width: 80 }, 			
			{ label: '状态0:成功1:失败2:未执行', name: 'status', index: 'status', width: 80 }, 			
			{ label: 'a', name: 'a', index: 'a', width: 80 }, 			
			{ label: 'b', name: 'b', index: 'b', width: 80 }, 			
			{ label: 'c', name: 'c', index: 'c', width: 80 }, 			
			{ label: 'd', name: 'd', index: 'd', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "resultList",
            page: "page",
            count: "count",
            rows: "rows"
        },
        prmNames : {
            page:"page", 
            rows:"rows", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		testcase1: {
			controllerName:null,
			testCaseId:null,
			createdTime:null,
			updatedTime:null,
			execLastTime:null,
			status:null,
			a:null,
			b:null,
			c:null,
			d:null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.testcase1 = {};
		},
		update: function (event) {
			var testCaseId = getSelectedRow();
			if(testCaseId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(testCaseId)
		},
		saveOrUpdate: function (event) {
			var url = vm.testcase1.testCaseId? "testcase1/editTestcase1" : "testcase1/saveTestcase1";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.testcase1),
			    success: function(r){
			    	if(r.code == 200){
						alert(r.message, function(index){
							vm.reload();
						});
					}else{
						alert(r.message);
					}
				}
			});
		},
		del: function (event) {
			var testCaseIds = getSelectedRows();
			if(testCaseIds == null){
				return ;
			}
			var arr = [];
			for(var i = 0;i<testCaseIds.length;i++){
				arr.push($("#jqGrid").jqGrid('getRowData',testCaseIds[i]))
			}

			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "testcase1/deleteTestcase1List",
                    contentType: "application/json",
				    data: JSON.stringify(arr),
				    success: function(r){
						if(r.code == 200){
							alert(r.message, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.message);
						}
					}
				});
			});
		},
		getInfo: function(testCaseId){
			var rowData = $("#jqGrid").jqGrid('getRowData',testCaseId);
			$.ajax({
				type: "POST",
			    url: baseURL + "testcase1/queryTestcase1List",
                contentType: "application/x-www-form-urlencoded",
			    data: rowData,
			    success: function(r){
					if(r.code == 200){
						if(r.resultList.length==0){
							alter("数据不存在");
							return;
						}
		                vm.testcase1  = r.resultList[0];
					}else{
						alert(r.message);
					}
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});