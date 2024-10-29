import React from 'react';

const ErrorComponent = ({ error }) => {
    if (!error) return null;

    return (
        <div style={{ color: 'red' }}>
            <p>Błąd: {error}</p>
        </div>
    );
};

export default ErrorComponent;