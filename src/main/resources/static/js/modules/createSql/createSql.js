var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			keyWords:null
		},
		showList: true,
		title: null,
		createSql: {
			ordernum:"",
			outTradeNo:"",
			customernum:""
		},
		sql:""
	},
	methods: {

		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.createSql = {};
		},
		update: function (event) {
            
            var rowData = getSelectedOneRow();
			if (rowData == undefined) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.getInfo(rowData)
		},

		
		del: function (event) {
			var ordernums = getSelectedMoreRow();
			if (ordernums == undefined) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type: "POST",
					url: baseURL + "createSql/deleteCreateSqlList",
					contentType: "application/json",
					data: JSON.stringify(ordernums),
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
		create: function(){
			$.ajax({
				type: "POST",
			    url: baseURL + "createSql/queryCreateSqlList",
				contentType: "application/json",
			    data: JSON.stringify(vm.createSql),
			    success: function(r){
					if(r.code == 200){

						// var converter = new showdown.Converter();
						// var html = converter.makeHtml(r.sql);
						vm.sql = r.sql;
		                // vm.createSql  = vm.createSql;
					}else{
						alert(r.msg);
					}
				}
			});
		},
	}
});