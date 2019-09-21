<template>
  <div
    class="flex h-screen"
  >
    <div class="bg-white w-full max-w-md mt-64 mb-auto mx-auto rounded">
      <span class="block text-4xl my-10 text-center">今日のご飯は・・・</span>
      <span class="block text-2xl mb-8 text-center">
        <span
          id="suggested-eats"
          class="select-all"
        >
          {{ eats }}
        </span>
        に決まり！
      </span>
      <div class="text-center mb-8">
        <Button
          @click="closeModal"
          class="bg-blue-500 hover:bg-blue-700 text-white font-bold"
          name="OK"
        />
      </div>
    </div>
  </div>
</template>

<script>
import Button from '@/components/Button.vue'
export default {
  name: 'SuggestedEats',
  components: { Button },
  props: {
    eats: {
      type: String,
      default: ''
    }
  },
  methods: {
    closeModal () {
      this.$emit('click')
    }
  }
}
</script>
