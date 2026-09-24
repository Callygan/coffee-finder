package org.example

/* Parse the coffee shop data from the CSV content */
fun parseCoffeeShops(csvContent: String): List<CoffeeShop> {
    val lines = csvContent // Split the CSV content into lines
        .lineSequence() // Convert the lines to a sequence for efficient processing
        .filter { it.isNotBlank() } // Remove any blank lines from the sequence

    val coffeeShops = mutableListOf<CoffeeShop>()

    /* Iterate through each line of the CSV content and parse the coffee shop data */
    for ((index, line) in lines.withIndex()) {
        
        /* Split the line into columns based on commas */
        val columns = line.split(",") // Split the line into columns based on commas

        /* Check if the line has the correct number of columns */
        if (columns.size != 3) {
            throw IllegalArgumentException("Malformed CSV row ${index + 1}: $line")
        }

        /* Extract and validate the coffee shop's name and coordinates */
        val name = columns[0].trim()
        val shopY = columns[1].trim().toDoubleOrNull()
        val shopX = columns[2].trim().toDoubleOrNull()

        /* Check if the coffee shop's name is not blank */
        if (name.isBlank()) { // la fel de corect era si isEmpty()
            throw IllegalArgumentException("Missing shop name in CSV row ${index + 1}")
        }

        /* Check if the coffee shop's coordinates are valid */
        if (shopY == null || shopX == null || !shopY.isFinite() || !shopX.isFinite()) {
            throw IllegalArgumentException("Invalid number in CSV row ${index + 1}: $line")
        }

        /* Add the valid coffee shop to the list */
        coffeeShops.add(CoffeeShop(name, shopY, shopX))
    }

    return coffeeShops
}