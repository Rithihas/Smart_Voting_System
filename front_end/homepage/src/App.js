import './App.css';
import Header from './components/Header';
import Home from './components/Home';
import HomeOrg from './components/HomeOrg';
import Result from './components/Results';
import AddCand from './components/AddCand'
import RemoveCand from './components/RemoveCand'
import SearchHome from './components/searchHome';
import Table from './components/Table';
import Slider from './components/Slider'
import SearchRes from './components/searchres'

import { Route,Routes } from 'react-router-dom';
import { useState } from 'react';
function App() {
  const [event,setEvent]=useState('');
  const [date,setDate] = useState('');

  const [voteevent,setVoteevent] = useState('');

  return (
    <div>
      <Header/>
      <Routes>
        <Route path='/' element={<Home/>}></Route>
        <Route exact path='/HomeOrg' element={<HomeOrg event={event} setEvent={setEvent} date={date} setDate={setDate}/>}></Route>
        <Route path='/result/:eventname' element={<Result/>}></Route>
        <Route path='/addcand' element={<AddCand event={event}/>}></Route>
        <Route path='/removecand' element={<RemoveCand/>}></Route>
        <Route path='/table' element={<Table/>}></Route>
        <Route path='/searchres' element={<SearchRes/>}></Route>
        <Route exact path='/search' element={<SearchHome/>}></Route>
        <Route path='/candidates/:eventname' element={<Slider/>}></Route>
     </Routes>
    </div>
   
  );
}

export default App;


