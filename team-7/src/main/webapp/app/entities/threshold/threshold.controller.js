(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('ThresholdController', ThresholdController);

    ThresholdController.$inject = ['$scope', '$state', 'Threshold', 'ThresholdSearch'];

    function ThresholdController ($scope, $state, Threshold, ThresholdSearch) {
        var vm = this;
        
        vm.thresholds = [];
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Threshold.query(function(result) {
                vm.thresholds = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            ThresholdSearch.query({query: vm.searchQuery}, function(result) {
                vm.thresholds = result;
            });
        }    }
})();
