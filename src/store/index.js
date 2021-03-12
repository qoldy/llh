import Vue from "vue";
import Vuex from "vuex";
import auth from "@/store/auth";
import pulseList from "@/store/pulseList";
import sleepList from "@/store/sleepList";
import temperatureList from "@/store/temperatureList";
import pulse from "@/store/pulse";
import sleep from "@/store/sleep";

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
    sleep
  }
});

// TODO:
// - сделать очищение стора при выходе
// - сделать защиту роутов на логин
// - сделать добавление, удаление и редактирование для всех сущностей
// - сделать сортировку по месяцам
// - поменять во всех инпутах формат даты и времени
