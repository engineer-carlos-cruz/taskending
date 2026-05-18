package com.ccruz.taskending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ccruz.taskending.ui.theme.TaskendingTheme

private const val REGISTRATION_ROUTE = "registration"
private const val MAIN_ROUTE = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskendingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskendingApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskendingApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = REGISTRATION_ROUTE,
        modifier = modifier.fillMaxSize()
    ) {
        composable(REGISTRATION_ROUTE) {
            RegistrationScreen(
                onSubmitSuccess = {
                    navController.navigateToMain()
                }
            )
        }
        composable(MAIN_ROUTE) {
            Greeting(
                name = "Android",
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

private fun NavHostController.navigateToMain() {
    navigate(MAIN_ROUTE) {
        popUpTo(REGISTRATION_ROUTE) { inclusive = true }
        launchSingleTop = true
    }
}

@Composable
fun RegistrationScreen(
    onSubmitSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Register")
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = null
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            isError = usernameError != null,
            supportingText = {
                usernameError?.let { Text(it) }
            },
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = null
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError != null,
            supportingText = {
                passwordError?.let { Text(it) }
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )
        Button(
            onClick = {
                val validationResult = validateCredentials(
                    username = username,
                    password = password
                )

                usernameError = validationResult.usernameError
                passwordError = validationResult.passwordError

                if (validationResult.isValid) {
                    onSubmitSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Account")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskendingTheme {
        TaskendingApp()
    }
}
