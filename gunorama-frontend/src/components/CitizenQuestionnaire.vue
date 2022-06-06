<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="6" md="6" class="header-col">
        <h1>Upitnik građanina</h1>
      </v-col>
    </v-row>
    <form @submit.prevent="fileQuestionnaire">
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="8" lg="6" xl="4">
          <v-card elevation="4">
            <div>
              <v-row align="center" justify="center">
                <v-col>
                  <h3 class="subheader">Lične preference</h3>
                </v-col>
              </v-row>

              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-select
                    deletable-chips
                    clearable
                    chips
                    label="Mehanizmi hranjenja"
                    :items="dostupniMehanizmiHranjenja"
                    item-text="text"
                    item-value="value"
                    multiple
                    v-model="mehanizmiHranjenja"
                  />
                </v-col>
              </v-row>

              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-select
                    deletable-chips
                    clearable
                    chips
                    label="Mehanizmi okidanja"
                    :items="dostupniMehanizmiOkidanja"
                    item-text="text"
                    item-value="value"
                    multiple
                    v-model="mehanizmiOkidanja"
                  />
                </v-col>
              </v-row>

              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-select
                    deletable-chips
                    clearable
                    chips
                    label="Kalibri"
                    :items="dostupniKalibri"
                    item-text="naziv"
                    item-value="id"
                    multiple
                    v-model="kalibri"
                  />
                </v-col>
              </v-row>

              <v-row align="center" justify="center">
                <v-col cols="10" md="5">
                  <v-text-field
                    type="number"
                    min="0"
                    max="20"
                    step="0.1"
                    v-model="minimalniKalibar"
                    label="Minimalni prečnik kalibra u mm"
                  />
                </v-col>
                <v-col cols="10" md="5">
                  <v-text-field
                    type="number"
                    min="0"
                    max="20"
                    step="0.1"
                    v-model="maksimalniKalibar"
                    label="Maksimalni prečnik kalibra u mm"
                  />
                </v-col>
              </v-row>
            </div>

            <v-row align="center" justify="center">
              <v-col>
                <h3 class="subheader">Domeni primjene i konkretne namjene</h3>
              </v-col>
            </v-row>

            <div v-if="zahtjev.domenPrimjene === 'LOV'">
              <v-row align="center" justify="center">
                <v-col>
                  <h4 class="subheader">Lov</h4>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="8" sm="8" md="8" lg="10" xl="10">
                  <v-text-field
                    label="Težina divljači"
                    type="number"
                    min="0"
                    v-model="tezinaDivljaci"
                  />
                </v-col>
              </v-row>
            </div>

            <div v-else-if="zahtjev.domenPrimjene === 'STRELJASTVO'">
              <v-row align="center" justify="center">
                <v-col>
                  <h4 class="subheader">Streljaštvo</h4>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="8" sm="8" md="8" lg="10" xl="10">
                  <v-text-field
                    label="Daljina mete"
                    type="number"
                    min="0"
                    v-model="daljinaMete"
                  />
                </v-col>
              </v-row>
            </div>

            <div v-else-if="zahtjev.domenPrimjene === 'LOVNO_STRELJASTVO'">
              <v-row align="center" justify="center">
                <v-col>
                  <h4 class="subheader">Lovno streljaštvo</h4>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="8" sm="8" md="8" lg="10" xl="10">
                  <v-radio-group v-model="skitTrap" row>
                    <v-radio label="Skit" value="1" />
                    <v-radio label="Trap" value="2" />
                  </v-radio-group>
                </v-col>
              </v-row>
            </div>

            <div v-else>
              <v-row align="center" justify="center">
                <v-col>
                  <h4 class="subheader">Samoodbrana</h4>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="8" sm="8" md="8" lg="10" xl="10">
                  <v-radio-group v-model="nosenjePosjedovanje" row>
                    <v-radio label="Nošenje" value="1" />
                    <v-radio label="Posjedovanje" value="2" />
                  </v-radio-group>
                </v-col>
              </v-row>
            </div>

            <v-row align="center" justify="center">
              <v-col cols="8" sm="8" md="8" lg="10" xl="10">
                <v-btn
                  depressed
                  color="primary"
                  type="submit"
                  :loading="btnLoading"
                  :disabled="
                    skitTrap === '-1' &&
                    nosenjePosjedovanje === '-1' &&
                    tezinaDivljaci === null &&
                    daljinaMete === null
                  "
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
    <v-dialog
      width="50%"
      v-model="dialog"
      hide-overlay
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar dark color="primary">
          <v-toolbar-title> Predlozi </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>

        <v-container>
          <div>
            <v-row
              v-if="preporuka.preporucenoOruzje.length === 0"
              align="center"
              justify="center"
            >
              <h4 class="subheader">
                Nema predloga za konkretnu namjenu
                {{ preporuka.konkretnaNamjena | capitalize | removeUnderscore }}
              </h4>
            </v-row>
            <div v-else>
              <v-row align="center" justify="center">
                <v-col cols="12" md="6">
                  <v-flex class="text-center">
                    <h4 class="subheader">
                      Rezultati za konkretnu namjenu:
                      {{
                        preporuka.konkretnaNamjena
                          | capitalize
                          | removeUnderscore
                      }}
                    </h4>
                  </v-flex>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th class="text-left">Naziv</th>
                        <th class="text-left">Mehanizam hranjenja</th>
                        <th class="text-left">Mehanizam okidanja</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="oruzje in preporuka.preporucenoOruzje"
                        :key="oruzje.naziv"
                      >
                        <td>
                          {{ oruzje.naziv }}
                        </td>
                        <td>
                          {{
                            oruzje.mehanizamHranjenja
                              | capitalize
                              | removeUnderscore
                          }}
                        </td>
                        <td>
                          {{
                            oruzje.mehanizamOkidanja
                              | capitalize
                              | removeUnderscore
                          }}
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-row>
            </div>
            <div v-if="preporuka.dozvoljeniKalibri.length > 0">
              <v-row align="center" justify="center">
                <v-col cols="12" md="6">
                  <v-flex class="text-center">
                    <h4 class="subheader">
                      Dozvoljeni kalibri za konkretnu namjenu:
                      {{
                        preporuka.konkretnaNamjena
                          | capitalize
                          | removeUnderscore
                      }}
                    </h4>
                  </v-flex>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th class="text-left">Id</th>
                        <th class="text-left">Naziv</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="kalibar in preporuka.dozvoljeniKalibri"
                        :key="kalibar.id"
                      >
                        <td>
                          {{ kalibar.id }}
                        </td>
                        <td>
                          {{ kalibar.naziv }}
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-row>
            </div>

            <v-row align="center" justify="center" v-else>
              <v-col cols="12" md="6">
                <v-flex class="text-center">
                  <h4 class="subheader">
                    Ne postoje dozvoljeni kalibri koji zadovoljavaju navedene
                    kriterijume.
                  </h4>
                </v-flex>
              </v-col>
            </v-row>
          </div>
        </v-container>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import { korisnikService } from "../service/korisnikService";
