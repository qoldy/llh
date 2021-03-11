import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "welcome",
    component: () => import("../views/WelcomePage")
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/Login")
  },
  {
    path: "/register",
    name: "register",
    component: () => import("../views/Register")
  },
  {
    path: "/data",
    name: "data",
    redirect: { name: "pulseList" },
    component: () => import("../views/Data"),
    children: [
      {
        path: "pulse",
        name: "pulseList",
        components: {
          chart: () => import("@/components/charts/PulseChart"),
          pulseList: () => import("@/components/PulseList")
        }
      },
      {
        path: "sleep",
        name: "sleepList",
        components: {
          chart: () => import("@/components/charts/SleepChart"),
          sleepList: () => import("@/components/SleepList")
        }
      },
      {
        path: "temperature",
        name: "temperatureList",
        components: {
          chart: () => import("@/components/charts/TemperatureChart"),
          temperatureList: () => import("@/components/TemperatureList")
        }
      }
    ]
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
