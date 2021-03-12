import temperatureApi from "@/api/temperature";

export const getterTypes = {
  temperatureData: "[temperature] temperature data",
  isLoading: "[temperature] is loading",
  isSubmitting: "[temperature] is submitting",
  errors: "[temperature] errors"
};

export const mutationTypes = {
  addTemperatureStart: "[temperature] add temperature start",
  addTemperatureSuccess: "[temperature] add temperature success",
  addTemperatureFailure: "[temperature] add temperature failure",

  editTemperatureStart: "[temperature] edit temperature start",
  editTemperatureSuccess: "[temperature] edit temperature success",
  editTemperatureFailure: "[temperature] edit temperature failure",

  deleteTemperatureStart: "[temperature] delete temperature start",
  deleteTemperatureSuccess: "[temperature] delete temperature success",
  deleteTemperatureFailure: "[temperature] delete temperature failure"
};

export const actionTypes = {
  addTemperature: "[temperature] add temperature",
  editTemperature: "[temperature] edit temperature",
  deleteTemperature: "[temperature] delete temperature"
};

const state = {
  data: null,
  isLoading: false,
  isSubmitting: false,
  errors: null
};

const getters = {
  [getterTypes.temperatureData]: state => state.data,
  [getterTypes.isLoading]: state => state.isLoading,
  [getterTypes.isSubmitting]: state => state.isSubmitting,
  [getterTypes.errors]: state => state.errors
};

const mutations = {
  // Мутации на добавление
  [mutationTypes.addTemperatureStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.addTemperatureSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.addTemperatureFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  },

  // Мутации на изменение
  [mutationTypes.editTemperatureStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.editTemperatureSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.editTemperatureFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  },

  // Мутации на удаление
  [mutationTypes.deleteTemperatureStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.deleteTemperatureSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.deleteTemperatureFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  }
};

const actions = {
  // Экшен на добавление
  async [actionTypes.addTemperature]({ commit }, { temperatureData }) {
    try {
      commit(mutationTypes.addTemperatureStart);
      await temperatureApi.add(temperatureData);
      commit(mutationTypes.addTemperatureSuccess);
    } catch (e) {
      commit(mutationTypes.addTemperatureFailure, e.response.data);
      throw e;
    }
  },

  // Экшен на редактирование
  async [actionTypes.editTemperature](
    { commit },
    { temperatureId, temperatureData }
  ) {
    try {
      commit(mutationTypes.editTemperatureStart);
      await temperatureApi.edit(temperatureId, temperatureData);
      commit(mutationTypes.editTemperatureSuccess);
    } catch (e) {
      commit(mutationTypes.editTemperatureFailure, e.response.data);
      throw e;
    }
  },

  // Экшен на удаление
  async [actionTypes.deleteTemperature]({ commit }, { temperatureId }) {
    try {
      commit(mutationTypes.deleteTemperatureStart);
      await temperatureApi.remove(temperatureId);
      commit(mutationTypes.deleteTemperatureSuccess);
    } catch (e) {
      commit(mutationTypes.deleteTemperatureFailure, e.response.data);
      throw e;
    }
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
