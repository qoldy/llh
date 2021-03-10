<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-tabs type="border-card" @tab-click="clickHandler">
          <el-tab-pane label="Пульс">
            <router-view />
          </el-tab-pane>
          <el-tab-pane label="Сон">
            <router-view />
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Data",
  methods: {
    clickHandler(tab) {
      let routeName = "";

      switch (tab.label) {
        case "Сон":
          routeName = "sleepList";
          break;
        case "Пульс":
          routeName = "pulseList";
          break;
      }

      this.$router.push({ name: routeName });
    }
  }
};
</script>
