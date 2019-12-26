[![](https://i.imgur.com/rg6nA9k.png)](https://i.imgur.com/rg6nA9k.png)
# Launch Data Aggregator

### Features
- Caching of all spacex launch-data
- Caching of launchlibrary data
	- Launch
	- Agency
	- Mission
- Aggregating of spacex and launchlibrary data
- Return only the essential data

### Usage
todo

### Contribution
todo

### Known issues
[![GitHub issues open](https://img.shields.io/github/issues/Jmaasy/launch-data-aggregator/shconfparser.svg?maxAge=2592000&style=for-the-badge&logo=appveyor)](https://github.com/Jmaasy/launch-data-aggregator/issues)

### Improvements
- Cache the launches when converted instead of when requested
- Look into GraphQL
- Add 2 cronjobs to 
	- Update the next 50 launches in the cache
	- Validate the launch time each hour (when in last hour each minute) on the date of launch
