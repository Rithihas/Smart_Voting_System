// global variable used to stop the usage of camera once we take the photo
var localstream;

// for knowing whether this page is being used by voter or organizer
var prefix='v';


function createToken(username)
{
  const d = new Date();
  d.setTime(d.getTime() + (6*60*60*1000)); // 6 hours expiry
  let expires = "expires="+ d.toUTCString();
  

  if(prefix == 'o')
  {
   fetch("http://localhost:3128/organizers/generatetoken/"+username,  // link to the server
   {
   method: 'GET',   // since we are sending an object
   headers: {
     // specifies that we are sending a json object.
   'Accept': 'application/json'         // ensures that we recieve a json object.
   }
  
  }).then(res=>{       //body specifies the object we are sending
   if(res.ok){             // res.ok is true if response returned successfully
  
   return res.json()       // this value is returned to the next "then"
   }
   else{
   alert("Something went wrong.")
   }
   }).then(jsonResponse=>{
   
   
    document.cookie = "sessionID" + "=" + jsonResponse.sessionID + ";" + expires + ";path=/";

    console.log(document.cookie);

    window.location.href = "orgui.html";
 
  
  
  
  
   
   } 
   ).catch((err) => console.log("Internal server error. "+err));
  
  
  }
  
  else
  
  {
  
    fetch("http://localhost:3128/voters/generatetoken/"+username,  // link to the server
   {
   method: 'GET',   // since we are recieving an object
   headers: {
    
   'Accept': 'application/json'         // ensures that we recieve a json object.
   }
  
  }).then(res=>{       //body specifies the object we are sending
   if(res.ok){     
            // res.ok is true if response returned successfully
   return res.json()       // this value is returned to the next "then"
   }
   else{
   alert("Something went wrong.")
   }
   }).then(jsonResponse=>{
   
     console.log(jsonResponse);
   
     document.cookie = "sessionID" + "=" + jsonResponse.sessionID + ";" + expires + ";path=/";

     window.location.href = "voteui.html";
   

   
   } 
   ).catch((err) => console.log("Internal server error. "+err));
  
  
  }


  sessionStorage.setItem("username", username);


}


function validatePhoto(){

//extracting input values from input tags  
var username = document.getElementById(prefix+'username').value;
var password = document.getElementById(prefix+'password').value;

// retrieving bae64 encoded string from hidden tag
var photo_string = document.getElementById('base64encoded').innerHTML;

//for displaying output during development
console.log("validating face....");

var collection = "";

if(prefix == "o")
collection = "organizer";
else 
collection = "voter";
//creating javascript object , to POST/send to the python code running on the flask server
const obj = [
  { "user":username , "pass":password , "photo":photo_string , "collection":collection}
 ];


 // this is the fetch API. it is promise based.it allows us to fetch resources and also manipulate request/responses.
 fetch("http://127.0.0.1:5000/validate",  // link to the server
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
 alert("something is wrong")
 }
 }).then(jsonResponse=>{
 
 
 console.log(jsonResponse);
 console.log("validation result: "+jsonResponse["fresult"]);

 if(jsonResponse["fresult"] == "true")
 createToken(username);
 else 
 alert("face did not match. Please try again in a well lit place with your face positioned straight.");
 



 } 
 ).catch((err) => console.error(err));
 


}


const width = 320;    // We will scale the photo width to this
const height = 320;     // photo height

function verifyLogin()
{
  var username = document.getElementById(prefix+'username').value;
    var password = document.getElementById(prefix+'password').value;

    if(username==="")
alert("please enter username");
else if(password=='')
alert("please enter valid password");

else {

  var canproceed = false;

  
  if(prefix == 'o')
   {
    fetch("http://localhost:3128/organizers/validateLogin/"+username+"/"+password,  // link to the server
    {
    method: 'GET',   // since we are sending an object
    headers: {
      // specifies that we are sending a json object.
    'Accept': 'application/json'         // ensures that we recieve a json object.
    }
   
   }).then(res=>{       //body specifies the object we are sending
    if(res.ok){             // res.ok is true if response returned successfully
   
    return res.json()       // this value is returned to the next "then"
    }
    else{
    alert("Something went wrong.")
    }
    }).then(jsonResponse=>{
    
    
    if(jsonResponse.validCredential == "true")
    onCapLog();
    else 
    alert("Invalid username or password.");
  
   
   
   
   
    
    } 
    ).catch((err) => console.log("Internal server error. "+err));
   
   
   }
   
   else
   
   {
   
     fetch("http://localhost:3128/voters/validateLogin/"+username+"/"+password,  // link to the server
    {
    method: 'GET',   // since we are recieving an object
    headers: {
     
    'Accept': 'application/json'         // ensures that we recieve a json object.
    }
   
   }).then(res=>{       //body specifies the object we are sending
    if(res.ok){     
             // res.ok is true if response returned successfully
    return res.json()       // this value is returned to the next "then"
    }
    else{
    alert("Something went wrong.")
    }
    }).then(jsonResponse=>{
    
      console.log(jsonResponse);
    
    if(jsonResponse.validCredential == "true")
    onCapLog();
    else 
    alert("Invalid username or password.");

    

    
    } 
    ).catch((err) => console.log("Internal server error. "+err));
   
   
   }
}

}

