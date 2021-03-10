import pulseApi from "@/api/pulse";

export const getterTypes = {
  pulseList: "[pulse list] pulse list",
  isLoading: "[pulse list] is loading",
  isEmpty: "[pulse list] is empty"
};

export const mutationTypes = {
  getPulseListStart: "[pulse list] get pulse list start",
  getPulseListSuccess: "[pulse list] get pulse list success",
  getPulseListFailure: "[pulse list] get pulse list failure"
};

export const actionTypes = {
  getPulseList: "[pulse list] get pulse list"
};

const state = {
  data: null,
  isLoading: false
};

const getters = {
  [getterTypes.pulseList]: state => state.data,
  [getterTypes.isLoading]: state => state.isLoading,
  [getterTypes.isEmpty]: state => state.data === null || state.data.length === 0
};

const mutations = {
  [mutationTypes.getPulseListStart](state) {
    state.data = null;
    state.isLoading = true;
  },
  [mutationTypes.getPulseListSuccess](state, pulseDataList) {
    state.isLoading = false;
    state.data = pulseDataList;
  },
  [mutationTypes.getPulseListFailure](state) {
    state.isLoading = false;
  }
};

const actions = {
  async [actionTypes.getPulseList]({ commit }) {
    try {
      commit(mutationTypes.getPulseListStart);
      const pulseListData = await pulseApi.getAll();
      commit(mutationTypes.getPulseListSuccess, pulseListData);
    } catch (e) {
      commit(mutationTypes.getPulseListFailure);
    }
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
