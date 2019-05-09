/**
 * @Title: QuestionBank.js
 * @Package js/view_teacher
 * @Description: 上传题库,给servlet一个文件名,文件流,题库号,
 * @author alway
 * @date 2019年5月9日 下午6:41:59
 * @version V1.0
 */

$("#uploadButton").click(function() {
    var pId = $('#pId').val();
    var fd = new FormData();
    console.log("题库编号" + pId)
    fd.append("pId", pId);
    fd.append("photo", $('#f')[0].files[0]);
    $.ajax({
	url : 'ReadFile',
	type : 'post',
	processData : false,
	contentType : false,
	data : fd,
	success : function(result) {
	    console.log("success!!!");
	    $(".result").html(result);
	}
    });
});
