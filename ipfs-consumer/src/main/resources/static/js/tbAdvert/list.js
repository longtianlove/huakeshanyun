/*
*  @author dp
*  @since 2018-10-29
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbAdvert/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
		 	 ,{field: 'name', title: '广告名称', minWidth:100, align:"center"}
		 	 ,{field: 'linkAddress', title: '广告地址', minWidth:100, align:"center"}
		 	 ,{field: 'imagePath', title: '图片路径', minWidth:100, align:"center",templet:function(d){
		 		 return '<span onclick="javascript:maxImg(\''+d.imagePath+'\')"><img id="'+d.imagePath+'"  src="'+d.imagePath+'" /></span>';
		 		 }}
		 	 ,{field: 'sort', title: '排序', minWidth:100, align:"center"}
		 	 ,{field: 'createTime', title: '创建时间', minWidth:100, align:"center"}
		 	 ,{field: 'endTime', title: '结束时间', minWidth:100, align:"center"}
		 	 ,{field: 'state', title: '当前状态', minWidth:100, align:"center",templet:function(d){
		 		 if(d.state===1){
		 			 return '<span class="layui-badge layui-bg-green">启用</span>';
		 		 }else{
		 			return '<span class="layui-badge layui-bg-cyan">禁用</span>';
		 		 }
		 	 }}
        ]] 
    });
 
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                name: $("#name").val()
            } 
        })
    });
 
    //添加角色
    function addDic(edit){
        var title = edit===null?"添加 广告信息管理表":"编辑 广告信息管理表";
        layui.layer.open({ 
            title : title,
            type : 2,  
            area : ["60%","60%"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    	  body.find("#id").val(edit.id); 
                    	   body.find(".name").val(edit.name); 
                    	   body.find(".linkAddress").val(edit.linkAddress); 
                    	   body.find(".imagePath").val(edit.imagePath1);
                    	   body.find("#demo1").attr("src",edit.imagePath);
                    	   body.find(".sort").val(edit.sort); 
                    	   body.find(".createTime").val(edit.createTime); 
                    	   body.find(".endTime").val(edit.endTime); 
                    	   body.find(".state").val(edit.state); 
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
                $.get("/tbAdvert/delBatch",{
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
