class GetSmartInsightsUseCase @Inject constructor(
    private val repository: AzureOpenAIRepository
) {
    suspend operator fun invoke(prompt: String): String = repository.getInsight(prompt)
}

