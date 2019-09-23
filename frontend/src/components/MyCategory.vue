<template>
  <div class="flex mt-10 m-auto w-11/12">
    <div class="inline-flex w-9/12">
      <h2 class="text-xl">
        好みのジャンル設定
      </h2>
    </div>
    <div class="inline-flex w-2/12">
      <select
        class="border rounded w-full appearance-none focus:outline-none focus:shadow-outline"
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
        @click="registerCategory"
        class="bg-blue-500 hover:bg-blue-700 text-white font-bold"
        name="登録"
      />
    </div>
  </div>
</template>

<script>
import Button from '@/components/Button.vue'

export default {
  name: 'MyCategory',
  components: {
    Button
  },
  props: {
    categories: {
      type: Array,
      required: true
    },
    // TODO use data or computed to avoid mutating since the value will be overwritten
    selected: {
      type: Number,
      required: true
    }
  },
  methods: {
    registerCategory () {
      this.$emit('registerCategory', this.selected)
    }
  }
}
</script>
