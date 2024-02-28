import { ApolloClient, InMemoryCache, HttpLink } from "@apollo/client";

const httpLink = new HttpLink({
  uri: 'http://localhost:8080/graphql', 
  headers: {
    'Content-Type': 'application/json',
    mode: 'no-cors'
  }
});

const client = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache(),
});

export default client;
