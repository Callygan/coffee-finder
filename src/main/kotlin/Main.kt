package org.example
import java.net.URI //For parsing the shop data URL
import java.util.Locale // For locale-specific operations, such as converting strings to lowercase or uppercase
import kotlin.system.exitProcess // For terminating the program with a specific exit code

fun main(args: Array<String>) {

     /*Check if the correct number of arguments is provided */
    if (args.size != 3) {
        System.err.println("Expected exactly 3 arguments, but got ${args.size}")
        exitProcess(1)
    }

    /* Check if the user's Y coordinate is valid */
    val userY = args[0].toDoubleOrNull()

    if (userY == null || !userY.isFinite()) { // 
        System.err.println("Invalid number for userY: ${args[0]}")
        exitProcess(1)
    }

    /* Check if the user's X coordinate is valid */
    val userX = args[1].toDoubleOrNull()

    if (userX == null || !userX.isFinite()) { // Check if the user's X coordinate is valid
        System.err.println("Invalid number for userX: ${args[1]}")
        exitProcess(1)
    }

    /* Get the shop data URL from the command-line arguments */
    val shopDataUrl = args[2]

    val csvContent = try {
        URI(shopDataUrl).toURL().readText() // Read the CSV content from the shop data URL
    } catch (exception: Exception) {
        System.err.println("Could not read shop data: ${exception.message}")
        exitProcess(1)
    }

    /* Parse the coffee shop data from the CSV content */
    val coffeeShops = try {
        parseCoffeeShops(csvContent)
    } catch (exception: IllegalArgumentException) {
        System.err.println("Missing shop name or malformed CSV: ${exception.message}")
        exitProcess(1)
    }

    /* Find the closest coffee shops to the user's location */
    val closestShops = coffeeShops
        .map { shop -> shop to calculateDistance(userY, userX, shop) } // Pair each shop with its distance to the user
        .sortedBy { (_, distance) -> distance } // Sort the shops by their distance to the user in ascending order
        .take(3) // Take the top 3 closest coffee shops

    /* Print the closest coffee shops and their distances */
    for ((shop, distance) in closestShops) {
        val formattedDistance = String.format(Locale.US, "%.4f", distance)
        println("${shop.name},$formattedDistance")
    }
}