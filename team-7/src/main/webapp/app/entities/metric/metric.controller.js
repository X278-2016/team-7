(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('MetricController', MetricController);

    MetricController.$inject = ['$scope', '$state', 'Metric', 'MetricSearch'];

    function MetricController ($scope, $state, Metric, MetricSearch) {
        var vm = this;
        
        vm.metrics = [];
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Metric.query(function(result) {
                vm.metrics = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            MetricSearch.query({query: vm.searchQuery}, function(result) {
                vm.metrics = result;
            });
        }    }
})();
