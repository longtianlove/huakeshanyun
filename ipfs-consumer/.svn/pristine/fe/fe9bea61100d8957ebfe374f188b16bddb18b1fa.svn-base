/*
*  @author dp
*  @since 2018-11-14
*/
layui.use(['form','layer','layedit','laydate'],function(){
	    var layedit = layui.layedit;
	    var up_url="/tbAdvert/upload";//上传图片url

        layedit.set({ 
            uploadImage: {  
                url:up_url //接口url
                ,type: 'post' //默认post 
            }
        });
        layedit.set({
            //暴露layupload参数设置接口 --详细查看layupload参数说明
            uploadImage: {
                url: '/tbAdvert/upload',
                accept: 'image',
                acceptMime: 'image/*',
                exts: 'jpg|png|gif|bmp|jpeg',
                size: '10240'
            }
            , uploadVideo: {
                url: '/tbAdvert/upload',
                accept: 'video',
                acceptMime: 'video/*',
                exts: 'mp4|flv|avi|rm|rmvb',
                size: '20480'
            }
            //右键删除图片/视频时的回调参数，post到后台删除服务器文件等操作，
            //传递参数：
            //图片： imgpath --图片路径
            //视频： filepath --视频路径 imgpath --封面路径
            , calldel: {
                url: '/tbAdvert/deleteFile'
            }
            //开发者模式 --默认为false
            , devmode: true
            //插入代码设置
            , codeConfig: {
                hide: true,  //是否显示编码语言选择框
                default: 'javascript' //hide为true时的默认语言格式
            }
            , tool: [
                'html', 'code', 'strong', 'italic', 'underline', 'del', 'addhr', '|', 'fontFomatt', 'colorpicker', 'face'
                , '|', 'left', 'center', 'right', '|', 'link', 'unlink', 'image_alt', 'video', 'anchors'
                , '|', 'fullScreen'
            ]
            , height:780
        });
	    var contentindex=layedit.build('content'); //建立编辑器
        
	    var laydate = layui.laydate;
	    
	    //执行一个laydate实例
	    laydate.render({
	      elem: '.startTime',
	      type: 'datetime'
	     // range: true //指定元素
	    });
	    laydate.render({
		      elem: '.endTime',
		      type: 'datetime'
		});
	
	    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
	    
	    $.post("/dic/selectListData",{
	    	groupName :'图文管理'
	    },function(data){
	        var articleList = data.data;
	        articleList.forEach(function(e){
	            $("#dicSelect").append("<option value='"+e.id+"'>"+e.name+"</option>");
	        });
	        $("#dicSelect").val($("#dicId").val());//默认选中
	        form.render('select');//刷新select选择框渲染
	    });
	    
 
    form.on("submit(addArticle)",function(data){   
        //弹出loading
    	var content=layedit.getContent(contentindex); 
    	data.field["content"]=content;  
        var index = top.layer.msg('数据保存中，请稍候...',{icon: 16,time:false,shade:0.8});
        if ($("#id").val()==="") {
            $.post("/article/add",data.field,function(res){
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
            $.post("/article/edit",data.field,function(res){
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