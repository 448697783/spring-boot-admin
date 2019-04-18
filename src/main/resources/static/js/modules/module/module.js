var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        menu:{
            parentName:null,
            parentId:0,
            type:1,
            orderNum:0,
            projectName:'',
            packageName:''
        }
    },
    methods: {
        getMenu: function(){
            //加载菜单树
            $.get(baseURL + "sys/module/select", function(r){
                ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
                var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
                ztree.selectNode(node);

                vm.menu.parentName = node.name;
            })
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            var parentId=getMenuId();
            if(parentId == null){
                return ;
            }
            vm.menu = {parentName:null,parentId:parentId,type:1,orderNum:0};
            vm.getMenu();
        },
        update: function () {
            var menuId = getMenuId();
            if(menuId == null){
                return ;
            }

            $.get(baseURL + "sys/module/info/"+menuId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.menu = r.menu;

                vm.getMenu();
            });
        },
        del: function () {
            var menuId = getMenuId();
            if(menuId == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/module/delete",
                    data: "menuId=" + menuId,
                    success: function(r){
                        if(r.code === 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.menu.menuId == null ? "sys/module/save" : "sys/module/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.menu),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        generatorCode: function (event) {
        	
        	var ids = getMenuIds();
        	if (vm.menu.projectName==null||vm.menu.projectName.trim() == '') {
				alert("请输入项目名称");
				return false;
        	} 
        	if (vm.menu.packageName==null||vm.menu.packageName.trim() == '') {
				alert("请输入包名");
				return false;
        	} 
        	var str = '';
        	for(var i=0;i<ids.length;i++){
        		str+=ids[i].id;
        		if(i+1<ids.length){
        			str+=','
        		}
        	}
        	location.href = baseURL+"sys/module/springbootproject?dis="+str+"&projectName="+vm.menu.projectName+"&packageName="+vm.menu.packageName+"&token="+token;
        },
        menuTree: function(){
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
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.menu.parentId = node[0].menuId;
                    vm.menu.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            Menu.table.refresh();
        }
    }
});


var Menu = {
    id: "menuTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', checkbox: true},
        {title: 'ID', field: 'menuId', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '模块名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '280px'},
        {title: '图标', field: 'icon', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(item, index){
            return item.icon == null ? '' : '<i class="'+item.icon+' fa-lg"></i>';
        }},
        {title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true, width: '100px', formatter: function(item, index){
            if(item.type === 0){
                return '<span class="label label-primary">目录</span>';
            }
            if(item.type === 1){
                return '<span class="label label-success">maven</span>';
            }
            if(item.type === 2){
                return '<span class="label label-warning">按钮</span>';
            }
        }},
        {title: '模版名	', field: 'url', align: 'center', valign: 'middle', sortable: true, width: '460px'},
        {title: 'maven-rep', field: 'perms', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


function getMenuId () {
    var selected = $('#menuTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } 
    if(selected.length >1){
    	alert("只能选择一条记录一条记录");
    }
    else {
        return selected[0].id;
    }
}

function getMenuIds () {
    var selected = $('#menuTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请至少选择一个模块");
        return false;
    } 
    else {
        return selected;
    }
}


$(function () {
    var colunms = Menu.initColumn();
    var table = new TreeTable(Menu.id, baseURL + "sys/module/list", colunms);
    table.setExpandColumn(2);
    table.setIdField("menuId");
    table.setCodeField("menuId");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    table.init();
    Menu.table = table;
});
