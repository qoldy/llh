import pulseApi from "@/api/pulse";

export const getterTypes = {
  pulseData: "[pulse] pulse data",
  isLoading: "[pulse] is loading",
  isSubmitting: "[pulse] is submitting",
  errors: "[pulse] errors"
};

export const mutationTypes = {
  addPulseStart: "[pulse] add pulse start",
  addPulseSuccess: "[pulse] add pulse success",
  addPulseFailure: "[pulse] add pulse failure",

  editPulseStart: "[pulse] edit pulse start",
  editPulseSuccess: "[pulse] edit pulse success",
  editPulseFailure: "[pulse] edit pulse failure",

  deletePulseStart: "[pulse] delete pulse start",
  deletePulseSuccess: "[pulse] delete pulse success",
  deletePulseFailure: "[pulse] delete pulse failure"
};

export const actionTypes = {
  addPulse: "[pulse] add pulse",
  editPulse: "[pulse] edit pulse",
  deletePulse: "[pulse] delete pulse"
};

const state = {
  data: null,
  isLoading: false,
  isSubmitting: false,
  errors: null
};

const getters = {
  [getterTypes.pulseData]: state => state.data,
  [getterTypes.isLoading]: state => state.isLoading,
  [getterTypes.isSubmitting]: state => state.isSubmitting,
  [getterTypes.errors]: state => state.errors
};

const mutations = {
  [mutationTypes.addPulseStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.addPulseSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.addPulseFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  },

  [mutationTypes.editPulseStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.editPulseSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.editPulseFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  },

  [mutationTypes.deletePulseStart](state) {
    state.isSubmitting = true;
    state.errors = null;
  },
  [mutationTypes.deletePulseSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.deletePulseFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  }
};

const actions = {
  async [actionTypes.addPulse]({ commit }, { pulseData }) {
    try {
      commit(mutationTypes.addPulseStart);
      await pulseApi.add(pulseData);
      commit(mutationTypes.addPulseSuccess);
    } catch (e) {
      commit(mutationTypes.addPulseFailure, e.response.data);
      throw e;
    }
  },
  async [actionTypes.editPulse]({ commit }, { pulseId, pulseData }) {
    try {
      commit(mutationTypes.editPulseStart);
      await pulseApi.edit(pulseId, pulseData);
      commit(mutationTypes.editPulseSuccess);
    } catch (e) {
      commit(mutationTypes.editPulseFailure, e.response.data);
      throw e;
    }
  },
  async [actionTypes.deletePulse]({ commit }, { pulseId }) {
    try {
      commit(mutationTypes.deletePulseStart);
      await pulseApi.remove(pulseId);
      commit(mutationTypes.deletePulseSuccess);
    } catch (e) {
      commit(mutationTypes.deletePulseFailure, e.response.data);
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
