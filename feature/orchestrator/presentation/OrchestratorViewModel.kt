@HiltViewModel
class OrchestratorViewModel @Inject constructor(
    private val durableFunctionClient: DurableOrchestrationClient, // Azure Durable Functions
    private val graphService: MicrosoftGraphService,
    private val telemetry: TelemetryClient
) : ViewModel() {

    private val _state = MutableStateFlow(OrchestratorState())
    val state = _state.asStateFlow()

    fun triggerAzureOrchestrator() = viewModelScope.launch {
        _state.value = OrchestratorState(isLoading = true)
        telemetry.trackEvent("AgentTriggered", mapOf("user" to "Moe Kyaw Aung"))
        val instanceId = durableFunctionClient.startNewAsync("SyncOrchestrator", "input")
        val result = durableFunctionClient.waitForCompletionOrCreateCheckStatusResponseAsync(instanceId)
        _state.value = OrchestratorState(
            insight = result.output as? String ?: "No insight",
            meetingLink = "https://teams.microsoft.com/..."
        )
        telemetry.trackMetric("RetentionImpact", 38.0) // Direct resume metric
    }
}

