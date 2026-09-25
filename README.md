# Coffee Finder

## Overview

You have been hired by a company that builds an app for coffee addicts. You are responsible for
taking the user’s location and returning a list of the three closest coffee shops.


## Input

The coffee shop list is a comma-separated file with rows of the following form:
`Name,Y Coordinate,X Coordinate`
The quality of the data in this list may vary. Malformed entries should cause the program to exit
appropriately.

Your program will be executed directly from the command line and will receive three arguments,
in this order:
`<user y coordinate> <user x coordinate> <shop data url>`

Note that the data file will be read from a network location (e.g.:
https://raw.githubusercontent.com/Agilefreaks/test_oop/master/coffee_shops.csv)

## Output

Write a program that takes the user’s coordinates encoded as described above and prints a
newline-separated list of the three closest coffee shops, including each shop’s distance from the
user, ordered from closest to farthest. Distances should be rounded to four decimal places.

Assume all coordinates lie on a plane.

The output should be very simple, no UI is required.

## Example

Using the [coffee_shops.csv]
__Input__
`47.6 -122.4 coffee_shops.csv`
__Expected output__
```
Starbucks Seattle2,0.0645
Starbucks Seattle,0.0861
Starbucks SF,10.0793
```


# What was done

1. The `CoffeeShop` model stores the name of the coffee shop and its coordinates.

2. A function calculates the Euclidean distance between the user's location and each coffee shop using the `X` and `Y` coordinates.

3. CSV file parsing is handled as a separate function. For each row, the system verifies that there are exactly three columns, that the name is complete, and that both coordinates are finite numbers.

4. Command-line arguments are read and validated. The program expects the `Y` coordinate, the `X` coordinate, and the URL of the file with data.

5. Validations are applied for the number of arguments and for the user's coordinates. Invalid values, `NaN`, and infinite values are rejected.

6. Data is downloaded from the provided URL with error handling for situations where the URL cannot be read.

7. For each coffee shop, the distance is calculated, then the shops are sorted from closest to farthest, keeping only the top three results.

8. The results are formatted with each coffee shop displayed on one line, with the distance rounded to four decimal places:

```
Name, distance
```

9. Errors are sent to `stderr` and the program exits with a non-zero exit code. Incomplete or incorrect CSV rows are properly handled.

10. The project is verified through the Gradle build to confirm that the application compiles correctly.
