/**
 * 
 */

 function unfollowShow(button){
	button.style.color="red";
	button.style.fontWeight="bold";
	button.style.border="red solid 1px";
	button.style.backgroundColor="#ebeced";
	button.innerHTML="Unfollow";
 }
 
 function followingShow(button){
	button.style.color="white";
	button.style.fontWeight="normal";
	button.style.backgroundColor="#24243e";
	button.innerHTML="Following";
	button.style.fontWeight="none";
	button.style.border="none";
	}