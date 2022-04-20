<template>
  <div class="deleteBox">
    <h1 id="deleteuserlogo">Tech Gram</h1>
        <link href="http://fonts.cdnfonts.com/css/billabong" rel="stylesheet">

    <h3>Are you sure you want to delete your page?</h3>
    <button class="deleteButton" @click="$router.push('/')">Go Back</button>
    <router-link
      class="linkToDelete"
      v-bind:to="{ name: 'deleteUserFinal' }"
      
      >Yes, Delete My Profile</router-link
    >
  </div>

</template>


<script>
export default {
  name: "deleteUserFinal",
    data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  /*  deleteUser() {
      authService
        .delete(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
    } */
};
</script>

<style>
.deleteBox {
  border: 2px solid black;
  border-radius: 10px;
  background-color: aliceblue;
  position: absolute;
  margin: auto;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  width: 500px;
  height: 600px;
  text-align: center;
}
.deleteButton {
  background-color: #00adee;
  border-radius: 10px;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  height: 80px;
  width: 200px;
  color: aliceblue;
  padding: 15px 32px;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  margin-top: 80px;
}
.deleteButton:hover {
  background-color: #167fa5;
  transition: 0.7s;
}
#deleteuserlogo {
 margin-top: 40px;
  font-size: 70px;
  font-family: 'Billabong', sans-serif;
}
.linkToDelete {
  display: flex;
  flex-direction: column;
  position: absolute;
  text-decoration: none;
  bottom: 10px;
  font-size: 19px;
  margin: 0px 155px 0px 155px;
  color: blue;
}
</style>
