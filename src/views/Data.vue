<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-tabs type="border-card" v-model="activeTabName">
          <el-tab-pane label="Пульс" name="pulse">
            <router-view />
          </el-tab-pane>
          <el-tab-pane label="Сон" name="sleep">
            <router-view />
          </el-tab-pane>
          <el-tab-pane label="Температура" name="temperature">
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
  data() {
    return {
      activeTabName: ""
    };
  },
  watch: {
    activeTabName(newValue) {
      let routeName = "";

      switch (newValue) {
        case "sleep":
          routeName = "sleepList";
          break;
        case "pulse":
          routeName = "pulseList";
          break;
        case "temperature":
          routeName = "temperatureList";
          break;
      }

      if (this.$route.name !== routeName)
        this.$router.push({ name: routeName });
    }
  },
  methods: {
    getActiveTabName() {
      const routeName = this.$route.name;
      let tabName = "";

      switch (routeName) {
        case "sleepList":
          tabName = "sleep";
          break;
        case "pulseList":
          tabName = "pulse";
          break;
        case "temperatureList":
          tabName = "temperature";
          break;
      }

      return tabName;
    }
  },
  created() {
    this.activeTabName = this.getActiveTabName();
  }
};
</script>
