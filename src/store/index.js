import Vue from "vue";
import Vuex from "vuex";
import auth from "@/store/auth";
import pulseList from "@/store/pulseList";
import sleepList from "@/store/sleepList";
import temperatureList from "@/store/temperatureList";
import pulse from "@/store/pulse";
import sleep from "@/store/sleep";
import temperature from "@/store/temperature";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    auth,
    pulseList,
    sleepList,
    temperatureList,
    pulse,
    sleep,
    temperature
  }
});
