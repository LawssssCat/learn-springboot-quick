/**
 * 
 */
//基于此函数发送ajax请求
function doHandleResponseJson(result) {
	//console.log(result) ; 
	var jsonResult = JSON.parse(result);
	//console.log(jsonResult);

	var trs = "" ;
	for(var i=0 ; i<jsonResult.length ; i++){
		var j =  jsonResult[i];
		trs += 	"<tr>"+
					"<td>"+j.id+"</td>"+
					"<td>"+j.name+"</td>"+
					"<td>"+j.remark+"</td>"+
					"<td>"+j.createdTime+"</td>"+
					"<td>"+
						"<a onclick='deleteObject("+j.id+",\""+j.name+"\")'>delete</a>"+
					"</td>"+
				"</tr>";
	}

	
	document.getElementById("tbodyId").innerHTML = trs;

}


function findObjects() {
	//发送 ajax请求获取商品，然后将数据根性到页面上
	var params = "" ; 
	var url = "doFindGoods" ;
	doAjaxGet(url , params ,function(result) {
		var xhr = result.target ; 
		doHandleResponseJson(xhr.responseText) ;
	}) ; 
}

//负责处理添加按钮事件
function doSaveObject() {
	//1.url
	var url = "addGoods";
	//2.params
	var namePar = document.forms[0].name.value ; 
	var remarkPar = document.forms[0].remark.value;
	
	var params ="name="+namePar+"&remark="+remarkPar ;
	//console.log("params"+params);
	//3.send ajax post request
	doAjaxPost(url, params , function(event){
		findObjects();
	});
}

function deleteObject(id , name) {
	//alert("id",id);
	var url = "deleteGoods" ; 
	var params = "id="+id;

	
	if(confirm("确定删除 "+ name + " ?")){
		doAjaxGet(url, params, function() {
			findObjects();
			alert("删除"+id+" ok!");
		});
	}
}