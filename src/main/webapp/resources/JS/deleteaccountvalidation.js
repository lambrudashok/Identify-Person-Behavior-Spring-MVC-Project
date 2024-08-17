/**
 * 
 */

  function deleteRequest(userID){
	let msg=document.getElementById("msg");
	msg.innerHTML="Delete Request Send";
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("requestGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","deleterequest?userID="+userID,true);
	xhttp.send();
 }
 
 
   function recoverRequest(userID){
	let msg=document.getElementById("msg");
	msg.innerHTML="Account Recover Successfully";
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("requestGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","recoverrequest?userID="+userID,true);
	xhttp.send();
 }