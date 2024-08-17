


 function searchUserProfile(str){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("searchGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","predictionadmincontroller?n="+str,true);
	xhttp.send();
 }