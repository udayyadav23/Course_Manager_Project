import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './OtpVerification.css';

const OtpVerification = () => {
  const [otp, setOtp] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleVerify = async () => {
    try {
      const response = await axios.post(`http://localhost:8082/public/email-verify/${otp}`);
      console.log(response.data);
      navigate('/set-credentials'); // ➡ Go to credentials page after success
    } catch (err) {
      console.error(err);
      setError('Invalid OTP. Please try again.');
    }
  };

  return (
    <div className="otpContainer">
      <h2>Enter OTP</h2>
      <input
        type="text"
        placeholder="Enter OTP"
        value={otp}
        onChange={(e) => setOtp(e.target.value)}
        className="otpInput"
      />
      {error && <div className="errorText">{error}</div>}
      <button onClick={handleVerify} className="verifyButton">Enter</button>
    </div>
  );
};

export default OtpVerification;
