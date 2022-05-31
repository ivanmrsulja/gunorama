<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="6" md="6" class="header-col">
        <h1>Prijava</h1>
      </v-col>
    </v-row>
    <form @submit.prevent="login">
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="8" lg="6" xl="4">
          <v-card elevation="4">
            <v-row align="center" justify="center">
              <v-col cols="8" sm="8" md="8" lg="10" xl="10">
                <v-text-field label="Email" required v-model="payload.email">
                </v-text-field>
              </v-col>
            </v-row>
            <v-row align="center" justify="center">
              <v-col cols="8" sm="8" md="8" lg="10" xl="10">
                <v-text-field
                  label="Lozinka"
                  :type="valuePassword ? 'password' : 'text'"
                  @click:append="() => (valuePassword = !valuePassword)"
                  :append-icon="valuePassword ? 'mdi-eye-off' : 'mdi-eye'"
                  required
                  v-model="payload.password"
                >
                </v-text-field>
              </v-col>
            </v-row>

            <v-row align="center" justify="center">
              <v-col cols="8" sm="8" md="8" lg="10" xl="10">
                <v-btn
                  depressed
                  color="primary"
                  type="submit"
                  :disabled="payload.email === '' || payload.password === ''"
                  :loading="loginBtnLoading"
                >
                  Potvrdi
                </v-btn>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
      </v-row>
    </form>

    <v-snackbar v-model="snackbar" :timeout="timeout">
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn color="blue" text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import { authService } from "../service/authService";
import jwtDecode from "jwt-decode";

export default {
  name: "Login",
  data: () => {
    return {
      valuePassword: String,
      payload: {
        email: "",
        password: "",
      },
      loginBtnLoading: false,
      snackbar: false,
      text: "Wrong username/password combination.",
      timeout: 5000,
    };
  },
  methods: {
    login() {
      this.loginBtnLoading = true;
      authService
        .login(this.payload)
        .then((response) => {
          this.loginBtnLoading = false;
          let decodedToken = jwtDecode(response.data.jwt);
          authService.setToken(response.data.jwt);
          this.$root.$emit("loginSuccess", decodedToken.roles);
          this.$router.push({ name: "Home" });
        })
        .catch((error) => {
          this.loginBtnLoading = false;
          this.snackbar = true;
          if (error.response) this.text = error.response.data.message;
          else this.text = "Wrong username/password combination.";
        });
    },
  },
};
</script>

<style scoped>
.header-col {
  text-align: center;
  margin-top: 2%;
}
</style>
