<template>
  <div class="flex">
    <div class="w-8/12 mt-10 m-auto">
      <h1 class="text-4xl">
        設定
      </h1>
      <hr class="solid">
      <div class="flex mt-10 m-auto w-11/12">
        <div class="inline-flex w-9/12">
          <h2 class="text-xl">
            好みのジャンル設定
          </h2>
        </div>
        <div class="inline-flex w-2/12">
          <select
            class="border rounded w-full"
            id="settings__categories"
            v-model="selected"
          >
            <option
              class="p-16"
              :key="category.id"
              :value="category.id"
              v-for="category in categories"
            >
              {{ category.name }}
            </option>
          </select>
        </div>
        <div class="inline-flex w-1/12 justify-end">
          <Button
            @click="register"
            class="bg-blue-500 hover:bg-blue-700 text-white font-bold"
            name="登録"
          />
        </div>
      </div>
    </div>
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
          id="settings__my-category"
        >
          変更を保存しました！
        </p>
      </div>
    </transition>
  </div>
</template>

<script>
import SettingsRepository from '@/repository/SettingsRepository.js'
import Button from '@/components/Button.vue'

const SUCCESS_RESPONSE_CREATED = 201

function toggleNotify (vm) {
  vm.isNotified = true
  setTimeout(() => {
    vm.isNotified = false
  }, 3500)
}

export default {
  name: 'Settings',
  components: {
    Button
  },
  data: () => ({
    api: new SettingsRepository(),
    categories: [],
    isNotified: false,
    myCategory: null,
    selected: null
  }),
  async created () {
    const [{ data: categories }, { data: myCategory }] = await Promise.all([ this.api.retrieveCategories(), this.api.retrieveMyCategory() ])

    this.categories = categories
    this.selected = myCategory.id
  },
  methods: {
    async register () {
      this.isNotified = false
      const myCategory = this.categories.find(category => { return category.id === this.selected })
      const { status } = await this.api.registerMyCategory(myCategory.id)

      if (status === SUCCESS_RESPONSE_CREATED) {
        toggleNotify(this)
      }
    }
  }
}
</script>

<style scoped>
.solid {
  border-top: 1px solid #8c8b8b;
}
.toast-enter-active, .toast-leave-active {
  transition: all .3s ease;
}

.toast-enter, .toast-leave-to {
  transform: translateX(20px);
  opacity: 0;
}
</style>
