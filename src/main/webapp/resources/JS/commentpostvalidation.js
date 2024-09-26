/**
 * 
 */



 function followUser(value){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("prodiv3").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","follow?followid="+value,true);
	xhttp.send();
 }
 
 function unfollowUser(value){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("prodiv3").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","unfollow?unfollowid="+value,true);
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
	button.style.color="black";
	button.style.fontWeight="normal";
	button.style.backgroundColor="#ebeced";
	button.innerHTML="Following";
	button.style.fontWeight="none";
	button.style.border="none";
	}
 
 


// user profile comment
 function commentfun1(postid,commentname){
	
        if(commentname.trim() == ''){
            return false;
        } else {
			let xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange=function(){
				if(this.readyState==4 && this.status==200){
					document.getElementById("commentGrid"+postid).innerHTML=this.responseText;
					
				}
			};
			xhttp.open("POST","commentsubmit?comment="+commentname+"&postid="+postid,true);
			xhttp.send();
			return false;
        }
 }
 
 
 
 // another user profile comment
 function commentfun(postid,commentname){
	
        if(commentname.trim() == ''){
            return false;
        } else {
			let xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange=function(){
				if(this.readyState==4 && this.status==200){
					document.getElementById("commentGrid"+postid).innerHTML=this.responseText;
					
				}
			};
			xhttp.open("POST","commentsubmitanotheruser?comment="+commentname+"&postid="+postid,true);
			xhttp.send();
			return false;
        }
 }
 
 
 function likefun(postid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("likeGrid"+postid).innerHTML=this.responseText;
			
		}
	};
	xhttp.open("POST","likecontroller?postid="+postid,true);
	xhttp.send();
 }
 
 function unlikefun(postid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("likeGrid"+postid).innerHTML=this.responseText;
			
		}
	};
	xhttp.open("POST","unlikecontroller?postid="+postid,true);
	xhttp.send();
 }
 