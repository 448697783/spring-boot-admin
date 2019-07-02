$(function() {
	//表格
	layui.use('table', function() {
		var table = layui.table;
		table.render({
			elem: '#layuiTable',
			id: 'tableId',
			height: 'full-55',
			//toolbar: '#toolbar',
			method: 'get', //接口http请求类型，默认：get
			url: baseURL + 'sys/user/list', //?page=1&limit=10（该参数可通过 request 自定义）
			//where: {token: 'sasasas', id: 123}, //接口的其它参数
			/* request: {
				pageName: 'curr', //页码的参数名称，默认：page
				limitName: 'nums', //每页数据量的参数名，默认：limit
			}, */
			//将其解析成 table 组件所规定的数据
			parseData: function(res) { //res 即为原始返回的数据
				return {
					"code": res.code, //解析接口状态
					"msg": '', //解析提示文本
					"count": res.page.totalCount, //解析数据长度
					"data": res.page.list //解析数据列表
				};
			},
			/* response: {
				statusName: 'code', //规定数据状态的字段名称，默认：code
				statusCode: 200, //规定成功的状态码，默认：0
				msgName: 'msg', //规定状态信息的字段名称，默认：msg
				countName: 'count', //规定数据总数的字段名称，默认：count
				dataName: 'resultList', //规定数据列表的字段名称，默认：data
			}, */
			page: true, //是否分页
			limit: 10, //每页显示的条数
			limits: [10, 20, 30], //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
			cols: [
				[{
						type: 'checkbox',
						fixed: 'left',
					},
					{
						field: 'userId', //字段名
						title: '用户ID', //标题
						width: 100,
						sort: true, //是否允许排序 默认：false
						fixed: 'left' //固定列
					}, {
						field: 'username',
						title: '用户名',
						width: 200
					}, {
						field: 'deptName',
						title: '所属部门',
						width: 200,
						sort: true
					}, {
						field: 'email',
						title: '邮箱',
						width: 200
					}, {
						field: 'mobile',
						title: '手机号',
						width: 200
					}, {
						field: 'status',
						title: '状态',
						width: 200,
						templet: function(d) {
							return d.status === 0 ?
								'<span class="label label-danger">禁用</span>' :
								'<span class="label label-success">正常</span>';
						}
					}, {
						field: 'createTime',
						title: '创建时间',
						width: 200,
						sort: true
					}
				]
			]
		});
		//头部左侧按钮点击事件
		table.on('toolbar(test)', function(obj) {
			switch (obj.event) {
				case 'add':
					vm.add();
					break;
				case 'update':
					vm.update();
					break;
				case 'del':
					vm.del();
					break;
				case 'query':
					vm.query();
					break;
			};
		});
	})


	layui.use('form', function() {
		var form = layui.form;
		form.on('checkbox(checkboxChange)', function(data) {
			if (data.elem.checked) {
				vm.user.roleIdList.push(data.value);
			} else {
				vm.user.roleIdList.splice(vm.user.roleIdList.indexOf(data.value), 1);
			}
		});

		form.on('radio(radioChange)', function(data) {
			vm.user.status = data.value;
		});

	});

});

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "deptId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url: "nourl"
		}
	}
};
var ztree;

var vm = new Vue({
	el: '#rrapp',
	data: {
		q: {
			username: null
		},
		showList: true,
		title: null,
		roleList: [],
		user: {
			status: 1,
			deptId: null,
			deptName: null,
			roleIdList: []
		}
	},
	methods: {
		query: function() {
			vm.reload();
		},
		add: function() {
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = [];
			vm.user = {
				deptName: null,
				deptId: null,
				status: 1,
				roleIdList: []
			};

			vm.getDept();
		},
		getDept: function() {
			//加载部门树
			$.get(baseURL + "sys/dept/list", function(r) {
				ztree = $.fn.zTree.init($("#deptTree"), setting, r);
				var node = ztree.getNodeByParam("deptId", vm.user.deptId);
				if (node != null) {
					ztree.selectNode(node);

					vm.user.deptName = node.name;
				}
			})
		},
		update: function() {
			var userId = getSelectedOneRow();
			if (userId == undefined) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.getUser(userId.userId);
		},
		del: function() {
			var userIds = getSelectedMoreRow();
			if (userIds == undefined) {
				return;
			}
			var id = [];
			for (var i = 0; i < userIds.length; i++) {
				id.push(userIds[i]['userId'])
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type: "POST",
					url: baseURL + "sys/user/delete",
					contentType: "application/json",
					data: JSON.stringify(id),
					success: function(r) {
						if (r.code == 0) {
							alert('操作成功', function() {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function() {
			var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.user),
				success: function(r) {
					if (r.code === 0) {
						alert('操作成功', function() {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		getUser: function(userId) {
			$.get(baseURL + "sys/user/info/" + userId, function(r) {
				vm.user = r.user;
				vm.user.password = null;
				vm.$nextTick(function() {
					layui.use('form', function() {
						var form = layui.form;
						form.render();
					});
				});

				vm.getDept();
			});
		},
		getRoleList: function() {
			$.get(baseURL + "sys/role/select", function(r) {
				vm.roleList = r.list;
				vm.$nextTick(function() {
					layui.use('form', function() {
						var form = layui.form;
						form.render();
					});
				});
			});
		},
		deptTree: function() {
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择部门",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function(index) {
					var node = ztree.getSelectedNodes();
					//选择上级部门
					vm.user.deptId = node[0].deptId;
					vm.user.deptName = node[0].name;

					layer.close(index);
				}
			});
		},
		reload: function(event) {
			vm.showList = true;
			var keyVal = document.getElementById('keyWords').value;
			layui.use('table', function() {
				var table = layui.table;
				table.reload('tableId', {
					where: { //设定异步数据接口的额外参数
						username: keyVal
					},
					page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			})
		}
	},
	created() {
		//获取角色信息
		this.getRoleList();
	}
});
