<template class="fade-in">
  <div class="container">
    <div class="search-panel">
      <h1>Consulta de Operadoras de Saúde</h1>
      <div class="search-form">
        <div class="input-group">
          <label for="operadora-id">ID da Operadora:</label>
          <input
            id="operadora-id"
            v-model="operadoraId"
            @keyup.enter="buscarOperadora"
            type="text"
            placeholder="Digite o ID da operadora..."
          />
        </div>
        <div class="button-container">
          <button
            @click="buscarOperadora"
            :disabled="isLoading"
          >
            <span v-if="isLoading">Buscando...</span>
            <span v-else>Buscar</span>
          </button>
        </div>
      </div>
    </div>

    <div v-if="error" class="error-message">
      {{ error }}
    </div>

    <div v-if="isLoading" class="loading">
      <div class="spinner"></div>
      <p>Carregando resultados...</p>
    </div>

    <div v-else-if="results.length > 0" class="fade-in">
      <div class="results-header">
        <h2>Resultados da Busca</h2>
        <div class="badge">
          {{ results.length }} encontrado(s)
        </div>
      </div>
      <OperadoraTable :operadoras="results" />
    </div>

    <div v-else-if="hasSearched" class="no-results">
      <p>Nenhuma operadora encontrada com o ID "{{ lastOperadoraId }}".</p>
    </div>

    <div class="gastos-section fade-in">
      <div class="section-header">
        <h2>Maiores Gastos em Assistência à Saúde</h2>
        <div class="toggle-container">
          <button 
            @click="selecionarPeriodo('trimestre')" 
            :class="['toggle-button', { active: periodoSelecionado === 'trimestre' }]"
          >
            Últimos 3 Meses
          </button>
          <button 
            @click="selecionarPeriodo('semestre')" 
            :class="['toggle-button', { active: periodoSelecionado === 'semestre' }]"
          >
            Últimos 7 Meses
          </button>
          <button 
            @click="selecionarPeriodo('ano')" 
            :class="['toggle-button', { active: periodoSelecionado === 'ano' }]"
          >
            Últimos 12 Meses
          </button>
        </div>
      </div>

      <div v-if="loadingDespesas" class="loading">
        <div class="spinner"></div>
        <p>Carregando dados de despesas...</p>
      </div>

      <div v-else-if="erroDespesas" class="error-message">
        {{ erroDespesas }}
      </div>

      <div v-else-if="despesas.length > 0">
        <MaioresDespesasTable :despesas="despesas.slice(0, 5)" />
      </div>

      <div v-else class="no-results">
        <p>Nenhum dado de despesa disponível no momento.</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import OperadoraTable from './components/OperadoraTable.vue';
import MaioresDespesasTable from './components/MaioresDespesasTable.vue';

export default {
  name: 'App',
  components: {
    OperadoraTable,
    MaioresDespesasTable
  },
  setup() {
    const operadoraId = ref('');
    const lastOperadoraId = ref('');
    const results = ref([]);
    const isLoading = ref(false);
    const error = ref('');
    const hasSearched = ref(false);

    // Novas variáveis para a seção de despesas
    const despesas = ref([]);
    const loadingDespesas = ref(false);
    const erroDespesas = ref('');
    const periodoSelecionado = ref('trimestre'); // Padrão: trimestre (3 meses)

    const buscarOperadora = async () => {
      if (!operadoraId.value.trim()) {
        error.value = 'Por favor, digite um ID de operadora válido.';
        return;
      }

      error.value = '';
      isLoading.value = true;
      hasSearched.value = true;
      lastOperadoraId.value = operadoraId.value;

      try {
        const response = await axios.get(`http://localhost:8000/api/operadoras/${operadoraId.value}`);
        
        // Se a resposta for um objeto único, convertemos para um array
        if (response.data && !Array.isArray(response.data)) {
          results.value = [response.data];
        } else {
          results.value = response.data || [];
        }
      } catch (err) {
        console.error('Erro ao buscar operadora:', err);
        error.value = `Ocorreu um erro ao buscar a operadora com ID "${operadoraId.value}". Por favor, verifique o ID e tente novamente.`;
        results.value = [];
      } finally {
        isLoading.value = false;
      }
    };

    // Função para buscar as maiores despesas
    const buscarMaioresDespesas = async (periodo) => {
      loadingDespesas.value = true;
      erroDespesas.value = '';

      try {
        let endpoint;
        
        // Determinar o endpoint com base no período selecionado
        if (periodo === 'trimestre') {
          endpoint = 'http://localhost:8000/api/demonstracoes/maiores-despesas/trimestre';
        } else if (periodo === 'semestre') {
          endpoint = 'http://localhost:8000/api/demonstracoes/maiores-despesas/sete-meses';
        } else { // ano
          endpoint = 'http://localhost:8000/api/demonstracoes/maiores-despesas/ano';
        }

        const response = await axios.get(endpoint);
        despesas.value = response.data || [];
      } catch (err) {
        console.error(`Erro ao buscar maiores despesas do ${periodo}:`, err);
        erroDespesas.value = `Ocorreu um erro ao buscar os dados de maiores despesas. Por favor, tente novamente mais tarde.`;
        despesas.value = [];
      } finally {
        loadingDespesas.value = false;
      }
    };

    // Função para alternar entre períodos
    const selecionarPeriodo = (periodo) => {
      if (periodoSelecionado.value !== periodo) {
        periodoSelecionado.value = periodo;
        buscarMaioresDespesas(periodo);
      }
    };

    // Carregar os dados de despesas quando o componente é montado
    onMounted(() => {
      buscarMaioresDespesas(periodoSelecionado.value);
    });

    return {
      operadoraId,
      lastOperadoraId,
      results,
      isLoading,
      error,
      hasSearched,
      buscarOperadora,
      // Novas propriedades
      despesas,
      loadingDespesas,
      erroDespesas,
      periodoSelecionado,
      selecionarPeriodo
    };
  }
};
</script>

