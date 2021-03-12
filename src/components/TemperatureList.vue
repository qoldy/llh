<template>
  <div>
    <div class="list__header">
      <el-button @click="toggleAddForm" type="success">Добавить</el-button>
    </div>
    <!-- Начало таблицы -->
    <el-table
      :data="localTemperatureList"
      stripe
      style="width: 100%; min-height: 300px"
      v-loading="isLoading"
    >
      <!-- Время измерения -->
      <el-table-column prop="measurement_time" label="Время измерения">
        <template slot-scope="scope">
          <span v-if="!scope.row.isEditing">{{
            scope.row.localeMeasurementTime
          }}</span>
          <el-date-picker
            v-else
            v-model="scope.row.newValues.measurement_time"
            :disabled="isTemperatureSubmitting"
            type="datetime"
            format="dd.MM.yyyy hh:mm"
            placeholder="Новое время"
          >
          </el-date-picker>
        </template>
      </el-table-column>
      <!-- Значение температуры -->
      <el-table-column prop="value" label="Температура">
        <template slot-scope="scope">
          <span v-if="!scope.row.isEditing">{{ scope.row.value }}</span>
          <el-input-number
            v-else
            :disabled="isTemperatureSubmitting"
            :min="25"
            :max="46"
            :precision="1"
            :step="0.1"
            placeholder="Новое значение"
            v-model="scope.row.newValues.value"
            class="editing-input"
          ></el-input-number>
        </template>
      </el-table-column>
      <!-- Кнопки управления -->
      <el-table-column label="Операции" width="130">
        <template slot-scope="scope">
          <template v-if="!scope.row.isEditing">
            <!-- Кнопка редактирования -->
            <el-button
              @click="editTemperatureHandler(scope.$index)"
              :disabled="isTemperatureSubmitting"
              icon="el-icon-edit"
              circle
              plain
            ></el-button>
            <!-- Кнопка удаления -->
            <el-button
              @click="deleteTemperatureHandler(scope.$index)"
              :disabled="isTemperatureSubmitting"
              type="danger"
              icon="el-icon-delete"
              circle
              plain
            ></el-button>
          </template>
          <template v-else>
            <!-- Кнопка отмены редактирования -->
            <el-button
              @click="cancelEditTemperatureHandler(scope.$index)"
              :disabled="isTemperatureSubmitting"
              icon="el-icon-close"
              circle
              plain
            ></el-button>
            <!-- Кнопка сохраения изменений -->
            <el-button
              @click="submitTemperatureHandler(scope.$index)"
              :disabled="isTemperatureSubmitting"
              type="success"
              icon="el-icon-check"
              circle
              plain
            ></el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>
    <!-- Слой формы добавления данных -->
    <div
      v-if="isAddFormOpen"
      @click.self="toggleAddForm"
      class="add-form__layer"
    >
      <AppAddTemperatureForm
        @close="toggleAddForm"
        @finished="
          getTemperatureList();
          toggleAddForm();
        "
      />
    </div>
  </div>
</template>

<script>
import AppAddTemperatureForm from "@/components/forms/AddTemperatureForm";
import {
  actionTypes as temperatureListActionTypes,
  getterTypes as temperatureListGetterTypes
} from "@/store/temperatureList";
import {
  getterTypes as temperatureGetterTypes,
  actionTypes as temperatureActionTypes
} from "@/store/temperature";
import { mapGetters } from "vuex";
import dateHelper from "@/helpers/date";

export default {
  name: "SleepList",
  components: {
    AppAddTemperatureForm
  },
  data() {
    return {
      isAddFormOpen: false,
      localTemperatureList: null
    };
  },
  watch: {
    temperatureList: {
      immediate: true,
      handler(newTemperatureList) {
        this.localTemperatureList = this.prepareTemperatureList(
          newTemperatureList
        );
      }
    }
  },
  computed: {
    ...mapGetters({
      temperatureList: temperatureListGetterTypes.temperatureList,
      isLoading: temperatureListGetterTypes.isLoading,
      isEmpty: temperatureListGetterTypes.isEmpty,
      isTemperatureSubmitting: temperatureGetterTypes.isSubmitting
    })
  },
  methods: {
    toggleAddForm() {
      this.isAddFormOpen = !this.isAddFormOpen;
    },
    getTemperatureList() {
      return this.$store.dispatch(
        temperatureListActionTypes.getTemperatureList
      );
    },
    prepareTemperatureList(temperatureList) {
      return temperatureList !== null
        ? temperatureList
            .map(temperature => {
              const item = { ...temperature };
              const date = new Date(item.measurement_time).toLocaleDateString();
              const time = new Date(item.measurement_time)
                .toLocaleTimeString()
                .slice(0, -3);
              item.localeMeasurementTime = `${date} ${time}`;
              item.isEditing = false;
              item.newValues = {
                measurement_time: null,
                value: null
              };
              return item;
            })
            .reverse()
        : null;
    },
    editTemperatureHandler(temperatureIndex) {
      const temperatureItem = this.localTemperatureList[temperatureIndex];
      temperatureItem.isEditing = true;
      temperatureItem.newValues.measurement_time =
        temperatureItem.measurement_time;
      temperatureItem.newValues.value = temperatureItem.value;
    },
    cancelEditTemperatureHandler(temperatureIndex) {
      const temperatureItem = this.localTemperatureList[temperatureIndex];
      temperatureItem.isEditing = false;
      temperatureItem.newValues.measurement_time = null;
      temperatureItem.newValues.value = null;
    },
    deleteTemperatureHandler(temperatureIndex) {
      const temperatureId = this.localTemperatureList[temperatureIndex].id;
      this.$store
        .dispatch(temperatureActionTypes.deleteTemperature, {
          temperatureId
        })
        .then(() => {
          this.$store.dispatch(temperatureListActionTypes.getTemperatureList);
        });
    },
    submitTemperatureHandler(temperatureIndex) {
      const temperatureItem = this.localTemperatureList[temperatureIndex];
      const temperatureId = temperatureItem.id;
      const temperatureData = temperatureItem.newValues;
      temperatureData.measurement_time = dateHelper.convertToDatetime(
        new Date(temperatureData.measurement_time)
      );

      this.$store
        .dispatch(temperatureActionTypes.editTemperature, {
          temperatureId,
          temperatureData
        })
        .then(() => {
          this.$store.dispatch(temperatureListActionTypes.getTemperatureList);
          this.cancelEditTemperatureHandler(temperatureIndex);
        });
    }
  },
  created() {
    if (!this.temperatureList) this.getTemperatureList();
  }
};
</script>

<style scoped>
.list__header {
  text-align: right;
}

.add-form__layer {
  position: absolute;
  z-index: 1;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.2);
  display: flex;
  padding-top: 50px;
  align-items: flex-start;
  justify-content: center;
}
</style>
