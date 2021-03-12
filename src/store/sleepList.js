import sleepApi from "@/api/sleep";
import { mutationTypes as authMutationTypes } from "@/store/auth";

export const getterTypes = {
  sleepList: "[sleep list] sleep list",
  isLoading: "[sleep list] is loading",
  isEmpty: "[sleep list] is empty"
};

export const mutationTypes = {
  getSleepListStart: "[sleep list] get sleep list start",
  getSleepListSuccess: "[sleep list] get sleep list success",
  getSleepListFailure: "[sleep list] get sleep list failure"
};

export const actionTypes = {
  getSleepList: "[sleep list] get sleep list"
};

const state = {
  data: null,
  isLoading: false
};

const getters = {
  [getterTypes.sleepList]: state => state.data,
  [getterTypes.isLoading]: state => state.isLoading,
  [getterTypes.isEmpty]: state => state.data === null || state.data.length === 0
};

const mutations = {
  [mutationTypes.getSleepListStart](state) {
    state.isLoading = true;
  },
  [mutationTypes.getSleepListSuccess](state, sleepDataList) {
    state.isLoading = false;
    state.data = sleepDataList;
  },
  [mutationTypes.getSleepListFailure](state) {
    state.isLoading = false;
  },

  [authMutationTypes.logoutStart](state) {
    state.data = null;
    state.errors = null;
  }
};

const actions = {
  async [actionTypes.getSleepList]({ commit }) {
    try {
      commit(mutationTypes.getSleepListStart);
      const sleepListData = await sleepApi.getAll();
      commit(mutationTypes.getSleepListSuccess, sleepListData);
    } catch (e) {
      commit(mutationTypes.getSleepListFailure);
    }
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
