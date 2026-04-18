resource "azurerm_kubernetes_cluster" "swarm_aks" {
  name                = "pulseswarm-aks"
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix          = "pulseswarm"
  default_node_pool {
    name       = "agentpool"
    node_count = 5
    vm_size    = "Standard_D4s_v5"
  }
}

resource "azurerm_machine_learning_workspace" "swarm_ml" {
  name                = "pulseswarm-ml"
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name
  sku_name            = "Basic"
}

resource "azurerm_ai_search_service" "vector_memory" {
  name                = "pulseswarm-search"
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name
  sku                 = "standard"
}

resource "azurerm_logic_app_workflow" "swarm_escalation" {
  name                = "swarm-escalation-workflow"
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name
}

