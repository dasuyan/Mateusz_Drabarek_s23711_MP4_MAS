package constraint_bag

import java.time.LocalDate

data class Caretaker(
    val name: String,
    val email: String,
    val dateOfEmployment: LocalDate,
    val cares: MutableList<Care> = mutableListOf()
) {
    fun addCare(care: Care) {
        if (!cares.contains(care)) {
            cares.add(care)
        }
    }

    fun removeCare(careToRemove: Care) {
        careToRemove.removeCare()
    }

    fun countRepetitiveCareRecords(animal: Animal) {
        var counter = 0
        for (care in cares)
            if (care.animal == animal)
                counter++

        println("$name: I've taken care of ${animal.name} $counter times")
    }

    fun getAnimals(): String {
        var res = "Care records for Mr./Mrs. $name: \n"
        for (care in cares) {
            res += "    name: " + care.animal.name + "; species: " + care.animal.species + "; duration in days: " + care.numberOfDays + "\n"
        }
        return res
    }
}
