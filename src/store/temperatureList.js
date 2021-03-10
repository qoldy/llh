import temperatureApi from "@/api/temperature";

export const getterTypes = {
  temperatureList: "[temperature list] temperature list",
  isLoading: "[temperature list] is loading",
  isEmpty: "[temperature list] is empty"
};

export const mutationTypes = {
  getTemperatureListStart: "[temperature list] get temperature list start",
  getTemperatureListSuccess: "[temperature list] get temperature list success",
  getTemperatureListFailure: "[temperature list] get temperature list failure"
};

export const actionTypes = {
  getTemperatureList: "[temperature list] get temperature list"
};

const state = {
  data: null,
  isLoading: false
};

const getters = {
  [getterTypes.temperatureList]: state => state.data,
  [getterTypes.isLoading]: state => state.isLoading,
  [getterTypes.isEmpty]: state => state.data === null || state.data.length === 0
};

const mutations = {
  [mutationTypes.getTemperatureListStart](state) {
    state.data = null;
    state.isLoading = true;
  },
  [mutationTypes.getTemperatureListSuccess](state, temperatureDataList) {
    state.isLoading = false;
    state.data = temperatureDataList;
  },
  [mutationTypes.getTemperatureListFailure](state) {
    state.isLoading = false;
  }
};

const actions = {
  async [actionTypes.getTemperatureList]({ commit }) {
    try {
      commit(mutationTypes.getTemperatureListStart);
      const temperatureListData = await temperatureApi.getAll();
      commit(mutationTypes.getTemperatureListSuccess, temperatureListData);
    } catch (e) {
      commit(mutationTypes.getTemperatureListFailure);
    }
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
