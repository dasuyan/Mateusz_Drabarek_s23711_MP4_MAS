import constraint_attribute.Product
import constraint_bag.Animal
import constraint_bag.Care
import constraint_bag.Caretaker
import constraint_custom.Order
import constraint_custom.OrderStatus
import constraint_ordered.Band
import constraint_ordered.Member
import constraint_subset.Cat
import constraint_subset.Cat.Companion.roleBelongsTo
import constraint_subset.Cat.Companion.roleCanBePickedUpBy
import constraint_subset.Customer
import constraint_subset.Customer.Companion.roleIsAuthorizedToPickUp
import constraint_subset.Customer.Companion.roleIsOwnerOf
import constraint_unique.User
import constraint_xor.Person
import constraint_xor.PrivateHealthInsurance
import constraint_xor.PublicHealthInsurance
import java.time.LocalDate

fun main() {
//    testAttributeConstraint()
//    testUniqueConstraint()
//    testSubsetConstraint()
//    testOrderedConstraint()
//    testBagConstraint()
//    testXorConstraint()
//    testCustomConstraint()
}

private fun testAttributeConstraint() {
    try {
        val product1 = Product(Product.minimumPrice + 350.0)
        println(product1)
        product1.price *= 3
        //product1.price -= 100
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun testUniqueConstraint() {
    val user1 = User(1, "Alice", "alice@example.com")
    println(user1)

    val user2 = User(2, "Bob", "bob@example.com")
    println(user2)

    val user3 = User(3, "Charlie", "alice@example.com") // throws exception
    println(user3)

    println("All users created successfully.")
}

private fun testSubsetConstraint() {
    val cat = Cat(
        name = "Pruti",
        surname = "Hunter",
        dateOfBirth = LocalDate.of(2020, 3, 10),
        sex = "Female",
        breed = "Tricolore",
        documents = mutableListOf("passport", "book of health", "vaccination certificate")
    )

    val customer = Customer(
        name = "Mateusz",
        surname = "Drabarek",
        sex = "male",
        contactData = mutableListOf("Słoneczna 42, Kotuń", "123456789", "m.d@gmail.com")
    )

    try {
        // Add the ordinary link
        cat.addLink(roleCanBePickedUpBy, roleIsAuthorizedToPickUp, customer) // <== exception thrown if we comment out the base role
        // Add the subset link
        cat.addLink_subset(roleBelongsTo, roleIsOwnerOf, roleCanBePickedUpBy, customer)

        // Show links info
        cat.showLinks(roleCanBePickedUpBy, System.out)
        cat.showLinks(roleBelongsTo, System.out)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }

}

private fun testOrderedConstraint() {
    val member1 = Member("John", "Lennon", "singer")
    val member2 = Member("Paul", "McCartney", "bass guitar")
    val member3 = Member("Ringo", "Starr", "drummer")
    val member4 = Member("George", "Harrison", "guitar")

    val band = Band("The Beatles")
    band.addMember(member1)
    band.addMember(member2)
    band.addMember(member3)
    band.addMember(member4)
    println("The original order, as they've been added to the list")
    println(band)

    band.removeMember(member2)
    band.removeMember(member3)
    println("After removal of the two 'inner' members, we're left with two 'outer' ones")
    println(band)

    band.addMember(member2)
    band.addMember(member3)
    println("Adding the previously members will place them at the end of the list, following the stacking order constraint")
    println(band)
}

private fun testBagConstraint() {
    val animal = Animal("Calypso", "gecko", 3)
    val caretaker = Caretaker("Edyta", "edyta@gmail.com", LocalDate.of(2001, 2, 1))

    // this is a {bag} constraint, because we allow duplicate links between a pair of the same objects in this attribute association
    // MutableList is a container that allows for this to happen
    val care1 = Care(12, animal, caretaker)
    val care2 = Care(4, animal, caretaker)
    val care3 = Care(7, animal, caretaker)

    println(caretaker.getAnimals())
    care1.removeCare() // removing just one instance, so the rest of the duplicates will persist
    println(caretaker.getAnimals())
    println(animal.getCaretakers())

    println(caretaker.getAnimals())

    caretaker.countRepetitiveCareRecords(animal)
    animal.countRepetitiveCareRecords(caretaker)
}

private fun testXorConstraint() {
    val roleIsPrivateInsurer = "private insurer"
    val roleIsPrivateInsured = "private insured"
    val roleIsPublicInsurer = "public insurer"
    val roleIsPublicInsured = "public insured"

    val person = Person("Aaron", "Knowles")
    val privateHealthInsurance = PrivateHealthInsurance("Medicon", 1217.24)
    val publicHealthInsurance = PublicHealthInsurance("pension", 30.0)
    try {
        // Add info about roles constrained by the XOR
        person.addXorRole(roleIsPrivateInsured)
        person.addXorRole(roleIsPublicInsured)

        // Add link no. 1
        person.addLinkXor(roleIsPrivateInsured, roleIsPrivateInsurer, privateHealthInsurance)

        // Add link no. 2 {xor} ==> exception
        // person.addLinkXor(roleIsPublicInsured, roleIsPublicInsurer, publicHealthInsurance);

        person.showLinks(roleIsPrivateInsured, System.out)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}

fun testCustomConstraint() {
    val order = Order(7777)

    println(order)
    order.status = OrderStatus.PAID
    println(order)
    order.status = OrderStatus.DELIVERED
    println(order)

    order.status = OrderStatus.SENT // can't revert to a status that comes before the current one
}