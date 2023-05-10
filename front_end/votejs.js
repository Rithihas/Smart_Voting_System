
var currentuser = null;


window.onload = function() {
    

     currentuser = sessionStorage.getItem("username");

     const obj = {

      sessionID : getCookie("sessionID")

     }

     console.log(obj)

     fetch("http://localhost:3128/voters/validatetoken/"+currentuser,  // link to the server
 {
 method: 'POST',   // since we are sending an object
 headers: {
 'Content-type': 'application/json',  // specifies that we are sending a json object.
 'Accept': 'application/json'         // ensures that we recieve a json object.
 },
 
 body:JSON.stringify(obj)}).then(res=>{       //body specifies the object we are sending
 if(res.ok){             // res.ok is true if response returned successfully
 return res.json()       // this value is returned to the next "then"
 }
 else{
 alert("please login.")
 }
 }).then(jsonResponse=>{
 
 
 console.log(jsonResponse);

 if(jsonResponse.authorized == "false")
 window.location.href = "login.html";



 
 } 
 ).catch((err) => window.location.href = "login.html");

}


function getCookie(cname) {
   let name = cname + "=";
   let ca = document.cookie.split(';');
   for(let i = 0; i < ca.length; i++) {
     let c = ca[i];
     while (c.charAt(0) == ' ') {
       c = c.substring(1);
     }
     if (c.indexOf(name) == 0) {
       return c.substring(name.length, c.length);
     }
   }
   return "";
 }
  