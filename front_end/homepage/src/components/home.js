import React from "react"
import './home.css'
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
         <p className="subtitle">Seemless and secure platform for online voting</p>
         <div className="button">
<<<<<<< HEAD
         <button className="btn" onClick={()=>{
            window.location.href='reg.html'
         }}>Get started</button>
=======
         <button className="btn">Get started</button>
>>>>>>> 9f390d7d6a4270df8a753e9258d7c8cd68a65bf1
         <h6>IT'S FREE</h6>
         </div>
      </div>
      </div>
</main>

</>
    )
}