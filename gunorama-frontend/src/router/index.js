import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);
//TODO: dodati komponente da se prikazu adminu svi pristigli zahtjevi i korisniku svi njegovi zahtjevi

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/HomeView.vue"),
  },
  {
    path: "/prijava",
    name: "Login",
    component: () => import("../views/LoginView.vue"),
  },
  {
    //TODO: dodati id zahtjeva za koji se popunjava upitnik
    path: "/upitnik-gradjanina",
    name: "CitizenQuestionnaireView",
    component: () => import("../views/CitizenQuestionnaireView.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
