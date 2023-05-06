package constraint_attribute

class Product(price: Double) {
    @set:Throws(Exception::class)
    var price: Double = 0.0
        set(price) {
            if (price < this.price) {
                throw Exception("""The price cannot be decreased!
                    |   The price is currently: ${this.price}, while you tried to bring it down to $price
                """.trimMargin())
            }
            if (this.price.toInt() != 0 &&
                this.price * (1.0 + maxPriceChangePercentage / 100.0) < price) {
                throw Exception(
                    "The price increase (${this.price} => $price) cannot be more than $maxPriceChangePercentage%"
                )
            }
            if (price < minimumPrice) {
                throw Exception("The price ($price) has to be at least $minimumPrice)")
            }
            field = price
        }

    init {
        this.price = price
    }

    override fun toString(): String {
        return "Product, price: $price"
    }

    companion object {
        const val minimumPrice = 2000.0
        const val maximumPrice = 5000.0
        const val maxPriceChangePercentage = 10.0
    }
}
