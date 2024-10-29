import React, { useState, useEffect } from 'react';
import '../styles/event.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Row, Col } from 'react-bootstrap';
import EventCard from './EventCard'; // Import komponentu EventCard
import { useNavigate } from 'react-router-dom';
import { Card, Button } from 'react-bootstrap';


const Events = () => {
    const [events, setEvents] = useState([]); 
    const [loading, setLoading] = useState(true); 
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    // getEvents from backend
    const fetchEvents = async (page = 1) => {
        console.log("Fetching events for page:", page); 
        try {
            const response = await fetch(`${process.env.REACT_APP_BASE_URL}/event/all/${page}`);
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


    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div className="container text-center">
            <h2 className="mb-4">Event List</h2>
            <Row>
                {events.content.map((event, index) => (
                    <Col key={index} sm={12} md={6} lg={4} className="mb-4">
                        <EventCard event={event} /> {/* Użycie komponentu EventCard */}
                    </Col>
                ))}
            </Row>
        </div>
    );
};

export default Events;
