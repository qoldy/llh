import Vue from "vue";
import Vuex from "vuex";
import auth from "@/store/auth";
import pulseList from "@/store/pulseList";
import sleepList from "@/store/sleepList";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    auth,
    pulseList,
    sleepList
  }
});
