<template>
  <div class="home">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header>
      <img
        class="logo"
        src="https://images.squarespace-cdn.com/content/v1/55ef2da9e4b03f6e1ef0cd28/1558034939902-652JBW936829PJQM7K30/Mark.jpg"
      />
      <h1 class="title">Tech Gram</h1>
    </header>
    <picture-card></picture-card>
    <login></login>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Fredoka+One">
  </div>
</template>

<script>
import SideMenu from "../components/SideMenu.vue";
import PictureCard from "../components/PictureCard.vue";
export default {
  name: "home",
  components: {
    SideMenu,
    PictureCard,
  },
};
</script>
<style>
body {
  background-color: #00adee;
  margin: 0px;
}
.home {
  display: flex;
  flex-direction: column;
  height: 100px;
  
}
.sideMenu {
  border-left: 2px solid black;
  background-color: aliceblue;
  position: absolute;
  right: 0px;
  width: 350px;
  height: 100%;
  display: flex;
  justify-content: space-around;
}
.title {
  font-size: 40px;
  font-family: Fredoka One;
}
header {
  display: flex;
  text-align: center;
  background-color: #167fa5;
  border-bottom: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  margin-bottom: 20px;
}
.logo {
  width: 70px;
  height: 70px;
}
</style>