import React, { useState, useEffect } from 'react';
import { useQuery, useMutation } from '@apollo/client';
import './App.css';
import { GET_POSTS_QUERY, CREATE_POST_MUTATION, DELETE_POST_MUTATION, UPDATE_POST_MUTATION, GET_USERS_QUERY } from './graphql';

function Posts() {
  const { loading, error, data, refetch } = useQuery(GET_POSTS_QUERY);
  const [createPost] = useMutation(CREATE_POST_MUTATION);
  const [deletePost] = useMutation(DELETE_POST_MUTATION);
  const [updatePost] = useMutation(UPDATE_POST_MUTATION);

  const [content, setContent] = useState('');
  const [editingPostId, setEditingPostId] = useState(null);
  const [selectedUserId, setSelectedUserId] = useState("");
  const { loading: usersLoading, error: usersError, data: usersData } = useQuery(GET_USERS_QUERY, {
    variables: {
      page: 1,
      limit: 10,
      sortBy: "registrationDate",
    },
  });

  useEffect(() => {
    if (editingPostId === null) {
      setContent('');
      setSelectedUserId("");
    }
  }, [editingPostId]);

  if (loading || usersLoading) return <div className="loader">Loading...</div>;
  if (error) return <div className="error">Error loading posts: {error.message}</div>;
  if (usersError) return <div className="error">Error loading users: {usersError.message}</div>;

  return (
    <div className="posts-container">
      <div className="user-selector">
        <label>Select User: </label>
        <select value={selectedUserId} onChange={(e) => setSelectedUserId(e.target.value)}>
          <option value="">Select a user</option>
          {usersData?.users.data.map((user) => (
            <option key={user.id} value={user.id}>{user.firstName} {user.lastName}</option>
          ))}
        </select>
      </div>
      <div className="posts-container">
      <div className="user-selector">
        <label>Select User: </label>
        <select value={selectedUserId} onChange={(e) => setSelectedUserId(e.target.value)}>
          <option value="">Select a user</option>
          {usersData?.users.data.map((user) => (
            <option key={user.id} value={user.id}>{user.firstName} {user.lastName}</option>
          ))}
        </select>
      </div>
      <div className="posts-list">
        {data.findAllPosts.map(post => (
          <div key={post.id} className="post-item">
            <p>{post.content}</p>
            <p>Posted by: {post.user.firstName} {post.user.lastName}</p>
          </div>
        ))}
      </div>
    </div>
    </div>
    
  );
}

export default Posts;
