
function closefun(){
	window.history.back();
}





// its used for report problem user side
function reportbtnshowfun(){
	 var titlename = document.getElementById("title").value;
	 var description = document.getElementById("description").value;
	 
	 
	    if(titlename.trim() === '' || description.trim() === ''){
			var reportbtn=document.getElementById("reportbtn");
			reportbtn.style.background="white";
			reportbtn.style.color="#24243e";
			reportbtn.style.borderColor="black";
			reportbtn.disabled=true;
	    } else {
	      var reportbtn=document.getElementById("reportbtn");
			reportbtn.style.background="#24243e";
			reportbtn.style.color="white";
			reportbtn.style.fontWeight="bold";
			reportbtn.disabled=false;
	    }
        
}


// report admin page side
function pendingReportFun(reportid,title,registerid){
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("solveGrid"+reportid).innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","solvependingreport?reportid="+reportid+"&title="+title+"&registerid="+registerid,true);
	xhttp.send();
}