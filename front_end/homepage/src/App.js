import logo from './logo.svg';
import './App.css';
import Home from './components/Home';

import Register from './components/Register';
import Result from './components/Results';
import Login from './components/Login';
import Header from './components/Header';
import { Route,Routes } from 'react-router-dom';
function App() {
  return (
    <div>
      <Header/>
      <Routes>
        <Route path='/' element={<Home/>}></Route>
        <Route path='/register' element={<Register/>}></Route>
        <Route path='/result' element={<Result/>}></Route>
        <Route path='/login' element={<Login/>}></Route>
      </Routes>
    </div>
   
  );
}

export default App;
