/*
 *  @author dp
 *  @since 2018-10-31
 */
layui.use([ 'form', 'layer' ], function() {
	var form = layui.form
	layer = parent.layer === undefined ? layui.layer : top.layer,
			$ = layui.jquery;

	var $_GET = (function(){
	    var url = window.document.location.href.toString();
	    var u = url.split("?");
	    if(typeof(u[1]) == "string"){
	        u = u[1].split("&");
	        var get = {};
	        for(var i in u){
	            var j = u[i].split("=");
	            get[j[0]] = j[1];
	        }
	        return get;
	    } else {
	        return {};
	    }
	})();
	
	$("#userId").val($_GET['id']); 
	
	
	
	//查询商品信息
	var dicList;
	$.post("/tbProduct/selectData", null, function(data) {
		data.forEach(function(e) {
			$(".tbProductId").append(
					"<option value='" + e.id + "'  price='"+e.productPreferentialPrice+"'>" + e.productName + "</option>"); 
		});
		//$(".merchandiseId").val($("#merchandiseId").val());//默认选中 
		form.render('select');//刷新select选择框渲染
	});

	$(".state").val($("#state").val());//默认选中 
	
	//提交数据
	 form.on("submit(addAppUser)", function(data) {
		//弹出loading
		var index = top.layer.msg('数据保存中，请稍候...', {
			icon : 16,
			time : false,
			shade : 0.8
		});
	 
		  $.post("/appUserinfo/personBuy", data.field, function(res) {  
				if (res.data) {
				
					layer.close(index);
					layer.msg("购买成功！");
					layer.closeAll("iframe"); 
					//刷新父页面
				} else {
					layer.msg(res.msg);
				}
			})
		 
		return false;
	})

});