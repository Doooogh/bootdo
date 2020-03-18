$().ready(function() {
	validateRule();
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
		url : "/classinfo/classinfo/save",
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
			introduce:{
               		 required : true
                },
				culture:{
               		 required : true
                },
				deptId:{
               		 required : true
                },
				createBy:{
               		 required : true
                },
				createDate:{
               		 required : true
                },
				updateDate:{
               		 required : true
                },
				isNew:{
               		 required : true
                },
					},
		messages : {
	introduce:{
     				  required : icon + "请输入班级介绍"
		},
			culture:{
     				  required : icon + "请输入班级文化"
		},
			deptId:{
     				  required : icon + "请输入班级id"
		},
			createBy:{
     				  required : icon + "请输入创建人"
		},
			createDate:{
     				  required : icon + "请输入"
		},
			updateDate:{
     				  required : icon + "请输入修改时间"
		},
			isNew:{
     				  required : icon + "请输入是否是最新 0不是 1 是"
		},
					}
	})
}