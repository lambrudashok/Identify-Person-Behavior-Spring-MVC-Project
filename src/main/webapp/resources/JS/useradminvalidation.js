

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
