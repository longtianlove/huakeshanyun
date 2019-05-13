layui.use(['form','layer','table'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#dicList',
        url : '/dic/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "dicListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'groupName', title: '分组名称', minWidth:100, align:"center"},
            {field: 'name', title: '字典名称', minWidth:100, align:"center"},
            {field: 'value1', title: '字典值1', minWidth:200, align:'center'},
            {field: 'value2', title: '字典值2', minWidth:200, align:'center'},
            {field: 'createTime', title: '修改时间', align:'center',minWidth:150},
            {field: 'state', title: '当前状态', align:'center',minWidth:150,
            	templet:function(d){
                    if (d.state === 1) {
                        return '<span class="layui-badge layui-bg-green">启用</span>';
                    } 
                    if (d.state === 0) {
                        return '<span class="layui-badge layui-bg-cyan">禁用</span>';
                    } 
             }
            },
            {field: 'sort', title: '排序', align:'center',minWidth:150}
        ]]
    });
 
    //搜索
    $(".search_btn").on("click",function(){
        table.reload("dicListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                name: $("#name").val(),
                groupName: $("#groupName").val()
            } 
        })
    });
 
    //添加角色
    function addDic(edit){
        var title = edit===null?"添加字典表":"编辑字典表";
        layui.layer.open({
            title : title,
            type : 2,
            area : ["400px","470px"],
            content : "info.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".groupName").val(edit.groupName);
                    body.find(".name").val(edit.name);
                    body.find(".value1").val(edit.value1);
                    body.find(".value2").val(edit.value2);
                    body.find(".sort").val(edit.sort);
                    body.find(".state").val(edit.state); 
                    body.find("#id").val(edit.id);
                    form.render(); 
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回字典列表', '.layui-layer-setwin .layui-layer-close', {
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
            layer.msg("请选择需要修改的字典"); 
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
            layer.confirm('确定删除选中的字典？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/dic/delBatch",{
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
            layer.msg("请选择需要删除的字典");
        }
    })

})
