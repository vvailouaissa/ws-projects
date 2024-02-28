import React from 'react';
import { Link } from 'react-router-dom';
import { Drawer, List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import HomeIcon from '@mui/icons-material/Home';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import PostAddIcon from '@mui/icons-material/PostAdd';
import CommentIcon from '@mui/icons-material/Comment';
import LabelIcon from '@mui/icons-material/Label';

function Sidebar() {
  return (
    <Drawer
      variant="permanent"
      anchor="left"
    >
      <List>
        <ListItem button component={Link} to="/">
          <ListItemIcon><HomeIcon /></ListItemIcon>
          <ListItemText primary="Posts" />
        </ListItem>
        
        <ListItem button component={Link} to="/users">
          <ListItemIcon><AccountBoxIcon /></ListItemIcon>
          <ListItemText primary="Users" />
        </ListItem>
        
        <ListItem button component={Link} to="/create-post">
          <ListItemIcon><PostAddIcon /></ListItemIcon>
          <ListItemText primary="Create Post" />
        </ListItem>

        <ListItem button component={Link} to="/create-user">
          <ListItemIcon><PostAddIcon /></ListItemIcon>
          <ListItemText primary="Create User" />
        </ListItem>
        
        
        <ListItem button component={Link} to="/comments">
          <ListItemIcon><CommentIcon /></ListItemIcon>
          <ListItemText primary="Comments" />
        </ListItem>
        
        <ListItem button component={Link} to="/tags">
          <ListItemIcon><LabelIcon /></ListItemIcon>
          <ListItemText primary="Tags" />
        </ListItem>

      </List>
    </Drawer>
  );
}

export default Sidebar;
