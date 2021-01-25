layui.use(['layer', 'table','jquery','form'], function(){
    var layer = layui.layer //弹层
        ,table = layui.table //表格
        ,$=layui.jquery
        ,form=layui.form
    //执行一个 table 实例
    table.render({
        elem: '#demo'
        ,id:'tableReload'
        ,height: 420
        ,url: '/emp/lisEmp' //数据接口
        ,title: '员工表'
        ,page: false //开启分页
        ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'empId', title: '员工编号', width:80, sort: true}
            ,{field: 'empName', title: '员工姓名', width:80}
            ,{field: 'age', title: '员工年龄', width: 90}
            ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
        ]],/*parseData:function(rs){//数据格式解析
            console.log("rs===="+rs.toString()+"---"+rs.data.total+"---"+rs.data.list+"---"+rs.msg)
            return { // 里面的每一个值必须与传递的json 数据对应
                "code":rs.code,	//返回状态码200
                "msg":rs.msg,	// 消息
                "count":rs.data.total,	//总条目
                "data":rs.data.list	//具体内容
            }
        }*/
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                layer.msg('添加');
                myForm()
                break;
            case 'update':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else if(data.length > 1){
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        };
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
        } else if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
                //obj.del(); //删除对应行（tr）的DOM结构
                delById(data.empId)
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){
            layer.msg('编辑操作');
            edit(data)
        }
    });
     function delById(empId) {
            $.ajax({
                type: 'post'
                ,url: '/emp/delEmp'
                ,data: {
                    "empId":empId
                }
                ,dataType:'json'
                ,success:function(msg){
                table.reload('tableReload',{})
                }
          })
     }
    function myForm() {
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['620px', '640px'], //宽高
            content: $("#myForm").show()
            ,btn: ['确定', '取消']
            ,yes: function(index, layero){
                $("#submitBtn").click();
                layer.closeAll()
            }
            ,btn2: function(index, layero){
                layer.closeAll()
                //按钮【按钮二】的回调
            }, end:function(){
                $("#myForm").hide();
                $("#resetBtn").click();
                layer.closeAll();
            }
        });
    }
    //监听提交
    form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        $.ajax({
                type: 'post'
                ,url: '/emp/saveEmp'
                ,data: {
                "empId":data.field.empId,
                "empName":data.field.empName,
                "age":data.field.age,
                }
                ,dataType:'json'
                ,success:function(msg){
                    table.reload('tableReload',{})
                }
        })
        return false;
    });

       function edit(data) {
            myForm()
           $("input[name='empId']").val(data.empId)
           $("input[name='empName']").val(data.empName)
           $("input[name='age']").val(data.age)
           form.render()
       }
});