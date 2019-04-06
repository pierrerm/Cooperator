import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Logout from '@/components/Logout'
import ViewAllStudents from '@/components/ViewAllStudents'
import ViewStudentForms from '@/components/ViewStudentForms'
import ViewStudentCoops from '@/components/ViewStudentCoops'
import ViewEmployerForms from '@/components/ViewEmployerForms'
import ViewEmployerCoops from '@/components/ViewEmployerCoops'
import ViewAllEmployers from '@/components/ViewAllEmployers'
import SemesterStats from '@/components/SemesterStats'
import Reminder from '@/components/Reminder'
import ViewForm from '@/components/ViewForm'
import ViewCoop from '@/components/ViewCoop'
import ViewReminders from '@/components/ViewReminders'
import ViewGroup3StudentCoops from '@/components/ViewGroup3StudentCoops'

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Hello
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/students',
      name: 'ViewAllStudents',
      component: ViewAllStudents,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/studentForms/:userId',
      name: 'ViewStudentForms',
      component: ViewStudentForms,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/studentCoops/:userId',
      name: 'ViewStudentCoops',
      component: ViewStudentCoops,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/employerForms/:userId',
      name: 'ViewEmployerForms',
      component: ViewEmployerForms,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/employerCoops/:userId',
      name: 'ViewEmployerCoops',
      component: ViewEmployerCoops,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/coop/:coopId',
      name: 'ViewCoop',
      component: ViewCoop,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/logout',
      name: 'Logout',
      component: Logout,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/employers',
      name: 'ViewAllEmployers',
      component: ViewAllEmployers,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/stats',
      name: 'SemesterStats',
      component: SemesterStats,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/reminder',
      name: 'Reminder',
      component: Reminder,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/studentForm/:formId',
      name: 'ViewForm',
      component: ViewForm,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/createReminder',
      name: 'CreateReminder',
      component: ViewForm,
      meta:{
        requiresAuth: true
      }
    },
	{
      path: '/studentReminder',
      name: 'StudentReminder',
      component: ViewReminders,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/studentCoopGroup3',
      name: 'ViewGroup3StudentCoops',
      component: ViewGroup3StudentCoops,
      meta:{
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  if(to.matched.some(record => record.meta.requiresAuth)) {
      if (localStorage.getItem('loggedIn') == "Administrator") {
          next()
      } else {
        next({
          path: '/login'
        })
      }
  } else {
      next() 
  }
})

export default router