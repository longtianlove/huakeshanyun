/*
*  @author dp
*  @since 2019-03-12
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbPersonalInfo/listData',
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
		 	 ,{field: 'accont', title: '用户账号', minWidth:100, align:"center"}
		 	 ,{field: 'bankCard', title: '银行卡号', minWidth:100, align:"center"}
		 	 ,{field: 'idcard', title: '身份证号', minWidth:100, align:"center"}
		 	 ,{field: 'phone', title: '手机号', minWidth:100, align:"center"}
		 	 ,{field: 'realName', title: '用户真实姓名', minWidth:100, align:"center"}
		 	 ,{field: 'personalStatus', title: '认证状态', minWidth:100, align:"center",templet:function(d){
		 		 if(d.personalStatus=="1"){ 
		 			 return '<span class="layui-green layui-bg-green">已认证</span>';
		 		 }}
		 	 }
		 	 ,{field: 'identityImgFront', title: '身份证正面', minWidth:100, align:"center",templet:function(d){
		 		 return '<span onclick="javascript:maxImg(\''+d.identityImgFront+'\')"><img id="'+d.identityImgFront+'"  src="'+d.identityImgFront+'" /></span>';
		 		 }}
		 	 ,{field: 'identityImgReverse', title: '身份证反面', minWidth:100, align:"center",templet:function(d){
		 		 return '<span onclick="javascript:maxImg(\''+d.identityImgReverse+'\')"><img id="'+d.identityImgReverse+'"  src="'+d.identityImgReverse+'" /></span>';
		 		 }}
//		 	,{fixed: 'right', title:'操作', minWidth:300, align:'center', toolbar: '#barDemo'}
        ]] 
    });
//    table.on('tool(dicList)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
//        var data = obj.data; //获得当前行数据
//        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
//        var tr = obj.tr; //获得当前行 tr 的DOM对象
//        if(layEvent === 'register'){ 
//        	layer.confirm('确定为该用户注册飞马通？', {icon: 3, title: '提示信息'}, function (index) {
//      		  layer.close(index);
//                $.get("/tbPersonalInfo/registerFMT",{
//              	  id : data.id
//                },function(data){
//                    tableIns.reload();
//                    if (data.data){
//                        layer.msg("成功！");
//                    } else {
////                  	  console.log(data);
//                        layer.msg(data.msg);
//                    }
//                })
//            })
//        }
//      }) 
 
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	nickname: $("#nickname").val(),
            	accont: $("#accont").val(),
            	realName: $("#realName").val()
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
                    	  body.find("#id").val(edit.id); 
                    	   body.find(".userId").val(edit.userId); 
                    	   body.find(".bankCard").val(edit.bankCard); 
                    	   body.find(".idcard").val(edit.idcard); 
                    	   body.find(".phone").val(edit.phone); 
                    	   body.find(".realName").val(edit.realName); 
                    	   body.find(".personalStatus").val(edit.personalStatus); 
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
                $.get("/tbPersonalInfo/delBatch",{
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
	var img_infor = "<img src='" + url + "' style='height:98%;with:98%;margin:5px;' />";
	var imges = document.getElementById(url);
	var  naturalWidth = imges.naturalWidth;
	var  naturalHeight = imges.naturalHeight;
	if(naturalWidth<930&&naturalHeight>=700){
		 img_infor = "<img src='" + url + "' style='height:98%;with:"+naturalWidth+";margin:5px;' />";
	}
	layer.open({    
        type: 1, 
        closeBtn: 1,
        title: false, //不显示标题
		shadeClose: true, //点击遮罩区域是否关闭页面
		shade: 0.8,  //遮罩透明度
        area: [naturalWidth, '700px'],   
        content: img_infor //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响    
       
    	}); 
}
