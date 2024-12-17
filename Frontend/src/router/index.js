import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import RegisterPage from "../views/RegisterForm.vue"
import LoginPage from "@/views/LoginForm.vue";
import ExpenseList from "@/views/ExpenseList.vue";
import AddExpense from "@/views/AddExpense.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/register",
    name: "register",
    component: RegisterPage
  },
  {
    path: "/login",
    name: "login",
    component: LoginPage
  },
  {
    path: "/expenseList",
    name: "expense-list",
    component: ExpenseList
  },
  {
    path: "/addExpense",
    name: "add-expense",
    component: AddExpense
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
