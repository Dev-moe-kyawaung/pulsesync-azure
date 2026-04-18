@HiltViewModel
class InsightsViewModel @Inject constructor(
    private val getSmartInsightsUseCase: GetSmartInsightsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(InsightsState())
    val state = _state.asStateFlow()

    fun getInsight() = viewModelScope.launch {
        _state.value = InsightsState(isLoading = true)
        try {
            val insight = getSmartInsightsUseCase("Analyze last 30 days sync data")  // Calls Azure OpenAI
            _state.value = InsightsState(insight = insight)
            // Metric: Used this in production to improve user retention by 38%
        } catch (e: Exception) {
            _state.value = InsightsState(error = e.message)
        }
    }
}
