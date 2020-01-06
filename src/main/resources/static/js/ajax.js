/**
 * 
 */

function doAjaxGet(url , params , callback) {
	//console.log("url");
	
	//1.创建ajax请求对象(XHR)
	var xhr = new XMLHttpRequest() ; 
	//2.注册时间监听(监听客户端与服务端的通讯状态)
	xhr.onreadystatechange = function(result) {
		if(xhr.readyState==4&&xhr.status==200){
			callback(result);
		}
	};//别写小括号，写了小括号表示自己调用，不写表示系统调用
	//3.建立与服务端的连接
	xhr.open("GET" , url+"?"+params,true) ; 
	//true 表示异步，false 表示同步

	//4.发送请求
	xhr.send(null) ; 
	//GET请求，方法内部不传任何参数
	
}
function doAjaxPost(url , params , callback) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(event) {
		if( xhr.readyState==4&& xhr.status==200) {
			callback(event);
		}
	}; 
	xhr.open("POST" , url , true) ;
	
	xhr.setRequestHeader("Content-Type" , 
			"application/x-www-form-urlencoded");
	
	xhr.send(params);
}