<template>
  <div>
      <button v-show="this.yourPhoto">Edit Caption {{ photoUsername }}</button>
  </div>
</template>

<script>
export default {
name: "edit-caption",
props: ['photoUsername'],
data () {
return {
    yourPhoto: true,
    username: "",
    photoUser: "",
}
},
methods: {
    checkForUser(photoUsername) {
    const UserString= localStorage.getItem("user");
     let firstIndex = UserString.indexOf("username");
     let secondIndex = UserString.indexOf("authorities");
     this.username = UserString.substring(firstIndex + 11, secondIndex - 3);

    // this.photoUser = this.photoUsername;
    console.log(this.username)
    console.log(photoUsername)
    if (this.username == photoUsername) {
        this.yourPhoto = true
    }
    }
 },
mounted() {
    this.checkForUser(this.photoUsername);
},
// beforeMount() {
//      const UserString= localStorage.getItem("user");
//      let firstIndex = UserString.indexOf("username");
//      let secondIndex = UserString.indexOf("authorities");
//      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);

//     console.log(this.username)
//     console.log(this.photoUsername)
//     if (this.username == this.photoUsername) {
//         this.yourPhoto = true
//     }
// },
// beforeCreate() {
//     const UserString= localStorage.getItem("user");
//      let firstIndex = UserString.indexOf("username");
//      let secondIndex = UserString.indexOf("authorities");
//      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);

//     console.log(this.username)
//     console.log(this.photoUsername)
//     if (this.username == this.photoUsername) {
//         this.yourPhoto = true
//     }
// },
}
</script>


<style>

</style>