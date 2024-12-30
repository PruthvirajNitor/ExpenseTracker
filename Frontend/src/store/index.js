import { createStore } from 'vuex';
import axios from 'axios';

export default createStore({
  state: {
    user: null, 
    expenses: [], 
  },

  mutations: {
    SET_USER(state, user) {
      state.user = user; 
    },
    CLEAR_USER(state) {
      state.user = null; 
    },
    ADD_EXPENSE(state, expense) {
      state.expenses.push(expense); 
    },
    SET_EXPENSE(state, expenses) {
      state.expenses = expenses; 
    },
    UPDATE_EXPENSE(state, updatedExpense) {
      const index = state.expenses.findIndex(expense => expense.id === updatedExpense.id);
      if (index !== -1) {
        state.expenses.splice(index, 1, updatedExpense);
      }
    },
    DELETE_EXPENSE(state, expenseId) {
      state.expenses = state.expenses.filter(expense => expense.id !== expenseId);
    },
  },

  actions: {
    
    async loginUser({ commit }, credentials) {
      try {
        const response = await axios.post('http://localhost:8080/user/login', credentials);

        console.log("Response from backend:", response.data);

        const user = response.data;
        commit('SET_USER', user);

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
      localStorage.removeItem('user'); 
      alert('Logged out successfully.');
    },

    checkLoginState({ commit }) {
      const user = localStorage.getItem('user');
      if (user) {
        try {
          commit('SET_USER', JSON.parse(user)); 
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
        
      } catch (error) {
        console.error('Error while registering user:', error);
        alert('Registration failed. Please try again.');
      }
    },


    async updateExpense({ commit }, expense) {
      try {
        const response = await axios.put(`http://localhost:8080/expenses/${expense.id}`, expense);
        commit('UPDATE_EXPENSE', response.data);
      } catch (error) {
        console.error('Error updating expense:', error);
      }
    },

    async fetchExpenses({ commit }) {
      try {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user && user.id) {
          const response = await axios.get(`http://localhost:8080/expense/user/${user.id}`);
          commit('SET_EXPENSE', response.data);
        }
      } catch (error) {
        console.error('Error fetching expenses:', error);
      }
    },
  
    async deleteExpense({ commit, state }, expenseId) {
      try {
        const userId = state.user?.id;
        if (!userId) throw new Error('User not logged in.');
  
        await axios.delete(`http://localhost:8080/expense/${expenseId}`);
        
        commit('SET_EXPENSE', state.expenses.filter(expense => expense.id !== expenseId));
      } catch (error) {
        console.error('Error deleting expense:', error);
      }
    },
    
  },


  getters: {
    isLoggedIn(state) {
      return !!state.user; 
    },
    currentUser(state) {
      return state.user; 
    },
    getExpenses(state) {
      return state.expenses; 
    },
  },
});
