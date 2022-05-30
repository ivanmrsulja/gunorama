<template>
  <v-app-bar app color="primary" dark>
    <div class="d-flex align-center">GUNORAMA</div>

    <v-spacer></v-spacer>

    <v-btn text @click="redirect('CitizenQuestionnaireView')">
      Upitnik građanina
    </v-btn>

    <v-btn text @click="redirect('Login')"> Prijava </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  name: "Navbar",

  methods: {
    redirect(name) {
      this.$router.push({ name }).catch((_) => {});
    },
  },
};
</script>
