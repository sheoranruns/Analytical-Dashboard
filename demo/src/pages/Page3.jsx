import axios from 'axios';
import React, { useEffect, useState } from 'react'
import * as XLSX from 'xlsx';
// import { Line } from "react-chartjs-2";
import {  XAxis, YAxis, CartesianGrid, Tooltip, Legend, BarChart, Bar } from 'recharts';



function Page3() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = () => {
    axios
      .get("http://localhost:8080/dashboard3/failure_reason_count_by_sol_id")
      .then((res) => {
        console.log(res, "res");
        setData(res?.data);
      })
      .catch((err) => console.log(err));
  };
  const downloadexl = () => {
    let a = document.createElement("a");
    document.body.appendChild(a);
    a.style = "display: none";
    a.setAttribute("download", "abcd.xlsx");
    a.click();

  }
  function jsonToSheet(json) {
    const sheet = XLSX.utils.json_to_sheet(json);
    return sheet;
  }

  function downloadExcel() {
    const sheet = jsonToSheet(data);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, sheet, 'Sheet1');
    XLSX.writeFile(workbook, `abcd.xlsx`);
  }
  return (
    <>

      <h1 className="chart-heading" style={{ marginBottom: 10 }}>Failure Reason Count By Sol Id --- Bar Chart</h1>
      <BarChart width={730} height={250} data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="sol_id" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Bar dataKey="failure_reason_1_count" fill="#8884d8" />
        <Bar dataKey="failure_reason_4_count" fill="#82ca9d" />
      </BarChart>
      <div style={{ display: "flex", justifyContent: "flex-end" }}><button onClick={downloadExcel} >Download </button></div>
    </>
  )
}

export default Page3
