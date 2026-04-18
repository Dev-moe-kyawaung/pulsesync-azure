@Composable
fun InsightsScreen(viewModel: InsightsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    PulseTheme {
        Column {
            Text("Azure OpenAI Smart Insights", style = MaterialTheme.typography.headlineMedium)
            when {
                state.isLoading -> LoadingIndicator()
                state.insight != null -> Text(state.insight!!, style = MaterialTheme.typography.bodyLarge)
                state.error != null -> Text("Error: ${state.error}")
            }
            PulseButton("Get AI Insight") { viewModel.getInsight() }
        }
    }
}

