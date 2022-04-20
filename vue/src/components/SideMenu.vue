<template>
  <div class="menu">
    <logged-in-buttons v-if="$store.state.token != ''"></logged-in-buttons>
    <login-or-signUp class="buttons" v-else ></login-or-signUp>
    <footer class="footer">&copy; Tech Elevator 2022</footer>
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
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
.menu {
 border-left: 2px solid black;
  background-color: aliceblue;
  position: absolute;
  right: 0px;
  /* width: 350px; */
  width: 20%;
  /* height: 100%; */
  height: 100vh;
  display: flex;
  justify-content: space-around;
  position:fixed;
}

.buttons {
  align-items: top;
}
footer {
  position: fixed;
  bottom: 5px;
  color:grey;
  text-align: center;
  font-weight: bold;
  font-size: 16px;
}
</style>