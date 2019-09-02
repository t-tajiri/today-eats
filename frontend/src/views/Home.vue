<template>
  <div class="home">
    <h1>{{ title }}</h1>
    <Button @click="decideEats" />
    <div v-if="eats">
      <SuggestedEats :eats="eats" />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Button from '@/components/Button.vue'
import SuggestedEats from '@/components/SuggestedEats'

export default {
  name: 'Home',
  components: {
    Button,
    SuggestedEats
  },
  data: () => ({
    title: '今日のご飯を決めよう！',
    eats: ''
  }),
  methods: {
    async decideEats () {
      const { data } = await axios.get('http://localhost:8090/today-eats', {
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
          'Access-Control-Allow-Headers': '*'
        }
      })
      this.eats = data.name
    }
  }
}
</script>
