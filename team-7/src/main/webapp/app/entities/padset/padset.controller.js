(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadsetController', PadsetController);

    PadsetController.$inject = ['$scope', '$state', 'Padset', 'PadsetSearch'];

    function PadsetController ($scope, $state, Padset, PadsetSearch) {
        var vm = this;
        
        vm.padsets = [];
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Padset.query(function(result) {
                vm.padsets = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            PadsetSearch.query({query: vm.searchQuery}, function(result) {
                vm.padsets = result;
            });
        }    }
})();
