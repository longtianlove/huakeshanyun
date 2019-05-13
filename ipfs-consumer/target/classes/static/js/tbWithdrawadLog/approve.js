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
	
	$("#id").val($_GET['id']);
	// 监听
	$(document).ready(function() {       
	    // select下拉框选中触发事件
		var $_lab="<label class='layui-form-label'>不通过原因</label>";
		var $_remark="<div class='layui-input-block'><textarea name='verifyInfo' id='verifyInfo' rows='5' cols='20'  maxlength='20'  placeholder='最多可输入20汉字'  class='layui-textarea'  lay-verify='required'></textarea></div>";
	    form.on("select", function(data){
	    	$("#temp").children().remove();
	  		if(data.value==2){
	  			$("#temp").append($_lab);
	  			$("#temp").append($_remark);
	  		}
	    });
	 
	});
		

	
	//提交数据
	 form.on("submit(approve)", function(data) {
//		 console.log(data);	 
		 
		//弹出loading
		var index = top.layer.msg('数据保存中，请稍候...', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		  $.post("/tbWithdrawadLog/aproveEnd", data.field, function(res) {  
				if (res.data) {
					layer.close(index);
					layer.msg("审批成功！");
					var i = parent.layer.getFrameIndex(window.name);  
				    parent.layer.close(i);//关闭当前页  
					//刷新父页面
				    parent.location.reload();
				} else {
					layer.msg(res.msg);
				}
			})
		return false;
	})

});