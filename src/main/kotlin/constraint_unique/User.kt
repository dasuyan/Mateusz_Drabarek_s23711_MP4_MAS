package constraint_unique

data class User(val id: Int, val name: String, val email: String) {
    companion object {
        private val idSet = mutableSetOf<Int>()
        private val emailSet = mutableSetOf<String>()
    }

    init {
        if (idSet.contains(id)) {
            throw IllegalArgumentException("Duplicate ID: $id")
        } else {
            idSet.add(id)
        }
        if (emailSet.contains(email)) {
            throw IllegalArgumentException("Duplicate email: $email")
        } else {
            emailSet.add(email)
        }
    }
}