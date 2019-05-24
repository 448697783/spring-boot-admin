$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL+'sys/generator/list',
        datatype: "json",
        colModel: [			
			{ label: '表名', name: 'TABLENAME', width: 100, key: true },
			{ label: '模块名', name: 'MODULE', width: 70},
			{ label: '表备注', name: 'TABLECOMMENT', width: 100 },
			{ label: '创建时间', name: 'CREATETIME', width: 100 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50,100,200],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        },
        onSelectRow : function(ids) {
        	var grid = $("#jqGrid");
		    var selectedIDs = grid.getGridParam("selarrrow");
		    
		    var tableName = selectedIDs[0];
			if(tableName == null){
				return ;
			}
			jQuery("#edit").jqGrid(
                'setGridParam',
                {
                  url: baseURL+'sys/generator/queryTableColums?aliase='+vm.selected+'&tableName='+tableName,
                  page : 1
                });
            jQuery("#edit").jqGrid('setCaption',
                "Invoice Detail: " + ids).trigger(
                'reloadGrid');
        },
    });
});


$(function () {
	lastsel=-1;
    $("#edit").jqGrid({
        //url: baseURL+'sys/generator/list',
        datatype: "json",
        edit:true,
        cellEdit: true,
        cellsubmit: "clientArray",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        colModel: [			
			{ label: '列名', name: 'COLUMNNAME', width: 80, key: true },
			{ label: '描述', name: 'COLUMNCOMMENT', width: 100, key: true ,editable :true},
			{ label: '类型', name: 'DATATYPE', width: 50},
			{ label: '字符串长度范围@Size()', name: 'CHARACTER_MAXIMUM_LENGTH', width: 70,editable :true},
			{ label: '数字取值范围@Max,Min', name: 'NUMERIC_PRECISION', width: 70,editable :true},
			{ label: '是否允许为空@NotNull', name: 'IS_NULLABLE', width: 70,editable:true,edittype:"select",editoptions : {value : "YES:YES;NO:NO"}},
			{ label: '主键类型', name: 'COLUMNKEY', width: 30 },
			{ label: 'EXTRA', name: 'EXTRA', width: 30 }
        ],
        ondblClickRow : function(rowid,iRow,iCol,e) {
            if (iRow && iRow !== lastsel) {
              jQuery('#edit').jqGrid('restoreRow', lastsel);
              jQuery('#edit').jqGrid('editRow', iRow, true);
//              $("#fieldGrid").jqGrid('saveRow', iRow);//保存上一行

              lastsel = rowid;
            }
        },
		viewrecords: true,
        height: 385,
        autowidth:true,
        multiselect: true,
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#edit").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        },
    });
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			tableName: "",
			projectName:'productName',
			packageName:'com.csi',
			modeName:"",
			tableAliase:"",
			queryVo:true,
			editVo:false,
			entity:true,
			controller:true,
			service:true,
			iservice:true,
			dao:true,
			sql:null,
		},
		items:[],  
        selected:''  
	},
	mounted:function(){
		this.select();
	},
	methods: {
		select:function(){
			$.ajax({
				type: "POST",
			    url: baseURL + "databaseinfo/queryDatabaseInfoList",
                contentType: "application/json",
			    success: function(r){
					if(r.code == 200){
						if(r.resultList&&r.resultList.length>0){
							vm.items = r.resultList;
						}
					}else{
						alert(r.msg);
					}
				}
			});
		},
		query: function () {
			
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'tableName': vm.q.tableName,'aliase':vm.selected},
                page:1 
            }).trigger("reloadGrid");
		},
		generator: function() {
			var tableNames = getSelectedRows();
			if(tableNames != null||vm.q['sql']!=null){
//				alert("请选择一条记录或填写sql");
				vm.q['tables']=tableNames;
				vm.q['aliase']=vm.selected;
				location.href = baseURL+"sys/generator/code?&text="+encodeURI(JSON.stringify(vm.q))+"&token="+token;
			}else{
				alert("请选择一条记录");
//				alert(tableNames == null||vm.q['sql']==null);
			}
		},
		reportGenerator: function() {
			
//			if(tableNames == null){
//				return ;
//			}
//			vm.q['tables']=tableNames;
//			vm.q['aliase']=vm.selected;
//			location.href = baseURL+"sys/generator/code?&text="+encodeURI(JSON.stringify(vm.q))+"&token="+token;
		}
	}
	
});

