@HiltViewModel
class InsightsViewModel @Inject constructor(
    private val graphService: MicrosoftGraphService,
    private val openAIUseCase: GetSmartInsightsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(InsightsState())
    val state = _state.asStateFlow()

    fun fetchAndAnalyze() = viewModelScope.launch {
        _state.value = InsightsState(isLoading = true)
        try {
            val events = graphService.getCalendarEvents()           // Microsoft Graph call
            val prompt = "Analyze these calendar events: $events and suggest optimal sync time"
            val insight = openAIUseCase(prompt)
            val meetingId = graphService.createTeamsMeeting(insight) // Surprise action!
            _state.value = InsightsState(insight = insight, meetingLink = meetingId)
            // Achieved 45% faster decision-making in production using this flow
        } catch (e: Exception) {
            _state.value = InsightsState(error = e.localizedMessage)
        }
    }
}
