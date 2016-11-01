(function() {
    'use strict';

    angular
        .module('team7App')
        .factory('EquipmentSetSearch', EquipmentSetSearch);

    EquipmentSetSearch.$inject = ['$resource'];

    function EquipmentSetSearch($resource) {
        var resourceUrl =  'api/_search/equipment-sets/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
