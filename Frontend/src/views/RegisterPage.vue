<template>
  <div class="register-form">
    <h2>Register</h2>
    <form @submit.prevent="registerUser">
  
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="form.name" required />
      </div>
  
      <div class="form-group">
        <label for="phone">Phone Number:</label>
        <input type="tel" id="phone" v-model="form.phone" required />
      </div>
  
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="form.email" required />
      </div>
  
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="form.password" required />
      </div>
  
      <button type="submit">Register</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "RegisterForm",
  data() {
    return {
      form: {
        name: "",
        phone: "",
        email: "",
        password: "",
      },
    };
  },
  methods: {
    async registerUser() {
      try {
        const response = await axios.post("http://localhost:8080/user", this.form);
        alert("Registration Successful!");
        console.log(response.data);
      } catch (error) {
        console.error("Error while registering user:", error);
        alert("Registration failed. Please try again.");
      }
    },
  },
};
</script>

<style scoped>
.register-form {
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

input {
  width: 100%;
  padding: 12px;
  font-size: 16px;
  border: 1px solid #555;
  border-radius: 6px;
  box-sizing: border-box;
  background-color: #f9f9f9;
  color: #333;
  transition: border 0.3s ease;
}

input:focus {
  outline: none;
  border-color: #42b983;
  box-shadow: 0 0 5px rgba(66, 185, 131, 0.5);
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
