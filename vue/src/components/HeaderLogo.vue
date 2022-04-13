<template>
  <div>
    <header>
      <!-- <img
        class="logo"
        src="https://images.squarespace-cdn.com/content/v1/55ef2da9e4b03f6e1ef0cd28/1558034939902-652JBW936829PJQM7K30/Mark.jpg"
      /> -->
      <img class="logo" src="../../img/mark-update.png" />
      <h1 class="title">Tech Gram</h1>
    </header>

    <link href="http://fonts.cdnfonts.com/css/billabong" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Grand+Hotel&display=swap" rel="stylesheet">

    
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Fredoka+One"
    />
  </div>
</template>

<script>
export default {
    name:  "header-logo"
};
</script>

<style>
header {
  display: flex;
  
  text-align: center;
  background-color: #167fa5;
  /* background-color:aliceblue; */
  border-bottom: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  margin-bottom: 20px;

  /* edit below */
  width: 80%;
  height: 80px;
  vertical-align: middle;
}
.title {
  font-size: 40px;
  /* font-family: Fredoka One; */
  /* font-family: 'Grand Hotel', cursive; */
  font-family: 'Billabong', sans-serif;
  
}
</style>