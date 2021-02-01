# Project Patient

## Background Info
The following project is a backend GraphQL service for a patient electronic medical record (EMR) system, specifically
the creation and scheduling of appointments for a patient and a practitioner. This system is meant for a
private medical practice (not a hospital) that has multiple health care providers including doctors, therapists, and
chiropractors.

### Use Cases

- creation/update of a doctor/practitioner information
- creation/update of a patient information
- scheduling new appointments
- updating appointments
- removing a doctor from the clinic/network

### Technical Details
- GraphQL
- REST APIs
- Java
- Spring (Boot)
- HSQL


### How to start
- the project was created in IntelliJ, so you can open the project (pom.xml) there and run the
  PatientApp.java class. Port is hosted on the default 8080 port

### GraphQL
`localhost:8080/graphiql`

Refer to the following .graphqls schema file in the following directory:\
`src/main/resources/graphql/patientAppSchema.graphqls`

#### GraphQL Queries (Query Resolvers)
```
# retrieve a patient's information including their insurance info
retrieveAllPatientInfo(id: ID): AllPatientInfo!

#Retrieve all doctors/practitioners at the practice
retrieveAllDoctors: [Doc!]

#Retrieve specific doctor/practitioner by their ID
retrieveDoctorById(id: ID): Doc

#Retrieve all patients at the practice
retrievePatientsInfo: [Patient!]

#Retrieve specific patient's information
retrievePatientInfoById(id: ID): Patient

#Retrieve all appointments
retrieveAllAppointments: [Appointment!]

#Retrieve appointmet by its ID
retrieveAppointmentById(id: ID): Appointment

#Retrieve appointments by its status (enum type)
retrieveAppointmentByStatus(status: AppointmentStatus!): [Appointment]

#Retrieve appointments by patient id. If appointment status is null, retrieve all appointments
retrievePatientsAppointments(patientId: ID!, status: AppointmentStatus): [Appointment]

#Retrieve appointments by doctor id. If appointment status is null, retrieve all appointments
retrieveDoctorsAppointments(docId: ID!, status: AppointmentStatus): [Appointment]

```

#### GraphQL Mutations (Mutation Resolvers)
```
    #Update doctor's personal information
    updateDoctorInfo(id: ID!, input: DocInput!): Doc!

    #Creat a new doctor using the DocInput type
    createNewDoctor(input: DocInput!): Doc!

    #Create a brand new patient for the practice
    createNewPatient(input: PatientInput!): Patient!

    #Assign a new doc to the patient
    addDoctorToPatient(patientId: ID!, docId: ID!): Patient!

    #Schedule a new appointment for a patient and a doctor
    createNewAppointment(input: AppointmentInput): Appointment!

    #Update an appointment (ie. reschedule, cancel, update based on doctor's prognosis, etc)
    updateAppointment(id: ID!, input: AppointmentInput!): Appointment!

    #remove doctor info from the practice
    removeDoctor(id: ID): String!
```

### Rest Endpoints
*Note: REST endpoints were not the focus of this project but as a means of a way to compare how a backend system is developed with rest endpoints and adding graphql queries to it

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






