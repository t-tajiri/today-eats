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
    decideEats () {
      this.eats = '🍛 カレーライス'
    }
  }
}
</script>
