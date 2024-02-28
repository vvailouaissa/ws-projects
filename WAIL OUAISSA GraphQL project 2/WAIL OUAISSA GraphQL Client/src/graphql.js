import { gql } from '@apollo/client';

export const GET_USERS_QUERY = gql`
  query GetUsers($page: Int, $limit: Int) {
    users(page: $page, limit: $limit, sortBy: "registrationDate") {
      data {
        id
        firstName
        lastName
        email
        registrationDate
      }
      total
      page
      limit
    }
  }
`;

export const GET_USER_BY_ID_QUERY = gql`
  query GetUserById($id: ID!) {
    user(id: $id) {
      id
      firstName
      lastName
      email
      registrationDate
    }
  }
`;

export const CREATE_USER_MUTATION = gql`
  mutation CreateUser($firstName: String!, $lastName: String!, $email: String!) {
    createUser(firstName: $firstName, lastName: $lastName, email: $email) {
      id
      firstName
      lastName
      email
    }
  }
`;

export const UPDATE_USER_MUTATION = gql`
  mutation UpdateUser($id: ID!, $firstName: String, $lastName: String) {
    updateUser(id: $id, firstName: $firstName, lastName: $lastName) {
      id
      firstName
      lastName
      email
    }
  }
`;

export const DELETE_USER_MUTATION = gql`
  mutation DeleteUser($id: ID!) {
    deleteUser(id: $id)
  }
`;

export const GET_POSTS_QUERY = gql`
  query GetPosts($page: Int, $limit: Int) {
    posts(page: $page, limit: $limit, sortBy: "creationDate") {
      data {
        id
        content
        creationDate
        user {
          id
          firstName
          lastName
        }
      }
      total
      page
      limit
    }
  }
`;

export const GET_POSTS_BY_USER_QUERY = gql`
  query GetPostsByUser($userId: ID!, $page: Int, $limit: Int) {
    postsByUser(userId: $userId, page: $page, limit: $limit, sortBy: "creationDate") {
      data {
        id
        content
        creationDate
      }
      total
      page
      limit
    }
  }
`;

export const GET_POSTS_BY_TAG_QUERY = gql`
  query GetPostsByTag($tag: String!, $page: Int, $limit: Int) {
    postsByTag(tag: $tag, page: $page, limit: $limit, sortBy: "creationDate") {
      data {
        id
        content
        creationDate
      }
      total
      page
      limit
    }
  }
`;

export const GET_POST_BY_ID_QUERY = gql`
  query GetPostById($id: ID!) {
    post(id: $id) {
      id
      content
      creationDate
      user {
        id
        firstName
        lastName
      }
    }
  }
`;

export const CREATE_POST_MUTATION = gql`
  mutation CreatePost($userId: ID!, $content: String!) {
    createPost(userId: $userId, content: $content) {
      id
      content
      user {
        id
        firstName
        lastName
      }
    }
  }
`;

export const UPDATE_POST_MUTATION = gql`
  mutation UpdatePost($id: ID!, $content: String!) {
    updatePost(id: $id, content: $content) {
      id
      content
    }
  }
`;

export const DELETE_POST_MUTATION = gql`
  mutation DeletePost($id: ID!) {
    deletePost(id: $id)
  }
`;

export const GET_TAGS_QUERY = gql`
  query GetTags {
    tags {
      id
      name
    }
  }
`;

export const SUBMIT_COMMENT_MUTATION = gql`
mutation SubmitComment($content: String!, $postId: ID!, $userId: ID!) {
  createComment(content: $content, postId: $postId, userId: $userId) {
    id
    content
    creationDate
    user {
      id
      firstName
      lastName
      email
    }
    post {
      id
      content
    }
  }
}

`;


