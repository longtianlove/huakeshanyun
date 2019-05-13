layui.config({
    base : "/static/js/modules/"
}).extend({
    "treetable" : "treetable/treetable",
    "common" : "common"
});
layui.use(['form', 'table', 'treetable', 'common'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    var table = layui.table;
    var treetable = layui.treetable;
    var common = layui.common;

    // 渲染表格
    layer.load(2);
    treetable.render({
        treeColIndex: 1,
        treeSpid: 0,
        treeIdName: 'id',
        treePidName: 'parentId', 
        treeDefaultClose: true,
        elem: '#auth-table',
        url: '/tbInvite/listData',
        page: false,
        id: 'tbInviteListTable',
        cols: [[
        	  {type: "checkbox", fixed:"left", width:50},
              {field: 'tbIviter', minWidth: 100, title: '邀请人编号'},
              {field: 'iviterNickname', width: 200, title: '邀请人昵称'},
              {field: 'iviteeNickname', width: 200, title: '被邀请人昵称'},
              {field: 'phone2', width: 200, title: '邀请人手机'},
              {field: 'phone', width: 200, title: '被邀请人手机'},
              {field: 'name', width: 200, title: '被邀请人等级'},
              {field: 'name2', width: 200, title: '邀请人等级'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    $(".expandAll").on("click",function(){
	 treetable.expandAll('#auth-table');  
   });
    $(".foldAll").on("click",function(){
   	 treetable.foldAll('#auth-table');  
      });

});