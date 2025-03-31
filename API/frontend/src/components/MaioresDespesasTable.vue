<template>
    <div class="table-container">
      <table class="despesas-table">
        <thead>
          <tr>
            <th>#</th>
            <th>Razão Social</th>
            <th>Nome Fantasia</th>
            <th>Valor Total</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(despesa, index) in despesas" :key="`${despesa.razao_social}-${index}`">
            <td class="index-cell">{{ index + 1 }}</td>
            <td class="empresa-dado">{{ despesa.razao_social }}</td>
            <td class="empresa-dado">{{ despesa.nome_fantasia || '-' }}</td>
            <td class="valor-cell">{{ formatarValor(despesa.total_despesas) }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  export default {
    name: 'MaioresDespesasTable',
    props: {
      despesas: {
        type: Array,
        required: true
      }
    },
    methods: {
      formatarValor(valor) {
        return new Intl.NumberFormat('pt-BR', {
          style: 'currency',
          currency: 'BRL',
          minimumFractionDigits: 2
        }).format(valor);
      }
    }
  }
  </script>
  
  <style scoped>
  .table-container {
    overflow-x: auto;
    margin-bottom: 16px;
  }
  
  .despesas-table {
    width: 100%;
    border-collapse: collapse;
    font-size: 16px;
  }
  
  .despesas-table th,
  .despesas-table td {
    padding: 12px 16px;
    text-align: left;
    border-bottom: 1px solid #e5e7eb;
  }
  
  .despesas-table th {
    background-color: #f9fafb;
    font-weight: 600;
    color: #4b5563;
  }
  
  .despesas-table tr:hover {
    background-color: #f3f4f6;
  }
  
  .index-cell {
    text-align: center;
    color: #6b7280;
    font-weight: 500;
  }
  
  .valor-cell {
    text-align: right;
    font-weight: 600;
    color: #1e40af;
  }

  .empresa-dado{
    color: #374151;
    font-weight: 500;
  }

  </style>