<template>
    <div class="expense-list">
      <h2>Your Expenses</h2>
      <table>
        <thead>
          <tr>
            <th>Title</th>
            <th>Amount</th>
            <th>Category</th>
            <th>Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="expense in expenses" :key="expense.id">
            <td>{{ expense.title }}</td>
            <td>{{ expense.amount }}</td>
            <td>{{ expense.category }}</td>
            <td>{{ expense.date }}</td>
            <td>
              <button @click="editExpense(expense)">Edit</button>
              <button @click="deleteExpense(expense.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex';
  
  export default {
    name: 'ExpenseList',
    computed: {
      ...mapState(['expenses']), // Map expenses from Vuex store
    },
    methods: {
      ...mapActions(['fetchExpenses', 'deleteExpense']), // Map Vuex actions
      editExpense(expense) {
        // Redirect to AddExpenseForm with the current expense details
        this.$router.push({
          name: 'AddExpenseForm',
          query: {
            id: expense.id,
            title: expense.title,
            amount: expense.amount,
            category: expense.category,
            date: expense.date,
          },
        });
      },
      async deleteExpense(id) {
        if (confirm('Are you sure you want to delete this expense?')) {
          await this.deleteExpense(id); // Call Vuex action to delete the expense
          alert('Expense deleted successfully.');
        }
      },
    },
    created() {
      // Fetch expenses when the component is created
      this.fetchExpenses();
    },
  };
  </script>
  
  <style scoped>
  .expense-list {
    max-width: 800px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #444;
    border-radius: 8px;
    background-color: white;
    color: #333;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
    font-size: 24px;
    color: #333;
  }
  
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  th,
  td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: left;
  }
  
  th {
    background-color: #f4f4f4;
  }
  
  button {
    margin-right: 5px;
    padding: 5px 10px;
    background-color: #aaa;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 14px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #888;
  }
  
  button:active {
    background-color: #666;
  }
  </style>
  