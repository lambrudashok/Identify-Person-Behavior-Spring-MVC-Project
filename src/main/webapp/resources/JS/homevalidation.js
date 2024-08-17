/**
 * 
 */

 function searchUsingAjaxUser(str){
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("showGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","searchuser?n="+str,true);
	xhttp.send();
 }
 
 
 // For You Like
  function likeForYou(postid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("likecommentGridA"+postid).innerHTML=this.responseText;
			document.getElementById("likecommentGrid"+postid).innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","likeforyoucontroller?postid="+postid,true);
	xhttp.send();
 }
 
 // For You Unlike
 function unlikeForYou(postid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("likecommentGridA"+postid).innerHTML=this.responseText;
			document.getElementById("likecommentGrid"+postid).innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","unlikeforyoucontroller?postid="+postid,true);
	xhttp.send();
 }
 
 

 
 function followUser(value){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("showGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","searchfollowcontroller?followid="+value,true);
	xhttp.send();
 }
 
 function unfollowUser(value){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("showGrid").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","searchunfollowcontroller?unfollowid="+value,true);
	xhttp.send();
 }
 
 
 
 function unfollowShow(button){
	button.style.color="red";
	button.style.fontWeight="bold";
	button.style.border="red solid 1px";
	button.style.backgroundColor="#1a0000";
	button.innerHTML="Unfollow";
 }
 
 
 
 function followingShow(button){
	button.style.color="white";
	button.style.fontWeight="normal";
	button.style.backgroundColor="#BDBDBD";
	button.innerHTML="Following";
	button.style.fontWeight="none";
	button.style.border="none";
	}