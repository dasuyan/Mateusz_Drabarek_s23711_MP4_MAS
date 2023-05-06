package constraint_bag

class Care (
    val numberOfDays: Int,
    val animal: Animal,
    val caretaker: Caretaker
) {
    init {
        animal.addCare(this)
        caretaker.addCare(this)
    }
    fun removeCare() {
        animal.cares.remove(this)
        caretaker.cares.remove(this)
    }
}