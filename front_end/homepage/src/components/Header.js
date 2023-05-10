import { Link } from "react-router-dom";
import './home.css'
export default function Header(){
    return (
<header className="header">
<section className="section">
    <div className="div">
        <button id="open" className="nav-button">&#9776;</button>
        <nav className="nav" aria-label="main">
        <Link to="/" className="anchor">Home</Link>
            <Link to="/register" className="anchor">Register</Link> 
            <Link to="/login" className="anchor">Login</Link>
            <Link to="/result" className="anchor">Results</Link>
            </nav>
    </div>
 </section>
</header>);
}