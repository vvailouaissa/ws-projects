import React, { useState } from 'react';
import { useMutation } from '@apollo/client';
import { UPDATE_USER_MUTATION } from '../graphql';

function UpdateUser({ user, onClose, onUserUpdated }) {
  const [formData, setFormData] = useState({
    id: user.id,
    firstName: user.firstName,
    lastName: user.lastName,
    email: user.email,
  });

  const [updateUser, { loading, error }] = useMutation(UPDATE_USER_MUTATION, {
    onCompleted: () => {
      onUserUpdated();
      onClose();
    }
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    updateUser({ variables: formData });
  };

  if (loading) return <p>Updating...</p>;
  if (error) return <p>An error occurred: {error.message}</p>;

  return (
    <div className="update-form">
      <form onSubmit={handleSubmit}>
        <label>
          First Name:
          <input
            name="firstName"
            type="text"
            value={formData.firstName}
            onChange={handleChange}
          />
        </label>
        <label>
          Last Name:
          <input
            name="lastName"
            type="text"
            value={formData.lastName}
            onChange={handleChange}
          />
        </label>
        <label>
          Email:
          <input
            name="email"
            type="email"
            value={formData.email}
            onChange={handleChange}
          />
        </label>
        <button type="submit">Update User</button>
        <button type="button" onClick={onClose}>Cancel</button>
      </form>
    </div>
  );
}
export default UpdateUser;
