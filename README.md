# Flow Log Data Mapper

This project provides a solution for parsing flow log data, tagging each entry based on a predefined lookup table, and generating detailed reports on the tagged data. The goal is to automatically categorize flow log entries by matching the destination port and protocol with entries in a lookup table.

## Table of Contents

1. [Setup Instructions](#setup-instructions)
    - [Prerequisites](#prerequisites)
    - [Step-by-Step Setup](#step-by-step-setup)
2. [Running the Program](#running-the-program)
3. [Assumptions](#assumptions)
4. [Tests Performed](#tests-performed)


## Setup Instructions

### Prerequisites

Ensure you have the following installed on your system:

1. **Java Development Kit (JDK)**: Version 8 or later.
2. **Text Editor or IDE**: Optional, but recommended for editing and reviewing code.

### Step-by-Step Setup

1. **Clone the Repository**

   Download the project from GitHub using the following command:

   git clone https://github.com/asbhullar/flow-log-data-mapper.git

2. **Prepare Your Input Files**

   - **Flow Log File**: A text file containing your network traffic logs (e.g., `flowlogs.txt`).
   - **Lookup Table File**: A CSV file defining the tags (e.g., `lookup_table.csv`).

3. **Compile the Program**

   Navigate to the project directory and compile the Java source files:

   javac -d bin src/*.java

## Running the Program

Once compiled, you can run the program with the following command:

java -cp bin Main flowlogs.txt lookup_table.csv output_directory

Replace `flowlogs.txt`, `lookup_table.csv`, and `output_directory` with your actual file paths. The program will generate two CSV reports in the specified output directory.


## Assumptions

1. **Flow Log Format**:
    - The program only supports **version 2** of the flow log format as specified in the AWS documentation.
    - The input flow log is in a plain text format, with each line containing fields separated by spaces.
    - The default format for flow logs is used, which includes fields such as version, account ID, interface ID, source IP, destination IP, destination port, source port, protocol, etc.

2. **Lookup Table Format**:
    - The lookup table is a CSV file with the following columns: `dstport`, `protocol`, `tag`.
    - The combination of `dstport` and `protocol` uniquely determines the tag.
    - Tags are case-insensitive, e.g., `tcp` and `TCP` are treated as the same.
    - The program assumes that the lookup table has no more than 10,000 mappings, as specified in the requirements.

3. **Tagging Logic**:
    - Matching is done by combining the `dstport` and `protocol` fields into a key, for example, `25-tcp`.
    - If a flow log entry does not match any entry in the lookup table, it is tagged as `Untagged`.


## Tests Performed

- Verified correct tag mapping for flow logs based on the lookup table.
- Tested case insensitivity in protocol matching (e.g., `tcp` vs. `TCP`).
- Simulated large input files (up to 10 MB) to ensure performance and stability.
- Checked handling of unknown port/protocol combinations and invalid/missing fields.
- Confirmed accurate counting of tags and port/protocol combinations, including boundary cases.
