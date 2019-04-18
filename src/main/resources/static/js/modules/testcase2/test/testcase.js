$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'testcase2/test/queryTestcaseList',
        datatype: "json",
        colModel: [			
			{ label: 'controller名称', name: 'controllerName', index: 'controller_name', width: 80 }, 			
			{ label: '', name: 'describe', index: 'describe', width: 80 }, 			
			{ label: '', name: 'method', index: 'method', width: 80 }, 			
			{ label: 'testCaseId', name: 'testCaseId', index: 'test_case_id', width: 50, key: true },
			{ label: '', name: 'deptId', index: 'dept_id', width: 80 }, 			
			{ label: '创建时间', name: 'createdTime', index: 'created_time', width: 80 }, 			
			{ label: '更新时间', name: 'updatedTime', index: 'updated_time', width: 80 }, 			
			{ label: '上次执行时间', name: 'execLastTime', index: 'exec_last_time', width: 80 }, 			
			{ label: '状态0:成功1:失败2:未执行', name: 'status', index: 'status', width: 80 }			
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
            total: "totalPage",
            records: "count"
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
		testcase: {
			controllerName:null,
			describe:null,
			method:null,
			testCaseId:null,
			deptId:null,
			createdTime:null,
			updatedTime:null,
			execLastTime:null,
			status:null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.testcase = {};
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
			var url = vm.testcase.testCaseId? "testcase2/test/editTestcase" : "testcase2/test/saveTestcase";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.testcase),
			    success: function(r){
			    	if(r.code == 200){
						alert(r.msg, function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
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
				    url: baseURL + "testcase2/test/deleteTestcaseList",
                    contentType: "application/json",
				    data: JSON.stringify(arr),
				    success: function(r){
						if(r.code == 200){
							alert(r.msg, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(testCaseId){
			var rowData = $("#jqGrid").jqGrid('getRowData',testCaseId);
			$.ajax({
				type: "POST",
			    url: baseURL + "testcase2/test/queryTestcaseList",
                contentType: "application/x-www-form-urlencoded",
			    data: rowData,
			    success: function(r){
					if(r.code == 200){
						if(!r.resultList||r.resultList.length==0){
							alert("数据不存在");
							return;
						}
		                vm.testcase  = r.resultList[0];
					}else{
						alert(r.msg);
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