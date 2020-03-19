var pageNo=1;
var vm = new Vue({
    el: '#app',

    data:{
        dirId:'',  //相册id
        varList: [],	//list
        leaveMsgList:[], //留言集合
        pageNo:1,
        total:0,
        content:'', //内容
    },

    methods: {
        //初始执行
        init() {
            setTimeout(function(){
                vm.getList(vm.pageNo);
            },200);

        },
        //获取列表
        getList: function(pageNo1){
            if(undefined==pageNo1||""==pageNo1||null==pageNo1){
                pageNo1=1;
            }
            $.ajax({
                xhrFields: {
                    withCredentials: true
                },
                type: "GET",
                url: '/front/leavemsg/getList',
                data: {
                    pageNo:pageNo1,
                },
                dataType:"json",
                success: function(data){
                    if("0" == data.rc){
                        vm.leaveMsgList = data.leaveMsgList;
                        vm.pageNo=data.pageNo;
                        vm.total=data.total;
                        pageUtils(vm.total,vm.pageNo);
                    }else  {
                        layer.msg("系统错误,请联系管理员!");
                    }
                }
            }).done().fail(function(){
                swal("登录失效!", "请求服务器无响应，稍后再试", "warning");
                setTimeout(function () {
                    window.location.href = "../login.html";
                }, 2000);
            });
        },

        save:function(){
            $.ajax({
                xhrFields: {
                    withCredentials: true
                },
                type: "POST",
                url: '/front/leavemsg/save',
                dataType:"json",
                data:{
                  content:vm.content,
                },
                success: function(data){
                    if("0" == data.rc){
                        layer.msg("提交成功!");
                        $('.text').val("");
                        vm.pageNo=1;  //重置页数
                        vm.getList(vm.pageNo);
                    }else if("1"==data.rc){
                        layer.msg("提交失败!，内容中包含非法词语");
                    }else  {
                        layer.msg("系统错误,请联系管理员!");
                    }
                }
            })
        },
        del:function(id){
            $.ajax({
                xhrFields: {
                    withCredentials: true
                },
                type: "POST",
                url: '/front/leavemsg/del',
                dataType:"json",
                data:{
                    id:id
                },
                success: function(data){
                    if("0" == data.rc){
                        layer.msg("删除成功!");
                        vm.pageNo=1;  //重置页数
                        vm.getList(vm.pageNo);
                    }else  {
                        layer.msg("系统错误,请联系管理员!");
                    }
                }
            })
        },
        download:function(url){
            window.location.href='/front/photo/download?url='+url;
        },
        //下载
        downloadAtt:function(id,url){

            window.location.href=httpurl+"article/download?ID="+id+"&URL="+url;
        },
        //下载
        downloadAll:function(ids){
            window.location.href=httpurl+"complaint/downloadFiles?ATTACHMENT="+ids;
        },

        //根据url参数名称获取参数值
        getUrlKey: function (name) {
            return decodeURIComponent(
                (new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null;
        }
    },

    mounted(){
        this.init();
    }
});
function pageUtils(total,curr) {
    layui.use(['laypage', 'layer'], function() {
        var laypage = layui.laypage
            , layer = layui.layer;
        //完整功能
        laypage.render({
            elem: 'page'
            ,count: total,
            curr:curr,
            limit:10
            ,layout: ['count', 'prev', 'page', 'next' , 'refresh', 'skip']
            ,jump: function(obj,first){
                if(!first){
                    vm.pageNo=obj.curr;
                    vm.getList(vm.pageNo);
                }
            }
        });
    });
}

$(function () {
    // 绑定表情
    $('.face-icon').SinaEmotion($('.text'));

});
// 测试本地解析
function out() {
    var inputText = $('.text').val();
    vm.content=AnalyticEmotion(inputText);
    vm.save();
}

var html;
function reply(content){
    html  = '<li>';
    html += '<div class="head-face">';
    html += '<img src="images/1.jpg" / >';
    html += '</div>';
    html += '<div class="reply-cont">';
    html += '<p class="username">小小红色飞机</p>';
    html += '<p class="comment-body">'+content+'</p>';
    html += '<p class="comment-footer">2016年10月5日　回复　点赞54　转发12</p>';
    html += '</div>';
    html += '</li>';
    return html;
}