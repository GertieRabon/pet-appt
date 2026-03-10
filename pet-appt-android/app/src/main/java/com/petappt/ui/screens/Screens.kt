package com.petappt.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.petappt.data.Appointment
import com.petappt.data.AppointmentRequest
import com.petappt.data.GroomingService
import com.petappt.data.Pet
import com.petappt.data.RegisterRequest
import com.petappt.data.LoginRequest
import com.petappt.network.RetrofitClient
import com.petappt.utils.SessionManager
import kotlinx.coroutines.launch

@Composable
fun LoginRegisterScreen(
    onLoginSuccess: (String) -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val scope = rememberCoroutineScope()

    var isLogin by remember { mutableStateOf(true) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = if (isLogin) "Login" else "Register")
            Spacer(modifier = Modifier.height(16.dp))

            if (!isLogin) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First name") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last name") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        scope.launch {
                            isLoading = true
                            error = null
                            try {
                                if (isLogin) {
                                    val response = RetrofitClient.apiService.login(
                                        LoginRequest(email = email, password = password)
                                    )
                                    if (response.isSuccessful) {
                                        val token = response.body()
                                        if (token != null) {
                                            sessionManager.saveAuthToken(token)
                                            onLoginSuccess("OWNER")
                                        } else {
                                            error = "Empty response from server"
                                        }
                                    } else {
                                        error = "Login failed: ${response.code()}"
                                    }
                                } else {
                                    val response = RetrofitClient.apiService.register(
                                        RegisterRequest(
                                            firstName = firstName,
                                            lastName = lastName,
                                            email = email,
                                            password = password
                                        )
                                    )
                                    if (response.isSuccessful) {
                                        isLogin = true
                                    } else {
                                        error = "Registration failed: ${response.code()}"
                                    }
                                }
                            } catch (e: Exception) {
                                error = e.message
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text(text = if (isLogin) "Login" else "Register")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { isLogin = !isLogin },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = if (isLogin) "Need an account? Register" else "Already registered? Login"
                )
            }

            error?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }
        }
    }
}

@Composable
fun OwnerDashboardScreen(
    onBookAppointment: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Owner Dashboard") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onBookAppointment,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Book Appointment")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    sessionManager.clearData()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Logout")
            }
        }
    }
}

@Composable
fun BookAppointmentScreen(
    onSuccess: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val scope = rememberCoroutineScope()

    var pets by remember { mutableStateOf<List<Pet>>(emptyList()) }
    var services by remember { mutableStateOf<List<GroomingService>>(emptyList()) }
    var selectedPet by remember { mutableStateOf<Pet?>(null) }
    var selectedService by remember { mutableStateOf<GroomingService?>(null) }
    var dateTime by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val token = sessionManager.fetchAuthToken()
        if (token != null) {
            try {
                val petResponse = RetrofitClient.apiService.getPets("Bearer $token")
                val serviceResponse = RetrofitClient.apiService.getServices("Bearer $token")
                if (petResponse.isSuccessful) {
                    pets = petResponse.body().orEmpty()
                }
                if (serviceResponse.isSuccessful) {
                    services = serviceResponse.body().orEmpty()
                }
            } catch (e: Exception) {
                error = e.message
            }
        } else {
            error = "Not logged in"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Book Appointment") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = dateTime,
                onValueChange = { dateTime = it },
                label = { Text("Date & time (ISO)") },
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Select Pet:")
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(pets) { pet ->
                    Button(
                        onClick = { selectedPet = pet },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(pet.name)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Select Service:")
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(services) { service ->
                    Button(
                        onClick = { selectedService = service },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(service.name)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        val token = sessionManager.fetchAuthToken()
                        val pet = selectedPet
                        val service = selectedService
                        if (token == null || pet == null || service == null || dateTime.isBlank()) {
                            error = "Please fill all fields"
                            return@Button
                        }
                        scope.launch {
                            isLoading = true
                            error = null
                            try {
                                val request = AppointmentRequest(
                                    dateTime = dateTime,
                                    petId = pet.id ?: 0L,
                                    serviceId = service.id,
                                    ownerId = pet.ownerId ?: 0L
                                )
                                val response = RetrofitClient.apiService.createAppointment(
                                    "Bearer $token",
                                    request
                                )
                                if (response.isSuccessful) {
                                    onSuccess()
                                } else {
                                    error = "Failed to book: ${response.code()}"
                                }
                            } catch (e: Exception) {
                                error = e.message
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text("Confirm Appointment")
                }
            }

            error?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }
        }
    }
}

@Composable
fun AdminPanelScreen(
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Admin Panel") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Admin functionality can go here.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    sessionManager.clearData()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Logout")
            }
        }
    }
}

