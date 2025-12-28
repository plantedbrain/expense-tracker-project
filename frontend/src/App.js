import React, { useEffect, useState } from "react";
import axios from "axios";

const API = process.env.REACT_APP_API_URL + "/api/expenses";

export default function App() {
  const [expenses, setExpenses] = useState([]);
  const [title, setTitle] = useState("");
  const [amount, setAmount] = useState("");
  const [date, setDate] = useState("");
  const [msg, setMsg] = useState("");

  // ✅ LOAD EXPENSES ON PAGE LOAD
  useEffect(() => {
    fetchExpenses();
  }, []);

  const fetchExpenses = async () => {
    try {
      const res = await axios.get(API);
      setExpenses(res.data);
    } catch (err) {
      console.error(err);
      setMsg("Failed to load expenses");
    }
  };

  const submit = async () => {
    try {
      await axios.post(API, {
        title,
        amount: parseFloat(amount),
        date
      });

      setTitle("");
      setAmount("");
      setDate("");
      setMsg("Expense added");

      // ✅ REFRESH TABLE AFTER SAVE
      fetchExpenses();
    } catch (err) {
      console.error(err);
      setMsg("Error saving expense");
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Expense Tracker</h2>

      <input
        placeholder="Title"
        value={title}
        onChange={e => setTitle(e.target.value)}
      />
      <input
        type="number"
        placeholder="Amount"
        value={amount}
        onChange={e => setAmount(e.target.value)}
      />
      <input
        type="date"
        value={date}
        onChange={e => setDate(e.target.value)}
      />

      <button onClick={submit}>Save</button>
      <p>{msg}</p>

      <hr />

      <h3>Expenses</h3>

      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>Title</th>
            <th>Amount</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {expenses.map(e => (
            <tr key={e.expenseId}>
              <td>{e.title}</td>
              <td>{e.amount}</td>
              <td>{e.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
