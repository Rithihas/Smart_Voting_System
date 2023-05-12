import logo from './logo.svg';
import './App.css';
import Home from './components/Home';

import Result from './components/Results';

import Header from './components/Header';
import { Route,Routes } from 'react-router-dom';
function App() {
  return (
    <div>
      <Header/>
      <Routes>
        <Route path='/' element={<Home/>}></Route>
        
       
        <Route path='/result' element={<Result/>}></Route>
        </Routes>
    </div>
   
  );
}

export default App;
