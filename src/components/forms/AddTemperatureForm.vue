<template>
  <el-card class="box-card add-form">
    <div slot="header" class="clearfix add-form__header">
      <span>Добавить замер температуры</span>
    </div>
    <form action="#" @submit.prevent="submitHandler">
      <el-date-picker
        v-model="measurementTime"
        type="datetime"
        placeholder="Дата и время"
        format="dd.MM.yyyy HH:mm"
        class="add-form__input"
      >
      </el-date-picker>
      <el-input-number
        v-model="value"
        :min="25"
        :max="46"
        :precision="1"
        :step="0.1"
        class="add-form__input add-form__input--value"
        size="medium"
      ></el-input-number>
      <div class="add-form__footer">
        <el-button :loading="isSubmitting" native-type="submit" type="success"
          >Добавить
        </el-button>
        <el-button @click="closeHandler">Отмена</el-button>
      </div>
    </form>
  </el-card>
</template>

<script>
import {
  actionTypes as temperatureActionTypes,
  getterTypes as temperatureGetterTypes
} from "@/store/temperature";
import { getterTypes as authGetterTypes } from "@/store/auth";
import { mapGetters } from "vuex";
import dateHelper from "@/helpers/date";

export default {
  name: "AddTemperatureForm",
  data() {
    return {
      measurementTime: new Date(),
      value: 36.6
    };
  },
  computed: {
    ...mapGetters({
      isSubmitting: temperatureGetterTypes.isSubmitting,
      user: authGetterTypes.user
    }),
    temperatureData() {
      return {
        measurement_time: dateHelper.convertToDatetime(this.measurementTime),
        value: this.value,
        user_id: this.user.id
      };
    }
  },
  methods: {
    closeHandler() {
      this.$emit("close");
    },
    submitHandler() {
      this.$store
        .dispatch(temperatureActionTypes.addTemperature, {
          temperatureData: this.temperatureData
        })
        .then(() => {
          this.$emit("finished");
        });
    }
  }
};
</script>

<style scoped lang="scss">
.add-form__header {
  text-align: center;
}

.add-form__input {
  margin-bottom: 10px;
  width: 100%;

  &--value {
    width: unset;
  }
}

.add-form__footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
