import React, { useState } from 'react';
import { useMutation } from '@apollo/client';
import { CREATE_POST_MUTATION } from '../graphql.js';
import './CreatePost.css'; 

function CreatePost() {
  const [postContent, setPostContent] = useState('');
  const [createPost, { data, loading, error }] = useMutation(CREATE_POST_MUTATION);

  const handleSubmit = (e) => {
    e.preventDefault();
    const userId = 1; 
    createPost({ variables: { content: postContent, userId: userId } });
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error : {error.message}</p>;

  return (
    <div className="create-post-container">
      <form className="create-post-form" onSubmit={handleSubmit}>
        <textarea
          value={postContent}
          onChange={(e) => setPostContent(e.target.value)}
          placeholder="What's on your mind?"
        ></textarea>
        <button type="submit">Create Post</button>
      </form>
    </div>
  );
}

export default CreatePost;
