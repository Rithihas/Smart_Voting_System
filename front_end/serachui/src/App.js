import {useState} from 'react'
import './App.css'
import Table  from './components/Table'
import events from './components/data'
function App(){
  const keys=["organiserName","eventName"]
  const[query,setQuery]=useState("")
const search=(data)=>{
  return data.filter((item)=>
  keys.some(key=>item[key].toLowerCase().includes(query))
  )
}  
  return (
    <div className='app'>
    
      <input type='text' placeholder='search...' className='search' onChange={(e)=>setQuery(e.target.value)}></input>
   
      <Table data={
        search(events)
      }/>
    </div>
  )
}
export default App;