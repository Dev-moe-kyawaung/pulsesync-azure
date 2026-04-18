@Composable
fun SwarmScreen(viewModel: SwarmViewModel = hiltViewModel()) {
    val state by viewModel.swarmState.collectAsState()
    PulseTheme {
        Column {
            Text("Azure AI Agent Swarm Status — 7 Agents Active", style = MaterialTheme.typography.headlineLarge)
            LazyColumn {
                items(state.agents) { agent ->
                    Card { Text("${agent.name}: ${agent.status} — ${agent.lastInsight}") }
                }
            }
            PulseButton("Trigger Full Swarm") { viewModel.triggerSwarm() }
            // Real-time SignalR updates from Azure + push to Wear OS complication
        }
    }
}

