/**
 * 
 */
// we fetch data in form of json format
 function searchUsingAjaxUser(str){
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var jsonObject = JSON.parse(this.responseText);
			var str="";
			if(jsonObject != null){
				for(var i=0;i<jsonObject.length;i++){
					str=str+"<div class='userfollowing'>";
					str=str+"<div class='photo'>";
					str=str+"<img alt='' src='resources/Profile_Images/"+jsonObject[i].profileimage+"'>";
					str=str+"</div>"; // photo
					str=str+"<div class='userdetails'>";
					str=str+"<div class='namediv'>";
					str=str+"<div id='name'>"+jsonObject[i].name+"</div>";
					str=str+"<div id='username'>"+jsonObject[i].username+"</div>";
					str=str+"</div>";
					str=str+"<div id='btndiv'>";
					if(jsonObject[i].status ==0){
					//follow btn
					str=str+"<button name='follow' id='follow' value='"+jsonObject[i].registerid+"' onclick='followUser(this.value)' >Follow</button>";            
					}else{
			       // following btn
					str=str+"<button name='following' id='following' value='"+jsonObject[i].registerid+"' onmouseover='unfollowShow(this)' onmouseleave='followingShow(this)' onclick='unfollowUser(this.value)' >Following</button>";            
					}							
					str=str+"</div>";
					str=str+"</div>"; //userdetails
					str=str+"</div>";  // userfollowing
				}
			}else{
				str=str+"<h4>User Not Found</h4>";
			}
			document.getElementById("showGrid").innerHTML = str;
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
 
 
 
 
 // home page  comment logic
 function commentfun(postid,commentname){
	
        if(commentname.trim() == ''){
            return false;
        } else {
			let xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange=function(){
				if(this.readyState==4 && this.status==200){
					document.getElementById("commentGridA"+postid).innerHTML=this.responseText;
					document.getElementById("commentGrid"+postid).innerHTML=this.responseText;
				}
			};
			xhttp.open("POST","commentsubmithomepage?comment="+commentname+"&postid="+postid,true);
			xhttp.send();
			return false;
        }
 }
 
 

 // fetch JSON format data when user follow 
 function followUser(value){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			
			var jsonObject = JSON.parse(this.responseText);
			var str = "";
			if(jsonObject!=null){
				for(var i=0;i<jsonObject.length;i++){
					str=str+"<div class='userfollowing'>";
					str=str+"<div class='photo'>";
					str=str+"<img alt='' src='resources/Profile_Images/"+jsonObject[i].profileimage+"'>";
					str=str+"</div>"; // photo
					str=str+"<div class='userdetails'>";
					str=str+"<div class='namediv'>";
					str=str+"<div id='name'>"+jsonObject[i].name+"</div>";
					str=str+"<div id='username'>"+jsonObject[i].username+"</div>";
					str=str+"</div>";
					str=str+"<div id='btndiv'>";
					if(jsonObject[i].status ==0){
					//follow btn
					str=str+"<button name='follow' id='follow' value='"+jsonObject[i].registerid+"' onclick='followUser(this.value)' >Follow</button>";            
					}else{
			       // following btn
					str=str+"<button name='following' id='following' value='"+jsonObject[i].registerid+"' onmouseover='unfollowShow(this)' onmouseleave='followingShow(this)' onclick='unfollowUser(this.value)' >Following</button>";            
					}							
					str=str+"</div>";
					str=str+"</div>"; //userdetails
					str=str+"</div>";  // userfollowing
				}
			}else{
				str=str+"<h4>User Not Found</h4>";
			}
			document.getElementById("showGrid").innerHTML = str;
		}
	};
	xhttp.open("POST","searchfollowcontroller?followid="+value,true);
	xhttp.send();
 }
 
 
 //fetch JSON format data when user unfollow
 function unfollowUser(value){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("showGrid").innerHTML=this.responseText;
			var jsonObject = JSON.parse(this.responseText);
			var str = "";
			if(jsonObject != null){
				for(var i=0;i<jsonObject.length;i++){
					str=str+"<div class='userfollowing'>";
					str=str+"<div class='photo'>";
					str=str+"<img alt='' src='resources/Profile_Images/"+jsonObject[i].profileimage+"'>";
					str=str+"</div>"; // photo
					str=str+"<div class='userdetails'>";
					str=str+"<div class='namediv'>";
					str=str+"<div id='name'>"+jsonObject[i].name+"</div>";
					str=str+"<div id='username'>"+jsonObject[i].username+"</div>";
					str=str+"</div>";
					str=str+"<div id='btndiv'>";
					if(jsonObject[i].status ==0){
					//follow btn
					str=str+"<button name='follow' id='follow' value='"+jsonObject[i].registerid+"' onclick='followUser(this.value)' >Follow</button>";            
					}else{
			       // following btn
					str=str+"<button name='following' id='following' value='"+jsonObject[i].registerid+"' onmouseover='unfollowShow(this)' onmouseleave='followingShow(this)' onclick='unfollowUser(this.value)' >Following</button>";            
					}							
					str=str+"</div>";
					str=str+"</div>"; //userdetails
					str=str+"</div>";  // userfollowing
				}
			}else{
				str=str+"<h4>User Not Found</h4>";
			}
			document.getElementById("showGrid").innerHTML = str;
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