/*
*  @author dp
*  @since 2019-03-12
*/
layui.use(['form','layer','table','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    var laydate = layui.laydate;
    
//    layui.use('laydate', function(){
//    	  var laydate = layui.laydate;
//	  laydate.render({
//	  	    elem: '#endTime', //指定元素
//	  	   type: 'datetime'
//	  	  });
//	  	  laydate.render({
//	  		  elem: '#startTime', //指定元素
//	  		  type: 'datetime'
//	  	  });
//    	});
	    //日期时间
	    var start = laydate.render({
	        elem:'#startTime',
	        type:'datetime',
	        calendar: true,
            done: function(value, date, endDate){
            	end.config.min = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date,
                        hours: date.hours,
                        minutes: date.minutes,
                        seconds: date.seconds
                    }
            	}

    });
	    var end = laydate.render({
		  elem:'#endTime',
		  type:'datetime',
		  calendar: true,
	      done: function(value, date){
	    	  start.config.max = {
	    	            year: date.year,
	    	            month: date.month - 1,
	    	            date: date.date,
	    	            hours: date.hours,
	    	            minutes: date.minutes,
	    	            seconds: date.seconds
	    	        }
	      }
	    });

    
    var dicList;
	    $.post("/dic/selectListData",{
	    	groupName : '账变类型'
	    },function(data){
	        dicList = data.data;
	        $("#type").append("<option value=''>请选择账变类型</option>"); 
	        dicList.forEach(function(e){ 
	            $("#dicSelect").append("<option value='"+e.id+"'>"+e.name+"</option>");
	            $("#type").append("<option value='"+e.id+"'>"+e.name+"</option>");
	        });
	        $("#dicSelect").val($("#type").val());//默认选中 
	        form.render('select');//刷新select选择框渲染
	    });
    
    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbAssetsDetail/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
		 	 ,{field: 'userId', title: '用户编号', minWidth:100, align:"center"}
		 	 ,{field: 'nickname', title: '用户昵称', minWidth:100, align:"center"}
		 	 ,{field: 'phone', title: '平台账号', minWidth:100, align:"center"}
		 	 ,{field: 'realName', title: '真实姓名', minWidth:100, align:"center"}
		 	 ,{field: 'beforeAmount', title: '账变前金额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.beforeAmount){ 
		 			 return formatNumber(d.beforeAmount);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'amount', title: '账变金额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.amount){ 
		 			 return formatNumber(d.amount);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'afterAmount', title: '账变后金额', minWidth:100, align:"center",templet:function(d){
		 		 if(d.afterAmount){ 
		 			 return formatNumber(d.afterAmount);
		 		 }else{
		 			return 0; 
		 		 }
		 	 }}
		 	 ,{field: 'type', title: '账变类型', minWidth:100, align:"center",
		 		templet:function(d){
	                var name = "";
	                dicList.forEach(function(e){
	                    if (e.id === d.type){
	                        name = e.name; 
	                    }
	                });
//	                return name;
	                return'<span class="layui-green layui-bg-green">'+name+'</span>';
	            }}
		 	 ,{field: 'createTime', title: '创建时间', minWidth:100, align:"center"}
		 	 ,{field: 'accountType', title: '账务类型', minWidth:100, align:"center",templet:function(d){
		 		 if(d.accountType=="1"){ 
		 			 return '<span class="layui-orange layui-bg-orange">金币</span>';
		 		 }else if(d.accountType=="2"){
			 			 return '<span class="layui-red layui-bg-red">推广礼包</span>';
		 		 }else if(d.accountType=="3"){
			 			 return '<span class="layui-blue layui-bg-blue">超级矿工</span>';
		 		 }else {
		 			return '';
		 		 }
		 		 }
		 	 }
		 	 ,{field: 'inOrOut', title: '收支类型', minWidth:100, align:"center",templet:function(d){
		 		 if(d.inOrOut=="1"){ 
		 			 return '<span class="layui-green layui-bg-green">收入</span>';
		 		 }else{
			 			 return '<span class="layui-red layui-bg-red">支出 </span>';
		 		 }}
		 	 }
		 	 ,{field: 'sunname', title: '返利用户昵称', minWidth:100, align:"center"}
        ]] 
    });
 
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从 第 1 页开始
            },
            where: {
            	phone: $("#phone").val(),
            	nickname: $("#nickname").val(),
            	startTime: $("#startTime").val(),
            	endTime: $("#endTime").val(),
            	type: $("#type").val(),
            	accountType: $("#accountType").val()
            	
            } 
        })
    });

    $("#export").click(function(){
     	var title = "选择日期";
		layer.open({
			title : title,
			type : 2,
			area : [ "550px", "400px"],
			content : "tbAssetsDetail/chooseTime.html",
			success : function(layero, index) {
				layer.setTop(layero);
				  form.render();
				  laydate.render();
			},
			 end:function(){
				 tableIns.reload();
			 }
		})
    });
    
})

