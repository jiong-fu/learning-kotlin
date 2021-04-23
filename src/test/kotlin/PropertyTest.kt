import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Address {
    var streetName: String = ""
    var postalCode: String = ""
    var city: String? = ""
        set(city) {
            // Different from setter in Java, since the setter would be called recursively with this.city = city
            // avoid this by using keyword field
            field = if (city.isNullOrEmpty()) "Frankfurt" else city
        }
    var country: String? = "DE"
        set(country) {
            // Different from Elvis operator in Java, since the question mark is reserved for null check
            // field = country.isNullOrEmpty() ? "DE" : country
            field = if (country.isNullOrEmpty()) "DE" else country
        }
}

class PropertyTest {

    @Test
    fun testSetNullOrEmpty() {
        val address = Address()
        address.streetName = "Theodor-Heuss-Allee"
        address.postalCode = "60486"
        address.city = ""
        address.country = null

        assertEquals("Frankfurt", address.city)
        assertEquals("DE", address.country)
    }

}
