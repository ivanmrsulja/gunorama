<template>
  <v-app-bar app color="primary" dark>
    <div class="d-flex align-center home-icon" @click="redirect('Home')">
      <v-img width="50" height="50" :src="require('../assets/mup-rs.png')" />

      <span
        class="shrink mt-1 hidden-sm-and-down app-name"
        contain
        min-width="100"
      >
        gunorama
      </span>
    </div>

    <v-spacer></v-spacer>

    <v-btn
      text
      v-if="loggedIn === true && roles === 'GRADJANIN'"
      @click="redirect('ApprovedRequestsView')"
    >
      Pregled odobrenih zahtjeva
    </v-btn>

    <v-btn
      text
      v-if="loggedIn === true && roles === 'POLICIJSKI_SLUZBENIK'"
      @click="redirect('PoliceOfficerQuestionnaireView')"
    >
      Podnesi novi zahtjev
    </v-btn>

    <v-btn v-if="!loggedIn" text @click="redirect('Login')"> Prijava </v-btn>
    <v-btn v-else text @click="logout"> Odjava </v-btn>
  </v-app-bar>
</template>

<script>
import { authService } from "../service/authService";
export default {
  name: "Navbar",
  data: () => {
    return {
      roles: "",
      loggedIn: false,
    };
  },
  mounted() {
    if (authService.userLoggedIn()) {
      this.loggedIn = true;
      this.roles = authService.getRoles();
    }
    this.$root.$on("loginSuccess", (roles) => {
      this.loggedIn = true;
      this.roles = roles;
    });
  },
  methods: {
    redirect(name) {
      this.$router.push({ name }).catch((_) => {});
    },
    logout() {
      authService.removeJwt();
      this.loggedIn = false;
      this.roles = "";
      this.$router.push({ name: "Login" });
    },
  },
};
</script>

<style scoped>
.home-icon {
  cursor: pointer;
}
.app-name {
  font-family: "Montserrat", sans-serif;
}
</style>
