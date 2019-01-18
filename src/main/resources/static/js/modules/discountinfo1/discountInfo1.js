$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'discountinfo1/queryDiscountInfo1List',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },
			{ label: '是否启用(1:启用2:不启用)', name: 'enabled', index: 'ENABLED', width: 80 }, 			
			{ label: '活动编码', name: 'activeNumber', index: 'ACTIVE_NUMBER', width: 80 }, 			
			{ label: '更新日期', name: 'updatedOn', index: 'UPDATED_ON', width: 80 }, 			
			{ label: '活动CRON表达式', name: 'validTime', index: 'VALID_TIME', width: 80 }, 			
			{ label: '优惠类型(1:折扣 2:满减)', name: 'discountType', index: 'DISCOUNT_TYPE', width: 80 }, 			
			{ label: '版本号', name: 'versionOptimizedLock', index: 'VERSION_OPTIMIZED_LOCK', width: 80 }, 			
			{ label: '优惠形式JSO格式', name: 'rang', index: 'RANG', width: 80 }, 			
			{ label: '创建日期', name: 'createdOn', index: 'CREATED_ON', width: 80 }, 			
			{ label: '优先级(越大优先级越高)', name: 'priority', index: 'PRIORITY', width: 80 }, 			
			{ label: '活动名称', name: 'activeName', index: 'ACTIVE_NAME', width: 80 }, 			
			{ label: '业务线ID', name: 'merchantId', index: 'MERCHANT_ID', width: 80 }, 			
			{ label: '活动结束时间', name: 'endTime', index: 'END_TIME', width: 80 }, 			
			{ label: '活动开始时间', name: 'startTime', index: 'START_TIME', width: 80 }, 			
			{ label: '产品ID', name: 'productId', index: 'PRODUCT_ID', width: 80 }			
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
		discountInfo1: {
			id:null,
			enabled:null,
			activeNumber:null,
			updatedOn:null,
			validTime:null,
			discountType:null,
			versionOptimizedLock:null,
			rang:null,
			createdOn:null,
			priority:null,
			activeName:null,
			merchantId:null,
			endTime:null,
			startTime:null,
			productId:null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.discountInfo1 = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.discountInfo1.id ? "discountinfo1/editDiscountInfo1" : "discountinfo1/saveDiscountInfo1";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.discountInfo1),
			    success: function(r){
			    	if(r.code === 200){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.message);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var arr = [];
			for(var i = 0;i<ids.length;i++){
				arr.push($("#jqGrid").jqGrid('getRowData',ids[i]))
			}

			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "discountinfo1/deleteDiscountInfo1",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 200){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.message);
						}
					}
				});
			});
		},
		getInfo: function(id){
			var rowData = $("#jqGrid").jqGrid('getRowData',id);
			$.ajax({
				type: "POST",
			    url: baseURL + "discountinfo1/queryDiscountInfo1List",
                contentType: "application/x-www-form-urlencoded",
			    data: rowData,
			    success: function(r){
					if(r.code == 200){
						if(r.resultList.length==0){
							alter("数据不存在");
							return;
						}
		                vm.discountInfo1  = r.resultList[0];
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