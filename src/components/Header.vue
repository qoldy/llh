<template>
  <el-row class="header el-menu el-menu--horizontal">
    <div class="left-area">
      <router-link
        :to="{ name: isAuth ? 'data' : 'welcome' }"
        custom
        v-slot="{ navigate }"
      >
        <h1 @click="navigate" class="logo">
          Линия жизни
        </h1>
      </router-link>
    </div>
    <template v-if="!isLoading">
      <ul class="menu">
        <template v-if="isAnonymous">
          <router-link
            :to="{ name: 'login' }"
            custom
            v-slot="{ navigate, isActive }"
          >
            <li
              @click="navigate"
              :class="{ 'el-menu-item': true, 'is-active': isActive }"
            >
              Войти
            </li>
          </router-link>
          <router-link
            :to="{ name: 'register' }"
            custom
            v-slot="{ navigate, isActive }"
          >
            <li
              @click="navigate"
              :class="{ 'el-menu-item': true, 'is-active': isActive }"
            >
              Зарегистрироваться
            </li>
          </router-link>
        </template>
        <template v-if="isAuth">
          <router-link
            v-if="$route.name === 'project'"
            tag="li"
            class="el-menu-item"
            active-class="is-active"
            :to="{
              name: 'createTask',
              params: { projectId: this.$route.params.projectId }
            }"
            >Создать задачу
          </router-link>
          <li class="el-menu-item" @click="logout">Выйти</li>
        </template>
      </ul>
    </template>
  </el-row>
</template>

<script>
import { getterTypes, actionTypes } from "@/store/auth";
import { mapGetters } from "vuex";

export default {
  name: "Header",
  computed: {
    ...mapGetters({
      isAuth: getterTypes.isAuth,
      isAnonymous: getterTypes.isAnonymous,
      isLoading: getterTypes.isLoading
    })
  },
  methods: {
    logout() {
      this.$store.dispatch(actionTypes.logout).then(() => {
        if (this.$route.name !== "welcome")
          this.$router.push({ name: "welcome" });
      });
    }
  }
};
</script>

<style scoped lang="scss">
.header {
  min-height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;

  &::before,
  &::after {
    display: none;
  }
}

.logo {
  cursor: pointer;
}

.menu {
  display: flex;
  align-items: center;
}
</style>
