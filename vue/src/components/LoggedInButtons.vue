<template>
  <div>
    <button class="button">See Your Page</button>
    <button class="button">Favorites</button>
    <button class="button">Add Picture</button>
    <button
      class="logOut button"
      @click="$router.push('/logout')"
      v-if="$store.state.token != ''"
    >
      Log Out
    </button>
    <button class="delete button">Delete Profile</button>
  </div>
</template>

<script>
export default {
  name: "logged-in-buttons",
};
</script>

<style>
.button {
  background-color: #00adee;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  display: flex;
  width: 250px;
  color: black;
  padding: 15px 32px;
  margin: 30px;
  text-align: center;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
}
.delete {
  position: fixed;
  bottom: 20px;
}
.logOut {
  position: fixed;
  bottom: 100px;
}
</style>