import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './SetCredentials.css';

const SetCredentials = () => {
  const [credentials, setCredentials] = useState({
    username: '',
    password: ''
  });

  const [error, setError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [successMsg, setSuccessMsg] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCredentials((prev) => ({ ...prev, [name]: value }));

    if (name === 'password') {
      validatePassword(value);
    }

    setError('');
    setPasswordError('');
    setSuccessMsg('');
  };

  const validatePassword = (password) => {
    const lengthCheck = password.length >= 8;
    const upperCaseCheck = /[A-Z]/.test(password);
    const numberCheck = /[0-9]/.test(password);
    const specialCharCheck = /[!@#$%^&*(),.?":{}|<>]/.test(password);

    if (!lengthCheck || !upperCaseCheck || !numberCheck || !specialCharCheck) {
      setPasswordError(
        'Password must have at least 8 characters, one uppercase letter, one number, and one special character.'
      );
      return false;
    } else {
      setPasswordError('');
      return true;
    }
  };

  const handleSubmit = async (e) => {
  e.preventDefault();

  const isPasswordValid = validatePassword(credentials.password);
  if (!isPasswordValid) return;

  try {
    const response = await axios.post('http://localhost:8082/public/create-user', {
      userName: credentials.username,
      passWord: credentials.password
    });

    if (response.status === 202) {
      setSuccessMsg('Credentials saved successfully! Redirecting to home...');
      setTimeout(() => {
        navigate('/home');
      }, 2000);
    } else {
      setError('Username might already exist or something went wrong.');
    }

  } catch (err) {
    console.error('❌ Error creating user:', err);
    console.error('Status:', err.response?.status);
    console.error('Data:', err.response?.data);
    setError('Something went wrong while creating user.');
  }
};
  return (
    <div className="credentialsContainer">
      <h2>Set Your Login Credentials</h2>
      <form onSubmit={handleSubmit}>
        <label>Username:
          <input
            type="text"
            name="username"
            value={credentials.username}
            onChange={handleChange}
            required
          />
        </label>
        {error && <span className="errorText">{error}</span>}

        <label>Password:</label>
        <div className="passwordWrapper">
          <input
            type={showPassword ? 'text' : 'password'}
            name="password"
            value={credentials.password}
            onChange={handleChange}
            required
          />
          <span
            className="togglePassword"
            onClick={() => setShowPassword(!showPassword)}
          >
            {showPassword ? 'Hide' : 'Show'}
          </span>
        </div>
        {passwordError && <span className="errorText">{passwordError}</span>}

        <button type="submit" className="submitButton">Save</button>
      </form>

      {successMsg && <p className="successMsg">{successMsg}</p>}
    </div>
  );
};

export default SetCredentials;
