import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Apply from '@/components/Apply'
import Review from '@/components/Review'
import Login from '@/components/Login'
import Notification from '@/components/Notification'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/apply',
      name: 'Apply',
      component: Apply
    },
    {
      path: '/reviews',
      name: 'Review',
      component: Review
    },
    {
      path: '/notifications',
      name: 'Notification',
      component: Notification
    }

  ]
})
