import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Cooperator from '@/components/Cooperator'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Logout from '@/components/Logout'
import ViewAllStudents from '@/components/ViewAllStudents'
import ViewStudentForms from '@/components/ViewStudentForms'
import ViewAllEmployers from '@/components/ViewAllEmployers'
import SemesterStats from '@/components/SemesterStats'
import Reminder from '@/components/Reminder'
import EmployerForm from '@/components/EmployerForm'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Hello
    },
    {
      path: '/app',
      name: 'Cooperator',
      component: Cooperator
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/students',
      name: 'ViewAllStudents',
      component: ViewAllStudents
    },
    {
      path: '/studentForm/:userId',
      name: 'ViewStudentForms',
      component: ViewStudentForms
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/logout',
      name: 'Logout',
      component: Logout
    },
    {
      path: '/employers',
      name: 'ViewAllEmployers',
      component: ViewAllEmployers
    },
    {
      path: '/stats',
      name: 'SemesterStats',
      component: SemesterStats
    },
    {
      path: '/reminder',
      name: 'Reminder',
      component: Reminder
    },
    {
      path: '/employerForm',
      name: 'EmployerForm',
      component: EmployerForm
    }

  ]
})
