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
  addSleepFailure: "[sleep] add sleep failure"
};

export const actionTypes = {
  addSleep: "[sleep] add sleep"
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
  }
};

const actions = {
  async [actionTypes.addSleep]({ commit }, { sleepData }) {
    console.log(sleepData);
    try {
      commit(mutationTypes.addSleepStart);
      await sleepApi.add(sleepData);
      commit(mutationTypes.addSleepSuccess);
    } catch (e) {
      commit(mutationTypes.addSleepFailure, e.response.data);
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
