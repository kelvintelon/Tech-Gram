<template>
  <div>
    <!-- <button
      v-on:click="promptCaption" >Edit Caption</button> -->

    <button
      v-show="this.yourPhoto"
      v-on:click="showCaptionForm = !showCaptionForm"
      id="editButton"
    >
      EDIT CAPTION
    </button>
    <div id="formAndButtons" v-if="showCaptionForm">
    <form  v-on:submit.prevent="saveNewCaption" id="captionForm">
      <textarea
        class="captionTextarea"
        placeholder="Write a new caption..."
        type="text"
        @input="$emit('input', $event.target.value)"
        rows="1"
        cols="3000"
        name="textarea"
        
      ></textarea>
      <br>
      <button id="cancel" @click="showCaptionForm = !showCaptionForm">
        Cancel
      </button>
      &nbsp;
      <button id="edit" type="submit">Submit</button>
    </form>
    </div>
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
      } else {
                alert("This is not your photo!!")
      }
    },
  },
  //  promptCaption() {
  //      this.$prompt("Please enter a new caption", "Caption").then(result => {
  //          console.log(`${result.result}, ${result.text}`)
  //      })

  //  },

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
};
</script>


<style>
#editButton {
  /* display: block; */
  width: 100%;
  height: 22px;
  border: none;
  outline: none;
  border-radius: 50px;
  border: 1px solid rgba(0, 0, 0, 0.61);
  cursor: pointer;
}
#captionForm {
    /* float: left;
    width: 100%; */
    max-width: auto;
}

#formAndButtons {
    display:flex;
    flex-direction: row;
    background: #efe3ef;
    width: 100%;
    height: 150%;
    padding-top: 8px; 
    
}

.captionTextarea {
   display:inline-block;
   width: 90%;
}

#cancel {
  cursor: pointer;
  border-radius: 50px;
}

#edit {
  cursor: pointer;
  border-radius: 50px;
}
</style>