import React, { useState } from 'react';
import axios from 'axios';

export default function App(){
 const [title,setTitle]=useState('');
 const [amount,setAmount]=useState('');
 const [msg,setMsg]=useState('');

 const submit=async()=>{
   try{
     const res=await axios.post(process.env.REACT_APP_API_URL+'/expenses',
       { title, amount: parseFloat(amount), date:'2025-01-01' });
     setMsg("Created: "+res.data.expenseId);
   }catch(e){ setMsg("Error"); }
 }

 return <div style={{padding:20}}>
   <h2>Expense Tracker</h2>
   <input placeholder="title" value={title} onChange={e=>setTitle(e.target.value)}/>
   <input placeholder="amount" value={amount} onChange={e=>setAmount(e.target.value)}/>
   <button onClick={submit}>Save</button>
   <p>{msg}</p>
 </div>;
}
