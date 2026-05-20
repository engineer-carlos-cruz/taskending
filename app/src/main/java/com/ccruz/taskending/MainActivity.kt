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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ccruz.taskending.ui.theme.TaskendingTheme

private const val LOGIN_ROUTE = "login"
private const val MAIN_ROUTE = "main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskendingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskendingApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TaskendingApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var isAuthenticated by rememberSaveable { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) MAIN_ROUTE else LOGIN_ROUTE,
        modifier = modifier.fillMaxSize()
    ) {
        composable(LOGIN_ROUTE) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                state = loginViewModel.state,
                onUsernameChange = loginViewModel::onUsernameChange,
                onPasswordChange = loginViewModel::onPasswordChange,
                onSignInClick = loginViewModel::onSignInClick,
                onSubmitSuccess = {
                    isAuthenticated = true
                    navController.navigateToMain()
                }
            )
        }
        composable(MAIN_ROUTE) {
            Greeting(name = "Android", modifier = Modifier.padding(24.dp))
        }
    }
}

private fun NavHostController.navigateToMain() {
    navigate(MAIN_ROUTE) {
        popUpTo(LOGIN_ROUTE) { inclusive = true }
        launchSingleTop = true
    }
}

@Composable
fun LoginScreen(
    state: LoginUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSubmitSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) onSubmitSuccess()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Sign in")
        OutlinedTextField(
            value = state.username,
            onValueChange = onUsernameChange,
            label = { Text("Username or email") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.usernameError != null,
            supportingText = { state.usernameError?.let { Text(it) } },
            singleLine = true
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            isError = state.passwordError != null,
            supportingText = { state.passwordError?.let { Text(it) } },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )
        state.authError?.let { Text(text = it, modifier = Modifier.testTag("authError")) }
        Button(
            onClick = onSignInClick,
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.isLoading) "Signing in..." else "Sign in")
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.testTag("loading"))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskendingTheme {
        TaskendingApp()
    }
}
