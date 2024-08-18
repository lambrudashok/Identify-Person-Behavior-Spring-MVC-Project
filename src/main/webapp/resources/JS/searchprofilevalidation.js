/**
 * 
 */
// json format response 
 function searchUserProfile(str){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			
			var jsonObject = JSON.parse(this.responseText);
			var str = "";
			if(jsonObject.length >0){		
				for(var i=0;i<jsonObject.length;i++){
				str=str+"<a class='userappinfo' id='userappinfo' href='anotheruserprofilepage?id="+jsonObject[i].registerid+" '>"; 
				str=str+"<div class='photo'>";
				str=str+"<img alt='' src='resources/Profile_Images/"+jsonObject[i].profileimage+"'>";
				str=str+"</div>"; // photo
				str=str+"<div class='userdetails'>";
				str=str+"<div class='namediv'>";
				str=str+"<div id='name'>"+jsonObject[i].name+"</div>";
				str=str+"<div id='username'>"+jsonObject[i].username+"</div>";
				str=str+"</div>";
				str=str+"</div>"; // userdetails
				str=str+"</a>";  //userappinfo
				}
			}else{
				str=str+"<h4>User Not Found</h4>";
			}
			document.getElementById("searchGrid").innerHTML=str;	
			
		}
	};
	xhttp.open("POST","searchprofile?n="+str,true);
	xhttp.send();
 }