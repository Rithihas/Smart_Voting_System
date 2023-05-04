
import React from "react";
import './Table.css'
const  Table =({data})=>{
  return(
    <table cellPadding={5}px cellSpacing={10} >
      <tbody>
        <tr>
          <td className="th">event-Name</td>
          <td className="th">organiser-Name</td>
          <td className="th">due-date</td>
        </tr>
        {data.map((item)=>(
          <tr key={item.key}>
            <a href="login.html" className="a"><td className="td">{item.eventName}</td></a>
            <td className="td">{item.organiserName}</td>
            <td className="td">{item.duedate}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
export default Table;