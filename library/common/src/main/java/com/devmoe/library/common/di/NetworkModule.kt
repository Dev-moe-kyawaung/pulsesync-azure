@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("api-key", BuildConfig.AZURE_OPENAI_KEY) // Store in local.properties or Azure Key Vault
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://YOUR-AZURE-OPENAI-INSTANCE.openai.azure.com/openai/deployments/gpt-4o/chat/completions?api-version=2024-02-15-preview")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAzureOpenAIService(retrofit: Retrofit): AzureOpenAIService =
        retrofit.create(AzureOpenAIService::class.java)
}
