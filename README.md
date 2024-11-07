# ChocolateFactory API

A Spring Boot application for managing a chocolate factory's operations including raw materials, finished products, suppliers, orders, and stores.

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage raw materials
- Manage finished products
- Manage suppliers
- Handle orders
- Manage store inventories
- Exception handling

## Project Structure

```plaintext
chocolate
├── bin
│   ├── main
│   └── test
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── chocolate
│   │   │               ├── controllers
│   │   │               ├── entities
│   │   │               ├── exceptions
│   │   │               ├── repositories
│   │   │               └── services
│   │   └── resources
│   └── test
│       └── java
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── HELP.md
└── settings.gradle
```

## Installation

To get started with the ChocolateFactory API, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/chocolate-factory-api.git
    cd chocolate-factory-api
    ```

2. Build the project using Gradle:

    ```bash
    ./gradlew build
    ```

3. Run the application:

    ```bash
    ./gradlew bootRun
    ```

## Usage

Once the application is running, you can access the API at `http://localhost:8080`.

## API Endpoints

Here are the main API endpoints provided by the ChocolateFactory API:

### Raw Materials

- `GET /raw-materials`: Get all raw materials
- `POST /raw-materials`: Add a new raw material
- `GET /raw-materials/{id}`: Get a raw material by ID
- `PUT /raw-materials/{id}`: Update a raw material
- `DELETE /raw-materials/{id}`: Delete a raw material

### Finished Products

- `GET /finished-products`: Get all finished products
- `POST /finished-products`: Add a new finished product
- `GET /finished-products/{id}`: Get a finished product by ID
- `PUT /finished-products/{id}`: Update a finished product
- `DELETE /finished-products/{id}`: Delete a finished product

### Suppliers

- `GET /suppliers`: Get all suppliers
- `POST /suppliers`: Add a new supplier
- `GET /suppliers/{id}`: Get a supplier by ID
- `PUT /suppliers/{id}`: Update a supplier
- `DELETE /suppliers/{id}`: Delete a supplier

### Orders

- `GET /orders`: Get all orders
- `POST /orders`: Add a new order
- `GET /orders/{id}`: Get an order by ID
- `PUT /orders/{id}`: Update an order
- `DELETE /orders/{id}`: Delete an order

### Stores

- `GET /stores`: Get all stores
- `POST /stores`: Add a new store
- `GET /stores/{id}`: Get a store by ID
- `PUT /stores/{id}`: Update a store
- `DELETE /stores/{id}`: Delete a store

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Make your changes
4. Commit your changes (`git commit -am 'Add new feature'`)
5. Push to the branch (`git push origin feature-branch`)
6. Create a new Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
