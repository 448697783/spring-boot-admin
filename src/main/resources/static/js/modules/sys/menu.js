layui.config({
	base: '../../plugins/'
}).extend({
	treetable: 'treetable-lay/treetable'
}).use(['layer', 'table', 'treetable'], function() {
	var $ = layui.jquery;
	var table = layui.table;
	var layer = layui.layer;
	var treetable = layui.treetable;

	// 渲染表格
	renderTable = function() {
		layer.load(2);
		treetable.render({
			treeColIndex: 2,
			treeSpid: 0,
			treeIdName: 'menuId',
			treePidName: 'parentId',
			treeDefaultClose: true,
			treeLinkage: false,
			elem: '#layuiTable',
			id: 'tableId',
			url: baseURL + "sys/menu/list",
			page: false,
			cols: [
				[{
						type: 'checkbox',
						fixed: 'left'
					},
					{
						field: 'menuId',
						title: '菜单ID'
					},
					{
						field: 'name',
						title: '菜单名称'
					},
					{
						field: 'parentName',
						title: '上级菜单'
					},
					{
						field: 'parentId',
						title: 'parentId'
					},
					{
						field: 'icon',
						title: '图标',
						templet: function(d) {
							return d.icon == null ? '' : '<i class="' + d.icon + ' fa-lg"></i>';
						}
					},
					{
						field: 'type',
						title: '类型',
						templet: function(d) {
							if (d.type === 0) {
								return '<span class="label label-primary">目录</span>';
							}
							if (d.type === 1) {
								return '<span class="label label-success">菜单</span>';
							}
							if (d.type === 2) {
								return '<span class="label label-warning">按钮</span>';
							}
						}
					},
					{
						field: 'orderNum',
						title: '排序号'
					},
					{
						field: 'url',
						title: '菜单URL'
					},
					{
						field: 'perms',
						title: '授权标识'
					}
				]
			],
			done: function() {
				layer.closeAll('loading');
			}
		});
	};

	renderTable();
});

var renderTable;

layui.use('form', function() {
	var form = layui.form;
	form.on('radio(radioChange)', function(data) {
		vm.menu.type = data.value;
	});

});

var setting = {
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
	}
};
var ztree;

var vm = new Vue({
	el: '#rrapp',
	data: {
		showList: true,
		title: null,
		menu: {
			parentName: null,
			parentId: 0,
			type: 1,
			orderNum: 0
		}
	},
	methods: {
		getMenu: function() {
			//加载菜单树
			$.get(baseURL + "sys/menu/select", function(r) {
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
				ztree.selectNode(node);

				vm.menu.parentName = node.name;
			})
		},
		add: function() {
			vm.showList = false;
			vm.title = "新增";
			vm.menu = {
				parentName: null,
				parentId: 0,
				type: 1,
				orderNum: 0
			};
			vm.getMenu();
		},
		update: function() {
			var menuId = getSelectedOneRow();
			if (menuId == undefined) {
				return;
			}
			$.get(baseURL + "sys/menu/info/" + menuId.menuId, function(r) {
				vm.showList = false;
				vm.title = "修改";
				vm.menu = r.menu;
				vm.$nextTick(function() {
					layui.use('form', function() {
						var form = layui.form;
						form.render();
					});
				});

				vm.getMenu();
			});
		},
		del: function() {
			var menuId = getSelectedOneRow();
			if (menuId == undefined) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type: "POST",
					url: baseURL + "sys/menu/delete",
					data: "menuId=" + menuId.menuId,
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
			});
		},
		saveOrUpdate: function(event) {
			var url = vm.menu.menuId == null ? "sys/menu/save" : "sys/menu/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.menu),
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
		menuTree: function() {
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择菜单",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#menuLayer"),
				btn: ['确定', '取消'],
				btn1: function(index) {
					var node = ztree.getSelectedNodes();
					//选择上级菜单
					vm.menu.parentId = node[0].menuId;
					vm.menu.parentName = node[0].name;

					layer.close(index);
				}
			});
		},
		reload: function() {
			vm.showList = true;
			renderTable();
		}
	}
});
