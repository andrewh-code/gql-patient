# Project Patient

## Background Info
The following project is a backend GraphQL service for a patient electronic medical record (EMR) system, specifically
the creation and scheduling of appointments for a patient and a practitioner. This system is meant for a
private medical practice (not a hospital) that has multiple health care providers including doctors, therapists, and
chiropractors.


## Demo
[Project GQL Patient](https://gql-patient.herokuapp.com/graphiql)

review the queries/mutations examples in the [GraphQL Section](#GraphQL) or the [GraphQL page](graphql/gql_api.md)
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

[click here for sample queries/mutations from graphiql](graphql/gql_api.md)

### REST Endpoints
*Note: REST endpoints were not the focus of this project but as a means of a way to compare how a backend system is developed with rest endpoints and adding graphql queries to it


| request | endpoint | description |
| ------- | -------- | ----------- | 
| GET     | /doctors | retrieve all doctors in the practice |
| POST    | /doctors | create a new doctor for the practice |
| GET     | /doctors{id} | get specific doctor's information |
| GET     | /doctors{id}/appointments | get a doctor's appointments |
| GET     | /patients | get all patients in the practice |
| GET     | /patients/{id} | get specific patient's information
| GET     | /patients/{id}/appointments | get specific patient's appointments |
| GET     | /appointments | get all appointments in the practice |

[click here for more detailed information on the REST endpoints](rest/rest_endpoints.md)









