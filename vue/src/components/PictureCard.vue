<template>
  <div class="card">
    <div
      class="picContainer allPics"
      v-for="photo in this.$store.state.imagePosts"
      v-bind:key="photo.photo_id"
      v-show="allPhotos"
    >
      <img
        class="picture"
        :src="photo.image_location"
        @click="
          $router.push({
            name: 'photoDetails',
            params: { photoId: photo.photo_id },
          })
        "
      />
      <div class="usernameYours">{{photo.username}}</div>
      <div class="caption">{{ photo.caption }}</div>
      <div class="likesBlock">
        <like-button class="likes"></like-button>
        <div class="likes likesCount">3</div>
      </div>
      <div class="comments">Comments:</div>
    </div>

    <div
      class="picContainer userPics"
      v-for="photo in this.$store.state.userImages"
      v-bind:key="photo.username"
      v-show="userPhotos"
    >
      <img
        class="picture"
        :src="photo.image_location"
        @click="
          $router.push({
            name: 'photoDetails',
            params: { photoId: photo.photo_id },
          })
        "
      />
       <div class="usernameYours">{{photo.username}}</div>
      <div class="caption">{{ photo.caption }}</div>
      <div class="likesBlock">
        <like-button class="likes"></like-button>
        <div class="likes likesCount">3</div>
      </div>
      <div class="comments">Comments:</div>
    </div>

    <div
      class="picContainer favoritePics"
      v-for="photo in this.$store.state.favoriteImages"
      v-bind:key="photo.user_id"
      v-show="favoritesPhotos"
    >
      <img
        class="picture"
        :src="photo.image_location"
        @click="
          $router.push({
            name: 'photoDetails',
            params: { photoId: photo.photo_id },
          })
        "
      />
       <div class="usernameYours">{{photo.username}}</div>
      <div class="caption">{{ photo.caption }}</div>
      <div class="likesBlock">
        <like-button class="likes"></like-button>
        <div class="likes likesCount">3</div>
      </div>
      <div class="comments">Comments:</div>
    </div>
  </div>
</template>



<script>
import UploadFileService from "../services/UploadFileService";
import LikeButton from "../components/LikeButton.vue";
import FavoriteService from "../services/FavoriteService";

export default {
  name: "picture-card",
  components: {
    LikeButton,
  },
  props: ["allPhotos", "userPhotos", "favoritesPhotos"],
  data() {
    return {};
  },
  created() {
    if (this.allPhotos == true) {
      this.getImagePosts();
    }
    if (this.userPhotos == true) {
      this.getUserImages();
    }
    if (this.favoritesPhotos == true) {
      this.getFavoriteImages();
    }
  },
  mounted() {
    const UserString = localStorage.getItem("user");
    let firstIndex = UserString.indexOf("username");
    let secondIndex = UserString.indexOf("authorities");
    this.username = UserString.substring(firstIndex + 11, secondIndex - 3);

 if (this.allPhotos == true) {
      this.getImagePosts();
    }
    if (this.userPhotos == true) {
      this.getUserImages();
    }
    if (this.favoritesPhotos == true) {
      this.getFavoriteImages();
    }
  },
  methods: {
    turnFalse() {},
    getImagePosts() {
      UploadFileService.getFiles().then((response) => {
        this.$store.commit("SET_IMAGEPOSTS", response.data);
      });
    },
    getUserImages() {
      UploadFileService.getUserFiles(this.username).then((response) => {
        this.$store.commit("SET_USERIMAGES", response.data);
      });
    },
    getFavoriteImages() {
      FavoriteService.getFavorites().then((response) => {
        this.$store.commit("SET_FAVORITEIMAGES", response.data);
      });
    },
  },
};
</script>

<style>
.card {
  display: -ms-flexbox;
  display: inline-flex;
  max-width: 1550px;
  -ms-flex-pack: distribute;
  justify-content: left;
  margin: 40px auto;
  flex-wrap: wrap;
}
.picContainer {
  background-color: #efe3ef;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  width: 200px;
  height: 100%;
  margin-left: 30px;
  margin-bottom: 30px;
  padding: 10px;
}

img {
  width: 80%;
  height: 80%;
  margin: 20px;
  margin-bottom: 10px;
}
.likes,
.comments {
  margin-bottom: 10px;
}
ul {
  list-style: none;
}
.likes {
  display: inline-block;
}
.likesCount {
  margin-left: 3px;
  font-size: 20px;
}
</style>