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
todo

### Contribution
todo

### Known issues
[![GitHub issues open](https://img.shields.io/github/issues/Jmaasy/launch-data-aggregator/shconfparser.svg?maxAge=2592000&style=for-the-badge&logo=appveyor)](https://github.com/Jmaasy/launch-data-aggregator/issues)

### Improvements
- DONE | Cache the launches when converted instead of when requested
- DONE | Remove the 2 caches and implement only 1 cache which contains the total data of the next 50 launches
- DONE | Add 2 cronjobs to 
	- Update the next 50 launches in the cache
	- Validate the launch time each hour (when in last hour each minute) on the date of launch
- Look into GraphQL
- Add routing to return the daily launches
- Add sorting

### Dependencies
Spacex data API: https://github.com/r-spacex/SpaceX-API
LaunchLibrary API: http://launchlibrary.net/docs/1.4.1/api.html
