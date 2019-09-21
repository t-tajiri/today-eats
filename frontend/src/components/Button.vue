<template>
  <button
    class="px-4 py-2 rounded-lg"
    @click="onClick"
    type="submit"
  >
    {{ name }}
  </button>
</template>

<script>
export default {
  name: 'Button',
  props: {
    name: {
      type: String,
      required: true
    }
  },
  methods: {
    onClick () {
      this.$emit('click')
    }
  }
}
</script>
