<template>
  <div class="card">
    <!--       This    div    contains    All     pictures -->
    <div
      class="picContainer allPics"
      v-for="photo in this.$store.state.imagePosts"
      v-bind:key="photo.photo_id"
      v-show="allPhotos"
    >
      <router-link
        v-bind:to="{ name: 'userPage', params: { username: photo.username } }"
        v-if="checkUser(photo)"
      >
        <div class="usernameYours">{{ photo.username }}</div>
      </router-link>

      <router-link
        v-bind:to="{ name: 'userFeed', params: { username: photo.username } }"
        v-if="!checkUser(photo)"
      >
        <div class="usernameYours">{{ photo.username }}</div>
      </router-link>
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

      <!-- edit above -->
      <div class="caption">{{ photo.caption }}</div>
      <div class="likesBlock">
        <like-button class="likes" :photoId="photo.photo_id"></like-button>
        <favorite-button
          class="favorites"
          :photoId="photo.photo_id"
        ></favorite-button>
      </div>
      <div class="comments" v-show="photo.comments.length > 0">Comments:</div>
      <table>
        <tr
          v-for="comment in photo.comments.slice(-2)"
          :key="comment.comments_id"
        >
          <th>{{ comment.username }}:</th>
          <td v-show="comment.text.length < 11">{{ comment.text }}</td>
          <td
            class="threeDots"
            v-show="comment.text.length > 11"
            @click="
              $router.push({
                name: 'photoDetails',
                params: { photoId: photo.photo_id },
              })
            "
          >
            {{ comment.text.slice(0, 11) }}...
          </td>
        </tr>
      </table>
    </div>

    <!--       This    div    contains    Logged In    User     pictures    -->
    <div
      class="picContainer userPics"
      v-for="photo in this.$store.state.userImages"
      v-bind:key="photo.username"
      v-show="userPhotos"
    >
      <div class="usernameYours">{{ photo.username }}</div>
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
      <div class="caption">{{ photo.caption }}</div>
      <div class="likesBlock">
        <like-button class="likes" :photoId="photo.photo_id"></like-button>
        <favorite-button
          class="favorites"
          :photoId="photo.photo_id"
        ></favorite-button>
      </div>
      <div class="comments" v-show="photo.comments.length > 0">Comments:</div>
      <table>
        <tr
          v-for="comment in photo.comments.slice(-2)"
          :key="comment.comments_id"
        >
          <th>{{ comment.username }}:</th>
          <td v-show="comment.text.length < 11">{{ comment.text }}</td>
          <td
            class="threeDots"
            v-show="comment.text.length > 11"
            @click="
              $router.push({
                name: 'photoDetails',
                params: { photoId: photo.photo_id },
              })
            "
          >
            {{ comment.text.slice(0, 11) }}...
          </td>
        </tr>
      </table>
    </div>

    <!--      This     bottom     div     contains      favoritePics -->
    <div
      class="picContainer favoritePics"
      v-for="photo in this.$store.state.favoriteImages"
      v-bind:key="photo.user_id"
      v-show="favoritesPhotos"
    >
      <div class="usernameYours">{{ photo.username }}</div>

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
      <router-link
        v-bind:to="{ name: 'userFeed', params: { username: photo.username } }"
      >
      </router-link>
      <div class="caption">{{ photo.caption }}</div>
      <div class="likesBlock">
        <like-button class="likes" :photoId="photo.photo_id"></like-button>
        <favorite-button
          class="favorites"
          :photoId="photo.photo_id"
        ></favorite-button>
      </div>
      <div class="comments" v-show="photo.comments.length > 0">Comments:</div>
      <table>
        <tr
          v-for="comment in photo.comments.slice(-2)"
          :key="comment.comments_id"
        >
          <th>{{ comment.username }}:</th>
          <td v-show="comment.text.length < 11">{{ comment.text }}</td>
          <td
            class="threeDots"
            v-show="comment.text.length > 11"
            @click="
              $router.push({
                name: 'photoDetails',
                params: { photoId: photo.photo_id },
              })
            "
          >
            {{ comment.text.slice(0, 11) }}...
          </td>
        </tr>
      </table>
    </div>

    <!--      This   bottom   div   is   for  other  users   besides   the   one   who  is  logged -->
    <div
      class="picContainer userFeedPics"
      v-for="photo in this.$store.state.userFeedImages"
      v-bind:key="photo.text"
      v-show="userFeed"
    >
      <div class="usernameYours">{{ photo.username }}</div>

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
      <div class="caption">{{ photo.caption }}</div>
      <div class="likesBlock">
        <like-button class="likes" :photoId="photo.photo_id"></like-button>
        <favorite-button
          class="favorites"
          :photoId="photo.photo_id"
        ></favorite-button>
      </div>
      <div class="comments" v-show="photo.comments.length > 0">Comments:</div>
      <table>
        <tr
          v-for="comment in photo.comments.slice(-2)"
          :key="comment.comments_id"
        >
          <th>{{ comment.username }}:</th>
          <td v-show="comment.text.length < 11">{{ comment.text }}</td>
          <td
            class="threeDots"
            v-show="comment.text.length > 11"
            @click="
              $router.push({
                name: 'photoDetails',
                params: { photoId: photo.photo_id },
              })
            "
          >
            {{ comment.text.slice(0, 11) }}...
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>


