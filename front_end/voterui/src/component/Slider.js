import React,{useState,useEffect} from 'react'
import "./Slider.css"
import data from "../data"
import "https://kit.fontawesome.com/a81368914c.js"
export default function Slider(){
    const [people]=useState(data)  
    const [index,setIndex]=useState(0)
    useEffect(()=>{
        const lastIndex=people.length-1;
        if(index<0)
        setIndex(lastIndex)

        if(index>lastIndex)
        setIndex(0)
    },[index,people]);

return (<>
    <section className='section' id='section'> 
        <div className='title'>
            <h2 className='cand'>candidates</h2>


        </div>
        <div className='section-center'>
            {
                people.map((item,indexPeople)=>{
                const {id,photo,name,party,desc}=item;
                let position="nextslide";
                if(indexPeople===index)
                position="activeslide"
                if(indexPeople===index-1||(indexPeople===people.length-1&&index===0))
                position="lastslide";
                return(
                    <article className={position} key={id}>
                        <img src={photo} alt={name} className='person-img'></img>
                        <h3 className='person-name'><h5>{name}</h5></h3>
                        <h4 className='person-party'>PARTY:{party}</h4>
                        <div className='div1'>
                        <p className='person-desc'>DESCRIPTION:{desc}</p>
                        </div>
                        <div className='button'>
                            <button className='btn' onClick={()=>{
                                let section=document.getElementById("section");
                                section.style.display="none";
                               let con= document.getElementById("confirm")
                                con.style.display="block";
                            }
                        
                            } >vote</button>
                        </div>
                    </article>
                )
                })
            }
            <button className='prev' onClick={()=>{
                setIndex(index-1)
                
            }}>
                <i className='fas fa-arrow-left'></i>
            </button>
            <button className='next' onClick={()=>{
                setIndex(index+1)
                
            }}>
                <i className="fa fa-arrow-right"></i>
            </button>
        </div>

    </section>
    <div className='confirm'id='confirm'>
        <div id='upper-side'>
            <i class="fa fa-check"></i>
            <h3 id='status'> Success </h3> 
        </div>
        <div id='lower-side'>
            <p id='message'>
            Your vote has been successfully registered.
            </p>
            <a href="index.js" id="contBtn">exit</a>
        </div>
        
    </div>
   </>
)
}