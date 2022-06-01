import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);
const roles = {
  policijskiSluzbenik: "POLICIJSKI_SLUZBENIK",
  gradjanin: "GRADJANIN",
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/HomeView.vue"),
    meta: {
      authenticated: false,
      authorities: [],
    },
  },
  {
    path: "/prijava",
    name: "Login",
    component: () => import("../views/LoginView.vue"),
    meta: {
      authenticated: false,
      authorities: [],
    },
  },
  {
    //TODO: dodati id zahtjeva za koji se popunjava upitnik
    path: "/:id_zahtjeva/upitnik-gradjanina",
    name: "CitizenQuestionnaireView",
    component: () => import("../views/CitizenQuestionnaireView.vue"),
    meta: {
      authenticated: true,
      authorities: [roles.gradjanin],
    },
  },
  {
    path: "/odobreni-zahtjevi",
    name: "ApprovedRequestsView",
    component: () => import("../views/ApprovedRequestsView.vue"),
    meta: {
      authenticated: true,
      authorities: [roles.gradjanin],
    },
  },
  {
    path: "/upitnik-sluzbenika",
    name: "PoliceOfficerQuestionnaireView",
    component: () => import("../views/PoliceOfficerQuestionnaireView.vue"),
    meta: {
      authenticated: true,
      authorities: [roles.policijskiSluzbenik],
    },
  },
];

const router = new VueRouter({
  routes,
});

export default router;
