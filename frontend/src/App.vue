<template>
  <div id="app">
    <Header />
    <main>
      <ErrorBoundary>
        <transition
          name="fade"
          mode="out-in"
        >
          <router-view />
        </transition>
      </ErrorBoundary>
    </main>
  </div>
</template>

<script>
import Header from '@/components/Header.vue'
export default {
  name: 'App',
  components: {
    Header
  }
}
</script>

<style scoped>
.fade-enter-active, .fade-leave-active {
  transition: all .3s ease;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>
