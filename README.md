# employee-management

## Getting Started

### 1. Build the Project

```bash
mvn clean compile
```

### 2. Run the Application

#### Using Maven:

```bash
mvn spring-boot:run
```

### 3. Test the Application

The application will start on `http://localhost:8080`

#### Available Endpoints:

- **GET** `/` - Welcome message
- **GET** `/health` - Health check
- **GET** `/api/employees` - Get employees
- **POST** `/api/employees` - Create a new employee
- **PUT** `/api/employees/{id} - Update employee by ID
- **DELETE** `/api/employees/{id} - Delete employee by ID
- **GET** `/api/departments` - Get departments
- **POST** `/api/departments` - Create a new department
- **PUT** `/api/departments/{id} - Update department by ID
- **DELETE** `/api/departments/{id} - Delete department by ID
