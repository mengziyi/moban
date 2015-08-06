function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function setCookie(Name, cookievalue) {
	var Days = 1;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	var newcookie = Name + "=" + encodeURIComponent(cookievalue);
	document.cookie = newcookie + ";expires=" + exp.toGMTString();
}

function getCookie(name) {
	var result = null;
	var myCookie = document.cookie + ";";
	var searchName = name + "=";
	var startOfCookie = myCookie.indexOf(searchName);
	var endOfCookie;
	if (startOfCookie != -1) {
		startOfCookie += searchName.length;
		endOfCookie = myCookie.indexOf(";", startOfCookie);
		result = decodeURIComponent(myCookie.substring(startOfCookie, endOfCookie));
	}
	return result;
}