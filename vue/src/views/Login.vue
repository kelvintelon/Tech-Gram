<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Tech Gram</h1>
      <h3>Log in to see your page:</h3>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only">Username:</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password:</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link class="link" :to="{ name: 'register' }"
        >Create an Account</router-link
      >
      <button class="btn" type="submit">Login</button>
    </form>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Fredoka+One"
    />
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
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
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style>
.text-center {
  border: 2px solid black;
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
.form-control,
.link,
.btn {
  display: flex;
  flex-direction: column;
  margin: auto;
  margin-bottom: 20px;
}
.sr-only {
font-size: 20px;
}
.link {
  position: absolute;
  text-decoration: none;
  bottom: 10px;
  font-size: 20px;
  margin: 0px 172px 0px 172px;
  color: blue;
}
.btn {
  background-color: #00adee;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  color: black;
  padding: 15px 32px;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  margin-top: 50px;
}
.btn:hover {
      background-color:#167fa5;
      transition: 0.7s;
  }
h1 {
  margin-top: 40px;
  font-size: 40px;
  font-family: Fredoka One;
}
h3{
  margin-bottom: 40px;
}
</style>
