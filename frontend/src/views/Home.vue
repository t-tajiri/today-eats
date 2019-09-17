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
import Button from '@/components/Button.vue'
import SuggestedEats from '@/components/SuggestedEats.vue'
import HomeRepository from '@/repository/HomeRepository.js'

export default {
  name: 'Home',
  components: {
    Button,
    SuggestedEats
  },
  data: () => ({
    title: '今日のご飯を決めよう！',
    eats: null
  }),
  methods: {
    async decideEats () {
      const api = new HomeRepository()
      const { data } = await api.getTodayEats()
      this.eats = data.name
    }
  }
}
</script>
