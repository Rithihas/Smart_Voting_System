
// global variable used to stop the usage of camera once we take the photo
var localstream;


function validatePhoto(){

//extracting input values from input tags  
var username = document.getElementById('username').value;
var password = document.getElementById('password').value;

// retrieving bae64 encoded string from hidden tag
var photo_string = document.getElementById('base64encoded').innerHTML;

//for displaying output during development
document.getElementById('inform').innerHTML = "validating face....";


//creating javascript object , to POST/send to the python code running on the flask server
const obj = [
  { "user":username , "pass":password , "photo":photo_string}
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
 
 
 console.log(jsonResponse)
 document.getElementById('inform').innerHTML = "validation result: "+jsonResponse["fresult"];



 } 
 ).catch((err) => console.error(err));
 


}


const width = 320;    // We will scale the photo width to this
const height = 320;     // photo height


  function onCap() {
   
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

function takePicture() {

  
  document.getElementById('vid').style.display = "none";
  document.getElementById('can').style.display = "flex";

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

  localstream.getTracks()[0].stop();  // this is for stopping the camera.

 
 
}


function storeDB(){

  //extracting input values from input tags  
var username = document.getElementById('username').value;
var password = document.getElementById('password').value;

// retrieving bae64 encoded string from hidden tag
var photo_string = document.getElementById('base64encoded').innerHTML;

//for displaying output during development
document.getElementById('inform').innerHTML = "validating face....";


//creating javascript object , to POST/send to the python code running on the flask server
const obj = [
  { "user":username , "pass":password , "photo":photo_string}
 ];


 // this is the fetch API. it is promise based.it allows us to fetch resources and also manipulate request/responses.
 fetch("http://127.0.0.1:5000/insert",  // link to the server
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
 document.getElementById('inform').innerHTML = "validation result: "+jsonResponse["fresult"];



 } 
 ).catch((err) => console.error(err));


}
