import org.junit.jupiter.api.Test

interface Animal {
    fun speak()
    fun move()
}

class Dinosaur : Animal {
    override fun speak() {
        println("roaring horribly...")
    }

    override fun move() {
        println("creeping slowly...")
    }
}

class Cuckoo : Animal {
    override fun speak() {
        println("singing loudly...")
    }

    override fun move() {
        println("flying gently...")
    }
}

/**
 * Demonstrate one advantage of composition instead of inheritance
 * The ability to combine behaviors of multiple delegates
 * Inspired by https://kotlinlang.org/docs/delegation.html
 */
class SingingDinosaur : Animal {
    private val speakDelegate = Cuckoo()
    private val moveDelegate = Dinosaur()

    override fun speak() {
        speakDelegate.speak()
    }

    override fun move() {
        moveDelegate.move()
    }
}

/**
 * Demonstrate the usage of by keyword and override method
 * Compiler will generate implementation methods defined in the interface with default delegation
 *   1. Operations can only be delegated to an interface
 *   2. Delegate must be of interface type
 */
class Mutant(private val animal: Animal) : Animal by animal {
    override fun speak() {
        println("speaking language...")
    }
}

class AnimalTest {

    @Test
    fun testCuckoo() {
        val cuckoo = Cuckoo()
        cuckoo.speak()
        cuckoo.move()
    }

    @Test
    fun testSingingDinosaur() {
        val singingDinosaur = SingingDinosaur()
        singingDinosaur.speak()
        singingDinosaur.move()
    }

    @Test
    fun testMutant() {
        val flyingMan = Mutant(Cuckoo())
        flyingMan.speak()
        flyingMan.move()
    }

}
