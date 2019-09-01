<template>
  <button
    @click="onClick"
    id="decide-eats"
    type="submit"
  >
    {{ buttonName }}
  </button>
</template>

<script>
export default {
  name: 'Button',
  data: () => ({
    buttonName: 'ご飯を決める'
  }),
  methods: {
    onClick () {
      this.$emit('click')
    }
  }
}
</script>
