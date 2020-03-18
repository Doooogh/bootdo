$().ready(function() {
	validateRule();
    dateUtils();
    getSelectData();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});


function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/basicinfo/basicInfo/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
            name:{
                required : true
            },
            sex:{
                required : true
            },
            birthday:{
                required : true
            },
            height:{
                required : true
            },
            weight:{
                required : true
            },
            allergicHistory:{
                required : true
            },
            diseasesHistory:{
                required : true
            },
            faDiseasesHistory:{
                required : true
            },
            others:{
                required : true
            },
			userId:{
                required : true
			}

		},
		messages : {
            name:{
                required : icon + "请输入姓名"
            },
            sex:{
                required : icon + "请输入性别"
            },
            birthday:{
                required : icon + "请输入生日"
            },
            height:{
                required : icon + "请输入身高"
            },
            weight:{
                required : icon + "请输入体重"
            },
            allergicHistory:{
                required : icon + "请输入过敏史"
            },
            diseasesHistory:{
                required : icon + "请输入疾病史"
            },
            faDiseasesHistory:{
                required : icon + "请输入家族病史"
            },
            others:{
                required : icon + "请输入其他"
            },
            userId:{
                required : icon + "请输入用户id"
            },
		}
	})
}
function dateUtils() {
    layui.use('laydate', function() {
        var laydate = layui.laydate;
        //常规用法
        laydate.render({
            elem: '#birthday'
        });
    });
    }
    
    function getSelectData() {
        $('#userId').select2({
            placeholder: '请选择用户',
            ajax: {
                url: "/sys/user/listAll",
                dataType: 'json',
                delay: 250,
                data: function (params) {
                	console.log(params.term);
                    return {
                        name: params.term,
                    };
                },
                processResults: function (data) {
                	var list=new Array();
                	if(data.code=="0"){
                		list=data.list;
					}
                    return {
                        results: list
                    };
                },
                cache: true
            },
            // minimumInputLength: 2
        });
    }


