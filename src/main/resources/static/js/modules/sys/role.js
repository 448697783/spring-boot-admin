$(function() {
	//表格
	layui.use('table', function() {
		var table = layui.table;
		var toolbar = '<div class="layui-btn-container">' +
			'<div class="layui-input-inline" style="margin-right:10px;">' +
			'<input type="text" name="keyWords" id="keyWords" autocomplete="off" placeholder="请输入关键字" class="layui-input" style="height:32px;" v-model="q.roleName" @keyup.enter="query">' +
			'</div>' +
			'<button v-if="hasPermission(' + 'sys:role:save' +
			')" class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>新增</button>' +
			'<button v-if="hasPermission(' + 'sys:role:update' +
			')" class="layui-btn layui-btn-sm" lay-event="update"><i class="layui-icon layui-icon-edit"></i>修改</button>' +
			'<button v-if="hasPermission(' + 'sys:role:delete' +
			')" class="layui-btn layui-btn-sm" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</button>' +
			'<button class="layui-btn layui-btn-sm" lay-event="query"><i class="layui-icon layui-icon-search"></i>查询</button>' +
			'</div>'
		table.render({
			elem: '#layuiTable',
			id: 'tableId',
			height: 'full-20',
			//toolbar: '#toolbar',
			method: 'get', //接口http请求类型，默认：get
			url: baseURL + 'sys/role/list', //?page=1&limit=10（该参数可通过 request 自定义）
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
						field: 'roleId', //字段名
						title: '角色ID', //标题
						width: 100,
						sort: true, //是否允许排序 默认：false
						fixed: 'left' //固定列
					}, {
						field: 'roleName',
						title: '角色名称',
						width: 200
					}, {
						field: 'deptName',
						title: '所属部门',
						width: 200,
						sort: true
					}, {
						field: 'remark',
						title: '备注',
						width: 200
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
	});

});

//菜单树
var menu_ztree;
var menu_setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url: "nourl"
		}
	},
	check: {
		enable: true,
		nocheckInherit: true
	}
};

//部门结构树
var dept_ztree;
var dept_setting = {
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

//数据树
var data_ztree;
var data_setting = {
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
	},
	check: {
		enable: true,
		nocheckInherit: true,
		chkboxType: {
			"Y": "",
			"N": ""
		}
	}
};

var vm = new Vue({
	el: '#rrapp',
	data: {
		q: {
			roleName: null
		},
		showList: true,
		title: null,
		role: {
			deptId: null,
			deptName: null
		}
	},
	methods: {
		query: function() {
			vm.reload();
		},
		add: function() {
			vm.showList = false;
			vm.title = "新增";
			vm.role = {
				deptName: null,
				deptId: null
			};
			vm.getMenuTree(null);

			vm.getDept();

			vm.getDataTree();
		},
		update: function() {
			var roleId = getSelectedOneRow();
			if (roleId == undefined) {
				return;
			}

			vm.showList = false;
			vm.title = "修改";
			vm.getDataTree();
			vm.getMenuTree(roleId.roleId);

			vm.getDept();
		},
		del: function() {
			var roleIds = getSelectedMoreRow();
			if (roleIds == undefined) {
				return;
			}
			var id = [];
			for (var i = 0; i < roleIds.length; i++) {
				id.push(roleIds[i]['roleId'])
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type: "POST",
					url: baseURL + "sys/role/delete",
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
		getRole: function(roleId) {
			$.get(baseURL + "sys/role/info/" + roleId, function(r) {
				vm.role = r.role;

				//勾选角色所拥有的菜单
				var menuIds = vm.role.menuIdList;
				for (var i = 0; i < menuIds.length; i++) {
					var node = menu_ztree.getNodeByParam("menuId", menuIds[i]);
					menu_ztree.checkNode(node, true, false);
				}

				//勾选角色所拥有的部门数据权限
				var deptIds = vm.role.deptIdList;
				for (var i = 0; i < deptIds.length; i++) {
					var node = data_ztree.getNodeByParam("deptId", deptIds[i]);
					data_ztree.checkNode(node, true, false);
				}

				vm.getDept();
			});
		},
		saveOrUpdate: function() {
			//获取选择的菜单
			var nodes = menu_ztree.getCheckedNodes(true);
			var menuIdList = new Array();
			for (var i = 0; i < nodes.length; i++) {
				menuIdList.push(nodes[i].menuId);
			}
			vm.role.menuIdList = menuIdList;

			//获取选择的数据
			var nodes = data_ztree.getCheckedNodes(true);
			var deptIdList = new Array();
			for (var i = 0; i < nodes.length; i++) {
				deptIdList.push(nodes[i].deptId);
			}
			vm.role.deptIdList = deptIdList;

			var url = vm.role.roleId == null ? "sys/role/save" : "sys/role/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.role),
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
		getMenuTree: function(roleId) {
			//加载菜单树
			$.get(baseURL + "sys/menu/list", function(r) {
				menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r);
				//展开所有节点
				menu_ztree.expandAll(true);

				if (roleId != null) {
					vm.getRole(roleId);
				}
			});
		},
		getDataTree: function(roleId) {
			//加载菜单树
			$.get(baseURL + "sys/dept/list", function(r) {
				data_ztree = $.fn.zTree.init($("#dataTree"), data_setting, r);
				//展开所有节点
				data_ztree.expandAll(true);
			});
		},
		getDept: function() {
			//加载部门树
			$.get(baseURL + "sys/dept/list", function(r) {
				dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting, r);
				var node = dept_ztree.getNodeByParam("deptId", vm.role.deptId);
				if (node != null) {
					dept_ztree.selectNode(node);

					vm.role.deptName = node.name;
				}
			})
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
					var node = dept_ztree.getSelectedNodes();
					//选择上级部门
					vm.role.deptId = node[0].deptId;
					vm.role.deptName = node[0].name;

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
						roleName: keyVal
					},
					page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			})
		}
	}
});
