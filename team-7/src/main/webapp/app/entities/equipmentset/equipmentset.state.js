(function() {
    'use strict';

    angular
        .module('team7App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('equipmentset', {
            parent: 'entity',
            url: '/equipmentset',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Equipmentsets'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/equipmentset/equipmentsets.html',
                    controller: 'EquipmentsetController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('equipmentset-detail', {
            parent: 'entity',
            url: '/equipmentset/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Equipmentset'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/equipmentset/equipmentset-detail.html',
                    controller: 'EquipmentsetDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Equipmentset', function($stateParams, Equipmentset) {
                    return Equipmentset.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'equipmentset',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('equipmentset-detail.edit', {
            parent: 'equipmentset-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipmentset/equipmentset-dialog.html',
                    controller: 'EquipmentsetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Equipmentset', function(Equipmentset) {
                            return Equipmentset.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('equipmentset.new', {
            parent: 'equipmentset',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipmentset/equipmentset-dialog.html',
                    controller: 'EquipmentsetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                mEquipmentSetName: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('equipmentset', null, { reload: 'equipmentset' });
                }, function() {
                    $state.go('equipmentset');
                });
            }]
        })
        .state('equipmentset.edit', {
            parent: 'equipmentset',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipmentset/equipmentset-dialog.html',
                    controller: 'EquipmentsetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Equipmentset', function(Equipmentset) {
                            return Equipmentset.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('equipmentset', null, { reload: 'equipmentset' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('equipmentset.delete', {
            parent: 'equipmentset',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipmentset/equipmentset-delete-dialog.html',
                    controller: 'EquipmentsetDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Equipmentset', function(Equipmentset) {
                            return Equipmentset.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('equipmentset', null, { reload: 'equipmentset' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
