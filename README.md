# Spring Boot Rate limiting with Unkey

This simple application demonstrates how to implement Rate limiting for Spring Boot routes. The application has both `cheap` and `expensive` routes.

## Features

- **Cheap Route**: Can be accessed 6 times every 30 seconds. `/api/cheap`
- **Expensive Route**: Can be accessed 3 times every 30 seconds `/api/expensive`

## Setup Unkey 

1. Go to unkey [ratelimits](https://app.unkey.com/ratelimits)

2. Create a new namespace with name `harshbhat`

3. Go to settings/root-keys and create a root key with Ratelimit permissions
   
4. You can follow this link to create the root key with ratelimit permissions [https://app.unkey.com/settings/root-keys/new?permissions=ratelimit.*.create_namespace,ratelimit.*.read_namespace,ratelimit.*.limit,ratelimit.*.update_namespace,ratelimit.*.delete_namespace](https://app.unkey.com/settings/root-keys/new?permissions=ratelimit.*.create_namespace,ratelimit.*.read_namespace,ratelimit.*.limit,ratelimit.*.update_namespace,ratelimit.*.delete_namespace)

6. Add it in the .env file `UNKEY_ROOT_KEY`

## Prerequisites

- Java
- MVN
- An account with Unkey and a valid Root Key

## Quickstart

1. Clone this repository:
   
   ```
   git clone https://github.com/harshsbhat/UnkeySpringBootExample.git
   cd UnkeySpringbootExample

2. Set up your environment variables: Create a .env file in the project root and add the following variables.
Get the Unkey rootkey from [unkey dashboard](http://app.unkey.com/)


   ```
   UNKEY_ROOT_KEY=your_unkey_root_key
   ```

3. Install the required dependencies


   ```
   mvn clean install
   ```

4. Start the project. It should start on port 8080 by default

   ```
   mvn spring-boot:run
   ```
## Usage

- **Cheap Route:** Visit `http://localhost:8080/api/cheap` to access the cheap route which is accessible 6 times every 30 seconds before tripping the rate limit.

- **Expensive Route:** Visit `http://localhost:8080/api/expensive` to access the expensive route which is accessible 4 times every 30 seconds before tripping the rate limit.

### Example requests using curl:

#### Cheap
```bash
curl http://localhost:8080/api/cheap
```

#### Expensive
```bash
curl http://localhost:8080/api/expensive
```
