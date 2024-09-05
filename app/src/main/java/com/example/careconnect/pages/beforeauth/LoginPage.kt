package com.example.careconnect.pages.beforeauth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.careconnect.R
import com.example.careconnect.models.ApiState
import com.example.careconnect.navigation.BottomNavItems
import com.example.careconnect.navigation.NavGraph
import com.example.careconnect.pages.components.DisplayToast
import com.example.careconnect.pages.components.HeightSpacer
import com.example.careconnect.pages.components.Loader
import com.example.careconnect.viewmodel.AuthVM
import kotlinx.coroutines.delay

@Composable
fun LoginPage(rootNavController: NavController){
    val context = LocalContext.current
    val vm : AuthVM = viewModel();

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val login = vm.login.value
    val email = login.email
    val password = login.password
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    val state by vm.state.collectAsState()
    val error by vm.error.collectAsState()

    when(state){
        is ApiState.Loading -> {
            Loader()
        }
        is ApiState.Error -> {
            error?.let { DisplayToast(context, it) }
            vm.clear()
        }
        is ApiState.Success -> {
            rootNavController.navigate(NavGraph.Home){
                popUpTo(NavGraph.Auth){
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
        else -> {}
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxSize(),
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welcome",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Login via email",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .padding(top = 50.dp)
                ) {
                    OutlinedTextField(
                        value = email.value,
                        onValueChange = {
                            vm.setData("email",it)
                        },
                        label = {
                            Text(text = "Email")
                        },
                        leadingIcon = {
                          Icon(Icons.Filled.Email, contentDescription = "Email")
                        },
                        maxLines = 1,
                        isError = email.touched && email.untouched && email.error !== "",
                        supportingText = {
                            if(email.touched && email.untouched && email.error !== ""){
                                Text(text = email.error, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                if (email.touched && !it.isFocused) {
                                    vm.setUnTouchedGesture("email", true)
                                }
                                if (it.isFocused) {
                                    vm.setTouchedGesture("email", true)
                                }
                            }
                    )
                    HeightSpacer(1)
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = {
                            vm.setData("password",it)
                        },
                        label = {
                                Text(text = "Password")
                        },
                        leadingIcon = {
                            Icon(Icons.Filled.Lock, contentDescription = "Password")
                        },
                        trailingIcon = {
                           IconButton(onClick = {
                               passwordVisibility = !passwordVisibility
                           }) {
                               Icon(painter = painterResource(id = if(!passwordVisibility) R.drawable.eye else R.drawable.eyeoff), contentDescription = "Eye")
                           }
                        },
                        maxLines = 1,
                        isError = password.touched && password.untouched && password.error !== "",
                        supportingText = {
                            if(password.touched && password.untouched && password.error !== ""){
                                Text(text = password.error, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                if (password.touched && !it.isFocused) {
                                    vm.setUnTouchedGesture("password", true)
                                }
                                if (it.isFocused) {
                                    vm.setTouchedGesture("password", true)
                                }
                            }
                    )
                    HeightSpacer(8)
                    Button(
                        onClick = {
                            focusManager.clearFocus()
                            vm.doLogin(email,password)
                        },
                        enabled = (email.touched && email.error == "") && (password.touched && password.error == ""),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Login")
                    }
                }
            }
        }
    }
}