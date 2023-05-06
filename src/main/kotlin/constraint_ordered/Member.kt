package constraint_ordered

data class Member(
    val name: String,
    val surname: String?,
    val role: String,
    val bands: MutableList<Band> = mutableListOf(), // implementation of an association, cardinality "*"
) {
    fun addBand(newBand: Band) {
        // Check if we already have the info
        if (!bands.contains(newBand)) {
            bands.add(newBand)
            // Add the reverse connection
            newBand.addMember(this)
        }
    }

    fun removeBand(bandToRemove: Band) {
        if (bands.contains(bandToRemove)) {
            bands.remove(bandToRemove)
            // Remove the reverse link
            bandToRemove.removeMember(this)
        }
    }

    override fun toString(): String {
        var info = "Member: $name\n"

        // Add info about bands
        for (band in bands) {
            info += "   " + band.name + "\n"
        }
        return info
    }
}