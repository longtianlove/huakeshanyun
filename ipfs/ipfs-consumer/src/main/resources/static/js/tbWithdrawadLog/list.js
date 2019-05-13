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
            {type: "checkbox", fixed:"left", width:50}
            ,{field: 'userId', title: '用户编号', minWidth:100, align:"center"}
            ,{field: 'nickname', title: '用户昵称', minWidth:100, align:"center"}
            ,{field: 'phone', title: '平台账号', minWidth:100, align:"center"}
            ,{field: 'userName', title: '真实姓名', minWidth:100, align:"center"}
            ,{field: 'userAccount', title: '用户银行账户', minWidth:100, align:"center"}
		 	 ,{field: 'cashNumber', title: '提现金额', minWidth:100, align:"center"}
		 	 ,{field: 'actualAmount', title: '实际扣除', minWidth:100, align:"center"}
		 	 ,{field: 'createTime', title: '提现发起时间', minWidth:100, align:"center"}
		 	 ,{field: 'handfree', title: '手续费', minWidth:100, align:"center"}
		 	 ,{field: 'verifyInfo', title: '审核信息', minWidth:100, align:"center"}
		 	 ,{field: 'cashStatus', title: '审核状态', minWidth:100, templet:function(d){
		 		 if(d.cashStatus=="0"){ 
		 			 return '<span class="layui-badge layui-bg-badge">待审核</span>';
		 		 }else if(d.cashStatus=="1"){
		 			 return '<span class="layui-green layui-bg-green">通过</span>';
		 		 }else if(d.cashStatus=="2"){
		 			 return '<span class="layui-red layui-bg-red">不通过</span>';
		 		 }
		 	 }}	
		 	 ,{fixed: 'right', title:'操作', minWidth:200, align:'center', toolbar: '#barDemo'}
        ]] 
    });
    
    table.on('tool(dicList)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
       
        if(layEvent === 'unpass'){ 
    		  layer.prompt({title: '请输入不通过的理由，并确认', formType: 2}, function(text, index){
    			  $.get("/tbWithdrawadLog/unpass",{
                  	  id : data.id,
                  	verifyInfo:text
                    },function(data){
                        layer.close(index);
                        tableIns.reload();
                        if (data.data){
                            layer.msg("审核成功！");
                        } else {
                            layer.msg("审核失败!");
                        }
                    })
    		  });
        		
        } else if(layEvent === 'pass'){ 
      	  layer.confirm('确定通过该用户审核？', {icon: 3, title: '提示信息'}, function (index) {
      		  layer.close(index);
                $.get("/tbWithdrawadLog/pass",{
              	  id : data.id
                },function(data){
                    tableIns.reload();
                    if (data.data){
                        layer.msg("审核成功！");
                    } else {
                        layer.msg("审核失败！");
                    }
                })
            })
        }
      }) 
 
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
 
    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('dicListTable'),
            data = checkStatus.data,
            idArr = [];
        if(data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id); 
            }
            layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/tbWithdrawadLog/delBatch",{
                    idArr : idArr.toString()
                },function(data){
                    layer.close(index);
                    tableIns.reload();
                    if (data.data){
                        layer.msg("删除成功！");
                    } else {
                        layer.msg(data.msg);
                    }
                })
            })
        }else{
            layer.msg("请选择需要删除的数据");
        }
    })

})