import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import locale from "element-ui/lib/locale/lang/ru-RU";
import "element-ui/lib/theme-chalk/reset.css";
import "normalize.css/normalize.css";

Vue.config.productionTip = false;
Vue.use(ElementUI, { locale });

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");

//TODO:
// - сделать форму добавления температуры
// - сделать везде формы для редактирования данных
// - сделать везде кнопки для удаления данных
// - сделать сортировку по месяцу
// - сделать защиту роутов
