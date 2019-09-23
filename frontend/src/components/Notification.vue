<template>
  <transition
    name="toast"
    mode="out-in"
  >
    <div
      class="flex fixed right-0 w-3/12 m-8 py-2 items-center rounded bg-blue-500 text-white text-sm font-bold"
      role="alert"
      v-if="isNotified"
    >
      <v-icon
        class="mx-2"
        name="info-circle"
        scale="1.25"
      />
      <p
        id="settings__notification"
      >
        変更を保存しました！
      </p>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'Notification',
  props: {
    isNotified: {
      type: Boolean,
      required: true
    }
  }
}
</script>

<style scoped>
.toast-enter-active, .toast-leave-active {
  transition: all .3s ease;
}

.toast-enter, .toast-leave-to {
  transform: translateX(20px);
  opacity: 0;
}
</style>
