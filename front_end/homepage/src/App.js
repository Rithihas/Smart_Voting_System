import logo from './logo.svg';
import './App.css';
import Home from './components/Home';

<<<<<<< HEAD
import Result from './components/Results';

=======
import Register from './components/Register';
import Result from './components/Results';
import Login from './components/Login';
>>>>>>> 9f390d7d6a4270df8a753e9258d7c8cd68a65bf1
import Header from './components/Header';
import { Route,Routes } from 'react-router-dom';
function App() {
  return (
    <div>
      <Header/>
      <Routes>
        <Route path='/' element={<Home/>}></Route>
<<<<<<< HEAD
        
       
        <Route path='/result' element={<Result/>}></Route>
        </Routes>
=======
        <Route path='/register' element={<Register/>}></Route>
        <Route path='/result' element={<Result/>}></Route>
        <Route path='/login' element={<Login/>}></Route>
      </Routes>
>>>>>>> 9f390d7d6a4270df8a753e9258d7c8cd68a65bf1
    </div>
   
  );
}

export default App;
