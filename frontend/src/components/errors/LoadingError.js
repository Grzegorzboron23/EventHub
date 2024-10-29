import React from 'react';

const LoadingError = ({ loading, error }) => {
    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>; 
    }

    return null;
};

export default LoadingError;