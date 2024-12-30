<template>
  <header class="app-header">
    <div class="logo">
      <h1>Expense Tracker</h1>
    </div>
    <nav>
      <ul>
        <!-- Show Home, Login, Register when not logged in -->
        <li v-if="!isLoggedIn"><router-link to="/">Home</router-link></li>
        <li v-if="!isLoggedIn"><router-link to="/login">Login</router-link></li>
        <li v-if="!isLoggedIn"><router-link to="/register">Register</router-link></li>

        <!-- Show Expense List, Add Expense, Reports, Logout when logged in -->
        <li v-if="isLoggedIn"><router-link to="/expenseList">Expense List</router-link></li>
        <li v-if="isLoggedIn"><router-link to="/addExpense">Add Expense</router-link></li>
        <li v-if="isLoggedIn"><router-link to="/reports">Reports</router-link></li>
        <li v-if="isLoggedIn"><a href="#" @click.prevent="handleLogout">Logout</a></li>
      </ul>
    </nav>
  </header>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'HeaderComponent',
  computed: {
    ...mapGetters(['isLoggedIn']), 
  },
  methods: {
    ...mapActions(['logoutUser']), 
    async handleLogout() {
      await this.logoutUser();
      this.$router.push('/'); 
    },
  },
};
</script>

<style scoped>

.app-header {
  background-color: #333;
  color: white;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo h1 {
  font-size: 1.8rem;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin: 0;
}

nav ul {
  display: flex;
  gap: 2rem;
  list-style: none;
  margin: 0;
  padding: 0;
}

nav a {
  color: white;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.1rem;
}

nav a:hover {
  color: #f8f8f8; 
  text-decoration: underline;
}

nav ul li {
  transition: all 0.3s ease;
}

nav ul li:hover {
  transform: scale(1.1);
}
</style>
