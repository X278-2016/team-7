(function() {
    'use strict';

    angular
        .module('team7App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('padset', {
            parent: 'entity',
            url: '/padset',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Padsets'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/padset/padsets.html',
                    controller: 'PadsetController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('padset-detail', {
            parent: 'entity',
            url: '/padset/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Padset'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/padset/padset-detail.html',
                    controller: 'PadsetDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Padset', function($stateParams, Padset) {
                    return Padset.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'padset',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('padset-detail.edit', {
            parent: 'padset-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/padset/padset-dialog.html',
                    controller: 'PadsetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Padset', function(Padset) {
                            return Padset.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('padset.new', {
            parent: 'padset',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/padset/padset-dialog.html',
                    controller: 'PadsetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                mPadSetName: null,
                                mLatitude: null,
                                mLongitude: null,
                                coolingCoilsNominalSensibleHeatRatio: null,
                                coolingCoilsNominalSensibleHeatRatioThreshold: null,
                                coolingCoilsNominalTotalCapacity: null,
                                coolingCoilsNominalTotalCapacityThreshold: null,
                                heatingCoilsNominalTotalCapacityReheatCoilZone1: null,
                                heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold: null,
                                heatingCoilsNominalTotalCapacityReheatCoilZone2: null,
                                heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold: null,
                                heatingCoilsNominalTotalCapacityReheatCoilZone3: null,
                                heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold: null,
                                fanMaxAirFlowRate: null,
                                fanMaxAirFlowRateThreshold: null,
                                fanRatedElectricPower: null,
                                fanRatedElectricPowerThreshold: null,
                                fanRatedPowerPerMaxAirFlowRate: null,
                                fanRatedPowerPerMaxAirFlowRateThreshold: null,
                                fanMotorHeatInAirFraction: null,
                                fanMotorHeatInAirFractionThreshold: null,
                                fanTotalEfficiency: null,
                                fanTotalEfficiencyThreshold: null,
                                pumpsPowerPerWaterFlowRateCircPump: null,
                                pumpsPowerPerWaterFlowRateCircPumpThreshold: null,
                                pumpsPowerPerWaterFlowRateHwCircPump: null,
                                pumpsPowerPerWaterFlowRateHwCircPumpThreshold: null,
                                pumpsPowerPerWaterFlowRateCondCircPump: null,
                                pumpsPowerPerWaterFlowRateCondCircPumpThreshold: null,
                                pumpsHeadCircPump: null,
                                pumpsHeadCircPumpThreshold: null,
                                pumpsHeadHwCircPump: null,
                                pumpsHeadHwCircPumpThreshold: null,
                                pumpsHeadCondCircPump: null,
                                pumpsHeadCondCircPumpThreshold: null,
                                pumpsElectricPowerCircPump: null,
                                pumpsElectricPowerCircPumpThreshold: null,
                                pumpsElectricPowerHwCircPump: null,
                                pumpsElectricPowerHwCircPumpThreshold: null,
                                pumpsElectricPowerCondCircPump: null,
                                pumpsElectricPowerCondCircPumpThreshold: null,
                                pumpsMotorEfficiencyCircPump: null,
                                pumpsMotorEfficiencyCircPumpThreshold: null,
                                pumpsMotorEfficiencyHwCircPump: null,
                                pumpsMotorEfficiencyHwCircPumpThreshold: null,
                                pumpsMotorEfficiencyCondCircPump: null,
                                pumpsMotorEfficiencyCondCircPumpThreshold: null,
                                pumpsWaterFlowCircPump: null,
                                pumpsWaterFlowCircPumpThreshold: null,
                                pumpsWaterFlowHwCircPump: null,
                                pumpsWaterFlowHwCircPumpThreshold: null,
                                pumpsWaterFlowCondCircPump: null,
                                pumpsWaterFlowCondCircPumpThreshold: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('padset', null, { reload: 'padset' });
                }, function() {
                    $state.go('padset');
                });
            }]
        })
        .state('padset.edit', {
            parent: 'padset',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/padset/padset-dialog.html',
                    controller: 'PadsetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Padset', function(Padset) {
                            return Padset.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('padset', null, { reload: 'padset' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('padset.delete', {
            parent: 'padset',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/padset/padset-delete-dialog.html',
                    controller: 'PadsetDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Padset', function(Padset) {
                            return Padset.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('padset', null, { reload: 'padset' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
