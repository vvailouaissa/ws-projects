import React from 'react';
import { useMutation } from '@apollo/client';
import { DELETE_USER_MUTATION } from '../graphql/userQueries';

function DeleteUser({ userId, onUserDeleted }) {
  const [deleteUser, { loading, error }] = useMutation(DELETE_USER_MUTATION, {
    variables: { id: userId },
    onCompleted: onUserDeleted,
  });

  if (loading) return <p>Deleting...</p>;
  if (error) return <p>Error : {error.message}</p>;

  return (
    <button onClick={() => deleteUser()}>Delete User</button>
  );
}

export default DeleteUser;
