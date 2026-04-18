interface AzureOpenAIService {

    @POST("")
    suspend fun getCompletion(
        @Body request: OpenAIRequest
    ): OpenAIResponse
}

data class OpenAIRequest(
    val messages: List<Message>,
    val max_tokens: Int = 300,
    val temperature: Double = 0.7
)

data class Message(val role: String = "user", val content: String)

data class OpenAIResponse(
    val choices: List<Choice>
)

data class Choice(val message: MessageResponse)

data class MessageResponse(val content: String)

