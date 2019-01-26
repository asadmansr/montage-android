package com.asadmansoor.montage

import com.asadmansoor.montage.helper.UserHelper
import org.junit.Test
import org.junit.Assert.*

/**
 * Local unit test, which will execute on the development machine (host).
 *
 * class: UserHelper.kt
 * methods:
 * - isNameValid(name): validates whether full name is generated
 * - modifyNameFormat(name): validates name is correctly capitalized
 */

class UserHelperTest {

    private var nameHelper = UserHelper()

    @Test
    fun isNameValid_CorrectNameSimple_ReturnsTrue() {
        assertTrue(nameHelper.isNameValid("John Smith"))
    }

    @Test
    fun isNameValid_CorrectNameOther_ReturnsTrue() {
        assertTrue(nameHelper.isNameValid("john smith"))
    }

    @Test
    fun isNameValid_CorrectNameLong_ReturnsTrue() {
        assertTrue(nameHelper.isNameValid("John P Smith"))
    }

    @Test
    fun isNameValid_EmptyString_ReturnsFalse() {
        assertFalse(nameHelper.isNameValid(""))
    }

    @Test
    fun isNameValid_OnlyFirstName_ReturnsFalse() {
        assertFalse(nameHelper.isNameValid("John"))
    }

    @Test
    fun isNameValid_FirstNameWithSpaceAtEnd_ReturnsFalse() {
        assertFalse(nameHelper.isNameValid("John "))
    }

    @Test
    fun isNameValid_FirstNameWithSpaceAtStart_ReturnsFalse() {
        assertFalse(nameHelper.isNameValid(" John"))
    }

    @Test
    fun modifyNameFormat_CorrectName_ReturnsTrue() {
        assertEquals(nameHelper.modifyNameFormat("John Smith"), "John Smith")
    }

    @Test
    fun modifyNameFormat_CorrectNameLong_ReturnsTrue() {
        assertEquals(nameHelper.modifyNameFormat("John P Smith"), "John P Smith")
    }

    @Test
    fun modifyNameFormat_CorrectLowercaseName_ReturnsTrue() {
        assertEquals(nameHelper.modifyNameFormat("john smith"), "John Smith")
    }

    @Test
    fun modifyNameFormat_CorrectLowercaseNameLong_ReturnsTrue() {
        assertEquals(nameHelper.modifyNameFormat("john p smith"), "John P Smith")
    }

    @Test
    fun modifyNameFormat_CorrectMixNameFirst_ReturnsTrue() {
        assertEquals(nameHelper.modifyNameFormat("John smith"), "John Smith")
    }

    @Test
    fun isEmailValid_EmptyString_ReturnsFalse() {
        assertFalse(nameHelper.isEmailValid(""))
    }

    @Test
    fun isEmailValid_InvalidString_ReturnsFalse() {
        assertFalse(nameHelper.isEmailValid("john.example.com"))
    }

    @Test
    fun isEmailValid_CorrectUpperString_ReturnsTrue() {
        assertTrue(nameHelper.isEmailValid("JOHN@EXAMPLE.COM"))
    }

    @Test
    fun isEmailValid_CorrectString_ReturnsTrue() {
        assertTrue(nameHelper.isEmailValid("john@example.com"))
    }
}
