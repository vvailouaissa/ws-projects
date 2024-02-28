import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CreateUser from './components/CreateUser';
import UpdateUser from './components/UpdateUser';
import DeleteUser from './components/DeleteUser';
import UsersList from './components/UsersList';
import CreatePost from './components/CreatePost';
import UpdatePost from './components/UpdatePost';
import DeletePost from './components/DeletePost';
import PostsList from './components/PostsList';
import CreateComment from './components/CreateComment';
import DeleteComment from './components/DeleteComment';
import CommentsList from './components/CommentsList';
import TagsList from './components/TagsList';
import Sidebar from './components/SideBar';
function App() {
  return (
    <Router>
    <div style={{ display: 'flex' }}>
      <Sidebar />
      <div style={{ flexGrow: 1, padding: '20px' }}>
        <Routes>
         
        
        <Route path="/create-user" element={<CreateUser />} />
        <Route path="/update-user/:id" element={<UpdateUser />} />
        <Route path="/delete-user/:id" element={<DeleteUser />} />
        <Route path="/users" element={<UsersList />} />
        
        <Route path="/create-post" element={<CreatePost />} />
        <Route path="/update-post/:id" element={<UpdatePost />} />
        <Route path="/delete-post/:id" element={<DeletePost />} />
        <Route path="/" element={<PostsList />} exact />

        
        <Route path="/create-comment" element={<CreateComment />} />
        <Route path="/delete-comment/:id" element={<DeleteComment />} />
        <Route path="/comments" element={<CommentsList />} />
        
        <Route path="/tags" element={<TagsList />} />
        
        </Routes>
      </div>
    </div>
  </Router>
    
  );
}

export default App;
