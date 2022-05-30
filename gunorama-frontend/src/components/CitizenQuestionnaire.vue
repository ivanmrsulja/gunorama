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
                    item-text="text"
                    item-value="value"
                    multiple
                    v-model="kalibri"
                  />
                </v-col>
              </v-row>
            </div>

            <!--TODO: dodaj neki v-show, na osnovu domena primjene iz zahtjeva prikazuj i skrivaj ove div-ove -->
            <div>
              <v-row align="center" justify="center">
                <v-col>
                  <h3 class="subheader">Domeni primjene i konkretne namjene</h3>
                </v-col>
              </v-row>

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

            <div>
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

            <div>
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

            <div>
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
export default {
  name: "CitizenQuestionnaire",
  data: () => {
    return {
      btnLoading: false,
      snackbar: false,
      text: "Wrong username/password combination.",
      timeout: 5000,
      dostupniKalibri: [],
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
      zahtjevId: 1, //TODO: ovo dobavi iz url,
      tezinaDivljaci: null,
      daljinaMete: null,
      skitTrap: "-1",
      nosenjePosjedovanje: "-1",
      mehanizmiHranjenja: [],
      mehanizmiOkidanja: [],
      kalibri: [],
    };
  },
  mounted() {
    //TODO: dobavi kalibre
    //TODO: dobavi id zahtjeva iz url-a, pa dobavi zahtjev
  },
  methods: {
    fileQuestionnaire() {
      let payload = {};
      payload["zahtjevId"] = this.zahtjevId;
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

      console.log(payload);
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
