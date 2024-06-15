# BuildingCutChallenge

Command Line Tool to process CSV and TSV files.

## Overview

This project is a custom implementation of the Unix `cut` command line tool. It follows the Unix philosophy of creating simple, modular, and composable tools. The tool can process CSV and TSV files, allowing users to extract specific fields and use different delimiters.

## Features

- **Step 1**: Basic functionality to print the second field (`-f2`) from each line of a tab-separated file.
- **Step 2**: Added support for custom delimiters using the `-d` option.
- **Step 3**: Extended support to handle multiple fields specified by the `-f` option.
- **Step 4**: Added functionality to read from standard input if no filename is provided or if a single dash (`-`) is used.
- **Step 5**: Integration with other command line tools like `uniq` and `wc` to build data processing pipelines.

## Usage

### Basic Usage

Print the second field from a tab-separated file:

```
java -jar BuildingCutChallenge.jar -f2 sample.tsv
```

Custom Delimiter
Print the first field from a comma-separated file:

```
java -jar BuildingCutChallenge.jar -f1 -d, fourchords.csv | head -n5
```

Multiple Fields
Print the first and second fields from a tab-separated file:

```
java -jar BuildingCutChallenge.jar -f1,2 sample.tsv
```

Print the first and second fields from a comma-separated file:
```
java -jar BuildingCutChallenge.jar -d, -f"1 2" fourchords.csv | head -n5
```

Reading from Standard Input
Print the last five lines from a file and cut the first and second fields:
```
tail -n5 fourchords.csv | java -jar BuildingCutChallenge.jar -d, -f"1 2" -
```

Data Processing Pipeline
Count the number of unique artists in a CSV file:
```
java -jar BuildingCutChallenge.jar -f2 -d, fourchords.csv | uniq | wc -l
```

Installation
Clone the repository:
```
git clone https://github.com/0kakarot0/BuildingCutChallenge.git
```

Navigate to the project directory:
```
cd BuildingCutChallenge
```

Build the project using Maven:
```
mvn clean package
```

Run the tool:
```
java -jar target/BuildingCutChallenge.jar [options] [filename]
```


### License
This project is licensed under the MIT License. See the LICENSE file for details.

### Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

### Acknowledgments
Inspired by the Unix Philosophy and the book "The Art of Unix Programming".

### Contact
Feel free to reach out via GitHub issues if you have any questions or suggestions [My Linkedin](https://www.linkedin.com/in/ahtishamilyas/).




