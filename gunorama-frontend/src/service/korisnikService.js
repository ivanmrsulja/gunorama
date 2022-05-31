const axios = require("axios");

class KorisnikService {
  getApprovedRequestsForUser(userId) {
    return axios.get(
      `${process.env.VUE_APP_BASE_PATH}/korisnici/${userId}/odobreni-zahtjevi`
    );
  }

  getApprovedRequestForUser(userId, requestId) {
    return axios.get(
      `${process.env.VUE_APP_BASE_PATH}/korisnici/${userId}/odobreni-zahtjevi/${requestId}`
    );
  }
}

export const korisnikService = new KorisnikService();
