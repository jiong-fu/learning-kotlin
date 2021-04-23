import org.junit.jupiter.api.Test

interface MouseEventHandler {
    fun onMouseClicked()
    fun onMouseHover()
}

interface KeyboardEventHandler {
    fun onKeyClicked()
    fun onKeyReleased()
}

/**
 * Demonstrate the combination of multiple delegates and strategy design pattern
 * The class implements the interfaces and delegates the method calls directly to the handlers
 * Inspired by https://kotlinlang.org/docs/delegation.html#overriding-a-member-of-an-interface-implemented-by-delegation
 */
class Widget(private val mouseEventHandler: MouseEventHandler, private val keyboardEventHandler: KeyboardEventHandler) :
    MouseEventHandler by mouseEventHandler, KeyboardEventHandler by keyboardEventHandler

class WidgetTest {

    @Test
    fun testDeleteButton() {
        val deleteButtonMouseEventHandler = object : MouseEventHandler {
            override fun onMouseClicked() {
                println("show confirm dialog for deletion")
            }

            override fun onMouseHover() {
                println("show tooltip for deletion")
            }
        }
        val deleteButtonKeyBoardEventHandler = object : KeyboardEventHandler {
            override fun onKeyClicked() {
                println("perform deletion when enter is clicked")
            }

            override fun onKeyReleased() {
                println("no operation")
            }
        }

        val deleteButton = Widget(deleteButtonMouseEventHandler, deleteButtonKeyBoardEventHandler)
        deleteButton.onMouseClicked()
        deleteButton.onMouseHover()
        deleteButton.onKeyClicked()
        deleteButton.onKeyReleased()
    }

    @Test
    fun testTextBox() {
        val textBoxMouseEventHandler = object : MouseEventHandler {
            override fun onMouseClicked() {
                println("move pointer to the character")
            }

            override fun onMouseHover() {
                println("no operation")
            }
        }
        val textBoxKeyboardEventHandler = object : KeyboardEventHandler {
            override fun onKeyClicked() {
                println("show input character")
            }

            override fun onKeyReleased() {
                println("perform spelling check after two seconds")
            }
        }

        val textBox = Widget(textBoxMouseEventHandler, textBoxKeyboardEventHandler)
        textBox.onMouseClicked()
        textBox.onMouseHover()
        textBox.onKeyClicked()
        textBox.onKeyReleased()
    }

}
