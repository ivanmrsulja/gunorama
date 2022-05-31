const axios = require("axios");

class QuestionnaireService {
  fileQuestionnaire(payload) {
    return axios.post(
      `${process.env.VUE_APP_BASE_PATH}/korisnicki-upitnik`,
      payload
    );
  }
}

export const questionnaireService = new QuestionnaireService();
