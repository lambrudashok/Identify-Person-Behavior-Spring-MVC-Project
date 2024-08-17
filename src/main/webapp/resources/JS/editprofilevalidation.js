
function checkField(){
	var name=document.getElementById("cname").value;
	var username =document.getElementById("username").value;
	var email =document.getElementById("email").value;
	var bio=document.getElementById("bio").value;
	
	if (!validateName(name) && !name.trim()=='') {
		let msgname = document.getElementById("msgname");
		msgname.innerHTML="Invalid name. Please use alphabets only";
        return false;
    }
    if (!validateEmail(email)  && !email.trim()=='') {
		let msgemail = document.getElementById("msgemail");
		msgemail.innerHTML="Invalid email format.";
        return false;
    }
    
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("editGrid").innerHTML=this.responseText;
			
		}
	};
	xhttp.open("POST","updateprofile?name="+name+"&username="+username+"&email="+email+"&bio="+bio,true);
	xhttp.send();
 }
    

function validateName(name) {
	//Allows only alphabets and spaces
    let nameRegex = /^[a-zA-Z\s]+$/;
    return nameRegex.test(name);
}

function validateEmail(email) {
    let emailRegex = /^[a-zA-Z0-9]*@gmail.com$/;
    return emailRegex.test(email);
}

function a(){
	let file=document.getElementById("chooseprofile");
	file.click();
}

//check image choose or not
function profilefun(){
	let chooseImgFile=document.getElementById("chooseprofile").value;
	if(!chooseImgFile==''){
		return true;
	}else{
		return false;
	}
}

//change image profile
 function profileImgChange(value){
	let img = document.getElementById("profilepic");
	img.src = URL.createObjectURL(value.files[0]);
 }

 