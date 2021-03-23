package com.linelife.demo.controller;

import com.linelife.demo.model.constant.Pressure;
import com.linelife.demo.model.constant.Pulse;
import com.linelife.demo.model.constant.SleepTime;
import com.linelife.demo.model.constant.Temperature;
import com.linelife.demo.service.constant.PressureService;
import com.linelife.demo.service.constant.PulseService;
import com.linelife.demo.service.constant.SleepTimeService;
import com.linelife.demo.service.constant.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/constant/")
public class ConstantController {

    private final PulseService pulseService;
    private final PressureService pressureService;
    private final SleepTimeService sleepTimeService;
    private final TemperatureService temperatureService;

    @Autowired
    public ConstantController(PulseService pulseService, PressureService pressureService, SleepTimeService sleepTimeService, TemperatureService temperatureService) {
        this.pulseService = pulseService;
        this.pressureService = pressureService;
        this.sleepTimeService = sleepTimeService;
        this.temperatureService = temperatureService;
    }

    @GetMapping(value = "pressure/{id}")
    public ResponseEntity<List<Pressure>> getAllPressure(@PathVariable Long id){

        List<Pressure> pressures = pressureService.getAll(id);
        if(pressures.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pressures, HttpStatus.OK);
    }
    @GetMapping(value = "pulse/{id}")
    public ResponseEntity getAllPulse(@PathVariable Long id){

        List<Pulse> pulses = pulseService.getAll(id);
        if(pulses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pulses, HttpStatus.OK);
    }
    @GetMapping(value = "sleep/{id}")
    public ResponseEntity<List<SleepTime>> getAllSleepTime(@PathVariable Long id){

        List<SleepTime> sleeps = sleepTimeService.getAll(id);
        if(sleeps.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sleeps, HttpStatus.OK);
    }
    @GetMapping(value = "temperature/{id}")
    public ResponseEntity<List<Temperature>> getAll(@PathVariable Long id){

        List<Temperature> temperatures = temperatureService.getAll(id);
        if(temperatures.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(temperatures, HttpStatus.OK);
    }

    @PostMapping(value = "pressure")
    public ResponseEntity<HttpStatus> setPressure(@RequestBody Pressure pressure){

        return new ResponseEntity<>(pressureService.save(pressure));
    }
    @PostMapping(value = "pulse")
    public ResponseEntity<HttpStatus> setPulse(@RequestBody Pulse pulse){

        return new ResponseEntity<>(pulseService.save(pulse));
    }
    @PostMapping(value = "sleep")
    public ResponseEntity<HttpStatus> setPressure(@RequestBody SleepTime sleepTime){

        return new ResponseEntity<>(sleepTimeService.save(sleepTime));
    }
    @PostMapping(value = "temperature")
    public ResponseEntity<HttpStatus> setPressure(@RequestBody Temperature temperature){

        return new ResponseEntity<>(temperatureService.save(temperature));
    }

    @PutMapping(value = "pressure/{id}")
    public ResponseEntity updatePressure(@RequestBody Pressure pressure, @PathVariable Long id){
        Pressure up = pressureService.findById(id);
        if(up==null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        up.setDiastolic(pressure.getDiastolic());
        up.setSystolic(pressure.getSystolic());
        return pressureService.update(up);
    }
    @PutMapping(value = "pulse/{id}")
    public ResponseEntity updatePulse(@RequestBody Pulse pulse, @PathVariable Long id){
        Pulse up = pulseService.findById(id);
        if(up==null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        up.setPulse(pulse.getPulse());
        return pulseService.update(up);
    }
    @PutMapping(value = "sleep/{id}")
    public ResponseEntity updateSleepTime(@RequestBody SleepTime sleepTime, @PathVariable Long id){
        SleepTime up = sleepTimeService.findById(id);
        if(up==null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        up.setSleepTime(sleepTime.getSleepTime());
        return sleepTimeService.update(up);
    }
    @PutMapping(value = "temperature/{id}")
    public ResponseEntity updateTemperature(@RequestBody Temperature temperature, @PathVariable Long id){
        Temperature up = temperatureService.findById(id);
        if(up==null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        up.setTemperature(temperature.getTemperature());
        return temperatureService.update(up);
    }
}
