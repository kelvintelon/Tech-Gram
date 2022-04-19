<template>
  <div>
      <!-- <button
      v-on:click="promptCaption" >Edit Caption</button> -->
    
      <button v-show="this.yourPhoto" 
      v-on:click="showCaptionForm = !showCaptionForm" >Edit Caption {{ photoUsername }}</button>
      <form v-if="showCaptionForm">
          <textarea
              class="caption-input"
              placeholder="Write a caption..."
              type="text"
             
              @input="$emit('input', $event.target.value)"
              cols="20"
              rows="5"
              name="textarea"
            ></textarea>

            <button id="cancel" @click="showCaptionForm = !showCaptionForm">Cancel</button> &nbsp;
            <button id="upload" type="submit">Upload</button>
      </form>
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
    showCaptionForm: false,
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
//  promptCaption() {
//      this.$prompt("Please enter a new caption", "Caption").then(result => {
//          console.log(`${result.result}, ${result.text}`)
//      })
     
//  },
 
mounted() {
    console.log(this.$store.state.pictureDetails.username)
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