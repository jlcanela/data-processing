# Data processing use cases

## Analyse source

Given a local source parquet file
When I analyze the source
I get a schema

Given a local source csv file with headers
When I analyze a source
I get a schema

## Data annotation

A field can be annotated as primary key
A field can be annotated as composite key
A field can be annotated as foreign key
A field can be annotated as a data distribution values
A field can be annotated as a default value if not exist
A field can be annotated as a default value if incorrect

## Clean data

Given a clean definition
When I clean a file
I get a cleaned data file

Business rules:
- when a clean definition contains a type all values are coerced to the target type
  - if a value cannot be coerced and a default value is provided then the default value is selected
  - if a value cannot be coerced and no default value is provided then the row is skipped
- when a list of valid is provided
  - if a value is not valid and a default value is provided then the default value is selected
  - if a value is not valid and no default value is provided then the row is skipped

## Check data distribution

Business rules:

## Generate data transformation

Given source schema and target schema
When I generate data transformation
Then I get a list of required bidirectional data transformations

Business rules
- data transformation are isomorphic to Quill Quoted AST

## Apply data transformation

Given source, source schema, target schema and transformation
When I transform the data
Then I get a valid target dataset
