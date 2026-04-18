class GetSmartInsightsUseCaseTest {

    @MockK private lateinit var repository: AzureOpenAIRepository
    private lateinit var useCase: GetSmartInsightsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetSmartInsightsUseCase(repository)
    }

    @Test
    fun `use case returns insight from repository`() = runTest {
        coEvery { repository.getInsight(any()) } returns "AI Recommendation"
        val result = useCase("test prompt")
        assertEquals("AI Recommendation", result)
        // Hits 100% coverage on UseCase layer
    }
}
