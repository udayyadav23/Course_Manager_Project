import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './SignUp.css';

const SignUp = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: '',
    contact: '',
    firstName: '',
    lastName: '',
    gender: ''
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const validate = () => {
    const newErrors = {};
    if (!formData.email.includes('@')) newErrors.email = 'Invalid email';
    if (!formData.contact.match(/^\d{10}$/)) newErrors.contact = 'Contact must be 10 digits';
    if (!formData.firstName) newErrors.firstName = 'First name is required';
    if (!formData.lastName) newErrors.lastName = 'Last name is required';
    if (!formData.gender) newErrors.gender = 'Gender is required';
    return newErrors;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formErrors = validate();
    setErrors(formErrors);

    if (Object.keys(formErrors).length === 0) {
      try {
        const response = await axios.post('http://localhost:8082/public/sign-up', formData);
        console.log('User created:', response.data);
        alert('OTP is sent to your mail');
        navigate('/otp');
      } catch (error) {
        console.error('Error creating user:', error);
        alert('Failed to register. Please try again.');
      }
    }
  };

  return (
    <div className="signupContainer">
      <h2 className="formTitle">Sign Up</h2>
      <form onSubmit={handleSubmit}>
        <label>Email:
          <input type="email" name="email" value={formData.email} onChange={handleChange} />
          {errors.email && <span className="errorText">{errors.email}</span>}
        </label>

        <label>Contact Number:
          <input type="text" name="contact" value={formData.contact} onChange={handleChange} />
          {errors.contact && <span className="errorText">{errors.contact}</span>}
        </label>

        <label>First Name:
          <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} />
          {errors.firstName && <span className="errorText">{errors.firstName}</span>}
        </label>

        <label>Last Name:
          <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} />
          {errors.lastName && <span className="errorText">{errors.lastName}</span>}
        </label>

        <label>Gender:
          <div className="genderOptions">
            <label>
              <input type="radio" name="gender" value="Male" onChange={handleChange} />
              Male
            </label>
            <label>
              <input type="radio" name="gender" value="Female" onChange={handleChange} />
              Female
            </label>
            <label>
              <input type="radio" name="gender" value="Other" onChange={handleChange} />
              Other
            </label>
          </div>
          {errors.gender && <span className="errorText">{errors.gender}</span>}
        </label>

        <button type="submit" className="submitButton">Sign Up</button>
      </form>
    </div>
  );
};

export default SignUp;
