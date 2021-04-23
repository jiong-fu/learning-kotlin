import org.junit.jupiter.api.Test

data class CustomerResponse1(
    val firstName: String,
    val familyName: String,
    val streetName: String = "",
    val houseNumber: String = "",
    val postalCode: String = "",
    val city: String
)

data class CustomerResponse2(val map: Map<String, Any?>) {
    val firstName: String by map
    val familyName: String by map
    val streetName: String by map
    val houseNumber: String by map
    val postalCode: String by map
    val city: String by map
}

class CustomerTest {

    @Test
    fun testCustomerWithNamedProperties() {
        // 1. named property provides more flexibility in writing and readability in maintaining the code
        // 2. constructor overloading for optional parameters is not needed anymore
        // 3. default getter and setter declarations can be omitted with data class
        val customer = CustomerResponse1(firstName = "Jiong", familyName = "Fu", city = "Bamberg", postalCode = "96052")

        println(customer.firstName)
        println(customer.familyName)
        println(customer.city)
        println(customer.postalCode)
        println(customer)
    }

    /**
     * Demonstrate the property storage with map which is especially useful when creating JSON object for API response
     * Inspired by https://kotlinlang.org/docs/delegated-properties.html#storing-properties-in-a-map
     */
    @Test
    fun testCustomerWithPropertiesInMap() {
        val customer = CustomerResponse2(
            mapOf(
                "firstName" to "Jiong",
                "familyName" to "Fu",
                "city" to "Bamberg",
                "postalCode" to "96052",
                // More properties that are not defined in the data class can be inserted into the map as well
                // However, the compile time accessibility to these properties could not be provided
                "isNewCustomer" to true
            )
        )

        println(customer.firstName)
        println(customer.familyName)
        println(customer.city)
        println(customer.postalCode)
        println(customer)
    }

}
