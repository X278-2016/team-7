(function() {
    'use strict';

    angular
        .module('team7App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('threshold', {
            parent: 'entity',
            url: '/threshold',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Thresholds'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/threshold/thresholds.html',
                    controller: 'ThresholdController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('threshold-detail', {
            parent: 'entity',
            url: '/threshold/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Threshold'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/threshold/threshold-detail.html',
                    controller: 'ThresholdDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Threshold', function($stateParams, Threshold) {
                    return Threshold.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'threshold',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('threshold-detail.edit', {
            parent: 'threshold-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/threshold/threshold-dialog.html',
                    controller: 'ThresholdDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Threshold', function(Threshold) {
                            return Threshold.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('threshold.new', {
            parent: 'threshold',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/threshold/threshold-dialog.html',
                    controller: 'ThresholdDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                value: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('threshold', null, { reload: 'threshold' });
                }, function() {
                    $state.go('threshold');
                });
            }]
        })
        .state('threshold.edit', {
            parent: 'threshold',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/threshold/threshold-dialog.html',
                    controller: 'ThresholdDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Threshold', function(Threshold) {
                            return Threshold.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('threshold', null, { reload: 'threshold' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('threshold.delete', {
            parent: 'threshold',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/threshold/threshold-delete-dialog.html',
                    controller: 'ThresholdDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Threshold', function(Threshold) {
                            return Threshold.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('threshold', null, { reload: 'threshold' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
