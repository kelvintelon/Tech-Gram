<template>
  <div class="buttonHolder">
    <button
      class="button"
      @click="$router.push('/')"
      v-if="this.$router.currentRoute.path == '/userPage/' + username"
    >
      <span class="material-icons"> grid_view </span>
      See Feed
    </button>
    <button
      class="button"
      @click="$router.push({ name: 'userPage', params: { username } })"
      v-else
    >
      <span class="material-icons">perm_identity</span>
      See Your Page
    </button>
    <button
      class="button"
      @click="$router.push('/')"
      v-if="this.$router.currentRoute.path == '/favorites/' + username"
    >
      <span class="material-icons"> grid_view </span>
      See Feed
    </button>
    <button
      class="button"
      @click="$router.push({ name: 'favorites', params: { username } })"
      v-else
    >
      <span class="material-icons">bookmarks</span>
      Favorites
    </button>
    <button
      class="button"
      @click="$router.push('/')"
      v-if="this.$router.currentRoute.path == '/addPicture'"
    >
      <span class="material-icons"> grid_view </span>
      See Feed
    </button>
    <button class="button" @click="$router.push('/addPicture')" v-else>
      <span class="material-icons"> photo </span>
      Add Picture
    </button>

    <button
      class="logOut button"
      @click="$router.push('/logout')"
      v-if="$store.state.token != ''"
    >
      <span class="material-icons"> emoji_people </span>
      Log Out
    </button>
    <button class="delete button" @click="$router.push('/deleteUser')">
      <span class="material-icons"> no_accounts </span>Delete Profile
    </button>
  </div>
</template>

<script>
export default {
  name: "logged-in-buttons",
  data() {
    return {
      username: "",
    };
  },
  mounted() {
    const UserString = localStorage.getItem("user");
    let firstIndex = UserString.indexOf("username");
    let secondIndex = UserString.indexOf("authorities");
    this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
  },
};
</script>

<style>

.button {
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

.button9:hover {
  background-color: #167fa5;
  transition: 0.5s;
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