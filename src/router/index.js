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
    component: () => import("../views/Data")
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
        component: () => import("@/components/PulseList")
      },
      {
        path: "sleep",
        name: "sleepList",
        component: () => import("@/components/SleepList")
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
