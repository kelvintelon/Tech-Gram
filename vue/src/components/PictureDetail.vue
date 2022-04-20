<template>
  <div class="picCard">
    <div class="picDetailContainer">
      <img class="picture" :src="photo.image_location" id="img" />

      <div class="picInfo">
        <div class="like_and_fav">
          <like-button
            class="likes"
            v-bind:photoId="photo.photo_id"
          ></like-button>
          <favorite-button
            class="favorites"
            :photoId="photo.photo_id"
          ></favorite-button>
          
          <button id="removePhoto" @click="removePhoto(photo.photo_id)">
            REMOVE
          </button>
<edit-caption
            id="editCaption"
            :photoUsername="photo.username"
            
          ></edit-caption>
        </div>

        <div class="userInfo">
          <router-link
        v-bind:to="{ name: 'userPage', params: { username: photo.username } }" v-if="checkUser(photo)"
      >
        <div class="picusername">{{ photo.username }}</div>
      </router-link>

      <router-link
        v-bind:to="{ name: 'userFeed', params: { username: photo.username } }" v-if="!checkUser(photo)"
      >
        <div class="picusername">{{ photo.username }}</div>
      </router-link>

          
          <div class="piccaption">{{ photo.caption }}</div>
        </div>
      </div>
    </div>

    <div class="picComments">
      <add-comment></add-comment>
      <comments-list></comments-list>
    </div>
  </div>
</template>

<script>
import PhotoService from "../services/PhotoService";
import CommentsList from "./CommentsList.vue";
import AddComment from "./AddComment.vue";
import LikeButton from "./LikeButton.vue";
import FavoriteButton from "./FavoriteButton.vue";
import EditCaption from "./EditCaption.vue";

export default {
  name: "picture-detail",

  components: {
    CommentsList,
    AddComment,
    LikeButton,
    FavoriteButton,
    EditCaption,
  },
  data() {
    return {
      username: "",
      yourPhoto: false,

      errorMsg: "",
    };
  },
  mounted() {
    this.retrievePicture();
  },

  created() {
    this.retrievePicture();

    //  const UserString= localStorage.getItem("user");
    //  let firstIndex = UserString.indexOf("username");
    //  let secondIndex = UserString.indexOf("authorities");
    //  this.username = UserString.substring(firstIndex + 11, secondIndex - 3);

    //  if (this.username == this.$store.state.pictureDetails.username) {
    //    this.yourPhoto = true;
    // console.log(this.username);
    //  console.log(this.$store.state.pictureDetails.username)
    //  }
    //  console.log(this.username);
    //  console.log(this.$store.state.pictureDetails.username)
  },
  methods: {
    retrievePicture() {
      PhotoService.getOnePicture(this.$route.params.photoId)
        .then((response) => {
          this.$store.commit("SET_CURRENT_PIC", response.data);
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert(
              "Photo not available. This photo may have been deleted or you have entered an invalid card ID."
            );
            this.$router.push("/");
          }
        });
    },
    removePhoto(photoId) {
      if (this.username == this.$store.state.pictureDetails.username) {
        if (
        confirm(
          "Are you sure you want to delete this photo? This action cannot be undone."
        )
      ) {
        PhotoService.deletePhoto(photoId)
          .then((response) => {
            if (response.status === 200) {
              alert("Photo successfully removed");
              this.$router.push({
                name: "userPage",
                params: { username: this.username },
              });
            }
          })
          .catch((error) => {
            if (error.response) {
              this.errorMsg =
                "Error deleting photo. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error deleting photo. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error deleting photo. Request could not be created.";
            }
          });
      }
      } else {
        alert("You cannot delete someone else's photo")
      }
    },
    checkUser(photo){
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
      if (this.username == photo.username){
        return this.isUser=true;
      }else{
        return this.isUser=false;
      }
    },
    checkForUser(photousername) {
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
      if (this.username == photousername) {
        return true;
      }
    },
  },
  computed: {
    photo() {
      return this.$store.state.pictureDetails;
    },
  },
};
</script>

<style>
* {
  font-family: Arial, Helvetica, sans-serif;
}

.picCard {
  /* display: -ms-flexbox;  */
  display: flex;
  flex-direction: column;
  width: 80%;
  /* -ms-flex-pack: distribute; */
  justify-content: center;
  /* margin: 40px auto; */
  /* flex-wrap: wrap; */
}
.picDetailContainer {
  /* background-color: #ddab23; */
  background-color: #efe3ef;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  width: 400px;
  height: 100%;
  /* margin-left: 30px; */
  /* margin-bottom: 30px; */
  margin: 30px auto 0 auto;
  /* padding: 10px; */
}

div.picInfo {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;

  /* align-items: center; */

  column-gap: 30px;

  margin-top: 10px;
  border-top: 1px solid black;
  /* height: 20px; */
  padding: 20px;
}

.like_and_fav {
  display: flex;
  flex-direction: row;
  height: 35px;
  justify-content: space-between;
  padding-bottom: 10px;
}
.likes {
  display: inline-block;
}
.like {
  margin: 6px 0 1px 5px;
  font-size: 20px;
}

img.picture {
  margin: 20px 20px 20px 20px;
}
#img {
  margin: 40px 20px 20px 40px;
}

.userInfo {
  display: flex;
  flex-direction: row;
  /* this below is hard code */
  margin-top: 15px;
  margin-bottom: 7px;
}
#removePhoto {
  /* display: block; */
  width: 30%;
  height: 22px;
  outline: none;
  border-radius: 50px;
  border: 1px solid rgba(0, 0, 0, 0.61);
  background-color: rgb(253, 168, 168);
  cursor: pointer;
  padding-right: 10px;
}

/* .likes,
.comments {
  margin-bottom: 10px;
} */
.picusername {
  font-weight: bold;
  color:#00adee;
}
.piccaption {
  margin-left: 5px;
}
ul {
  list-style: none;
}
.picComments {
  width: 80%;
  /* border: 1px black solid; */
  border-radius: 6px;
  padding: 1rem;
  margin: 20px auto 0 auto;
}
a {
  text-decoration: none;
}
</style>