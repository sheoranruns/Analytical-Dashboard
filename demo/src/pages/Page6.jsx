import axios from 'axios';
import React, { useEffect, useState } from 'react'
import * as XLSX from 'xlsx';
// import { Line } from "react-chartjs-2";
import { XAxis, YAxis, CartesianGrid, Tooltip, AreaChart,Area } from 'recharts';



function Page6() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = () => {
    axios
      .get("http://localhost:8080/dashboard6/good_customers_by_cbo_srm_id")
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

      <h1 className="chart-heading" style={{ marginBottom: 10 }}>Good Customers By Cbo Srm Id --- Area Chart</h1>
      <AreaChart width={730} height={250} data={data}
        margin={{ top: 10, right: 30, left: 0, bottom: 0 }}>
        <defs>
          <linearGradient id="colorUv" x1="0" y1="0" x2="0" y2="1">
            <stop offset="5%" stopColor="#8884d8" stopOpacity={0.8} />
            <stop offset="95%" stopColor="#8884d8" stopOpacity={0} />
          </linearGradient>
          <linearGradient id="colorPv" x1="0" y1="0" x2="0" y2="1">
            <stop offset="5%" stopColor="#82ca9d" stopOpacity={0.8} />
            <stop offset="95%" stopColor="#82ca9d" stopOpacity={0} />
          </linearGradient>
        </defs>
        <XAxis dataKey="sol_id" />
        <YAxis />
        <CartesianGrid strokeDasharray="3 3" />
        <Tooltip />
        <Area type="monotone" dataKey="failure_reason_3_count" stroke="#8884d8" fillOpacity={1} fill="url(#colorUv)" />
        <Area type="monotone" dataKey="failure_reason_2_count" stroke="#82ca9d" fillOpacity={1} fill="url(#colorPv)" />
      </AreaChart>
      <div style={{ display: "flex", justifyContent: "flex-end" }}><button onClick={downloadExcel} >Download </button></div>
    </>
  )
}

export default Page6
