import React from "react"
import './home.css'
import { Link } from "react-router-dom";

import { ReactDOM } from "react"
export default function Home(){
    return (<>
  
 <main className="main">
    { /*<h2 style={{color:"wheat", textAlign:"center"}}>We create online platform to vote ,</h2>
 <h2 className="decor">"YOUR VOTE IS YOUR VOICE"</h2>
 <img class="wave" src="wavedup2.png"></img>
 <div className="home-img">
 <img src="\imgdup.svg" className="img1" alt="network error"></img>
 </div>
    */}
      <img className="wave" src="wavedup2.png"></img>
      <div className="container">
      <div className="img">
			<img src="bgimgdup.svg"  className="bgimg"/>  
		</div>
      <div className="side-div">
         <p className="title"> SMART VOTE</p>
         <p className="subtitle">Seamless and secure platform for online voting.</p>
         <div className="button">

         <button className="btn-home" onClick={()=>{
            window.location.href='reg.html'
         }}>Get started</button>

         </div>
      </div>
      </div>
</main>

</>
    )
}