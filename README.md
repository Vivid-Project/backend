# Vivid Project Backend

<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![Build Status](https://travis-ci.com/Vivid-Project/backend.svg?branch=main)](https://travis-ci.com/Vivid-Project/backend)
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

To properly use this application you will need to set up and configure three repositories. Follow the *Configuration* directions in each repository to get Vivd running locally! Alternatively, check out the production application [here](https://vivid-project.herokuapp.com/)!  Heroku puts some apps to sleep after a while; if loading the home page fails or times out, refreshing the page should be enough to see the app in production.

## Prerequisites
These setup instructions are for Mac OS.

### Configuration
  * Fork and clone this repo into the directory that will house the Frontend or Microservice repositories.
  * Install the Maven Command Line tool with Homebrew by entering `brew install maven`
  * Be sure to have PostgreSQL installed on your machine and create a new database titled `vivid`

### Usage
  * Run the server from the command line by entering `mvn spring-boot:run`.  This should seed the Postgres database with prepackaged seed data and allow you to make API calls from a tool such as [Postman](https://www.postman.com/).

#### API Endpoints

##### Authenticate User
Request:
```
Uri: POST /users/authenticate

{
    "email": "adrew@example.com",
    "password": "password"
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

##### Get user dreams
Request:
```
Uri: GET /dreams

Header: "Authorization": "Bearer <token>"
```
Response:
```
[
    {
        "id": 551,
        "date": "02/22/2021",
        "title": "Weird Dream",
        "description": "This was very weird",
        "emotion": "Fun",
        "themes": [],
        "toneAnalysis": {
          "tone_strength": {
                "Tentative": 1
            },
            "unique_tones": "Tentative"
        }
    }
    {
        ...
    }
]
```

##### Get user dreams by date range

Request:
```
Uri: GET /dreams

Header: "Authorization": "Bearer <token>"

Params(required): dateStart, dateEnd
```
Response:
```
[
    {
        "id": 3,
        "date": "2021-02-23",
        "title": "Cool Dream",
        "description": "This is a good dream",
        "emotion": "Happy",
        "themes": [],
        "toneAnalysis": {
          "tone_strength": {
                "Tentative": 1
            },
            "unique_tones": "Tentative"
        }
    },
    {
        "id": 4,
        "date": "2021-02-24",
        "title": "Bad Dream",
        "description": "This was scary",
        "emotion": "Bad",
        "themes": [],
        "toneAnalysis": {
          "tone_strength": {
                "Joy": 1
            },
            "unique_tones": "Joy"
        }
    },
    {
        ...
    }
]
```

##### Get single user dream
Request:
```
Uri: GET /dreams/<dreamId>

Header: "Authorization": "Bearer <token>"
```
Response:
```
{
    "id": 579,
    "user": {
        "id": 576,
        "name": "Ava Drew",
        "email": "adrew@example.com"
    },
    "date": "02/22/2021",
    "title": "Weird Dream",
    "description": "This was very weird",
    "emotion": "Fun",
    "themes": [],
    "toneAnalysis": {
        "tone_strength": {
            "Tentative": 1
        },
        "unique_tones": "Tentative"
    }
}
```

##### Create a user dream
Request:
```
Uri: POST /dreams

Header: "Authorization": "Bearer <token>"

Raw JSON Body:

{
    "date": "02/25/2021",
    "title": "Train dream",
    "description": "I was on a train",
    "emotion": "Weird"
}
```

Response:
```
{
    "id": 146,
    "date": "02/25/2021",
    "title": "Train dream",
    "description": "I was on a train",
    "emotion": "Weird",
    "themes": [],
    "toneAnalysis": {
        "tone_strength": {
            "Tentative": 1
        },
        "unique_tones": "Tentative"
    }
}
```

##### Create user
Request:
```
URI: POST /users

Raw JSON Body:

{
    "name": "Alex",
    "email": "alex@example.com",
    "password": "password123"
}
```
Response:
```
{
    "id": 14,
    "name": "Alex",
    "email": "alex@example.com",
    "token": "K157xIzmC9WBc7O4Rd6Ge2PV_jiwyfvN"
}
```





### Testing


<!-- ROADMAP -->
## Roadmap

See [Open Issues](https://github.com/Vivid-Project/backend/issues) or visit our [Project Board](https://github.com/orgs/Vivid-Project/projects/1) for a list of proposed features, known issues, and project extensions.


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
