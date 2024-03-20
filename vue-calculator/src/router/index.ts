import { createRouter, createWebHistory } from 'vue-router'
import Calculator from '../components/Calculator.vue'
import Contact_form from '../components/Contact_form.vue'
import LoginForm from '@/components/LoginForm.vue'

const routes = [
  {
    path: '/',
    component: LoginForm
  },
  {
    path: '/calculator',
    name: 'Calculator',
    component: Calculator,
    meta: { requiresAuth: true }
  },
  {
    path: '/contactform',
    component: Contact_form
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router