<template>
  <div class="flex">
    <div class="mt-10 m-auto">
      <h1 class="text-4xl">
        {{ title }}
      </h1>
      <div class="mt-10 text-center">
        <Button
          @click="decideEats"
          class="bg-blue-500 hover:bg-blue-700 text-white font-bold"
          name="ご飯を決める"
          id="decide-eats"
        />
      </div>
    </div>
    <transition
      name="modal"
      mode="out-in"
    >
      <div
        class="absolute w-full h-full bg-translucent-gray"
        v-if="showModal"
      >
        <SuggestedEats
          :eats="eats"
          @click="toggleModal"
        />
      </div>
    </transition>
  </div>
</template>

<script>
import Button from '@/components/Button.vue'
import SuggestedEats from '@/components/SuggestedEats.vue'
import EatsRepository from '@/repository/EatsRepository.js'

export default {
  name: 'Home',
  components: {
    Button,
    SuggestedEats
  },
  data: () => ({
    api: new EatsRepository(),
    eats: null,
    showModal: false,
    title: '今日のご飯を決めよう！'
  }),
  methods: {
    async decideEats () {
      const { data } = await this.api.getTodayEats()
      this.eats = data.name

      this.showModal = true
    },
    toggleModal () {
      this.showModal = !this.showModal
    }
  }
}
</script>

<style scoped>
.modal-enter-active {
  transition: opacity 1s;
}
.modal-enter, .modal-leave-to {
  opacity: 0;
}
</style>
