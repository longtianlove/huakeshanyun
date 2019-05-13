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
        url : '/tbUsdtUser/listData2',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
		 	// ,{field: 'usdtId', title: '主键', minWidth:100, align:"center"}
		  	,{field: 'nickname', title: '昵称', minWidth:100, align:"center"}
		 	,{field: 'phone', title: '电话', minWidth:100, align:"center"}
		 	// ,{field: 'userId', title: '用户主键', minWidth:100, align:"center"}
		 	,{field: 'usdtAddr', title: '地址', minWidth:100, align:"center"}
		 	,{field: 'address_imgpath', title: '二维码', minWidth:100, align:"center",templet:function(d){
		 		var   imgpath= '<span onclick="javascript:maxImg(\''+d.addressImgpath+'\')"><img   src="../'+d.addressImgpath+'" /></span>'; 
		 		 
		 		 return imgpath;
		 		
		 	 }}
		 	
        ]]  
    });
 
    //搜索
    $(".search_btn").on("click",function(){
    	var TbUsdtVO= new Object();
    	TbUsdtVO.phone= $(".name").val();
    	
    	console.info(TbUsdtVO);
        
    	table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	queryStr:JSON.stringify(TbUsdtVO)
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
                    	   body.find(".usdtId").val(edit.usdtId); 
                    	   body.find(".userId").val(edit.userId); 
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
                $.get("/tbUsdtUser/delBatch",{
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
function maxImg(imgPaths) {
 
	 
	var imageArr=imgPaths.split(",");
	
	var imgData={};
	var  arr=new Array();
 
	for(var i=0;i<imageArr.length;i++){
		var obj={};
 		obj.src="../"+imageArr[i]; 
		arr.push(obj);
	}
	imgData.data=arr;
	  layer.photos({
		  	//title:"<span style='color:red;font-size:12px;'>"+ imageArr.length+"张图</span>", //相册标题
		    photos:imgData,
		    anim: 5  
	 });

}
