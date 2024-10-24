import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.css';


const App = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchUsers = async () => {
            const response = await fetch('/user');
            const data = await response.json();
            setUsers(data);
        };

        fetchUsers();
    }, []);

    return (
        <div className="container">
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <a className="navbar-brand" href="#">User App</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item active">
                            <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Users</a>
                        </li>
                    </ul>
                </div>
            </nav>

            {/* User List */}
            <h1 className="mt-4">User List</h1>
            <ul className="list-group">
                {users.map(user => (
                    <li className="list-group-item" key={user.id}>{user.name}</li>
                ))}
            </ul>

            {/* Bootstrap Button */}
            <button className="btn btn-primary mt-3">Click me</button>
        </div>
    );
};

export default App;
