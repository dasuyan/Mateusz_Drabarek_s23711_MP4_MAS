package constraint_subset

import util.ObjectPlus4

data class Customer(
    val name: String,
    val surname: String,
    val sex: String,
    val contactData: MutableList<String>,
) : ObjectPlus4() {
    companion object {
        const val roleIsAuthorizedToPickUp = "is authorized to pick up"
        const val roleIsOwnerOf = "is the owner of"
    }
}
