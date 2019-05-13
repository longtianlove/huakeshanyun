/*
*  @author dp
*  @since 2019-03-17
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbVipminer/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
//		 	 ,{field: 'id', title: '', minWidth:100, align:"center"}
		 	 ,{field: 'userId', title: '用户编号', minWidth:100, align:"center"}
		 	 ,{field: 'nickname', title: '用户昵称', minWidth:100, align:"center"}
		 	 ,{field: 'realName', title: '用户真实姓名', minWidth:100, align:"center"}
		 	 ,{field: 'limitCoin', title: '限额', minWidth:100, align:"center"}
		 	 ,{field: 'createTime', title: '创建时间', minWidth:100, align:"center"}
		 	 ,{field: 'status', title: '出局状态', minWidth:100, align:"center",templet:function(d){
		 		 if(d.status=="1"){ 
		 			 return '<span class="layui-green layui-bg-green">未出局</span>';
		 		 }else{
		 			 return '<span class="layui-red layui-bg-red">已出局</span>';
		 			 
		 		 }}
		 		 }
//		 	 ,{field: 'vipSort', title: '排序编号', minWidth:100, align:"center"}
		 	 ,{field: 'giftCoin', title: '平台分红收益', minWidth:100, align:"center"}
		 	,{fixed: 'right', title:'操作', minWidth:200, align:'center', toolbar: '#barDemo'}
        ]] 
    });
    
    table.on('tool(dicList)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        if(layEvent === 'rebuy'){ 
        	layer.confirm('确定给该用户复购？', {icon: 3, title: '提示信息'}, function (index) {
        		  layer.close(index);
                  $.get("/tbVipminer/rebuy",{
                	  id : data.id
                  },function(data){
                      tableIns.reload();
                      if (data.data){
                          layer.msg("成功！");
                      } else {
//                    	  console.log(data);
                          layer.msg(data.msg);
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
            	nickname: $("#nickname").val()
            } 
        })
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
                    	   body.find(".userId").val(edit.createTime); 
                    	   body.find(".limitCoin").val(edit.limitCoin); 
                    	   body.find(".createTime").val(edit.createTime); 
                    	   body.find(".endTime").val(edit.endTime); 
                    	   body.find(".vipSort").val(edit.vipSort); 
                    	   body.find(".giftCoin").val(edit.giftCoin); 
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
                $.get("/tbVipminer/delBatch",{
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