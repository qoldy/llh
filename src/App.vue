<template>
  <el-container>
    <el-header>
      <AppHeader />
    </el-header>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script>
import AppHeader from "@/components/Header";
import { actionTypes } from "@/store/auth";

export default {
  name: "App",
  components: {
    AppHeader
  },
  mounted() {
    this.$store.dispatch(actionTypes.getUser);
  }
};
</script>

<style lang="scss">
html,
body {
  ul {
    margin: 0;
    bottom: 0;
  }
}
</style>
