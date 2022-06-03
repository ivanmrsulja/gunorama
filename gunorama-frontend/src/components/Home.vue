<template>
  <div class="container">
    <div class="bg-image"></div>

    <div class="bg-text">
      <h2>Tim 09 vam predstavlja</h2>
      <h1 style="font-size: 50px">GUNORAMA</h1>
      <p>Sistem za pomoć pri izdavanju oružne dozvole i preporuku oružja</p>
    </div>
    <audio id="foobar" src="../assets/guitar_sfx.mp3" preload="auto"></audio>
  </div>
</template>

<script>
import { authService } from "../service/authService";

export default {
  name: "Home",
  data: () => ({}),
  mounted() {
    document.body.addEventListener("click", function () {
      if (!authService.userLoggedIn()) {
        let sample = document.getElementById("foobar");
        if (sample) sample.play();
      }
    });
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.container {
  height: 100%;
  margin: 0px;
  padding: 0px;
  font-family: Arial, Helvetica, sans-serif;
}

.bg-image {
  /* The image used */
  background-image: url("../assets/background.jpg");

  /* Add the blur effect */
  filter: blur(3px);

  /* Full height */
  height: 100%;
  width: 107%;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

/* Position text in the middle of the page/image */
.bg-text {
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.6); /* Black w/opacity/see-through */
  color: white;
  font-weight: bold;
  border: 3px solid #f1f1f1;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
  width: 80%;
  padding: 20px;
  text-align: center;
  animation: fadeInAnimation ease 6s;
  animation-iteration-count: 1;
  animation-fill-mode: forwards;
}

@keyframes fadeInAnimation {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>