<style>
html, body {
  background-color: #f9fafb;
  min-height: 100vh;
  margin: 0;
  padding: 0;
  font-family: 'Inter', sans-serif;
}

#app {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  width: 100vw;
}

.fade-in {
  animation: fadeIn 0.3s ease-in-out;
  
}


.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 24px;
  min-height: 100vh;
  width: 50vw;
  
}

.search-panel {
  background: linear-gradient(to right, #e0f2fe, #f0f9ff);
  padding: 24px;
  border-radius: 12px;
  margin-bottom: 32px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border: 1px solid #bfdbfe;
}

h1 {
  font-size: 30px;
  font-weight: bold;
  text-align: center;
  color: #1e40af;
  margin-bottom: 16px;
}

.search-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
}

@media (min-width: 768px) {
  .search-form {
    flex-direction: row;
    justify-content: center;
  }
}

.input-group {
  width: 100%;
}

@media (min-width: 768px) {
  .input-group {
    width: 66.666667%;
  }
}

label {
  display: block;
  font-size: 18px;
  font-weight: 500;
  color: #1e40af;
  margin-bottom: 8px;
}

input {
  width: 100%;
  padding: 12px 16px;
  font-size: 18px;
  border: 1px solid #93c5fd;
  border-radius: 8px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
}

input:focus {
  outline: none;
  border-color: #3b82f6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.25);
}

.button-container {
  width: 100%;
  margin-top: 24px;
}

@media (min-width: 768px) {
  .button-container {
    width: auto;
    margin-top: 0;
    padding-top: 24px;
  }
}

button {
  width: 100%;
  padding: 12px 32px;
  background-color: #2563eb;
  color: white;
  font-size: 18px;
  font-weight: 500;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.2s;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

@media (min-width: 768px) {
  button {
    width: auto;
  }
}

button:hover {
  background-color: #1d4ed8;
}

button:disabled {
  background-color: #93c5fd;
  cursor: not-allowed;
}

.error-message {
  background-color: #fee2e2;
  color: #b91c1c;
  padding: 24px;
  border-radius: 12px;
  margin-bottom: 32px;
  font-size: 18px;
  font-weight: 500;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border: 1px solid #fecaca;
}

.loading {
  text-align: center;
  padding: 48px;
}

.spinner {
  display: inline-block;
  width: 48px;
  height: 48px;
  border-radius: 50%;
  border: 4px solid rgba(203, 213, 225, 0.3);
  border-top-color: #3b82f6;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}

.loading p {
  margin-top: 16px;
  font-size: 20px;
  color: #6b7280;
}

.results-header {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

h2 {
  font-size: 24px;
  font-weight: 600;
  color: #1f2937;
  margin: 0;
}

.badge {
  margin-left: 12px;
  background-color: #dbeafe;
  color: #1e40af;
  padding: 4px 12px;
  border-radius: 9999px;
  font-size: 14px;
  font-weight: 500;
}

.no-results {
  text-align: center;
  padding: 40px;
  background-color: white;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border: 1px solid #e5e7eb;
}

.no-results p {
  font-size: 20px;
  color: #6b7280;
}

.fade-in {
  animation: fadeIn 0.3s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.gastos-section {
  margin-top: 48px;
  background-color: white;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border: 1px solid #e5e7eb;
  padding: 24px;
  width: 100%;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  flex-wrap: wrap;
  gap: 16px;
}

.toggle-container {
  display: flex;
  gap: 8px;
}

.toggle-button {
  background-color: #e0f2fe;
  color: #1e40af;
  font-size: 16px;
  padding: 8px 16px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  font-weight: 500;
}

.toggle-button:hover {
  background-color: #bfdbfe;
}

.toggle-button.active {
  background-color: #3b82f6;
  color: white;
}
</style>