<template>
  <div>
    <button class="authorize" @click="$router.push('/login')">
      <span class="material-icons">login</span>Login
    </button>
    <button class="authorize" @click="$router.push('/register')">
      <span class="material-icons">person_add</span>Sign Up
    </button>
  </div>
</template>

<script>
export default {
  name: "login-or-singUp",
};
</script>

<style>
.authorize {
  background-color: #00adee;
  border: 1px solid black;
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  display: flex;
  width: 250px;
  color: aliceblue;
  padding: 15px 32px;
  margin: 30px;
  justify-content: space-between;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
}

.authorize:hover {
  background-color: #167fa5;
  transition: 0.5s;
}
</style>