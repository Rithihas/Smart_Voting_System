import './result.css';
import data2 from './data2';
import React,{useState,useEffect} from 'react';
import { useParams } from "react-router-dom";
import axios from 'axios';


export default function Result(){
    const [data,setData]=useState(data2);

    const { eventname } = useParams();

    const [fetched, setFetched] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
          const result = await axios.get('http://localhost:3128/eventcandidates/'+eventname);
          setFetched(result.data);
  
         
  
        };
        fetchData();
      }, []);
  
      useEffect(() => {
          console.log("Data has been updated:", fetched);
        }, [fetched]);
    
        return (<div className='div-table'>
           <h1>CANDIDATE STANDINGS</h1>
        <table className='table-res'>
            <thead>
                
                <th className='th-res'>candidate-name</th>
                <th className='th-res'>party</th>
                <th className='th-res'>votes</th>
            </thead>
            <tbody>
                { 
                fetched.sort((a,b)=>(a["votes"]<b["votes"])?1:-1).map((item)=>(
                <tr>
                <td className='td-res'>{item.candidateName}</td>
                <td className='td-res'>{item.partyname}</td>
                <td className='td-res'>{item.votes}</td>
                </tr>))}
                
            </tbody>
        </table>
    </div>)
}