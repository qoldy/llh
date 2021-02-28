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
  loginFailure: "[auth] login failure"
};

export const actionTypes = {
  login: "[auth] login"
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

const mutations = {};

const actions = {};

export default {
  state,
  getters,
  mutations,
  actions
};
