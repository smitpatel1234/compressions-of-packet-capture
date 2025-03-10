# PCAP Compression Tool

This Maven-based Java project compresses PCAP files using various compression algorithms including LZ4, GZIP, BZIP2, and Snappy. The tool leverages the pcap4j library to handle PCAP files and uses multiple compression libraries to reduce their storage size efficiently.

## Features

- **Multiple Compression Algorithms:**  
  Supports LZ4 for high-speed compression, as well as GZIP, BZIP2, and Snappy.
  
- **Maven-Managed:**  
  Dependencies and build processes are managed via Maven.
  
- **Flexible Usage:**  
  Easily configurable via command-line arguments to select the desired compression algorithm.

## Technologies Used

- **Java (JDK 8 or higher)**
- **Maven** for build and dependency management
- **pcap4j** for reading and processing PCAP files
- **Apache Commons Compress** for GZIP and BZIP2 compression
- **LZ4 Java** for LZ4 compression
- **Snappy** for Snappy compression

## Prerequisites
- Java JDK 8 or higher installed
- Maven installed on your system
- PCAP file(s) that you wish to compress


