function checkPassword() {
    var pass = document.getElementById("newpass").value;
    var retype = document.getElementById("retypepass").value;

    if (!password(pass)) {
		let msg = document.getElementById("msg");
        msg.innerHTML="Password must be at least 6 characters long and contain a digit.";
        return false; 
    }

    if (!retypepassword(pass, retype)) {
		let msg = document.getElementById("msg");
		msg.innerHTML="Passwords do not match.";
        return false; 
    }
    return true;
}


function password(pass) {
    let passRex = /^(?=.*\d).{6,}$/;
    return passRex.test(pass);
}

function retypepassword(pass, retype) {
    return pass === retype;
}