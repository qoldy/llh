import authApi from "@/api/auth";
import token from "@/helpers/token";

export const getterTypes = {
  user: "[auth] user",
  isLoading: "[auth] is loading",
  isSubmitting: "[auth] is submitting",
  errors: "[auth] errors",
  isAnonymous: "[auth] is anonymous",
  isAuth: "[auth] is auth"
};

export const mutationTypes = {
  loginStart: "[auth] login start",
  loginSuccess: "[auth] login success",
  loginFailure: "[auth] login failure",

  registerStart: "[auth] register start",
  registerSuccess: "[auth] register success",
  registerFailure: "[auth] register failure",

  logoutStart: "[auth] logout start",
  logoutSuccess: "[auth] logout success",
  logoutFailure: "[auth] logout failure",

  getUserStart: "[auth] get user start",
  getUserSuccess: "[auth] get user success",
  getUserFailure: "[auth] get user failure"
};

export const actionTypes = {
  login: "[auth] login",
  register: "[auth] register",
  logout: "[auth] logout",
  getUser: "[auth] getUser"
};

const state = {
  data: null,
  isLoading: false,
  isSubmitting: false,
  errors: null
};

const getters = {
  [getterTypes.user]: state => state.data,
  [getterTypes.isLoading]: state => state.isLoading,
  [getterTypes.errors]: state => state.errors,
  [getterTypes.isSubmitting]: state => state.isSubmitting,
  [getterTypes.isAuth]: state => state.data != null,
  [getterTypes.isAnonymous]: state => state.data === null
};

const mutations = {
  [mutationTypes.loginStart](state) {
    state.isSubmitting = true;
    state.errors = null;
    state.data = null;
  },
  [mutationTypes.loginSuccess](state, userData) {
    state.isSubmitting = false;
    state.data = userData;
  },
  [mutationTypes.loginFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  },

  [mutationTypes.registerStart](state) {
    state.errors = null;
    state.isSubmitting = true;
    state.data = null;
  },
  [mutationTypes.registerSuccess](state) {
    state.isSubmitting = false;
  },
  [mutationTypes.registerFailure](state, errors) {
    state.isSubmitting = false;
    state.errors = errors;
  },

  [mutationTypes.logoutStart](state) {
    state.data = null;
    state.errors = null;
    state.isSubmitting = false;
    state.isLoading = false;
  },
  [mutationTypes.logoutSuccess]() {},
  [mutationTypes.logoutFailure]() {},

  [mutationTypes.getUserStart](state) {
    state.errors = null;
    state.isLoading = true;
    state.data = null;
  },
  [mutationTypes.getUserSuccess](state, userData) {
    state.isLoading = false;
    state.data = userData;
  },
  [mutationTypes.getUserFailure](state) {
    state.isLoading = false;
  }
};

const actions = {
  async [actionTypes.login]({ commit }, { userCredentials }) {
    try {
      commit(mutationTypes.loginStart);
      const data = await authApi.login(userCredentials);
      token.set(data.access_token);
      commit(mutationTypes.loginSuccess, data.user);
    } catch (e) {
      commit(mutationTypes.loginFailure, e.response.data);
      throw e;
    }
  },
  async [actionTypes.register]({ commit }, { userCredentials }) {
    try {
      commit(mutationTypes.registerStart);
      await authApi.register(userCredentials);
      commit(mutationTypes.registerSuccess);
    } catch (e) {
      commit(mutationTypes.registerFailure, e.response.data);
    }
  },
  [actionTypes.logout]({ commit }) {
    commit(mutationTypes.logoutStart);
    token.remove();
    commit(mutationTypes.logoutSuccess);
  },
  async [actionTypes.getUser]({ commit }) {
    try {
      commit(mutationTypes.getUserStart);
      const userData = await authApi.getUser();
      commit(mutationTypes.getUserSuccess, userData);
    } catch (e) {
      commit(mutationTypes.getUserFailure);
    }
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
