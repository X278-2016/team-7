(function() {
    'use strict';

    angular
        .module('team7App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pad-set', {
            parent: 'entity',
            url: '/pad-set',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PadSets'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pad-set/pad-sets.html',
                    controller: 'PadSetController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('pad-set-detail', {
            parent: 'entity',
            url: '/pad-set/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PadSet'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pad-set/pad-set-detail.html',
                    controller: 'PadSetDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PadSet', function($stateParams, PadSet) {
                    return PadSet.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pad-set',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pad-set-detail.edit', {
            parent: 'pad-set-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pad-set/pad-set-dialog.html',
                    controller: 'PadSetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PadSet', function(PadSet) {
                            return PadSet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pad-set.new', {
            parent: 'pad-set',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pad-set/pad-set-dialog.html',
                    controller: 'PadSetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                latitude: null,
                                longitude: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pad-set', null, { reload: 'pad-set' });
                }, function() {
                    $state.go('pad-set');
                });
            }]
        })
        .state('pad-set.edit', {
            parent: 'pad-set',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pad-set/pad-set-dialog.html',
                    controller: 'PadSetDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PadSet', function(PadSet) {
                            return PadSet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pad-set', null, { reload: 'pad-set' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pad-set.delete', {
            parent: 'pad-set',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pad-set/pad-set-delete-dialog.html',
                    controller: 'PadSetDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PadSet', function(PadSet) {
                            return PadSet.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pad-set', null, { reload: 'pad-set' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
