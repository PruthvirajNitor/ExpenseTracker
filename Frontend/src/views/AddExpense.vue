<template>
  <div class="add-expense-form">
    <h2>Add Expense</h2>
    <form @submit.prevent="submitExpenseForm">
      <div class="form-group">
        <label for="title">Title:</label>
        <input type="text" id="title" v-model="expense.title" required />
      </div>

      <div class="form-group">
        <label for="amount">Amount:</label>
        <input type="number" id="amount" v-model="expense.amount" required min="0" step="0.01" />
      </div>

      <div class="form-group">
        <label for="category">Category:</label>
        <select id="category" v-model="expense.category" required>
          <option value="FOOD">Food</option>
          <option value="TRANSPORT">Transport</option>
          <option value="ENTERTAINMENT">Entertainment</option>
          <option value="OTHER">Other</option>
        </select>
      </div>

      <div class="form-group">
        <label for="date">Date:</label>
        <input type="date" id="date" v-model="expense.date" required />
      </div>

      <button type="submit">Add Expense</button>
    </form>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'AddExpenseForm',
  data() {
    return {
      expense: {
        title: '',
        amount: 0,
        category: 'FOOD',
        date: '',
      },
    };
  },
  computed: {
    
    ...mapState(['user']),
  },
  methods: {
    ...mapActions(['addExpense']), 
    async submitExpenseForm() {
      
      if (!this.user || !this.user.id) {
        alert('You must be logged in to add an expense.');
        return;
      }

      console.log("Form data: ",this.expense);
      

      
      const expenseData = {
        ...this.expense,
        userId: this.user.id, 
      };

      
      const response = await this.addExpense(expenseData);

      console.log("Added expense: ",response );
      

      
      this.$router.push('/expenseList');

      console.log("Form submitted");
      
    },
  },
};
</script>

<style scoped>

.add-expense-form {
  max-width: 450px;
  margin: 50px auto;
  padding: 30px;
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

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
  font-size: 16px;
  color: #333;
  font-weight: 600;
}

input,
select {
  width: 100%;
  padding: 12px;
  font-size: 16px;
  border: 1px solid #555;
  border-radius: 6px;
  box-sizing: border-box;
  background-color: #f9f9f9;
  color: #333;
}

button {
  width: 100%;
  padding: 14px;
  background-color: #aaa;
  color: white;
  border: none;
  border-radius: 6px;
  font-size: 18px;
  font-weight: 600;
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
