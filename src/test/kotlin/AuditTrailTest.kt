import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Customer {
    var firstName: String by AuditTrailDelegate()
    var familyName: String by AuditTrailDelegate()
    var streetName: String by AuditTrailDelegate()
    var houseNumber: String by AuditTrailDelegate()
    var postalCode: String by AuditTrailDelegate()
    var city: String by AuditTrailDelegate()
}

/**
 * Demonstrate the usage of a reusable delegate
 * Inspired by https://kotlinlang.org/docs/delegated-properties.html
 */
class AuditTrailDelegate : ReadWriteProperty<Customer, String> {
    private var value: String = ""

    override fun getValue(thisRef: Customer, property: KProperty<*>): String {
        println("Audit - property ${property.name} is read from object $thisRef.")
        return value
    }

    // Add audit trail when modifying any property value
    override fun setValue(thisRef: Customer, property: KProperty<*>, value: String) {
        if (value.isBlank()) {
            throw IllegalArgumentException("Blank value not allowed, stop writing.")
        }

        println("Audit - property ${property.name} is written to object $thisRef with value $value.")
        this.value = value
    }
}

class AuditTrailTest {

    @Test
    fun testPropertyAccess() {
        val customer = Customer()
        customer.firstName = "Jiong"
        customer.familyName = "Fu"
        customer.city = "Bamberg"

        println(customer.firstName)
        println(customer.familyName)
        println(customer.city)
        println(customer)
    }

    @Test
    fun testPropertyWritingWithInvalidValue() {
        assertThrows(IllegalArgumentException::class.java) {
            val customer = Customer()
            customer.firstName = " "
        }
    }

}
