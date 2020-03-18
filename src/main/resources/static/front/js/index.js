var pageNo=1;
var vm = new Vue({
	el: '#app',
	data:{
		varList: [],	//list
		newPhoto:[],  //最新相册
		newLeaveMsg:[],  //最新留言
		fromMyPage:[],//我访问最多
		toMyPage:[], //访问我最多
		classStu:[],  //同班同学
    },
    
	methods: {
        //初始执行
        init() {
            this.getList();
            setTimeout(function(){
                vm.getClaStu(pageNo);
            },200);
            setTimeout(function(){
    			$(".slideBox").slide({mainCell:".bd ul",autoPlay:true});
        		$(".slideTxtBox").slide({});
        		$(".picScroll-left").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",vis:5,trigger:"click"});
        		$(".ul_rmtj li").each(function(index){if(index > 2){ $(this).remove();};});
        		$(".ul_xwdt li").each(function(index){if(index > 7){ $(this).remove();};});
        		$(".ul_gzgl li").each(function(index){if(index > 7){ $(this).remove();};});
        		$(".ul_gsgg li").each(function(index){if(index > 7){ $(this).remove();};});
        		// $(".ul_dwgk li").each(function(index){if(index > 1){ $(this).remove();};});
        		$(".ul_bmfw li").each(function(index){if(index > 2){ $(this).remove();};});
        		$(".searchhistory a").each(function(index){if(index > 2){ $(this).remove();};});
            },1000);
    		
        },
        //获取列表
        getList: function(){
        	$.ajax({
        		xhrFields: {
                    withCredentials: true
                },
        		type: "GET",
        		url: '/front/index/frontIndex',
        		dataType:"json",
        		success: function(data){
        		 if("0" == data.rc){
        			 vm.newPhoto = data.photoList;
        			 vm.newLeaveMsg = data.leavemsgList;
        			 vm.classStu = data.studentList;
        		 }else if ("exception" == data.result){
                 	// showException("前台首页",data.exception);//显示异常
                 }
        		}
        	}).done().fail(function(){
                swal("登录失效!", "请求服务器无响应，稍后再试", "warning");
                setTimeout(function () {
                	window.location.href = "../login.html";
                }, 2000);
            });
        },
        getClaStu:function(pageNo1){
        	if(undefined==pageNo1||""==pageNo1||null==pageNo1){
        		pageNo1=1;
			}
            $.ajax({
                xhrFields: {
                    withCredentials: true
                },
                type: "GET",
                url: '/front/index/getClaStu',
				data:{
                    pageNo:pageNo1,
                },
                dataType:"json",
                success: function(data){
                    if("0" == data.rc){
                        vm.classStu = data.studentList;
                        pageNo=data.pageNo;
                        pageUtils(data.total,pageNo);
                    }else if ("exception" == data.result){
                        // showException("前台首页",data.exception);//显示异常
                    }
                }
            }).done().fail(function(){
                swal("登录失效!", "请求服务器无响应，稍后再试", "warning");
                setTimeout(function () {
                    window.location.href = "../login.html";
                }, 2000);
            });
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
                        vm.getClaStu(pageNo);
                    }
                }
            });
        });
    }

