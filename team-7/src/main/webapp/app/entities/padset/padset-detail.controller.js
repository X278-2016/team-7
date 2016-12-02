(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadsetDetailController', PadsetDetailController);

    PadsetDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Padset'];

    function PadsetDetailController($scope, $rootScope, $stateParams, previousState, entity, Padset) {
        var vm = this;

        vm.padset = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('team7App:padsetUpdate', function(event, result) {
            vm.padset = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        
        $scope.test = function() {
        	console.log("TEST")
        }
        
        $scope.makeAPIcall=  function(id){
        	console.log("Made api call with" + id)
        	var root = location.protocol + '//' + location.host;
        	root = root + "/api/padsets"
        	root = root + "/" + id + ""
        	console.log(root)
        	var geoarray = [];
        	$.ajax({
                type: "GET",
                dataType: "json",
                url:root
            }).done(function(data){
               console.log(data)
               $scope.makeGraphs(data)
               
            });
        	
        	
        }
        
        $scope.makeGraphs = function(data) {
        	
        	
        	//Creating Cooling Coil Nominal Total Capacity 
        	var coolingCoilNominalTotalCapacityChart = document.getElementById("coolingCoilNominalTotalCapacityGraph");
        	var myChart = new Chart(coolingCoilNominalTotalCapacityChart, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Cooling Coil Total Capacity"],
        	        datasets: [
        	        {
        	        	label: "Cooling Coil Total Capacity",
        	        	backgroundColor:'rgba(255,153,0,0.4)',
        	        	data: [data['coolingCoilsNominalTotalCapacity']]
        	        },
        	        { 
        	        	label: " Cooling Coil Total Capacity Threshold",
        	        	backgroundColor:'rgba(150,15,150,0.4)',
        	        	data: [data['coolingCoilsNominalTotalCapacityThreshold']]
        	        }  
        	        ]
        	    }
        	});
        	
        	//Creating Cooling Coil Nominal Sensible Heat Ratio Chart
        	var coolingCoilNominalSensibleHeatRatioChart = document.getElementById("coolingCoilNominalSensibleHeatRatioGraph");
        	var myChart2 = new Chart(coolingCoilNominalSensibleHeatRatioChart, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Cooling Coil Nominal Sensible Heat Ratio"],
        	        datasets: [
        	        	{
            	        	label: "Cooling Coil Nominal Sensible Heat Ratio",
            	        	backgroundColor:'rgba(255,153,0,0.4)',
            	        	data: [data['coolingCoilsNominalSensibleHeatRatio']]
            	        },
            	        { 
            	        	label: "Cooling Coil Nominal Sensible Heat Ratio Threshold",
            	        	backgroundColor:'rgba(150,15,150,0.4)',
            	        	data: [data['coolingCoilsNominalSensibleHeatRatioThreshold']]
            	        }  
            	        
            	        ]
        	    }
        	});
        	
        	var fanGraph1 = document.getElementById("fanGraph1");
        	var myChart3 = new Chart(fanGraph1, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Max Air Flow Rate",  "Motor Heat In Air"
        	        	,"Total Efficiency"],
        	        datasets: [
        	        	{
        	        	  label: ["Value"],
        	        	  data: [data['fanMaxAirFlowRate'], data['fanMotorHeatInAirFraction'], data['fanTotalEfficiency']],
        	        	  backgroundColor: 'rgba(255,153,0,0.4)'  
        	        	},
        	        	{
          	        	  label: ["Threshold"],
          	        	  data: [data['fanMaxAirFlowRateThreshold'], data['fanMotorHeatInAirFractionThreshold'], data['fanTotalEfficiencyThreshold']],
          	        	  backgroundColor:'rgba(150,15,150,0.4)',
          	        	}   	
        	        ]
        	    }
        	});
        	
        	var fanGraph2 = document.getElementById("fanGraph2");
        	var myChart4 = new Chart(fanGraph2, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Electric Power", "Power Per Air Max Flow Rate"],
        	        datasets: [
        	        	{
          	        	  label: ["Value"],
          	        	  data: [data['fanRatedElectricPower'], data['fanRatedPowerPerMaxAirFlowRate']],
          	        	  backgroundColor: 'rgba(255,153,0,0.4)'  
          	        	},
          	        	{
            	        	  label: ["Threshold"],
            	        	  data: [data['fanRatedElectricPowerThreshold'], data['fanRatedPowerPerMaxAirFlowRateThreshold']],
            	        	  backgroundColor:'rgba(150,15,150,0.4)',
            	        }  
        	        ]
        	    }
        	});
        	
        	var heatingCoilGraph1 = document.getElementById("heatingCoilGraph1");
        	var myChart5  = new Chart(heatingCoilGraph1, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Nominal Total Capacity Reheat Coil Zone 1",
        	        		 "Nominal Total Capacity Reheat Coil Zone 2",
        	        	     "Nominal Total Capacity Reheat Coil Zone 3",],
        	        datasets: 	
        	        [
        	        		{
            	        	  label: ["Value"],
            	        	  data: [data['heatingCoilsNominalTotalCapacityReheatCoilZone1'],data['heatingCoilsNominalTotalCapacityReheatCoilZone2'],
            	        		  data['heatingCoilsNominalTotalCapacityReheatCoilZone3']],
            	        	  backgroundColor: 'rgba(255,153,0,0.4)'  
            	        	},
            	            {
              	        	  label: ["Threshold"],
              	        	  data:  [data['heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold'],data['heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold'],
            	        		  data['heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold']],
              	        	  backgroundColor: 'rgba(255,153,150,0.4)'  
              	        	}
       	
        	        ]
        	    }
        	});
        	
        	var pumpsGraph1 = document.getElementById("pumpsGraph1")
        	var pumpData = {
			    labels: ["Circ Pump - Power Per Water Flow Rate", "Cond Circ Pump - Power Per Water Flow Rate", "HW Circ Pump - Power Per Water Flow Rate",
			    	"Circ Pump - Head[pressure] ", "Cond Circ Pump -Head[pressure] ", "HW Circ Pump - Head[pressure] ",
			    	"Circ Pump - Electric Power", "Cond Circ Pump - Electric Power", "HW Circ Pump - Electric Power",],
			    datasets: [
			        {
			            label: "Value",
			            backgroundColor:'rgba(255, 99, 132, 0.2)',
			            data: [data['pumpsPowerPerWaterFlowRateCircPump'],data['pumpsPowerPerWaterFlowRateCondCircPump'],data['pumpsPowerPerWaterFlowRateHwCircPump'],
			            	   data['pumpsHeadCircPump'],data['pumpsHeadCondCircPump'],data['pumpsHeadHwCircPump'],
			            	   data['pumpsElectricPowerCircPump'],data['pumpsElectricPowerCondCircPump'],data['pumpsElectricPowerHwCircPump']]
			            	   
			        },
			        {
			            label: "Threshold",
			            backgroundColor:'rgba(54, 162, 235, 0.2)',
			            data: [data['pumpsPowerPerWaterFlowRateCircPumpThreshold'],data['pumpsPowerPerWaterFlowRateCondCircPumpThreshold'],data['pumpsPowerPerWaterFlowRateHwCircPumpThreshold'],
			            	   data['pumpsHeadCircPumpThreshold'],data['pumpsHeadCondCircPumpThreshold'],data['pumpsHeadHwCircPumpThreshold'],
			            	   data['pumpsElectricPowerCircPumpThreshold'],data['pumpsElectricPowerCondCircPumpThreshold'],data['pumpsElectricPowerHwCircPumpThreshold']]
			        },
			     ]
        	};
        	var myChart6  = new Chart(pumpsGraph1, {
        		
        		
        		type: 'bar',
        	    data: pumpData,
        	    options: {
        	    	title: {
        	            display: true,
        	            text: 'Pump Chart 1- Pressure/Power/Electric Power'
        	        }
        	    }
        	});
        	
        	
        	var pumpsGraph2 = document.getElementById("pumpsGraph2")
        	var pumpData = {
			    labels: ["Circ Pump - Motor Efficiency", "Cond Circ Pump - Motor Efficiency", "HW Circ Pump - Motor Efficiency",
			    	    "Circ Pump - Water Flow ", "Cond Circ Pump - Water Flow", "HW Circ Pump - Water Flow"],
			    datasets: [
			    	{
			            label: "Value",
			            backgroundColor:'rgba(255, 99, 132, 0.2)',
			            data: [data['pumpsMotorEfficiencyCircPump'],data['pumpsMotorEfficiencyCondCircPump'],data['pumpsMotorEfficiencyHwCircPump'],
			            	   data['pumpsWaterFlowCircPump'],data['pumpsWaterFlowCondCircPump'],data['pumpsWaterFlowHwCircPump']]
			            	   
			        },
			        {
			            label: "Threshold",
			            backgroundColor:'rgba(54, 162, 235, 0.2)',
			            data: [data['pumpsMotorEfficiencyCircPumpThreshold'],data['pumpsMotorEfficiencyCondCircPumpThreshold'],data['pumpsMotorEfficiencyHwCircPumpThreshold'],
			            	   data['pumpsWaterFlowCircPumpThreshold'],data['pumpsWaterFlowCondCircPumpThreshold'],data['pumpsWaterFlowHwCircPumpThreshold']]
			        },
			     ]
        	};
        	var myChart6  = new Chart(pumpsGraph2, {
        		type: 'bar',
        	    data: pumpData,
        	    options: {
        	    	title: {
        	            display: true,
        	            text: 'Pump Chart 2 - Water Flow/Motor Efficiency'
        	        }
        	    }
        	});   	
        }
    }
    

})();