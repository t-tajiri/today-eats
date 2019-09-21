import Vue from 'vue'
import App from './App.vue'
import router from './router'
import '@/assets/tailwind.css'
import 'vue-awesome/icons/utensils'
import 'vue-awesome/icons/cog'
import Icon from 'vue-awesome/components/Icon'

Vue.component('v-icon', Icon)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
