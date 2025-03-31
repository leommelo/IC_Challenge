<template>
  <div class="table-container">
    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>Registro ANS</th>
            <th>CNPJ</th>
            <th>Razão Social</th>
            <th>Nome Fantasia</th>
            <th>Modalidade</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(operadora, index) in operadoras" :key="index" 
              :class="index % 2 === 0 ? 'row-even' : 'row-odd'">
            <td>{{ operadora.registro_ans }}</td>
            <td>{{ operadora.cnpj }}</td>
            <td>{{ operadora.razao_social }}</td>
            <td>{{ operadora.nome_fantasia }}</td>
            <td>{{ operadora.modalidade }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    
    <div v-if="operadoras.length === 0" class="empty-message">
      <p>Nenhuma operadora para exibir.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'OperadoraTable',
  props: {
    operadoras: {
      type: Array,
      required: true,
      default: () => []
    }
  }
}
</script>

<style scoped>
.table-container {
  background-color: white;
  border-radius: 12px;
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  border: 1px solid #e5e7eb;
}

.table-wrapper {
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
  background-color: white;
}

th {
  padding: 16px 24px;
  text-align: left;
  font-size: 14px;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.05em;
  color: #1e40af;
  border-bottom: 2px solid #bfdbfe;
  background: linear-gradient(to right, #f0f9ff, #dbeafe);
  position: relative;
}

td {
  padding: 20px 24px;
  white-space: nowrap;
  font-size: 16px;
  color: #1f2937;
  border-right: 1px solid #f3f4f6;
  position: relative;
  background-color: inherit;
}

td:last-child {
  border-right: none;
}

tr.row-even {
  background-color: white;
}

tr.row-odd {
  background-color: #f0f9ff;
}

tr:hover td {
  background-color: rgba(59, 130, 246, 0.1);
}

.empty-message {
  text-align: center;
  padding: 32px;
  background-color: white;
}

.empty-message p {
  font-size: 20px;
  color: #6b7280;
}

@media (max-width: 768px) {
  table {
    font-size: 14px;
  }
  
  th, td {
    padding: 12px;
  }
}
</style>