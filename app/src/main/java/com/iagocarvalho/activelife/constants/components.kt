package com.iagocarvalho.activelife.constants

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iagocarvalho.activelife.R

@Composable
fun EmailImput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enableb: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    action: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        enabled = enableb,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = action
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
) {

    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) },
        keyboardActions = onAction
    )

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icons.Default.Close

    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    loading: Boolean = false,
    iscreateAcount: Boolean = false,
    onDone: (String, String) -> Unit = { email, pws -> }
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val keybordController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    Column(
        modifier = Modifier
            .padding(16.dp),
    ) {
        EmailImput(emailState = email)
        PasswordInput(
            modifier = Modifier,
            passwordState = password,
            labelId = "Passaword",
            enabled = !loading,
            passwordVisibility = passwordVisibility
        )
        SubmitButton(
            textId = if (iscreateAcount) "Criar Conta" else "Login",
            loadind = loading,
            validInputs = valid
        ) {
            onDone(email.value.trim(), password.value.trim())
            keybordController?.hide()
        }


    }
}

@Composable
fun SubmitButton(
    textId: String,
    loadind: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = !loadind && validInputs,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(Color(R.color.OrangeApp))
    ) {
        if (loadind) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}


@Preview(showBackground = true)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UseFormeCreateUser(
    loading: Boolean = false,
    onDone: (String, String, String, String, String, String) -> Unit = { name, peso, altura, idade, email, pws -> }
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val peso = remember { mutableStateOf("") }
    val altura = remember { mutableStateOf("") }
    val idade = remember { mutableStateOf("") }


    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val keybordController = LocalSoftwareKeyboardController.current
    val valid =
        remember(email.value, password.value, name.value, peso.value, altura.value, idade.value) {
            email.value.trim().isNotEmpty() && password.value.trim()
                .isNotEmpty() && name.value.trim().isNotEmpty() && peso.value.trim()
                .isNotEmpty() && altura.value.trim().isNotEmpty() && idade.value.trim().isNotEmpty()
        }

    Column() {
        GenericTextFild(TextFild = name, keyboardType = KeyboardType.Text)
        GenericTextFild(TextFild = peso, labelId = "Peso em KG", keyboardType = KeyboardType.Number)
        GenericTextFild(
            TextFild = altura,
            labelId = "Altura em Cm",
           keyboardType = KeyboardType.Number
        )
        GenericTextFild(TextFild = idade, labelId = "Idade", keyboardType = KeyboardType.Number)
        EmailImput(emailState = email)
        PasswordInput(
            modifier = Modifier,
            passwordState = password,
            labelId = "password",
            enabled = !loading,
            passwordVisibility = passwordVisibility
        )
        SubmitButton(textId = "Criar Conta", loadind = loading, validInputs = valid) {
            onDone(
                name.value.trim(),
                peso.value.trim(),
                altura.value.trim(),
                idade.value.trim(),
                email.value.trim(),
                password.value.trim()
            )
            keybordController?.hide()

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericTextFild(
    modifier: Modifier = Modifier,
    TextFild: MutableState<String>,
    labelId: String = "Name",
    enableb: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    action: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = TextFild.value,
        onValueChange = { TextFild.value = it },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enableb,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = action
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(text: MutableState<String>) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            // Hide the keyboard after submitting the search
            keyboardController?.hide()
            //or hide keyboard
            focusManager.clearFocus()

        })
    )
}