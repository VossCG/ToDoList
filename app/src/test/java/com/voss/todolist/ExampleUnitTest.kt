package com.voss.todolist

import com.voss.todolist.util.mock.*
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testRequestTextString() {
        val view = mockk<IMockView>()
        val presenter = MockFragmentPresenter(view)

        every {
            view.setOnTextView(any())
        } just Runs

        presenter.setTextView()

        verify {
            view.setOnTextView(any())
        }
    }

    @Test
    fun testStatic() {
        val expected = UserData("Voss", 26)
        val serviceManager = ServiceManager()

        val actual = serviceManager.getUserData()

        mockkObject(ObjectService)
        every {
            ObjectService.instance.requestUserData()
        }.returns(expected)
        assertEquals(expected, actual)
    }
}
