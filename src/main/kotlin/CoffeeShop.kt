package org.example
import kotlin.math.hypot // For calculating the Euclidean distance between two points

/* Represents a coffee shop with a name and coordinates */
data class CoffeeShop(
    val name: String,
    val y: Double,
    val x: Double
)

/* Calculate the Euclidean distance between the user's location and a coffee shop */
fun calculateDistance(
    userY: Double,
    userX: Double,
    shop: CoffeeShop
): Double {
    return hypot(shop.y - userY, shop.x - userX) //hypotenuse -> sqrt(a*a + b*b)
}