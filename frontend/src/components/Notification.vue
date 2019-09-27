<template>
  <transition-group
    name="toast"
  >
    <div
      class="flex fixed right-0 w-3/12 m-8 py-2 items-center rounded bg-blue-500 text-white text-sm font-bold"
      key="notified"
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
    <div
      class="flex fixed right-0 w-3/12 m-8 py-2 items-center rounded bg-red-500 text-white text-sm font-bold"
      key="error"
      role="alert"
      v-if="!isValid"
    >
      <v-icon
        class="mx-2"
        name="info-circle"
        scale="1.25"
      />
      <div class="flex flex-col">
        <span
          :key="'settings__notification-error-' + i"
          v-for="(errorMessage, i) in errorMessages"
        >
          {{ errorMessage }}
        </span>
      </div>
    </div>
  </transition-group>
</template>

<script>
export default {
  name: 'Notification',
  props: {
    isNotified: {
      type: Boolean,
      required: true
    },
    isValid: {
      type: Boolean,
      required: true
    },
    errorMessages: {
      type: Array,
      default: () => []
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
