// src/App.jsx
import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import logo from "./assets/logo.jpeg";

import Login from "./components/Login";
import ForgotPassword from "./components/ForgotPassword";
import Register from "./components/Register";
// User side
import UserHome from "./components/UserHome";
import JourneyList from "./components/JourneyList";
import JourneyDetail from "./components/JourneyDetail";
import Payment from "./components/Payment";
// Manager side
import ManagerPanel from "./components/ManagerPanel";
// Admin side
import Admin from "./components/Admin";
import PendingUsers from "./components/PendingUsers";
import TicketEdit from "./components/TicketEdit";
import TrainEdit from "./components/TrainEdit";
import Footer from "./components/Footer";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    // Uygulama yüklendiğinde localStorage'da "token" var mı diye bakıyoruz
    const token = localStorage.getItem("token");
    setIsLoggedIn(!!token); // token varsa true, yoksa false
  }, []);

  return (
    <Router>
      <div className="app-container">
        <header
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            backgroundColor: "#fff",
            padding: "10px 20px",
            borderBottom: "1px solid #ccc",
          }}
        >
          <Link
            to="/"
            style={{
              display: "flex",
              alignItems: "center",
              textDecoration: "none",
              color: "inherit",
            }}
          >
            <img
              src={logo}
              alt="Fast Train System Logo"
              style={{ height: "40px", marginRight: "10px" }}
            />
            <h1 style={{ margin: 0 }}>RailWave</h1>
          </Link>
          <nav>
            {isLoggedIn ? (
              // Eğer giriş yapıldıysa "Profile" göster
              <Link to="/profile" style={{ textDecoration: "none", color: "#333" }}>
                Profile
              </Link>
            ) : (
              // Giriş yoksa "Sign In" göster
              <Link to="/signin" style={{ textDecoration: "none", color: "#333" }}>
                Sign In
              </Link>
            )}
          </nav>
        </header>

        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/signin" element={<Login />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />
          <Route path="/register" element={<Register />} />

          <Route path="/user" element={<UserHome />} />
          <Route path="/journey-list" element={<JourneyList />} />
          <Route path="/journey/:id" element={<JourneyDetail />} />
          <Route path="/payment" element={<Payment />} />

          <Route path="/managerPanel" element={<ManagerPanel />} />

          <Route path="/admin" element={<Admin />} />
          <Route path="/pending-users" element={<PendingUsers />} />
          <Route path="/ticket-edit" element={<TicketEdit />} />
          <Route path="/train-edit" element={<TrainEdit />} />

          {/* Örnek bir profile rotası (isteğe bağlı) */}
          {/* <Route path="/profile" element={<Profile />} /> */}
        </Routes>

        <Footer />
      </div>
    </Router>
  );
}

export default App;
