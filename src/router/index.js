import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store/index";
import { getterTypes, actionTypes } from "@/store/auth";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "welcome",
    component: () => import("../views/WelcomePage"),
    meta: {
      isFree: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/Login"),
    meta: {
      isFree: true
    }
  },
  {
    path: "/register",
    name: "register",
    component: () => import("../views/Register"),
    meta: {
      isFree: true
    }
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

router.beforeEach(async (to, from, next) => {
  let isAuth = store.getters[getterTypes.isAuth];

  if (to.meta.isFree) {
    next();
  } else {
    if (isAuth) {
      next();
    } else {
      await store.dispatch(actionTypes.getUser);
      isAuth = store.getters[getterTypes.isAuth];
      if (isAuth) {
        next();
      } else {
        next({ name: "login" });
      }
    }
  }
});

export default router;
