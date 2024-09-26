/**
 * 
 */

 function postfun(){
	 var postname = document.getElementById("postname").value;
	 
        if(postname.trim() === ''){
			var postbtn=document.getElementById("pst");
			postbtn.style.background="white";
			postbtn.style.color="#24243e";
			postbtn.style.fontWeight="bold";
			postbtn.style.border="#24243e solid 1px";
			postbtn.disabled=true;
        } else {
          var postbtn=document.getElementById("pst");
			postbtn.style.background="#24243e";
			postbtn.style.color="white";
			postbtn.disabled=false;
        }
        
 }
 
 function changeImg(value){
	let img = document.getElementById("twitterpic");
	img.src = URL.createObjectURL(value.files[0]);
 }
 
 