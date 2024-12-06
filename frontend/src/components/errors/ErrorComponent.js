import React from 'react';

const ErrorComponent = ({ error }) => (
    <div className="d-flex justify-content-center align-items-center" style={{ height: '100vh' }}>
        <div className="text-center p-3" style={{ color: 'red', border: '1px solid red', borderRadius: '5px' }}>
            <p>Error: {error}</p>
            <p>Please report to admin or try again later</p>
        </div>
    </div>
);

export default ErrorComponent;