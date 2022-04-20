<template>
  <div class="finalForm">
    <h1 id="deleteuserfinallogo">Tech Gram</h1>
    <form class="toDelete" @submit.prevent="deleteUser">
      <div class="alert" role="alert" v-if="invalidCredentials">
        Invalid username or password!
      </div>
      <label for="username">Username:</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
      />
      <label for="password">Password:</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button class="deleteButtonFinal" type="submit">Delete</button>
    </form>
    <button id="goBack" class="deleteButtonFinal" @click="$router.push('/')">
      Go Back
    </button>
            <link href="http://fonts.cdnfonts.com/css/billabong" rel="stylesheet">

  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "deleteUserFinal",
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
  deleteUser() {
    authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
          authService.delete(this.user)
          this.$router.push('/logout');
        }
      })
      .catch((error) => {
        const response = error.response;

        if (response.status === 401) {
          this.invalidCredentials = true;
        }
      });
  },
  }
};
</script>

<style>
.finalForm {
  border: 2px solid black;
  background-color: aliceblue;
  border-radius: 10px;
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
.deleteButtonFinal {
  background-color: #00adee;
  border: 1px solid black;
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  color:aliceblue;
  padding: 15px 32px;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  margin-top: 30px;
  margin-bottom: 70px;
}
#goBack{
  padding: 15px 25px;

}

.deleteButton:hover {
  background-color: #167fa5;
  transition: 0.7s;
}
#deleteuserfinallogo {
 margin-top: 40px;
  font-size: 70px;
  font-family: 'Billabong', sans-serif;
}
</style>
