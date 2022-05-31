import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import axios from "axios";

import jwt_decode from "jwt-decode";

Vue.config.productionTip = false;

Vue.filter("capitalize", function (param) {
  return param.charAt(0).toUpperCase() + param.slice(1).toLowerCase();
});

Vue.filter("removeUnderscore", (param) => {
  return param.replaceAll("_", " ");
});
// Configure axios to always include JWT when sending a request
axios.interceptors.request.use(
  (config) => {
    let jwt = localStorage.getItem("jwt");
    if (jwt) {
      if (config.headers) {
        config.headers.Authorization = `Bearer ${jwt}`;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Authority guard
router.beforeEach((to, from, next) => {
  const { authenticated, authorities } = to.meta;

  if (authenticated) {
    let jwt = localStorage.getItem("jwt");
    if (jwt) {
      let decodedToken = jwt_decode(jwt);
      if (authorities.some((element) => decodedToken.roles.includes(element))) {
        next();
      } else {
        next({ name: "Login" });
      }
    } else {
      next({ name: "Login" });
    }
  } else {
    next();
  }
});

new Vue({
  router,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
