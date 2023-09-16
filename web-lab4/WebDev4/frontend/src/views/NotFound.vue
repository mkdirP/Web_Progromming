<template>
  <div id="body-table">

    <div class="header">
      <div>
        <p>Wang Mingzhi P32151</p>
        <p>Variant 18000</p>
      </div>
    </div>

    <div class="errors">
      <h1 class="errors-h">Error {{ errorCode }}:</h1>
      <h2 class="errors-h">{{ errorMsg }}</h2>
      <button class="gradient-button" @click="goHome">
        Войти
      </button>
    </div>

  </div>
</template>

<style>
@import "../assets/header.css";
@import "../assets/body.css";

.errors {
  text-align: center;
  margin-top: 180px;
}
</style>

<script>
export default {
  name: "NotFound",
  props: {
    errorCode: String,
    errorMsg: String
  },
  methods: {
    goHome() {
      this.$router.push({name: "auth-page"})
    }
  }
}
</script>