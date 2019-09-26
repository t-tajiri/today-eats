<template>
  <div>
    <div
      v-if="error"
      class="error mt-10 flex justify-center"
    >
      <div class="border-dashed border-2 border-red-500 rounded p-2">
        <p class="text-red-500">
          予期しないエラーが発生しました。しばらく経ってからやり直してください。
        </p>
        <p class="text-red-500">
          エラー内容: {{ error.message }}
        </p>
      </div>
    </div>
    <slot v-else />
  </div>
</template>

<script>
export default {
  name: 'ErrorBoundary',
  data: () => ({
    error: null
  }),
  errorCaptured (err, vm, info) {
    this.error = err
  }
}
</script>

<style scoped>
.error {
  text-align: center;
}
</style>
