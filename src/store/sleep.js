import sleepApi from "@/api/sleep";

export const getterTypes = {
  sleepData: "[sleep] sleep data",
  isLoading: "[sleep] is loading",
  isSubmitting: "[sleep] is submitting",
  errors: "[sleep] errors"
};

export const mutationTypes = {
  addSleepStart: "[sleep] add sleep start",
  addSleepSuccess: "[sleep] add sleep success",
  addSleepFailure: "[sleep] add sleep failure",

  editSleepStart: "[sleep] edit sleep start",
  editSleepSuccess: "[sleep] edit sleep success",
  editSleepFailure: "[sleep] edit sleep failure",

  deleteSleepStart: "[sleep] delete sleep start",
  deleteSleepSuccess: "[sleep] delete sleep success",
  deleteSleepFailure: "[sleep] delete sleep failure"
};

export const actionTypes = {
  addSleep: "[sleep] add sleep",
  editSleep: "[sleep] edit sleep",
  deleteSleep: "[sleep] delete sleep"
};

const state = {
  data: null,
  isLoading: false,
  isSubmitting: false,
  errors: null
};

const getters = {
  [getterTypes.sleepData]: state => state.data,
  [getterTypes.isLoading]: state => state.isLoading,
  [getterTypes.isSubmitting]: state => state.isSubmitting,
  [getterTypes.errors]: state => state.errors
};

const mutations = {
  // Мутации на добаление
  [mutationTypes.addSleepStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.addSleepSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.addSleepFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  },

  // Мутации на изменение
  [mutationTypes.editSleepStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.editSleepSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.editSleepFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  },

  // Мутации на удаление
  [mutationTypes.deleteSleepStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.deleteSleepSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.deleteSleepFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  }
};

const actions = {
  // Экшен на добавление
  async [actionTypes.addSleep]({ commit }, { sleepData }) {
    try {
      commit(mutationTypes.addSleepStart);
      await sleepApi.add(sleepData);
      commit(mutationTypes.addSleepSuccess);
    } catch (e) {
      commit(mutationTypes.addSleepFailure, e.response.data);
      throw e;
    }
  },

  // Экшен на редактирование
  async [actionTypes.editSleep]({ commit }, { sleepId, sleepData }) {
    try {
      commit(mutationTypes.editSleepStart);
      await sleepApi.edit(sleepId, sleepData);
      commit(mutationTypes.editSleepSuccess);
    } catch (e) {
      commit(mutationTypes.editSleepFailure, e.response.data);
      throw e;
    }
  },

  // Экшен на удаление
  async [actionTypes.deleteSleep]({ commit }, { sleepId }) {
    try {
      commit(mutationTypes.deleteSleepStart);
      await sleepApi.remove(sleepId);
      commit(mutationTypes.deleteSleepSuccess);
    } catch (e) {
      commit(mutationTypes.deleteSleepFailure, e.response.data);
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
