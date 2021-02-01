### REST Endpoints

#### <u>Doctor Endpoints</u>


`GET /doctors`

**Description**: Retrieves all doctors in the practice\
**Status:** 200\
**Sample Response Response Body**

```
    {
    "result": [
        {
            "id": 1,
            "patients": [
                {
                    "id": 3,
                    "firstName": "patient",
                    "lastName": "three",
                    "email": "p3@email.com",
                    "phone": "213-456-7890",
                    "dob": "2021-02-01T00:10:06.575+00:00",
                    "healthCard": "healthcard"
                }
            ],
            "title": "DC",
            "firstName": "chiro",
            "lastName": "two",
            "specialty": "functional movement"
        }
    ],
    "status": 200,
    "responseDate": "2021-01-31"
}
```


`POST /doctors`

**Description:** Create a new doctor in the practice\
**Status:** 200\
**Sample Request Body**

```
{
    "title": "MD",
    "firstName": "postman",
    "lastName": "doc",
    "specialty": "orthopaedics"
}

```

`GET /doctors/{id}`

**Description**: Get specific doctor's information\
**Status:** 200\
**Sample Request Body**

```
{
    "result": {
        "id": 1,
        "patients": [
            {
                "id": 3,
                "firstName": "patient",
                "lastName": "three",
                "email": "p3@email.com",
                "phone": "213-456-7890",
                "dob": "2021-02-01T00:10:06.575+00:00",
                "healthCard": "healthcard"
            }
        ],
        "title": "DC",
        "firstName": "chiro",
        "lastName": "two",
        "specialty": "functional movement"
    },
    "status": 200,
    "responseDate": "2021-01-31"
}
```

`GET /doctors/{id}/appointments`

**Description:** Get a specific doctor's appointments\
**Status:** 200\
**Sample Response Body**

```
{
    "result": [
        {
            "id": 1,
            "docId": 1,
            "patientId": 3,
            "scheduledDate": "2021-02-05T08:30:00-05:00",
            "scheduledEnd": "2021-02-05T09:00:00-05:00",
            "appointmentStatus": "UPCOMING",
            "notes": "patient is fine"
        },
        {
            "id": 3,
            "docId": 1,
            "patientId": 3,
            "scheduledDate": "2021-02-10T10:00:00-05:00",
            "scheduledEnd": "2021-02-10T10:30:00-05:00",
            "appointmentStatus": "UPCOMING",
            "notes": null
        },
        {
            "id": 5,
            "docId": 1,
            "patientId": 3,
            "scheduledDate": "2021-01-21T11:30:00-05:00",
            "scheduledEnd": "2021-01-21T12:00:00-05:00",
            "appointmentStatus": "UPCOMING",
            "notes": null
        }
    ],
    "status": 200,
    "responseDate": "2021-01-31"
}
```

##### <u>Patient Endpoints</u>
`GET /patients`
**Description:** Get all patients that are part of the practice\
**Status:** 200\
**Sample Response Body**
```
{
    "result": [
        {
            "id": 1,
            "firstName": "patient",
            "lastName": "one",
            "email": "p1@email.com",
            "phone": "213-456-7890",
            "dob": "2021-01-29T22:34:01.073+00:00",
            "healthCard": "healthcard"
        },
        {
            "id": 2,
            "firstName": "patient",
            "lastName": "two",
            "email": "p2@email.com",
            "phone": "321-456-7890",
            "dob": "2021-01-29T22:34:01.073+00:00",
            "healthCard": "healthcard"
        }
    ],
    "status": 200,
    "responseDate": "2021-01-29"
}
```

`GET /patients/{id}`
**Description:** Get specific patient's information including the doctors assigned to the patient (can be more than one)\
**Status:** 200\
**Sample Response Body**
```
{
    "result": {
        "id": 1,
        "doctorIds": [
            3
        ],
        "firstName": "patient",
        "lastName": "one",
        "email": "p1@email.com",
        "phone": "213-456-7890",
        "dob": "2021-02-01T00:10:06.547+00:00"
    },
    "status": 200,
    "responseDate": "2021-01-31"
}
```

`GET /patients/{id}/appointments`
**Description:** Get a specific patient's appointments
**Status:** 200\
**Sample Response Body**
```
    {
    "result": [
        {
            "id": 1,
            "docId": 1,
            "patientId": 3,
            "scheduledDate": "2021-02-05T08:30:00-05:00",
            "scheduledEnd": "2021-02-05T09:00:00-05:00",
            "appointmentStatus": "UPCOMING",
            "notes": "patient is fine"
        },
        {
            "id": 2,
            "docId": 2,
            "patientId": 3,
            "scheduledDate": "2021-02-05T09:00:00-05:00",
            "scheduledEnd": "2021-02-05T09:30:00-05:00",
            "appointmentStatus": "UPCOMING",
            "notes": null
        }
    ],
    "status": 200,
    "responseDate": "2021-01-31"
}
```


#### <u>Appointments</u>

`GET /appointments`

**Description:** Get all appointments in the practice\
**Status:** 200\
**Sample Response Body**
```
    {
    "result": [
        {
            "id": 1,
            "docId": 1,
            "patientId": 3,
            "scheduledDate": "2021-02-04T08:30:00-05:00",
            "scheduledEnd": "2021-02-04T09:00:00-05:00",
            "appointmentStatus": "UPCOMING",
            "notes": "patient is fine"
        }
    ],
    "status": 200,
    "responseDate": "2021-01-30"
}
```