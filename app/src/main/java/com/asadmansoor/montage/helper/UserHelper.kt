package com.asadmansoor.montage.helper

import java.util.regex.Pattern

class UserHelper {

    private val ALPHABET_PATTERN =  Pattern.compile("^[a-z A-Z]+")

    fun isNameValid(name: String) : Boolean {
        if (name.isEmpty()){
            return false
        }

        if (name.isBlank()){
            return false
        }

        if (!(name.contains(" "))){
            return false
        }


        if (name.split(" ").size < 2){
            return false
        }

        if (name.split(" ").size > 1){
            for (i in name.split(" ")){
                if (i.isEmpty()){
                    return false
                }
            }
        }

        if (!(ALPHABET_PATTERN.matcher(name).matches())){
            return false
        }

        return true
    }


    fun isEmailValid(email: String) : Boolean {
        return (email.contains("@"))
    }


    fun modifyNameFormat(name: String) : String {
        var outputString = ""
        val nameSplit = name.split(" ")
        for (i in nameSplit){
            outputString = outputString + i.capitalize() + " "
        }
        outputString = outputString.trimEnd()

        return outputString
    }
}