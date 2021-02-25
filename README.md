# Vivid Project Backend

<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
<!--   Add in badge :  [![Build Status] -->


  <h3 align="center">Vivid Backend</h3>

  <p align="center">
    This is the backend API service for the Vivid app.
    <br />
    <br />
    <!-- for adding a demo video
    <a href="Add our video link here">View Demo</a>  · -->
    ·
    <a href="https://github.com/Vivid-Project/backend/issues">Report Bug</a>
    ·
    <a href="https://github.com/Vivid-Project/backend/issues">Request Feature</a>
  </p>
</p>




<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Configuration](#configuration)
  * [Testing](#testing)
  * [Usage](#usage)
* [Contributing](#contributing)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)




<!-- ABOUT THE PROJECT -->
## About The Project
Please visit the [frontend](https://https://github.com/Vivid-Project/frontend) repository of this project and check out the readme there for a more in depth look at this project!

This backend repository was built to provide CRUD endpoints to the front end for users, dreams, and themes. This backend also provides as the middleman between the frontend and the [Tone Analyzer Microservice](https://github.com/Vivid-Project/microservice). This repository saves, updates, retrieves and deletes user information, dream logs, and dream themes in the PostgreSQL database.  When a user saves a dream journal entry, a request will be issued to the tone analyzer microservice containing a raw text body holding that journal entry. After analysis, the list of tones and their counts are transformed and saved to the database.

To view all the repositories associated with Vivid, please visit [Vivid-Project](https://github.com/Vivid-Project)

### Built With


* [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Spring Boot](https://spring.io/projects/spring-boot)


<!-- GETTING STARTED -->
## Getting Started

To properly use this application you will need to set up and configure three repositories. Follow the *Configuration* directions in each repository to get Vivd running locally! Alternatively, check out the production application [here](put front end heroku here)!!!

## Prerequisites
These setup instructions are for Mac OS.

### Configuration


### Usage

#### API Endpoints

##### Authenticate User
Request:
```
Uri: POST /users/authenticate

{
    "email": "adrew@example.com"
}
```
Response:
```
{
    "id": 345,
    "name": "Ava Drew",
    "email": "adrew@example.com",
    "token": "a6t9dvJW424Mw3BRHTfE5qiZg67-kXk_"
}
```

##### Get user data
Request:
```
Uri: GET /user

Header: "Authorization": "Bearer <token>"
```
Response:
```
{
    "id": 450,
    "name": "Ava Drew",
    "email": "adrew@example.com"
}
```


### Testing


<!-- ROADMAP -->
## Roadmap

See [Open Issues](https://github.com/Vivid-Project/microservice/issues) or visit our [Project Board](https://github.com/orgs/Vivid-Project/projects/1) for a list of proposed features, known issues, and project extensions.


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make this community such an amazing and fun place to learn, grow, and create! Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch ```git checkout -b feature/NewGreatFeature```
3. Commit your Changes ```git commit -m 'Add some NewGreatFeature'```
4. Push to the Branch ```git push origin feature/NewGreatFeature```
5. Open a new Pull Request!


<!-- CONTACT -->
## Contact

Amanda Davidson &nbsp;- [![LinkedIn][linkedin-shield]](https://www.linkedin.com/in/amanda-davidson02/) - [GitHub](https://github.com/ADavidson02) - [Turing Alum Profile](https://alumni.turing.io/alumni/amanda-davidson)

Shawn Truesdale &nbsp;&nbsp;&nbsp;- [![LinkedIn][linkedin-shield]](https://www.linkedin.com/in/shawntruesdale/) - [GitHub](https://github.com/Shawntru)

Jonathan Wilson &nbsp;&nbsp;&nbsp;&nbsp;- [![LinkedIn][linkedin-shield]](https://www.linkedin.com/in/jonathan--wilson/) - [GitHub](https://github.com/Jonathan-M-Wilson) - [Turing Alum Profile](https://alumni.turing.io/alumni/jonathan-wilson)

Zach Stearns &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- [![LinkedIn][linkedin-shield]](https://www.linkedin.com/in/zach-stearns/) - [GitHub](https://github.com/Stearnzy) - [Turing Alum Profile](https://alumni.turing.io/alumni/zach-stearns)

Aidan Murray &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- [![LinkedIn][linkedin-shield]](https://www.linkedin.com/in/aidan-murray-teknoserval/) - [GitHub](https://github.com/TeknoServal)

Taylor Phillips &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- [![LinkedIn][linkedin-shield]](https://www.linkedin.com/in/taphill/) - [GitHub](https://github.com/taphill) - [Turing Alum Profile](https://alumni.turing.io/alumni/taylor-phillips)




Project Link: [Vivid](https://github.com/Vivid-Project)


<!-- ACKNOWLEDGEMENTS -->
<!-- Add resources that were used to help create this project here -->


<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/Vivid-Project/backend
[contributors-url]: https://github.com/Vivid-Project/backend/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Vivid-Project/backend
[forks-url]: https://github.com/Vivid-Project/backend/network/members
[stars-shield]: https://img.shields.io/github/stars/Vivid-Project/backend
[stars-url]: https://github.com/Vivid-Project/backend/stargazers
[issues-shield]: https://img.shields.io/github/issues/Vivid-Project/backend
[issues-url]: https://https://github.com/Vivid-Project/backend/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
