(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadSetController', PadSetController);

    PadSetController.$inject = ['$scope', '$state', 'PadSet', 'PadSetSearch'];

    function PadSetController ($scope, $state, PadSet, PadSetSearch) {
        var vm = this;
        
        vm.padSets = [];
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            PadSet.query(function(result) {
                vm.padSets = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            PadSetSearch.query({query: vm.searchQuery}, function(result) {
                vm.padSets = result;
            });
        }    }
})();
