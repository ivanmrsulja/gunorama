const axios = require("axios");

class QuestionnaireService {
  fileQuestionnaire(payload) {
    return axios.post(
      `${process.env.VUE_APP_BASE_PATH}/korisnicki-upitnik`,
      payload
    );
  }

  sendLicenceRequest(payload) {
    return axios.post(
      `${process.env.VUE_APP_BASE_PATH}/zahtjev-za-dozvolu`,
      payload
    );
  }
}

export const questionnaireService = new QuestionnaireService();
