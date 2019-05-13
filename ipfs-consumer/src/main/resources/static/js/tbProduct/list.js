/*
*  @author dp
*  @since 2019-03-09
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbProduct/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
		 	 ,{field: 'productName', title: '名称', minWidth:100, align:"center"}
		 	 ,{field: 'productType', title: '类型', minWidth:100, align:"center"}
		 	 ,{field: 'productSales', title: '销量', minWidth:100, align:"center"}
		 	 ,{field: 'productUnitPrice', title: '销售单价', minWidth:100, align:"center"}
		 	 ,{field: 'productPreferentialPrice', title: '特惠价格', minWidth:100, align:"center"}
		 	 ,{field: 'status', title: '是否发布', minWidth:100, align:"center",templet:function(d){
		 		 if(d.status=="1"){ 
		 			 return '<span class="layui-green layui-bg-green">发布</span>';
		 		 }else{
		 			 return '<span class="layui-red layui-bg-red">未发布</span>';
		 		 }}
		 		 }
		 	 ,{field: 'outNumber', title: '出局倍数', minWidth:100, align:"center"}
		 	 ,{field: 'hotSaleProduct', title: '热销状态', minWidth:100, align:"center",templet:function(d){
		 		 if(d.hotSaleProduct=="1"){ 
		 			 return '<span class="layui-red layui-bg-red">热销</span>';
		 		 }else{
		 			 return '<span class="layui-green layui-bg-green">普通</span>';
		 		 }}
		 	 }
		 	 ,{field: 'afterSaleService', title: '售后服务', minWidth:100, align:"center"}
		 	 ,{field: 'homeProductImg', title: '首页展示图片', minWidth:100, align:"center",templet:function(d){
		 		 return '<span onclick="javascript:maxImg(\''+d.homeProductImg+'\')"><img  id="'+d.homeProductImg+'"  src="'+d.homeProductImg+'" /></span>';
		 	 }}
		 	 ,{field: 'productPictures', title: '产品图片', minWidth:100, align:"center",templet:function(d){
		 		 return '<span onclick="javascript:maxImg(\''+d.productPictures+'\')"><img   id="'+d.productPictures+'"  src="'+d.productPictures+'" /></span>';
		 		 }}
		 	 ,{field: 'productDetail', title: '基本信息', minWidth:100, align:"center",templet:function(d){
		 		 return '<span onclick="javascript:maxImg(\''+d.productDetail+'\')"><img   id="'+d.productDetail+'"  src="'+d.productDetail+'" /></span>';
		 	 }}
		 	 ,{field: 'prodcutProperty', title: '详细配置', minWidth:100, align:"center",templet:function(d){
		 		 return '<span onclick="javascript:maxImg(\''+d.prodcutProperty+'\')"><img   id="'+d.prodcutProperty+'"  src="'+d.prodcutProperty+'" /></span>';
		 	 }}
		 	 ,{field: 'productThumbnail', title: '产品缩略图', minWidth:100, align:"center",templet:function(d){
		 		 return '<span onclick="javascript:maxImg(\''+d.productThumbnail+'\')"><img  id="'+d.productThumbnail+'"  src="'+d.productThumbnail+'" /></span>';
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
            	productName: $(".name").val()
            } 
        })
    });
 
    //添加角色
    function addDic(edit){
        var title = edit===null?"添加 ":"编辑 ";
        layui.layer.open({ 
            title : title,
            type : 2,  
            area : ["80%","80%"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    	  body.find("#id").val(edit.id); 
                    	   body.find(".productName").val(edit.productName); 
                    	   body.find(".productType").val(edit.productType); 
                    	   body.find(".productSales").val(edit.productSales); 
                    	   body.find(".productUnitPrice").val(edit.productUnitPrice); 
                    	   body.find(".status").val(edit.status); 
                    	   body.find(".hotSaleProduct").val(edit.hotSaleProduct); 
                    	   body.find(".outNumber").val(edit.outNumber); 
                    	   body.find(".productPreferentialPrice").val(edit.productPreferentialPrice); 
                    	   body.find("#afterSaleService").val(edit.afterSaleService); 
                    	   body.find(".productPictures").val(edit.productPictures); 
                    	   body.find("#demo1").attr("src",edit.productPictures);
                    	   body.find(".productDetail").val(edit.productDetail); 
                    	   body.find("#demo2").attr("src",edit.productDetail);
                    	   body.find(".prodcutProperty").val(edit.prodcutProperty);
                    	   body.find("#demo3").attr("src",edit.prodcutProperty);
                    	   body.find(".homeProductImg").val(edit.homeProductImg); 
                    	   body.find("#demo4").attr("src",edit.homeProductImg);
                    	   body.find(".productThumbnail").val(edit.productThumbnail); 
                    	   body.find("#demo5").attr("src",edit.productThumbnail);
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
                $.get("/tbProduct/delBatch",{
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
