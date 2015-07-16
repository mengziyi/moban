/**
 * 	Utils说明：  基础工具类
 * 
 * 	API ：
 *		Utils.Cookie.get(name);								//获取一个值
 *
 * */

//基础工具类
var Utils = {};
//html cookie存储
Utils.Cookie={
	cookiesBuffer: "",
	nStartIndex: -1,
	nEndIndex: -1,
	vStartIndex: -1,
	vEndIndex: -1
};
//获取一个值
Utils.Cookie.get = function(name){
	this.cookiesBuffer = "; " + document.cookie + ";";
	this.nStartIndex = this.cookiesBuffer.indexOf("; " + name + "=");
	if(this.nStartIndex == -1){
		return -1;
	} else{
		this.nStartIndex += 2;
		this.nEndIndex = this.nStartIndex + name.length;
		this.vStartIndex = this.nEndIndex + 2;
		var cTempStr = this.cookiesBuffer.slice(this.vStartIndex);
		this.vEndIndex = cTempStr.indexOf(";") + this.vStartIndex;
		return this.cookiesBuffer.slice(this.vStartIndex-1,this.vEndIndex);
	}
};