function onCapLog()
{
  
  document.getElementById('det').style.display = "none"; 
  document.getElementById('can').style.display = "none";
  document.getElementById('vid').style.display = "flex";

let streaming = false;

// all the variables required
let video = null;
let canvas = null;
let photo = null;
let startbutton = null;
var info = "this is info";



 // linking the variables to the html tags
 video = document.getElementById('video');
 canvas = document.getElementById('canvas');
 photo = document.getElementById('photo');
 startbutton = document.getElementById('snap');

 // provides access to media devices
 navigator.mediaDevices
 .getUserMedia({ video: true, audio: false })
 .then((stream) => {
   localstream = stream;       // video stream object from our webcam
   video.srcObject = stream;   // video.srcObject refers to the source of the media for the video tag. in this case, the source is the stream from our webcam.
   video.play();               // starts playing the video.
 })
 .catch((err) => {
   console.error(`An error occurred: ${err}`);
 });

 video.addEventListener(
   "canplay",       //The canplay event occurs when the browser can start playing the specified audio/video (when it has buffered enough to begin).
   (ev) => {

       //setting the height and width of our video
       video.setAttribute("width", width);
       video.setAttribute("height", height);
       canvas.setAttribute("width", width);
       canvas.setAttribute("height", height);
       streaming = true;
     
   },
   false
 );
  

}


function verifyUsername()
{
  var name = document.getElementById(prefix+'username').value;


  if(name==="")
alert("please enter username");

else {


 // this is the fetch API. it is promise based.it allows us to fetch resources and also manipulate request/responses.
 if(prefix == 'o') {
 fetch("http://localhost:3128/organizers/verifyuser/"+name,  // link to the server
 {
 method: 'GET',   // since we are sending an object
 headers: {
   // specifies that we are sending a json object.
 'Accept': 'application/json'         // ensures that we recieve a json object.
 }

}).then(res=>{       //body specifies the object we are sending
 if(res.ok){             // res.ok is true if response returned successfully

 return res.json()       // this value is returned to the next "then"
 }
 else{
 alert("Something went wrong.")
 }
 }).then(jsonResponse=>{
 
 
 if(jsonResponse.UserPresent == "true")
 alert("Username already taken.");
 else 
 {
  
 onCap();

 }


 
 } 
 ).catch((err) => console.log("Internal server error. "+err));


}

else

{

  fetch("http://localhost:3128/voters/verifyuser/"+name,  // link to the server
 {
 method: 'GET',   // since we are sending an object
 headers: {
   // specifies that we are sending a json object.
 'Accept': 'application/json'         // ensures that we recieve a json object.
 }

}).then(res=>{       //body specifies the object we are sending
 if(res.ok){             // res.ok is true if response returned successfully
 return res.json()       // this value is returned to the next "then"
 }
 else{
 alert("Something went wrong.")
 }
 }).then(jsonResponse=>{
 
 
 if(jsonResponse.UserPresent == "true")
 alert("Username already taken.");
 else 
 onCap();




 
 } 
 ).catch((err) => console.log("Internal server error. "+err));


}

}

}


function onCap() {
    var username = document.getElementById(prefix+'username').value;
var password = document.getElementById(prefix+'password').value;
var cpassword = document.getElementById(prefix+'cpassword').value;

if(username==="")
alert("please enter username");
else if(password=='')
alert("please enter valid password");
else if(password != cpassword)
alert("password does not match.");
else{
   
   document.getElementById('det').style.display = "none"; 
   document.getElementById('can').style.display = "none";
   document.getElementById('vid').style.display = "flex";
   document.getElementById('vid').style.position = "absolute";
   document.getElementById('vid').style.top = "0";

let streaming = false;

// all the variables required
let video = null;
let canvas = null;
let photo = null;
let startbutton = null;
var info = "this is info";



  // linking the variables to the html tags
  video = document.getElementById('video');
  canvas = document.getElementById('canvas');
  photo = document.getElementById('photo');
  startbutton = document.getElementById('snap');

  // provides access to media devices
  navigator.mediaDevices
  .getUserMedia({ video: true, audio: false })
  .then((stream) => {
    localstream = stream;       // video stream object from our webcam
    video.srcObject = stream;   // video.srcObject refers to the source of the media for the video tag. in this case, the source is the stream from our webcam.
    video.play();               // starts playing the video.
  })
  .catch((err) => {
    console.error(`An error occurred: ${err}`);
  });

  video.addEventListener(
    "canplay",       //The canplay event occurs when the browser can start playing the specified audio/video (when it has buffered enough to begin).
    (ev) => {

        //setting the height and width of our video
        video.setAttribute("width", width);
        video.setAttribute("height", height);
        canvas.setAttribute("width", width);
        canvas.setAttribute("height", height);
        streaming = true;
      
    },
    false
  );
  }
}

