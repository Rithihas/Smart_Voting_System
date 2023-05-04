import React from "react"
import './home.css'
import { ReactDOM } from "react"
export default function home(){
    return (<>
        <header className="header">
    <section className="section">
        <h1 class="title">💻SMART VOTING SYSTEM</h1>
        <div>
            <button id="open" className="nav-button">&#9776;</button>
            <nav className="nav" aria-label="main">
                <a href="./" className="anchor">Register</a> 
                <a href="#" className="anchor">Login</a>
                <a href="#" className="anchor">Results</a>
                <a href="#about" className="anchor">About</a>
            </nav>
        </div>

    </section>
   
</header>
 <main className="main">
 <h2 style={{color:"wheat", textAlign:"center"}}>We create online platform to vote ,</h2>
 <h2 className="decor">"YOUR VOTE IS YOUR VOICE"</h2>
 <img src="\votedup.png" alt="network error"></img>
 <div className="para">
 <p>Voting system plays a vital role in democratic
countries like India. “Vote” means to choose a better candidate
who is participating in the election. The process of choosing a
leader in all the candidates from a list by casting their votes, is
called “voting”.</p>
</div>
<h3 className="guide">Steps to be followed... </h3>
<ul>
    <li>step1</li>
    <li>step2</li>
    <li>step3</li>
    <li>step4</li>
    <li>step5</li>
    <li>step6</li>
</ul>

</main>
<form action="" className="form">
                <label for="subject">subject:</label>
                <input type="text" id="subject" name="subject" required minlength="30" maxlength="6000" placeholder="your subject" className="subject"></input>
                <label for="message">Message:</label>
                <textarea name="message" id="message" cols="10" rows="3" placeholder="your message" required className="message"></textarea>
                <button className="submit" value="submit">Submit</button>
            </form>
<footer>
    
</footer>
</>
    )
}
