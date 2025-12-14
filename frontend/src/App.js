import React, { useEffect, useState } from "react";
import axios from "axios";

export default function App() {
  const [title, setTitle] = useState("");
  const [amount, setAmount] = useState("");
  const [msg, setMsg] = useState("");
  const [expenses, setExpenses] = useState([]);

  const [sortColumn, setSortColumn] = useState("date");
  const [sortOrder, setSortOrder] = useState("desc"); // default: latest first

  const API = process.env.REACT_APP_API_URL + "/expenses";

  const loadExpenses = async () => {
    const res = await axios.get(API);
    setExpenses(res.data);
  };

  useEffect(() => {
    loadExpenses();
  }, []);

  const submit = async () => {
    const res = await axios.post(API, {
      title,
      amount: parseFloat(amount),
      date: new Date().toISOString().substring(0, 10),
    });

    setMsg("Created: " + res.data.expenseId);
    setTitle("");
    setAmount("");
    loadExpenses();
  };

  // 🔹 Handle column sort click
  const sortBy = (column) => {
    if (sortColumn === column) {
      setSortOrder(sortOrder === "asc" ? "desc" : "asc");
    } else {
      setSortColumn(column);
      setSortOrder("asc");
    }
  };

  // 🔹 Sort logic
  const sortedExpenses = [...expenses].sort((a, b) => {
    let valA = a[sortColumn];
    let valB = b[sortColumn];

    // Numeric sort for amount
    if (sortColumn === "amount") {
      valA = Number(valA);
      valB = Number(valB);
    }

    // String comparison works for title & date (YYYY-MM-DD)
    if (valA < valB) return sortOrder === "asc" ? -1 : 1;
    if (valA > valB) return sortOrder === "asc" ? 1 : -1;
    return 0;
  });

  // Arrow indicator
  const arrow = (col) =>
    sortColumn === col ? (sortOrder === "asc" ? " ▲" : " ▼") : "";

  const deleteExpense = async (id) => {
  if (!window.confirm("Delete this expense?")) return;

  await axios.delete(API + "/" + id);
  loadExpenses(); // refresh table
  };


  return (
    <div style={{ padding: 20 }}>
      <h2>Expense Tracker</h2>

      {/* Create expense */}
      <input
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />

      <input
        placeholder="Amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      />

      <button onClick={submit}>Save</button>

      <p>{msg}</p>

      <hr />

      {/* Expenses table */}
      <h3>Expenses</h3>

      <table border="1" cellPadding="6">
        <thead>
          <tr>
            <th style={{ cursor: "pointer" }} onClick={() => sortBy("title")}>
              Title{arrow("title")}
            </th>
            <th style={{ cursor: "pointer" }} onClick={() => sortBy("amount")}>
              Amount{arrow("amount")}
            </th>
            <th style={{ cursor: "pointer" }} onClick={() => sortBy("date")}>
              Date{arrow("date")}
            </th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {sortedExpenses.map((e) => (
            <tr key={e.expenseId}>
              <td>{e.title}</td>
              <td>{e.amount}</td>
              <td>{e.date}</td>
              <td>
                <button onClick={() => deleteExpense(e.expenseId)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
