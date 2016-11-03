(function() {
    'use strict';

    angular
        .module('team7App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('metric', {
            parent: 'entity',
            url: '/metric',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Metrics'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/metric/metrics.html',
                    controller: 'MetricController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('metric-detail', {
            parent: 'entity',
            url: '/metric/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Metric'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/metric/metric-detail.html',
                    controller: 'MetricDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Metric', function($stateParams, Metric) {
                    return Metric.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'metric',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('metric-detail.edit', {
            parent: 'metric-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/metric/metric-dialog.html',
                    controller: 'MetricDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Metric', function(Metric) {
                            return Metric.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('metric.new', {
            parent: 'metric',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/metric/metric-dialog.html',
                    controller: 'MetricDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                mMetricName: null,
                                mMetricValue: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('metric', null, { reload: 'metric' });
                }, function() {
                    $state.go('metric');
                });
            }]
        })
        .state('metric.edit', {
            parent: 'metric',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/metric/metric-dialog.html',
                    controller: 'MetricDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Metric', function(Metric) {
                            return Metric.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('metric', null, { reload: 'metric' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('metric.delete', {
            parent: 'metric',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/metric/metric-delete-dialog.html',
                    controller: 'MetricDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Metric', function(Metric) {
                            return Metric.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('metric', null, { reload: 'metric' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
