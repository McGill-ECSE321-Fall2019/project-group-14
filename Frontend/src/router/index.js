import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Apply from '@/components/Apply'
import Review from '@/components/Review'
import CreateReview from '@/components/CreateReview'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
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
      path: '/reviews/create',
      name: 'create-review',
      component: CreateReview,
    }
  ]
})
