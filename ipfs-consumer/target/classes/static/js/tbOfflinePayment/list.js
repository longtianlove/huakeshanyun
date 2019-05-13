/*
*  @author dp
*  @since 2019-03-21
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbOfflinePayment/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "radio", fixed:"left", width:50}
//		 	 ,{field: 'id', title: '主键', minWidth:100, align:"center"}
		 	 ,{field: 'userId', title: '用户编号', minWidth:100, align:"center"}
		 	 ,{field: 'price', title: '充值金额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.price){ 
		 			 return formatNumber(d.price);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'createTime', title: '创建时间', minWidth:100, align:"center"}
		 	 ,{field: 'phone', title: '平台账户', minWidth:100, align:"center"}
		 	 ,{field: 'realName', title: '真实姓名', minWidth:100, align:"center"}
		 	 ,{field: 'status', title: '状态', minWidth:100, align:"center",
			 		templet:function(d){
			 			 if (d.status === 2) {
			                   return '<span class="layui-badge layui-bg-blue">拒绝</span>';
			                }else if(d.status === 0){
			                   return '<span class="layui-badge layui-bg-green">待审核</span>';
			               }else if(d.status ===3){
			            	 return '<span class="layui-badge layui-bg-cyan">待复核</span>';
			               }else if(d.status ===1){
			            	 return '<span class="layui-badge layui-bg-warm">已充值</span>';
			 		}
			 }}
		 	 ,{field: 'voucherPath', title: '打款凭证', minWidth:100, align:"center",templet:function(d){
		 		var  imgpath="";
		 		var str=d.voucherPath;
		 		 if(str.indexOf(",")<0){
		 			imgpath= '<span onclick="javascript:maxImg(\''+str+'\')"><img   src="'+str+'" /></span>'; 
		 		 }else{
		 			 
		 			var newstr=str.substring(0,str.indexOf(","))
		 			imgpath= '<span onclick="javascript:maxImg(\''+str+'\')"><img   src="'+newstr+'" /></span>';  
		 		 }
		 		 return imgpath;
		 		
		 	 }}
		 	 ,{field: 'remark', title: '备注', minWidth:100, align:"center"}
//		 	,{fixed: 'right', title:'操作', minWidth:200, align:'center', toolbar: '#barDemo'}
        ]] 
    });
    table.on('row(dicList)', function(obj){////注：test是table原始容器的属性 lay-filter="对应的值"
    	obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');//选中行样式
        obj.tr.find('input[lay-type="layTableRadio"]').prop("checked",true);
        var index = obj.tr.data('index')
        var thisData = table.cache.dicListTable;//tableName 表名 id
        //重置数据单选属性
        layui.each(thisData, function(i, item){
          if(index === i){
            item.LAY_CHECKED = true;
          } else {
            delete item.LAY_CHECKED;
          }
        });
        form.render('radio');
   });

      
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	phone: $("#phone").val(),
            	realName: $("#realName").val(),
            	status: $("#status").val()
            } 
        })
    });
 
    $(".fisrtApprove").click(function(){
    	var checkStatus = table.checkStatus('dicListTable'),
        data = checkStatus.data;
     if(data.length > 0){
    	 if(data[0].status==0){
    		 layui.layer.open({ 
    			 title : "初审",
    			 type : 2,  
    			 area : ["360px","470px"],
    			 content : "firstApprove.html?id="+data[0].id,
    		 });
    	 }else{
             layer.msg("当前记录已审核！"); 
         } 
     }else{
         layer.msg("请选择需要修改的数据"); 
     }
    });
    $(".approved").click(function(){
    	var checkStatus = table.checkStatus('dicListTable'),
    	data = checkStatus.data;
    	if(data.length > 0){
    		if(data[0].status==0){
    			layer.msg("当前记录未初审，不能审核！"); 
    		}
    		if(data[0].status==3){
    			layui.layer.open({ 
    				title : "复核",
    				type : 2,  
    				area : ["360px","470px"],
    				content : "approve.html?id="+data[0].id,
    			});
    		}else if(data[0].status==1){
    			layer.msg("当前记录不用复核！"); 
    		} 
    	}else{
    		layer.msg("请选择需要修改的数据"); 
    	}
    });
    

})
function maxImg(imgPaths) {
 
	 
	var imageArr=imgPaths.split(",");
	
	var imgData={};
	var  arr=new Array();
 
	for(var i=0;i<imageArr.length;i++){
		var obj={};
 		obj.src=imageArr[i]; 
		arr.push(obj);
	}
	imgData.data=arr;
	  layer.photos({
		  	title:"<span style='color:red;font-size:22px;'>"+ imageArr.length+"张图</span>", //相册标题
		    photos:imgData,
		    anim: 5  
	 });

}
 	
		
		
		
		
		
 







