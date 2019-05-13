/*
*  @author dp
*  @since 2019-03-11
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/appUserinfo/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
//		 	 ,{field: 'id', title: '主键', minWidth:100, align:"center"}
		 	 ,{field: 'userId', title: '用户编号', minWidth:100, align:"center"}
		 	 ,{field: 'phone', title: '账户', minWidth:100, align:"center"}
		 	 ,{field: 'nickname', title: '昵称', minWidth:100, align:"center"}
		 	 ,{field: 'lv', title: '等级', minWidth:100, align:"center"}
		 	 ,{field: 'code', title: '邀请码', minWidth:100, align:"center"}
		 	 ,{field: 'coin', title: '金币', minWidth:100, align:"center",templet:function(d){
		 		 if(d.coin){ 
		 			 return formatNumber(d.coin);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'storage', title: '存储', minWidth:100, align:"center",templet:function(d){
		 		 if(d.storage){ 
		 			 return d.storage+"T";
		 		 }else{
		 			 return "0T";
		 			 
		 		 }}
		 		 }
		 	 ,{field: 'giftCoin', title: '推广礼包金额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.giftCoin){ 
		 			 return formatNumber(d.giftCoin);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'limitCoin', title: '距离出局还剩金额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.limitCoin){ 
		 			 return formatNumber(d.limitCoin);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'vipminerStatus', title: '超级矿工', minWidth:100, align:"center",templet:function(d){
		 		 if(d.vipminerStatus=="1"){ 
		 			 return '<span class="layui-red layui-bg-red">超级矿工</span>';
		 		 }else{
		 			 return '<span class="layui-green layui-bg-green">普通用户</span>';
		 		 }}
		 		 }
		 	 ,{field: 'personalStatus', title: '实名认证', minWidth:100, align:"center",templet:function(d){
		 		 if(d.personalStatus=="1"){ 
		 			 return '<span class="layui-green layui-bg-green">已认证</span>';
		 		 }else{
		 			 return '<span class="layui-red layui-bg-red">未认证</span>';
		 			 
		 		 }}
		 		 }
		 	,{fixed: 'right', title:'操作', minWidth:300, align:'center', toolbar: '#barDemo'}
        ]] 
    });
 
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                nickname: $("#nickname").val(),
                phone: $("#phone").val()
            } 
        })
    });
 // 购买
	function buy(data) {

		var h = "340px";
		var title = "购买";
		layer.open({
			title : title,
			type : 2,
			area : [ "420px", h ],
			content : "appUserinfo/buy.html?id="+data.userId,
			success : function(layero, index) {
				layer.setTop(layero);
				
				  form.render();
				 
			},
			 end:function(){
				 tableIns.reload();
			 }
		})
	}
	// 升级
	function upgrade(data) {
		
		var h = "400px";
		var title = "升级";
		layer.open({
			title : title,
			type : 2,
			area : [ "420px", h ],
			content : "appUserinfo/grade.html?id="+data.userId,
			success : function(layero, index) {
				layer.setTop(layero);
				
				form.render();
				
			},
			end:function(){
				tableIns.reload();
			}
		})
	}
	// 修改存储
	function storage(data) {
		
		var h = "400px";
		var title = "存储升级";
		layer.open({
			title : title,
			type : 2,
			area : [ "420px", h ],
			content : "appUserinfo/storage.html?id="+data.userId,
			success : function(layero, index) {
				layer.setTop(layero);
				
				form.render();
				
			},
			end:function(){
				tableIns.reload();
			}
		})
	}
    
    table.on('tool(dicList)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        if(layEvent === 'buy'){ 
    	  buy(data);
        }
        if(layEvent === 'upGrade'){ 
        	upgrade(data);
        }
        if(layEvent === 'upStorage'){ 
        	storage(data);
        }
        if(layEvent === 'super'){ 
        	layer.confirm('确定该用户成为超级矿工？', {icon: 3, title: '提示信息'}, function (index) {
        		  layer.close(index);
                  $.get("/appUserinfo/super",{
                	  userId : data.userId
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
                    	  body.find("#id").val(edit.id); 
                    	   body.find(".nickname").val(edit.nickname); 
                    	   body.find(".avater").val(edit.avater); 
                    	   body.find(".invitationCode").val(edit.invitationCode); 
                    	   body.find(".userId").val(edit.userId); 
                    	   body.find(".code").val(edit.code); 
                    	   body.find(".codePath").val(edit.codePath); 
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
                $.get("/appUserinfo/delBatch",{
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