function takePicture() {

  
  document.getElementById('vid').style.display = "none";
  document.getElementById('can').style.display = "flex";
  document.getElementById('can').style.position = "absolute";
   document.getElementById('can').style.top = "0";
  // a canvas is a html tag for drawing/displaying graphic outputs to the user.
  const context = canvas.getContext("2d");  // returns a drawing context on the canvas.
  if (width && height) { 
    canvas.width = width;
    canvas.height = height-80;
    context.drawImage(video, 0, 0, width, height-80); // captures the image from our video 

    const data = canvas.toDataURL("image/jpeg",1.0); // gives base64 encoded string of our image. 

    // base64 is an encoding algorithm for representing various data (even images) in the form of readable strings.


    var cimage = data;

    cimage = cimage.substring(23);  // extracting only the base64 string.

    document.getElementById('base64encoded').innerHTML = cimage;
    

  } 

  // localstream.getTracks()[0].stop();  // this is for stopping the camera.
  localstream.getTracks()
  .forEach(track => track.stop());
 
 
}


function storeDB(){

  //extracting input values from input tags  
var name = document.getElementById(prefix+'username').value;
var pass = document.getElementById(prefix+'password').value;

// retrieving bae64 encoded string from hidden tag
var photo = document.getElementById('base64encoded').innerHTML;

//for displaying output during development
//document.getElementById('inform').innerHTML = "storing in server....";


//creating javascript object , to POST/send to the Spring REST API 
const obj = {
  username  : name ,
  password  : pass ,
  photoString : photo
}


 // this is the fetch API. it is promise based.it allows us to fetch resources and also manipulate request/responses.
 if(prefix == 'o') {
 fetch("http://localhost:3128/organizers",  // link to the server
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
 alert("Username already exists.")
 }
 }).then(jsonResponse=>{
 
 
 console.log(jsonResponse)

document.getElementById('confirm').style.display='flex';
document.getElementById('confirm').style.position = "absolute";
   document.getElementById('confirm').style.top = "0";

 
 } 
 ).catch((err) => console.log("User already exists."));

}

else

{

  fetch("http://localhost:3128/voters",  // link to the server
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
  alert("something is wrong")
  }
  }).then(jsonResponse=>{
  
  
  console.log(jsonResponse)
  
   document.getElementById('can').style.display = "none";
  document.getElementById('confirm').style.display='block';
  document.getElementById('confirm').style.position = "absolute";
   document.getElementById('confirm').style.top = "0";

  } 
  ).catch((err) => console.error(err));


}


}


// function validate()
// {
//     let username=document.getElementById(prefix+"username").value;
//     let password=document.getElementById(prefix+"password").value;
//     let confirmpassword=document.getElementById(prefix+"cpassword").value;
//     let usernamechecker=/^[A-Z]{1}[a-z]{5,9}@[0-9]{3}$/;
//     let passwordchecker=/^[A-Za-z]{5,11}@[0-9]{2,8}$/;
//     if(!usernamechecker.test(username))
//     {
//       document.getElementById("usererror").style.display="block";
//       document.getElementById("usererror").innerHTML="username cannot contain special characters.";
      
//     }
//     else
//     {
//         document.getElementById("usererror").style.display="none";
//     }
//      if(!passwordchecker.test(password)&&usernamechecker.test(username))
//     {
//       document.getElementById("passworderror").innerHTML="Invalid password";
      
//     }
//     else{
//         document.getElementById("passworderror").innerHTML="";
//     }
//     if(passwordchecker.test(password)&&usernamechecker.test(username)&&password!==confirmpassword)
//     {
//       document.getElementById("mismatchingerror").innerHTML="Mismatching of password";
      
//     }
//     else{
//         document.getElementById("mismatchingerror").innerHTML="";
//     }
// }

// function reload()
// {

//     document.getElementById("username").value="";
//     document.getElementById("password").value="";
//     document.getElementById("confirmpassword").value="";


// }




function voterLogin(){
  prefix = 'v';
    var x=document.getElementById("voter");
    var y=document.getElementById("organiser");
    var z=document.getElementById("btn");
    y.style.display='none';
    x.style.display="inherit";
    z.style.left="0px";
   }

function organiserLogin(){
  prefix = 'o';
    var x=document.getElementById("voter");
    var y=document.getElementById("organiser");
    var z=document.getElementById("btn");
    x.style.display="none";
    y.style.display="inherit";
    z.style.left="45%";
   }