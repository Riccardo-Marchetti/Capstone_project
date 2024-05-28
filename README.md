# Capstone Project - Dream Cinema
This is my final full-stack project for the Epicode course!
I have created an application for managing a cinema, allowing users to book tickets for various available screenings and save them in their profile.

## Main functions
- Register/Login with your credentials to navigate the site.  
- An always updated Home page with the last movies in theaters.
There are 8 films showing in theaters, and another 8 in the "Coming Soon" section.  
- A promotions page to view the current offers available at the cinema.  
- Watch the trailer of each film with a click on ‘watch trailer’ on the film’s poster.  
- Go to the details of each film with a click on ‘book ticket’ on the posters of the films being screened and on ‘show details’ for those coming soon.    
- Add a review. On the details page, you can view reviews from other users and create new reviews.  
- Book a ticket. Also on the details page, you have the option to book one or more tickets for that film, selecting the cinema, city, date, and time.  
- Select seats. After choosing the show on the details page, you will be redirected to the seat selection page where you can select one or more seats if they are available. <br /> Each room is made up of 64 seats, and there are two types of seats: classic (€10 ) and premium (€20).  
- After payment, you will be redirected to the profile page where you can see your personal information and booked tickets.
## Screenshots
### Login Page
![Screenshot (70)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/76d73c9f-abb2-4419-8780-f687670a24bd)
### Home Page
![Screenshot (71)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/e8b6ee73-3d82-482d-9139-99ebce31af4f)
![Screenshot (72)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/15c473ae-6814-4d38-96d2-8a64bdbf9fff)
![Screenshot (74)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/6844f9cf-6771-4396-880e-16c98003c87a)
### Promotions Page
![Screenshot (73)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/3a99868e-038a-41f2-aa85-c1342f2b464c)
### Details Page
![Screenshot (80)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/d1bf3dd3-ca11-4333-934f-056d4c83f912)
![Screenshot (81)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/1675e71e-a44d-459c-8f14-32049cfa5e57)

### Book Seat Page
![Screenshot (77)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/808ee5e4-1527-4ed7-9d48-370b0a32b6be)
### Profile Page
![Screenshot (78)](https://github.com/Riccardo-Marchetti/Capstone_project/assets/151193857/be97a7f4-064a-4158-8ff2-e07c764ef290)



## Technologies
For this project, I have used many of the technologies learned in the past 6 months of the course. <br /> Here is a list of what I have used:  
- For backend development, I used Java with the Spring Boot framework. Its libraries allowed me to create an efficient and secure backend, enabling me to manage user registration to protect their credentials. <br /> At the time of access, thanks to JWT (JSON Web Tokens) authentication, a token will be generated and saved to securely authenticate users. <br /> I managed the API requests for the front-end so that various endpoints are available for the administrator and moderators so that they can make all available requests, while not all are available for users to ensure safe management of the application.  
- To manage the databadse and the data flow I used postgreSQL.  
- For front-end development, I used React with the Redux library to manage the global state of the application. I used it for ticket booking, saving all necessary data in the Redux state. <br /> To manage the URLs of the pages and navigate between them, I used react-router-dom. It was useful for passing the movie ID from the homepage to the details page, using it to make requests to the back-end to manage ticket reservations. <br /> For styling, I used the Bootstrap library, which allowed me to easily manage the layout of elements and the responsive part of the application.
## How to clone
**Front-end:**
1. First of all make sure to have Node.js on your system.
2. Clone the front-end repository here: https://github.com/Riccardo-Marchetti/FRONT-END.
3. Enter in the frontend folder.
4. Install all the dependencies with `$ npm i`.
5. Start the application with `$ npm run dev`.

**Back-end:**
1. Make sure to have Java and Maven on your system.
2. Enter in the backend folder.
3. Remember to update the application.proprieties file with your database credentials and stuff.
4. Start the application with `$ mvn spring-boot:run` and then click "ok".
