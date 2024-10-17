import React, { useState, useEffect } from 'react';
import './event.css'



const Events = () => {
    const [events, setEvents] = useState([]); 
    const [loading, setLoading] = useState(true); 
    const [error, setError] = useState(null);

    const fetchEvents = async (page = 1) => {
        console.log("Fetching events for page:", page); 
        try {
          const response = await fetch(`http://localhost:8080/event/all/${page}`);
          console.log("Response status:", response.status);
          if (!response.ok) {
            throw new Error('Failed to fetch events');
          }
          const data = await response.json();
          console.log("Fetched events:", data);
          setEvents(data); 
          setLoading(false);
        } catch (error) {
            console.error("Error fetching events:", error); 
          setError(error.message); 
          setLoading(false); 
        }
      };

      useEffect(() => {
        fetchEvents();
      }, []);

    return (
        <div>
          <h2>Event List</h2>
          <ul>
            {events.map((event, index) => (
              <li key={index}>
                <h3>{event.eventName}</h3>
                <ul>{event.eventDescription}</ul>
              </li>
            ))}
          </ul>
        </div>
      );
};

export default Events;