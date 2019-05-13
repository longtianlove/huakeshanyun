/*
*  @author dp
*  @since 2019-01-21
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbOrder/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
		 	 ,{field: 'orderNo', title: '订单编号', minWidth:100, align:"center"}
		 	 ,{field: 'orderPrice', title: '订单价格', minWidth:100, align:"center"}
		 	 ,{field: 'userId', title: '用户编号', minWidth:100, align:"center"}
		 	 ,{field: 'nickname', title: '用户名称', minWidth:100, align:"center"}
		 	 ,{field: 'orderTime', title: '订单创建时间', minWidth:100, align:"center"}
		 	 ,{field: 'merchandiseName', title: '商品名称', minWidth:100, align:"center"}
		 	 ,{field: 'productNum', title: '商品数量', minWidth:100, align:"center"}
		 	 ,{field: 'voucherPath', title: '打款凭证', minWidth:100, align:"center",templet:function(d){
		 		 if(d.voucherPath){
		 			 return '<span onclick="javascript:maxImg(\''+d.voucherPath+'\')"><img   src="'+d.voucherPath+'" /></span>'
		 			 +'<img style="display:none" id="'+d.voucherPath+'" src="'+d.voucherPath+'" />';
		 		 }else{
		 			 return '';
		 		 }
		 		 }}
		 	 ,{field: 'orderStatus', title: '订单状态', minWidth:100, align:"center",templet:function(d){
		 		 if(d.orderStatus=="0"){ 
		 			 return '<span class="layui-badge layui-bg-badge">待支付</span>';
		 		 }else if(d.orderStatus=="1"){
		 			return '<span class="layui-green layui-bg-green">支付成功</span>';
		 		 }else if(d.orderStatus=="2"){
		 			return '<span class="layui-red layui-bg-red">支付失败</span>';
		 		 }
		 	 }}	 
		 	 ,{fixed: 'right', title:'操作', minWidth:200, align:'center', toolbar: '#barDemo'}
		 	 // 	 
        ]] 
    });
 
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	orderNo: $("#orderNo").val()
            } 
        })
    });
    
    table.on('tool(dicList)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
       
        if(layEvent === 'pay'){ //查看
      	  layer.confirm('确认客户已经付款？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/tbOrder/apppay",{
               	 id : data.id
                },function(data){
                    layer.close(index);
                    tableIns.reload(); 
                    layer.msg(data.msg);
                })
            })
        } 
    });   
 
    //添加角色
    function addDic(edit){
        var title = edit===null?"添加 ":"编辑 ";
        layui.layer.open({ 
            title : title,
            type : 2,  
            area : ["360px","470px"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    	   body.find(".id").val(edit.id); 
                    	   body.find(".orderNo").val(edit.orderNo); 
                    	   body.find(".orderName").val(edit.orderName); 
                    	   body.find(".userId").val(edit.userId); 
                    	   body.find(".createTime").val(edit.createTime); 
                    	   body.find(".productType").val(edit.productType); 
                    	   body.find(".status").val(edit.status); 
                    form.render(); 
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回展现列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    }

    $(".add_btn").click(function(){
        addDic(null);
    });

    $(".edit_btn").click(function(){
        var checkStatus = table.checkStatus('dicListTable'),
            data = checkStatus.data;
        if(data.length > 0){
            addDic(data[0]);
        }else{
            layer.msg("请选择需要修改的数据"); 
        }
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
                $.get("/tbOrder/delBatch",{
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
function maxImg(url) {
	layer.open({ type: 1, title: false, closeBtn:0, shadeClose: true, 
	area: [ '50%', '50%'],
	content: '<img src="'+url+'"/>' 
	}); 
}
