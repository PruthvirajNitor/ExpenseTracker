import { createStore } from 'vuex';
import axios from 'axios';

export default createStore({
  state: {
    user: null, // Stores the logged-in user's data
    expenses: [], // To store list of expenses
  },

  mutations: {
    SET_USER(state, user) {
      state.user = user; // Set the user object in Vuex state
    },
    CLEAR_USER(state) {
      state.user = null; // Clear user data from Vuex state
    },
    ADD_EXPENSE(state, expense) {
      state.expenses.push(expense); // Add expense to state
    },
    SET_EXPENSE(state, expenses) {
      state.expenses = expenses; // Replace expense list in state
    },
  },

  actions: {
    
    async loginUser({ commit }, credentials) {
      try {
        const response = await axios.post('http://localhost:8080/user/login', credentials);

        console.log("Response from backend:", response.data);

        const user = response.data;
        commit('SET_USER', user);

        // Store user in localStorage
        localStorage.setItem('user', JSON.stringify(user));

        console.log("Logged in user:", user);
        alert('Login successful!');
      } catch (error) {
        console.error('Error during login:', error);
        alert('Login failed. Please try again.');
      }
    },

    logoutUser({ commit }) {
      commit('CLEAR_USER');
      localStorage.removeItem('user'); // Clear user from localStorage
      alert('Logged out successfully.');
    },

    checkLoginState({ commit }) {
      const user = localStorage.getItem('user');
      if (user) {
        try {
          commit('SET_USER', JSON.parse(user)); // Parse and set user from localStorage
        } catch (error) {
          console.error('Error parsing user data from localStorage:', error);
        }
      }
    },

    async addExpense({ commit }, expenseData) {
      try {
        const response = await axios.post('http://localhost:8080/expense', expenseData);
        commit('ADD_EXPENSE', response.data);
        alert('Expense added successfully!');
        return response;
      } catch (error) {
        console.error('Error adding expense:', error);
        alert('Unable to add expense');
      }
    },

    async registerUser({commit}, userData) {
      try {
        const response = await axios.post('http://localhost:8080/user', userData);
        alert('Registration successful!');
        console.log(response.data);
        commit("SET_USER",userData);
        commit('CLEAR_USER');
        // Optionally, handle immediate login after registration
      } catch (error) {
        console.error('Error while registering user:', error);
        alert('Registration failed. Please try again.');
      }
    },
    
  },


  getters: {
    isLoggedIn(state) {
      return !!state.user; // Determine login status based on user presence
    },
    currentUser(state) {
      return state.user; // Return user object
    },
    getExpenses(state) {
      return state.expenses; // Return expense list
    },
  },
});
