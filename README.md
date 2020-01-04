[![](https://i.imgur.com/rg6nA9k.png)](https://i.imgur.com/rg6nA9k.png)
# Launch Data Aggregator

### Features
- Caching of the combined launch-data (Spacex, LaunchLibrary)
- Aggregating of spacex and launchlibrary data
- Return single launch data
- Return next 50 launches
- Able to return minified data with only the essential data.
- Cronjob to fetch the next 50 launches aggregate them and update the cache
- Cronjob which checks the daily launches
- Cronjob which validates the launch time

### Usage
#### Endpoints
- https://JOHNDOE.com/api/v1/launch (id: Int **optional**)
- https://JOHNDOE.com/api/v1/launch-minimal (id: Int **optional**)

#### Return model example
```json
{
    "totalCount": 50,
    "launches": [
        {
            "id": 1962,
            "name": "Falcon 9 Block 5 | Starlink 2",
            "net": "2020-01-07T02:19:00",
            "netStatus": 1,
            "location": {
                "name": "Cape Canaveral, FL, USA",
                "countryCode": "USA",
                "pad": {
                    "id": 84,
                    "name": "Space Launch Complex 40, Cape Canaveral, FL",
                    "latitude": "28.56194122",
                    "longitude": "-80.57735736"
                }
            },
            "missions": [
                {
                    "id": 1229,
                    "name": "Starlink 2",
                    "description": "A batch of 60 satellites for Starlink mega-constellation - SpaceX's project for space-based Internet communication system.",
                    "orbit": "VLEO",
                    "company": ""
                }
            ],
            "rocket": {
                "id": 188,
                "name": "Falcon 9 Block 5",
                "family": "Falcon",
                "configuration": "9 Block 5",
                "country": "",
                "cores": [
                    {
                        "serial": "B1049",
                        "flightNo": 4,
                        "block": 5,
                        "gridFin": true,
                        "legs": true
                    }
                ],
                "agency": {
                    "name": "SpaceX",
                    "short": "SpX",
                    "type": 3,
                    "location": "USA",
                    "lsp": false
                },
                "image": {
                    "url": "https://s3.amazonaws.com/launchlibrary/RocketImages/Falcon9Block5.jpg_1920.jpg",
                    "sizes": [
                        320,
                        480,
                        640,
                        720,
                        768,
                        800,
                        960,
                        1024,
                        1080,
                        1280,
                        1440,
                        1920
                    ]
                }
            },
            "recovery": {
                "fairing": {
                    "serial": "",
                    "attempt": true,
                    "onShip": false,
                    "location": [
                        "unknown"
                    ],
                    "recovered": false
                },
                "cores": [
                    {
                        "serial": "B1049",
                        "attempt": true,
                        "onShip": true,
                        "location": [
                            "OCISLY"
                        ],
                        "recovered": false
                    }
                ]
            }
        }
    ]
}
```

### Known issues
[![GitHub issues open](https://img.shields.io/github/issues/Jmaasy/launch-data-aggregator/shconfparser.svg?maxAge=2592000&style=for-the-badge&logo=appveyor)](https://github.com/Jmaasy/launch-data-aggregator/issues)

### Improvements
- **DONE** | Cache the launches when converted instead of when requested
- **DONE** | Remove the 2 caches and implement only 1 cache which contains the total data of the next 50 launches
- **DONE** | Add 2 cronjobs to 
	- Update the next 50 launches in the cache
	- Validate the launch time each hour (when in last hour each minute) on the date of launch
- Look into GraphQL
- Add routing to return the daily launches
- Add sorting
- The recovery info of the fairing and cores are the same which results in the fairing having a core serial which is not needed. Should be something to look into

### Dependencies
- Spacex data API: https://github.com/r-spacex/SpaceX-API
- LaunchLibrary API: http://launchlibrary.net/docs/1.4.1/api.html
