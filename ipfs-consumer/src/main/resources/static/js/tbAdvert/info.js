/*
*  @author dp
*  @since 2018-10-29
*/
layui.use(['form','layer','upload'],function(){
		var form = layui.form
        , layer = layui.layer
        , laydate = layui.laydate
        , $ = layui.jquery
        , upload = layui.upload;
		var uploadInst = upload.render({
			elem:'#upload',
			url:"/tbAdvert/upload",
			before:function(obj){
				//本地预览
				obj.preview(function(index, file, result){
					$('#demo1').attr('src',result);
				});
			},
			done:function(res){
				//如果上传失败
	            if(res.code !=1){
	                return layer.msg('上传失败');
	            }
	            //上传成功
	            layer.msg('上传成功！');
	            document.getElementById("imagePath").value = res.msg;
			},
	    });
	});
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  laydate.render({
	    elem: '#endTime' //指定元素
	  });
	  laydate.render({
		  elem: '#createTime' //指定元素
	  });
	});
layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
  ;
    form.on("submit(addTbAdvert)",function(data){   
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#id").val()==="") {
            $.post("/tbAdvert/add",data.field,function(res){
                if (res.data){
                    layer.close(index);
                    layer.msg("添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.msg(data.msg);
                }
            })
        } else {  
            $.post("/tbAdvert/edit",data.field,function(res){
                if (res.data){
                    layer.close(index);
                    layer.msg("修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                } else {
                    layer.msg(data.msg);
                }
            })
        }
        return false;
    });
	 form.on('select(myty)', function(data){
	    	if(data.value==1){
	    		getDataList();
	    	}
	   });

});


