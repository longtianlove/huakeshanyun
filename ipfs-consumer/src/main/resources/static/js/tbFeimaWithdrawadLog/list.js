/*
*  @author dp
*  @since 2019-05-05
*/
layui.use(['form','layer','table','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    var laydate = layui.laydate;
    // 
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


    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/tbFeimaWithdrawadLog/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[ 
            {type: "checkbox", fixed:"left", width:50}
			 ,{field: 'phone', title: '手机号码', minWidth:100, align:"center"}
			 ,{field: 'name', title: '姓名', minWidth:100, align:"center"}
		 	 ,{field: 'cashNumber', title: '提现金额', minWidth:100, align:"center"}
		 	 ,{field: 'actualAmount', title: '实际扣除', minWidth:100, align:"center"}
		 	 ,{field: 'createTime', title: '提现时间', minWidth:100, align:"center"}
		 	 ,{field: 'userName', title: '用户姓名', minWidth:100, align:"center"}
		 	 ,{field: 'handfree', title: '手续费', minWidth:100, align:"center"}
		 	 ,{field: 'beforeAmount', title: '提现前余额', minWidth:100, align:"center"}
        ]] 
    }); 
 
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                name: $(".name").val()
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
                    	   body.find(".cashNumber").val(edit.cashNumber); 
                    	   body.find(".cashStatus").val(edit.cashStatus); 
                    	   body.find(".actualAmount").val(edit.actualAmount); 
                    	   body.find(".createTime").val(edit.createTime); 
                    	   body.find(".userName").val(edit.userName); 
                    	   body.find(".handfree").val(edit.handfree);  
                    	   body.find(".beforeAmount").val(edit.beforeAmount); 
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
                $.get("/tbFeimaWithdrawadLog/delBatch",{
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
