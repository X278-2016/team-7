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
        	var geoarray = [];
        	$.ajax({
                type: "GET",
                dataType: "json",
                url:"http://localhost:8080/api/padsets" + "/" + id + ""
            }).done(function(data){
               console.log(data)
               $scope.makeCoolingCoilGraph(data)
               
            });
        	
        	
        }
        
        $scope.makeCoolingCoilGraph = function(data) {
        	
        	
        	//Creating Cooling Coil Nominal Total Capacity 
        	var coolingCoilNominalTotalCapacityChart = document.getElementById("coolingCoilNominalTotalCapacityGraph");
        	var myChart = new Chart(coolingCoilNominalTotalCapacityChart, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Cooling Coil Total Capacity"],
        	        datasets: [{
        	            label: 'Cooling Coil Total Capacity',
        	            data: [ data['coolingCoilsNominalTotalCapacity']],
        	            backgroundColor: [
        	                'rgba(255, 99, 132, 0.2)',
        	               
        	            ],
        	            borderWidth: 1
        	        }]
        	    },
        	    options: {
        	        scales: {
        	            yAxes: [{
        	                ticks: {
        	                    beginAtZero:true
        	                }
        	            }]
        	        }
        	    }
        	});
        	
        	//Creating Cooling Coil Nominal Sensible Heat Ratio Chart
        	var coolingCoilNominalSensibleHeatRatioChart = document.getElementById("coolingCoilNominalSensibleHeatRatioGraph");
        	var myChart2 = new Chart(coolingCoilNominalSensibleHeatRatioChart, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Cooling Coil Nominal Sensible Heat Ratio"],
        	        datasets: [{
        	            label: 'Cooling Coil Nominal Sensible Heat Ratio',
        	            data: [data['coolingCoilsNominalSensibleHeatRatio']],
        	            backgroundColor: [
        	            	'rgba(255, 206, 86, 0.2)',
        	               
        	            ],
        	            borderWidth: 1
        	        }]
        	    },
        	    options: {
        	        scales: {
        	            yAxes: [{
        	                ticks: {
        	                    beginAtZero:true
        	                }
        	            }]
        	        }
        	    }
        	});
        	
        	var fanGraph1 = document.getElementById("fanGraph1");
        	var myChart3 = new Chart(fanGraph1, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Max Air Flow Rate","Motor Heat In Air", "Total Efficiency"],
        	        datasets: [{
        	            label: 'Fan Metrics',
        	            data: [data['fanMaxAirFlowRate'], data['fanMotorHeatInAirFraction'], data['fanTotalEfficiency']],
        	            backgroundColor: [
        	            	'rgba(255, 99, 132, 0.2)',
        	                'rgba(54, 162, 235, 0.2)',
        	                'rgba(255, 206, 86, 0.2)',
        	               
        	            ],
        	            borderWidth: 1
        	        }]
        	    },
        	    options: {
        	        scales: {
        	            yAxes: [{
        	                ticks: {
        	                    beginAtZero:true
        	                }
        	            }]
        	        }
        	    }
        	});
        	
        	var fanGraph2 = document.getElementById("fanGraph2");
        	var myChart4 = new Chart(fanGraph2, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Electric Power","Power Per Air Max Flow Rate"],
        	        datasets: [{
        	            label: 'Fan Metrics',
        	            data: [data['fanRatedElectricPower'], data['fanRatedPowerPerMaxAirFlowRate']],
        	            backgroundColor: [
        	            	'rgba(255, 99, 132, 0.2)',
        	                'rgba(54, 162, 235, 0.2)',
        	                'rgba(255, 206, 86, 0.2)',
        	               
        	            ],
        	            borderWidth: 1
        	        }]
        	    },
        	    options: {
        	        scales: {
        	            yAxes: [{
        	                ticks: {
        	                    beginAtZero:true
        	                }
        	            }]
        	        }
        	    }
        	});
        	
        	var heatingCoilGraph1 = document.getElementById("heatingCoilGraph1");
        	var myChart5  = new Chart(heatingCoilGraph1, {
        	    type: 'bar',
        	    data: {
        	        labels: ["Reheat Coil Zone 1","Reheat Coil Zone 2","Reheat Coil Zone 3" ],
        	        datasets: [{
        	            label: 'Heating Coil Nominal Total Capacity',
        	            data: [data['heatingCoilsNominalTotalCapacityReheatCoilZone1'], data['heatingCoilsNominalTotalCapacityReheatCoilZone2'],
        	            	   data['heatingCoilsNominalTotalCapacityReheatCoilZone3']],
        	            backgroundColor: [
        	            	'rgba(255, 99, 132, 0.2)',
        	                'rgba(54, 162, 235, 0.2)',
        	                'rgba(255, 206, 86, 0.2)',
        	               
        	            ],
        	            borderWidth: 1
        	        }]
        	    },
        	    options: {
        	        scales: {
        	            yAxes: [{
        	                ticks: {
        	                    beginAtZero:true
        	                }
        	            }]
        	        }
        	    }
        	});
        	
        	var pumpsGraph1 = document.getElementById("pumpsGraph1")
        	var pumpData = {
			    labels: ["Circ Pump", "Cond Circ Pump", "HW Circ Pump"],
			    datasets: [
			        {
			            label: "Power Per Water Flow Rate",
			            backgroundColor:'rgba(255, 99, 132, 0.2)',
			            data: [data['pumpsPowerPerWaterFlowRateCircPump'],data['pumpsPowerPerWaterFlowRateCondCircPump'],data['pumpsPowerPerWaterFlowRateHwCircPump']]
			        },
			        {
			            label: "Head [Pressure] ",
			            backgroundColor:'rgba(54, 162, 235, 0.2)',
			            data: [data['pumpsHeadCircPump'],data['pumpsHeadCondCircPump'],data['pumpsHeadHwCircPump']]
			        },
			        
			        {
			            label: "Electric Power ",
			            backgroundColor:'rgba(54, 162, 235, 0.2)',
			            data: [data['pumpsElectricPowerCircPump'],data['pumpsElectricPowerCondCircPump'],data['pumpsElectricPowerHwCircPump']]
			        }
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
			    labels: ["Circ Pump", "Cond Circ Pump", "HW Circ Pump"],
			    datasets: [
			        {
			            label: "Water Flow",
			            backgroundColor:'rgba(255, 99, 132, 0.2)',
			            data: [data['pumpsWaterFlowCircPump'],data['pumpsWaterFlowCondCircPump'],data['pumpsWaterFlowHwCircPump']]
			        },
			        {
			            label: "Motor Efficiency",
			            backgroundColor:'rgba(54, 162, 235, 0.2)',
			            data: [data['pumpsMotorEfficiencyCircPump'],data['pumpsMotorEfficiencyCondCircPump'],data['pumpsMotorEfficiencyHwCircPump']]
			        }
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

