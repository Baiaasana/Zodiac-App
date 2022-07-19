package com.example.recyclerviewgrid

fun isEmptyField(
    firstName: String,
    lastName: String,
    sign: String,
): Boolean {
    return firstName.isEmpty() ||
            lastName.isEmpty() ||
            sign.isEmpty()
}
