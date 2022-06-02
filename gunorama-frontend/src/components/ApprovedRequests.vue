<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="12" md="6">
        <v-flex class="text-center"> <h1>Odobreni zahtjevi</h1> </v-flex>
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-col cols="12" md="10">
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">Id</th>
                <th class="text-left">Domen primjene</th>
                <th class="text-left">Akcija</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="request in requests" :key="request.id">
                <td>{{ request.id }}</td>
                <td>
                  {{ request.domenPrimjene | capitalize | removeUnderscore }}
                </td>
                <td>
                  <v-btn
                    elevation="2"
                    depressed
                    text
                    :to="{
                      name: 'CitizenQuestionnaireView',
                      params: { id_zahtjeva: request.id },
                    }"
                  >
                    Popuni upitnik
                  </v-btn>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { authService } from "../service/authService";
import { korisnikService } from "../service/korisnikService";

export default {
  name: "ApprovedRequests",
  data: () => {
    return {
      requests: [],
    };
  },
  mounted() {
    korisnikService
      .getApprovedRequestsForUser(authService.userId())
      .then((response) => {
        this.requests = response.data;
      });
  },
};
</script>
