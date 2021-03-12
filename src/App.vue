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

export default {
  name: "App",
  components: {
    AppHeader
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

.el-main {
  height: calc(100vh - 60px);
}
</style>
