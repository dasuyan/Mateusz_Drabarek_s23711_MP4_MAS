package constraint_ordered

data class Band(
    val name: String, // public for simplicity
    val members: MutableList<Member> = mutableListOf(), // implementation of an association, cardinality "*"
) {
    fun addMember(newMember: Member) {
        // Check if we already have the info
        if (!members.contains(newMember)) {
            members.add(newMember)
            // Add the reverse connection
            newMember.addBand(this)
        }
    }

    fun removeMember(memberToRemove: Member) {
        if (members.contains(memberToRemove)) {
            members.remove(memberToRemove)
            // Remove the reverse link
            memberToRemove.removeBand(this)
        }
    }

    override fun toString(): String {
        var info = """Band: $name
            |   Members:
        """.trimMargin()

        info += "\n"
        // Add info about the members
        for (member in members) {
            info += "\t\t" + member.name + " " + member.surname + "\n"
        }
        return info
    }
}
