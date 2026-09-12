# HVA — Animal Hotel Management System

Object-oriented Java application for managing an animal hotel, developed as part of the Object-Oriented Programming course at Instituto Superior Técnico.

The application manages animals, species, habitats, trees, employees, vaccines and vaccinations, while providing commands for querying and modifying the hotel state.

## Features

* Register and manage animals and species
* Register and manage habitats
* Transfer animals between habitats
* Configure habitat influence on different species
* Register and manage trees
* Track tree seasons and calculate cleaning effort
* Register employees and assign responsibilities
* Support for veterinarians and keepers
* Calculate animal and employee satisfaction
* Register vaccines and vaccinations
* Track vaccination and animal health history
* Detect and record problems caused by incorrect vaccinations
* Query animals, vaccinations and medical acts
* Save and load the application state using Java serialization
* Import the initial application state from structured text files

## Architecture

The project follows the architecture and design specified for the course, separating the user interface from the application domain.

```text
hva
├── app
│   ├── animal
│   ├── employee
│   ├── exception
│   ├── habitat
│   ├── main
│   ├── search
│   └── vaccine
│
└── core
    ├── domain entities
    └── exception

pt
└── tecnico
    └── uilib
```

### `hva.app`

Contains the presentation layer and application commands.

The different packages organize functionality by domain:

* `animal` — animal management
* `employee` — employee management
* `habitat` — habitat and tree management
* `main` — file operations, season management and global satisfaction
* `search` — queries and reports
* `vaccine` — vaccine and vaccination management
* `exception` — presentation-layer exceptions

### `hva.core`

Contains the domain model and business logic.

The main domain entities include:

* `Animal`
* `Species`
* `Habitat`
* `Tree`
* `DeciduousTree`
* `EvergreenTree`
* `Employee`
* `Keeper`
* `Veterinarian`
* `Vaccine`
* `Vaccination`
* `Hotel`

The domain layer is separated from the user interface and is responsible for the application's business operations.

### `pt.tecnico.uilib`

Contains the interaction library provided with the course, including menus, forms and text/Swing interaction support.

## Domain Model

### Animals and Habitats

Animals belong to a species and are associated with a habitat.

Animal satisfaction takes into account factors such as:

* Number of animals of the same species
* Number of animals of different species
* Habitat area
* Habitat influence on the animal's species

Habitats can have positive, negative or neutral influence on individual species.

### Employees

The system supports two types of employees:

* `Keeper` — responsible for habitats
* `Veterinarian` — responsible for species and vaccinations

Employee satisfaction depends on the responsibilities assigned to each employee and the workload associated with them.

### Trees

Trees can be either:

* `DECIDUOUS`
* `EVERGREEN`

Trees have a biological cycle that depends on the current season and their cleaning effort depends on their type, season, age and cleaning difficulty.

The application starts in `SPRING` and supports the following seasonal cycle:

```text
SPRING → SUMMER → AUTUMN → WINTER → SPRING
```

### Vaccination System

Vaccines specify the species they can be administered to.

When a veterinarian administers a vaccine, the system verifies whether the veterinarian is authorized and records the resulting vaccination.

Incorrect vaccinations can produce different health-history results:

```text
NORMAL
CONFUSION
ACCIDENT
ERROR
```

## Data Persistence

The application supports saving and loading its state using Java serialization.

It uses:

* `Serializable`
* `ObjectInputStream`
* `ObjectOutputStream`

The application can also be initialized from structured text files containing entities such as species, trees, habitats, animals, employees and vaccines.

## Project Structure

```text
src/
├── hva/
│   ├── app/
│   │   ├── animal/
│   │   ├── employee/
│   │   ├── exception/
│   │   ├── habitat/
│   │   ├── main/
│   │   ├── search/
│   │   └── vaccine/
│   │
│   └── core/
│       └── exception/
│
└── pt/
    └── tecnico/
        └── uilib/
```

## Technologies

* Java
* Object-Oriented Programming
* Inheritance and polymorphism
* Java Collections Framework
* Java serialization
* Exception handling
* Text file parsing
* `pt.tecnico.uilib`

## Authors

Developed by:

* **Miguel Mateus**
* **André Roque**

The project's initial skeleton, architecture, UML design and overall structural specifications were provided by the course instructor as part of the assignment.

## Academic Context

This project was developed for the Object-Oriented Programming (PO) course at Instituto Superior Técnico during the 2024/25 academic year.

The assignment focused on designing and implementing an object-oriented animal hotel management system, with particular emphasis on inheritance, polymorphism, collections, exception handling and object persistence.
