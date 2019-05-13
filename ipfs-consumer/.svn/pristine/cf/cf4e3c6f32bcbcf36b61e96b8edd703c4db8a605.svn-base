/*
*  @author dp
*  @since 2018-10-25
*/
layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/taskSchedule/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50}
		 	 ,{field: 'jobName', title: '任务名称', minWidth:100, align:"center"}
		 	 ,{field: 'jobGroup', title: '任务分组', minWidth:100, align:"center"}
		 	 ,{field: 'aliasName', title: '任务别名', minWidth:100, align:"center"}
		 	 ,{field: 'jobClass', title: '任务路径', minWidth:100, align:"center"}
		 	 ,{field: 'status', title: '运行状态',  align:'center',templet:function(d){
	                if (d.status === 1) {
	                    return '<span class="layui-badge layui-bg-green">启用</span>';
	                } else {
	                    return '<span class="layui-badge layui-bg-cyan">禁用</span>';
	                }
	            }}
		 	 ,{field: 'cronexpression', title: '规则说明', minWidth:100, align:"center"}
		 	 ,{field: 'description', title: '描述', minWidth:100, align:"center"}
		 	 ,{field: 'createTime', title: '创建时间', minWidth:100, align:"center"}
		 	 ,{fixed: 'right', title:'操作', minWidth:200, align:'center', toolbar: '#barDemo'}
        ]] 
    });
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	jobName: $("#jobName").val()
            } 
        })
    });
    
  //监听工具条
    table.on('tool(dicList)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
      var tr = obj.tr; //获得当前行 tr 的DOM对象
     
      if(layEvent === 'run'){ //查看
    	  layer.confirm('确定运行选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
              $.get("/taskSchedule/resumeJob",{
             	 id : data.id
              },function(data){
                  layer.close(index);
                  tableIns.reload();
                  if (data.data){
//                      layer.msg("删除成功！");
                  } else {
//                      layer.msg(data.msg);
                  }
              })
          })
      } else if(layEvent === 'runOnce'){ //删除
    	  layer.confirm('确定运行选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
    		  layer.close(index);
              $.get("/taskSchedule/runOnceJob",{
             	 id : data.id
              },function(data){
                  tableIns.reload();
                  if (data.data){
//                      layer.msg("删除成功！");
                  } else {
//                      layer.msg(data.msg);
                  }
              })
          })
      } else if(layEvent === 'stop'){ //编辑
    	  layer.confirm('确定运行选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
              $.get("/taskSchedule/stopJob",{
             	 id : data.id
              },function(data){
                  layer.close(index);
                  tableIns.reload();
                  if (data.data){
//                      layer.msg("删除成功！");
                  } else {
//                      layer.msg(data.msg);
                  }
              })
          })       
      }
    });
    //添加角色
    function addDic(edit){
        var title = edit===null?"添加任务调度":"编辑任务调度"; 
        layui.layer.open({
            title : title,
            type : 2,
            area : ["550px","670px"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                       body.find("#id").val(edit.id);  
                       body.find(".jobName").val(edit.jobName); 
                       body.find(".jobGroup").val(edit.jobGroup); 
                       body.find(".aliasName").val(edit.aliasName); 
                       body.find(".jobClass").val(edit.jobClass); 
                       body.find(".status").val(edit.status); 
                       body.find(".cronexpression").val(edit.cronexpression); 
                       body.find(".description").val(edit.description); 
                       body.find(".createTime").val(edit.createTime); 
                       body.find(".updateTime").val(edit.updateTime); 
                       body.find(".cronexpressionExplain").val(edit.cronexpressionExplain); 
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
                $.get("/taskSchedule/delBatch",{
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
