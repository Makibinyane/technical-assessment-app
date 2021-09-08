package com.example.technical_assessment_app.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.security.MessageDigest
import java.util.stream.Collectors
import kotlin.math.min

class UserViewModel: ViewModel() {

    private var nameOddLetters = ""
    private var surnameEvenLetters = ""
    private var binaryResult = ""

    fun checkNameOddLetters(name: String) {
        //iterate through the characters in the name to determine odd letters in a name string
        for (i in name.indices) {
            if (i % 2 != 0) {
                nameOddLetters += name[i].toString()
            }
        }
        Log.d("DATA", "Name Odd Letters: $nameOddLetters")
    }

    fun checkSurnameEvenLetters(surname: String) {
        //iterate through the characters in the name to determine even letters in a surname string
        for (i in surname.indices) {
            if (i % 2 == 0) {
                surnameEvenLetters += surname[i].toString()
            }
        }
        Log.d("DATA", "Surname Even Letters: $surnameEvenLetters")
    }

    private fun convertStringToBinary(input: String): String {
        //convert the given string input into binary
        val result = StringBuilder()
        val chars = input.toCharArray()
        for (aChar in chars) {
            result.append(
                String.format("%8s", Integer.toBinaryString(aChar.code))
                    .replace(" ".toRegex(), "0")
            )
        }
        return result.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun prettyBinary(binary: String, blockSize: Int, separator: String?): String? {
        //convert the binary into a pretty binary string
        val result: MutableList<String> = ArrayList()
        var index = 0
        while (index < binary.length) {
            result.add(binary.substring(index, min(index + blockSize, binary.length)))
            index += blockSize
        }
        return result.stream().collect(Collectors.joining(separator))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun displayBinary() {
        //display both pretty and normal binary strings
        val result = convertStringToBinary(nameOddLetters + surnameEvenLetters)
        binaryResult =  result
        Log.d("DATA", "Binary: $result")
        val prettyResult = prettyBinary(result, 8, " ")
        Log.d("DATA", "Pretty Binary: $prettyResult")
    }

    fun getBinaryResult() : String {
        //get the binary results
        return binaryResult
    }

    fun get256Data() {
        //convert the odd name letters and even surname letters into SHA-256
        val input = nameOddLetters + surnameEvenLetters
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedString = digest.fold("", { str, it -> str + "%02x".format(it) })
        Log.d("DATA", "256 String: $hashedString")
    }

}