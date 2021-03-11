<canvas ref="canvas"></canvas>

<script>
import { Line } from "vue-chartjs";
import { mapGetters } from "vuex";
import { getterTypes as pulseListGetterTypes } from "@/store/pulseList";
import { getterTypes as sleepListGetterTypes } from "@/store/sleepList";
import { getterTypes as temperatureListGetterTypes } from "@/store/temperatureList";

export default {
  extends: Line,
  data: () => ({
    chartdata: {
      labels: [],
      datasets: [
        {
          label: "Температура",
          backgroundColor: "rgb(248,121,121)",
          borderColor: "rgba(248,121,121, 0.5)",
          fill: false,
          data: []
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      animation: {
        duration: 0
      }
    }
  }),
  watch: {
    cleanCurrentData: {
      immediate: true,
      handler(newValue) {
        if (newValue) {
          const localData = [...newValue];

          const labels = localData.map(item => {
            return new Date(item.measurement_time).toLocaleDateString();
          });

          const data = localData.map(item => {
            return +item.value;
          });

          this.chartdata.labels = labels;
          this.chartdata.datasets[0].data = data;
          if (this.$data._chart) this.$data._chart.update();
        }
      }
    }
  },
  computed: {
    ...mapGetters({
      pulseList: pulseListGetterTypes.pulseList,
      sleepList: sleepListGetterTypes.sleepList,
      temperatureList: temperatureListGetterTypes.temperatureList
    }),
    cleanCurrentData() {
      switch (this.$route.name) {
        case "pulseList":
          return this.pulseList;
        case "sleepList":
          return this.sleepList;
        case "temperatureList":
          return this.temperatureList;
        default:
          return null;
      }
    }
  },
  mounted() {
    this.renderChart(this.chartdata, this.options);
  }
};
</script>

<style scoped></style>
