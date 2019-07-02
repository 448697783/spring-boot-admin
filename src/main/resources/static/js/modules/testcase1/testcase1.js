$(function() {
	//表格
	layui.use('table', function() {
		var table = layui.table;
		table.render({
			elem: '#layuiTable',
			id: 'tableId',
			height: 'full-20',
			//toolbar: '#toolbar',
			method: 'get', //接口http请求类型，默认：get
			url: baseURL + 'testcase1/queryTestcase1List', //?page=1&limit=10（该参数可通过 request 自定义）
			//where: {token: 'sasasas', id: 123}, //接口的其它参数
			/* request: {
				pageName: 'curr', //页码的参数名称，默认：page
				limitName: 'nums', //每页数据量的参数名，默认：limit
			}, */
			response: {
				statusName: 'code', //规定数据状态的字段名称，默认：code
				statusCode: 200, //规定成功的状态码，默认：0
				msgName: 'msg', //规定状态信息的字段名称，默认：msg
				countName: 'count', //规定数据总数的字段名称，默认：count
				dataName: 'resultList', //规定数据列表的字段名称，默认：data
			},
			page: true, //是否分页
			limit: 10, //每页显示的条数
			limits: [10, 20, 30], //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
			cols: [
				[{
						type: 'checkbox',
						fixed: 'left',
					},
					{
						field: 'controllerName', //字段名
						title: 'controllerName', //标题
						width: 200,
						sort: true, //是否允许排序 默认：false
						fixed: 'left' //固定列
					}, {
						field: 'describe',
						title: 'describe',
						width: 200
					}, {
						field: 'method',
						title: 'method',
						width: 200,
						sort: true
					}, {
						field: 'testCaseId',
						title: 'testCaseId',
						width: 200
					}, {
						field: 'deptId',
						title: 'deptId',
						width: 200
					}, {
						field: 'createdTime',
						title: 'createdTime',
						width: 200,
						sort: true
					}, {
						field: 'updatedTime',
						title: 'updatedTime',
						width: 200,
						sort: true
					}, {
						field: 'execLastTime',
						title: 'execLastTime',
						width: 200
					}, {
						field: 'status',
						title: 'status',
						width: 200,
						sort: true
					}
				]
			]
		});
	})

	//日期
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//日期时间选择器
		laydate.render({
			elem: '#createdTime',
			type: 'datetime',
			done: function(value) {
				vm.testcase1.createdTime = value
			}
		});
		laydate.render({
			elem: '#updatedTime',
			type: 'datetime',
			done: function(value) {
				vm.testcase1.updatedTime = value
			}
		});
		laydate.render({
			elem: '#execLastTime',
			type: 'datetime',
			done: function(value) {
				vm.testcase1.execLastTime = value
			}
		});
	})
})

var vm = new Vue({
	el: '#rrapp',
	data: {
		showList: true,
		title: '',
		testcase1: {
			controllerName: null,
			describe: null,
			method: null,
			testCaseId: null,
			deptId: null,
			createdTime: null,
			updatedTime: null,
			execLastTime: null,
			status: null
		}
	},
	methods: {
		query: function() {
			vm.reload();
		},
		add: function() {
			vm.showList = false;
			vm.title = "新增";
			vm.testcase1 = {};
		},
		update: function(event) {
			var testCaseId = getSelectedOneRow();
			if (testCaseId == undefined) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.getInfo(testCaseId)
		},
		saveOrUpdate: function(event) {
			var url = vm.testcase1.testCaseId ? "testcase1/editTestcase1" : "testcase1/saveTestcase1";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.testcase1),
				success: function(r) {
					if (r.code == 200) {
						alert(r.msg, function(index) {
							vm.reload()
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		del: function(event) {
			var testCaseIds = getSelectedMoreRow();
			if (testCaseIds == undefined) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type: "POST",
					url: baseURL + "testcase1/deleteTestcase1List",
					contentType: "application/json",
					data: JSON.stringify(testCaseIds),
					success: function(r) {
						if (r.code == 200) {
							alert(r.msg, function(index) {
								vm.reload()
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(fid) {
			$.ajax({
				type: "POST",
				url: baseURL + "testcase1/queryTestcase1List",
				contentType: "application/x-www-form-urlencoded",
				data: fid,
				success: function(r) {
					if (r.code == 200) {
						if (!r.resultList || r.resultList.length == 0) {
							alert("数据不存在");
							return;
						}
						vm.testcase1 = r.resultList[0];
					} else {
						alert(r.msg);
					}
				}
			});
		},
		reload: function(event) {
			vm.showList = true;
			//var keyVal = document.getElementById('keyWords').value;
			layui.use('table', function() {
				var table = layui.table;
				table.reload('tableId', {
					where: { //设定异步数据接口的额外参数
						//controllerName: keyVal
					},
					page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			})
		}
	}
});
