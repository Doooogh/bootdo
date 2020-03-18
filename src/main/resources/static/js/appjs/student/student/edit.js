$().ready(function() {
    getStudentTreeData();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
        getAllSelectNodes();
		update();
	}
});
function getStudentTreeData() {
    $.ajax({
        type : "GET",
        url : "/student/student/getTreeSelected",
        success : function(stuTree) {
            loadStuTree(stuTree);
        }
    });
}
function getAllSelectNodes() {
    var ref = $('#stuTree').jstree(true); // 获得整个树

    studentIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组

    $("#stuTree").find(".jstree-undetermined").each(function(i, element) {
        studentIds.push($(element).closest('.jstree-node').attr("id"));
    });
}
function update() {
    $('#studentIds').val(studentIds);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/student/student/update",
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
		userId:{
            required : true
        },
				stuId:{
            required : true
        },
			},
    messages : {
userId:{
                required : icon + "请输入"
            },
stuId:{
                required : icon + "请输入同学名称"
            },
    }
})
}