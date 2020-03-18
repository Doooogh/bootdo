$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/filedir/fileDir/update",
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
				createBy:{
            required : true
        },
				createDate:{
            required : true
        },
				type:{
            required : true
        },
			},
    messages : {
name:{
                required : icon + "请输入相册目录名称"
            },
createBy:{
                required : icon + "请输入创建人"
            },
createDate:{
                required : icon + "请输入创建时间"
            },
type:{
                required : icon + "请输入类型"
            },
    }
})
}