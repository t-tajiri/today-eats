<template>
  <header class="flex items-center justify-between flex-wrap bg-blue-400 p-5">
    <div class="flex items-center flex-no-shrink text-white mr-6">
      <router-link
        class="text-2xl font-semibold tracking-tight"
        to="/"
      >
        <v-icon
          class="align-text-top"
          name="utensils"
          scale="1.75"
        />
        <span class="m-2">Today Eats!</span>
      </router-link>
    </div>
    <nav>
      <router-link
        class="text-white hover:text-gray-300"
        to="/settings"
      >
        <v-icon
          name="cog"
          label="設定"
          scale="1.75"
        />
      </router-link>
    </nav>
  </header>
</template>
