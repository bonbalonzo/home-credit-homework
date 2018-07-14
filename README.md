# Homework

Homework


### API

API #1) http://&lt;address&gt;:&lt;port&gt;/weather?cities=&lt;comma separated name of cities&gt;
	- this GET method API will give you the city location, weather, and temperature(by default, it's in celsius)
```
Sample Request: GET http://localhost:8080/weather?cities=london,manila,tokyo,chicago,san francisco,prague

Sample Response:
[
    {
        "location": "London,GB",
        "actual weather": "haze",
        "temperature": "15.41"
    },
    {
        "location": "Manila,PH",
        "actual weather": "broken clouds",
        "temperature": "31.0"
    },
    {
        "location": "Tokyo,JP",
        "actual weather": "scattered clouds",
        "temperature": "33.59"
    },
    {
        "location": "Chicago,US",
        "actual weather": "light rain",
        "temperature": "23.88"
    },
    {
        "location": "San Francisco,US",
        "actual weather": "mist",
        "temperature": "17.09"
    },
    {
        "location": "Prague,CZ",
        "actual weather": "clear sky",
        "temperature": "19.0"
    }
]

```

API #2) http://&lt;address>:&lt;port&gt;/weather/log
	- this POST Method API will log a maximum of five location, weather and temperature of the city in the request body
```
Sample Reqeust: 
	POST: http://localhost:8080/weather/log

	Request Body: 

[
    {
        "location": "London,GB",
        "actual weather": "haze",
        "temperature": "15.41"
    },
    {
        "location": "Manila,PH",
        "actual weather": "broken clouds",
        "temperature": "31.0"
    },
    {
        "location": "Tokyo,JP",
        "actual weather": "scattered clouds",
        "temperature": "33.59"
    },
    {
        "location": "Chicago,US",
        "actual weather": "light rain",
        "temperature": "23.88"
    },
    {
        "location": "San Francisco,US",
        "actual weather": "mist",
        "temperature": "17.09"
    },
    {
        "location": "Prague,CZ",
        "actual weather": "clear sky",
        "temperature": "19.0"
    }
]

Sample Response:
[
    {
        "location": "Manila,PH",
        "actual weather": "broken clouds",
        "temperature": "31.0"
    },
    {
        "location": "Tokyo,JP",
        "actual weather": "scattered clouds",
        "temperature": "33.59"
    },
    {
        "location": "Chicago,US",
        "actual weather": "light rain",
        "temperature": "23.88"
    },
    {
        "location": "San Francisco,US",
        "actual weather": "mist",
        "temperature": "17.09"
    },
    {
        "location": "Prague,CZ",
        "actual weather": "clear sky",
        "temperature": "19.0"
    }
]

```
