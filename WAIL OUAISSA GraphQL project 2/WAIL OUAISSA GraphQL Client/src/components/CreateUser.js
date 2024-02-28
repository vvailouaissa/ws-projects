import React, { useState } from 'react';
import { useMutation } from '@apollo/client';
import { CREATE_USER_MUTATION } from '../graphql.js';
import './CreateUser.css';

function CreateUser() {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
  });
  const [isUserCreated, setIsUserCreated] = useState(false); 

  const [createUser, { loading, error }] = useMutation(CREATE_USER_MUTATION, {
    onCompleted: () => {
      setIsUserCreated(true); 
      setFormData({
        firstName: '',
        lastName: '',
        email: '',
      });
    },
    onError: () => {
      setIsUserCreated(false); 
    },
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    createUser({ variables: formData });
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <div className="create-user-form-container">
      <form onSubmit={handleSubmit} className="create-user-form">
        <h2>Create User</h2>
        <input
          className="form-input"
          name="firstName"
          type="text"
          placeholder="First Name"
          value={formData.firstName}
          onChange={handleChange}
        />
        <input
          className="form-input"
          name="lastName"
          type="text"
          placeholder="Last Name"
          value={formData.lastName}
          onChange={handleChange}
        />
        <input
          className="form-input"
          name="email"
          type="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
        />
        <button type="submit" className="submit-btn">Create User</button>
        {loading && <p className="loading">Loading...</p>}
        {error && <p className="error">Error: {error.message}</p>}
        {isUserCreated && <p className="success-message">User created successfully!</p>} {}
      </form>
    </div>
  );
}

export default CreateUser;
