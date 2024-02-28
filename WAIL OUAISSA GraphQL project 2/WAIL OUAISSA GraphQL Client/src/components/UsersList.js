import React from 'react';
import { useQuery, useMutation } from '@apollo/client';
import { GET_USERS_QUERY, UPDATE_USER_MUTATION, DELETE_USER_MUTATION } from '../graphql';
import './UsersList.css';

function UsersList() {
  const { data, loading, error, refetch } = useQuery(GET_USERS_QUERY, {
    variables: { page: 0, limit: 10, sortBy: 'registrationDate' },
  });
  const [updateUser] = useMutation(UPDATE_USER_MUTATION);
  const [deleteUser] = useMutation(DELETE_USER_MUTATION);

  const handleDelete = (id) => {
    deleteUser({ variables: { id } })
      .then(() => refetch())
      .catch((error) => console.error("Error deleting user:", error));
  };

  if (loading) return <div className="loading">Loading...</div>;
  if (error) return <div className="error">Error: {error.message}</div>;

  return (
    <div className="users-list-container">
      <h2 className="users-title">Registered Users</h2>
      <ul className="users-list">
        {data && data.users.data.map((user) => (
          <li key={user.id} className="user-item">
            <div className="user-info">
              <div className="user-name">{`${user.firstName} ${user.lastName}`}</div>
              <div className="user-email">{user.email}</div>
            </div>
            <div className="user-actions">
              <button className="action-btn edit" onClick={() => {}}>Edit</button>
              <button className="action-btn delete" onClick={() => handleDelete(user.id)}>Delete</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default UsersList;
