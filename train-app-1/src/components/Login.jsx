// src/components/Login.jsx
import React, { useState } from "react";
import "./Login.css";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  // Dummy user credentials (Admin, Manager, User)
  const users = [
    { username: "admin", password: "1111", role: "admin", path: "/admin" },
    { username: "manager", password: "5678", role: "manager", path: "/managerPanel" },
    { username: "metin", password: "1234", role: "user", path: "/user" },
    { username: "mehmet", password: "9999", role: "user", path: "/user" }, // Extra user example
  ];

  const handleSubmit = (e) => {
    e.preventDefault();

    // Find the user and redirect
    const foundUser = users.find(
      (user) => user.username === username && user.password === password
    );

    if (foundUser) {
      localStorage.setItem("role", foundUser.role);
      setError("");
      navigate(foundUser.path); // Redirect to the relevant page
      return;
    }

    // Invalid credentials
    setError("Invalid username or password!");
  };

  return (
    <div>
      {/* Top Navbar */}
      <nav className="navbar">
        <div className="navbar-logo">RailWave</div>
        <div className="navbar-links">
          <Link to="/register">Sign Up</Link>
          <Link to="/forgot-password">Forgot Password</Link>
        </div>
      </nav>

      {/* Login Form Container */}
      <div className="login-wrapper">
        <div className="login-container">
          <h2>Train System</h2>
          <p>Please log in to continue</p>

          {/* Error Message */}
          {error && <div className="error">{error}</div>}

          <form onSubmit={handleSubmit}>
            <input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <button type="submit">Log In</button>
          </form>

          {/* Additional Links */}
          <div className="login-links">
            <Link to="/forgot-password">Forgot Password?</Link>
           
          </div>

          {/* Extra message at the bottom */}
          <p style={{ marginTop: "15px" }}>
            Don't have an account?
            <Link
              to="/register"
              style={{ marginLeft: "5px", color: "#2193b0" }}
            >
              Sign up now!
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Login;
