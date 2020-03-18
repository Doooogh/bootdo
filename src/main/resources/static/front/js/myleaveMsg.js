var pageNo=1;
var vm = new Vue({
    el: '#app',

    data:{
        dirId:'',  //相册id
        varList: [],	//list
        photoList:[], //图片集合
        dirList:[],  //相册集合
    },

    methods: {
        //初始执行
        init() {
            this.getDir();
            setTimeout(function(){
                vm.getPhoto(pageNo);
            },200);

        },
        //获取列表
        getPhoto: function(pageNo1){
            if(undefined==pageNo1||""==pageNo1||null==pageNo1){
                pageNo1=1;
            }
            $.ajax({
                xhrFields: {
                    withCredentials: true
                },
                type: "GET",
                url: '/front/photo/getPhoto',
                data: {
                    pageNo:pageNo1,
                    dirId:this.dirId,
                },
                dataType:"json",
                success: function(data){
                    if("0" == data.rc){
                        vm.photoList = data.photoList;
                        pageNo=data.pageNo;
                        pageUtils(data.total,pageNo);
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
        //获取相册列表
        getDir: function(){
            $.ajax({
                xhrFields: {
                    withCredentials: true
                },
                type: "GET",
                url: '/front/photo/getDir',
                dataType:"json",
                success: function(data){
                    if("0" == data.rc){
                        vm.dirList = data.dirList;
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
        reGetPhoto:function(dir){
            vm.dirId=dir;
            pageNo=1;
            vm.getPhoto(pageNo);
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
                    pageNo=obj.curr;
                    vm.getPhoto(pageNo);
                }
            }
        });
    });
}