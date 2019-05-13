/*
*  @author dp
*  @since 2018-11-14
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
 
    
    var dicList;
    $.post("/dic/selectListData",{
    	groupName : '图文管理'
    },function(data){
        dicList = data.data;
        dicList.forEach(function(e){ 
            $("#dicSelect").append("<option value='"+e.id+"'>"+e.value1+"</option>");
        });
        $("#dicSelect").val($("#dicId").val());//默认选中
        form.render('select');//刷新select选择框渲染
    });
    
    
    
    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/article/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
		 	 ,{field: 'title', title: '标题', minWidth:100, align:"center"}
		 	,{field: 'imgPath', title: '图片路径', minWidth:100, align:"center",templet:function(d){
		 		if(d.imgPath){
		 			return '<span onclick="javascript:maxImg(\''+d.imgPath+'\')"><img id="'+d.imgPath+'"  src="'+d.imgPath+'" /></span>';
		 		}else{
		 			return "";
		 		}
		 	}}
		 	 ,{field: 'dicId', title: '类型', minWidth:100, align:"center",
		 		templet:function(d){
	                var name = "";
	                dicList.forEach(function(e){
	                    if (e.id === d.dicId){
	                        name = e.name; 
	                    }
	                });
	                return name;
	            }}
		 	,{field: 'content', title: '文本内容', minWidth:100, align:"center"}
		 	 ,{field: 'createTime', title: '创建时间', minWidth:100, align:"center"}
		 	 ,{field: 'startTime', title: '展示时间', minWidth:100, align:"center"}
		 	 ,{field: 'endTime', title: '结束时间', minWidth:100, align:"center"}
		 	 ,{field: 'clickNum', title: '点击量', minWidth:100, align:"center"}
		 	 ,{field: 'state', title: '当前状态', minWidth:100, align:"center",
		 		templet:function(d){
		 			 if (d.state === 1) {
		                    return '<span class="layui-badge layui-bg-green">启用</span>';
		                } else if (d.state === 0) {
		                    return '<span class="layui-badge layui-bg-cyan">禁用</span>';
		                } else {
		                    return '<span class="layui-badge layui-bg-orange">未知</span>';
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
                title: $("#title").val()
            } 
        })
    });
 
    //添加角色
    function addDic(edit){
        var title = edit===null?"添加 文章信息管理":"编辑 文章信息管理";
        layui.layer.open({ 
            title : title,
            type : 2,  
//            area : ["100%","100%"],
            area :["400px","470px"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    	    body.find("#id").val(edit.id); 
                    	   body.find(".title").val(edit.title);
                    	   body.find(".imgPath").val(edit.imgPath);
                    	   body.find("#demo1").attr("src",edit.imgPath);
                    	   body.find(".content").text(edit.content);
                    	   body.find("#dicId").val(edit.dicId);  
                    	   body.find(".createTime").val(edit.createTime); 
                    	   body.find(".startTime").val(edit.startTime); 
                    	   body.find(".endTime").val(edit.endTime); 
                    	   body.find(".clickNum").val(edit.clickNum); 
                    	   body.find(".remark").val(edit.remark); 
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
                $.get("/article/delBatch",{
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
