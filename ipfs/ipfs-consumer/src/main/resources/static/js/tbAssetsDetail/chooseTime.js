/*
*  @author dp
*  @since 2019-03-12
*/
layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
 
    layui.use('laydate', function(){
  	  var laydate = layui.laydate;
  	  
  	  laydate.render({
  	    elem: '#endTime' //指定元素
  	  });
  	  laydate.render({
  		  elem: '#startTime' //指定元素
  	  });
  	});
    form.on("submit(export)",function(data){   
    	var end=$("#endTime").val();
    	var start=$("#startTime").val(); 
    	window.open("/tbAssetsDetail/export?endTime="+end+"&startTime="+start);
    //	window.location.href="/tbAssetsDetail/export?endTime="+end+"&startTime="+start; 
//    	window.opener=null;
//		window.open('','_self');
//		window.close();
 /*   	  $.post("/tbAssetsDetail/export",data.field,function(res){
    		  layer.close(index);
    		  layer.msg("添加成功！");
    		  layer.closeAll("iframe");
    		   if (res.data){
                   //刷新父页面
                   parent.location.reload();
               } else {
                   layer.msg(data.msg);
               }
          }) */
    })
//    $("#export").on("click",function(){
//    	var end=$("#endTime").val();
//    	var start=$("#startTime").val();
//    	window.location.href="/tbAssetsDetail/export?endTime="+end+"&startTime="+start; 
//    	window.opener=null;
//		window.open('','_self');
//		window.close();
//    })
})