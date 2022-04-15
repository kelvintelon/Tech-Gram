<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
    />
    <i
      class="material-icons"
      id="notLiked"
      @click="whenClicked"
      v-if="notLiked"
      >favorite_border</i
    >
    <span class="material-icons" id="Liked" v-else>favorite</span>
  </div>
</template>

<script>
export default {
  name: "like-button",
  data() {
    return {
      notLiked: true,
    };
  },
  methods: {
    delay(time) {
      return new Promise((resolve) => setTimeout(resolve, time));
    },
    whenClicked() {
      this.notLiked = false;
      this.delay(400).then(() => this.notLiked = true);
    },
  },
};
</script>

<style>
#notLiked {
  color: #00adee;
  cursor: default;
}

#Liked {
  color: #00adee;
}
</style>