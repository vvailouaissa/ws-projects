import React, { useState } from 'react';
import { useQuery, useMutation } from '@apollo/client';
import { GET_POSTS_QUERY, UPDATE_POST_MUTATION, DELETE_POST_MUTATION, SUBMIT_COMMENT_MUTATION } from '../graphql';
import './PostsList.css';

function PostsList() {
  const [page, setPage] = useState(0);
  const [editingPostId, setEditingPostId] = useState(null);
  const [newContent, setNewContent] = useState('');
  const [commentContent, setCommentContent] = useState({});
  const [notification, setNotification] = useState({ message: '', type: '' });
  const [showCommentInput, setShowCommentInput] = useState({});
 
  const { data, loading, error } = useQuery(GET_POSTS_QUERY, {
    variables: { page: page, limit: 10, sortBy: 'creationDate' },
  });

  const [updatePost] = useMutation(UPDATE_POST_MUTATION);
  const [deletePost] = useMutation(DELETE_POST_MUTATION);
  const [submitComment] = useMutation(SUBMIT_COMMENT_MUTATION);

  const showNotification = (message, type = 'success') => {
    setNotification({ message, type });
    setTimeout(() => {
      setNotification({ message: '', type: '' });
    }, 3000);
  };

  const handleUpdatePost = (postId, newContent) => {
    if (!postId) {
      console.error('Post ID is null or undefined.');
      showNotification('Error updating post. Post ID is undefined.', 'error');
      return;
    }

    updatePost({
      variables: { id: postId, content: newContent },
      optimisticResponse: {
        __typename: 'Mutation',
        updatePost: {
          __typename: 'Post',
          id: postId,
          content: newContent,
        },
      },
    }).then(() => {
      setEditingPostId(null);
      showNotification('Post updated successfully.', 'success');
    }).catch((error) => {
      console.error('Error updating post:', error);
      showNotification('Failed to update post.', 'error');
    });
  };

  const handleDeletePost = (postId) => {
    deletePost({
      variables: { id: postId },
      optimisticResponse: {
        __typename: 'Mutation',
        deletePost: {
          __typename: 'Post',
          id: postId,
        },
      },
      update: (cache) => {
        const existingPosts = cache.readQuery({
          query: GET_POSTS_QUERY,
          variables: { page: page, limit: 10, sortBy: 'creationDate' },
        });

        const newPosts = existingPosts.posts.data.filter(post => post.id !== postId);

        cache.writeQuery({
          query: GET_POSTS_QUERY,
          data: { posts: { ...existingPosts.posts, data: newPosts }},
          variables: { page: page, limit: 10, sortBy: 'creationDate' },
        });
      }
    }).then(() => {
      showNotification('Post deleted successfully.', 'success');
    }).catch((error) => {
      console.error('Error deleting post:', error);
      showNotification('Failed to delete post.', 'error');
    });
  };

  const handleCommentSubmit = (postId) => {
    const content = commentContent[postId] || '';
    const userId = "1"; 

    if (!content.trim()) {
      console.error("Comment content cannot be empty.");
      return;
    }
  
    submitComment({
      variables: {
        content: content,
        postId: postId,
        userId: userId,
      },
      optimisticResponse: {
        __typename: 'Mutation',
        submitComment: {
          __typename: 'Comment',
          id: "-1", 
          content: content,
        },
      },
    }).then(() => {
    }).catch((error) => {
      console.error("Error submitting comment:", error);
    });
  };
  const toggleCommentInput = (postId) => {
    setShowCommentInput(prevState => ({ ...prevState, [postId]: !prevState[postId] }));
  };
  const [showComments, setShowComments] = useState(true);

  if (loading) return <p>Loading...</p>;
if (error) return <p>Error : {error.message}</p>;

return (
  <div className="post-list-container">
    <h2>Posts</h2>
    {notification.message && (
      <div className={`notification ${notification.type}`}>
        {notification.message}
      </div>
    )}
    <ul className="post-list">
      {data.posts.data.map((post) => (
        <li key={post.id} className="post-item card">
          {editingPostId === post.id ? (
            <textarea
              className="post-content-editable"
              value={newContent}
              onChange={(e) => setNewContent(e.target.value)}
            />
          ) : (
            <div className="post-content">{post.content}</div>
          )}
          <div className="post-actions">
            {editingPostId === post.id ? (
              <button
                className="icon-button"
                onClick={() => handleUpdatePost(post.id, newContent)}
              >
                <i className="fas fa-check"></i>
              </button>
            ) : (
              <>
                <button
                  className="icon-button"
                  onClick={() => {
                    setEditingPostId(post.id);
                    setNewContent(post.content);
                  }}
                >
                  <i className="fas fa-edit"></i>
                </button>
                <button
                  className="icon-button"
                  onClick={() => handleDeletePost(post.id)}
                >
                  <i className="fas fa-trash-alt"></i>
                </button>
              </>
            )}
            <div className="comment-section">
              <input
                type="text"
                placeholder="Write a comment..."
                value={commentContent[post.id] || ''}
                onChange={(e) => setCommentContent({ ...commentContent, [post.id]: e.target.value })}
              />
              <button className="action-button" onClick={() => handleCommentSubmit(!showComments)}>
  <i className="fa fa-comment icon"></i> 
</button>
            </div>
          </div>
        
          <ul className="comments-list">
            {
            post.comments && post.comments.map((comment) => (

              <li key={comment.id} className="comment-item">
                {comment.content} 
              </li>
            ))}
          </ul>
        </li>
      ))}
    </ul>
    <div className="pagination">
      <button onClick={() => setPage(Math.max(0, page - 1))} className="page-item">Previous</button>
      <button onClick={() => setPage(page + 1)} className="page-item">Next</button>
    </div>
  </div>
);
            }
export default PostsList;
