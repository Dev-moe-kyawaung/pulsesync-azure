@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    activity: Activity
) {
    val state by viewModel.state.collectAsState()
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        viewModel.handleMsalResult(result.resultCode, result.data)
    }

    PulseTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PulseButton("Login with Microsoft Azure AD") {
                viewModel.loginWithAzureAD(activity, launcher)
            }
            if (state.isLoading) CircularProgressIndicator()
            if (state.error != null) Text("Error: ${state.error}", color = Color.Red)
        }
    }
}

