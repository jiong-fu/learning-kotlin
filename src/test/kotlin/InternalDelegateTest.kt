import org.junit.jupiter.api.Test
import kotlin.properties.Delegates

class InternalDelegateTest {

    /**
     * Demonstrate the usage of notNull delegate
     * Inspired by https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-delegates/not-null.html
     */
    @Test
    fun testNotNull() {
        // notNull delegate takes no argument
        var age: Int by Delegates.notNull()

        println(age) // Java IllegalStateException is thrown since the property value is read before initialized
        age = 30
        println(age)
    }

    /**
     * Demonstrate the usage of observable delegate
     * Inspired by https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-delegates/observable.html
     */
    @Test
    fun testObservable() {
        // observable delegate takes two arguments - the initial value and the handler for modifications
        var name: String by Delegates.observable("Unknown") { property, old, new ->
            run {
                println("$property : $old -> $new")
            }
        }

        name = "Jiong"
        name = "Monster"
    }

    /**
     * Demonstrate the usage of vetoable delegate
     * Inspired by https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-delegates/vetoable.html
     */
    @Test
    fun testVetoable() {
        var age: Int by Delegates.vetoable(0) { _, _, new ->
            new > 0
        }

        age = 50
        println(age)
        age = -100
        println(age)
    }

    /**
     * Demonstrate the usage of lazy delegate
     * Inspired by https://kotlinlang.org/docs/delegated-properties.html#lazy-properties
     */
    @Test
    fun testLazy() {
        // The lambda expression defined in lazy delegate will only be executed once
        // when the property value is read for the first time
        val countryList: MutableList<String> by lazy {
            println("Loading country list by performing expensive call")
            mutableListOf("Germany", "Austria", "Switzerland")
        }

        println(countryList)
        println(countryList)

        // The lambda expression defined in lazy delegate will not be executed again
        countryList.add("Belgium")
        println(countryList)
    }

}
