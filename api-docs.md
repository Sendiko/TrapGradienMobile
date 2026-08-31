# Cloud Gradien API Documentation

Welcome to the official API documentation for Cloud Gradien (Just Cloud It!), designed for TrapGradien and other Gradien devices.

## Overview

- Base URL: \[https://cloud.gradien.my.id/api\](https://cloud.gradien.my.id/api)
- Content-Type / Format: application/x-www-form-urlencoded or JSON / Query Parameters (depending on the endpoint).
- Timezone Note: created_at timestamps are provided in UTC. Add +7 hours to convert to WIB (Western Indonesia Time).

## Authentication & User Management

### 1. User Login

Authenticate a user and retrieve associated profile information, accessible nodes, and clusters.

- Endpoint: POST /user
- URL: \[https://cloud.gradien.my.id/api/user\](https://cloud.gradien.my.id/api/user)
- Content-Type: application/x-www-form-urlencoded

#### Request Body Parameters

| Parameter | Type   | Required | Description                                                |
|-----------|--------|----------|------------------------------------------------------------|
| email     | String | Yes      | User's registered email address (e.g., user@gradien.my.id) |
| password  | String | Yes      | User's password                                            |

#### Example Response

```json
{
  "id": "6",
  "username": "user",
  "name": "User",
  "email": "user@gradien.my.id",
  "password": "$2y$10$H1K1uTqsvYqpO0MQBv0J.e9kshwP20AENzqfPw.rX3dy2Mtjfq2kK",
  "url_foto": "",
  "role": "user",
  "nodes": "[\"b30c7928\",\"b8221c84\"]",
  "clusters": "[\"abe990a2\",\"ce004260\"]",
  "additional": "",
  "reset_token": "bd6df49ba5e92ff8f0a065ebc697c02f95ebbd010305f0c94b9d96a3060a4f43",
  "created_at": "2025-12-01 06:52:40",
  "updated_at": "2025-12-02 07:40:58"
}
```

## Clusters Management

### 2. Get Cluster Details

Retrieve details about a specific cluster, including its location and associated node IDs.

- Endpoint: POST /cluster
- URL: \[https://cloud.gradien.my.id/api/cluster\](https://cloud.gradien.my.id/api/cluster)

#### Request Body Parameters

| Parameter | Type   | Required | Description                 |
|-----------|--------|----------|-----------------------------|
| email     | String | Yes      | User's email                |
| password  | String | Yes      | User's password             |
| id        | String | Yes      | Cluster ID (e.g., abe990a2) |

## Nodes Management

### 3. Get Node Details

Fetch configuration details, keys, and display parameters for a specific node device.

- Endpoint: POST /node
- URL: \[https://cloud.gradien.my.id/api/node\](https://cloud.gradien.my.id/api/node)

### 4. Get Node Logs & Notifications

Retrieve the latest issues (alerts/notifications) and commands associated with a node by passing the Node Table ID.

- Endpoint: GET /logs
- URL: \[https://cloud.gradien.my.id/api/logs\](https://cloud.gradien.my.id/api/logs)

| Parameter | Type             | Required | Description                                 |
|-----------|------------------|----------|---------------------------------------------|
| id        | String / Integer | Yes      | The primary ID of the Node table (e.g., 27) |

## Sensor Telemetry Data

### 5. Read Sensor Data

Fetch recorded sensor readings for a specified node.

- Endpoint: GET /read
- URL: \[https://cloud.gradien.my.id/api/read\](https://cloud.gradien.my.id/api/read)

#### Endpoint Variants

- Default Read (100 latest items): GET /api/read?id={NODE_ID}&key={NODE_KEY}
- Custom Limit: GET /api/read?id={NODE_ID}&key={NODE_KEY}&limit={LIMIT_COUNT}
- Date Range: GET /api/read?id={NODE_ID}&key={NODE_KEY}&start={START_TIME}&end={END_TIME}