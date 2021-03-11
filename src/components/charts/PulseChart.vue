<canvas v-else ref="canvas" class="chart"></canvas>

<script>
import { Line } from "vue-chartjs";
import { mapGetters } from "vuex";
import { getterTypes as pulseListGetterTypes } from "@/store/pulseList";

export default {
  name: "PulseChart",
  extends: Line,
  data: () => ({
    chartdata: {
      labels: [],
      datasets: [
        {
          label: "Пульс",
          backgroundColor: "rgb(22,199,154)",
          borderColor: "rgba(22,199,154, 0.5)",
          fill: false,
          data: [0, 0]
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      animation: {
        duration: 0
      },
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true
            }
          }
        ]
      },
      tooltips: {
        mode: "index",
        intersect: false
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
      isLoading: pulseListGetterTypes.isLoading
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
