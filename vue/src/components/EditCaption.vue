<template>
  <div>
    <!-- <button
      v-on:click="promptCaption" >Edit Caption</button> -->

    <button
      v-show="this.yourPhoto"
      v-on:click="showCaptionForm = !showCaptionForm"
    >
      Edit Caption
    </button>
    <form v-if="showCaptionForm" v-on:submit.prevent="saveNewCaption" id="captionForm" style="border:dashed 2px orange">
      <textarea
        class="captionTextarea"
        placeholder="Write a new caption..."
        type="text"
        @input="$emit('input', $event.target.value)"
        rows="3"
        cols="40"
        name="textarea"
        
      ></textarea>
      <button id="cancel" @click="showCaptionForm = !showCaptionForm">
        Cancel
      </button>
      &nbsp;
      <button id="edit" type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import PhotoService from "../services/PhotoService";

export default {
  name: "edit-caption",
  props: ["photoUsername"],
  data() {
    return {
      yourPhoto: true,
      username: "",
      photoUser: "",
      showCaptionForm: false,
      newPhoto: {
        caption: "",
        image_location: "",
      },
    };
  },
  methods: {
    checkForUser(photoUsername) {
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);

      // this.photoUser = this.photoUsername;
      console.log(this.username);
      console.log(photoUsername);
      if (this.username == this.$store.state.pictureDetails.username) {
        this.yourPhoto = true;
      }
    },
    saveNewCaption() {
      this.newPhoto.caption = document.querySelector("textarea").value;
      this.newPhoto.image_location =
        this.$store.state.pictureDetails.image_location;
      if (this.username == this.$store.state.pictureDetails.username) {
        PhotoService.updateCaption(this.newPhoto, this.$route.params.photoId)
          .then((response) => {
            if (response.status === 200) {
              location.reload();
              this.showCaptionForm = !this.showCaptionForm;
            }
          })
          .catch((error) => {
            this.handleErrorResponse(error, "updating");
          });
      }
    },
  },
  //  promptCaption() {
  //      this.$prompt("Please enter a new caption", "Caption").then(result => {
  //          console.log(`${result.result}, ${result.text}`)
  //      })

  //  },

  mounted() {
    console.log(this.$store.state.pictureDetails.username);
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
};
</script>


<style>
#captionForm {
    /* float: left;
    margin-left: 100px; */
    background: #efe3ef
}
</style>