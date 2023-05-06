package constraint_custom

import constraint_custom.OrderStatus

data class Order(private val orderNumber: Int) {
    var status: OrderStatus = OrderStatus.CREATED
        set(newStatus) {
            if (status <= newStatus) {
                field = newStatus
            } else throw Exception("""You can't revert to a previous status! 
            |If you suspect there's been a mistake in the processing of Order No. $orderNumber, please contact the Order Tracking Department.""".trimMargin())
        }

    override fun toString(): String {
        return "Order: $orderNumber status: $status"
    }
}