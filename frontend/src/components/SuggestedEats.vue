<template>
  <div id="suggested-eats">
    {{ eats }}
  </div>
</template>

<script>
export default {
  name: 'SuggestedEats',
  props: {
    eats: {
      type: String,
      default: ''
    }
  }
}
</script>
