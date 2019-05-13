/*
*  @author dp
*  @since 2019-03-13
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbWithdrawadLog/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "radio", fixed:"left", width:50}
//            ,{field: 'userId', title: '用户编号', minWidth:100, align:"center"}
//            ,{field: 'nickname', title: '用户昵称', minWidth:100, align:"center"}
            ,{field: 'phone', title: '平台账号', minWidth:100, align:"center"}
            ,{field: 'userName', title: '真实姓名', minWidth:100, align:"center"}
            ,{field: 'userAccount', title: '用户银行账户', minWidth:100, align:"center"}
            ,{field: 'beforeAmount', title: '提现前余额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.beforeAmount){ 
		 			 return formatNumber(d.beforeAmount);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'cashNumber', title: '提现金额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.cashNumber){ 
		 			 return formatNumber(d.cashNumber);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'actualAmount', title: '实际扣除', minWidth:100, align:"center",templet:function(d){
		 		 if(d.actualAmount){ 
		 			 return formatNumber(d.actualAmount);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'afterAmount', title: '提现后余额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.afterAmount){ 
		 			 return formatNumber(d.afterAmount);
		 		 }else{
		 			 return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'createTime', title: '提现发起时间', minWidth:100, align:"center"}
		 	 ,{field: 'handfree', title: '手续费', minWidth:100, align:"center"}
		 	 ,{field: 'verifyInfo', title: '审核信息', minWidth:100, align:"center"}
		 	 ,{field: 'cashStatus', title: '审核状态', minWidth:100, templet:function(d){
		 		 if(d.cashStatus=="0"){ 
		 			 return '<span class="layui-badge layui-bg-cyan">待审核</span>';
		 		 }else if(d.cashStatus=="1"){
		 			 return '<span class="layui-green layui-bg-green">通过</span>';
		 		 }else if(d.cashStatus=="2"){
		 			 return '<span class="layui-red layui-bg-blue">不通过</span>';
		 		 }else if(d.cashStatus=="3"){
		 		 return '<span class="layui-red layui-bg-red">待复核</span>';
		 	 }
		 	 }}	
        ]] 
    });
    table.on('row(dicList)', function(obj){////注：test是table原始容器的属性 lay-filter="对应的值"
    	obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');//选中行样式
        obj.tr.find('input[lay-type="layTableRadio"]').prop("checked",true);
        var index = obj.tr.data('index')
        var thisData = table.cache.dicListTable;//tableName 表名 id
        //重置数据单选属性
        layui.each(thisData, function(i, item){
          if(index === i){
            item.LAY_CHECKED = true;
          } else {
            delete item.LAY_CHECKED;
          }
        });
        form.render('radio');
   });
 
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	phone: $("#phone").val(),
            	userName: $("#userName").val(),
            	cashStatus: $("#cashStatus").val()
            } 
        })
    });
    
    $(".fisrtApprove").click(function(){
   	 var checkStatus = table.checkStatus('dicListTable'),
        data = checkStatus.data;
    if(data.length > 0){
   	 if(data[0].cashStatus==0){
   		 layui.layer.open({ 
   			 title : "初审",
   			 type : 2,  
   			 area : ["360px","470px"],
   			 content : "firstApprove.html?id="+data[0].id,
   		 });
   	 }else{
            layer.msg("当前记录已审核！"); 
        } 
    }else{
        layer.msg("请选择需要修改的数据"); 
    }
   });
   $(".approved").click(function(){
   	var checkStatus = table.checkStatus('dicListTable'),
   	data = checkStatus.data;
   	if(data.length > 0){
   		if(data[0].cashStatus==0){
   			layer.msg("当前记录未初审，不能审核！"); 
   		}
   		if(data[0].cashStatus==3){
   			layui.layer.open({ 
   				title : "复核",
   				type : 2,  
   				area : ["360px","470px"],
   				content : "approve.html?id="+data[0].id,
   			});
   		}else if(data[0].cashStatus==1||data[0].cashStatus==2){
   			layer.msg("当前记录不用复核！"); 
   		} 
   	}else{
   		layer.msg("请选择需要修改的数据"); 
   	}
   });


    
    //批量删除
//    $(".delAll_btn").click(function(){
//        var checkStatus = table.checkStatus('dicListTable'),
//            data = checkStatus.data,
//            idArr = [];
//        if(data.length > 0) {
//            for (var i in data) {
//                idArr.push(data[i].id); 
//            }
//            layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
//                $.get("/tbWithdrawadLog/delBatch",{
//                    idArr : idArr.toString()
//                },function(data){
//                    layer.close(index);
//                    tableIns.reload();
//                    if (data.data){
//                        layer.msg("删除成功！");
//                    } else {
//                        layer.msg(data.msg);
//                    }
//                })
//            })
//        }else{
//            layer.msg("请选择需要删除的数据");
//        }
//    })

})
