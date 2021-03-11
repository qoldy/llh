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
          label: "Длительность сна",
          backgroundColor: "rgb(79,66,202)",
          borderColor: "rgba(79,66,202, 0.5)",
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
            return new Date(item.date).toLocaleDateString();
          });

          const data = localData.map(item => {
            return Number(`${item.hours}.${item.minutes}`);
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
