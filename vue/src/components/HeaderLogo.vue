<template>
  <div>
    <header>
      <img
        class="logo"
        src="https://images.squarespace-cdn.com/content/v1/55ef2da9e4b03f6e1ef0cd28/1558034939902-652JBW936829PJQM7K30/Mark.jpg"
      />
      <h1 class="title">Tech Gram</h1>
    </header>
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
  border-bottom: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  margin-bottom: 20px;
}
.title {
  font-size: 40px;
  font-family: Fredoka One;
}
</style>