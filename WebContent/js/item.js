function validateIid() {
	return validateEmpty("iid");
}

function validateTitle() {
	return validateEmpty("title");
}
function goUpdate(iid) { 	//自定义表单控制
	if(validateEmpty("title-" + iid)) {
		title = document.getElementById("title-" + iid).value;
		document.getElementById("iid").value = iid;
		document.getElementById("title").value = title;
		document.getElementById("itemForm").submit();
	}
}
function validateInsert() {
	return validateTitle();
}