import { authService } from "../service/authService";
import { kalibarService } from "../service/kalibarService";
import { questionnaireService } from "../service/questionnaireService";

export default {
  name: "CitizenQuestionnaire",
  data: () => {
    return {
      dialog: false,
      btnLoading: false,
      snackbar: false,
      text: "Wrong username/password combination.",
      timeout: 5000,
      dostupniKalibri: [],
      preporuka: {
        preporucenoOruzje: [],
        dozvoljeniKalibri: [],
        konkretnaNamjena: null,
      },
      dostupniMehanizmiHranjenja: [
        {
          text: "Obrtnočepni",
          value: "OBRTNOCEPNI",
        },
        {
          text: "Polu-automatski",
          value: "POLUAUTOMATSKI",
        },
        {
          text: "Pumparica",
          value: "PUMPARICA",
        },
        {
          text: "Preklapajući",
          value: "PREKLAPAJUCI",
        },
      ],
      dostupniMehanizmiOkidanja: [
        {
          text: "Otvoreni",
          value: "OTVORENI",
        },
        {
          text: "Zatvoreni",
          value: "ZATVORENI",
        },
      ],
      tezinaDivljaci: null,
      daljinaMete: null,
      skitTrap: "-1",
      nosenjePosjedovanje: "-1",
      mehanizmiHranjenja: [],
      mehanizmiOkidanja: [],
      kalibri: [],
      zahtjev: {},
      minimalniKalibar: 0.0,
      maksimalniKalibar: 20.0,
    };
  },
  mounted() {
    kalibarService.getAll().then((response) => {
      this.dostupniKalibri = response.data;
    });

    korisnikService
      .getApprovedRequestForUser(
        authService.userId(),
        this.$route.params.id_zahtjeva
      )
      .then((response) => {
        this.zahtjev = response.data;
      });
  },
  methods: {
    fileQuestionnaire() {
      this.btnLoading = true;
      let payload = {};
      payload["zahtjevId"] = this.$route.params.id_zahtjeva;
      if (this.skitTrap === "1") payload["skitIzabran"] = true;
      else if (this.skitTrap === "2") payload["trapIzabran"] = true;

      if (this.tezinaDivljaci !== null)
        payload["tezinaDivljaci"] = this.tezinaDivljaci;

      if (this.daljinaMete !== null) payload["daljinaMete"] = this.daljinaMete;

      if (this.nosenjePosjedovanje === "1") payload["nosenjeIzabrano"] = true;
      else if (this.nosenjePosjedovanje === "2")
        payload["posjedovanjeIzabrano"] = true;

      payload["mehanizmiOkidanja"] = this.mehanizmiOkidanja;
      payload["mehanizmiHranjenja"] = this.mehanizmiHranjenja;
      payload["kalibri"] = this.kalibri;
      payload["minimalniPrecnikUMilimetrima"] = this.minimalniKalibar;
      payload["maksimalniPrecnikUMilimetrima"] = this.maksimalniKalibar;
      //console.log(payload);
      questionnaireService
        .fileQuestionnaire(payload)
        .then((response) => {
          this.btnLoading = false;
          this.dialog = true;
          this.preporuka = response.data;
          // console.log(response.data);
        })
        .catch((error) => {
          this.btnLoading = false;
          this.snackbar = true;
          if (error.response) this.text = error.response.data.message;
          else this.text = "Desila se greška.";
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

.subheader {
  margin-left: 20px;
  color: rgb(99, 99, 99);
}
</style>
