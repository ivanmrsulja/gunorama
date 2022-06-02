<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="4" md="4">
        <v-flex class="text-center">
          <h1>Predaja zahtjeva za građanina</h1>
        </v-flex>
      </v-col>
    </v-row>
    <br />
    <br />
    <form @submit.prevent="submitQuestionnaire">
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="8" lg="6" xl="4">
          <v-card elevation="4">
            <div>
              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-text-field
                    v-model="email"
                    label="Email građanina"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-text-field
                    v-model="jmbg"
                    label="JMBG građanina"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-text-field
                    v-model="dioptrija"
                    type="number"
                    min="0"
                    step="0.05"
                    label="Izmjerena dioptrija na sistematskom pregledu"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-checkbox
                    v-model="prosaoPsiholoskuEvaluaciju"
                    label="Građanin prošao psihološku evaluaciju"
                  ></v-checkbox>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-checkbox
                    v-model="prosaoPsihijatrijskuEvaluaciju"
                    label="Građanin prošao psihijatrijsku evaluaciju"
                  ></v-checkbox>
                </v-col>
              </v-row>
              <v-row align="center" justify="center">
                <v-col cols="12" md="10">
                  <v-select
                    deletable-chips
                    clearable
                    chips
                    label="Dokumentacija"
                    :items="tipoviDokumenata"
                    item-text="text"
                    item-value="value"
                    multiple
                    v-model="dokumenti"
                  />
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
import { questionnaireService } from "../service/questionnaireService";

export default {
  name: "PoliceOfficerQuestionnaire",
  data() {
    return {
      snackbar: false,
      text: "",
      timeout: 2000,
      btnLoading: false,
      dokumenti: [],
      dioptrija: 0,
      email: "",
      jmbg: "",
      prosaoPsiholoskuEvaluaciju: false,
      prosaoPsihijatrijskuEvaluaciju: false,
      tipoviDokumenata: [
        {
          text: "Lična karta",
          value: "LICNA_KARTA",
        },
        {
          text: "Dokaz u članstvu u streljačkom klubu",
          value: "DOKAZ_O_CLANSTVU_U_STRELJACKOM_KLUBU",
        },
        {
          text: "Dokaz o položenom lovačkom ispitu",
          value: "DOKAZ_O_POLOZENOM_LOVACKOM_ISPITU",
        },
        {
          text: "Dokaz o članstvu u lovačkom društvu",
          value: "DOKAZ_O_CLANSTVU_U_LOVACKOM_DRUSTVU",
        },
        {
          text: "Dokaz o članstvu u lovačko-streljačkom klubu",
          value: "DOKAZ_O_CLANSTVU_U_LOVACKO_STRELJACKOM_DRUSTVU",
        },
        {
          text: "Dokaz o ugroženosti lične bezbjednosti",
          value: "DOKAZ_O_UGROZENOSTI_LICNE_BEZBIJEDNOSTI",
        },
        {
          text: "Dokaz o položenoj obuci za rukovanje oružjem",
          value: "DOKAZ_O_POLOZENOJ_OBUCI_ZA_RUKOVANJE_ORUZJEM",
        },
        {
          text: "Dokaz o neosuđivanosti za nasilna krivična djela",
          value: "DOKAZ_O_NEOSUDJIVANOSTI_ZA_NASILNA_KRIVICNA_DJELA",
        },
        {
          text: "Dokaz o uplaćenim neophodnim taksama",
          value: "DOKAZ_O_UPLACENIM_NEOPHODNIM_TAKSAMA",
        },
        {
          text: "Dokaz o plaćenoj članarini u lovačkom ili streljačkom klubu",
          value: "DOKAZ_O_PLACENOJ_CLANARINI_U_LOVACKOM_ILI_STRELJACKOM_KLUBU",
        },
      ],
    };
  },
  methods: {
    submitQuestionnaire() {
      if (this.email.trim() === "" || !this.validateEmail()) {
        this.text = "Morate unijeti ispravan email.";
        this.snackbar = true;
        return;
      }

      if (this.jmbg.trim() === "" || !this.validateJMBG()) {
        this.text = "Morate unijeti ispravan jmbg.";
        this.snackbar = true;
        return;
      }

      if (!this.dioptrija) {
        this.text = "Morate unijeti dioptriju.";
        this.snackbar = true;
        return;
      }

      this.btnLoading = true;
      let zahtjev = {
        jmbgKorisnika: this.jmbg,
        emailKorisnika: this.email,
        dioptrija: this.dioptrija,
        prosaoPsiholoskuEvaluaciju: this.prosaoPsiholoskuEvaluaciju,
        prosaoPsihijatrijskuEvaluaciju: this.prosaoPsihijatrijskuEvaluaciju,
        dokumenta: this.dokumenti,
      };
      questionnaireService
        .sendLicenceRequest(zahtjev)
        .then((response) => {
          if (response.data.odobren) {
            this.text =
              "Gradjaninu izdata dozvola za " + response.data.domenPrimjene;
          } else if (response.data.razlogOdbijanja !== null) {
            this.text = response.data.razlogOdbijanja;
          } else {
            this.text = response.data.nepravilnost;
          }
          this.snackbar = true;
          this.btnLoading = false;
        })
        .catch((error) => {
          this.text = error.response.data.message;
          this.snackbar = true;
          this.btnLoading = false;
        });
    },
    validateEmail() {
      return this.email.match(
        /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
      );
    },
    validateJMBG() {
      return this.jmbg.match(/[0-9]{13}/);
    },
  },
};
</script>
