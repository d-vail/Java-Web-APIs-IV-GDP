# Java Web APIs IV - Deployment and Best Practices

## GDP Project

### Introduction

The top 100 economies in the world are provided in the JSON file. We will be reviewing some of this data and logging 
when certain data is accessed.

### Instructions

#### Setup your database

Set up an H2 database to hold the data for countries and their Gross Domestic Product. Use the provided JSON file to 
upload your data. The data contains two fields:

- The Country Name
- The GDP in millions

#### Setup a Rabbit Message Queue

Set up a Rabbit Message Queue to hold logging information.

- Different endpoints will send messages to the queue
- Set up a class to consume the logs. This will represent another server that manages logging.

#### Expose the following endpoints

| Method    | Endpoints             | Results                                                                               |
| --------- | --------------------- | --------------------------------------------------------------------------------------|
| GET       | `/names`              | Return using the JSON format all of the countries alphabetized by name |                     
| GET       | `/economy`            | Return using the JSON format all of the countries sorted from most to least GDP |
| GET       | `/total`              | Return the sum of all GDPs using the JSON format with country name being returned as Total |
| GET       | `/gdp/{countryname}`  | Return using the JSON format the record for that country. Must be spelled as in the data!<br>Log that someone looked up this country |
| POST      | `/gdp`                | Loads the data from the provided JSON file |

#### Recommended Steps

1. Create a new project
2. Add Jackson Dependencies
3. Build Queue and Exchange
4. Populate the rest of the Application class
5. Write the Message Listener (Consumer)
6. Write Data class
7. Write Exception class
8. Write Repository interface
9. Write Controller class!