<template>
  <div>
    <button class="button" @click="$router.push('/login')">Login</button>
    <button class="button" @click="$router.push('/register')">Sign Up</button>
  </div>
</template>

<script>
export default {
  name: "login-or-singUp",
};
</script>

<style>
.button {
  background-color: #00adee;
  border: 2px solid black;
  color: aliceblue;
  padding: 15px 32px;
  margin: 20px;
  text-align: center;
  font-weight: bold;
  font-size: 16px;
  cursor:pointer;
}
</style>