import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './Login.css';

const Login = () => {
  const [credentials, setCredentials] = useState({
    userName: '',
    passWord: ''
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCredentials((prev) => ({ ...prev, [name]: value }));
  };

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8082/public/login', credentials);
      console.log('Login Success:', response.data);
      navigate('/home');
    } catch (error) {
      console.error('Login Error:', error);
      alert('Invalid credentials or server error');
    }
  };

  return (
    <div className="loginContainer">
      <div className="loginBox">
        <h2>Login</h2>
        <input
          type="text"
          name="userName"
          placeholder="Username"
          className="loginInput"
          value={credentials.userName}
          onChange={handleChange}
        />
        <input
          type="password"
          name="passWord"
          placeholder="Password"
          className="loginInput"
          value={credentials.passWord}
          onChange={handleChange}
        />
        <button className="loginButton" onClick={handleLogin}>Login</button>
        <p className="loginText">
          Don't have an account? <Link to="/signup" className="loginLink">Sign up</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
