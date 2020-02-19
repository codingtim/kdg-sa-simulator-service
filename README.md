# Simulator service

## Requests

Start a sensor simulation for load test.

    curl -XPOST -H "Content-Type: application/json" -XPOST -d '{"duration": "PT15M","delay": 2000,"delayVariation": 150,"delayType":"NO_DELAY","locationConfiguration": {"latitudeConfiguration": {"lowerBound": 51.2012806,"upperBoundExclusive": 51.2064643},"longitudeConfiguration": {"lowerBound": 4.5827843,"upperBoundExclusive": 4.6048775}},"sensorConfigurations": [{"type": "CO2","lowerBound": 0,"upperBoundExclusive": 150},{"type": "NOx","lowerBound": 0,"upperBoundExclusive": 75}]}' 'http://localhost:8081/api/sensor'

Start a sensor simulation with realtime generation.

    curl -XPOST -H "Content-Type: application/json" -XPOST -d '{"duration": "PT15M","delay": 2000,"delayVariation": 150,"delayType":"REALTIME","locationConfiguration": {"latitudeConfiguration": {"lowerBound": 51.2012806,"upperBoundExclusive": 51.2064643},"longitudeConfiguration": {"lowerBound": 4.5827843,"upperBoundExclusive": 4.6048775}},"sensorConfigurations": [{"type": "CO2","lowerBound": 0,"upperBoundExclusive": 150},{"type": "NOx","lowerBound": 0,"upperBoundExclusive": 75}]}' 'http://localhost:8081/api/sensor'
