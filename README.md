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

### Endpoints
#### Get launches with all data
* **URL**
  https://JOHNDOE.com/api/v1/launch
* **Method:**
  `GET`
* **URL Params**
  **Optional:**
  `id=[integer]`
* **Success Response:**
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
  
#### Get launches essential only
* **URL**
  https://JOHNDOE.com/api/v1/launch/minimal
* **Method:**
  `GET`
* **URL Params**
  **Optional:**
  `id=[integer]`
* **Success Response:**
```json
   {
       "totalCount": 1,
       "launches": [
           {
               "id": 1962,
               "name": "Falcon 9 Block 5 | Starlink 2",
               "orbit": [
                   "VLEO"
               ],
               "net": "2020-01-07T02:19:00",
               "agency": {
                   "name": "SpaceX",
                   "short": "SpX",
                   "type": 3,
                   "location": "USA",
                   "lsp": false
               }
           }
       ]
   }
```

### Usage
todo

### Known issues
[![GitHub issues open](https://img.shields.io/github/issues/EverydayAstronaut/launch-data-aggregator?style=for-the-badge)](https://img.shields.io/github/issues/EverydayAstronaut/launch-data-aggregator?style=for-the-badge)
 
### Improvements
- **DONE** | Cache the launches when converted instead of when requested
- **DONE** | Remove the 2 caches and implement only 1 cache which contains the total data of the next 50 launches
- **DONE** | Add 2 cronjobs to 
	- Update the next 50 launches in the cache
	- Validate the launch time each hour (when in last hour each minute) on the date of launch
- **DONE** | Add routing to return the daily launches
- When not a spacex launch, attempt to find orbit ( only default present on spacex launches ATM )
- **WILL MAYBE IMPLEMENTED** | sorting
- **WILL MAYBE IMPLEMENTED** | Add timezone to request which will automatically convert the launchtime to the users timezone
- **WILL MAYBE IMPLEMENTED** | When spacex launch compare the 2 descriptions and return the most detailed
- **WILL MAYBE IMPLEMENTED** | Look into GraphQL

### Dependencies
- Spacex data API: https://github.com/r-spacex/SpaceX-API
- LaunchLibrary API: http://launchlibrary.net/docs/1.4.1/api.html
