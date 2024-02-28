import React from 'react';
import { useQuery } from '@apollo/client';
import { GET_TAGS_QUERY } from '../graphql/tagQueries';

function TagsList() {
  const { data, loading, error } = useQuery(GET_TAGS_QUERY);

  if (loading) return <p>Loading tags...</p>;
  if (error) return <p>Error : {error.message}</p>;

  return (
    <ul>
      {data.tags.map((tag) => (
        <li key={tag.id}>{tag.name}</li>
      ))}
    </ul>
  );
}

export default TagsList;
