(function() {
    'use strict';

    angular
        .module('team7App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('equipment-set', {
            parent: 'entity',
            url: '/equipment-set',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'EquipmentSets'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/equipment-set/equipment-sets.html',
                    controller: 'EquipmentSetController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('equipment-set-detail', {
            parent: 'entity',
            url: '/equipment-set/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'EquipmentSet'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/equipment-set/equipment-set-detail.html',
                    controller: 'EquipmentSetDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'EquipmentSet', function($stateParams, EquipmentSet) {
                    return EquipmentSet.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'equipment-set',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('equipment-set-detail.edit', {
            parent: 'equipment-set-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipment-set/equipment-set-dialog.html',
                    controller: 'EquipmentSetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EquipmentSet', function(EquipmentSet) {
                            return EquipmentSet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('equipment-set.new', {
            parent: 'equipment-set',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipment-set/equipment-set-dialog.html',
                    controller: 'EquipmentSetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('equipment-set', null, { reload: 'equipment-set' });
                }, function() {
                    $state.go('equipment-set');
                });
            }]
        })
        .state('equipment-set.edit', {
            parent: 'equipment-set',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipment-set/equipment-set-dialog.html',
                    controller: 'EquipmentSetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EquipmentSet', function(EquipmentSet) {
                            return EquipmentSet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('equipment-set', null, { reload: 'equipment-set' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('equipment-set.delete', {
            parent: 'equipment-set',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipment-set/equipment-set-delete-dialog.html',
                    controller: 'EquipmentSetDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['EquipmentSet', function(EquipmentSet) {
                            return EquipmentSet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('equipment-set', null, { reload: 'equipment-set' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
