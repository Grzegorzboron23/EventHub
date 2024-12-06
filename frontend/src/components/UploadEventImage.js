import React, { useState } from 'react';
import axios from 'axios';

const UploadEventImages = () => {
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [eventId, setEventId] = useState('');
    const [uploadStatus, setUploadStatus] = useState(null);

    const handleFileChange = (event) => {
        setSelectedFiles(event.target.files);
    };

    const handleEventIdChange = (event) => {
        setEventId(event.target.value);
    };

    const handleUpload = async () => {
        if (!eventId) {
            alert("Please enter a valid event ID!");
            return;
        }

        if (selectedFiles.length === 0) {
            alert("Please select at least one file!");
            return;
        }

        const formData = new FormData();
        for (const file of selectedFiles) {
            formData.append("files", file);
        }
        formData.append("eventId", eventId);

        try {
            const response = await axios.post(
                `${process.env.REACT_APP_BASE_URL}/uploads/events/images`,
                formData,
                {
                    headers: {
                        "Content-Type": "multipart/form-data",
                    },
                }
            );
            setUploadStatus(`Images uploaded successfully for event: ${response.data.name}`);
        } catch (error) {
            console.error("Error uploading files:", error);
            setUploadStatus("Error uploading files. Please try again.");
        }
    };

    return (
        <div className="upload-event-images">
            <h3>Upload Images for Event</h3>
            <div className="mb-3">
                <label htmlFor="eventId" className="form-label">Event ID:</label>
                <input
                    type="text"
                    id="eventId"
                    className="form-control"
                    value={eventId}
                    onChange={handleEventIdChange}
                />
            </div>

            <div className="mb-3">
                <label htmlFor="fileInput" className="form-label">Select Images:</label>
                <input
                    type="file"
                    id="fileInput"
                    className="form-control"
                    multiple
                    accept="image/*"
                    onChange={handleFileChange}
                />
            </div>

            <button onClick={handleUpload} className="btn btn-primary">Upload</button>

            {uploadStatus && <p className="mt-3">{uploadStatus}</p>}
        </div>
    );
};

export default UploadEventImages;
