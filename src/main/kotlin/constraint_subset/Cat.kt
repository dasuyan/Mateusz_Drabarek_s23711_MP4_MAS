package constraint_subset

import util.ObjectPlus4
import java.time.LocalDate

data class Cat(
    val name: String,
    val surname: String?,
    val dateOfBirth: LocalDate,
    val sex: String,
    val breed: String,
    val documents: MutableList<String>,
) : ObjectPlus4() {
    companion object {
        const val roleCanBePickedUpBy = "can be picked up by"
        const val roleBelongsTo = "belongs to"
    }
}
