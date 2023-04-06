import axios from 'axios';
import React, { useEffect, useState } from 'react'
import * as XLSX from 'xlsx';
// import { Line } from "react-chartjs-2";
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,  } from 'recharts';



function Page1() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = () => {
    axios
      .get("http://localhost:8080/dashboard1/cbo_srm_total_interest")
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

      <h1 className="chart-heading" style={{ marginBottom: 10 }}>Cbo Srm Total Interest --- Line Chart</h1>
      <LineChart width={800} height={400} data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="total_interest" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="cbo_srm_id" stroke="#8884d8" activeDot={{ r: 8 }} />
      </LineChart>
      <div style={{ display: "flex", justifyContent: "flex-end" }}><button onClick={downloadExcel} >Download </button></div>
    </>
  )
}

export default Page1
