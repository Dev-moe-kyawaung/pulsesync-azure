resource cosmos 'Microsoft.DocumentDB/databaseAccounts@2023-04-15' = {
  name: 'pulsesync-cosmos'
  location: resourceGroup().location
  properties: { databaseAccountOfferType: 'Standard' }
}

resource openai 'Microsoft.CognitiveServices/accounts@2023-05-01' = {
  name: 'pulsesync-openai'
  location: resourceGroup().location
  kind: 'OpenAI'
  properties: { customSubDomainName: 'pulsesync' }
  sku: { name: 'S0' }
}

resource signalr 'Microsoft.SignalRService/signalR@2023-02-01' = {
  name: 'pulsesync-signalr'
  location: resourceGroup().location
  sku: { name: 'Standard_S1' }
}

resource appinsights 'Microsoft.Insights/components@2020-02-02' = {
  name: 'pulsesync-insights'
  location: resourceGroup().location
  properties: { Application_Type: 'web' }
}

// Add Functions, Key Vault, Static Web Apps, Logic Apps for full autonomous agent
output azureUrl string = signalr.properties.hostName

