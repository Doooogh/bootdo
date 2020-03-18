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
		url : "/leavemsg/leavemsg/save",
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
			touserId:{
               		 required : true
                },
				fromuserId:{
               		 required : true
                },
				content:{
               		 required : true
                },
				createDate:{
               		 required : true
                },
				isPass:{
               		 required : true
                },
					},
		messages : {
	touserId:{
     				  required : icon + "请输入给谁留言"
		},
			fromuserId:{
     				  required : icon + "请输入留言者"
		},
			content:{
     				  required : icon + "请输入留言内容"
		},
			createDate:{
     				  required : icon + "请输入留言日期"
		},
			isPass:{
     				  required : icon + "请输入审批是否通过 0否 1是"
		},
					}
	})
}