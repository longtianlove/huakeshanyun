/*
*  @author dp
*  @since 2019-03-09
*/
layui.use(['form','layer','layedit','upload'],function(){
		var form = layui.form
        , layer = layui.layer
        , laydate = layui.laydate
        , $ = layui.jquery
        , upload = layui.upload;
		
		var uploadInst = upload.render({
			elem:'#upload1',
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
	            document.getElementById("productPictures").value = res.msg;
			},
	    });
		var uploadInst = upload.render({
			elem:'#upload2',
			url:"/tbAdvert/upload",
			before:function(obj){
				//本地预览
				obj.preview(function(index, file, result){
					$('#demo2').attr('src',result);
				});
			},
			done:function(res){
				//如果上传失败
	            if(res.code !=1){
	                return layer.msg('上传失败');
	            }
	            //上传成功
	            layer.msg('上传成功！');
	            document.getElementById("productDetail").value = res.msg;
			},
	    });
		var uploadInst = upload.render({
			elem:'#upload3',
			url:"/tbAdvert/upload",
			before:function(obj){
				//本地预览
				obj.preview(function(index, file, result){
					$('#demo3').attr('src',result);
				});
			},
			done:function(res){
				//如果上传失败
	            if(res.code !=1){
	                return layer.msg('上传失败');
	            }
	            //上传成功
	            layer.msg('上传成功！');
	            document.getElementById("prodcutProperty").value = res.msg;
			},
	    });
		var uploadInst = upload.render({
			elem:'#upload4',
			url:"/tbAdvert/upload",
			before:function(obj){
				//本地预览
				obj.preview(function(index, file, result){
					$('#demo4').attr('src',result);
				});
			},
			done:function(res){
				//如果上传失败
				if(res.code !=1){
					return layer.msg('上传失败');
				}
				//上传成功
				layer.msg('上传成功！');
				document.getElementById("homeProductImg").value = res.msg;
			},
		});
		var uploadInst = upload.render({
			elem:'#upload5',
			url:"/tbAdvert/upload",
			before:function(obj){
				//本地预览
				obj.preview(function(index, file, result){
					$('#demo5').attr('src',result);
				});
			},
			done:function(res){
				//如果上传失败
				if(res.code !=1){
					return layer.msg('上传失败');
				}
				//上传成功
				layer.msg('上传成功！');
				document.getElementById("productThumbnail").value = res.msg;
			},
		});
	   
	});
layui.use(['form','layer','layedit'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
 
    var layedit = layui.layedit;
    var contentindex=layedit.build('afterSaleService'); //建立编辑器
	    form.verify({
	        content: function(value) {
	            return layedit.sync(contentindex);
	        }
	    });
    form.on("submit(addTbProduct)",function(data){   
        //弹出loading
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#id").val()==="") {
            $.post("/tbProduct/add",data.field,function(res){
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
            $.post("/tbProduct/edit",data.field,function(res){
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
    })

})