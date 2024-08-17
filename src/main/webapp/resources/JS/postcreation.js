/**
 * 
 */

 function postfun(){
	 var postname = document.getElementById("postname").value;
	 
        if(postname.trim() === ''){
			var postbtn=document.getElementById("pst");
			postbtn.style.background="#AFEEEE";
			postbtn.style.color="black";
			postbtn.disabled=true;
        } else {
          var postbtn=document.getElementById("pst");
			postbtn.style.background="deepskyblue";
			postbtn.style.color="white";
			postbtn.disabled=false;
        }
        
 }
 
 function changeImg(value){
	let img = document.getElementById("twitterpic");
	img.src = URL.createObjectURL(value.files[0]);
 }
 
 