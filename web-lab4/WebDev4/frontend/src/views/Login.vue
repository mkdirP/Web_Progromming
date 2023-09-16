<template>
  <div id="login-">

    <div class="header">
      <div>
        <p>Wang Mingzhi P32151</p>
        <p>Variant 18000</p>
      </div>
    </div>

    <div class="wrap">
      <div class="form">

        <div class="left">
          <video src="../video/moonlight.mp4" muted autoplay loop></video>
        </div>

        <div class="right">
          <form class="login">
            <h1>Who are you?</h1>
            <h3>Name</h3>
            <div >
              <input type="text" class="text" required placeholder="Name" v-model.trim="login">
            </div>

            <h3>Password</h3>
            <div >
              <input type="password" class="text" required placeholder="Password" v-model.trim="password">
            </div>

            <a id="forError" style="font-size: 10px; font-style: oblique; color: #4C489D"></a>

            <button class="btn" @click="loging">
              <span>LogIn</span>
            </button>

            <div class="btn">
              <a id="btn-a" href="/" @click="register" >SignUp</a>
            </div>
          </form>


        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'Login',
  data() {
    return {
      login: "",
      password: ""
    }
  },
  methods: {
    loging(e) {
      e.preventDefault();
      axios.post('http://localhost:8083/api/user/auth', {
        login: this.login,
        password: this.password
      }).then(response => {
        localStorage.setItem("jwt", response.data);
        this.$router.push({name: 'main'});
      }).catch(error => {
        document.getElementById("forError").innerText = error.response.data;
      })
    },
    register(e) {
      e.preventDefault();
      axios.put('http://localhost:8083/api/user/auth', {
        login: this.login,
        password: this.password
      }).then(() => {
        document.getElementById("forError").innerText = "Successfully signed up";
      }).catch(error => {
        document.getElementById("forError").innerText = error.response.data;
      })
    }
  }
}
</script>

<style>
@import "../assets/header.css";
@import "../assets/login.css";
</style>
