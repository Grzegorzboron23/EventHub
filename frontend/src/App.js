import React, { useState, useEffect } from 'react';

function listGroup(){
    let items = ["Senegal", "RPA", "EGIPT"];
}

const App = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        // Symulowane pobieranie danych użytkowników
        const fetchUsers = async () => {
            const response = await fetch('/user'); // Upewnij się, że twój backend działa i obsługuje to zapytanie
            const data = await response.json();
            setUsers(data);
        };

        fetchUsers();
    }, []);

    return (
        <div>
            <h1>User List</h1>
            <ul>
                {users.map(user => (
                    <li key={user.id}>{user.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default App;
