const axios = require("axios");

class KalibarService {
  getAll() {
    return axios.get(`${process.env.VUE_APP_BASE_PATH}/kalibri`);
  }
}

export const kalibarService = new KalibarService();
