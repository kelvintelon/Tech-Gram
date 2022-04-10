<template>
  <div class="menu">
    <logged-in-buttons v-if="$store.state.token != ''"></logged-in-buttons>
    <login-or-signUp class="buttons" v-else ></login-or-signUp>
    <footer class="footer">&copy; Tech Elevator 2022</footer>
  </div>
</template>

<script>
import LoginOrSignUp from "../components/LoginOrSignUp.vue";
import LoggedInButtons from "../components/LoggedInButtons.vue";

export default {
  name: "side-menu",
  components: {
    LoginOrSignUp,
    LoggedInButtons
  },
};
</script>

<style>
.buttons {
  align-items: top;
}
footer {
  position: fixed;
  bottom: 5px;
  color: black;
  text-align: center;
  font-weight: bold;
  font-size: 16px;
}
</style>