package com.emanuellecarvalho.mebusca

class InputValidator {

    fun validateInput(searchValue: String): Boolean {
        if (searchValue == "") {
            return false
        }
        if (searchValue.length < 3) {
            return false
        }
        return true
    }
}