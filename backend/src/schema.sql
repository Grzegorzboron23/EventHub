INSERT INTO Users (id, name, surname, userName, hashedPassword, email, phoneNumber, points)
VALUES
    (1, 'John', 'Doe', 'johndoe', 'hashedPassword1', 'johndoe@example.com', '12345678901', 100),
    (2, 'Jane', 'Smith', 'janesmith', 'hashedPassword2', 'janesmith@example.com', '12345678902', 200),
    (3, 'Alice', 'Johnson', 'alicej', 'hashedPassword3', 'alice.johnson@example.com', '12345678903', 150);


INSERT INTO Event (id, dateRange_startDate, dateRange_endDate, users_id, name, address_street, address_city, address_zipCode)
VALUES
    (1, '2024-08-21', '2024-08-22', 1, 'Tech Conference', '123 Tech Street', 'Tech City', '12345'),
    (2, '2024-09-01', '2024-09-03', 2, 'Music Festival', '456 Music Ave', 'Music Town', '67890'),
    (3, '2024-10-10', '2024-10-12', 3, 'Art Expo', '789 Art Blvd', 'Art City', '11223');
