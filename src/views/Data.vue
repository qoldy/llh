<template>
  <div>
    <el-row>
      <div class="chart">
        <router-view name="chart" />
      </div>
      <el-col :span="24">
        <el-tabs type="border-card" v-model="activeTabName">
          <el-tab-pane label="Пульс" name="pulse">
            <router-view name="pulseList" />
          </el-tab-pane>
          <el-tab-pane label="Сон" name="sleep">
            <router-view name="sleepList" />
          </el-tab-pane>
          <el-tab-pane label="Температура" name="temperature">
            <router-view name="temperatureList" />
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
      dictionary: {
        sleep: "sleepList",
        pulse: "pulseList",
        temperature: "temperatureList"
      }
    };
  },
  computed: {
    activeTabName: {
      get() {
        return this.convertRouteNameToTabName(this.$route.name);
      },
      set(tabName) {
        const routeName = this.convertTabNameToRouteName(tabName);
        if (this.$route.name !== routeName)
          this.$router.push({ name: routeName });
      }
    }
  },
  methods: {
    convertTabNameToRouteName(tabName) {
      return this.dictionary[tabName];
    },
    convertRouteNameToTabName(routeName) {
      return Object.keys(this.dictionary).find(
        tabName => this.dictionary[tabName] === routeName
      );
    }
  }
};
</script>

<style>
.chart {
  margin-bottom: 25px;
}
</style>