<script>
import UploadFileService from "../services/UploadFileService";
import LikeButton from "../components/LikeButton.vue";
import FavoriteService from "../services/FavoriteService";
import FavoriteButton from "../components/FavoriteButton.vue";

export default {
  name: "picture-card",
  components: {
    LikeButton,
    FavoriteButton,
  },
  props: ["allPhotos", "userPhotos", "favoritesPhotos", "userFeed"],
  data() {
    return {
      username: "",
      isUser: false,
    };
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
    if (this.userFeed == true) {
      this.getUserFeedImages();
    }
  },
  mounted() {
    if (this.allPhotos == true) {
      this.getImagePosts();
    }
    if (this.userPhotos == true) {
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
      this.getUserImages();
    }
    if (this.favoritesPhotos == true) {
      this.getFavoriteImages();
    }
    if (this.userFeed == true) {
      this.getUserFeedImages();
    }
  },
  methods: {
    getUserFeedImages() {
      UploadFileService.getUserFiles(this.$route.params.username).then(
        (response) => {
          this.$store.commit("SET_USERFEEDIMAGES", response.data);
        }
      );
    },
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

    // to check is login user=username, yes-isUser to true, no-isUser to false
    checkUser(photo) {
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
      if (this.username == photo.username) {
        return (this.isUser = true);
      } else {
        return (this.isUser = false);
      }
    },
  },
};
</script>

<style>
.usernameYours {
  text-align: center;
  color: #00adee;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: bold;
  border-radius: 5px;
  font-size: 20px;
}
a {
  text-decoration: none;
}
.card {
  display: -ms-flexbox;
  display: inline-flex;
  width: 80%;
  /* max-width: 1550px; */
  -ms-flex-pack: distribute;
  justify-content: left;
  margin: 40px auto;
  flex-wrap: wrap;
  text-align: center;
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

.comments {
  margin-bottom: 10px;
  margin-top: 10px;
  padding-right: 5px;
  text-align: left;
  font-weight: bold;
  background-color:#efe3ef;
  border-bottom: 4px solid rgb(253, 168, 168);
}
.caption {
  text-align: left;
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
.likesBlock {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}
.favorites {
  margin-right: 10px;
}

td {
  word-wrap: break-word;
  white-space: pre-wrap;
  word-break: break-word;
  text-align: left;
}
th {
  vertical-align: top;
  text-align: left;
}
.threeDots:hover {
  text-decoration: underline;
  color: blue;
  cursor: grab;
}
</style>