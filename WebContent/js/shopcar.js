var allPrice = 0.0;
window.onload = function() {
	document.getElementById("result").innerHTML = "<font color='red'>" + allPrice + "</font>";
}
function calGoods(gid) {
	var price = parseFloat(document.getElementById("price-" + gid).innerHTML);
	var count = parseInt(document.getElementById(gid).value);
	allPrice += price * count;
	document.getElementById("cal-" + gid).innerHTML = "<font color='red'>" + (price * count) + "</font>";
	if(document.getElementById("result") != undefined) {
		document.getElementById("result").innerHTML = "<font color='red'>" + allPrice + "</font>";
	}
}

function addBut(gid) {
	var count = parseInt(document.getElementById(gid).value);
	var price = parseFloat(document.getElementById("price-" + gid).innerHTML);
	allPrice -= price * count;
	count ++;
	document.getElementById(gid).value = count;
	calGoods(gid);	//重新计算价格
}

function subBut(gid) {
	var count = parseInt(document.getElementById(gid).value);
	var price = parseFloat(document.getElementById("price-" + gid).innerHTML);
	allPrice -= price * count;
	if(count > 0) { 
		count --;
		document.getElementById(gid).value = count;
		calGoods(gid);	//重新计算价格
	}
}