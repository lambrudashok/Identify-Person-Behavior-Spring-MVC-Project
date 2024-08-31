

function userDelete(userid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("detailsGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","userdeleteadmin?userid="+userid,true);
	xhttp.send();
}

//unfreeze user account
function unFreezeUserFun(userid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("freezeGrid"+userid).innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","unfreezeaccountuser?userid="+userid,true);
	xhttp.send();
}


//freeze user account
function freezeUserFun(userid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("freezeGrid"+userid).innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","freezeaccountuser?userid="+userid,true);
	xhttp.send();
}

function deleteAccountRequestUser(userid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("deleteuserrequestGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","deleteaccountrequest?userid="+userid,true);
	xhttp.send();
}
