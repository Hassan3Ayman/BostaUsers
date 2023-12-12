package com.pluto.bostausers.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen() {
    ProfileContent()
}

@Composable
private fun ProfileContent(){
    Column {
        Text(text = "hello, world")
    }
}