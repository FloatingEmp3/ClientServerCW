## Overview
This project is a RESTful API built using JAX-RS for managing rooms, sensors, and sensor readings in a smart campus system.

## How to run
1. Open the project in NetBeans
2. Run ClientServerCW.java
3. Server will start at http://localhost:8080/api/v1

# Get all rooms
curl http://localhost:8080/api/v1/rooms

# Create a room
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id":"R1","name":"Lab A","capacity":30}'

# Create a sensor (valid)
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":"S1","type":"CO2","status":"ACTIVE","currentValue":0,"roomId":"R1"}'

# Get sensors with filtering
curl "http://localhost:8080/api/v1/sensors?type=CO2"

# Add a sensor reading
curl -X POST http://localhost:8080/api/v1/sensors/S1/readings \
-H "Content-Type: application/json" \
-d '{"id":"READ1","timestamp":1713984000000,"value":22.5}'

# ERROR CASE: Create sensor with invalid room (should return 422)
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":"S999","type":"CO2","status":"ACTIVE","currentValue":0,"roomId":"FAKE"}'

# ERROR CASE: Delete room with sensors (should return 409)
curl -X DELETE http://localhost:8080/api/v1/rooms/R1