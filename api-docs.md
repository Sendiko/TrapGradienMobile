# Cloud Gradien API Documentation

Welcome to the official API documentation for **Cloud Gradien** (`Just Cloud It!`), designed for **TrapGradien** and other Gradien devices.

---

## Overview

* **Base URL:** `https://cloud.gradien.my.id/api`
* **Content-Type / Format:** `application/x-www-form-urlencoded` or JSON / Query Parameters (depending on the endpoint).
* **Timezone Note:** `created_at` timestamps are provided in UTC. Add `+7` hours to convert to WIB (Western Indonesia Time).

---

## Authentication & User Management

### 1. User Login

Authenticate a user and retrieve associated profile information, accessible nodes, and clusters.

* **Endpoint:** `POST /user`
* **URL:** `https://cloud.gradien.my.id/api/user`
* **Content-Type:** `application/x-www-form-urlencoded`

#### Request Body Parameters

| Parameter | Type | Required | Description |
| :--- | :--- | :--- | :--- |
| `email` | String | Yes | User's registered email address (e.g., `user@gradien.my.id`) |
| `password` | String | Yes | User's password |

#### Example Request
```http
POST /api/user HTTP/1.1
Host: cloud.gradien.my.id
Content-Type: application/x-www-form-urlencoded

email=user%40gradien.my.id&password=123

```

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

#### Response Fields Description

* `nodes`: JSON string list containing Node IDs assigned to this user.
* `clusters`: JSON string list containing Cluster IDs assigned to this user.

---

## Clusters Management

### 2. Get Cluster Details

Retrieve details about a specific cluster, including its location and associated node IDs.

* **Endpoint:** `POST /cluster`
* **URL:** `https://cloud.gradien.my.id/api/cluster`
* **Content-Type:** `application/x-www-form-urlencoded`

#### Request Body Parameters

| Parameter | Type | Required | Description |
| --- | --- | --- | --- |
| `email` | String | Yes | User's email |
| `password` | String | Yes | User's password |
| `id` | String | Yes | Cluster ID (e.g., `abe990a2`) |

#### Example Request

```http
POST /api/cluster HTTP/1.1
Host: cloud.gradien.my.id
Content-Type: application/x-www-form-urlencoded

email=user%40gradien.my.id&password=123&id=abe990a2

```

#### Example Response

```json
{
  "id": "8",
  "cluster_id": "ce004260",
  "cluster_key": "b732cc9c",
  "name": "TrapGradien TelU",
  "type": "Stationary",
  "longitude": "0",
  "latitude": "0",
  "description": "Kumpulan TrapGradien di TelU",
  "icon": "stationary.jpg",
  "nodes": "[\"b30c7928\"]",
  "created_at": "2025-12-02 07:40:58",
  "updated_at": "2025-12-02 07:40:58"
}

```

---

## Nodes Management

### 3. Get Node Details

Fetch configuration details, keys, and display parameters for a specific node device.

* **Endpoint:** `POST /node`
* **URL:** `https://cloud.gradien.my.id/api/node`
* **Content-Type:** `application/x-www-form-urlencoded`

#### Request Body Parameters

| Parameter | Type | Required | Description |
| --- | --- | --- | --- |
| `email` | String | Yes | User's email |
| `password` | String | Yes | User's password |
| `id` | String | Yes | Node ID (e.g., `b8221c84`) |

#### Example Request

```http
POST /api/node HTTP/1.1
Host: cloud.gradien.my.id
Content-Type: application/x-www-form-urlencoded

email=user%40gradien.my.id&password=123&id=b8221c84

```

#### Example Response

```json
{
  "id": "27",
  "node_id": "b30c7928",
  "node_key": "fe2a6b06",
  "name": "Lab P320",
  "type": "TrapGradien",
  "icon": "TrapGradien.png",
  "config": "{\"field1\":{\"icon\": \"wind\", \"scale\": {\"max\": \"4\", \"min\": \"0\"}, \"title\": \"Purification Status\", \"xaxis\": \"Time\", \"yaxis\": \"Purification Level\", \"thresholds\": [{\"to\": \"0\", \"from\": \"0\", \"icon\": \"check-circle\", \"color\": \"#2ecc71\", \"label\": \"No Purification\"}, {\"to\": \"1\", \"from\": \"1\", \"icon\": \"leaf\", \"color\": \"#1abc9c\", \"label\": \"Ion Added\"}, {\"to\": \"2\", \"from\": \"2\", \"icon\": \"sync\", \"color\": \"#f1c40f\", \"label\": \"Normal Purification\"}, {\"to\": \"3\", \"from\": \"3\", \"icon\": \"spinner\", \"color\": \"#e67e22\", \"label\": \"Significant Purification\"}, {\"to\": \"4\", \"from\": \"4\", \"icon\": \"fan\", \"color\": \"#e74c3c\", \"label\": \"Maximum Purification\"}]}}"
}

```

