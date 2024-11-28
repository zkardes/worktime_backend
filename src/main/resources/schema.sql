-- Drop tables if they already exist to ensure a clean setup
DROP TABLE IF EXISTS timesheet;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user_account;

-- User Account Table
CREATE TABLE user_account (
    id SERIAL PRIMARY KEY, -- Auto-incrementing primary key
    username VARCHAR(50) NOT NULL UNIQUE, -- Unique username
    password VARCHAR(100) NOT NULL, -- Hashed password
    email VARCHAR(100) NOT NULL UNIQUE, -- Unique email address
    enabled BOOLEAN DEFAULT TRUE, -- Account status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Last update timestamp
);

-- Role Table
CREATE TABLE role (
    id SERIAL PRIMARY KEY, -- Auto-incrementing primary key
    name VARCHAR(50) NOT NULL UNIQUE -- Role name (e.g., 'USER', 'ADMIN')
);

-- User Role Table (Many-to-Many Relationship)
CREATE TABLE user_role (
    user_id INT NOT NULL, -- FK to user_account
    role_id INT NOT NULL, -- FK to role
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user_account (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

-- Project Table
CREATE TABLE project (
    id SERIAL PRIMARY KEY, -- Auto-incrementing primary key
    name VARCHAR(100) NOT NULL, -- Project name
    description TEXT, -- Optional project description
    start_date DATE, -- Project start date
    end_date DATE, -- Project end date
    created_by INT NOT NULL, -- FK to user_account (who created the project)
    FOREIGN KEY (created_by) REFERENCES user_account (id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Last update timestamp
);

-- Timesheet Table
CREATE TABLE timesheet (
    id SERIAL PRIMARY KEY, -- Auto-incrementing primary key
    user_id INT NOT NULL, -- FK to user_account
    project_id INT NOT NULL, -- FK to project
    work_date DATE NOT NULL, -- Date of work
    hours_worked NUMERIC(5, 2) NOT NULL, -- Hours worked (e.g., 8.5 for 8 hours 30 mins)
    description TEXT, -- Optional description of work
    FOREIGN KEY (user_id) REFERENCES user_account (id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Creation timestamp
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Last update timestamp
);

