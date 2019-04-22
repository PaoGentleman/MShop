//自己编写的库函 减少重复的代码

function validateEmpty(eleName) {
  var obj = document.getElementById(eleName);
  var msg = document.getElementById(eleName + "Msg");
  if(obj.value != ""){
    obj.className = "right";
    if(msg != null)
      msg.innerHTML = "<font color='green'>内容输入正确!</font>";
    return true;
  } else {
    obj.className = "wrong";
    if(msg != null)
      msg.innerHTML = "<font color='red'>内容不允许为空!</font>";
    return false;
  }
}

function validateRegex(eleName, regex) {
    var obj = document.getElementById(eleName);
    var msg = document.getElementById(eleName + "Msg");
    if(regex.test(obj.value)){
      obj.className = "right";
      if(msg != null)
        msg.innerHTML = "<font color='green'>内容输入正确!</font>";
      return true;
    } else {
      obj.className = "wrong";
      if(msg != null)
        msg.innerHTML = "<font color='red'>内容输入不正确!</font>";
      return false;
    }
}

function changeColor(obj,color) {
	obj.bgColor = color;
}

function checkboxSelect(obj,eleName) {
	var item = document.all(eleName);
	if(item.length == undefined) {
		document.getElementById(eleName).checked = this.checked;
	} else {
		for(var x = 0; x < item.length; x++) {
			item[x].checked = obj.checked;
		}
	}
}

//paramName需要删除的名字
//url为需要删除的操作路径
//eleName取得数据的名称
function deleteAll(url,paramName,eleName) {
	var data = "";//保存所有要删除的数据编号，但是数据又可能是数组也有可能是一个
	var item = document.all(eleName);
	var count = 0;//保存要删除的数据个数
	//判断是否有需要删除的数据
	if(item.length == undefined) {
		if(document.getElementById(eleName).checked == true) {
			data += document.getElementById(eleName).value + "--";
			count++;
		}
	} else {
		for(var x = 0; x < item.length; x++) {
			if(item[x].checked == true) {
				data += item[x].value + "--";
				count++;
			}
		}
	}
	if(count > 0) { 
		if(window.confirm("确定要执行操作吗？")) {
			var jsurl = url;
			var jsparamName = paramName;
			var durl = url + "&" + paramName + "=" + data;
			window.location.href = durl;
		}
	} else {
		alert("您还未选择任何要操作的数据");
	}	
}

function updateAll(url,paramName,eleName) {
	deleteAll(url,paramName,eleName);
}

function openPage(url) {
	window.open(url,"_blank","查看详细信息","width=300;height=500;scollable=yes");
}
function closePage() {
	window.close();
}

function preview(file) {
    var prevDiv = document.getElementById('preview');
    if(file.files && file.files[0]) {
        var reader = new FileReader();
        reader.onload = function(evt) {
            prevDiv.innerHTML = '<img src="' + evt.target.result + '"/>';
        }
        reader.readAsDataURL(file.files[0]);
    } else {
        prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.' +
          'Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
    }
}

function goList(url,iid) {
	window.location = url + "?iid=" + iid;
}