#### Parsed `config` Schema Example

```json
{
  "field1": {
    "icon": "wind",
    "scale": {
      "min": "0",
      "max": "4"
    },
    "title": "Purification Status",
    "xaxis": "Time",
    "yaxis": "Purification Level",
    "thresholds": [
      { "from": "0", "to": "0", "icon": "check-circle", "color": "#2ecc71", "label": "No Purification" },
      { "from": "1", "to": "1", "icon": "leaf", "color": "#1abc9c", "label": "Ion Added" },
      { "from": "2", "to": "2", "icon": "sync", "color": "#f1c40f", "label": "Normal Purification" },
      { "from": "3", "to": "3", "icon": "spinner", "color": "#e67e22", "label": "Significant Purification" },
      { "from": "4", "to": "4", "icon": "fan", "color": "#e74c3c", "label": "Maximum Purification" }
    ]
  }
}

```

---

### 4. Get Node Logs & Notifications

Retrieve the latest issues (alerts/notifications) and commands associated with a node by passing the Node Table ID.

* **Endpoint:** `GET /logs`
* **URL:** `https://cloud.gradien.my.id/api/logs`

#### Query Parameters

| Parameter | Type | Required | Description |
| --- | --- | --- | --- |
| `id` | String / Integer | Yes | The primary **ID of the Node table** (e.g., `27`) |

#### Functionality

* Returns up to **10 latest issues** (useful for push notifications or alert logs).
* Returns up to **4 latest commands** (used by physical devices to poll for queued commands from the cloud).

#### Example Request

```http
GET /api/logs?id=27 HTTP/1.1
Host: cloud.gradien.my.id

```

#### Example Response

```json
{
  "issues": [
    {
      "time": "2025-12-02 23:12:58",
      "issue": "Low Battery",
      "status": "Unresolved"
    }
  ],
  "commands": []
}

```

---

## Sensor Telemetry Data

### 5. Read Sensor Data

Fetch recorded sensor readings for a specified node. Supports standard default queries, custom limit fetching, and time range filtering.

* **Endpoint:** `GET /read`
* **URL:** `https://cloud.gradien.my.id/api/read`

#### Query Options

##### Option A: Default Read (Latest 100 Entries)

```http
GET /api/read?id={NODE_ID}&key={NODE_KEY}

```

##### Option B: Read with Custom Limit

```http
GET /api/read?id={NODE_ID}&key={NODE_KEY}&limit={LIMIT_COUNT}

```

##### Option C: Read by Time Range

```http
GET /api/read?id={NODE_ID}&key={NODE_KEY}&start={DATETIME_START}&end={DATETIME_END}

```

#### Query Parameters Reference

| Parameter | Type | Required | Description |
| --- | --- | --- | --- |
| `id` | String | Yes | Node ID (e.g., `b30c7928`) |
| `key` | String | Yes | Node Key (e.g., `fe2a6b06`) |
| `limit` | Integer | Optional | Number of recent entries to return (default is 100) |
| `start` | Datetime | Optional | Start timestamp for filtering (`YYYY-MM-DD HH:MM:SS`) |
| `end` | Datetime | Optional | End timestamp for filtering (`YYYY-MM-DD HH:MM:SS`) |

#### Example Response

```json
[
  {
    "id": "1",
    "created_at": "2025-12-02 07:06:26",
    "field1": "3",
    "field2": "15",
    "field3": "11",
    "field4": "5",
    "field5": "700",
    "field6": "20",
    "field7": "50",
    "field8": "70"
  },
  {
    "id": "2",
    "created_at": "2025-12-02 07:06:41",
    "field1": "3",
    "field2": "15",
    "field3": "11",
    "field4": "5",
    "field5": "700",
    "field6": "20",
    "field7": "50",
    "field8": "70"
  },
  {
    "id": "3",
    "created_at": "2025-12-02 07:07:13",
    "field1": "3",
    "field2": "15"
  }
]

```


* **Custom Limit:** `GET /api/read?id={NODE_ID}&key={NODE_KEY}&limit={LIMIT_COUNT}`
* **Date Range:** `GET /api/read?id={NODE_ID}&key={NODE_KEY}&start={START_TIME}&end={END_TIME}`