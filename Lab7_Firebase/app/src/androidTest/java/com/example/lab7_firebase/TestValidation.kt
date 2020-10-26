package com.example.lab7_firebase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TestValidation {
    // TODO: Test passwords
    // Add two tests for a valid password
    // Add two tests for an invalid password

    @Test
    fun testPasswords(){
        val validator = Validators()

        //test invalid passwords
        assertFalse(validator.validPassword("123456"))
        assertFalse(validator.validPassword("aaaaaa"))

        // test valid passwords
        assertTrue(validator.validPassword("123aa1"))
        assertTrue(validator.validPassword("aaa1a1a"))
    }
}