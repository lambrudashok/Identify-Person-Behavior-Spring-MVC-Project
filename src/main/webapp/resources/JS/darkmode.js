


/*menus notification desktop count*/
function notificationCount() {
     let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("cout").innerHTML=this.responseText;
		}                                           
	};
	xhttp.open("POST","countnotification",true);
	xhttp.send();
 }
 
 
 /*menus notification mobile count*/
function notificationCountMobile() {
     let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("coutNty").innerHTML=this.responseText;
		}                                           
	};
	xhttp.open("POST","countnotificationNty",true);
	xhttp.send();
 }
 

// Function to toggle dark mode by changing CSS variables
const darkmodechange = () => {
    const root = document.documentElement; // Get the root element (for CSS variables)
    
    if (root.classList.contains('dark-mode')) {
        // Switch to light mode
        
        root.style.setProperty('--mixvoilet', 'linear-gradient(to bottom, #0f0c29, #302b63, #24243e)');
        root.style.setProperty('--voilet', '#24243e');
        root.style.setProperty('--pink', 'linear-gradient(to right, #e8e6f7, white, #e8e6f7)');
        root.style.setProperty('--gray', '#ebeced');
        root.style.setProperty('--white', 'white');
        root.style.setProperty('--black', 'black');
        root.style.setProperty('--border', '#BDBDBD');
        root.style.setProperty('--darkgray', '#878787');
        root.style.setProperty('--darkgraymode', '#878786');
        root.style.setProperty('--error', 'red');
        root.style.setProperty('--success', 'green');
        
        checkbox.nextElementSibling.querySelector('.darkmodebtn').style.transform = 'translateX(0rem)';
        localStorage.setItem('theme', 'light');
        root.classList.remove('dark-mode');
    } else {
        // Switch to dark mode
        root.style.setProperty('--mixvoilet', 'linear-gradient(to right, #e8e6f7, white, #e8e6f7)');
        root.style.setProperty('--voilet', 'white');
        root.style.setProperty('--pink', '#242526');
        root.style.setProperty('--gray', '#737475');
        root.style.setProperty('--white', '#24243e');
        root.style.setProperty('--black', 'white');
        root.style.setProperty('--darkgray', '#E4E6EB');
        root.style.setProperty('--darkgraymode', '#2196F3');
        root.style.setProperty('--error', '#fa7070');
        root.style.setProperty('--success', '#91fac4');
       	
       	checkbox.nextElementSibling.querySelector('.darkmodebtn').style.transform = 'translateX(1rem)';
        localStorage.setItem('theme', 'dark');
        root.classList.add('dark-mode');
    }
};

// Check stored theme preference on page load
window.onload = () => {
	notificationCount();
	notificationCountMobile();
	
    const root = document.documentElement; // Get the root element
    const savedTheme = localStorage.getItem('theme');
	 
    if (savedTheme === 'dark') {
        root.style.setProperty('--mixvoilet', 'linear-gradient(to right, #e8e6f7, white, #e8e6f7)');
        root.style.setProperty('--voilet', 'white');
        root.style.setProperty('--pink', '#242526');
        root.style.setProperty('--gray', '#737475');
        root.style.setProperty('--white', '#24243e');
        root.style.setProperty('--black', 'white');
        root.style.setProperty('--darkgray', '#E4E6EB');
        root.style.setProperty('--darkgraymode', '#2196F3');
        root.style.setProperty('--error', '#fa7070');
        root.style.setProperty('--success', '#91fac4');
        
        checkbox.nextElementSibling.querySelector('.darkmodebtn').style.transform = 'translateX(1rem)';
        root.classList.add('dark-mode');
        checkbox.checked = true;
    }else {
		checkbox.nextElementSibling.querySelector('.darkmodebtn').style.transform = 'translateX(0rem)';
        checkbox.checked = false;
    }
 
    document.getElementById('checkbox').addEventListener('click', darkmodechange);
};

