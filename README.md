# FindMyRoomie

FindMyRoomie is a Java-based room rental application that connects property owners with seekers. The platform allows hosts to list available rooms with details, while seekers can browse listings and apply for suitable accommodations. The application uses Java Swing for the user interface and MySQL for backend data storage and management.



# Features
User roles: Host and Seeker
Room listing with details such as description, rent, address, and amenities
Graphical User Interface built using Java Swing
View available rooms
Apply for rooms directly through the platform
Manage applications
MySQL database integration using JDBC
Tech Stack
Java (Core Java, JDBC)
Java Swing (GUI)
MySQL
VS Code (Development Environment)
Setup Instructions


## Clone the repository:

git clone <https://github.com/Sneha07k/Online-Rental-Service>


## Navigate to the project folder:

cd FindMyRoomie


## Run the SQL schema file to create the database:

mysql -u root -p < schema.sql


## Compile the Java files:

javac -d . *.java


## Run the application:

java -cp ".;mysql-connector-j-8.0.33.jar" findmyroomie.MainFrame



## Screenshots

### Login Page
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/89d24505-f2ac-4720-bec9-d4debd7e8684" />



### Room Listings
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/27409ebf-cbb5-4550-8255-dc8831f6c19d" />



### Browse rooms
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/6ec720a9-b092-47f7-8256-505d44c61be6" />



### Application requests
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/eb2e2b3d-551a-4f70-bc30-f8a2922c0c8d" />



### Roommate dashboard
<img width="958" height="540" alt="image" src="https://github.com/user-attachments/assets/034ac75e-3318-4bc4-aff1-1879d774d654" />





## Future Improvements
Add authentication and password encryption

Improve UI/UX of Swing interface

Add search and filtering options

Convert to a web-based application
